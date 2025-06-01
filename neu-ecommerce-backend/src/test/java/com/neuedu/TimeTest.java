package com.neuedu;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeTest {

    @Test
    public void test() {
        String dateTimeStr = "2023-04-01 14:30:00";

        // 创建一个DateTimeFormatter对象，用于解析ISO 8601格式的日期时间字符串
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 使用parse方法将字符串解析为LocalDateTime对象
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

        // 输出结果，验证转换是否成功
        System.out.println(dateTime);
    }
}
