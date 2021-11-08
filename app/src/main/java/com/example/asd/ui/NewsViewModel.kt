package com.example.asd.ui

import androidx.lifecycle.ViewModel
import com.example.asd.mydb.NewsDAO
import io.reactivex.Flowable

class NewsViewModel(
    private val dataSource: NewsDAO
) : ViewModel() {

    fun newsName(): Flowable<String> {
        return dataSource.getById(id = 1)
            .map { news -> news.name }
    }
}