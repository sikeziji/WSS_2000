package com.example.wss_2000.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.dao
 * @ClassName: ComponentsDB
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/19 18:38
 * @Version: 1.0
 */
@Entity
class ComponentsDB {
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    Long Id;
    @Property(nameInDb = "name")
    String name;
    @Property(nameInDb = "value")
    String value;
    @Generated(hash = 2042768860)
    public ComponentsDB(Long Id, String name, String value) {
        this.Id = Id;
        this.name = name;
        this.value = value;
    }
    @Generated(hash = 1283002856)
    public ComponentsDB() {
    }
    public Long getId() {
        return this.Id;
    }
    public void setId(Long Id) {
        this.Id = Id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }

}
