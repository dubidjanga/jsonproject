package com.example.myapplicationahahah

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationahahah.model.Blockchain
import com.example.myapplicationahahah.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository): ViewModel() {

    val myResponse: MutableLiveData<Response<Blockchain>> = MutableLiveData()

    fun getChain(){
        viewModelScope.launch {
            val response = repository.getChain()
            myResponse.value = response
        }
    }
}