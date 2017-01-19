package com.github.sky.keyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.github.sky.keyboardlib.CustomKeyBoard;
import com.github.sky.keyboardlib.CustomKeyBoardView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.edit);
        CustomKeyBoardView keyBoardView = (CustomKeyBoardView) findViewById(R.id.keyboard);
        CustomKeyBoard keyBoard = new CustomKeyBoard(this, keyBoardView);
        keyBoard.init();
        keyBoard.setEditText(editText);
    }
}
