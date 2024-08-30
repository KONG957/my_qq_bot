package love.baihao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @projectName: Default (Template) Project
 * @package: love.baihao
 * @className: ${NAME}
 * @author: bh
 * @description: 启动类
 * @date: 2024/8/28 下午9:01
 * @version: 1.0
 */
@SpringBootApplication
public class AppMain {
    public static void main(String[] args) {
        SpringApplication.run(AppMain.class, args);
        System.out.println("running ✅");
    }
}