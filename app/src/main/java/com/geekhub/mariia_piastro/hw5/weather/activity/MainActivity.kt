package com.geekhub.mariia_piastro.hw5.weather.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.preference.PreferenceManager
import com.geekhub.mariia_piastro.hw5.weather.R
import com.geekhub.mariia_piastro.hw5.weather.entities.WeatherResponse
import com.geekhub.mariia_piastro.hw5.weather.fragments.DetailFragment
import com.geekhub.mariia_piastro.hw5.weather.fragments.ListFragment
import com.geekhub.mariia_piastro.hw5.weather.recyclerView.WeatherAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), WeatherAdapter.ItemClick {

    private var weatherResponse: WeatherResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ListFragment.newInstance()
                )
                .commit()
        } else weatherResponse = savedInstanceState.getSerializable("weather") as WeatherResponse

        if (fragment_container_details != null) {
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStack()
            supportFragmentManager
                .beginTransaction()
                .replace(
                    R.id.fragment_container,
                    ListFragment()
                )
                .replace(
                    R.id.fragment_container_details,
                    DetailFragment.newInstance(weatherResponse)
                )
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("weather", weatherResponse)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState!!)
        weatherResponse = savedInstanceState.getSerializable("weather") as WeatherResponse
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClick(weatherResponse: WeatherResponse) {
        this.weatherResponse = weatherResponse
        val fragmentDetails: FrameLayout? = fragment_container_details
        val detailsFragment =
            DetailFragment.newInstance(weatherResponse)
        if (fragmentDetails == null) { //портрет
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, detailsFragment)
                .addToBackStack(null)
                .commit()
        } else { //пейзаж
            if (supportFragmentManager.backStackEntryCount > 0)
                supportFragmentManager.popBackStack()
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container_details, detailsFragment)
                .commit()
        }
    }
}
