package com.org.Service.Impl;
import com.org.Dao.OrderSettingDao;
import com.org.MyError.MyException;
import com.org.PoJo.OrderSetting;
import com.org.Service.OrderSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * @Author HP
 * @Date 2021/12/4 11:51
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */
@Service
public class OrderSettingServiceImpl implements OrderSettingService {
    private final Logger logger=LoggerFactory.getLogger(OrderSettingServiceImpl.class);
    @Autowired
    private OrderSettingDao settingDao;
    @Override
    public void doImport(ArrayList<OrderSetting> arrayList) {
        for (OrderSetting orderSetting : arrayList) {
            //通过日期来查询是否存在预约数据
            OrderSetting osInb=settingDao.findByOrderttingTime(orderSetting.getOrderDate());
            if (null!=osInb){
                // 判断已预约数是否大于最大预约数字
                int number = osInb.getNumber();
                int reservations = osInb.getReservations();
                //如果大于就报错
                if (reservations>number){
                    throw  new MyException("预约数不能大于最大预约数");
                }else {
                // 如果小于就更新
                    settingDao.UpdateNumber(orderSetting);
            }
        } else {
                // 如果不存在那么就添加数据
                settingDao.add(orderSetting);
            }
        }
    }


    @Override

    public List<Map> getOrderSettingByMonth(String date) {
        // 获取当前月份的最后一天
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MARCH,1);
        calendar.set(Calendar.DAY_OF_MONTH,0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
        String format = dateFormat.format(calendar.getTime());
        // 该月的开始时间
        String starttime = date + "-1";
        // 该月的结束时间
        String endtime = date + "-"+format;
        Calendar instance = calendar;

        // 创建一个Map集合，把开始结束时间分别添加到Map中
        HashMap hashMap = new HashMap();
        hashMap.put("start",starttime);
        hashMap.put("end",endtime);
        // 查询该月份预约设置，返回list集合
       List<OrderSetting> orderSettingList= settingDao.findByMonth(hashMap);
        //将返回的List集合包装成为一个List<Map>用于返回数据
        List<Map> mapList = new ArrayList<>();
        // 遍历数据报装成一个map，把当前是几号，和已预约人数，可预约人数put的进去   date,number,reservations
        for (OrderSetting orderSetting : orderSettingList) {
            HashMap map = new HashMap();
            // 获取当前是几号
            map.put("date",orderSetting.getOrderDate());
            // 可预约人数
            map.put("number",orderSetting.getNumber());
            // 已预约人数
            map.put("reservations",orderSetting.getReservations());
            mapList.add(map);
        }
        return mapList;



    }


}
