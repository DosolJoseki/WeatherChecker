package com.home.joseki.weatherchecker.model

class Weather {
    var message: String? = null

    var cnt: String? = null

    var cod: String? = null

    var list: List<WeatherList>? = null

    var city: City? = null

    override fun toString(): String {
        return "ClassPojo [message = $message, cnt = $cnt, cod = $cod, list = $list, city = $city]"
    }
}