package com.home.joseki.weatherchecker.adapters


import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.home.joseki.weatherchecker.R
import com.home.joseki.weatherchecker.model.WeatherList
import com.squareup.picasso.Picasso
import io.reactivex.annotations.NonNull

import java.util.*

class WeatherAdapter(private val context: Context) : RecyclerView.Adapter<WeatherAdapter.ProjectViewHolder>() {
    private val weatherItems = ArrayList<WeatherList>()

    fun setItems(list: Collection<WeatherList>) {
        weatherItems.addAll(list)
        notifyDataSetChanged()
    }

    fun clearItems() {
        weatherItems.clear()
        notifyDataSetChanged()
    }

    inner class ProjectViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvDate: TextView = itemView.findViewById(R.id.text_date)
        private val tvTemp: TextView = itemView.findViewById(R.id.text_temp)
        private val tvClouds: TextView = itemView.findViewById(R.id.text_clouds)
        private val ivIcon: ImageView = itemView.findViewById(R.id.image_weather)
        private val layout: ConstraintLayout = itemView.findViewById(R.id.layout)

        fun bind(item: WeatherList, i: Int) {
            tvDate.text = item.dt_txt
            tvTemp.text = String.format(context.getString(R.string.temp_info), item.main!!.temp)
            tvClouds.text = item.weather!![0].main
            Picasso.get().load(String.format(context.getString(R.string.icon_url), item.weather!![0].icon)).into(ivIcon)

            if (i % 2 == 0) {
                layout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorItemsMain))
            } else {
                layout.setBackgroundColor(ContextCompat.getColor(context, R.color.colorItemsSecondary))
            }
        }
    }

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, i: Int): ProjectViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.listelement_day, viewGroup, false)
        return ProjectViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull projectViewHolder: ProjectViewHolder, i: Int) {
        projectViewHolder.bind(weatherItems[i], i)
    }

    override fun getItemCount(): Int {
        return weatherItems.size
    }
}
