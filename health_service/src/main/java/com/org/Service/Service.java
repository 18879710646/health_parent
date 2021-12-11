package com.org.Service;

import com.org.PoJo.CheckGroup;
import com.org.PoJo.CheckItem;
import com.org.PoJo.User;
import com.org.entity.PageResult;
import com.org.entity.QueryPageBean;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/22 13:56
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public interface Service {
//     查询所有
    List<User> SelectById();

    // 新增检查项
    void add(CheckItem checkItem);
    // 检查项分页查询
    PageResult<CheckItem>  findPage(QueryPageBean queryPageBean);

    // 检查项删除操作
    void deleteById(int id);

    // 编辑检查项
    CheckItem findById(int id);

    // 编辑检查项（更新数据）
    void update(CheckItem checkItem);


    // 检查组查询所有
    List<CheckItem> findAll();

}
