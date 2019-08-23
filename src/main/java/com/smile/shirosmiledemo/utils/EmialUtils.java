package com.smile.shirosmiledemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/23 9:53
 * @description：
 */
public class EmialUtils {

    private static final String regEx = "[a-zA-Z_]{0,}[0-9]{0,}@(([a-zA-z0-9]-*){1,}\\.){1,3}[a-zA-z\\-]{1,}";
    private static Pattern pattern = Pattern.compile(regEx);


    public static boolean verify(String emailStr) {
        Matcher matcher = pattern.matcher(emailStr);
        // 字符串是否与正则表达式相匹配
        return matcher.matches();
    }

}
