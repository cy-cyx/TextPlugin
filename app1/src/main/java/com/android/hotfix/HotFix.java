package com.android.hotfix;

import android.app.Activity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.DexClassLoader;

/**
 * create by caiyx in 2021/6/4
 * <p>
 * 热修复工具
 */
public class HotFix {

    static String dexName = "fixdex.dex";

    static void init(Activity activity) {
        copy2Local(activity);
    }

    private static void copy2Local(Activity activity) {
        File dex = new File(activity.getCacheDir(), dexName);
        if (dex.exists()) dex.delete();

        try {
            InputStream inputStream = activity.getResources().getAssets().open("fixdex.dex");
            FileOutputStream outputStream = new FileOutputStream(dex);

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

    /**
     * 缺少很多版本的适配,只是验证了方法
     */
    static void fix(Activity activity) {
        ClassLoader loader = activity.getClassLoader();

        try {
            Field pathListField = null;
            pathListField = findField(loader, "pathList");
            Object dexPathList = pathListField.get(loader);
            expandFieldArray(dexPathList, "dexElements", makeDexElements(activity));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void expandFieldArray(Object instance, String fieldName,
                                         Object[] extraElements) throws NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field jlrField = findField(instance, fieldName);
        Object[] original = (Object[]) jlrField.get(instance);

        Object[] combined = (Object[]) Array.newInstance(original.getClass().getComponentType(), original.length + extraElements.length);
        System.arraycopy(extraElements, 0, combined, 0, extraElements.length);
        System.arraycopy(original, 0, combined, extraElements.length, original.length);
        jlrField.set(instance, combined);
    }

    private static Field findField(Object instance, String name) throws NoSuchFieldException {
        for (Class<?> clazz = instance.getClass(); clazz != null; clazz = clazz.getSuperclass()) {
            try {
                Field field = clazz.getDeclaredField(name);


                if (!field.isAccessible()) {
                    field.setAccessible(true);
                }

                return field;
            } catch (NoSuchFieldException e) {
                // ignore and search next
            }
        }

        throw new NoSuchFieldException("Field " + name + " not found in " + instance.getClass());
    }

    private static Object[] makeDexElements(Activity activity)
            throws IllegalAccessException, NoSuchFieldException {

        File dexOutDir = activity.getDir("dex", activity.MODE_PRIVATE);
        ClassLoader classLoader = new DexClassLoader(new File(activity.getCacheDir(), dexName).getAbsolutePath(),
                dexOutDir.getAbsolutePath(), null, activity.getClassLoader());

        Field pathListField = findField(classLoader, "pathList");
        Object dexPathList = pathListField.get(classLoader);

        Field jlrField = findField(dexPathList, "dexElements");
        Object[] dexElements = (Object[]) jlrField.get(dexPathList);

        return dexElements;
    }
}
