package com.bupt.log.util;

import com.bupt.log.Bean.User;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.annotation.Resource;

public class Login {
    @Resource
    MongoTemplate mongoTemplate;
    private String LoginChcek(String username,String password){
        Query query=new Query(Criteria.where("username").is(username));
        User user=mongoTemplate.findOne(query, User.class);
        if (user==null)
            return "0";
        if (user.getPassword()==password)
            return "1";
        else return "2";
    }
}
