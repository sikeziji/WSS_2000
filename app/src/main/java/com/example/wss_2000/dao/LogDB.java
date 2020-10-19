package com.example.wss_2000.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @ProjectName: WSS_2000
 * @Package: com.example.wss_2000.dao
 * @ClassName: LogDB
 * @Description: java类作用描述
 * @Author: wangj
 * @CreateDate: 2020/10/19 18:34
 * @Version: 1.0
 */
@Entity
public class LogDB {
    @Id(autoincrement = true)
    Long id;
    @Property(nameInDb = "component")
    String component;
    @Property(nameInDb = "time")
    String time;
    @Property(nameInDb = "content")
    String content;
    @Property(nameInDb = "type")
    String type;
    @Generated(hash = 1415245496)
    public LogDB(Long id, String component, String time, String content,
            String type) {
        this.id = id;
        this.component = component;
        this.time = time;
        this.content = content;
        this.type = type;
    }
    @Generated(hash = 668991307)
    public LogDB() {
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
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
