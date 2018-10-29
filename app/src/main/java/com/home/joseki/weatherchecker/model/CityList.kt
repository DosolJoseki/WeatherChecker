package com.home.joseki.weatherchecker.model

class CityList {
    var Cities: List<CityInfo>? = null

    inner class CityInfo {
        var city: String? = null
        var city_ascii: String? = null
        var lat: String? = null
        var lng: String? = null
        var country: String? = null
        var iso2: String? = null
        var iso3: String? = null
        var admin_name: String? = null
        var capital: String? = null
        var id: String? = null
    }
}
