package com.org.Controller;
import com.org.POI.POIUtils;
import com.org.PoJo.OrderSetting;
import com.org.Service.OrderSettingService;
import com.org.constant.MessageConstant;
import com.org.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @Author HP
 * @Date 2021/12/4 10:15
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */
@RestController
@RequestMapping("/ordersetting")
public class orderSettingController {
    @Autowired
    private OrderSettingService settingService;
    // 把excel中的数据在mysql中进行查询添加更新操作
    @PostMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            // 读取到excel中的数据
            List<String[]> readExcel = POIUtils.readExcel(excelFile);
            // 初始化日期格式
            SimpleDateFormat dateFormat = new SimpleDateFormat(POIUtils.DATE_FORMAT);
            //生成一个OrderSetting的一个List容器
            ArrayList<OrderSetting> arrayList = new ArrayList<>();
            // 遍历excel中的数据
            for (String[] strings : readExcel) {
                // 获取到第一个数据
                String orderDate = strings[0];
                // 获取到第二个数据
                String number = strings[1];
                int num = (int)Double.parseDouble(number);
                // 把数据带入到实体类中
                OrderSetting orderSetting = new OrderSetting(dateFormat.parse(orderDate),num);
                // 把实体类装进list集合中
                arrayList.add(orderSetting);
                settingService.doImport(arrayList);
            }
            return new Result(true,MessageConstant.IMPORT_ORDERSETTING_SUCCESS);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    // 把预约数据添加到前端中展示
    @PostMapping("getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String date){
        try {
            List<Map> settingByMonth=settingService.getOrderSettingByMonth(date);
            return new Result(true,MessageConstant.QUERY_ORDER_SUCCESS,settingByMonth);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,MessageConstant.QUERY_ORDER_FAIL);
        }

    }
}
