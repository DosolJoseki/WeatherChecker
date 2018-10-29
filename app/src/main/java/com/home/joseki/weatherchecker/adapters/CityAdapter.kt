package com.home.joseki.weatherchecker.adapters

import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CityAdapter(
    context: Context, textViewResourceId: Int,
    private val cityInfos: List<String>
) : ArrayAdapter<String>(context, textViewResourceId, cityInfos) {

    override fun getCount(): Int {
        return cityInfos.size
    }

    override fun getItem(position: Int): String? {
        return cityInfos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = TextView(context)
        view.setTextColor(Color.BLACK)
        view.gravity = Gravity.CENTER
        view.text = cityInfos[position]

        return view
    }

    override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup
    ): View {
        val view = TextView(context)
        view.setTextColor(Color.BLACK)
        view.text = cityInfos[position]
        view.height = 60

        return view
    }
}
