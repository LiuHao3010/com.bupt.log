package com.bupt.log.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    @RequestMapping("/savelog")
    public String save(){
        com.bupt.log.Service.LogService logService=new com.bupt.log.Service.LogService();
        logService.insertAll("C:\\Users\\LH\\Desktop\\Other\\com.bupt.log\\src\\main\\resources\\static\\2_20181207.txt");
        return "save";
    }
}
