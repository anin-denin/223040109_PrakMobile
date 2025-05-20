package com.example.ppm05.di

import android.app.Application
import androidx.room.Room
import com.example.ppm05.data.local.NoteDao
import com.example.ppm05.data.local.NoteDatabase
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
    fun provideDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(app, NoteDatabase::class.java, "note_db").build()
    }

    @Provides
    fun provideDao(db: NoteDatabase): NoteDao = db.noteDao()
}
