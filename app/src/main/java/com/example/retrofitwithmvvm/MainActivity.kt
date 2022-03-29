package com.example.retrofitwithmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitwithmvvm.api.QuoteService
import com.example.retrofitwithmvvm.api.RetrofitHelper
import com.example.retrofitwithmvvm.repository.QuoteRepository
import com.example.retrofitwithmvvm.viewmodels.MainViewModel
import com.example.retrofitwithmvvm.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {


    lateinit var mainViewModel: MainViewModel
    val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
    val repository = QuoteRepository(quoteService)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))
            .get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, {

            Log.d("MainActivity",it.results.toString())
        })
    }
}