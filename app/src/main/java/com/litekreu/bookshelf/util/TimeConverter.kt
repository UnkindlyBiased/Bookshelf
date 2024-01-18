package com.litekreu.bookshelf.util

import androidx.room.TypeConverter
import java.sql.Date

class TimeConverter {
    @TypeConverter
    fun dateToTimestamp(date: Date?) : Long? = date?.time

    @TypeConverter
    fun timestampToDate(timestamp: Long?) : Date? = timestamp?.let { Date(it) }
}