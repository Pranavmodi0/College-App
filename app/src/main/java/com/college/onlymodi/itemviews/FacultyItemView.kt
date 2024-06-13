package com.college.onlymodi.itemviews

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.intuit.sdp.R

@Composable
fun FacultyItemView (category : String,
                     onClick:(category: String)->Unit){


    OutlinedCard(modifier = Modifier.padding(dimensionResource(com.intuit.ssp.R.dimen._16ssp))
        .fillMaxWidth().clickable {
            onClick(category)
        }) {
        ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
            val (categorys, delete) = createRefs()

            Text(
                text = category,
                modifier = Modifier
                    .padding(horizontal = dimensionResource(id = R.dimen._12sdp),
                        vertical = dimensionResource(id = R.dimen._8sdp)
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
                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._16ssp).value.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FacultyView(){
//    BannerItemView(bannerModel = BannerModel())
}