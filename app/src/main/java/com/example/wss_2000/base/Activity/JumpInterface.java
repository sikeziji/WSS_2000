package com.example.wss_2000.base.Activity;

import android.content.Intent;

/**
 * 项目名称    BaseProject
 * 类描述
 * 创建人      hp
 * 创建时间    2019/10/24
 */
public interface JumpInterface {

    /**
     * 跳转到activity
     * * @param intent
     */
    void startActivity(Intent intent);

    /**
     * 跳转到activity并且返回内容
     * * @param intent
     * * @param requestCode
     */
    void startActivityForResult(Intent intent, int requestCode);

}
