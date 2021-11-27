package com.example.assigmentbypyramidion

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.assigmentbypyramidion.adapter.AdapterUserData
import com.example.assigmentbypyramidion.api.ScrollDirection
import com.example.assigmentbypyramidion.api.UserDataService
import com.example.assigmentbypyramidion.callback.PaginationCallback
import com.example.assigmentbypyramidion.databinding.ActivityMainBinding
import com.example.assigmentbypyramidion.globalVariable.CustomLoader
import com.example.assigmentbypyramidion.repository.Repository
import com.example.assigmentbypyramidion.util.UtilityMethod
import com.example.assigmentbypyramidion.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var viewModel : MainViewModel
    lateinit var adapter: AdapterUserData
    lateinit var loader: CustomLoader


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val repo = Repository(UserDataService)
        viewModel = ViewModelProvider(this,ViewModelFactory(repo)).get(MainViewModel::class.java)
        loader = CustomLoader(this)
        initAdapter()
        fetchData()

    }

    private fun initAdapter(){
        adapter = AdapterUserData(arrayListOf())
        binding.userDetailRecycler.adapter = adapter
    }

    private fun fetchData(){
        viewModel.getUsersData().observe(this, Observer {
            it?.let { stateData ->
                when (stateData.status) {
                    StateData.DataStatus.SUCCESS -> {
                        val data = stateData.data?.data
                        if (data != null) {
                            adapter.initList(data)
                        }
                        loader.hideProgress()
                    }
                    StateData.DataStatus.ERROR -> {
                        loader.hideProgress()
                        Toast.makeText(this, stateData.error?.message, Toast.LENGTH_LONG).show()
                    }
                    StateData.DataStatus.LOADING -> {
                        loader.showProgress()
                    }
                }
            }
        })
    }
}