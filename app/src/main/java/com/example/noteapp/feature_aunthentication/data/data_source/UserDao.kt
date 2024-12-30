package com.example.noteapp.feature_aunthentication.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.noteapp.feature_aunthentication.domain.model.User
@Dao
interface UserDao {

    @Insert(
        onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)


}