package com.github.sky.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.sky.keyboardlib.SecurityKeyboardManager;
import com.github.sky.keyboardlib.SecurityKeyboardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.edit);
        SecurityKeyboardView keyBoardView = (SecurityKeyboardView) findViewById(R.id.skeyboard);
        SecurityKeyboardManager.getInstance().initKeyboard(this, keyBoardView, editText);
    }
}
