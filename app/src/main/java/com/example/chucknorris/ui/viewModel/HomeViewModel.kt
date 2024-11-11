package com.example.chucknorris.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chucknorris.api.Resource
import com.example.chucknorris.api.data.response.JokesCategoriesResponse
import com.example.chucknorris.api.data.response.JokesResponse
import com.example.chucknorris.api.repository.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel(){
    val serviceRandom = MutableLiveData<Resource<JokesResponse>>()
    val serviceCategories = MutableLiveData<Resource<JokesCategoriesResponse>>()
    val serviceRandomCategories = MutableLiveData<Resource<JokesResponse>>()

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
    fun getRandomCategory(category :String){
        serviceRandomCategories.value = Resource.loading(null)
        viewModelScope.launch{
            val mess = repo.getRandomCategory(category)
            withContext(Dispatchers.Main){
                serviceRandomCategories.value = mess
            }
        }
    }

    fun getCategories(){
        serviceCategories.value = Resource.loading(null)
        viewModelScope.launch{
            val mess = repo.getCategories()
            withContext(Dispatchers.Main){
                serviceCategories.value = mess
            }
        }
    }

}