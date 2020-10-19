package com.example.wss_2000.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.dao
 * @ClassName: HistoryDB
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/19 18:31
 * @Version: 1.0
 */
@Entity
public class HistoryDB {
    @Id(autoincrement = true)
    Long id;
    @Property(nameInDb = "component")
    String component;
    @Property(nameInDb = "time")
    String time;
    @Property(nameInDb = "value")
    String value;
    @Property(nameInDb = "unit")
    String unit;
    @Property(nameInDb = "flow")
    String flow;
    @Property(nameInDb = "A")
    String A;
    @Property(nameInDb = "temperature")
    String temperature;
    @Property(nameInDb = "energy")
    String energy;
    @Property(nameInDb = "tag")
    String tag;
    @Generated(hash = 347682062)
    public HistoryDB(Long id, String component, String time, String value,
            String unit, String flow, String A, String temperature, String energy,
            String tag) {
        this.id = id;
        this.component = component;
        this.time = time;
        this.value = value;
        this.unit = unit;
        this.flow = flow;
        this.A = A;
        this.temperature = temperature;
        this.energy = energy;
        this.tag = tag;
    }
    @Generated(hash = 1964680625)
    public HistoryDB() {
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
    public String getValue() {
        return this.value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getUnit() {
        return this.unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getFlow() {
        return this.flow;
    }
    public void setFlow(String flow) {
        this.flow = flow;
    }
    public String getA() {
        return this.A;
    }
    public void setA(String A) {
        this.A = A;
    }
    public String getTemperature() {
        return this.temperature;
    }
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    public String getEnergy() {
        return this.energy;
    }
    public void setEnergy(String energy) {
        this.energy = energy;
    }
    public String getTag() {
        return this.tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }

}
