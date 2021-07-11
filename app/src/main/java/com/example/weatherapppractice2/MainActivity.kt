package com.example.weatherapppractice2

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapppractice2.models.Weather
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Callback
import retrofit2.Response

const val TAG = "sohailalam"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        button.setOnClickListener {
////            getWeather()
//        }

//        button.setOnClickListener(View.OnClickListener {
//            getWeatherData(
//                textField.getText().toString().trim()
//            )
//        })



    }


}