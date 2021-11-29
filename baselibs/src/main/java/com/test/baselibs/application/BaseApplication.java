package com.test.baselibs.application;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.alibaba.android.arouter.launcher.ARouter;
import com.test.baselibs.BuildConfig;
import com.test.baselibs.constans.MyConstans;
import com.test.baselibs.utils.ScreenUtils;

import java.util.Locale;

import androidx.core.content.ContextCompat;

public class BaseApplication extends Application {
    //默认中文
    public static Locale language;
    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        if (initOOM()) {
//            return;
//        }
        mContext = getApplicationContext();
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
        //修改app语言
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            language = getResources().getConfiguration().getLocales().get(0);
        } else {
            language = getResources().getConfiguration().locale;
        }
        //手机屏幕基础信息
        MyConstans.Screen_Width = ScreenUtils.getScreenWidth(mContext);
        MyConstans.Screen_Height = ScreenUtils.getScreenHeight(mContext);
        MyConstans.Screen_Status_Height = ScreenUtils.getStatusHeight(mContext);

        //下载工具初始化
        initDownloadUtil();

        //DK播放器初始化
//        VideoViewManager.setConfig(VideoViewConfig.newBuilder()
//                //使用使用自定义IjkPlayer解码
//                .setPlayerFactory(MyVideoPlayerFactory.create())
//                .build());
    }

    //下载工具初始化
    private void initDownloadUtil() {
//        File file = VideoStorageUtils.getVideoCacheDir(this);
//        if (!file.exists()) {
//            file.mkdir();
//        }
//        VideoDownloadConfig config = new VideoDownloadManager.Build(this)
//                .setCacheRoot(file.getAbsolutePath())
//                .setTimeOut(DownloadConstants.READ_TIMEOUT, DownloadConstants.CONN_TIMEOUT)
//                .setConcurrentCount(1)//下载总数
//                .setIgnoreCertErrors(false)
//                .setShouldM3U8Merged(false)
//                .buildConfig();
//        VideoDownloadManager.getInstance().initConfig(config);
    }


    /**
     * 初始化监听内存溢出框架
     */
//    private boolean initOOM() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return true;
//        }
//        LeakCanary.install(this);
//        return false;
//    }
    public static String getStringByResId(int strId) {
        return BaseApplication.getContext().getResources().getString(strId);
    }

    public static int getColorByResId(int colorId) {
        return ContextCompat.getColor(BaseApplication.getContext(), colorId);
    }

    public static Drawable getDrawableByResId(int drawableId) {
        return ContextCompat.getDrawable(BaseApplication.getContext(), drawableId);
    }
}
