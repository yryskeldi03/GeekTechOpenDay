package com.example.geektechopenday

import com.example.geektechopenday.model.Registration
import com.example.geektechopenday.model.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface Service {

    @POST("api/v1/users/register/")
    fun registerUser(
        @Body newUser: Registration
    ): Call<RegistrationResponse>

}