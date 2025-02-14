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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dogimagegenerator.R
import com.example.dogimagegenerator.ui.theme.buttonColor

@Composable
fun HomeScreen(navController: NavController) {


    Column {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 170.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp)

                ,
                text = stringResource(id = R.string.random_dog_generator),
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium
            )
        }


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val generateInteractionSource = remember { MutableInteractionSource() }
            val isGeneratePressed by generateInteractionSource.collectIsPressedAsState()

            Button(
                modifier = Modifier
                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                    .height(40.dp),
                onClick = { navController.navigate("generate") },
                interactionSource = generateInteractionSource,
                colors = ButtonDefaults.buttonColors(buttonColor)
            ) {
                Text(
                    stringResource(id = R.string.generate_dogs),
                    color = if (isGeneratePressed) Color.Gray else Color.White,
                    )
            }
            Spacer(modifier = Modifier.height(16.dp))


            val recentInteractionSource = remember { MutableInteractionSource() }
            val isrecentPressed by recentInteractionSource.collectIsPressedAsState()
            Button(
                modifier = Modifier
                    .border(1.dp, Color.Black, RoundedCornerShape(20.dp))
                    .height(40.dp),
                onClick = { navController.navigate("recent") },
                interactionSource = recentInteractionSource,
                colors = ButtonDefaults.buttonColors(buttonColor)
            ) {
                Text(
                    stringResource(id = R.string.recent_dogs),
                    color = if (isrecentPressed) Color.Gray else Color.White
                )
            }
        }
    }
}