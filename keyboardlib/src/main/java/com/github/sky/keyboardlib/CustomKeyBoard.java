package com.github.sky.keyboardlib;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.BaseInputConnection;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by fuyuxian on 2017/1/18.
 */

public class CustomKeyBoard implements KeyboardView.OnKeyboardActionListener {

    private static final String TAG = "CustomKeyBoard";
    private Context mContext;
    private CustomKeyBoardView mKeyBoardView;
    private EditText mEditText;
    private boolean mIsUpper = false;
    private Keyboard mKeyBoard;

    public CustomKeyBoard(Context mContext, CustomKeyBoardView keyBoardView) {
        this.mContext = mContext;
        this.mKeyBoardView = keyBoardView;
        mKeyBoardView.setVisibility(View.GONE);
    }

    public void init() {
        initAbcKeyBoard();
        mKeyBoardView.setOnKeyboardActionListener(this);
    }

    public void setEditText(EditText editText) {
        this.mEditText = editText;
        disableSystemKeyBoard();
        processEditTextFocusEvent();
    }

    private void disableSystemKeyBoard() {
        mEditText.setInputType(InputType.TYPE_NULL);
    }

    private void processEditTextFocusEvent() {
        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    mKeyBoardView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int code, int[] ints) {

        switch (code) {
            case 123123:
                initNumberKeyBoard();
                break;
            case 741741:
            case 456456:
                initAbcKeyBoard();
                break;
            case 789789:
                initSymbolKeyBoard();
                break;
            case Keyboard.KEYCODE_SHIFT:
                initShiftAbc();
                break;
            case Keyboard.KEYCODE_DELETE:
                deleteInput();
                break;
            default:
                processInput(code);
                Log.d(TAG, "onKey: " + code + " : " + ((char) code));

        }
    }

    private void processInput(int code) {
        mEditText.getText().append((char) code);
    }

    private void processInput(CharSequence input) {
        mEditText.getText().append(input);
    }

    private void deleteInput() {
        BaseInputConnection inputConnection = new BaseInputConnection(mEditText, true);
        inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
    }

    private void initAbcKeyBoard() {
        if (mIsUpper) {
            initUppercaseAbc();
        } else {
            initLowercaseAbc();
        }
        shuffleAbc();
        mKeyBoardView.setKeyboard(mKeyBoard);
    }

    private void initLowercaseAbc() {
        mKeyBoard = new Keyboard(mContext, R.xml.symbols_abc);
    }

    private void initUppercaseAbc() {
        mKeyBoard = new Keyboard(mContext, R.xml.symbols_abc);

        Pattern pattern = Pattern.compile("^[a-z]$");
        for (Keyboard.Key key : mKeyBoard.getKeys()) {
            if (key == null || key.label == null) continue;
            if (pattern.matcher(key.label).find()) {

                key.label = key.label.toString().toUpperCase();
                key.codes[0] = key.codes[0] - 32;
            }
        }
    }

    private void initNumberKeyBoard() {
        mKeyBoard = new Keyboard(mContext, R.xml.symbols_num_abc);
        shuffleNumber();
        mKeyBoardView.setKeyboard(mKeyBoard);
    }

    private void initSymbolKeyBoard() {
        mKeyBoard = new Keyboard(mContext, R.xml.symbols_symbol);
        mKeyBoardView.setKeyboard(mKeyBoard);
    }

    private void initShiftAbc() {
        mIsUpper = !mIsUpper;
        initAbcKeyBoard();
    }

    private void shuffleAbc() {
        List<Character> set = new ArrayList();
        for (int i = 0; i < 26; i++) {
            set.add((char) (97 + i));
        }

        Random random = new Random(System.currentTimeMillis());

        Pattern pattern = Pattern.compile("^[a-z]$");
        for (Keyboard.Key key : mKeyBoard.getKeys()) {
            if (key == null || key.label == null) continue;
            if (pattern.matcher(key.label).find()) {
                char lable = set.remove(random.nextInt(set.size()));

                key.label = lable + "";
                key.codes[0] = lable;
            }
        }
    }

    private void shuffleNumber() {
        List<Integer> set = new ArrayList();
        for (int i = 0; i < 10; i++) {
            set.add(i);
        }

        Random random = new Random(System.currentTimeMillis());

        Pattern pattern = Pattern.compile("^[0-9]$");
        for (Keyboard.Key key : mKeyBoard.getKeys()) {
            if (key == null || key.label == null) continue;
            if (pattern.matcher(key.label).find()) {
                int num = set.remove(random.nextInt(set.size()));
                key.label = num + "";
                key.codes[0] = num + 48;
            }
        }
    }

    @Override
    public void onText(CharSequence charSequence) {
        processInput(charSequence);
        Log.d(TAG, "onText: " + charSequence);
    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
