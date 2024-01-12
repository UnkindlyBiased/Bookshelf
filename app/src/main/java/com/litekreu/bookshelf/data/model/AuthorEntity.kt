package com.litekreu.bookshelf.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity("Authors")
data class AuthorEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("AuthorId")
    val authorId: Int? = null,

    @ColumnInfo(name = "AuthorName")
    val authorName: String,

    @ColumnInfo(name = "AuthorBirthDate")
    val birthDate: Date,

    @ColumnInfo(name = "AuthorImageLink")
    val authorImageUrl: String
)