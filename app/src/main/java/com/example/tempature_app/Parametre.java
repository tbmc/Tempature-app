package com.example.tempature_app;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        updateFiles();
        final Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
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
        couetteEdit.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable textChanged) {
                SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE).edit();
                editor.putString(getString(R.string.key_couette), textChanged.toString());
                editor.commit();
            }
        });

        EditText sweatEdit = findViewById(R.id.sweatEdit);
        sweatEdit.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable textChanged) {
                SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE).edit();
                editor.putString(getString(R.string.key_sweat), textChanged.toString());
                editor.commit();
            }
        });


        EditText manteauEdit = findViewById(R.id.manteauEdit);
        manteauEdit.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable textChanged) {
                SharedPreferences.Editor editor = getSharedPreferences(getString(R.string.preferences), MODE_PRIVATE).edit();
                editor.putString(getString(R.string.key_manteau), textChanged.toString());
                editor.commit();
            }
        });
    }
}
