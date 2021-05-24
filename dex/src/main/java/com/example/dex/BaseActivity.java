package com.example.dex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.basemodel.ProxyInterface;

public class BaseActivity extends Activity implements ProxyInterface {


    private Activity proxy;

    public Context getContext() {
        if (proxy != null) {
            return proxy;
        }
        return this;
    }

    @Override
    public void attach(Activity proxyActivity) {
        this.proxy = proxyActivity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (proxy == null) {
            super.onCreate(savedInstanceState);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @Override
    public void setContentView(int layoutResID) {
        if (proxy == null) {
            super.setContentView(layoutResID);
        } else {
            proxy.setContentView(layoutResID);
        }

    }

    @Override
    public void onStart() {
        if (proxy == null) {
            super.onStart();
        }
    }

    @Override
    public void onResume() {
        if (proxy == null) {
            super.onResume();
        }
    }

    @Override
    public void onPause() {
        if (proxy == null) {
            super.onPause();
        }
    }

    @Override
    public void onStop() {
        if (proxy == null) {
            super.onStop();
        }
    }

    @Override
    public void onDestroy() {
        if (proxy == null) {
            super.onDestroy();
        }
    }

    @Override
    public Intent getIntent() {
        if (proxy != null) {
            return proxy.getIntent();
        }
        return super.getIntent();
    }

    @Override
    public Window getWindow() {
        if (proxy != null) {
            return proxy.getWindow();
        }
        return super.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        if (proxy != null) {
            return proxy.getWindowManager();
        }
        return super.getWindowManager();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (proxy != null) {
            return proxy.getClassLoader();
        }
        return super.getClassLoader();
    }

    @Override
    public Resources getResources() {
        if (proxy != null) {
            return proxy.getResources();
        }
        return super.getResources();
    }
}
