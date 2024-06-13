package com.college.onlymodi.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.college.onlymodi.R
import com.college.onlymodi.itemviews.GalleryItemView
import com.college.onlymodi.viewmodels.GalleryViewModel

@Composable
fun Gallery(navController: NavController) {

    val galleryViewModel : GalleryViewModel = viewModel()

    val galleryList by galleryViewModel.galleryList.observeAsState(null)

    galleryViewModel.getGallery()


    LazyColumn {
        items(galleryList ?: emptyList()) {
            GalleryItemView(it, delete = { docId ->
                galleryViewModel.deleteGallery(docId)

            }, deleteImage = { cat, imageUrl ->
                galleryViewModel.deleteImage(cat, imageUrl)
            })
        }
    }
}