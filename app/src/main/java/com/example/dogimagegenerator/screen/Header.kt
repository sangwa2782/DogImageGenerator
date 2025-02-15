package com.example.dogimagegenerator.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dogimagegenerator.R
import com.example.dogimagegenerator.ui.theme.buttonColor

@Composable
fun Header(navController: NavController, screenTitle: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(1.dp, RoundedCornerShape(0.dp))
            .padding(horizontal = 16.dp)
            .height(56.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { navController.popBackStack() }
                .padding(top = 5.dp, bottom = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.back_arrow),
                contentDescription = "Back",
                tint = buttonColor,
                modifier = Modifier.size(54.dp).offset(x = (-28).dp)
            )
            Text(
                text = stringResource(R.string.back),
                fontSize = 16.sp,
                color = buttonColor,
                modifier = Modifier.offset(x = (-46).dp)
            )
        }

        Spacer(modifier = Modifier.width(60.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset(x = 20.dp)
                .padding(13.dp),
            contentAlignment = Alignment.Center,

        ) {
            Text(
                text = screenTitle,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.lato_bold, FontWeight.ExtraBold)),
                color = Color.Black
            )
        }
    }
}
