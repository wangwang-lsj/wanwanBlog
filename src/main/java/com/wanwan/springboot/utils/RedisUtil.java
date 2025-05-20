package com.wanwan.springboot.utils;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author：玩玩
 * @since：2024/3/13 20:35
 * @description:
 */
@Component
public class RedisUtil {


    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil(StringRedisTemplate stringRedisTemplate) {
        RedisUtil.stringRedisTemplate = stringRedisTemplate;
    }


    /**
     * 写入缓存
     *
     * @param key      键
     * @param value    值
     * @param time     缓存时长
     * @param timeUnit 缓存时长 时间单位
     */
    public static void put(String key, Object value, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value == null ? "" : JSON.toJSONString(value, SerializerFeature.WriteMapNullValue), time, timeUnit);
    }

    /**
     * 写入缓存 (8小时)
     *
     * @param key   键
     * @param value 值
     */
    public static void putEightHours(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, value == null ? "" : JSON.toJSONString(value, SerializerFeature.WriteMapNullValue), 8, TimeUnit.HOURS);
    }
    /**
     * 写入缓存 (24小时)
     *
     * @param key   键
     * @param value 值
     */
    public static void put(String key, Object value) {
        stringRedisTemplate.opsForValue().set(key, value == null ? "" : JSON.toJSONString(value, SerializerFeature.WriteMapNullValue), 24, TimeUnit.HOURS);
    }

    /**
     * 获取缓存
     *
     * @param key    键
     * @param tClass 需序列化 class
     * @param <T>    实例泛型
     * @return class实例
     */
    public static <T> T get(String key, Class<T> tClass) {
        Object o = stringRedisTemplate.opsForValue().get(key);
        if (o == null) {
            return null;
        }
        return JSON.parseObject(o.toString(), tClass);
    }

    /**
     * 获取缓存
     *
     * @param key           键
     * @param typeReference 类型引用
     * @param <T>           实例泛型
     * @return class实例
     */
    public static <T> T get(String key, TypeReference<T> typeReference) {
        Object o = stringRedisTemplate.opsForValue().get(key);
        if (o == null) {
            return null;
        }
        return JSON.parseObject(o.toString(), typeReference);
    }
    public static <T> List<T> getArray(String key,Class<T> tClass) {
        Object o = stringRedisTemplate.opsForValue().get(key);
        if (o == null) {
            return null;
        }
        return JSON.parseArray(o.toString(),tClass);
    }
    /**
     * 删除key
     *
     * @param key
     */
    public static void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    /**
     * 删除文件夹(user:1的话,用user:*)
     * @param prefix
     * @return Long
     */
    public static Long deletePrefix(String prefix){
        Set<String> keys = stringRedisTemplate.keys(prefix+":*");
        if(!CollectionUtils.isEmpty(keys)){
            return stringRedisTemplate.delete(keys);
        }
        return null;
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return Boolean
     */
    public static Boolean hasKey(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    /**
     * 设置过期时间
     *
     * @param key     键
     * @param timeout 时长
     * @param unit    时间单位
     * @return 设置是否成功
     */
    public static Boolean expire(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    //------------------------------------------ Map的操作 -----------------------------------------------

    /**
     * 保存map值到指定key
     */
    public static void hput(String key, String field, Object object) {
        stringRedisTemplate.opsForHash().put(key, field, object);
    }

    /**
     * 保存多个key-value的map
     */
    public static <T> void hPutAll(String key, Map<String, T> map) {
        stringRedisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 获取存储在哈希表中指定字段的值
     *
     * @param key
     * @param field
     * @return
     */
    public static <T> T hGet(String key, String field, Class<T> clazz) {
        if (!hasKey(key)) {
            return null;
        }
        String mapJson = JSON.toJSONString(stringRedisTemplate.opsForHash().get(key, field));
        return JSON.parseObject(mapJson, clazz);
    }

    /**
     * 删除一个或多个哈希表字段
     *
     * @param key
     * @param fields
     * @return
     */
    public static Long hDelete(String key, Object... fields) {
        return stringRedisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 获取一个map集
     *
     * @param key   名
     * @param typeReference 返回的map-value的值类型
     * @param <E>
     * @return
     */
    public static <E> HashMap<String, E> hGetAll(String key,TypeReference<E> typeReference) {
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
        if (entries.size() == 0) {
            return new HashMap<>();
        }
        HashMap<String, E> resultMap = new HashMap<>(entries.size());
        for (Map.Entry<Object, Object> entry : entries.entrySet()) {
            E valueObj = JSON.parseObject(JSON.toJSONString(entry.getValue()), typeReference);
            String hashKey = JSON.toJSONString(entry.getKey());
            hashKey = hashKey.replace("\"", "");
            resultMap.put(hashKey, valueObj);
        }
        return resultMap;
    }
}


