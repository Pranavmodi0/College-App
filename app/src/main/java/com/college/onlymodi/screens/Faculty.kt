package com.college.onlymodi.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.college.onlymodi.itemviews.FacultyItemView
import com.college.onlymodi.navigations.Routes
import com.college.onlymodi.viewmodels.FacultyViewModel


@Composable
fun Faculty(navController: NavController) {

    val facultyViewModel : FacultyViewModel = viewModel()
    val faultyList by facultyViewModel.categoryList.observeAsState(null)

    facultyViewModel.getCategory()

    LazyColumn {
        items(faultyList ?: emptyList()){
            FacultyItemView(it, onClick = {categoryName->
                val routes = Routes.FacultyDetailScreen.routes.replace("{category}", categoryName)
                navController.navigate(routes)
            })
        }
    }
}