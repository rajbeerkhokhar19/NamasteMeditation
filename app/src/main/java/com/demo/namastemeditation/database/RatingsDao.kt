package com.demo.namastemeditation.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RatingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRating(movie: RatingsEntity)

    @Query("SELECT * FROM ratings_table where detail_id=:detailId")
    fun getRatingsByDetailId(detailId: Int): LiveData<List<RatingsEntity>>

    @Query("SELECT COUNT (*) FROM ratings_table where detail_id=:detailId")
    suspend fun getSizeOfDatabaseById(detailId: Int): Int
}