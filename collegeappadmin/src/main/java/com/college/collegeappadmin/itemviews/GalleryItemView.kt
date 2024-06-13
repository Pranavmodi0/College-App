package com.college.collegeappadmin.itemviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.college.collegeappadmin.models.GalleryModel
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
                    .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp),
                        vertical = dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)
                    ).constrainAs(
                        categorys
                    ){
                        start.linkTo(parent.start)
                        end.linkTo(delete.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    },
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = R.dimen._16ssp).value.sp
            )

            Card(modifier = Modifier
                .constrainAs(delete) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(dimensionResource(com.intuit.sdp.R.dimen._4sdp))
                .clickable {
                    delete(galleryModel)
                }
            ) {
                Image(painter = painterResource(id = com.college.collegeappadmin.R.drawable.delete),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)))
            }
        }

        LazyRow() {
            items(galleryModel.images?: emptyList()){
                ImageItemView(imageUrl = it, cat = galleryModel.category!!, delete =  {cat : String, imageUrl->
                    deleteImage(cat, imageUrl)
                })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GalleryView(){
//    BannerItemView(bannerModel = BannerModel())
}