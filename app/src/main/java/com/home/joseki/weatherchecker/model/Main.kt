package com.home.joseki.weatherchecker.model

class Main {
    var temp_kf: String? = null

    var humidity: String? = null

    var pressure: String? = null

    var temp_max: String? = null

    var sea_level: String? = null

    var temp_min: String? = null

    var temp: String? = null

    var grnd_level: String? = null

    override fun toString(): String {
        return "ClassPojo [temp_kf = $temp_kf, humidity = $humidity, pressure = $pressure, temp_max = $temp_max, sea_level = $sea_level, temp_min = $temp_min, temp = $temp, grnd_level = $grnd_level]"
    }
}
