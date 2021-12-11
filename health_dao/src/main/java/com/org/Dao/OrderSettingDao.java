package com.org.Dao;

import com.org.PoJo.OrderSetting;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author HP
 * @Date 2021/12/4 12:03
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public interface OrderSettingDao {
    // 根据预约日期来查找到预约数据
    OrderSetting findByOrderttingTime(Date orderDate);
    // 更新数据
    void UpdateNumber(OrderSetting orderSetting);
    // 添加数据
    void add(OrderSetting orderSetting);
    // 查询日期数据集
    List<OrderSetting> findByMonth(HashMap hashMap);
}
