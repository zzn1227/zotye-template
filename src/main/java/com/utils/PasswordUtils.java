package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-1-30 上午11:03:57
 */
public class PasswordUtils {

    /**
     * 判断是否为弱口令
     * 
     * @author tcl [2015-6-16]
     * @param str
     * @return
     */
    public static boolean isWeakPassword(String password) {

        if (!isLengthOK(password, 8)) {
            return true;
        }
        if (isAllNumeric(password)) {
            return true;
        }
        if (isAllCharacter(password)) {
            return true;
        }
        if (isNotAllowPassword(password)) {
            return true;
        }

        return false;
    }

    /**
     * 密码长度是否符合
     * 
     * @author Administrator [2015-6-16]
     * @param password
     * @param minLength
     * @return
     */
    private static boolean isLengthOK(String password, int minLength) {

        return password.length() >= minLength;

    }

    /**
     * 是否是所有数字
     */
    private static boolean isAllNumeric(String password) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(password);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是所有字符
     */
    private static boolean isAllCharacter(String password) {
        Pattern pattern = Pattern.compile("[a-zA-Z]*");
        Matcher isNum = pattern.matcher(password);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 是否是其他不允许的密码
     * 
     * @author tcl [2015-6-16]
     * @param password
     * @return
     */
    private static boolean isNotAllowPassword(String password) {
        String notAllowPassword = "ZOTYE123,";
        if (notAllowPassword.indexOf((password + ",").toUpperCase()) >= 0) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // System.out.println("1234:"+isAllNumeric("1234"));
        // System.out.println("12a34:"+isAllNumeric("12a34"));
        // System.out.println("12a34:"+isAllCharacter("12a34"));
        // System.out.println("abc:"+isAllCharacter("abc"));
        // System.out.println("ABC:"+isAllCharacter("ABC"));
        // System.out.println("ABC:"+isLengthOK("ABC",8));
        // System.out.println("ABC:"+isLengthOK("ABCddd1223",8));
        // System.out.println("ZoTYE123:"+isNotAllowPassword("ZoTYE12"));
        System.out.println(isWeakPassword("abcdergGHH"));

    }
}
