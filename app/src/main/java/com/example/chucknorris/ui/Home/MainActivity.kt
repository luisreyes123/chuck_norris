package com.example.chucknorris.ui.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.chucknorris.R
import com.example.chucknorris.api.Resource
import com.example.chucknorris.databinding.ActivityMainBinding
import com.example.chucknorris.ui.viewModel.HomeViewModel
import com.example.chucknorris.utils.DialogUtils
import com.example.chucknorris.utils.LoaderDialogFragment

class MainActivity : AppCompatActivity() {
    private val viewModel = HomeViewModel()
    private lateinit var binding: ActivityMainBinding
    val loader = LoaderDialogFragment.newInstance()
    private val categories = mutableListOf(
        "animal", "career", "celebrity", "dev", "explicit", "fashion",
        "food", "history", "money", "movie", "music", "political",
        "religion", "science", "sport", "travel"
    )
    private var lastSelectedCategory: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    private fun initView(){
        viewModel.getRandom()
        binding.newChisteBtn.setOnClickListener {
            DialogUtils.showConfirmationDialog(
                this,getString(R.string.chuck_angry), getString(R.string.accept), getString(R.string.cancelar),
                onConfirm = {
                    viewModel.getRandom()
                }
            )
        }
        subscribeLiveData()
    }

    private fun subscribeLiveData() {

        viewModel.serviceRandom.observe(this, androidx.lifecycle.Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loader.show(supportFragmentManager, "Cargando ando")
                }

                Resource.Status.SUCCESS -> {
                    loader.dismiss()
                    viewModel.getCategories()
                    binding.txtChiste.text = it.data?.value
                }

                Resource.Status.ERROR -> {
                    loader.dismiss()
                    DialogUtils.showErrorDialog(this, getString(R.string.sorry_error), getString(R.string.sorry_error))
                }
            }
        })

        viewModel.serviceCategories.observe(this, androidx.lifecycle.Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loader.show(supportFragmentManager, "Cargando ando")
                }

                Resource.Status.SUCCESS -> {
                    loader.dismiss()
                    categories.clear()
                    categories.addAll(it.data!!.categories)
                    initSpinner()
                }
                Resource.Status.ERROR -> {
                    loader.dismiss()
                    initSpinner()
                }
            }
        })
        viewModel.serviceRandomCategories.observe(this, androidx.lifecycle.Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    loader.show(supportFragmentManager, "Cargando ando")
                }

                Resource.Status.SUCCESS -> {
                    loader.dismiss()
                    binding.txtChisteCategory.text = it.data?.value
                }

                Resource.Status.ERROR -> {
                    loader.dismiss()
                    DialogUtils.showErrorDialog(this, getString(R.string.sorry_error), getString(R.string.sorry_error))
                }
            }
        })
    }
    fun initSpinner(){
        val spinnerAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            categories
        )
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.categorySpinner.adapter = spinnerAdapter
        binding.categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedCategory = categories[position]
                if (selectedCategory != lastSelectedCategory) {
                    lastSelectedCategory = selectedCategory
                    val category = selectedCategory
                    viewModel.getRandomCategory(category)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

}