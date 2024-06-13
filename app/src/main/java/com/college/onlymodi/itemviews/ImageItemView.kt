package com.college.onlymodi.itemviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.intuit.sdp.R

@Composable
fun ImageItemView(imageUrl: String) {

    OutlinedCard {
        ConstraintLayout {
//            val (image, delete) = createRefs()

            Image(painter = rememberAsyncImagePainter(model = imageUrl), contentDescription = null,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen._150sdp))
                    .width(dimensionResource(id = R.dimen._150sdp))
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ImageView(){
//    BannerItemView(bannerModel = BannerModel())
}