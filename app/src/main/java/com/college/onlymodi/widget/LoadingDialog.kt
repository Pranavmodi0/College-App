package com.college.onlymodi.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import com.intuit.sdp.R
import okhttp3.Request

@Composable
fun LoadingDialog(onDismissRequest: () -> Unit){

    Dialog(onDismissRequest = { onDismissRequest }) {
        Box(modifier = Modifier
            .size(dimensionResource(id = R.dimen._120sdp))
            .background(
                color = Color.White,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen._8sdp))),
            contentAlignment = Alignment.Center){

            Column(modifier = Modifier.align(Alignment.Center)) {
                CircularProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoadingPreview(){
    LoadingDialog {

    }
}