package com.example.plugin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PluginManager.init(this);
    }

    public void gotoPlugin(View view) {
        PackageInfo packageInfo = PluginManager.getPluginFirstActivity(this);
        String className = packageInfo.activities[0].name;
        Intent intent = new Intent(this, ProxyActivity.class);
        intent.putExtra("new_activity", className);
        startActivity(intent);
    }
}