package com.bupt.log;

import com.bupt.log.Service.LogService;
import com.bupt.log.Service.MobileLogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

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
		String password = "admin";
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);
		String enPassword = encoder.encode(password);
		System.out.println(enPassword);
	}
}
