package com.android.hotfix;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HotFix.init(this);
    }

    public void onClick(View view) {
        startActivity(new Intent(this, TextActivity.class));
    }

    public void onFix(View view) {
        HotFix.fix(this);
    }
}
