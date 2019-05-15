package com.bupt.log.Bean;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Log {
    @Id
    private String id;
    private String ip;
    private String uuid;
    private String userAgent;
    private String action;
    private Date date;

    public String getSchoolid() {
        return schoolid;
    }

    public void setSchoolid(String schoolid) {
        this.schoolid = schoolid;
    }

    private String schoolid;
    public void setId(String id) {
        this.id = id;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public Log(){

    }
    public Log(String ip, String uuid, String userAgent, String action, Date date) {
        this.ip = ip;
        this.uuid = uuid;
        this.userAgent = userAgent;
        this.action = action;
        this.date = date;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIp() {
        return ip;
    }

    public String getAction() {
        return action;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getUuid() {
        return uuid;
    }

    public Date getDate() {
        return this.date;
    }


    @Override
    public String toString() {
        return "date: " +""+ this.date + " ip: " + this.ip + " uuid: " + this.uuid + " userAgent: " + this.userAgent + " action: " + this.action;
    }
}
