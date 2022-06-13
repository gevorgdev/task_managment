package com.gev.data.di

import android.content.Context
import androidx.room.Room
import com.gev.data.locale.room.AppRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context): AppRoomDatabase {
        return Room.databaseBuilder(context, AppRoomDatabase::class.java, "appDatabase")
            .build()
    }
}