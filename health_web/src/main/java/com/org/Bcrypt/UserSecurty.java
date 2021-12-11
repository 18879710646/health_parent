package com.org.Bcrypt;

import com.org.PoJo.Role;
import com.org.PoJo.User;
import com.org.Service.SecurtyService;
import com.org.Service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author HP
 * @Date 2021/12/8 09:46
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public class UserSecurty implements UserDetailsService {
    @Autowired
    private SecurtyService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 获取到数据库中的用户数据
        User userdata = service.getUsername(username);
        // 查询到所有的权限
       List<Role> roleList= service.findRole();
        if (null != userdata) {
            String password = userdata.getPassword();

            List<GrantedAuthority> arrayList = new ArrayList<>();
            for (Role role : roleList) {
                arrayList.add(new SimpleGrantedAuthority(role.getName()));
                org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(username, password, arrayList);
                return user;
            }

        }
        return null;
    }
}
