package com.example.tempature_app;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


public class Parametre extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parametre);
        Log.i("parametre", "start");
        final Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                updateFiles();
                Parametre.this.finish();
                setContentView(R.layout.activity_main);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("parameter", "destroy");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void updateFiles() {
        EditText couetteEdit = findViewById(R.id.couetteEdit);
        EditText sweatEdit = findViewById(R.id.sweatEdit);
        EditText manteauEdit = findViewById(R.id.manteauEdit);
        SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE).edit();
        Log.i("manteau edit", manteauEdit.getText().toString());
        Log.i("sweat edit", sweatEdit.getText().toString());
        Log.i("couette edit", couetteEdit.getText().toString());
        if(couetteEdit.getText().length() > 0){
            editor.putString(getString(R.string.key_couette),couetteEdit.getText().toString());
        }
        if (couetteEdit.> 0)
        editor.putString(getString(R.string.key_sweat),sweatEdit.getText().toString());
        editor.putString(getString(R.string.key_manteau),manteauEdit.getText().toString());
        editor.commit();
    }
}
