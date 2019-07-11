package com.bupt.log.Service;


import com.bupt.log.Bean.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService  {

    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("用户名为" );
        // TODO 根据用户名，查找到对应的密码，与权限

        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        User user = new User(username, "123456", "role");
        return user;
    }
}
