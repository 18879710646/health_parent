package com.org.Service;

import com.org.PoJo.OrderSetting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author HP
 * @Date 2021/12/4 11:51
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public interface OrderSettingService {
    void doImport(ArrayList<OrderSetting> arrayList);

    List<Map> getOrderSettingByMonth(String date);
}
