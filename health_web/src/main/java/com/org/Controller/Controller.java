package com.org.Controller;
import com.org.PoJo.CheckItem;
import com.org.Service.Service;
import com.org.constant.MessageConstant;
import com.org.entity.PageResult;
import com.org.entity.QueryPageBean;
import com.org.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/21 13:23
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */
@RestController
@RequestMapping("/checkitem")
public class Controller {
    @Autowired
    private Service service;
    // 新增检查项
    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        try {
            service.add(checkItem);
            return  new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(false,MessageConstant.ADD_CHECKGROUP_FAIL);

    }

    // 查询数据
    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        PageResult<CheckItem> page;
        try {
            page = service.findPage(queryPageBean);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,page);

    }

    // 删除检查项操作
    @PostMapping("/deleteById")
    public Result deleteById(int id){
        try {
            service.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true,MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    // 编辑检查项(通过id查询检查项)
    @GetMapping("/findById")
    public Result  findById(int id){
        CheckItem checkItem = service.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    // 编辑检查项（更新数据）
    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem){
        try {
            service.update(checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new  Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }


    // 检查组查询所有
    @GetMapping("/findAll")
    public Result findAll(){
        List<CheckItem> all;
        try {
            all = service.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,all);
    }


}
