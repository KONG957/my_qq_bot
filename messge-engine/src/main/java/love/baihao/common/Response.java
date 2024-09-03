package love.baihao.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @projectName: my_qq_bot
 * @package: love.baihao.common
 * @className: Respons
 * @author: bh
 * @description: 返回类型封装
 * @date: 2024/9/3 17:48
 * @version: 1.0
 */
@Data
@NoArgsConstructor
public class Response <T> implements Serializable {

    private int code;
    private String massage;
    private T data;



    public Response(int code, String massage, T data) {
        this.code = code;
        this.massage = massage;
        this.data = data;
    }

    public static <T>Response <T> success(){
        Response<T> response = new Response<>();
        response.setCode(200);
        response.setMassage("success");
        return response;
    }


    public static <T>Response <T> success(T data){
        Response<T> response = new Response<>();
        response.setCode(200);
        response.setMassage("success");
        response.setData(data);
        return response;
    }

    public static <T>Response <T> error(String massage){
        Response<T> response = new Response<>();
        response.setCode(500);
        response.setMassage(massage);
        if(massage==null||"".equals(massage)){
            response.setMassage("error");
        }
        return response;
    }

    public static <T>Response error(){
        return error("error");
    }


}
