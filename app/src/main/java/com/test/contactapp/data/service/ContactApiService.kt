package com.test.contactapp.data.service

import com.test.contactapp.data.model.LoginRequest
import retrofit2.Response
import javax.inject.Inject

class ContactApiService @Inject constructor(private val api: ContactApi){

    suspend fun login(
        loginRequest: LoginRequest
    ): Response<String> = api.login(loginRequest)

}