package com.bupt.log;

import com.bupt.log.Bean.MobileLog;
import com.bupt.log.Service.LogService;
import com.bupt.log.Service.MobileLogService;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.mongodb.client.model.Filters.eq;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
//	@Resource
//	MongoTemplate mongoTemplate;
	@Autowired
	public MobileLogService mobilelogService;
	@Autowired
	public LogService logService;
	@Test
	public void contextLoads() throws Exception {
	//logService.insertAll("C:\\Users\\LH\\Desktop\\Other\\201812");
		//mobilelogService.insertAll("C:\\Users\\LH\\Desktop\\Other\\201812");

	}
	@Test
	public void tese(){
		String[] strings=new String[5];
		Query query=new Query();
		String isIP = "\\d+\\.\\d+\\.\\d+\\.\\d+";
		String isUuid="\\d{}";
		Pattern pforIP = Pattern.compile(isIP);
		Matcher m = pforIP.matcher("192.168.0.1");
		System.out.println(m.find());
		for (String string:strings){
			if (m.find())
				query.addCriteria(Criteria.where("ip").is(string));
			else if ()
		}
	}
}
