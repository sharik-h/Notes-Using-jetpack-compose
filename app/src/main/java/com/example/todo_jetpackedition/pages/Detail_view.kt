package com.example.todo_jetpackedition

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todo_jetpackedition.navigation.Screen

@Composable
fun Detail_view(NavHostController: NavHostController, Title: String, SubTitle: String, notes: String) {

    if (Title != "" || SubTitle != "" || notes != ""){
        update(NavHostController = NavHostController, Title = Title, SubTitle = SubTitle, notes = notes)
    }
    else {
        insert(NavHostController = NavHostController)
    }
}


@Composable
fun insert(NavHostController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){


        val context= LocalContext.current
        val viewmodel : dataViewModel = viewModel(factory = ViewmodelFactory(context.applicationContext as Application))

        var title by remember { mutableStateOf("") }
        var subtitle by remember { mutableStateOf("") }
        var notes by remember { mutableStateOf("") }
        var color: String = "#ffffff"

        val imagered: Painter = painterResource(id = R.drawable.red_icon)
        val imageyellow: Painter = painterResource(id = R.drawable.yellow_icon)
        val imageblue: Painter = painterResource(id = R.drawable.blue_icon)
        val imagecyan: Painter = painterResource(id = R.drawable.cyan_icon)
        val imagegreen: Painter = painterResource(id = R.drawable.green_icon)
        val imageorange: Painter = painterResource(id = R.drawable.orange_icon)

        OutlinedTextField(
            value = title ,
            onValueChange = {newText -> title = newText},
            label = {Text(text = "Enter a title")},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { title = ""}) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear all")
                }
            }
        )

        OutlinedTextField(
            value = subtitle,
            onValueChange = {newText -> subtitle = newText},
            label = {Text(text = "Enter a Subtitle if you want one.")},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { subtitle = ""}) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear all")
                }
            }
        )

        OutlinedTextField(
            value = notes,
            onValueChange = {newText -> notes = newText},
            label = {Text(text = "Enter some notes.")},
            singleLine = false,
            modifier = Modifier.fillMaxWidth().height(100.dp),
            trailingIcon = {
                IconButton(onClick = { notes = ""}) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear all")
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { color = "imagered" }) {
                Image(
                    painter = imagered,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
            IconButton(onClick = { color = "imageorange" }) {
                Image(
                    painter = imageorange,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))

            }
            IconButton(onClick = { color = "imageyellow" }) {
                Image(
                    painter = imageyellow,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
            IconButton(onClick = { color = "imagegreen" }) {
                Image(
                    painter = imagegreen,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
            IconButton(onClick = { color = "imagecyan" }) {
                Image(
                    painter = imagecyan,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
            IconButton(onClick = { color = "imageblue" }) {
                Image(
                    painter = imageblue,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
        }

        Button(
            onClick = {
                NavHostController.navigate(Screen.mainpage.route){
                    popUpTo(Screen.mainpage.route){
                        inclusive = true
                    }
                }
                viewmodel.insertdata(Data(title = title, subtitle = subtitle, notes = notes , color = color))
            }
            ,modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "ADD NEW NOTE", fontSize = 20.sp)
        }

    }

}



@Composable
fun update(NavHostController: NavHostController, Title: String, SubTitle: String, notes: String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){


        val context= LocalContext.current
        val viewmodel : dataViewModel = viewModel(factory = ViewmodelFactory(context.applicationContext as Application))
        val input = viewmodel.getData(Title = Title).observeAsState()

        var temptitle = input.value?.title.toString()

        var id = input.value?.id?.toInt()

        var title by remember { mutableStateOf( Title ) }
        var subtitle by remember { mutableStateOf( SubTitle ) }
        var notes by remember { mutableStateOf( notes ) }
        var color: String = ""

        val imagered: Painter = painterResource(id = R.drawable.red_icon)
        val imageyellow: Painter = painterResource(id = R.drawable.yellow_icon)
        val imageblue: Painter = painterResource(id = R.drawable.blue_icon)
        val imagecyan: Painter = painterResource(id = R.drawable.cyan_icon)
        val imagegreen: Painter = painterResource(id = R.drawable.green_icon)
        val imageorange: Painter = painterResource(id = R.drawable.orange_icon)

        OutlinedTextField(
            value = title ,
            onValueChange = {newText -> title = newText},
            label = {Text(text = "Enter a title")},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { title = ""}) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear all")
                }
            } )

        OutlinedTextField(
            value = subtitle,
            onValueChange = {newText -> subtitle = newText},
            label = {Text(text = "Enter a Subtitle if you want one.")},
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                IconButton(onClick = { subtitle = ""}) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear all")
                }
            } )

        OutlinedTextField(
            value = notes,
            onValueChange = {newText -> notes = newText},
            label = {Text(text = "Enter some notes.")},
            singleLine = false,
            modifier = Modifier.fillMaxWidth().height(100.dp),
            trailingIcon = {
                IconButton(onClick = { notes = ""}) {
                    Icon(imageVector = Icons.Filled.Close, contentDescription = "Clear all")
                }
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { color = "imagered" }) {
                Image(
                    painter = imagered,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
            IconButton(onClick = { color = "imageorange" }) {
                Image(
                    painter = imageorange,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))

            }
            IconButton(onClick = { color = "imageyellow" }) {
                Image(
                    painter = imageyellow,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
            IconButton(onClick = { color = "imagegreen" }) {
                Image(
                    painter = imagegreen,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
            IconButton(onClick = { color = "imagecyan" }) {
                Image(
                    painter = imagecyan,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
            IconButton(onClick = { color = "imageblue" }) {
                Image(
                    painter = imageblue,
                    contentDescription = "" ,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp))
            }
        }

        Button(
            onClick = {
                NavHostController.navigate(Screen.mainpage.route){
                    popUpTo(Screen.mainpage.route){
                        inclusive = true
                    }
                }
                id?.let { Data(id = it,title = title, subtitle = subtitle, notes = notes, color = color) }
                    ?.let { viewmodel.updatedata(it) }
            }
            ,modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "UPDATE NOTE", fontSize = 20.sp)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewDetail_view() {
    insert(NavHostController = rememberNavController())
    update(NavHostController = rememberNavController(), Title = "", SubTitle = "", notes = "")
}
