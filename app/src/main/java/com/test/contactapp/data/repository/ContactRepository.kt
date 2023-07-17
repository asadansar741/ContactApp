package com.test.contactapp.data.repository

import com.test.contactapp.data.model.LoginRequest
import com.test.contactapp.data.service.ContactApiService
import com.test.contactapp.util.toResultFlow
import javax.inject.Inject


class ContactRepository @Inject constructor(private val apiService: ContactApiService){

    fun loginUser(
        loginRequest: LoginRequest
    ) = toResultFlow {
        apiService.login(loginRequest)
    }
}