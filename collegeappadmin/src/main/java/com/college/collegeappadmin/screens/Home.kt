package com.college.collegeappadmin.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController) {
    Text(text = "home")
}


@Preview(showSystemUi = true)
@Composable
fun HomePreview(){

}
