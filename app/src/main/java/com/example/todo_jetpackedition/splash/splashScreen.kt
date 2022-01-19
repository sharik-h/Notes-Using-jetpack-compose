package com.example.todo_jetpackedition.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todo_jetpackedition.R
import com.example.todo_jetpackedition.navGraph
import com.example.todo_jetpackedition.navigation.Screen
import kotlinx.coroutines.delay


@Composable
fun AnimatedSplash(navHostController: NavHostController) {
    var startAnimation by remember { mutableStateOf(false) }
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 2000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(2000)
        navHostController.popBackStack()
        navHostController.navigate(Screen.mainpage.route)
    }
    splash(alpha = alphaAnim.value)
}

@Composable
fun splash(alpha: Float) {
    Column(modifier = Modifier
        .fillMaxSize()
        .alpha(alpha = alpha)
        .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val splashicon: Painter = painterResource(id = R.drawable.splash_icon)
        Image(painter = splashicon, contentDescription = "splash_icon" , modifier = Modifier
            .height(100.dp)
            .width(100.dp))
        Text(text = "NoTes", fontWeight = FontWeight.Bold, fontSize = 23.sp)
    }
}

@Preview(showBackground =  true)
@Composable
fun PreviewSplashScreen() {
    splash(alpha = 1f)
}