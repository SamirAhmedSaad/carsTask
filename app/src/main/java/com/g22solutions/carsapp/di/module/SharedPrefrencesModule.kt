package com.g22solutions.carsapp.di.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module(includes = [ApplicationModule::class])
class SharedPrefrencesModule {

    @Singleton
    @Provides
    fun getSharedPrefrences(context: Context): SharedPreferences {
        return context.getSharedPreferences("news",MODE_PRIVATE)
    }


}