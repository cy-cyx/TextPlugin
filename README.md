#### 一、插件化（占坑）

``相关类：``

**主体工程：** *TextPlugin/app*

|类|作用|
|---|---|
|ProxyActivity| 坑位 |
|PluginManager|加载插件化的工具类|

总结：使用了独立AssetsManager加载apk中的资源，生成独立的Recourse，防止资源id冲突。通过
DexClassLoader加载apk中的class。

**插件工程的统一接口：** *TextPlugin/basemodel*

**插件工程：** *TextPlugin/dex*

#### 二、热修改（利用ClassLoader）

**主体工程：** *TextPlugin/app1*

**DEX修复工程：** *TextPlugin/dex1*

热修复的类：*com/android/hotfix/ThrowBug.java*

总结：ClassLoader中pathList是一个数组，热修复就是将修复的DexFile，塞到数组前面，到加载时，
优先加载修复的dex，实现不发包就可以修复崩溃


