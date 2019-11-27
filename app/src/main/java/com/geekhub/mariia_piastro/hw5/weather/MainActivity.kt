package com.geekhub.mariia_piastro.hw5.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geekhub.mariia_piastro.hw5.weather.fragments.DetailFragment
import com.geekhub.mariia_piastro.hw5.weather.fragments.ListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ListFragment.newInstance()
                )
                .commit()
        }

        if (fragment_container_details != null) {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStack()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,
                    ListFragment()
                )
                .replace(
                    R.id.fragment_container_details,
                    DetailFragment.newInstance()
                )
                .commit()
        }
    }
}
