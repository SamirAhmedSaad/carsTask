package com.g22solutions.carsapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.g22solutions.carsapp.di.MyViewModelProviders
import com.g22solutions.carsapp.di.scope.MyMapKey
import com.g22solutions.carsapp.screens.carsActivityPackage.CarsViewModelImp
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class MyViewModelModule {

    @Binds
    @IntoMap
    @MyMapKey(CarsViewModelImp::class)
    abstract fun bindCarsViewModelImp(carsViewModelImp : CarsViewModelImp) : ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: MyViewModelProviders): ViewModelProvider.Factory
}