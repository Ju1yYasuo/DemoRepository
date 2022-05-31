package com.example.demo.util.json;

import com.example.demo.config.exception.MyException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.NonNull;

import java.util.List;

/**
 * Jackson 工具类
 *
 * @author luox
 * @date 2022/5/31
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        // 对于空的对象转json的时候不抛出错误
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        // 允许属性名称没有引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 允许单引号
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 设置日期不转为时间戳
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        // 设置输入时忽略在json字符串中存在但在java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 设置输出时包含属性的风格
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 序列化，将对象转化为json字符串
     *
     * @param data 数据
     * @return {@link String }
     * @author luox
     * @date 2022/05/31
     */
    public static String toJsonString(Object data){
        if (data == null) {
            return null;
        }
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new MyException("Jackson toJsonString error：" + data.getClass().getSimpleName(), e);
        }
    }


    /**
     * 反序列化，将json字符串转化为对象
     *
     * @param json  json
     * @param clazz clazz
     * @return {@link T }
     * @author luox
     * @date 2022/05/31
     */
    public static <T> T parse(@NonNull String json, Class<T> clazz){
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new MyException("Jackson parse error：" + clazz.getSimpleName(), e);
        }
    }

    /**
     * 反序列化，将json字符串转化为集合对象
     *
     * @param json  json
     * @param clazz clazz
     * @return {@link List<T> }
     * @author luox
     * @date 2022/05/31
     */
    public static <T> List<T> parseArray(@NonNull String json, Class<T> clazz){
        try {
            return mapper.readValue(json, new TypeReference<List<T>>(){});
        } catch (JsonProcessingException e) {
            throw new MyException("Jackson parseArray error：" + clazz.getSimpleName(), e);
        }
    }

}
