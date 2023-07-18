package com.test.contactapp.data.repository

import com.test.contactapp.data.model.ContactListPagedResponse
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

    suspend fun getContactPagedList(
        pageNumber: Int,
    ): ContactListPagedResponse? {
        val request = apiService.getContactPagedList(pageNumber)
        if (request.failed || !request.isSuccessful) {
            return null
        }
        return request.body
    }
}