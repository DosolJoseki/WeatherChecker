package com.home.joseki.weatherchecker

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.home.joseki.weatherchecker.Fragments.MainFragment

class MainActivity : AppCompatActivity() {

    private val mainFragment: MainFragment = MainFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fTrans: FragmentTransaction = supportFragmentManager.beginTransaction()
        fTrans.add(R.id.frameLayout, mainFragment, getString(R.string.fragment_main_tag))
        fTrans.commitAllowingStateLoss()
    }
}
