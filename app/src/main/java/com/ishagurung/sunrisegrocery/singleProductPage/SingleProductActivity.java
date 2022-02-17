package com.ishagurung.sunrisegrocery.singleProductPage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ishagurung.sunrisegrocery.R;

public class SingleProductActivity extends AppCompatActivity {
    public static String DATA_KEY = "ds";
    public static String SINGLE_DATA_KEY = "sds";
    public static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);
    }
}