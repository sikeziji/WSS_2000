package com.example.wss_2000.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2020/1/9
 */
public class DBHelper {

    private DBHelper Instance;

    private DaoSession WaterDaoSession;

    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    public DBHelper(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "WaterOnLine.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        /*加密处理*/
        //Database db = helper.getEncryptedWritableDb("1212");
        DaoMaster daoMaster = new DaoMaster(db);
        WaterDaoSession = daoMaster.newSession();
    }

    public DaoSession getWaterDaoSession() {
        return WaterDaoSession;
    }


}
