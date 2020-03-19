package com.g22solutions.carsapp.retrofitApi

import com.g22solutions.carsapp.retrofitServiceDataModel.CarsDataModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {

    @GET("api/v1/cars")
    fun getCars(@Query("page") page : Int) : Single<Response<CarsDataModel>>

}