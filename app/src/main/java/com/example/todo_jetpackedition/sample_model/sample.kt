package com.example.todo_jetpackedition


import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todo_jetpackedition.navigation.Screen
import kotlin.math.roundToInt

@ExperimentalMaterialApi
@Composable
fun sample(navHostController: NavHostController, Title: String, subtitle: String, color: String) {

    val squaresize = 80.dp
    val swipable = rememberSwipeableState(initialValue = 0)
    val sizePx = with(LocalDensity.current) { squaresize.toPx() }
    val anchors = mapOf(0f to 0, sizePx to 1)

    val context = LocalContext.current
    val viewModel: dataViewModel = viewModel(
        factory = ViewmodelFactory(context.applicationContext as Application) )
    val input = viewModel.getData(Title = Title).observeAsState()
    val id = input.value?.id


    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(Color.Red)
            .swipeable(
                state = swipable,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
    ) {
        Column()
        {
            IconButton(
                onClick = {
                    if (id != null) {
                        viewModel.delete(id)
                    }
                },
                Modifier.padding(vertical = 5.dp, horizontal = 5.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "to delete",
                    Modifier.fillMaxSize().padding(10.dp)
                )
            }
        }

        Box(modifier = Modifier
            .offset { IntOffset(swipable.offset.value.roundToInt(), 0) }
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .height(60.dp))
        {
            Row(
                modifier = Modifier
                    .background(Color.White)
                    .height(60.dp)
                    .padding(horizontal = 5.dp)
                    .clickable(onClick = {
                        navHostController.navigate(
                            Screen.detailpage.passTitle(Title = Title)
                        )
                    }),
                verticalAlignment = Alignment.CenterVertically
            )
            {
//                var imagecolor: Painter = painterResource(id = R.drawable.red_icon)
//                if (color == "imagered") {
//                    imagecolor = painterResource(id = R.drawable.red_icon)
//                } else if (color == "imageyellow") {
//                    imagecolor = painterResource(id = R.drawable.yellow_icon)
//                } else if (color == "imageblue") {
//                    imagecolor = painterResource(id = R.drawable.blue_icon)
//                } else if (color == "imagecyan") {
//                    imagecolor = painterResource(id = R.drawable.cyan_icon)
//                } else if (color == "imagegreen") {
//                    imagecolor = painterResource(id = R.drawable.green_icon)
//                } else if (color == "imageorange") {
//                    imagecolor = painterResource(id = R.drawable.orange_icon)
//                }
//                IconButton(onClick = { /*TODO*/ }) {
//                    Image(
//                        painter = imagecolor,
//                        contentDescription = "selected tag color",
//                        modifier = Modifier
//                            .width(10.dp)
//                            .height(20.dp)
//                    )
//                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                )
                {
                    Text(text = " " + Title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = " " + subtitle, fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Color.DarkGray)
                }
            }
        }

    }

}



@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun Previewsample() {
    sample(navHostController = rememberNavController(),Title = "Sample title", subtitle = " sample title","")
}