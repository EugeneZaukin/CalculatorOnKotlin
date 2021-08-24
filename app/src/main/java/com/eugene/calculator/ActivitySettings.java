package com.eugene.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.radiobutton.MaterialRadioButton;

public class ActivitySettings extends AppCompatActivity {

    private static final String NAME_SHARED_PREFERENCE = "LOGIN";
    private static final String APP_THEME = "APP_THEME";
    private static final int SCHOOL_THEME = 0;
    private static final int SPACE_THEME = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getAppTheme(R.style.SchoolTheme));
        setContentView(R.layout.activity_settings);
        initThemeChoose();
        setAccept();
    }

    //Получить тему по коду
    private int getAppTheme(int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(codeStyle));
    }

    //Ищем тему по номеру
    private int codeStyleToStyleId(int codestyle) {
        switch (codestyle) {
            case SCHOOL_THEME:
                return R.style.SchoolTheme;
            case SPACE_THEME:
                return R.style.SpaceTheme;
            default:
                return R.style.SchoolTheme;
        }
    }

    private int getCodeStyle(int codestyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        return sharedPref.getInt(APP_THEME, codestyle);
    }

    //Инициализируем кнопки RadioGroup
    private void initThemeChoose() {
        initRadioButton(findViewById(R.id.school_style_button), SCHOOL_THEME);
        initRadioButton(findViewById(R.id.space_style_button), SPACE_THEME);
        RadioGroup rg = findViewById(R.id.radioGroup);
        ((MaterialRadioButton) rg.getChildAt(getCodeStyle(SCHOOL_THEME))).setChecked(true);
    }

    //Обработка нажатия RadioButton
    private void initRadioButton(View button, final int codestyle) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppTheme(codestyle);
                recreate();
            }
        });
    }

    //Записываем тему
    private void setAppTheme(int codestyle) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, codestyle);
        editor.apply();
    }

    //Обработка кнопки принять
    private void setAccept() {
        Button accept = findViewById(R.id.button_ok);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}