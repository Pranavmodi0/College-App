package com.college.onlymodi.itemviews

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.college.onlymodi.models.FacultyModel
import com.college.onlymodi.ui.theme.LightBlue
import com.college.onlymodi.utils.Constant.BANNER
import com.college.onlymodi.viewmodels.FacultyViewModel
import com.intuit.sdp.R

@Composable
fun TeacherItemView(facultyModel: FacultyModel){

    val context = LocalContext.current

    val facultyViewModel : FacultyViewModel = viewModel()

    val isUploaded by facultyViewModel.isPosted.observeAsState(false)

    val isDeleted by facultyViewModel.isDeleted.observeAsState(false)

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var position by remember {
        mutableStateOf("")
    }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    LaunchedEffect(isUploaded) {
        if (isUploaded){
            Toast.makeText(context, "Data Added", Toast.LENGTH_SHORT).show()
            imageUri = null
            position = ""
            name = ""
            email = ""
        }
    }

    LaunchedEffect(isDeleted) {
        if (isDeleted){
            Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
        }
    }

    OutlinedCard {
        ConstraintLayout {
            val (image, delete) = createRefs()

            Column(horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(dimensionResource(id =R.dimen._20sdp))) {
                Spacer(modifier = Modifier.height(dimensionResource(id =R.dimen._5sdp)))
                Image(
                    rememberAsyncImagePainter(model = facultyModel.imageUrl),
                    contentDescription = BANNER,
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen._130sdp))
                        .width(dimensionResource(id = R.dimen._130sdp))
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(dimensionResource(id =R.dimen._5sdp)))


                Text(
                    text = facultyModel.name!!,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen._12sdp),
                        vertical = dimensionResource(id = R.dimen._8sdp)
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._16ssp).value.sp
                )

                Text(
                    text = facultyModel.email!!,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen._12sdp),
                        vertical = dimensionResource(id = R.dimen._8sdp)
                    ),
                    color = LightBlue,
                    fontWeight = FontWeight.Normal,
                    fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._14ssp).value.sp
                )

                Text(
                    text = facultyModel.position!!,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen._12sdp),
                        vertical = dimensionResource(id = R.dimen._8sdp)
                    ),
                    color = LightBlue,
                    fontWeight = FontWeight.Normal,
                    fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._14ssp).value.sp
                )

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TeacherView(){
//    BannerItemView(bannerModel = BannerModel())
}