package com.g22solutions.carsapp.di.module

import android.content.Context
import android.content.SharedPreferences
import com.g22solutions.carsapp.retrofitApi.RetrofitApi
import com.g22solutions.carsapp.NoConnectionPackage.ConnectivityInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module(includes = [ApplicationModule::class, SharedPrefrencesModule::class])
class NetworkModule {

    private val url = "http://demo1286023.mockable.io/"

    private val cachSize = (10 * 1024 * 1024).toLong()

    @Provides
    @Singleton
    fun getMyCache(context: Context): Cache {
        return Cache(context.cacheDir, cachSize)
    }

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun getOkHttp(
        context: Context,
        httpLoggingInterceptor: HttpLoggingInterceptor,
        sharedPreferences: SharedPreferences,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .cache(cache)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request()

                    val builder = request.newBuilder()

                    builder.addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Authorization", "Bearer 6968273ab6da4d83bf32c9d99299c72c")


                    return chain.proceed(builder.build())

                }
            })
            .addInterceptor(ConnectivityInterceptor(context))
            .build()
    }


    @Provides
    @Singleton
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getRetrofitApi(retrofit: Retrofit): RetrofitApi {
        return retrofit.create(RetrofitApi::class.java)
    }

}