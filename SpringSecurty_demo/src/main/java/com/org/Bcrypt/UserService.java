package com.org.Bcrypt;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author HP
 * @Date 2021/12/5 11:58
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public class UserService  implements UserDetailsService {

    //模拟数据库中的用户数据
    public static Map<String, com.org.PoJo.User> map = new HashMap<String, com.org.PoJo.User>();
    static {
        com.org.PoJo.User user1 = new com.org.PoJo.User();
        user1.setUsername("admin");
        user1.setPassword("$2a$10$eA8VdJ6o4PkCq6i75OPhOe8UnwT1t5lmcp8Ir67EdZck8GZ1wD5DO");
        com.org.PoJo.User user2 = new com.org.PoJo.User();
        user2.setUsername("zhangsan");
        user2.setPassword("$2a$10$eA8VdJ6o4PkCq6i75OPhOe8UnwT1t5lmcp8Ir67EdZck8GZ1wD5DO");
        map.put(user1.getUsername(), user1);
        map.put(user2.getUsername(), user2);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         需要返回的参数中User中的参数有： username（用户名）,password（密码）, authorities(权限集合)
        com.org.PoJo.User userindb = map.get(username);
        if (null != userindb) {
            String password = userindb.getPassword();
            List<GrantedAuthority> list=new ArrayList<>();
//             添加权限     GrantedAuthority 是接口不能创建对象，但是我们使用它的实现类SimpleGrantedAuthority 创建对象
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
            list.add(grantedAuthority);
            list.add(new SimpleGrantedAuthority("add")); // 权限
            list.add(new SimpleGrantedAuthority("delete")); // 权限
           // list.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 角色
//             这里的User不是Pojo中的，而是Bcrypt中的User，
//             参数一：用户名，参数二：数据库中的密码（因为在配置文件中添加的endiong为BCrypt，所以在数据库中的密码也是该加密类型的），参数三:权限集合
            User user = new User(username,password,list);
            return user;
        }
       else {
           return null;
        }
    }
}
