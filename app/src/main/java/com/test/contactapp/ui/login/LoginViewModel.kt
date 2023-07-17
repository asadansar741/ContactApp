package com.test.contactapp.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.contactapp.data.model.LoginRequest
import com.test.contactapp.data.repository.ContactRepository
import com.test.contactapp.util.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(contactRepository: ContactRepository) : ViewModel() {

    private val repository = contactRepository

    fun userLogin(userName: String, password: String) {
        if (userName.isNotEmpty() && password.isNotEmpty()){
            val loginRequest = LoginRequest(
                userName,
                password
            )
            viewModelScope.launch {
                repository.loginUser(loginRequest).collect{
                    when(it){
                        is ApiState.Loading ->{
//                            _isLoadingState.postValue(true)
                        }
                        is ApiState.Success ->{
//                            _isLoadingState.postValue(false)
                            val loginResponse = it.data!!
                            navigateToContactListScreen()
                        }
                        is ApiState.Failure ->{
//                            _isLoadingState.postValue(false)
                        }
                    }
                }
            }
        }
        else{
            // show dialog to invalid username or password
        }
    }

    fun navigateToContactListScreen(){

    }

}