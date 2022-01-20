package com.example.todo_jetpackedition

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
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


     fun delete(id: Int){
         viewModelScope.launch {
             db.dataDao().delete(id = id)
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
