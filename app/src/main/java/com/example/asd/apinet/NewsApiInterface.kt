package com.example.asd.apinet

import com.example.asd.mydb.News
import io.reactivex.Observable
import retrofit2.http.GET

interface NewsApiInterface {
    @GET("posts")
//    fun newsReq(): Observable<List<NewsApi>>
    fun newsReq(): Observable<List<Results>>
}