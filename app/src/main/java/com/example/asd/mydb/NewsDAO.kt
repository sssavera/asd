package com.example.asd.mydb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface NewsDAO {
    @Query("SELECT * FROM news WHERE id = :id")
    fun getById(id: Int): Flowable<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News): Completable

    @Query("DELETE FROM news")
    fun deleteAll()
}