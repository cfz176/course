package com.course.server.utils;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用作实体类转换 dto vo
 */
public class BeanCopyUtils {

    //实体类转换
    public static <V> V copyBean(Object source,Class<V> target) {
        V result = null;
        try {
            //实例化实体类后进行转换
            result = target.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        //返回转换过的实体类
        return result;
    }

    //实体类集合转换
    public static <V,O> List<V> copyBeanList(List<O> source, Class<V> target) {
        ArrayList<V> targetList = new ArrayList<>();
        //实体类集合遍历
        for (int i = 0, l = source.size(); i < l; i++){
            //调用实体类转换方法
            V v = copyBean(source.get(i), target);
            //加入目标类集合
            targetList.add(v);
        }
        //返回转换后的实体类集合
        return targetList;
    }

}
