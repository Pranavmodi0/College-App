package com.college.collegeappadmin.admin_screens

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.college.collegeappadmin.itemviews.BannerItemView
import com.college.collegeappadmin.ui.theme.Cream
import com.college.collegeappadmin.utils.Constant
import com.college.collegeappadmin.utils.Constant.BANNER
import com.college.collegeappadmin.viewmodels.BannerViewModel
import com.college.collegeappadmin.widget.LoadingDialog
import com.intuit.sdp.R

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageBanner(navController: NavController) {

    val context = LocalContext.current

    val bannerViewModel : BannerViewModel = viewModel()

    val isUploaded by bannerViewModel.isPosted.observeAsState(false)

    val isDeleted by bannerViewModel.isDeleted.observeAsState(false)


    val bannerList by bannerViewModel.bannerList.observeAsState(null)

    bannerViewModel.getBanner()

    val showLoader = remember {
        mutableStateOf(false)
    }

    if (showLoader.value){
        LoadingDialog(onDismissRequest =  {
            showLoader.value = false
        })
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()) {
        imageUri = it
    }

    LaunchedEffect(isUploaded) {
        if (isUploaded){
            showLoader.value = false
            Toast.makeText(context, "Image Uploaded", Toast.LENGTH_SHORT).show()
            imageUri = null
        }
    }

    LaunchedEffect(isDeleted) {
        if (isDeleted){
            Toast.makeText(context, "Image Deleted", Toast.LENGTH_SHORT).show()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(title = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        "Manage Banner",
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                },
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        containerColor = Color.White
                    ),
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                        }
                    })
            })
        }, floatingActionButton = {
            FloatingActionButton(onClick = {
                launcher.launch("image/*")
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {padding->

        Column(modifier = Modifier.padding(padding)) {


            if (imageUri != null)

            ElevatedCard(modifier = Modifier.padding(dimensionResource(id = R.dimen._8sdp))) {
                Image(painter = rememberAsyncImagePainter(model = imageUri),
                    contentDescription = BANNER,
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen._220sdp))
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )

                Row {
                    Button(onClick = {
                        showLoader.value = true
                        bannerViewModel.saveImage(imageUri!!)
                    },

                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(dimensionResource(id = R.dimen._4sdp))


                    ) {
                        Text(text = "Add Image")
                    }

                    OutlinedButton(onClick = { imageUri = null },

                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(dimensionResource(id = R.dimen._4sdp))


                    ) {
                        Text(text = "Cancel")
                    }
                }
            }

            LazyColumn {
                items(bannerList ?: emptyList()){
                    BannerItemView(bannerModel = it, delete = {docId ->
                        bannerViewModel.deleteBanner(docId)
                    })
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun BannerPreview(){
    ManageBanner(navController = rememberNavController())
}
