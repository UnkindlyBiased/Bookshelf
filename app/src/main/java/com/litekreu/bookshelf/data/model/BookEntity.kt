package com.litekreu.bookshelf.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Books",
    foreignKeys = [
        ForeignKey(
            entity = AuthorEntity::class,
            parentColumns = ["AuthorId"],
            childColumns = ["AuthorRefId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "BookId")
    val id: Int? = null,

    @ColumnInfo(name = "BookName")
    val bookName: String,

    @ColumnInfo(name = "BookReleaseYear")
    val bookReleaseYear: Int,

    @ColumnInfo(name = "BookDescription")
    val bookDescription: String,

    @ColumnInfo(name = "BookImageUrl")
    val bookImageUrl: String,

    @ColumnInfo(name = "AuthorRefId")
    val authorRefId: Int
)
