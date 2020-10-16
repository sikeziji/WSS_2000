package com.example.wss_2000.util.UpdateApp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.io.File;

public class UpdateManager {
    private Context mContext;
    private String filePath;
    private String fileName;


    public UpdateManager(Context context, String path, String fileName) {
        this.mContext = context;
        this.filePath = path;
        this.fileName = fileName;
    }

    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 1) {
                installAPK();
            }
            return false;
        }
    });

    private boolean installAPK() {
        if (fileName == null || filePath == null) {
            return false;
        }
        File localFile = new File(filePath + fileName);
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setDataAndType(Uri.fromFile(localFile), "application/vnd.android.package-archive");
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(localIntent);
        return true;
    }

    public void checkUpdateInfo() {
        Message localMessage = new Message();
        localMessage.what = 1;
        mHandler.sendMessage(localMessage);
    }
}
