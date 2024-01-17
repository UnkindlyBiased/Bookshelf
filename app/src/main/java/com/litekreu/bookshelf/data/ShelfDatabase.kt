package com.litekreu.bookshelf.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.litekreu.bookshelf.data.dao.AuthorsDao
import com.litekreu.bookshelf.data.dao.BooksDao
import com.litekreu.bookshelf.data.dao.CommentsDao
import com.litekreu.bookshelf.data.model.AuthorEntity
import com.litekreu.bookshelf.data.model.BookEntity
import com.litekreu.bookshelf.data.model.CommentEntity

@Database(
    entities = [AuthorEntity::class, BookEntity::class, CommentEntity::class],
    version = 4,
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3),
        AutoMigration(from = 3, to = 4)
    ]
)
abstract class ShelfDatabase : RoomDatabase() {
    abstract val authorsDao: AuthorsDao
    abstract val booksDao: BooksDao
    abstract val commentsDao: CommentsDao
}