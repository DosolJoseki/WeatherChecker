package com.home.joseki.weatherchecker.model

class WeatherList {
    var clouds: Clouds? = null

    var dt: String? = null

    var wind: Wind? = null

    var sys: Sys? = null

    var snow: Snow? = null

    var weather: Array<WeatherDisc>? = null

    var dt_txt: String? = null

    var main: Main? = null

    override fun toString(): String {
        return "ClassPojo [clouds = $clouds, dt = $dt, wind = $wind, sys = $sys, snow = $snow, weather = $weather, dt_txt = $dt_txt, main = $main]"
    }
}
