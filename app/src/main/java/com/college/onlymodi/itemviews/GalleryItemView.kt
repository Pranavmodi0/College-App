package com.college.onlymodi.itemviews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.college.onlymodi.models.GalleryModel
import com.intuit.ssp.R

@Composable
fun GalleryItemView (galleryModel: GalleryModel,
                     delete:(galleryModel : GalleryModel)->Unit,
                     deleteImage : (cat:String, image:String) -> Unit){


    OutlinedCard(modifier = Modifier.padding(dimensionResource(R.dimen._16ssp))
        .fillMaxWidth()) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (categorys, delete) = createRefs()

            Text(
                text = galleryModel.category!!,
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp),
                        vertical = dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)
                    ).constrainAs(
                        categorys
                    ) {
                        start.linkTo(parent.start)
                        end.linkTo(delete.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen._16ssp).value.sp
            )
        }

        LazyRow() {
            items(galleryModel.images?: emptyList()){
                ImageItemView(imageUrl = it)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GalleryView(){
//    BannerItemView(bannerModel = BannerModel())
}