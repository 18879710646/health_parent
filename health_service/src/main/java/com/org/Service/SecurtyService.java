package com.org.Service;

import com.org.PoJo.Role;
import com.org.PoJo.User;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/12/8 10:14
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public interface SecurtyService {
    // 权限控制中获取用户信息
    User getUsername(String username);
    // 获取到所有的用户权限

    List<Role> findRole();
}
