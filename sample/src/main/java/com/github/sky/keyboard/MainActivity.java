package com.github.sky.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.sky.securitykeyboard.SecurityKeyboardManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.edit);
        SecurityKeyboardManager.getInstance().initKeyboard(this, "sky安全键盘", editText);
    }
}
