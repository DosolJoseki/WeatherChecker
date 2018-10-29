package com.home.joseki.weatherchecker.model

import com.google.gson.annotations.SerializedName

class Snow {
    @SerializedName("3h")
    var h: String? = null

    override fun toString(): String {
        return "ClassPojo [3h = $h]"
    }
}
