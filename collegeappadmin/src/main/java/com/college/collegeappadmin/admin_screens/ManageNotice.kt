package com.college.collegeappadmin.admin_screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.college.collegeappadmin.R
import com.college.collegeappadmin.itemviews.BannerItemView
import com.college.collegeappadmin.itemviews.NoticeItemView
import com.college.collegeappadmin.ui.theme.Cream
import com.college.collegeappadmin.utils.Constant
import com.college.collegeappadmin.viewmodels.NoticeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageNotice(navController: NavController) {
    val context = LocalContext.current

    val noticeViewModel : NoticeViewModel = viewModel()

    val isUploaded by noticeViewModel.isPosted.observeAsState(false)

    val isDeleted by noticeViewModel.isDeleted.observeAsState(false)


    val bannerList by noticeViewModel.noticeList.observeAsState(null)

    noticeViewModel.getNotice()

    var isNotice by remember {
        mutableStateOf(false)
    }

    var title by remember {
        mutableStateOf("")
    }

    var link by remember {
        mutableStateOf("")
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
            Toast.makeText(context, "Notice Uploaded", Toast.LENGTH_SHORT).show()
            imageUri = null
            isNotice = false
        }
    }

    LaunchedEffect(isDeleted) {
        if (isDeleted){
            Toast.makeText(context, "Notice Deleted", Toast.LENGTH_SHORT).show()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(title = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        "Manage Notice",
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
                isNotice = true
            }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = null)
            }
        }
    ) {padding->

        Column(modifier = Modifier.padding(padding)) {


            if (isNotice)

                ElevatedCard(modifier = Modifier.padding(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp))) {


                    OutlinedTextField(value = title, onValueChange = {
                        title = it
                    },

                        placeholder = { Text(text = "Notice Title")},
                        modifier = Modifier
                            .padding(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp))
                            .fillMaxWidth()
                        )

                    OutlinedTextField(value = link, onValueChange = {
                        link = it
                    },

                        placeholder = { Text(text = "Notice Link")},
                        modifier = Modifier
                            .padding(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp))
                            .fillMaxWidth()
                    )



                    Image(painter = if (imageUri == null) painterResource(id = R.drawable.gallery_icon)
                            else rememberAsyncImagePainter(model = imageUri),
                        contentDescription = Constant.BANNER,
                        modifier = Modifier
                            .height(dimensionResource(id = com.intuit.sdp.R.dimen._220sdp))
                            .fillMaxWidth()
                            .clickable {
                                launcher.launch("image/*")
                            },
                        contentScale = ContentScale.Crop
                    )

                    Row {
                        Button(onClick = {

                            if (imageUri == null){
                                Toast.makeText(context, "Please provide image", Toast.LENGTH_SHORT).show()
                            } else if (title == ""){
                                Toast.makeText(context, "Please provide title", Toast.LENGTH_SHORT).show()
                            } else {
                                noticeViewModel.saveNotice(imageUri!!, title, link)
                            }
                        },

                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp))


                        ) {
                            Text(text = "Add Notice")
                        }

                        OutlinedButton(onClick = { imageUri = null
                                                 isNotice == false},

                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp))


                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }

            LazyColumn {
                items(bannerList ?: emptyList()){
                    NoticeItemView(it, delete = {docId ->
                        noticeViewModel.deleteNotice(docId)
                    })
                }
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun NoticePreview(){
 ManageNotice(navController = rememberNavController())
}