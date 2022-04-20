package com.eugene.calculator;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActivitySettings implements View.OnClickListener {

    private final static String KEY = "KeyCalc";

    private final static int REQUEST_CODE_SETTINGS = 55;
    private static final String NAME_SHARED_PREFERENCE = "LOGIN";
    private static final String APP_THEME = "APP_THEME";
    private static final int SCHOOL_THEME = 0;

    private final Logic calculator = new Logic();
    private Button buttonAC;
    private Button buttonDelete;
    private Button buttonPercent;
    private Button buttonDiv;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button buttonMultiplic;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button buttonDeduction;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button buttonSum;
    private Button button0;
    private Button buttonPoint;
    private Button buttonResult;
    private Button buttonSettings;
    private TextView textEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getAppTheme();
        setContentView(R.layout.activity_main);
//        initView();
    }

    private void initView() {
        findView();
        buttonClickListener();
        setAsset();
    }

    private void findView() {
        buttonAC = findViewById(R.id.button_ac);
        buttonDelete = findViewById(R.id.button_delete);
        buttonPercent = findViewById(R.id.button_percent);
        buttonDiv = findViewById(R.id.button_div);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        buttonMultiplic = findViewById(R.id.button_multiplic);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        buttonDeduction = findViewById(R.id.button_deduction);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonSum = findViewById(R.id.button_sum);
        button0 = findViewById(R.id.button_zero);
        buttonPoint = findViewById(R.id.button_point);
        buttonResult = findViewById(R.id.button_equal);
        textEnter = findViewById(R.id.textView2);
        buttonSettings = findViewById(R.id.button_settings);
    }

    private void buttonClickListener() {
        buttonAC.setOnClickListener(this);
        buttonDelete.setOnClickListener(this);
        buttonPercent.setOnClickListener(this);
        buttonDiv.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        buttonMultiplic.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        buttonDeduction.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);
        buttonSum.setOnClickListener(this);
        button0.setOnClickListener(this);
        buttonPoint.setOnClickListener(this);
        buttonResult.setOnClickListener(this);
        buttonSettings.setOnClickListener(this);
    }

    //Обработка кнопок
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_1:
                setTextNumber(button1);
                break;
            case R.id.button_2:
                setTextNumber(button2);
                break;
            case R.id.button_3:
                setTextNumber(button3);
                break;
            case R.id.button_4:
                setTextNumber(button4);
                break;
            case R.id.button_5:
                setTextNumber(button5);
                break;
            case R.id.button_6:
                setTextNumber(button6);
                break;
            case R.id.button_7:
                setTextNumber(button7);
                break;
            case R.id.button_8:
                setTextNumber(button8);
                break;
            case R.id.button_9:
                setTextNumber(button9);
                break;
            case R.id.button_zero:
                setTextNumber(button0);
                break;
            case R.id.button_sum:
                setButtonSign(buttonSum);
                break;
            case R.id.button_deduction:
                setButtonSign(buttonDeduction);
                break;
            case R.id.button_multiplic:
                setButtonSign(buttonMultiplic);
                break;
            case R.id.button_div:
                setButtonSign(buttonDiv);
                break;
            case R.id.button_ac:
                setButtonAC();
                break;
            case R.id.button_delete:
                setButtonDelete();
                break;
            case R.id.button_equal:
                setButtonEqual();
                break;
            case R.id.button_percent:
                setButtonPercent();
                break;
            case R.id.button_point:
                setButtonPoint();
                break;
            case R.id.button_settings:
                setButtonSettings();
                break;
        }
    }

    //Обработка кнопки цифр
    private void setTextNumber(Button button) {
        calculator.buttonNumber((String) button.getText());
        textEnter.setText(calculator.getNumber());
    }

    //Обработка кнопки очистить всё
    private void setButtonAC() {
        calculator.buttonAC();
        textEnter.setText("");
    }

    //Обработка кнопки удалить элемент
    private void setButtonDelete() {
        calculator.buttonDelete();
        textEnter.setText(calculator.getNumber());
    }

    //Обоаботка кнопок знака
    private void setButtonSign(Button button) {
        calculator.buttonSign((String) button.getText());
        textEnter.setText(calculator.getNumber());
    }

    //Обработка знака .
    private void setButtonPoint() {
        calculator.buttonPoint((String) buttonPoint.getText());
        textEnter.setText(calculator.getNumber());
    }

    //Обработка знака =
    private void setButtonEqual() {
        textEnter.setText("");
        calculator.buttonEquals();
        textEnter.setText(calculator.getNumber());
    }

    //Обработка знака %
    private void setButtonPercent() {
        //textEnter.setText("");
        calculator.buttonPercent();
        textEnter.setText(calculator.getNumber());
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