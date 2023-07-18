package com.test.contactapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.test.contactapp.ui.MainViewModel
import com.test.contactapp.ui.screens.*
import com.test.contactapp.util.Destination
import com.test.contactapp.util.LOGIN_SCREEN


@Composable
fun ContactAppNavigation(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val navHostController = rememberNavController()
    mainViewModel.naveHostController = navHostController
    NavHost(navController = navHostController, startDestination = LOGIN_SCREEN ){
        composable(Destination.Login.rout){
            LoginScreen(mainViewModel)
        }
        composable(Destination.ContactList.rout){
            ContactListScreen(mainViewModel)
        }
        composable(Destination.AddContact.rout){
            AddContactScreen(mainViewModel)
        }
    }

    if (mainViewModel.isCustomDialogShown){
        CustomDialog(message = mainViewModel.message) {
            mainViewModel.dismissCustomDialog()
        }
    }
    if (mainViewModel.isLoadingDialogShown){
        LoadingDialog {
            mainViewModel.dismissLoadingDialog()
        }
    }
}