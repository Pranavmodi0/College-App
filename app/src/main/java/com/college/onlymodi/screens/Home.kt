package com.college.onlymodi.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.college.onlymodi.itemviews.NoticeItemView
import com.college.onlymodi.viewmodels.BannerViewModel
import com.college.onlymodi.viewmodels.NoticeViewModel
import com.intuit.sdp.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Home(navController: NavController) {

    val bannerViewModel : BannerViewModel = viewModel()
    val bannerList by bannerViewModel.bannerList.observeAsState(null)
    bannerViewModel.getBanner()

    val noticeViewModel : NoticeViewModel = viewModel()
    val noticeList by noticeViewModel.noticeList.observeAsState(null)
    noticeViewModel.getNotice()

    val imageSlider = ArrayList<AsyncImagePainter>()

    val pagerState = rememberPagerState(pageCount = {
        imageSlider.size
    })


    if (bannerList != null){
        bannerList!!.forEach {
            imageSlider.add(rememberAsyncImagePainter(model = it.url))
        }
    }

    LaunchedEffect(Unit) {
         try {
             while (true){
                 yield()
                 delay(2600)
                 pagerState.animateScrollToPage(page = (pagerState.currentPage+1) % pagerState.pageCount)
             }
         } catch (e : Exception){

         }
    }

    LazyColumn {
        item{
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._12sdp)))

            HorizontalPager(state = pagerState, modifier = Modifier.padding(dimensionResource(id = R.dimen._4sdp))) { page ->
                Card(modifier = Modifier.height(dimensionResource(id = R.dimen._200sdp))) {
                    Image(painter = imageSlider[page],
                        contentDescription = "banner",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(dimensionResource(id = R.dimen._200sdp))
                            .fillMaxWidth())
                }
            }
        }

        item {

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._12sdp)))

            Text(
                text = "Notices",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._15ssp).value.sp
                )

            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._8sdp)))
        }

        items(noticeList ?: emptyList()){
            NoticeItemView(it)
        }

    }

}

@Preview(showSystemUi = true)
@Composable
fun HomePreview(){

}
