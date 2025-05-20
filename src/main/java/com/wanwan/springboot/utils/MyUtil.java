package com.wanwan.springboot.utils;

import java.util.Random;

/**
 * @author 玩玩
 * @description
 * @since 2024/5/16  19:30
 */
public class MyUtil {
    // 生成包含数字和字母的随机字符串（6位）
    public static String generateRandomString() {
        int length = 6; // 生成的字符串长度
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }
}
