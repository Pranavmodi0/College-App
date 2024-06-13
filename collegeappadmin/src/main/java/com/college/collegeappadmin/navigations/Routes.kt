package com.college.collegeappadmin.navigations

sealed class Routes(val routes:String){


    object Home : Routes("home")

    object Faculty : Routes("faculty")

    object Gallery : Routes("gallery")

    object AboutUs : Routes("about_us")

    object BottomNav : Routes("bottom_nav")

    object AdminDashboard : Routes("admin_dashboard")

    object ManageBanner : Routes("manage_banner")
    object ManageFaculty : Routes("manage_faculty")
    object ManageGallery : Routes("manage_gallery")
    object ManageNotice : Routes("manage_notice")
    object ManageCollegeInfo : Routes("manage_college_info")
    object FacultyDetailScreen : Routes("faculty_detail_screen/{category}")

}