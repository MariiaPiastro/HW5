package com.geekhub.mariia_piastro.hw5.weather.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geekhub.mariia_piastro.hw5.weather.R

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(): DetailFragment =
            DetailFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }


}
