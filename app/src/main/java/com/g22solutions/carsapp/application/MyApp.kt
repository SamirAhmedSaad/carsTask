package com.g22solutions.carsapp.application

import android.content.Context
import android.content.SharedPreferences
import com.g22solutions.carsapp.di.component.AppComponent
import com.g22solutions.carsapp.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject


class MyApp : DaggerApplication() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate() {
        super.onCreate()


    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        var myComponent: AppComponent = DaggerAppComponent.builder().builder(this).build()
        myComponent.inject(this)
        return myComponent
    }


}