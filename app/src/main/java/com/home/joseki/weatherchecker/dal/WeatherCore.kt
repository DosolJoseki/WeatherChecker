package com.home.joseki.weatherchecker.dal

import android.app.Activity
import android.content.Context
import com.google.gson.Gson
import com.home.joseki.weatherchecker.R
import com.home.joseki.weatherchecker.contracts.MainContract
import com.home.joseki.weatherchecker.interfaces.WeatherMessageInterfacer
import com.home.joseki.weatherchecker.model.CityList
import com.home.joseki.weatherchecker.model.Weather
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.InetSocketAddress
import java.net.Socket
import java.util.concurrent.Callable

class WeatherCore(context: Context, private val activity: Activity?) : MainContract.MainInteractor {
    private val messagesApi: WeatherMessageInterfacer

    override val isOnline: Single<Boolean>
        get() = Single.fromCallable(Callable {
            try {
                val timeoutMs = 1500
                val socket = Socket()
                val socketAddress = InetSocketAddress("8.8.8.8", 53)

                socket.connect(socketAddress, timeoutMs)
                socket.close()

                return@Callable true
            } catch (e: IOException) {
                return@Callable false
            }
        }).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override val cities: CityList?
        get() {
            try {
                val reader = BufferedReader(
                    InputStreamReader(activity!!.assets.open("cities.txt"))
                )
                val gson = Gson()
                return gson.fromJson(reader, CityList::class.java)
            } catch (e: IOException) {
                return null
            }
        }


    override fun getForecast(lat: Double?, lng: Double?): Observable<Response<Weather>> {
        return messagesApi.getForecast(
            lat,
            lng,
            "metric",
            activity!!.baseContext.resources.getString(R.string.open_weather_api_key)
        )
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(context.resources.getString(R.string.base_url))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        messagesApi = retrofit.create(WeatherMessageInterfacer::class.java)
    }

    override fun getResourceString(id: Int): String {
        return if (activity == null) {
            ""
        } else {
            activity.getString(id)
        }
    }
}
