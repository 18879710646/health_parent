package com.org.Service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import com.org.Dao.CheckGroupDao;
import com.org.PoJo.CheckGroup;
import com.org.Service.CheckGroupService;
import com.org.entity.PageResult;
import com.org.entity.QueryPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/24 18:04
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */
@Service
public class CheckGroupServiceImpl implements CheckGroupService {
    @Autowired
    private CheckGroupDao checkGroupDao;

    // 添加检查组
    @Transactional
    @Override
    public void add(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 添加新增检查组
        checkGroupDao.add(checkGroup);
        // 获取到新增检查组中的id
        Integer checkGroupId = checkGroup.getId();
        // 对传入进来的id集合进行非空判断
        if (null != checkitemIds) {
            // 遍历集合
            for (Integer checkitemId : checkitemIds) {
                // 添加检查项和检查组的关系
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }
    }

    // 检查组分页展示
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        if (!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        Page<CheckGroup> page = checkGroupDao.findPage(queryPageBean.getQueryString());
        long total = page.getTotal();
        List<CheckGroup> row = page.getResult();
        return new PageResult<CheckGroup>(total,row);
    }

    // 通过id来查询出检查组中的数据
    @Override
    public CheckGroup findById(int id) {
        CheckGroup checkGroup = checkGroupDao.findById(id);
        return checkGroup;
    }

    // 通过检查组id来获取到检查项id集合
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        List<Integer> checkItemList=checkGroupDao.findCheckItemIdsByCheckGroupId(id);
        return checkItemList;
    }

    // 更新检查组数据

    /**
     *更新数据， 获取到新数据中的id，删除checkitem和cheeckgroup中的关系表中的数据
     * 在把两者中的新数据中的关系添加到数据表中
     * @param checkGroup
     * @param checkitemIds
     */
    @Transactional
    @Override
    public void Update(CheckGroup checkGroup, Integer[] checkitemIds) {
        // 更行数据
         checkGroupDao.Update(checkGroup);
        // 获取到该行更新的数据的id
        Integer updateId = checkGroup.getId();
        // 删除关系表中的数据
        checkGroupDao.DeletecheckitemAndcheckGroup(updateId);
        if (null !=checkitemIds){
            for (Integer checkitemId : checkitemIds) {
                checkGroupDao.insertcheckitemAndcheckGroup(checkitemId,updateId);
            }
        }
    }
    //删除检查组
    @Override
    public void DeleteById(int id) {
        checkGroupDao.DeleteById(id);
    }

    @Override
    public List<CheckGroup> findAll() {

     List<CheckGroup> checkGroups=   checkGroupDao.findAll();
     return checkGroups;
    }
}
