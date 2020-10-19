package com.example.wss_2000.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.dao
 * @ClassName: ErrorDB
 * @Description: 记录错误的数据库
 * @Author: wangj
 * @CreateDate: 2020/10/19 17:43
 * @Version: 1.0
 */
@Entity
public class ErrorDB {

    /**
     * 数据库id 自增
     */
    @Id(autoincrement = true)
    Long id;
    /**
     * 组件
     */
    @Property(nameInDb = "component")
    String component;

    /**
     * 时间
     */
    @Property(nameInDb = "time")
    String time;
    /**
     *
     */
    @Property(nameInDb = "num")
    String num;
    /**
     * 详情
     */
    @Property(nameInDb = "info")
    String info;
    /**
     * 类型
     */
    @Property(nameInDb = "type")
    String type;
    @Generated(hash = 688257956)
    public ErrorDB(Long id, String component, String time, String num, String info,
            String type) {
        this.id = id;
        this.component = component;
        this.time = time;
        this.num = num;
        this.info = info;
        this.type = type;
    }
    @Generated(hash = 1059001415)
    public ErrorDB() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getComponent() {
        return this.component;
    }
    public void setComponent(String component) {
        this.component = component;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getNum() {
        return this.num;
    }
    public void setNum(String num) {
        this.num = num;
    }
    public String getInfo() {
        return this.info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }


}
