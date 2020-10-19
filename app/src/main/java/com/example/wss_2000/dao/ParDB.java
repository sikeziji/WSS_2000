package com.example.wss_2000.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.dao
 * @ClassName: ParDB
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/19 18:36
 * @Version: 1.0
 */
@Entity
class ParDB {
    @Id(autoincrement = true)
    Long Id;
    @Property(nameInDb = "name")
    String name;
    @Property(nameInDb = "value")
    String value;
    @Property(nameInDb = "component")
    String component;
    @Generated(hash = 1394069782)
    public ParDB(Long Id, String name, String value, String component) {
        this.Id = Id;
        this.name = name;
        this.value = value;
        this.component = component;
    }
    @Generated(hash = 1083749303)
    public ParDB() {
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
    public String getComponent() {
        return this.component;
    }
    public void setComponent(String component) {
        this.component = component;
    }
}
