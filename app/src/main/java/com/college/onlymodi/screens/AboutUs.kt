package com.college.onlymodi.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.college.onlymodi.viewmodels.CollegeInfoViewModel
import com.college.onlymodi.viewmodels.NoticeViewModel
import com.intuit.sdp.R

@Composable
fun AboutUs(navController: NavController) {


    val collegeInfoViewModel : CollegeInfoViewModel = viewModel()
    val collegeInfo by collegeInfoViewModel.collegeInfo.observeAsState(null)
    collegeInfoViewModel.getCollegeInfo()


    Column(modifier = Modifier.padding(dimensionResource(id = R.dimen._8sdp))) {
        if (collegeInfo != null) {
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8sdp)))

            Image(painter = rememberAsyncImagePainter(model = collegeInfo!!.imageUrl),
                contentDescription = "college image",
                modifier = Modifier.height(dimensionResource(id = R.dimen._220sdp)).fillMaxWidth(),
                contentScale = ContentScale.Crop)

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._16sdp)))
            
            Text(
                text = collegeInfo!!.name!!,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._17ssp).value.sp
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8sdp)))

            Text(
                text = collegeInfo!!.desc!!,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._17ssp).value.sp
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8sdp)))

            Text(
                text = collegeInfo!!.address!!,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._17ssp).value.sp
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8sdp)))

            Text(
                text = collegeInfo!!.websiteLink!!,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._17ssp).value.sp
            )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8sdp)))
        }
    }
}