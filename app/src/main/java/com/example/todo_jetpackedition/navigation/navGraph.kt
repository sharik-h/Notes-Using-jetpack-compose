package com.example.todo_jetpackedition

import android.icu.text.CaseMap
import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todo_jetpackedition.navigation.Screen
import com.example.todo_jetpackedition.splash.AnimatedSplash

@ExperimentalMaterialApi
@Composable
fun navGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.splash.route ) {

        composable(route = Screen.mainpage.route){
            main_page(NavHostController = navHostController)
        }

        composable(route = Screen.addpage.route,
            arguments = listOf(
                navArgument("Title")
                {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("Subtitle")
                {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("notes")
                {
                    type = NavType.StringType
                    defaultValue = ""
                })
        ){
            val Title = it.arguments?.getString("Title").toString()
            val Subtitle = it.arguments?.getString("Subtitle").toString()
            val notes = it.arguments?.getString("notes").toString()
            Detail_view(NavHostController = navHostController, Title = Title, SubTitle = Subtitle, notes = notes )
        }

        composable(
            route = Screen.detailpage.route,
            arguments = listOf(navArgument("Title")
            {
                type = NavType.StringType
                defaultValue = ""
            })
        ){
            val title = it.arguments?.getString("Title").toString()
            Detail_page(NavHostController = navHostController, title)
        }

        composable(route = Screen.samplepage.route){
            sample(navHostController = navHostController,"","","")
        }

        composable(route = Screen.splash.route){
            AnimatedSplash(navHostController = navHostController)
        }

    }

}
