package com.example.todo_jetpackedition

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todo_jetpackedition.navigation.Screen


@Composable
fun Detail_page(NavHostController: NavHostController, title: String) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val context= LocalContext.current
            val viewmodel : dataViewModel = viewModel(factory = ViewmodelFactory(context.applicationContext as Application))
            val input = viewmodel.getData(Title = title).observeAsState()

            Text(text = input.value?.title.toString(), fontSize = 50.sp)
            Text(text = input.value?.subtitle.toString(), fontSize = 30.sp)
            Text(text = input.value?.notes.toString(), fontSize = 30.sp)
        }
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {

            val context = LocalContext.current
            val viewmodel: dataViewModel = viewModel(factory = ViewmodelFactory(context.applicationContext as Application))
            val input = viewmodel.getData(title).observeAsState()
            val title = input.value?.title.toString()
            val subtitle = input.value?.subtitle.toString()
            val notes = input.value?.notes.toString()

            FloatingActionButton(
                onClick = { NavHostController.navigate(Screen.addpage.passId(title, subtitle, notes)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "UPDATE THIS NOTE",
                    fontSize = 20.sp)
            }
        }


    }


@Preview(showBackground = true)
@Composable
fun PreviewDetailpage() {
    Detail_page(NavHostController = rememberNavController() , "sharikh")
}