package com.example.wss_2000.util.CrashReStart;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


import com.example.wss_2000.MyApplication;
import com.example.wss_2000.base.Activity.ActivityCollector;
import com.example.wss_2000.util.PackageUtils;
import com.example.wss_2000.util.SDCardUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/11/4
 */
public class UnCeHandler implements Thread.UncaughtExceptionHandler {


    private static final String TAG = "UnCeHandler";

    //系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private MyApplication application;
    private Class<?> startClass;
    //用来存储设备信息和异常信息
    private Map<String, String> infos = new HashMap<>();

    //用于格式化日期,作为日志文件名的一部分
    @SuppressLint("SimpleDateFormat")
    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

    /**
     * 保证只有一个CrashHandler实例
     */
    public UnCeHandler(MyApplication application, Class<?> cls) {
        init();
        this.application = application;
        this.startClass = cls;
    }

    /**
     * 初始化
     */
    public void init() {
        //获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该函数来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            //如果用户没有处理则让系统默认的异常处理器来处理
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Log.e(TAG, "error : ", e);
            }

            ex.printStackTrace();

//            exceptionStartActivity(startClass);

            ActivityCollector.finishActivity();
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex
     * @return true:如果处理了该异常信息;否则返回false.
     */
    private boolean handleException(Throwable ex) {
        if (ex == null) {
            return false;
        }

        //使用Toast来显示异常信息
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(application.getApplicationContext(), "I need to reboot, please wait!", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        collectDeviceInfo(application.getApplicationContext());
        //保存日志文件
        saveCrashInfo2File(ex);

        Log.i("getGlobalPath()", getGlobalPath());
        return true;
    }

    /**
     * 收集设备参数信息
     *
     * @param ctx
     */
    private void collectDeviceInfo(Context ctx) {
        try {
            PackageManager pm = ctx.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(), PackageManager.GET_ACTIVITIES);
            if (pi != null) {
                String versionName = pi.versionName == null ? "null" : pi.versionName;
                String versionCode = pi.versionCode + "";
                infos.put("versionName", versionName);
                infos.put("versionCode", versionCode);
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "an error occured when collect package info", e);
        }
        Field[] fields = Build.class.getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                infos.put(field.getName(), field.get(null).toString());
                Log.d(TAG, field.getName() + " : " + field.get(null));
            } catch (Exception e) {
                Log.e(TAG, "an error occured when collect crash info", e);
            }
        }
    }

    /**
     * 保存错误信息到文件中
     *
     * @param ex
     * @return 返回文件名称, 便于将文件传送到服务器
     */
    private String saveCrashInfo2File(Throwable ex) {
        try {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : infos.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                sb.append(key).append("=").append(value).append("\n");
            }

            Writer writer = new StringWriter();
            PrintWriter printWriter = new PrintWriter(writer);
            ex.printStackTrace(printWriter);
            Throwable cause = ex.getCause();
            while (cause != null) {
                cause.printStackTrace(printWriter);
                cause = cause.getCause();
            }
            printWriter.close();
            String result = writer.toString();
            sb.append(result);
            try {
                long timestamp = System.currentTimeMillis();
                String time = formatter.format(new Date());
                String fileName = "crash-" + time + "-" + timestamp + ".log";
                boolean f = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
                System.out.println("KKKKK:" + f);
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    File dir = new File(getGlobalPath());
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    FileOutputStream fos = new FileOutputStream(getGlobalPath() + fileName);
                    fos.write(sb.toString().getBytes());
                    fos.close();
                }
                return fileName;
            } catch (Exception e) {
                Log.e(TAG, "an error occured while writing file...", e);
            }
        } catch (Exception e) {
            Log.e(TAG, "saveCrashInfo2File", e);
        }
        return null;
    }


    //获取文件存储路径
    private static String getGlobalPath() {

        return SDCardUtils.getSDCardPath() + "/" + PackageUtils.getAppName(MyApplication.getMyAppContext()) + "/" + "crash" + File.separator;
    }


    // 启动程序
    private void exceptionStartActivity(Class<?> cls) {
        Intent intent = new Intent(application.getApplicationContext(), cls);
        PendingIntent restartIntent = PendingIntent.getActivity(application.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager mgr = (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000,
                restartIntent); // 1秒钟后重启应用
    }
}
