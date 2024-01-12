package com.litekreu.bookshelf.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Authors")
data class AuthorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("AuthorId")
    val authorId: Int? = null,

    @ColumnInfo(name = "AuthorName")
    val authorName: String,

    @ColumnInfo(name = "AuthorImageLink")
    val authorImageUrl: String
)