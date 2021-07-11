package com.example.weatherapppractice2.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import coil.load
import com.example.weatherapppractice2.R
import com.example.weatherapppractice2.TAG
import com.example.weatherapppractice2.WeatherService
import com.example.weatherapppractice2.models.Weather
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.view.*
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weather, container, false)

//        view.button.setOnClickListener {
//            getWeather(etCity.text.toString().trim())
//        }


        view.etCity.setOnEditorActionListener { v, actionId, event ->

            if (EditorInfo.IME_ACTION_SEARCH == actionId){
                Toast.makeText(requireContext(), "searching", Toast.LENGTH_SHORT).show()
                getWeather(etCity.text.toString().trim())
                etCity.isCursorVisible = false
            }
            return@setOnEditorActionListener false
        }



        return view
    }

    private fun getWeather(city: String) {
        val weather: retrofit2.Call<Weather> = WeatherService.weatherInstance.getWeather(city)
        weather.enqueue(object : Callback<Weather> {

            override fun onFailure(call: retrofit2.Call<Weather>, t: Throwable) {
                Log.d(TAG, "onFailure: failed", t)
                Toast.makeText(context, "Please type correct city name", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: retrofit2.Call<Weather>, response: Response<Weather>) {
                val weather = response.body()
                if (weather != null) {
                    Log.d(TAG, weather.toString())
                    val temp = weather.main.temp.toString()
                    tvCity.text = weather.name.toString()
                    //val icon = weatherJsonObject2.getJSONObject(0).getString("icon")
                    val weatherIconUrl = weather.weather[0].icon
                    weatherImage.load("https://openweathermap.org/img/wn/${weatherIconUrl}@4x.png")
                    val windSpeed = weather.wind.speed.toString()
                    tvTemp.setText("${temp}°C")
                    airSpeed.text = "${windSpeed}m/s"
                    val tempMin = weather.main.tempMin.toString()
                    val tempMax = weather.main.tempMax.toString()
                    view!!.tempMin.setText("${tempMin}°C")
                    view!!.tempMax.setText("${tempMax}°C")
                    weatherDescription.text = weather.weather[0].description
                }
            }

        })

    }
}