package com.test.contactapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.test.contactapp.ui.MainViewModel

@Composable
fun RegisterUserScreen(mainViewModel: MainViewModel){
    Scaffold(topBar = { AppBar("Register") }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            RegisterUserUI(mainViewModel)
        }
    }
}



@Composable
fun RegisterUserUI(mainViewModel: MainViewModel){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Email",
            style = MaterialTheme.typography.h6,
        )
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(
                "Enter Email",
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
                if (email.isEmpty() || password.isEmpty()){
                    mainViewModel.showCustomDialog("Invalid data")
                }
                else{
                    mainViewModel.registerUser(email,password)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            content = {
                Text(
                    text = "Register User",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = buttonColors(Color.Red),
        )
    }

}