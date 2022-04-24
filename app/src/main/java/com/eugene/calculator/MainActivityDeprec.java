package com.eugene.calculator;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivityDeprec extends ActivitySettings {

    private final static String KEY = "KeyCalc";

    private final static int REQUEST_CODE_SETTINGS = 55;
    private static final String NAME_SHARED_PREFERENCE = "LOGIN";
    private static final String APP_THEME = "APP_THEME";
    private static final int SCHOOL_THEME = 0;
    private TextView textEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void initView() {
        setAsset();
    }

    //Шрифт отображаемого текста
    private void setAsset() {
        Typeface tf = Typeface.createFromAsset(getAssets(), "Wellfont.ttf");
        textEnter.setTypeface(tf);
    }

    //Обработка кнопки Settings
    private void setButtonSettings() {
        Intent intent = new Intent(this, ActivitySettings.class);
        startActivityForResult(intent, REQUEST_CODE_SETTINGS);
    }

    //Получили результат из второй активити
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode != REQUEST_CODE_SETTINGS) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        if (resultCode == RESULT_OK) {
            recreate();
        }
    }

    //Устанавливаем тему
    private void getAppTheme() {
        SharedPreferences sharedPreferences = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        if (sharedPreferences != null) {
            int codeStyle = sharedPreferences.getInt(APP_THEME, 0);
            if (codeStyle == SCHOOL_THEME) {
                setTheme(R.style.SchoolTheme);
            } else {
                setTheme(R.style.SpaceTheme);
            }
        }
    }
}