package com.example.todo_jetpackedition

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todo_jetpackedition.navigation.Screen
import org.w3c.dom.Text

@Composable
fun main_page(NavHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp))
    {
        val context = LocalContext.current
        val viewModel: dataViewModel = viewModel(
            factory = ViewmodelFactory(context.applicationContext as Application)
        )
        val list: List<Data> = viewModel.todoList.observeAsState(listOf()).value

        Text(
            text = "My Notes",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        
        if (list.size < 1) {
            var emptylist = painterResource(id = R.drawable.emptylist)
            Image(painter = emptylist, contentDescription = "nothing in the list")
        }


            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
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
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        FloatingActionButton(
            onClick = { NavHostController.navigate(Screen.addpage.route) },
            modifier = Modifier.fillMaxWidth())
        { Text(text = "ADD SOME TODO", fontSize = 20.sp) }
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewMain_page() {
    main_page(NavHostController = rememberNavController())
}