package com.example.chucknorris.ui.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chucknorris.R
import com.example.chucknorris.api.Resource
import com.example.chucknorris.ui.viewModel.HomeViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel = HomeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }

    private fun initView(){
        newChisteBtn.setOnClickListener {
            viewModel.getRandom()
        }
        subscribeLiveData()
    }

    private fun subscribeLiveData() {

        viewModel.serviceRandom.observe(this, androidx.lifecycle.Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                }

                Resource.Status.SUCCESS -> {
                    txtChiste.text = it.data?.value
                }

                Resource.Status.ERROR -> {

                }
            }
        }
    }


}