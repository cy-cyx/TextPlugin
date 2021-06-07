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

#### 二、热修改（ClassLoader）



