package love.baihao.interceptor;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import love.baihao.annotation.SnakeToCamelCase;
import org.springframework.core.MethodParameter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SnakeToCamelCaseInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        MethodParameter[] methodParameters = handlerMethod.getMethodParameters();
        for (MethodParameter methodParameter : methodParameters) {
            if(methodParameter.hasParameterAnnotation(SnakeToCamelCase.class)){
                InputStream body = httpRequest.getBody();
                String requestBody = convertInputStreamToString(body);
                if(requestBody != null){
                    String convertRequestBody = convertToCamelCase(requestBody);
                    Map<String, Object> map = convertToMap(convertRequestBody);
                    map.forEach(request::setAttribute);
                }
            }
        }
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String camelCaseName = convertToCamelCase(paramName);
            if (!paramName.equals(camelCaseName)) {
                String paramValue = request.getParameter(paramName);
                request.setAttribute(camelCaseName, paramValue);
            }
        }
        return true;
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

    private Map<String,Object> convertToMap(String input) {
        JSONObject jsonObject = JSON.parseObject(input);
        Map<String, Object> modifiedMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
            String key = entry.getKey();
            String camelCaseKey = convertToCamelCase(key);
            modifiedMap.put(camelCaseKey, entry.getValue());
        }
        return modifiedMap;
    }


    public static BufferedReader convertToBufferedReader(String input) {
        StringReader stringReader = new StringReader(input);
        return new BufferedReader(stringReader);
    }

    public String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        String result = scanner.hasNext() ? scanner.next() : "";
        scanner.close();
        return result;
    }

}