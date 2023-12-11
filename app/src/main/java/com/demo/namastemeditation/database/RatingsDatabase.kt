package com.demo.namastemeditation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// defining entity class and database version
@Database(entities = [RatingsEntity::class], version = 1)
abstract class RatingsDatabase : RoomDatabase() {

    // defining movie dao class in the database
    abstract val dao: RatingsDao

    companion object {
        @Volatile
        private var instance: RatingsDatabase? = null

        // creating database and instance of the movie database
        fun getDatabaseInstance(context: Context): RatingsDatabase? {
            if (instance == null) {
                synchronized(RatingsDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        RatingsDatabase::class.java,
                        "ratings_database"
                    )
                        .build()
                }
            }
            return instance
        }
    }
}
