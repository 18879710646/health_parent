package com.org.Dao;

import com.github.pagehelper.Page;
import com.org.PoJo.CheckGroup;
import com.org.PoJo.CheckItem;
import com.org.PoJo.User;
import com.org.entity.PageResult;
import com.org.entity.QueryPageBean;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/21 12:58
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public interface UserDao {
    List<User> SelectById();

    //新增检查项
    public  void add(CheckItem checkItem);
    // 检查项分页
    Page<CheckItem> findPage(String querystring);

    // 删除检查项
    void deleteById(int id);
    // 编辑检查项（先查询到数据）
    CheckItem findById(int id);

    // 编辑检查项（更新数据）
    void Update(CheckItem checkItem);
    // 检查组查询所有数据
    List<CheckItem> findAll();
}
