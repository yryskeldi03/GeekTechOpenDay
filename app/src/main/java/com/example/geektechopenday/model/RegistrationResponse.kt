package com.example.geektechopenday.model

data class RegistrationResponse(
    val user: User,
    val refresh: String,
    val access: String
)

data class User(
    val id: Int,
    val username: String,
    val date_joined: String
)