package com.example.todo_jetpackedition


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todo_jetpackedition.navigation.Screen

@Composable
fun sample(navHostController: NavHostController, Title: String, subtitle: String, color: String) {


    Row(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(0.dp)
            .height(80.dp)
            .clickable(onClick = { navHostController.navigate(Screen.detailpage.passTitle(Title = Title)) })
    ) {

        var imagecolor: Painter = painterResource(id = R.drawable.red_icon)

        if (color == "imagered") {
            imagecolor = painterResource(id = R.drawable.red_icon)
        } else if (color == "imageyellow") {
            imagecolor = painterResource(id = R.drawable.yellow_icon)
        } else if (color == "imageblue") {
            imagecolor = painterResource(id = R.drawable.blue_icon)
        } else if (color == "imagecyan") {
            imagecolor = painterResource(id = R.drawable.cyan_icon)
        } else if (color == "imagegreen") {
            imagecolor = painterResource(id = R.drawable.green_icon)
        } else if (color == "imageorange") {
            imagecolor = painterResource(id = R.drawable.orange_icon)
        }

        IconButton(onClick = { /*TODO*/ }) {
            Image(painter = imagecolor, contentDescription = "selected tag color", modifier = Modifier
                .width(20.dp).height(20.dp))
        }


        Column (modifier = Modifier
            .fillMaxWidth()
        ) {
            Text(text = " "+Title, fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Text(text = " "+subtitle, fontSize = 20.sp, fontWeight = FontWeight.Light)
            }

     }
}



@Preview(showBackground = true)
@Composable
fun Previewsample() {
    sample(navHostController = rememberNavController(),Title = "Sample title", subtitle = " sample title","")
}