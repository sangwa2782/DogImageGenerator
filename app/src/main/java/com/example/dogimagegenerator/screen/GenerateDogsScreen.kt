package com.example.dogimagegenerator.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
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
fun GenerateDogsScreen(navController: NavController, viewModel: DogViewModel) {
    val dogImages by viewModel.dogImages.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()


    Header(navController, stringResource(id = R.string.generate_dogs))

    Column(
        modifier = Modifier.fillMaxSize().padding(top = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Spacer(modifier = Modifier.height(100.dp))

        Column(
            modifier = Modifier.height(250.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(45.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 70.dp)
                )
            } else {
                dogImages.lastOrNull()?.let { latestImageUrl ->
                    AsyncImage(
                        model = latestImageUrl,
                        contentDescription = "Dog Image",
                        modifier = Modifier
                            .height(300.dp)
                            .wrapContentWidth(),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(100.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            Button(
                modifier = Modifier
                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                    .height(40.dp)
                    .width(150.dp),
                onClick = { viewModel.generateDogImage() },
                interactionSource = interactionSource,
                colors = ButtonDefaults.buttonColors(buttonColor)
            ) {
                Text(
                    stringResource(id = R.string.generate),
                    color = if (isPressed) Color.Gray else Color.White,
                )
            }
        }

    }
}