package com.android.hotfix;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * create by caiyx in 2021/6/7
 */
public class TextActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 测试这个类是不是修复后的
        Toast.makeText(this, new ThrowBug().execute(), Toast.LENGTH_SHORT).show();
    }
}
