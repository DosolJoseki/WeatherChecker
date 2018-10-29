package com.home.joseki.weatherchecker.Fragments

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.home.joseki.weatherchecker.R
import com.home.joseki.weatherchecker.adapters.CityAdapter
import com.home.joseki.weatherchecker.adapters.WeatherAdapter
import com.home.joseki.weatherchecker.contracts.MainContract
import com.home.joseki.weatherchecker.dal.WeatherCore
import com.home.joseki.weatherchecker.model.CityList
import com.home.joseki.weatherchecker.model.Weather
import com.home.joseki.weatherchecker.presenters.MainPresenter
import com.toptoche.searchablespinnerlibrary.SearchableSpinner
import retrofit2.Response
import java.lang.Double.*

import java.util.ArrayList

class MainFragment : Fragment(), MainContract.MainViewInterface {
    private var presenter: MainPresenter? = null
    private var cityRecycler: RecyclerView? = null
    private var citySpinner: SearchableSpinner? = null
    private var activity: Activity? = null
    private var weatherAdapter: WeatherAdapter? = null
    private var swipe: SwipeRefreshLayout? = null

    private var lat: Double = 42.4822
    private var lng: Double = 20.7458

    override fun showProgress(show: Boolean) {
        swipe!!.isRefreshing = show
    }

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun setWeatherInfo(weather: Response<Weather>) {
        showProgress(false)

        if (cityRecycler == null || weatherAdapter == null || weather.body() == null) {
            return
        }

        weatherAdapter!!.clearItems()
        weatherAdapter!!.setItems(weather.body()!!.list!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity = getActivity()
        presenter = MainPresenter(WeatherCore(context!!, getActivity()), this)
        weatherAdapter = WeatherAdapter(context!!)

        cityRecycler = activity!!.findViewById(R.id.city_recycler)
        cityRecycler!!.adapter = weatherAdapter
        cityRecycler!!.layoutManager = LinearLayoutManager(activity)

        citySpinner = activity!!.findViewById(R.id.city_spinner)
        citySpinner!!.setTitle("Select Item")
        citySpinner!!.setPositiveButton("OK")

        swipe = activity!!.findViewById(R.id.swipe)
        swipe!!.setOnRefreshListener { presenter!!.getForecast(lat, lng) }


        fillCityList(presenter!!.cities)

        presenter!!.getForecast(lat, lng)
    }

    private fun fillCityList(list: CityList?) {
        if (list == null || list.Cities!!.isEmpty()) {
            return
        }

        val infosList = ArrayList<String>()
        for (info in list.Cities!!) {
            infosList.add(info.city + ", " + info.country)
        }

        citySpinner!!.adapter = CityAdapter(context!!, R.id.city_spinner, infosList)
        citySpinner!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                val info = list.Cities!![position]
                lat = valueOf(info.lat!!)
                lng = valueOf(info.lng!!)
                presenter!!.getForecast(lat, lng)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }
}
