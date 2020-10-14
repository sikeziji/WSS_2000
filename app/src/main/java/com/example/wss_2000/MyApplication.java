package com.example.wss_2000;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.example.wss_2000.dualprocessdaemon.DualProcessDaemon;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.greendao.annotation.NotNull;

import me.yokeyword.fragmentation.Fragmentation;
import me.yokeyword.fragmentation.helper.ExceptionHandler;


/**
 * 项目名称    WSS_2000
 * 类描述
 * 创建人      wj
 * 创建时间    2020年10月14日
 * <p>
 * 支持一次崩溃捕捉，自重启，双进程守护
 */
public class MyApplication extends Application {

    private RefWatcher mRefWatcher = null;
    private static Application application = null;
    private static Context context = null;


    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.mRefWatcher;
    }


    public static Application getMyApplication() {
        return application;
    }

    public static Context getMyAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        application = this;

        /*
        TODO 注释 泄漏组件
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            IMMLeaks.fixFocusedViewLeak(this);
        }
        */

        try {
            // 内存泄漏检测
            mRefWatcher = setupLeakCanary();
            // 进程守护
            DualProcessDaemon.start(context);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Fragmentation.builder()
                // 设置 栈视图 模式为 （默认）悬浮球模式   SHAKE: 摇一摇唤出  NONE：隐藏， 仅在Debug环境生效
                .stackViewMode(Fragmentation.BUBBLE)
                .debug(true) // 实际场景建议.debug(BuildConfig.DEBUG)
                /*
                 * 可以获取到{@link me.yokeyword.fragmentation.exception.AfterSaveStateTransactionWarning}
                 * 在遇到After onSaveInstanceState时，不会抛出异常，会回调到下面的ExceptionHandler
                 */
                .handleException(new ExceptionHandler() {
                    @Override
                    public void onException(@NotNull Exception e) {
                        // 以Bugtags为例子: 把捕获到的 Exception 传到 Bugtags 后台。
//                         Bugtags.sendException(e);
                    }
                })
                .install();
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }
}
