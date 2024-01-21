package com.litekreu.bookshelf.di

import android.app.Application
import androidx.room.Room
import com.litekreu.bookshelf.data.ShelfDatabase
import com.litekreu.bookshelf.data.model.AuthorEntity
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
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDefaultAuthor(): AuthorEntity = AuthorEntity(
        authorName = "Джордж Орвелл",
        authorImageUrl = "https://hips.hearstapps.com/hmg-prod/images/george-orwell.jpg?crop=1xw:1.0xh;center,top&resize=640:*"
    )
}