# ARouterComponentizationDemo
项目模块化 + ARouter Demo</br>
主要利用ARouter座位路由工具，连接各个模块和相互通讯，</br>
主要有以下技术要点</br>
        1.Module的Arouter配置</br>
        2.基础类的Arouter配置</br>
        3.Module之间的跳转和传参代码</br>
        4.Module引用baselibs的第三方库</br>
        5.Butterknife的引用使用</br></br>

注意点：Module引用baselibs的第三方库时 ，baselibs引用方法要将 implementation 改成 api 引用
，Module将 implementation 改成 annotationProcessor。</br></br>

使用Butterknife时 **Project** 的 **build** 需要加上Butterknife插件，如下</br>
```
 dependencies {
        classpath 'com.android.tools.build:gradle:x.x.x'
        //Butterknife插件</br>
        classpath 'com.jakewharton:butterknife-gradle-plugin:10.1.0'
    }
 ```
使用Butterknife的**Module** 的 build需要
```
apply plugin: 'com.jakewharton.butterknife'
android {
    defaultConfig {
       ...
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = [AROUTER_MODULE_NAME: project.getName()]
            }
        }
    }
 }
dependencies {
    ...
    annotationProcessor 'com.jakewharton:butterknife-compiler:10.1.0'
}
```

