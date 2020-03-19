package com.g22solutions.carsapp.di.component

import android.app.Application
import com.g22solutions.carsapp.application.MyApp
import com.g22solutions.carsapp.di.module.ApplicationModule
import com.g22solutions.carsapp.di.module.MyViewModelModule
import com.g22solutions.carsapp.di.module.NetworkModule
import com.g22solutions.carsapp.di.module.SharedPrefrencesModule
import com.g22solutions.carsapp.di.module.ActivityContributeModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ApplicationModule::class, NetworkModule::class, SharedPrefrencesModule::class, ActivityContributeModule::class, MyViewModelModule::class])
interface AppComponent : AndroidInjector<MyApp> {


    @Component.Builder
    interface Builder{

        @BindsInstance
        fun builder(application: Application): Builder


        fun build(): AppComponent

    }


    fun inject(application: Application)

}