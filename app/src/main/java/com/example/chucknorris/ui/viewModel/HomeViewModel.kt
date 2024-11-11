package com.example.chucknorris.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.api.Resource
import com.example.chucknorris.api.data.response.JokesResponse
import com.example.chucknorris.api.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel(){
    val serviceRandom = MutableLiveData<Resource<JokesResponse>>()

    val repo = BaseRepository()
    fun getRandom(){
        serviceRandom.value = Resource.loading(null)
        viewModelScope.launch{
            val mess = repo.getJoke()
            withContext(Dispatchers.Main){
                serviceRandom.value = mess
            }
        }
    }

}