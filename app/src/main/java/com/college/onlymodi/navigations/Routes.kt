package com.college.onlymodi.navigations

sealed class Routes(val routes:String){


    object Home : Routes("home")

    object Faculty : Routes("faculty")

    object Gallery : Routes("gallery")

    object AboutUs : Routes("about_us")

    object BottomNav : Routes("bottom_nav")

    object FacultyDetailScreen : Routes("faculty_detail_screen/{category}")

}