package com.college.collegeappadmin.itemviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.college.collegeappadmin.R
import com.college.collegeappadmin.models.BannerModel


@Composable
fun BannerItemView(bannerModel: BannerModel,
                    delete:(docId:BannerModel)->Unit) {

    OutlinedCard {
        ConstraintLayout {
            val (image, delete) = createRefs()
            
            Image(painter = rememberAsyncImagePainter(model = bannerModel.url), contentDescription = null,
                modifier = Modifier
                    .height(dimensionResource(id = com.intuit.sdp. R.dimen._220sdp))
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop)

            Card(modifier = Modifier.constrainAs(delete){
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }.padding(dimensionResource(com.intuit.sdp.R.dimen._4sdp))
                .clickable {
                    delete(bannerModel)
                }
            ) {
                Image(painter = painterResource(id = R.drawable.delete),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(dimensionResource(id = com.intuit.sdp.R.dimen._8sdp)))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun BannerView(){
//    BannerItemView(bannerModel = BannerModel())
}