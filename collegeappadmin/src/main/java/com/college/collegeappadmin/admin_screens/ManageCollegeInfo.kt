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
import coil.compose.rememberAsyncImagePainter
import com.college.collegeappadmin.itemviews.NoticeItemView
import com.college.collegeappadmin.utils.Constant
import com.college.collegeappadmin.utils.Constant.BANNER
import com.college.collegeappadmin.viewmodels.CollegeInfoViewModel
import com.intuit.sdp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageCollegeInfo(navController: NavController) {
    val context = LocalContext.current

    val collegeInfoViewModel : CollegeInfoViewModel = viewModel()

    val isUploaded by collegeInfoViewModel.isPosted.observeAsState(false)

    val collegeInfo by collegeInfoViewModel.collegeInfo.observeAsState(null)

    collegeInfoViewModel.getCollegeInfo()

    var title by remember {
        mutableStateOf("")
    }

    var link by remember {
        mutableStateOf("")
    }

    var desc by remember {
        mutableStateOf("")
    }

    var address by remember {
        mutableStateOf("")
    }

    var imageUrl by remember {
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
            Toast.makeText(context, "CollegeInfo Uploaded", Toast.LENGTH_SHORT).show()
            imageUri = null
        }
    }

    LaunchedEffect(collegeInfo) {
        if (collegeInfo != null) {
            title = collegeInfo!!.name!!
            link = collegeInfo!!.websiteLink!!
            desc = collegeInfo!!.desc!!
            address = collegeInfo!!.address!!
            imageUrl = collegeInfo!!.imageUrl!!
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        "Manage CollegeInfo",
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
        },
    ) { padding ->

        Column(modifier = Modifier.padding(padding)) {


            ElevatedCard(modifier = Modifier.padding(dimensionResource(id = R.dimen._8sdp))) {


                OutlinedTextField(
                    value = title, onValueChange = {
                        title = it
                    },

                    placeholder = { Text(text = "College Name") },
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen._4sdp))
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = desc, onValueChange = {
                        desc = it
                    },

                    placeholder = { Text(text = "College Description") },
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen._4sdp))
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = address, onValueChange = {
                        address = it
                    },

                    placeholder = { Text(text = "College Address") },
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen._4sdp))
                        .fillMaxWidth()
                )

                OutlinedTextField(
                    value = link, onValueChange = {
                        link = it
                    },

                    placeholder = { Text(text = "Website Link") },
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen._4sdp))
                        .fillMaxWidth()
                )



                Image(
                    painter = if (imageUrl != "") rememberAsyncImagePainter(model = imageUrl)
                    else if (imageUri == null) painterResource(id = com.college.collegeappadmin.R.drawable.gallery_icon)
                    else rememberAsyncImagePainter(model = imageUri),
                    contentDescription = BANNER,
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen._220sdp))
                        .fillMaxWidth()
                        .clickable {
                            launcher.launch("image/*")
                        },
                    contentScale = ContentScale.Crop
                )

                Row {
                    Button(
                        onClick = {

                            if (title == "" || desc == "" || address == "" || link == "") {
                                Toast.makeText(
                                    context,
                                    "Please provide all fields",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else if(imageUrl != "")
                                collegeInfoViewModel.uploadImage(
                                    imageUrl,
                                    title,
                                    address,
                                    desc,
                                    link
                                )
                            else if (imageUri == null) {
                                Toast.makeText(
                                    context,
                                    "Please provide image",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                collegeInfoViewModel.saveImage(
                                    imageUri!!,
                                    title,
                                    address,
                                    desc,
                                    link
                                )
                            }
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .padding(dimensionResource(id = R.dimen._4sdp))


                    ) {
                        Text(text = "Update College Info")
                    }
                }
            }
        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun CollegeInfoPreview(){

}
