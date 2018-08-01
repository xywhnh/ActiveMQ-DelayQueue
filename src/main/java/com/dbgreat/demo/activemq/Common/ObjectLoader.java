package com.dbgreat.demo.activemq.Common;

import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ObjectLoader {
    private static final ConcurrentMap<String, Object> objectCache = new ConcurrentHashMap<>();

    public static Map<String, Object> getInstances() {
        return objectCache;
    }

    public static Boolean addInstance(String clazz) {
        if (!validParam(clazz)) {
            throw new IllegalArgumentException("class path param is null");
        }
        Object instance = objectCache.get(clazz);
        if (null == instance) {
            instance = ObjectLoader.newInstance(clazz);
            objectCache.putIfAbsent(clazz, instance);
        }
        return null != instance;
    }

    public static Boolean removeInstance(String clazz) {
        if (!validParam(clazz)) {
            throw new IllegalArgumentException("class path param is null");
        }
        Object instance = objectCache.get(clazz);
        if (null != instance) {
            objectCache.remove(clazz);
        }
        return true;
    }

    private static Boolean validParam(String clazz) {
        if (StringUtils.isEmpty(clazz)) {
            return false;
        }
        return true;
    }

    private static Object newInstance(String clazz) {
        Class<?> implClass;
        Object implObject = null;
        try {
            implClass = Class.forName(clazz);
            if (null != implClass) {
                implObject = implClass.newInstance();
            }
        } catch (IllegalAccessException e) {

        } catch (InstantiationException e) {

        } catch (ClassNotFoundException e) {

        }
        return implObject;
    }

    public static long getSize() {
        return objectCache.size();
    }
}
