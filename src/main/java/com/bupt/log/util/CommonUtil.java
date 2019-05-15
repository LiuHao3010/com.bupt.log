package com.bupt.log.util;

import com.bupt.log.Bean.Log;
import com.bupt.log.Bean.MobileLog;
import com.mongodb.util.JSON;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Repository("commonUtil")
public class CommonUtil {
    //获取日志生成日期
    public static Map<String, Integer> getDate(String target){

        Map<String, Integer> result = new HashMap<String, Integer>();
        String datePattern = "_(\\d{8}).";
        Pattern p = Pattern.compile(datePattern);
        Matcher m = p.matcher(target);

        if(!m.find())
            System.out.println("wrong");
        //获取年月日
        String year_month_day;
        year_month_day = m.group(1);

        result.put("year", Integer.parseInt(year_month_day.substring(0, 4)));
        result.put("month", Integer.parseInt(year_month_day.substring(4, 6)));
        result.put("day", Integer.parseInt(year_month_day.substring(6)));
        return result;
    }
    //从文本中获取web端日志数据，并返回一个日志列表
    public  static List<Log> ReadLogFromFile(String path){
        List<Log> logs=new ArrayList<Log>();
        try{
            Map<String, Integer> date_ymd = CommonUtil.getDate(path);

            Integer year = date_ymd.get("year");
            Integer month = date_ymd.get("month");
            Integer day = date_ymd.get("day");

            //获取日志文本内容
            File file = new File(path);
            if(file.isFile() && file.exists()){
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String lineTxt;

                while((lineTxt = bufferedReader.readLine()) != null){
                    Log log = new Log();
                    String[] s = lineTxt.split(",");
                    if(s.length>4){

                        //设置时间
                        String[] time = s[0].split(":");
                        int hour = Integer.parseInt(time[0]);
                        int minute = Integer.parseInt(time[1]);
                        int second = Integer.parseInt(time[2]);
                        String sd=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d = sdf.parse(sd);
                        log.setDate(d);

                        //设置ip
                        log.setIp(s[1]);

                        //设置uuid
                        log.setUuid(s[3]);

                        StringBuilder userAgent = new StringBuilder(s[4]);
                        for(int i=5;i<s.length;i++){
                            userAgent.append(s[i]);
                        }
                        log.setUserAgent(userAgent.toString());
                    }
                    lineTxt = bufferedReader.readLine();
                    log.setAction(lineTxt);
                    logs.add(log);

                }
                bufferedReader.close();
                reader.close();
            }else{
                System.out.println("找不到指定文件");
            }
        }catch(Exception e){
            System.out.println("读取文件出错");
            e.printStackTrace();
        }
        return logs;
    }
    //从文件夹下所有文件获取web端日志数据，并返回一个日志列表
    public static List<Log> ReadLogFromDirectory(String path){
        File file=new File(path);
        if (!file.isDirectory()){
            return ReadLogFromFile(path);
        }
        else{
            List<Log> logs=new ArrayList<Log>();
            File[] filelist = file.listFiles();
            String pattern = "\\d{8}.txt$";
            Pattern r = Pattern.compile(pattern);
            for (File p:filelist
                 ) {
                Matcher m = r.matcher(p.toString());
                if (m.find()){
                    System.out.println(p.toString());
                    logs.addAll(ReadLogFromFile(p.toString()));
                }
                else
                    continue;
            }
            return logs;
        }
    }
    //从文本中获取mobile端日志，并返回一个日志列表
    public static List<MobileLog> ReadMobileLogFromFile(String path){
        List<MobileLog> mobilelogs=new ArrayList<MobileLog>();
        try{
            Map<String, Integer> date = CommonUtil.getDate(path);

            Integer year = date.get("year");
            Integer month = date.get("month");
            Integer day = date.get("day");

            File file = new File(path);
            if(file.isFile() && file.exists()){

                InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String lineTxt = bufferedReader.readLine();

                while(lineTxt != null ){
                    MobileLog log = new MobileLog();

                    String[] s = lineTxt.split(",");
                    if(s.length > 4){
                        String[] time = s[0].split(":");
                        int hour = Integer.parseInt(time[0]);
                        int minute = Integer.parseInt(time[1]);
                        int second = Integer.parseInt(time[2]);
                        String sd=year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
                        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d = sdf.parse(sd);
                        log.setDate(d);
                        log.setIp(s[1]);

                        StringBuilder userAgent = new StringBuilder(s[4]);
                        for(int i=5;i<s.length;i++){
                            userAgent.append(s[i]);
                        }
                        log.setUserAgent(userAgent.toString());
                    }
                    lineTxt = bufferedReader.readLine();
                    String pattern = "(\\{(.*?)\\})(.*)";
                    Pattern p = Pattern.compile(pattern);
                    Matcher m = p.matcher(lineTxt);
                    if(!m.find()) System.out.println("读取内容失败");
                    Map map = (Map) JSON.parse(m.group(1));
                    log.setInfo(map);
                    log.setRequest(m.group(3));
                    mobilelogs.add(log);

                    while ( ((lineTxt = bufferedReader.readLine()) != null) && (lineTxt.indexOf("{") == 0)){
                    }
                }
            } else {
                System.out.println("文件不存在");
            }

        }catch (Exception e){
            System.out.println("读取文件出错");
            e.printStackTrace();
        }
        return mobilelogs;
    }
    //从文件夹下所有文件获取web端日志数据，并返回一个日志列表
    public static List<MobileLog> ReadMobileLogFromDirectory(String path){
        File file=new File(path);
        if (!file.isDirectory()){
            return ReadMobileLogFromFile(path);
        }
        else{
            List<MobileLog> logs=new ArrayList<MobileLog>();
            File[] filelist = file.listFiles();
            String pattern = "\\d{8}M.txt$";
            Pattern r = Pattern.compile(pattern);
            for (File p:filelist
            ) {
                Matcher m = r.matcher(p.toString());
                if (m.find()){
                    System.out.println(p.toString());
                    logs.addAll(ReadMobileLogFromFile(p.toString()));
                }
                else
                    continue;;
            }
            System.out.println(logs.size());
            return logs;
        }
    }
}
