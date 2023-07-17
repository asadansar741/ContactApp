package com.test.contactapp.ui

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.test.contactapp.data.model.LoginRequest
import com.test.contactapp.data.repository.ContactRepository
import com.test.contactapp.util.ApiState
import com.test.contactapp.util.Destination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel
@Inject
constructor(
    private val repository: ContactRepository
): ViewModel() {

    lateinit var naveHostController: NavHostController

    var isCustomDialogShown by mutableStateOf(false)
    var isLoadingDialogShown by mutableStateOf(false)
    var message by mutableStateOf("")
        private set


    private fun navigateToContactListScreen() {
        naveHostController.navigate(Destination.ContactList.rout)
    }
    fun navigateToAddContactScreen() {
        naveHostController.navigate(Destination.AddContact.rout)
    }


    fun showCustomDialog(message:String){
        this.message = message
        isCustomDialogShown = true
    }
    fun dismissCustomDialog(){
        isCustomDialogShown = false
    }

    fun showLoadingDialog(){
        isLoadingDialogShown = true
    }
    fun dismissLoadingDialog(){
        isLoadingDialogShown = false
    }

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
                            Log.d("MyTag", "Loading")
                        }
                        is ApiState.Success ->{
                            Log.d("MyTag", "Success")
//                            _isLoadingState.postValue(false)
                            val token = it.data!!.token
                            navigateToContactListScreen()

                        }
                        is ApiState.Failure ->{
                            Log.d("MyTag", it.message)
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
}