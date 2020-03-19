package com.g22solutions.carsapp.di.module


import com.g22solutions.carsapp.screens.carsActivityPackage.CarsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityContributeModule {

    @ContributesAndroidInjector
    abstract fun getCarsActivity(): CarsActivity

}