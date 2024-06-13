package com.college.collegeappadmin.itemviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.college.collegeappadmin.models.BannerModel
import com.intuit.sdp.R

@Composable
fun ImageItemView(imageUrl: String,
                  cat : String,
                  delete:(cat:String, image: String)->Unit) {

    OutlinedCard {
        ConstraintLayout {
            val (image, delete) = createRefs()

            Image(painter = rememberAsyncImagePainter(model = imageUrl), contentDescription = null,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen._150sdp))
                    .width(dimensionResource(id = R.dimen._150sdp))
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop)

            Card(modifier = Modifier.constrainAs(delete){
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }.padding(dimensionResource(R.dimen._4sdp))
                .clickable {
                    delete(cat, imageUrl)
                }
            ) {
                Image(painter = painterResource(id = com.college.collegeappadmin.R.drawable.delete),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen._8sdp)))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ImageView(){
//    BannerItemView(bannerModel = BannerModel())
}