package com.example.dogimagegenerator.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.dogimagegenerator.R
import com.example.dogimagegenerator.ui.theme.buttonColor
import com.example.dogimagegenerator.viewModel.DogViewModel

@Composable
fun RecentDogsScreen(navController: NavController, viewModel: DogViewModel) {
    val dogImages by viewModel.dogImages.collectAsState()

    Header(navController, stringResource(id = R.string.recent_dogs))


    Column(
        modifier = Modifier.fillMaxSize()
            .padding( start = 16.dp, end = 16.dp)
    )
    {
        Spacer(modifier = Modifier.height(100.dp))

        Column (modifier = Modifier.height(300.dp)) {

                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(dogImages.reversed()) { imageUrl ->
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = "Dog Image",
                            modifier = Modifier
                                .height(300.dp)
                                .wrapContentWidth(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

        Spacer(modifier = Modifier.height(50.dp))


        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()
        Button(
            onClick = { viewModel.clearCache() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                .height(40.dp)
                .width(150.dp),
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(buttonColor)
        ) {
            Text(
                stringResource(id = R.string.clear_dogs),
                color = if (isPressed) Color.Gray else Color.White
            )
        }
    }
}