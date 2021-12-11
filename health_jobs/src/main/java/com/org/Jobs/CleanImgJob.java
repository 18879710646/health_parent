package com.org.Jobs;

import com.org.Dao.SetmealDao;
import com.org.QiNiouUtils.QiNiou;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * @Author HP
 * @Date 2021/11/30 19:42
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

@Component
public class CleanImgJob {
    @Autowired
    private SetmealDao setmealDao;
private final Logger log= LoggerFactory.getLogger(CleanImgJob.class);
    public void clean7NiuImgJob(){
        log.info("开始获取七牛云中的文件列表");
        QiNiou qiNiou = new QiNiou();
        // 获取到了全部数据的文件列表
        List<String> list = qiNiou.GetFile();
        log.info("七牛中有{}张图片",list.size());
        // 查询数据库中有所有图片
        List<String> dataImage= setmealDao.findImage();
        log.info("数据库中有{}张图片",dataImage.size());
        // 七牛上的图片减去数据库中的图片
        list.removeAll(dataImage);
        log.info("七牛中需要清理的图片张数为{}",list.size());
        qiNiou.DeleteFile(list);
        log.info("七牛上的垃圾图片清理完毕");
        List<String> lastlist = qiNiou.GetFile();
        log.info("七牛中还剩下有{}张图片",lastlist.size());
    }
}
