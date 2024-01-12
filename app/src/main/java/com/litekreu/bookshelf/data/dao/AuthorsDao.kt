package com.litekreu.bookshelf.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.litekreu.bookshelf.data.model.AuthorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AuthorsDao {
    @Upsert
    suspend fun insertAuthor(author: AuthorEntity)

    @Delete
    suspend fun deleteAuthor(author: AuthorEntity)

    @Query("SELECT * FROM Authors")
    fun getAllAuthors(): Flow<List<AuthorEntity>>

    @Query("SELECT * FROM Authors JOIN Books ON AuthorId = :authorRefId")
    fun getAuthorFromBook(authorRefId: Int): AuthorEntity
}