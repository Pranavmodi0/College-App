package com.college.onlymodi.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.college.onlymodi.R
import com.college.onlymodi.models.BottomNavItem

import com.college.onlymodi.models.NavItem
import com.college.onlymodi.navigations.Routes
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNav(navController: NavController) {


    val navController1 = rememberNavController();

    val context: Context = LocalContext.current

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    val list = listOf(
        NavItem(
            "Website",
            R.drawable.website
        ),
        NavItem(
            "Notice",
            R.drawable.notice
        ),
        NavItem(
            "Notes",
            R.drawable.notes
        ),
        NavItem(
            "Contact Us",
            R.drawable.contact_us
        )
    )

    ModalNavigationDrawer(drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(painter = painterResource(id = R.drawable.nav_banner), contentDescription = null,
                    modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._220sdp)),
                    contentScale = ContentScale.Crop)

                Divider()
                Text(text = "")

                list.forEachIndexed{index, item ->  
                    
                    NavigationDrawerItem(label = { 
                                                 Text(text = item.title)
                    }, selected = index == selectedItemIndex, onClick = {
                        Toast.makeText(context, item.title, Toast.LENGTH_SHORT).show()

                        scope.launch {
                            drawerState.close()
                        }
                    },
                        icon = {
                            Icon(painter = painterResource(id = item.icon), contentDescription = null,
                                modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._24sdp)))
                        })
                } 
            }
        },
        content = {
            Scaffold(

                topBar = {
                    CenterAlignedTopAppBar(title = { Text("SSPM", fontWeight = FontWeight.Bold, textAlign = TextAlign.Center) },
                        navigationIcon = {
                            IconButton(onClick = {scope.launch {drawerState.open()}}) {
                                Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                            }
                        })
                },
                bottomBar = {
                    MyBottomNav(navController = navController1)
                }
            ) {padding ->

                NavHost(navController = navController1 ,
                    startDestination = Routes.Home.routes,
                    modifier = Modifier.padding(padding)){
                    composable(route = Routes.Home.routes){
                        Home(navController1)
                    }
                    composable(Routes.Gallery.routes){
                        Gallery(navController1)
                    }
                    composable(Routes.AboutUs.routes){
                        AboutUs(navController1)
                    }
                    composable(Routes.Faculty.routes){
                        Faculty(navController)
                    }
                }
            }
        }) 

}

@Composable
fun MyBottomNav(navController: NavController){
    val backStackEntry = navController.currentBackStackEntryAsState()

    val list = listOf(
        BottomNavItem(
            "Home",
            R.drawable.home,
            Routes.Home.routes
        ),
        BottomNavItem(
            "Faculty",
            R.drawable.faculty,
            Routes.Faculty.routes
        ),
        BottomNavItem(
            "Gallery",
            R.drawable.image,
            Routes.Gallery.routes
        ),
        BottomNavItem(
            "About Us",
            R.drawable.about_us,
            Routes.AboutUs.routes
        )
    )

    BottomAppBar {
        list.forEach{

            val  curRoute = it.route
            val otherRoute = 
                try {
                    backStackEntry.value!!.destination.route
                } catch (e: Exception) {
                    Routes.Home.routes
                }
            
            val selected = curRoute == otherRoute
            
            NavigationBarItem(selected = selected,
                onClick = { navController.navigate(it.route){
                    popUpTo(navController.graph.findStartDestination().id){
                        saveState = true
                    }
                    launchSingleTop = true
                } },

                icon = {
                    Icon(painterResource(id = it.icon)  , contentDescription = it.title,
                        modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._24sdp)))
                })
        }
    }
}