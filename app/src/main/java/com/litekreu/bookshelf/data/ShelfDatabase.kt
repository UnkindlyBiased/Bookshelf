package com.litekreu.bookshelf.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.litekreu.bookshelf.data.dao.AuthorsDao
import com.litekreu.bookshelf.data.dao.BooksDao
import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity

@Database(
    entities = [AuthorEntity::class, BookEntity::class],
    version = 1
)
abstract class ShelfDatabase : RoomDatabase() {
    abstract val authorsDao: AuthorsDao

    abstract val booksDao: BooksDao
}