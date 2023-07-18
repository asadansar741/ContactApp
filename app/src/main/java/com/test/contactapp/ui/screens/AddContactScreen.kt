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
fun AddContactScreen(mainViewModel: MainViewModel){
    Scaffold(topBar = { AppBar("Login") }) { padding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            AddContactUI(mainViewModel)
        }
    }
}



@Composable
fun AddContactUI(mainViewModel: MainViewModel){

    var name by remember { mutableStateOf("") }
    var job by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Name",
            style = MaterialTheme.typography.h6,
        )
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(
                "Enter Name",
                style = TextStyle(color = Color.Gray)
            ) },
            maxLines = 1,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 18.sp),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
        )
        Text(
            text = "Job",
            style = MaterialTheme.typography.h6,
        )
        TextField(
            value = job,
            onValueChange = { job = it },
            label = { Text(
                "Enter Job",
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
                if (name.isEmpty() || job.isEmpty()){
                    mainViewModel.showCustomDialog("Invalid data")
                }
                else{
                    mainViewModel.addContact(name,job)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            content = {
                Text(
                    text = "Add Contact",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            colors = buttonColors(Color.Red),
        )
    }

}