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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.college.collegeappadmin.itemviews.NoticeItemView
import com.college.collegeappadmin.navigations.Routes
import com.college.collegeappadmin.utils.Constant
import com.college.collegeappadmin.viewmodels.FacultyViewModel

import com.intuit.sdp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageFaculty(navController: NavController) {
    val context = LocalContext.current

    val facultyViewModel : FacultyViewModel = viewModel()

    val isUploaded by facultyViewModel.isPosted.observeAsState(false)

    val isDeleted by facultyViewModel.isDeleted.observeAsState(false)


    val categoryList by facultyViewModel.categoryList.observeAsState(null)

    val option = arrayListOf<String>()

    facultyViewModel.getCategory()

    var isCategory by remember {
        mutableStateOf(false)
    }

    var mExpanded by remember {
        mutableStateOf(false)
    }

    var isTeacher by remember {
        mutableStateOf(false)
    }

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var position by remember {
        mutableStateOf("")
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
            isTeacher = false
            category = ""
            name = ""
            email = ""
            position = ""
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
                        "Manage Faculty",
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

            Row(modifier = Modifier.padding(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp))) {

                Card(modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp))
                    .clickable {
                        isCategory = true
                        isTeacher = false
                    }) {
                    Text(text = "Add Category",
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._17ssp).value.sp,
                        modifier = Modifier
                            .padding(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp))
                            .fillMaxWidth(), textAlign = TextAlign.Center
                        )
                }

                Card(modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = com.intuit.sdp.R.dimen._4sdp))
                    .clickable {
                        isCategory = false
                        isTeacher = true
                    }) {
                    Text(text = "Add Teacher",
                        fontWeight = FontWeight.Bold,
                        fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._17ssp).value.sp,
                        modifier = Modifier
                            .padding(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp))
                            .fillMaxWidth(), textAlign = TextAlign.Center
                    )
                }
            }

            if (isCategory)
                ElevatedCard(modifier = Modifier.padding(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp))) {
                    OutlinedTextField(value = category, onValueChange = {
                        category = it
                    },

                        placeholder = { Text(text = "Category")},
                        modifier = Modifier
                            .padding(dimensionResource(id = R.dimen._4sdp))
                            .fillMaxWidth()
                    )

                    Row {
                        Button(onClick = {
                            if (category == ""){
                                Toast.makeText(context, "Please provide category", Toast.LENGTH_SHORT).show()
                            } else {
                                facultyViewModel.uploadCategory(category)
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
                            isTeacher == false},

                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(dimensionResource(id = R.dimen._4sdp))


                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }


            if (isTeacher)

                ElevatedCard(modifier = Modifier.padding(dimensionResource(id = R.dimen._8sdp))) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

                        Spacer(modifier = Modifier.height(dimensionResource(id =R.dimen._5sdp)))
                        Image(
                            painter = if (imageUri == null) painterResource(id = com.college.collegeappadmin.R.drawable.gallery_icon)
                            else rememberAsyncImagePainter(model = imageUri),
                            contentDescription = Constant.BANNER,
                            modifier = Modifier
                                .height(dimensionResource(id = R.dimen._120sdp))
                                .width(dimensionResource(id = R.dimen._120sdp))
                                .clip(CircleShape)
                                .clickable {
                                    launcher.launch("image/*")
                                },
                            contentScale = ContentScale.Crop
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id =R.dimen._5sdp)))

                        OutlinedTextField(
                            value = name, onValueChange = {
                                name = it
                            },

                            placeholder = { Text(text = "Name") },
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen._4sdp))
                                .fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = email, onValueChange = {
                                email = it
                            },

                            placeholder = { Text(text = "Email") },
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen._4sdp))
                                .fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = position, onValueChange = {
                                position = it
                            },

                            placeholder = { Text(text = "Position") },
                            modifier = Modifier
                                .padding(dimensionResource(id = R.dimen._4sdp))
                                .fillMaxWidth()
                        )

                        Box(modifier = Modifier.wrapContentSize(Alignment.BottomEnd)) {
                            OutlinedTextField(
                                value = category, onValueChange = {
                                    category = it
                                },
                                readOnly = true,
                                placeholder = { Text(text = "Select your Department") },
                                label = { Text(text = "Department Name") },
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

                                if (categoryList != null && categoryList!!.isNotEmpty()) {
                                    option.clear()
                                    for (data in categoryList!!) {
                                        option.add(data)
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

                        Row {
                            Button(
                                onClick = {

                                    if (imageUri == null) {
                                        Toast.makeText(
                                            context,
                                            "Please provide image",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else if (name == "" || email == "" || position == "" || category == "") {
                                        Toast.makeText(
                                            context,
                                            "Please provide all fields",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    } else {
                                        facultyViewModel.saveFaculty(
                                            imageUri!!,
                                            name,
                                            email,
                                            position,
                                            category
                                        )
                                    }
                                },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .padding(dimensionResource(id = R.dimen._4sdp))


                            ) {
                                Text(text = "Add Teacher")
                            }

                            OutlinedButton(
                                onClick = {
                                    imageUri = null
                                    isCategory == false
                                    isTeacher == false
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
                items(categoryList ?: emptyList()){
                    FacultyItemView(it, delete = {docId ->
                        facultyViewModel.deleteCategory(docId)

                    }, onClick = {categoryName->
                        val routes = Routes.FacultyDetailScreen.routes.replace("{category}", categoryName)
                        navController.navigate(routes)
                    })
                }
            }
        }

    }
}


@Preview(showSystemUi = true)
@Composable
fun FacultyPreview(){

}
