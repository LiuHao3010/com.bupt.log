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
	public int contextLoads() throws Exception {
	//logService.insertAll("C:\\Users\\LH\\Desktop\\Other\\201812");
		//mobilelogService.insertAll("C:\\Users\\LH\\Desktop\\Other\\201812");
		int min=Integer.MAX_VALUE;
		int tmpn=0;
		int a=0;
		Scanner s=new Scanner(System.in);
		String ss=s.nextLine();
		String[] sss=ss.split(" ");
		int m=Integer.parseInt(sss[0]);
		int n=Integer.parseInt(sss[1]);
		ss=s.nextLine();
		String[] nums=ss.split(" ");
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		List<Integer> list=new ArrayList<Integer>();
		for(int i=0;i<m;i++){
			int tmp= Integer.parseInt(nums[i]);
			if(map.keySet().contains(tmp))
				map.put(tmp,map.get(tmp)+1);
			else{
				map.put(tmp,1);
				list.add(tmp);
			}
		}
		Collections.sort(list);
		int size=list.size();
		for (int i=size-1;i>0;i++){
			int tmpi=i;
			int tmph=map.get(list.get(i));
			if (tmph>=n)
				return 0;
			else {
				while(tmph<n){
					tmpi-=1;
					tmph+=map.get(tmpi);
					if(tmph<n)
						tmpn+=(list.get(i)-list.get(tmpi))*map.get(tmpi);
					else
						tmpn+=(list.get(i)-list.get(tmpi))*(n-tmph);
				}
			}
			min=min<tmpn?min:tmph;
		}
		return min;
	}
}
