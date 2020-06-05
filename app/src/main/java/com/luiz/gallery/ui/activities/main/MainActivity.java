package com.luiz.gallery.ui.activities.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.luiz.gallery.R;

/**
 * Activity main onde seto o NavHostFragment e o navigation para utilizar os fragments
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
