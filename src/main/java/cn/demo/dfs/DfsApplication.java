package cn.demo.dfs;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class DfsApplication {
//    @Bean
//    public ConstantMonitor getBean(){
//    return new ConstantMonitor();
//}
    public static void main(String[] args) {
        SpringApplication.run(DfsApplication.class, args);
    }

}
