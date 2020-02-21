//package cn.demo.dfs.spring;
//
//import cn.demo.dfs.config.AppConfig;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//public class TestSpring {
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
//        ac.register(AppConfig.class);//注册AppConfig
//        ac.refresh();//解析AppConfig里的ComponentScan注入的包名进行实例化对象 new
//        Man man=(Man)ac.getBean("man");
////        man.run();
//        ac.close();
//    }
//}
