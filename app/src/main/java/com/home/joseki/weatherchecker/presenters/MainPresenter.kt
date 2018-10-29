package com.home.joseki.weatherchecker.presenters

import com.home.joseki.weatherchecker.R
import com.home.joseki.weatherchecker.contracts.MainContract
import com.home.joseki.weatherchecker.model.CityList
import com.home.joseki.weatherchecker.model.Weather
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

class MainPresenter(
    private val interactor: MainContract.MainInteractor,
    private val view: MainContract.MainViewInterface?
) : MainContract.PresenterInterface {

    override val cities: CityList
        get() = interactor.cities!!

    override fun getForecast(lat: Double?, lng: Double?) {
        view?.showProgress(true)

        val compositeDisposable = CompositeDisposable()

        compositeDisposable.add(interactor.isOnline.subscribe { aBoolean ->
            if (aBoolean) {
                interactor.getForecast(lat, lng)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .take(1)
                    .subscribe(object : Observer<Response<Weather>> {
                        override fun onSubscribe(d: Disposable) {

                        }

                        override fun onNext(weatherResponse: Response<Weather>) {
                            view!!.setWeatherInfo(weatherResponse)
                        }

                        override fun onError(e: Throwable) {
                            view!!.showProgress(false)
                            view.showToast(e.message!!)
                        }

                        override fun onComplete() {

                        }
                    })
            } else {
                view!!.showToast(interactor.getResourceString(R.string.error_string_no_internet_connection))
                compositeDisposable.dispose()
            }
        })

    }
}
