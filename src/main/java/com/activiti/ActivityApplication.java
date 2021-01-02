package com.activiti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.net.InetAddress;
import java.net.UnknownHostException;

@EnableSwagger2
@SpringBootApplication(
        exclude = {
                org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class,
                org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration.class
        }
)
public class ActivityApplication {
//测试
    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(ActivityApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        //String path = env.getProperty("server.servlet.context-path");
        String active = env.getProperty("spring.profiles.active");
        String maxFileSize = env.getProperty("spring.servlet.multipart.max-file-size"); //最大文件大小
        String maxRequestSize = env.getProperty("spring.servlet.multipart.max-request-size"); //最大请求大小
        System.out.println("\n----------------------------------------------------------\n\t" +
                "Application is running! Access URLs:\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + "/doc.html\n\t" +
                "spring-profiles-active: \t\t" + active + "\n\t" +
                "max-file-size: \t\t" + maxFileSize + "\n\t" +
                "max-request-size: \t\t" + maxRequestSize + "\n" +
                "----------------------------------------------------------");
    }
}
