package com.org.Service.Impl;

import com.org.Dao.SecurtyDao;
import com.org.PoJo.Role;
import com.org.PoJo.User;
import com.org.Service.SecurtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/12/8 10:15
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */
@Service
public class SecurtyServiceImpl implements SecurtyService {
    @Autowired
    private SecurtyDao securtyDao;
    // 获取到用户信息
    @Override
    public User getUsername(String username) {
        User user=securtyDao.getUsername(username);
        return user;
    }

    // 获取到所有的权限列表
    @Override
    public List<Role> findRole() {
        List<Role>  roleList=securtyDao.findRole();
        return roleList;
    }
}
