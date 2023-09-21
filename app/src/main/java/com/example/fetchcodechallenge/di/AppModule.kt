package com.example.fetchcodechallenge.di

import android.app.Application
import androidx.room.Room
import com.example.fetchcodechallenge.model.database.ItemDatabase
import com.example.fetchcodechallenge.utils.Constants
import com.example.fetchcodechallenge.webservice.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): ItemDatabase = Room.databaseBuilder(app,ItemDatabase::class.java,"db_item")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideDao(database: ItemDatabase) = database.ItemDao()

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiClient(retrofit: Retrofit): ApiClient = retrofit.create(ApiClient::class.java)

}