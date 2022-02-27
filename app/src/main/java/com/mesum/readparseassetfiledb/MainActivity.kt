package com.mesum.readparseassetfiledb

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ParseDataViewModel
    lateinit var viewModelFactory : ParseDataViewModel.ParseViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        viewModelFactory = ParseDataViewModel.ParseViewModelFactory((application as ItemApplication).database.itemDao())
        viewModel = ViewModelProvider(this, viewModelFactory).get(ParseDataViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.insertRawData(this)


    }
}