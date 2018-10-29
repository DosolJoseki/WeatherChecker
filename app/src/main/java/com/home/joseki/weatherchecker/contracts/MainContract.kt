package com.home.joseki.weatherchecker.contracts

import com.home.joseki.weatherchecker.model.CityList
import com.home.joseki.weatherchecker.model.Weather
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface MainContract {
    interface PresenterInterface {
        val cities: CityList
        fun getForecast(lat: Double?, lng: Double?)
    }

    interface MainViewInterface {
        fun showProgress(show: Boolean)
        fun showToast(message: String)
        fun setWeatherInfo(weather: Response<Weather>)
    }

    interface MainInteractor {
        val isOnline: Single<Boolean>
        val cities: CityList?
        fun getForecast(lat: Double?, lng: Double?): Observable<Response<Weather>>
        fun getResourceString(id: Int): String
    }
}
