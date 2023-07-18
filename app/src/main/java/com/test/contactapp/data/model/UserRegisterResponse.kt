package com.test.contactapp.data.model

data class UserRegisterResponse(
    val id: Int,
    val token: String,
    val error: String
)