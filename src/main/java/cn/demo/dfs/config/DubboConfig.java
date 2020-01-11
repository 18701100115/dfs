//package cn.demo.dfs.config;
//
//import cn.user.service.UserService;
//import cn.user.service.impl.UserServiceImpl;
//import com.alibaba.dubbo.config.ApplicationConfig;
//import com.alibaba.dubbo.config.MethodConfig;
//import com.alibaba.dubbo.config.RegistryConfig;
//import com.alibaba.dubbo.config.ServiceConfig;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.ArrayList;
//import java.util.List;
//
////@Configuration
//public class DubboConfig     {
//
//    @Bean
//    public ApplicationConfig applicationConfig() {
//        ApplicationConfig applicationConfig = new ApplicationConfig();
//        applicationConfig.setName("user-service-provider");
//        return applicationConfig;
//    }
//    @Bean
//    public RegistryConfig registryConfig() {
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setProtocol("zookeeper");
//        registryConfig.setAddress("192.168.26.132:2181");
//        return registryConfig;
//    }
//    @Bean
//    public ServiceConfig<UserService> userServiceConfig(){
//        ServiceConfig<UserService> serviceConfig = new ServiceConfig<>();
//        serviceConfig.setInterface(UserService.class);
//        serviceConfig.setRef(new UserServiceImpl());
//        serviceConfig.setVersion("1.0.0");
//
//        //配置每一个method的信息
//        MethodConfig methodConfig = new MethodConfig();
//        methodConfig.setName("getUserAddressList");
//        methodConfig.setTimeout(1000);
//
//        //将method的设置关联到service配置中
//        List<MethodConfig> methods = new ArrayList<MethodConfig>();
//        methods.add(methodConfig);
//        serviceConfig.setMethods(methods);
//
//        return serviceConfig;
//    }
//}
