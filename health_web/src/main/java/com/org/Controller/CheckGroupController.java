package com.org.Controller;

import com.org.PoJo.CheckGroup;
import com.org.Service.CheckGroupService;
import com.org.constant.MessageConstant;
import com.org.entity.PageResult;
import com.org.entity.QueryPageBean;
import com.org.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/24 10:44
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {
    @Autowired
    private CheckGroupService checkGroupService;

    // 新增检查组
    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,Integer [] checkitemIds){
        try {
            checkGroupService.add(checkGroup,checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return  new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    // 检查组分页
    @PostMapping("/findPage")
    public  Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckGroup> page = null;
        try {
            page = checkGroupService.findPage(queryPageBean);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,page);
    }

    // 通过id来查询出检查组中的数据
    @GetMapping("/findById")
    public Result  findById(int id){
        CheckGroup checkGroup = null;
        try {
            checkGroup = checkGroupService.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    // 通过检查组id来获取到检查项中的id集合
    @GetMapping("/findCheckItemIdsByCheckGroupId")
    public Result findCheckItemIdsByCheckGroupId(int id){
        List<Integer> checkItemList=null;
        try {
            checkItemList =checkGroupService.findCheckItemIdsByCheckGroupId(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkItemList);
    }

    // 更新检查组数据
    @PostMapping("/update")
    public Result Update(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try {
            checkGroupService.Update(checkGroup,checkitemIds);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new  Result(true,MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    // 根据id来删除检查组中的数据
    @PostMapping("/deleteById")
    public Result DeleteById(int id){
        try {
            checkGroupService.DeleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(true,MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    // 查询所有
    @GetMapping("/findAll")
    public Result  findAll(){
     List<CheckGroup> checkGroups   =checkGroupService.findAll();
     if (checkGroups!=null && checkGroups.size()>0){
         return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroups);
     }
     return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
    }

}
