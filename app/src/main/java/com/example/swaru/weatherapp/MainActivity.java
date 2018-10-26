package com.example.swaru.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import zh.wang.android.yweathergetter4a.WeatherInfo;
import zh.wang.android.yweathergetter4a.YahooWeather;
import zh.wang.android.yweathergetter4a.YahooWeatherInfoListener;

public class MainActivity extends AppCompatActivity implements YahooWeatherInfoListener {

    YahooWeather mYahooWeather;
    YahooWeatherInfoListener yahooWeatherInfoListener;
    private ImageButton nextButton;
    private TextView cityName;
    TextView minTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mYahooWeather = YahooWeather.getInstance();
        nextButton = findViewById(R.id.btn_next);
        cityName = findViewById(R.id.city_name);
        minTemp = findViewById(R.id.min_temp);
        /*yahooWeatherInfoListener = new YahooWeatherInfoListener() {
            @Override
            public void gotWeatherInfo(WeatherInfo weatherInfo, YahooWeather.ErrorType errorType) {

            }
        };*/
        //mYahooWeather.queryYahooWeatherByPlaceName(getApplicationContext(), "Sydney Australia", yahooWeatherInfoListener);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location = "Sydney Australia";
                searchByPlaceName(location);
            }
        });
    }

    @Override
    public void gotWeatherInfo(WeatherInfo weatherInfo, YahooWeather.ErrorType errorType) {
        if (weatherInfo != null){
            cityName.setText(weatherInfo.getTitle());
            minTemp.setText(weatherInfo.getCurrentTemp());
        }

    }
    private void searchByPlaceName(String location) {
        mYahooWeather.setNeedDownloadIcons(true);
        mYahooWeather.setUnit(YahooWeather.UNIT.CELSIUS);
        mYahooWeather.setSearchMode(YahooWeather.SEARCH_MODE.PLACE_NAME);
        mYahooWeather.queryYahooWeatherByPlaceName(getApplicationContext(), location, MainActivity.this);
    }
}
