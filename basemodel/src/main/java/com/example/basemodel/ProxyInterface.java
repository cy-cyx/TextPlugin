package com.example.basemodel;

import android.app.Activity;
import android.os.Bundle;

public interface ProxyInterface {

    public void attach(Activity proxyActivity);

    public void onCreate(Bundle savedInstanceState);

    public void onStart();

    public void onResume();

    public void onPause();

    public void onStop();

    public void onDestroy();

    public void onSaveInstanceState(Bundle outState);
}
