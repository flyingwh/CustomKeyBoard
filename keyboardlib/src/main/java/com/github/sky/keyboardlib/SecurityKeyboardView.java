package com.github.sky.keyboardlib;

import android.content.Context;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by fuyuxian on 2017/1/18.
 */

public class SecurityKeyboardView extends LinearLayout {

    private KeyboardView mKeyboardView;
    private View mCloseView; //完成按钮

    public SecurityKeyboardView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecurityKeyboardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.input, this);
        mKeyboardView = (KeyboardView) findViewById(R.id.keyboard);
        mCloseView = findViewById(R.id.keyboard_view_finish);

    }

    public KeyboardView getKeyboardView() {
        return mKeyboardView;
    }

    public View getCloseView() {
        return mCloseView;
    }


}
