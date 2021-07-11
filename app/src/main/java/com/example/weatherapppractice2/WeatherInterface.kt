package com.example.weatherapppractice2

import com.example.weatherapppractice2.models.Weather
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY = "Get you own api key from openweather.org"
const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

interface WeatherInterface {
    //    @GET("group/{id}/users")
//    fun groupList(@Path("id") groupId: Int): Call<List<User?>?>?

//    @GET("weather?q={city},{state},{country}&units=metric&appid=${API_KEY}")
//    fun getWeather(
//        @Path("city") city: String = "Ratlam",
//        @Path("state") state: String = "mp",
//        @Path("country") country: String = "in"
//    ): retrofit2.Call<Weather>


    @GET("weather")
    fun getWeather(
        @Query("q") cityName: String = "Ratlam",
        @Query("appid") apiKey : String = API_KEY,
        @Query("units") units : String = "metric"
    ):retrofit2.Call<Weather>

}

object WeatherService {
    val weatherInstance: WeatherInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherInstance = retrofit.create(WeatherInterface::class.java)
    }
}
