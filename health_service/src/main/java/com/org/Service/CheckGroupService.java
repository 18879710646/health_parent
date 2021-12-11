package com.org.Service;

import com.github.pagehelper.Page;
import com.org.PoJo.CheckGroup;
import com.org.entity.PageResult;
import com.org.entity.QueryPageBean;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/24 18:04
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public interface CheckGroupService {
    // 添加检查组
    void add(CheckGroup checkGroup,Integer [] checkitemIds);

    // 检查组分页展示
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    // 通过id来查询检查组中的数据
    CheckGroup findById(int id);
    // 通过检查组id来获取到检查项id集合
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    // 更新检查组数据
    void Update(CheckGroup checkGroup, Integer[] checkitemIds);

    void DeleteById(int id);
    // 查询全部检查组
    List<CheckGroup> findAll();

}
