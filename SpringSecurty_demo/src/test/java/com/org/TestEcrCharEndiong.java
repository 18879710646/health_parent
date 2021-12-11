package com.org;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author HP
 * @Date 2021/12/5 11:26
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public class TestEcrCharEndiong {
    @Test
    public  void EctTest(){
//         BCryptPasswordEncoder 这是不可逆的  ，因为加的是随机盐
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123"));
        // $2a$10$7wMcyp2l83L10NihKwnBUOIoIBOkevYDDZU8VMtt/Unq1cCU5V5O6
        // $2a$10$m/ztEtZY/eJlJi/WcKAJ5OcAVCimbC5YU6kevvpca1lQgT9eiDeHK
//        $2a$10$eA8VdJ6o4PkCq6i75OPhOe8UnwT1t5lmcp8Ir67EdZck8GZ1wD5DO
    }

    @Test
    public void   Math(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 返回的是boolean类型，判断明文和密文是否相同
        System.out.println(encoder.matches("123", "$2a$10$eA8VdJ6o4PkCq6i75OPhOe8UnwT1t5lmcp8Ir67EdZck8GZ1wD5DO"));
    }
}
