package com.test.contactapp.data.service

import com.test.contactapp.data.model.LoginRequest
import com.test.contactapp.data.model.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class ContactApiService @Inject constructor(private val api: ContactApi){

    suspend fun login(
        loginRequest: LoginRequest
    ): Response<LoginResponse> = api.login(loginRequest)

}