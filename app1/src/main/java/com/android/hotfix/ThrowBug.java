package com.android.hotfix;

import android.util.Log;

/**
 * create by caiyx in 2021/6/4
 */
public class ThrowBug {

    static {
        Log.d("xx", "修复前的类");
    }

    public String execute() {
        throw new RuntimeException("奔溃");
    }
}
