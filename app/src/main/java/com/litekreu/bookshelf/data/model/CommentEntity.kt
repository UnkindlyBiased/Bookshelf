package com.litekreu.bookshelf.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "Comments",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["BookId"],
            childColumns = ["BookRefId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CommentId")
    val id: Int? = null,

    @ColumnInfo(name = "CommentText")
    val commentText: String,

    @ColumnInfo(name = "CommentProgress")
    val commentProgress: Int = 0,

    @ColumnInfo(name = "BookRefId")
    val bookRefId: Int? = null
)
