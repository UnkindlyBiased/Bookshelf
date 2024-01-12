package com.litekreu.bookshelf.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.litekreu.bookshelf.data.model.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {
    @Upsert
    suspend fun upsertBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("SELECT * FROM Books")
    fun getAllBooks() : Flow<List<BookEntity>>

    @Query("SELECT * FROM Books where BookName like '%' || :name || '%'")
    fun searchBooksByName(name: String): Flow<List<BookEntity>>
}