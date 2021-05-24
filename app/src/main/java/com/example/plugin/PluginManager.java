package com.example.plugin;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

public class PluginManager {

    static String pluginName = "plugin.apk";

    static void init(Activity activity) {
        copy2Local(activity);

        initPluginResources(activity);
        initPluginClassLoader(activity);
    }

    static void copy2Local(Activity activity) {
        File apk = new File(activity.getCacheDir(), pluginName);
        if (apk.exists()) apk.delete();

        try {
            InputStream inputStream = activity.getResources().getAssets().open("dex-debug.apk");
            FileOutputStream outputStream = new FileOutputStream(apk);

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            Log.d("xx", "拷贝完成");
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("xx", "拷贝失败");
        }
    }

    static Resources resources;

    static void initPluginResources(Activity activity) {
        Class<?> assetManagerClass = AssetManager.class;
        try {
            AssetManager assertManagerObj = (AssetManager) assetManagerClass.newInstance();
            Method addAssetPathMethod = assetManagerClass.getMethod("addAssetPath", String.class);
            addAssetPathMethod.setAccessible(true);
            addAssetPathMethod.invoke(assertManagerObj, new File(activity.getCacheDir(), pluginName).getAbsolutePath());
            //在创建一个Resource
            resources = new Resources(assertManagerObj, activity.getResources().getDisplayMetrics(), activity.getResources().getConfiguration());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static Resources getPluginResources() {
        return resources;
    }

    static DexClassLoader classLoader;

    static void initPluginClassLoader(Activity activity){
        File dexOutDir = activity.getDir("dex", activity.MODE_PRIVATE);
        classLoader =  new DexClassLoader(new File(activity.getCacheDir(), pluginName).getAbsolutePath(),
                dexOutDir.getAbsolutePath(), null, activity.getClassLoader());
    }

    static DexClassLoader getPluginClassLoader() {
       return classLoader;
    }

    static PackageInfo getPluginFirstActivity(Activity activity) {
        PackageManager packageManager = activity.getPackageManager();
        return packageManager.getPackageArchiveInfo(new File(activity.getCacheDir(), pluginName).getAbsolutePath(), PackageManager.GET_ACTIVITIES);
    }

}
