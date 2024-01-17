package com.litekreu.bookshelf.di

import android.app.Application
import androidx.room.Room
import com.litekreu.bookshelf.data.ShelfDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideShelfDatabase(app: Application): ShelfDatabase {
        return Room.databaseBuilder(
            app,
            ShelfDatabase::class.java,
            "ShelfDatabase"
        ).build()
    }
}