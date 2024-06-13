package com.college.onlymodi.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.college.onlymodi.itemviews.FacultyDetailScreen
import com.college.onlymodi.screens.AboutUs
import com.college.onlymodi.screens.BottomNav
import com.college.onlymodi.screens.Faculty
import com.college.onlymodi.screens.Gallery
import com.college.onlymodi.screens.Home

@Composable
fun NavGraph(navController:NavHostController){

    NavHost(
        navController = navController,
        startDestination = Routes.BottomNav.routes
    ){

        composable(Routes.BottomNav.routes){
            BottomNav(navController)
        }

        composable(Routes.Home.routes){
            Home(navController)
        }
        composable(Routes.Gallery.routes){
            Gallery(navController)
        }
        composable(Routes.AboutUs.routes){
            AboutUs(navController)
        }
        composable(Routes.Faculty.routes){
            Faculty(navController)
        }

        composable(Routes.FacultyDetailScreen.routes){

            val data = it.arguments!!.getString("category")

            FacultyDetailScreen(navController, data!!)
        }
    }
}