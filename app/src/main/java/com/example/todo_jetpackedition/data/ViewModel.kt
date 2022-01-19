package com.example.todo_jetpackedition

import android.app.Application
import android.icu.text.CaseMap
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException


class dataViewModel(application: Application): ViewModel() {

    private val db=dDatabase.getDatabase(application)

    internal val todoList: LiveData<List<Data>> = db.dataDao().getAllData()

     fun insertdata(data: Data){
        viewModelScope.launch(Dispatchers.IO) {
            db.dataDao().insert(data)
        }
    }

     fun updatedata(data: Data){
         viewModelScope.launch {
             db.dataDao().update(data)
         }
    }

     fun getData(Title: String): LiveData<Data>{
            return db.dataDao().getData(Title)
     }


     fun delete(data: Data){
         viewModelScope.launch {
             db.dataDao().delete(data)
         }
    }



}


class ViewmodelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(dataViewModel::class.java)) {
            return dataViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
