package com.test.contactapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.contactapp.ui.MainViewModel
import com.test.contactapp.ui.theme.ContactAppTheme

@Composable
fun LoginScreen(mainViewModel: MainViewModel){
    Scaffold(topBar = { AppBar("Login") }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            LoginScreenUI(mainViewModel)
        }
    }
}

@Composable
fun AppBar(title: String) {
    TopAppBar(
        title = { Text(title) }
    )
}

//@Preview(showBackground = true)
//@Composable
//fun LoginScreenPreview() {
//    ContactAppTheme() {
//        LoginScreen(mainViewModel = MainViewModel())
//    }
//}

@Composable
fun LoginScreenUI(mainViewModel: MainViewModel){

    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "User Name",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = userName,
            onValueChange = { userName = it },
            label = { Text(
                "Enter User Name",
                style = TextStyle(color = Color.Gray)
            ) },
            maxLines = 1,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )
        Text(
            text = "Password",
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(
                "Enter Password",
                style = TextStyle(color = Color.Gray)
            ) },
            maxLines = 1,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )
    }

    Box (modifier = Modifier
        .padding(10.dp,50.dp),
        contentAlignment = Alignment.BottomCenter
    ){
        Button(
            onClick = {
                if (userName.isEmpty() || password.isEmpty()){
                    mainViewModel.showCustomDialog("Invalid User Name or Password")
                }
                else{
                    mainViewModel.userLogin(userName,password)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            content = {
                Text(
                    text = "Submit",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = buttonColors(Color.Red),
        )
    }

}