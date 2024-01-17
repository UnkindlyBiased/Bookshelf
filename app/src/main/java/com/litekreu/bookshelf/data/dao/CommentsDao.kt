package com.litekreu.bookshelf.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.litekreu.bookshelf.data.model.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentsDao {
    @Insert
    suspend fun insertComment(comment: CommentEntity)

    @Delete
    suspend fun deleteComment(comment: CommentEntity)

    @Query("SELECT * FROM Comments")
    fun getAllComments(): Flow<List<CommentEntity>>
}