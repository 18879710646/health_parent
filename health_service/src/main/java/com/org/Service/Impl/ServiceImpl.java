package com.org.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.org.Dao.UserDao;
import com.org.PoJo.CheckGroup;
import com.org.PoJo.CheckItem;
import com.org.PoJo.User;
import com.org.Service.Service;
import com.org.entity.PageResult;
import com.org.entity.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/22 13:57
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */
@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
    @Autowired
    private UserDao dao;
    @Override
    public List<User> SelectById() {
        List<User> users = dao.SelectById();
        System.out.println(users);
        return users;
    }
    @Transactional
    // 新增检查项
    @Override
    public void add(CheckItem checkItem) {
        dao.add(checkItem);
    }
    // 查询并分页展示
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        // 模糊查询
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //   PageHelper.startPage 方法中已经写好了limit方法，后面添加参数的话也可以实行排序，我们只需要传递参数一：当前页码数 ，参数二：每页多少条数据
        // 在使用PageHelper和Page的时候，必须要配置要该工具包的配置
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckItem> page = dao.findPage(queryPageBean.getQueryString());
        Long total = page.getTotal(); // 数据量计算
        List<CheckItem> rows = page.getResult();  // 获取到数据集，因为在Page中继承了ArrayList所以传递出来的数据也将是List集合的
        return new PageResult<CheckItem>(total,rows);
    }
    // 检查项删除操作
    @Override
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    @Override
    public CheckItem findById(int id) {
        CheckItem checkItem = dao.findById(id);
        return checkItem;
    }
    // 更新检查项（更新数据）
    @Override
    public void update(CheckItem checkItem) {
        dao.Update(checkItem);
    }

    // 检查组查询所有
    @Override
    public List<CheckItem> findAll() {
        List<CheckItem> all = dao.findAll();
        return all;
    }
}
