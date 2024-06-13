package com.college.collegeappadmin.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.college.collegeappadmin.admin_screens.AdminDashboard
import com.college.collegeappadmin.admin_screens.FacultyDetailScreen
import com.college.collegeappadmin.admin_screens.ManageBanner
import com.college.collegeappadmin.admin_screens.ManageCollegeInfo
import com.college.collegeappadmin.admin_screens.ManageFaculty
import com.college.collegeappadmin.admin_screens.ManageGallery
import com.college.collegeappadmin.admin_screens.ManageNotice
import com.college.collegeappadmin.screens.AboutUs
import com.college.collegeappadmin.screens.BottomNav
import com.college.collegeappadmin.screens.Faculty
import com.college.collegeappadmin.screens.Gallery
import com.college.collegeappadmin.screens.Home
import com.college.collegeappadmin.utils.Constant.isAdmin

@Composable
fun NavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = if (isAdmin) Routes.AdminDashboard.routes else Routes.BottomNav.routes
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
        composable(Routes.AdminDashboard.routes){
            AdminDashboard(navController)
        }
        composable(Routes.ManageBanner.routes){
            ManageBanner(navController)
        }
        composable(Routes.ManageFaculty.routes){
            ManageFaculty(navController)
        }
        composable(Routes.ManageGallery.routes){
            ManageGallery(navController)
        }

        composable(Routes.ManageNotice.routes){
            ManageNotice(navController)
        }
        composable(Routes.ManageCollegeInfo.routes){
            ManageCollegeInfo(navController)
        }
        composable(Routes.FacultyDetailScreen.routes){

            val data = it.arguments!!.getString("category")

            FacultyDetailScreen(navController, data!!)
        }
    }
}