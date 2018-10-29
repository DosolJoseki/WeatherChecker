package com.home.joseki.weatherchecker.interfaces

import com.home.joseki.weatherchecker.model.Weather
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherMessageInterfacer {

    @GET("forecast")
    fun getForecast(
        @Query("lat") lat: Double?,
        @Query("lon") lon: Double?,
        @Query("units") units: String,
        @Query("appid") appid: String
    ): Observable<Response<Weather>>
}