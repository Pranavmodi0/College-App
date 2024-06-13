package com.college.collegeappadmin.admin_screens

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import com.college.collegeappadmin.R
import com.college.collegeappadmin.models.DashboardItemModel
import com.college.collegeappadmin.navigations.Routes
import com.college.collegeappadmin.ui.theme.Cream
import com.college.collegeappadmin.ui.theme.Purple40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboard(navController: NavController) {


    val list = listOf(
        DashboardItemModel("Manage Banner",
           Routes.ManageBanner.routes),
        DashboardItemModel("Manage Notice",
            Routes.ManageNotice.routes),
        DashboardItemModel("Manage Gallery",
            Routes.ManageGallery.routes),
        DashboardItemModel("Manage Faculty",
            Routes.ManageFaculty.routes),
        DashboardItemModel("Manage College Info",
            Routes.ManageCollegeInfo.routes),
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("SSPM Admin",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center)},
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = Cream
                )
            )
        },
        content = {padding->
            LazyColumn(modifier = Modifier.padding(padding)){
                items(items = list, itemContent = {
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp))
                        .clickable {

                            navController.navigate(it.route)


                        }) {
                        Text(
                            text = it.title,
                            modifier = Modifier.padding(
                                horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp),
                                vertical = dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)
                            ),
                            fontWeight = FontWeight.Bold,
                            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._15ssp).value.sp
                        )
                    }
                })
            }
        }
    )
}

@Preview(showSystemUi = true)
@Composable
fun AdminDashboardPreview(){
    AdminDashboard(navController = rememberNavController())
}