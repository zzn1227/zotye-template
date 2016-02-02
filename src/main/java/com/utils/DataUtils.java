package com.utils;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @author zhaozhineng
 * @date 2016-2-2 上午9:30:50
 */
public class DataUtils {

    /**
     * 数组去重
     * 
     * @author zhaozhineng
     * @date 2016-2-2
     */
    public static String[] arrayUnique(String[] a) {
        List<String> list = new LinkedList<String>();
        int size = a.length;
        for (int i = 0; i < size; i++) {
            if (!list.contains(a[i])) {
                list.add(a[i]);
            }
        }
        return (String[]) list.toArray(new String[list.size()]);
    }

}
