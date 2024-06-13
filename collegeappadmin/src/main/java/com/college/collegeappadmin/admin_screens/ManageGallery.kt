package com.college.collegeappadmin.admin_screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.college.collegeappadmin.itemviews.FacultyItemView
import com.college.collegeappadmin.itemviews.GalleryItemView
import com.college.collegeappadmin.navigations.Routes
import com.college.collegeappadmin.utils.Constant
import com.college.collegeappadmin.viewmodels.GalleryViewModel
import com.intuit.sdp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageGallery(navController: NavController) {
    val context = LocalContext.current

    val galleryViewModel : GalleryViewModel = viewModel()

    val isUploaded by galleryViewModel.isPosted.observeAsState(false)

    val isDeleted by galleryViewModel.isDeleted.observeAsState(false)


    val galleryList by galleryViewModel.galleryList.observeAsState(null)

    val option = arrayListOf<String>()

    galleryViewModel.getGallery()

    var isCategory by remember {
        mutableStateOf(false)
    }

    var mExpanded by remember {
        mutableStateOf(false)
    }

    var isImage by remember {
        mutableStateOf(false)
    }

    var category by remember {
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
            Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show()
            imageUri = null
            isCategory = false
            category = ""
        }
    }

    LaunchedEffect(isDeleted) {
        if (isDeleted){
            Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(title = {
                CenterAlignedTopAppBar(title = {
                    Text(
                        "Manage Gallery",
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
    ) {padding->

        Column(modifier = Modifier.padding(padding)) {

            Row(modifier = Modifier.padding(dimensionResource(id = R.dimen._8sdp))) {

                Card(modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen._4sdp))
                    .clickable {
                        isCategory = true
                        isImage = false
                    }) {
                    Text(text = "Add Category",
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._17ssp).value.sp,
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen._8sdp))
                            .fillMaxWidth(), textAlign = TextAlign.Center
                    )
                }

                Card(modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen._4sdp))
                    .clickable {
                        isCategory = false
                        isImage = true
                    }) {
                    Text(text = "Add Image",
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._17ssp).value.sp,
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen._8sdp))
                            .fillMaxWidth(), textAlign = TextAlign.Center
                    )
                }
            }

            if (isCategory)
                ElevatedCard(modifier = Modifier.padding(dimensionResource(id = R.dimen._8sdp))) {
                    OutlinedTextField(value = category, onValueChange = {
                        category = it
                    },

                        placeholder = { Text(text = "Category")},
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen._4sdp))
                            .fillMaxWidth()
                    )

                    Image(painter = if (imageUri == null) painterResource(id = com.college.collegeappadmin.R.drawable.gallery_icon)
                    else rememberAsyncImagePainter(model = imageUri),
                        contentDescription = Constant.BANNER,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen._220sdp))
                            .fillMaxWidth()
                            .clickable {
                                launcher.launch("image/*")
                            },
                        contentScale = ContentScale.Crop
                    )

                    Row {
                        Button(onClick = {
                            if (category == "" && imageUri == null){
                                Toast.makeText(context, "Please provide all fields", Toast.LENGTH_SHORT).show()
                            } else {
                                galleryViewModel.saveGalleryImage(imageUri!!, category, true)
                            }
                        },

                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(dimensionResource(id = R.dimen._4sdp))


                        ) {
                            Text(text = "Add Category")
                        }

                        OutlinedButton(onClick = {
                            imageUri = null
                            isCategory == false
                            isImage == false},

                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(dimensionResource(id = R.dimen._4sdp))


                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }


            if (isImage)

                ElevatedCard(modifier = Modifier.padding(dimensionResource(id = R.dimen._8sdp))) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Box(modifier = Modifier.wrapContentSize(Alignment.BottomEnd)) {
                            OutlinedTextField(
                                value = category, onValueChange = {
                                    category = it
                                },
                                readOnly = true,
                                placeholder = { Text(text = "Select your Gallery") },
                                label = { Text(text = "Gallery Name") },
                                modifier = Modifier
                                    .padding(dimensionResource(id = R.dimen._4sdp))
                                    .fillMaxWidth(),
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = mExpanded)
                                }
                            )

                            DropdownMenu(
                                expanded = mExpanded,
                                onDismissRequest = { mExpanded = false }) {

                                if (galleryList != null && galleryList!!.isNotEmpty()) {
                                    option.clear()
                                    for (data in galleryList!!) {
                                        option.add(data.category!!)
                                    }
                                }

                                option.forEach { selectOptions ->
                                    DropdownMenuItem(text = { Text(text = selectOptions) },
                                        onClick = {
                                            category = selectOptions
                                            mExpanded = false
                                        },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }

                            }

                            Spacer(modifier = Modifier
                                .matchParentSize()
                                .padding(dimensionResource(id = R.dimen._10sdp))
                                .clickable {
                                    mExpanded = !mExpanded
                                })
                        }

                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._5sdp)))
                        Image(painter = if (imageUri == null) painterResource(id = com.college.collegeappadmin.R.drawable.gallery_icon)
                        else rememberAsyncImagePainter(model = imageUri),
                            contentDescription = Constant.BANNER,
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen._220sdp))
                                .fillMaxWidth()
                                .clickable {
                                    launcher.launch("image/*")
                                },
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._5sdp)))


                        Row {
                            Button(
                                onClick = {

                                    if (imageUri == null) {
                                        Toast.makeText(
                                            context,
                                            "Please provide image",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (category == "") {
                                        Toast.makeText(
                                            context,
                                            "Please provide category",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        galleryViewModel.saveGalleryImage(
                                            imageUri!!,
                                            category, false
                                        )
                                    }
                                },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(dimensionResource(id = R.dimen._4sdp))


                            ) {
                                Text(text = "Add Image")
                            }

                            OutlinedButton(
                                onClick = {
                                    imageUri = null
                                    isCategory == false
                                    isImage == false
                                },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(dimensionResource(id = R.dimen._4sdp))


                            ) {
                                Text(text = "Cancel")
                            }
                        }
                    }
                }

            LazyColumn {
                items(galleryList ?: emptyList()){
                    GalleryItemView(it, delete = {docId ->
                        galleryViewModel.deleteGallery(docId)

                    }, deleteImage = { cat, imageUrl ->
                        galleryViewModel.deleteImage(cat, imageUrl)
                    })
                }
            }
        }

    }
}



@Preview(showSystemUi = true)
@Composable
fun GalleryPreview(){

}
