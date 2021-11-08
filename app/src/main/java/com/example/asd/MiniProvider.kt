package com.example.asd

import android.content.Context
import com.example.asd.mydb.NewsDAO
import com.example.asd.mydb.NewsDatabase
import com.example.asd.ui.ViewModelFactory

object MiniProvider {

    fun provideNewsDataSource(context: Context): NewsDAO {
        val database = NewsDatabase.getInst(context)
        return database.newsDao()
    }

    fun provideViewModelFactory(context: Context): ViewModelFactory {
        val dataSource = provideNewsDataSource(context)
        return ViewModelFactory(dataSource)
    }
}