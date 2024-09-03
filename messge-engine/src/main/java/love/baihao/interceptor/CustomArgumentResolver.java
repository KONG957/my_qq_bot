package love.baihao.interceptor;

import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import love.baihao.annotation.SnakeToCamelCase;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.objenesis.instantiator.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.regex.Pattern;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.interceptor
 * @className: CustomArgumentResolver
 * @author: bh
 * @description: TODO
 * @date: 2024/9/3 23:33
 * @version: 1.0
 */


public class CustomArgumentResolver implements HandlerMethodArgumentResolver {

    private final Pattern UNDER_LINE_PATTER = Pattern.compile("_(\\w)");
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(SnakeToCamelCase.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container, NativeWebRequest request,
                                  WebDataBinderFactory binderFactory) throws Exception {
        ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request.getNativeRequest(HttpServletRequest.class));
        String requestBody = convertInputStreamToString(httpRequest.getBody());
        // 1 判断请求数据是否存在
        Class<?> type = parameter.getParameterType();
        if (StringUtils.isEmpty(requestBody)) {
            return ClassUtils.newInstance(type);
        }

        // 2 获取请求字段
        Field[] fields = FieldUtils.getAllFields(type);
        Map<String, Field> map = Arrays.stream(fields).collect(Collectors.toMap(Field::getName, v -> v));

        Object clazz = ClassUtils.newInstance(type);
        JSONObject json = JSONObject.parseObject(requestBody);
        for (Map.Entry<String, Object> entry : json.entrySet()) {

            // 1 获取键值
            String key = entry.getKey();
            Object value = entry.getValue();
            // 2 判断是否为空
            if (ObjectUtils.isEmpty(value)) {
                continue;
            }
            // 3 处理下划线转驼峰命名
            if (isExistUnderLine(key)) {
                key = this.convertToCamelCase(key);
            }
            // 4 获取字段
            Field field = map.getOrDefault(key, null);
            if (ObjectUtils.isEmpty(field)) {
                continue;
            }
            // 5 为特别的值设置参数
            if (!isPrimitive(field.getType())) {
                value = getArgument(field.getType(), value);
            }
            // 6 设置参数
            Method method = getSetterMethod(type, field, key);
            if (method != null&&value!=null) {
                if(field.getType().getSimpleName().equalsIgnoreCase(value.getClass().getSimpleName())){
                    method.invoke(clazz, value);
                }else {
                    if(field.getType().getSimpleName().equalsIgnoreCase("long")){
                        method.invoke(clazz, Long.parseLong(value.toString()));
                    } else if (field.getType().getSimpleName().equalsIgnoreCase("Integer")||field.getType().getSimpleName().equalsIgnoreCase("int")) {
                        method.invoke(clazz, Integer.parseInt(value.toString()));
                    }
                }

            }
        }
        return clazz;
    }

    public String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String result = scanner.hasNext() ? scanner.next() : "";
        scanner.close();
        return result;
    }

    private String convertToCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        String[] parts = input.split("_");
        result.append(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            result.append(Character.toUpperCase(parts[i].charAt(0))).append(parts[i].substring(1));
        }
        return result.toString();
    }


    private boolean isExistUnderLine(String value) {
        Matcher matcher = this.UNDER_LINE_PATTER.matcher(value);
        return matcher.find();
    }


    private boolean isPrimitive(Class<?> clazz) {
        return Integer.class.equals(clazz) || Double.class.equals(clazz) || Float.class.equals(clazz) || Boolean.class.equals(clazz) || Long.class.equals(clazz) || Byte.class.equals(clazz) || Short.class.equals(clazz) || clazz.isPrimitive();
    }

    private Object getArgument(Class<?> type, Object value) throws Exception {
        if(value!=null){
            Object o = JSONObject.parseObject(JSONObject.toJSONString(value), type);
            return o;
        }
        return null;
    }

    private String toUpperCaseFirstOne(String fieldName) {
        if (Character.isUpperCase(fieldName.charAt(0))) {
            return fieldName;
        } else {
            return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        }


    }

    private Method getSetterMethod(Class<?> clazz, Field field, String fileName) throws Exception {
        return clazz.getMethod("set" + toUpperCaseFirstOne(fileName), field.getType());
    }

}
