package com.example.virtualguide;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
// Главный контейнер приложения VirtualGuide
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}
