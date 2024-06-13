package com.college.collegeappadmin.itemviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.college.collegeappadmin.models.BannerModel
import com.college.collegeappadmin.models.NoticeModel
import com.college.collegeappadmin.ui.theme.LightBlue
import com.intuit.sdp.R

@Composable
fun NoticeItemView (noticeModel: NoticeModel,
                    delete:(docId: NoticeModel)->Unit){


    OutlinedCard {
        ConstraintLayout {
            val (image, delete) = createRefs()

            Column {

                Text(
                    text = noticeModel.title!!,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen._12sdp),
                        vertical = dimensionResource(id = R.dimen._8sdp)
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._16ssp).value.sp
                )
                if (noticeModel.link!="")
                Text(
                    text = noticeModel.link!!,
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen._12sdp),
                        vertical = dimensionResource(id = R.dimen._8sdp)
                    ),
                    color = LightBlue,
                    fontWeight = FontWeight.Normal,
                    fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._14ssp).value.sp
                )

                Image(
                    painter = rememberAsyncImagePainter(model = noticeModel.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .height(dimensionResource(id = R.dimen._220sdp))
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            Card(modifier = Modifier
                .constrainAs(delete) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(dimensionResource(R.dimen._4sdp))
                .clickable {
                    delete(noticeModel)
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
fun NoticeView(){
//    BannerItemView(bannerModel = BannerModel())
}