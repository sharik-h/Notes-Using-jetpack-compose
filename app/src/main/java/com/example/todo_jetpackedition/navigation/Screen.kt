package com.example.todo_jetpackedition.navigation

import android.icu.text.CaseMap


sealed class Screen(val route: String) {
    object mainpage: Screen("main_page")
    object addpage: Screen("add_page?Title={Title}&Subtitle={Subtitle}&notes={notes}"){
        fun passId(Title: String, Subtitle: String, notes: String): String{
            return "add_page?Title=$Title&Subtitle=$Subtitle&notes=$notes"
        }
    }
    object detailpage: Screen("detail_page?Title={Title}"){
        fun passTitle(Title: String): String{
            return "detail_page?Title=$Title"
        }
    }
    object samplepage: Screen("sample_page")
    object splash: Screen("splash")
}
