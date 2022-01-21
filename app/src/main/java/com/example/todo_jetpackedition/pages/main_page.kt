package com.example.todo_jetpackedition

import android.app.Application
import android.icu.text.CaseMap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.util.TableInfo
import com.example.todo_jetpackedition.R.color.yellow
import com.example.todo_jetpackedition.navigation.Screen
import com.google.android.material.floatingactionbutton.FloatingActionButton
import org.w3c.dom.Text

@ExperimentalMaterialApi
@Composable
fun main_page(NavHostController: NavHostController) {

    Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE9E8E8))
                .padding(10.dp) )
        {

            val context = LocalContext.current
            val viewModel: dataViewModel = viewModel(factory = ViewmodelFactory(context.applicationContext as Application))
            val list: List<Data> = viewModel.todoList.observeAsState(listOf()).value

            Text(
                text = " Notes",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )

            if (list.size < 1) {
              Column(
                  Modifier.fillMaxSize(),
                  verticalArrangement = Arrangement.Center,
                  horizontalAlignment = Alignment.CenterHorizontally
              ) {
                  var emptylist = painterResource(id = R.drawable.emptyicon)
                  Image(
                      painter = emptylist,
                      contentDescription = "nothing in the list",
                      modifier = Modifier
                          .height(200.dp)
                          .width(200.dp)
                  )
                  Text(text = "Nothing to show", fontSize = 25.sp, color = Color(0xFF7E7E7E))
              }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(list.size) { index ->
                    sample(
                        navHostController = NavHostController,
                        Title = list[index].title,
                        subtitle = list[index].subtitle,
                        color = list[index].color
                    )
                }
            }


        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
//            FloatingActionButton(
//                onClick = { NavHostController.navigate(Screen.addpage.route) },
//                modifier = Modifier.fillMaxWidth(),
//                backgroundColor = Color(0xFFFFC810)) {
//                Text(text = "Add Note", color = Color.White, fontSize = 30.sp)
//            }
            IconButton(onClick = { NavHostController.navigate(Screen.addpage.route) }) {
                Icon(imageVector = Icons.Outlined.Add ,
                    contentDescription = "Add new note",
                    Modifier.size(30.dp),
                    tint = Color(0xFFFFC810)
                )
            }

        }
}


@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun PreviewMain_page() {
    main_page(NavHostController = rememberNavController())
}