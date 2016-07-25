package com.dante.knowledge;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.bugtags.library.Bugtags;
import com.dante.knowledge.mime.utils.CrashHandler;
import com.github.moduth.blockcanary.BlockCanary;
import com.github.moduth.blockcanary.BlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import io.realm.Realm;
import io.realm.RealmConfiguration;

import static android.os.Build.VERSION.SDK_INT;
import static android.os.Build.VERSION_CODES.GINGERBREAD;

/**
 * Init LeakCanary, Utils.
 */
public class KnowledgeApp extends Application {

    private RefWatcher refWatcher;
    public static Context context;
    private static KnowledgeApp sApplication;
    public static String cacheDir = "";

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        //初始化日志输出工具
        CrashHandler.init(new CrashHandler(getApplicationContext()));
        refWatcher = LeakCanary.install(this);

        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        setupRealm();
        Bugtags.start("17483b5ba7f71cf806b0707f3b1542c1", this, Bugtags.BTGInvocationEventShake);

        /**
         * 如果存在SD卡则将缓存写入SD卡,否则写入手机内存
         */

        if (getApplicationContext().getExternalCacheDir() != null && isExistSDCard()) {
            cacheDir = getApplicationContext().getExternalCacheDir().toString();
        }
        else {
            cacheDir = getApplicationContext().getCacheDir().toString();
        }
    }


    private void setupRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public static RefWatcher getWatcher(Context context) {
        sApplication = (KnowledgeApp) context.getApplicationContext();
        return sApplication.refWatcher;
    }

    public class AppBlockCanaryContext extends BlockCanaryContext {
        // override to provide context like app qualifier, uid, network type, block threshold, log save path
        // this is default block threshold, you can set it by phone's performance
        @Override
        public int getConfigBlockThreshold() {
            return 500;
        }

        // if set true, notification will be shown, else only write log file
        @Override
        public boolean isNeedDisplay() {
            return BuildConfig.DEBUG;
        }

        // path to save log file
        @Override
        public String getLogPath() {
            return "/blockcanary/performance";
        }
    }

    private boolean isExistSDCard() {
        if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
            return true;
        }
        else {
            return false;
        }
    }

    private void enabledStrictMode() {
        if (SDK_INT >= GINGERBREAD) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                    .detectAll()  //
                    .penaltyLog() //
                    .penaltyDeath() //
                    .build());
        }
    }

    public static Context getContext() {
        return context;
    }

}
