package com.bupt.log.Bean;

import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.Map;

public class MobileLog {
    @Id
    private String id;
    private String ip;
    private Date date;
    private String userAgent;
    private String request;
    private Map<String, String> info;

    public MobileLog(String ip, Date date, String userAgent, String request) {
        this.ip = ip;
        this.date = date;
        this.userAgent = userAgent;
        this.request = request;
    }

    public MobileLog() {
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    @Override
    public String toString(){
        return "ip: " + this.ip +
                " time: " + this.date +
                " userAgent: " + this.userAgent +
                " request: " + this.request +
                "\ninfo: " + this.info.toString()+"\n";
    }
}
