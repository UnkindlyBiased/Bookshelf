package com.litekreu.bookshelf.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comments")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CommentId")
    val id: Int? = null,

    @ColumnInfo(name = "CommentText")
    val commentText: String,

    @ColumnInfo(name = "BookRefId")
    val bookRefId: Int? = null
)
