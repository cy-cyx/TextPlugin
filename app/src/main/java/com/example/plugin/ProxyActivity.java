package com.example.plugin;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.basemodel.ProxyInterface;

public class ProxyActivity extends Activity {

    ProxyInterface pluginActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String newActivity = getIntent().getStringExtra("new_activity");
        // 建立插件实例
        try {
            Class<?> aClass = getClassLoader().loadClass(newActivity);
            pluginActivity = (ProxyInterface) aClass.newInstance();
            pluginActivity.attach(this);
            pluginActivity.onCreate(savedInstanceState);
        } catch (Exception e) {
            // nodo
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (pluginActivity != null) pluginActivity.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (pluginActivity != null) pluginActivity.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (pluginActivity != null) pluginActivity.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (pluginActivity != null) pluginActivity.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pluginActivity != null) pluginActivity.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (pluginActivity != null) pluginActivity.onSaveInstanceState(outState);
    }

    @Override
    public Resources getResources() {
        return PluginManager.getPluginResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getPluginClassLoader();
    }
}
