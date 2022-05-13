package com.example.geektechopenday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.geektechopenday.databinding.ActivityMainBinding
import com.example.geektechopenday.model.Registration
import com.example.geektechopenday.model.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var screen: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screen = ActivityMainBinding.inflate(layoutInflater)
        setContentView(screen.root)

        if (App.prefs.getBoolean("isRegistered", false)) {
            screen.tvTitle.text = "Авторизация"
            screen.etEnterPasswordAgain.gone()
            screen.button.text = "Войти"
        }

        screen.button.setOnClickListener {
            App.service.registerUser(
                Registration(
                    screen.etName.text(),
                    screen.etPassword.text(),
                    screen.etEnterPasswordAgain.text()
                )
            ).enqueue(object : Callback<RegistrationResponse> {
                override fun onResponse(
                    call: Call<RegistrationResponse>,
                    response: Response<RegistrationResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        screen.registrationLayout.gone()
                        screen.imageSuccess.show()
                        App.prefs.edit().putBoolean("isRegistered", true).apply()
                    }
                }

                override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
                    Log.e("TAG", "onFailure: ${t.localizedMessage}")
                }
            })
        }
    }
}