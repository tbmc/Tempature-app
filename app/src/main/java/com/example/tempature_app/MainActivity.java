package com.example.tempature_app;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MainActivity extends AppCompatActivity {

    private Weather meteo = new Weather();

    private ArrayMap<String,Integer> param = new ArrayMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAPI();
        loadObserver();
        loadButton();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            resetLayout();
            loadAPI();
        }
    }

    private void resetLayout(){
        findViewById(R.id.sweat).setVisibility(View.INVISIBLE);
        findViewById(R.id.hot).setVisibility(View.GONE);
        findViewById(R.id.manteau).setVisibility(View.GONE);
        findViewById(R.id.couette).setVisibility(View.GONE);
    }

    private void loadObserver() {
        final Observer<String> weatherObserver = new Observer<String>() {
            @Override
            public void onChanged(@Nullable final String weather) {
                if (weather != null) {
                    Log.i("info", "meteo");
                    setWeather(weather);
                }
            }
        };
        meteo.getWeather().observe(this, weatherObserver);
        final Observer<Integer> tempObserver = new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer temp) {
                if (temp != null) {
                    Log.i("info", "icon");
                    loadParameter();
                    showIcon(temp);
                    TextView tempText = findViewById(R.id.temp);
                    tempText.setText(String.valueOf(temp).concat("°"));
                }
            }
        };
        meteo.getTemp().observe(this, tempObserver);
    }

    private void loadButton() {
        final Button reload = findViewById(R.id.relancer);
        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadAPI();
            }
        });

        final Button parameter = findViewById(R.id.parametre);
        parameter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startParametre();
            }
        });
    }

    private void startParametre(){
        startActivity(new Intent(this, Parametre.class));
    }

    private void loadAPI() {
        try {
            meteo.callAPI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadParameter() {
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(getString(R.string.preferences),MODE_PRIVATE);
        String defaultTemp = "15";
        try {
            param.put("manteau",Integer.parseInt(preferences.getString(getString(R.string.key_manteau),defaultTemp)));
            param.put("sweat",Integer.parseInt(preferences.getString(getString(R.string.key_sweat),defaultTemp)));
            param.put("couette",Integer.parseInt(preferences.getString(getString(R.string.key_couette),defaultTemp)));
        }catch (NumberFormatException e){
            startParametre();
        }
    }

    private void showIcon(int temp){
        if (temp < param.get("manteau")) {
            setSweat();
            setManteau();
        } else if (temp < param.get("sweat")) {
            setSweat();
        } else {
            setHot();
        }
        if (temp < param.get("couette")) {
            setCouette();
        }
    }

    private void setHot() {
        ImageView img = findViewById(R.id.hot);
        img.setVisibility(View.VISIBLE);
        Log.i("info", "hot");
    }

    private void setCouette() {
        ImageView img = findViewById(R.id.couette);
        img.setVisibility(View.VISIBLE);
        Log.i("info", "couette");
    }

    private void setSweat() {
        ImageView img = findViewById(R.id.sweat);
        img.setVisibility(View.VISIBLE);
        Log.i("info", "sweat");
    }

    private void setManteau() {
        ImageView img = findViewById(R.id.manteau);
        img.setVisibility(View.VISIBLE);
        Log.i("info", "coat");
    }

    private void setWeather(String weather) {
        Log.i("info", weather);
        ImageView img = findViewById(R.id.weather);
        switch (weather) {
            case "Clear":
                img.setImageResource(R.mipmap.soleil);
                break;
            case "Clouds":
                img.setImageResource(R.mipmap.nuage);
                break;
            case "Rain":
            case "Drizzle":
                img.setImageResource(R.mipmap.pluie);
                break;
            case "Snow":
                img.setImageResource(R.mipmap.neige);
                break;
            default:
                img.setImageResource(R.mipmap.tempete);
                break;
        }
    }

}
