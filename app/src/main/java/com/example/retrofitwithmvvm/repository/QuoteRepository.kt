package com.example.retrofitwithmvvm.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofitwithmvvm.api.QuoteService
import com.example.retrofitwithmvvm.models.QuoteList

//Repository will access QuoteService and this will access hit our API.
//Repository class manage our data from retrofit or Room database.
class QuoteRepository(private val quoteService: QuoteService) {

    private val quoteLiveData = MutableLiveData<QuoteList>()

    //it will access quoteLiveData Mutable
    //we will observ this data in our viewModels.
    val quotes: LiveData<QuoteList>
    get() = quoteLiveData

    //we will define a function.That viewModel will call
    //This function is calling our API internally.
    suspend fun getQuotes(page: Int ){
        val result = quoteService.getQuotes(page)
        //check result from API is not null
        if (result?.body() != null){ //result != null && result.body() !=null

            quoteLiveData.postValue(result.body())

        }
    }

}