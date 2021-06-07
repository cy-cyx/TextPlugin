package com.android.hotfix;

import android.util.Log;

/**
 * create by caiyx in 2021/6/4
 */
public class ThrowBug {

    static {
        Log.d("xx", "修复后的类");
    }

    public String execute() {
        return "success";
    }
}
