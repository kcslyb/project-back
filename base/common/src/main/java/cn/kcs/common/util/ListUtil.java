package cn.kcs.common.util;

import java.util.*;

/**
 * @description:
 * @author: kcs
 * @create: 2019-03-23 18:27
 **/
public class ListUtil {

    public static boolean isNullOrEmpty(Collection list) {
        return null == list || list.isEmpty();
    }

    public static boolean notNullAndEmpty(Collection list) {
        return !isNullOrEmpty(list);
    }

    /**
     * 找出两个数组中不相同的元素
     *
     * @param t1
     * @param t2
     * @param <T>
     * @return
     */
    public static <T> List<T> getDifferent(List<T> t1, List<T> t2) {
        List<T> list2 = new ArrayList<T>();//用来存放2个数组中不相同的元素
        if (t1.size() < t2.size()) {
            for (T t : t2) {
                if (!t1.contains(t)) {
                    list2.add(t);
                }
            }
        } else {
            for (T t : t2) {
                if (t1.contains(t)) {
                    list2.add(t);
                }
            }
        }
        return list2;
    }

    /**
     * 找出两个数组中相同的元素
     *
     * @param a
     * @param b
     * @return
     */
    public static Set<Integer> getSame(Integer[] a, Integer[] b) {
        Set<Integer> same = new HashSet<Integer>();  //用来存放两个数组中相同的元素
        Set<Integer> temp = new HashSet<Integer>();  //用来存放数组a中的元素
        for (int i = 0; i < a.length; i++) {
            temp.add(a[i]);   //把数组a中的元素放到Set中，可以去除重复的元素
        }
        for (int j = 0; j < b.length; j++) {
            //把数组b中的元素添加到temp中
            //如果temp中已存在相同的元素，则temp.add（b[j]）返回false
            if (!temp.add(b[j]))
                same.add(b[j]);
        }
        return same;
    }

    /**
     * list 去重
     *
     * @param list
     * @return
     */
    public static <T> List<T> removeDuplicate(List<T> list) {
        List temp = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (!temp.contains(list.get(i))) {
                temp.add(list.get(i));
            }
        }
        return temp;
    }

}
