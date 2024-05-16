// package com.wanwan.springboot.config;
//
// import com.alibaba.fastjson.serializer.*;
// import com.alibaba.fastjson.support.config.FastJsonConfig;
// import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
// import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.http.MediaType;
//
// import java.math.BigDecimal;
// import java.nio.charset.StandardCharsets;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.List;
//
// /**
//  * @author 玩玩
//  * @description
//  * @since 2024/5/15  16:01
//  */
// @Configuration
// public class FastjsonConfig {
//
//     @Bean
//     public HttpMessageConverters getFastJSONHttpMessageConvert() {
//         // 定义一个转换消息的对象
//         FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//         // 添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
//         FastJsonConfig fastJsonConfig = new FastJsonConfig();
//
//         // 修改配置返回内容的过滤
//         fastJsonConfig.setSerializerFeatures(
//                 // 格式化输出
//                 SerializerFeature.PrettyFormat,
//                 // 消除循环引用
//                 SerializerFeature.DisableCircularReferenceDetect,
//                 // 返回结果保留null值
//                 SerializerFeature.WriteMapNullValue,
//                 // 将返回值为null的字符串转变成"",在这里可以自己设置
//                 SerializerFeature.WriteNullStringAsEmpty,
//                 // List字段如果为null,输出为[],而非null
//                 SerializerFeature.WriteNullListAsEmpty,
//                 SerializerFeature.WriteDateUseDateFormat
//         );
//         // 解决 SerializerFeature.WriteNullStringAsEmpty 不生效问题
//         // ValueFilter valueFilter = (object, name, value) -> {
//         //     if (null == value){
//         //         value = "";
//         //     }
//         //     return value;
//         // };
//
//         // 设置全局日期格式
//         fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
//
//         // 注入过滤器
//         // fastJsonConfig.setSerializeFilters(valueFilter);
//
//         // Long、BigDecimal 序列化时转 String
//         // SerializeConfig serializeConfig = SerializeConfig.globalInstance;
//         // serializeConfig.put(Long.class, ToStringSerializer.instance);
//         // serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
//         // serializeConfig.put(BigDecimal.class, ToStringSerializer.instance);
//         // 在转换器中添加配置信息
//         // fastJsonConfig.setSerializeConfig(serializeConfig);
//         // fastConverter.setDefaultCharset(StandardCharsets.UTF_8);
//         // fastConverter.setFastJsonConfig(fastJsonConfig);
//
//         // 解决中文乱码问题，相当于在Controller上的@RequestMapping中加了个属性produces = "application/json"
//         // List<MediaType> mediaTypeList = new ArrayList<>();
//         // mediaTypeList.add(MediaType.APPLICATION_JSON);
//         // fastConverter.setSupportedMediaTypes(mediaTypeList);
//
//         return new HttpMessageConverters(fastConverter);
//     }
// }