package com.org.Dao;

import com.github.pagehelper.Page;
import com.org.PoJo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/24 18:07
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public interface CheckGroupDao {
    // 添加检查组数据
    void add(CheckGroup checkGroup);
    // 添加检查组和检查项中的关系，在mysql中有独立的表来记录
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer  checkGroupId ,@Param("checkItemId") Integer checkItemId);

    // 检查组分页数据查询（模糊查询）
    Page<CheckGroup> findPage(String checkGroupstring);

    // 根据id来查询出检查组中的数据
    CheckGroup findById(int id);

    // 根据检查组查询检查项id集合
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    // 更新数据
    void Update(CheckGroup checkGroup);
    // 删除关系表
    void DeletecheckitemAndcheckGroup(Integer updateId);
    // 把关系添加重新添加到关系表中
    void insertcheckitemAndcheckGroup(@Param("checkitemId")Integer checkitemId, @Param("checkGroupId") Integer checkGroupId);
    // 删除检查组根据id来删除
    void DeleteById(int id);
    //查询所有的检查组数据
    List<CheckGroup> findAll();

}
