package com.example.wss_2000.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.dao
 * @ClassName: KBFDB
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/19 18:33
 * @Version: 1.0
 */
@Entity
public class KBFDB {
    @Id(autoincrement = true)
    Long id;
    @Property(nameInDb = "component")
    String component;
    @Property(nameInDb = "time")
    String time;
    @Property(nameInDb = "k")
    String k;
    @Property(nameInDb = "b")
    String b;
    @Property(nameInDb = "f")
    String f;
    @Generated(hash = 1984516734)
    public KBFDB(Long id, String component, String time, String k, String b,
            String f) {
        this.id = id;
        this.component = component;
        this.time = time;
        this.k = k;
        this.b = b;
        this.f = f;
    }
    @Generated(hash = 1690746161)
    public KBFDB() {
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
    public String getK() {
        return this.k;
    }
    public void setK(String k) {
        this.k = k;
    }
    public String getB() {
        return this.b;
    }
    public void setB(String b) {
        this.b = b;
    }
    public String getF() {
        return this.f;
    }
    public void setF(String f) {
        this.f = f;
    }
}
