package cn.demo.dfs.config;

import org.springframework.boot.SpringBootConfiguration;

import javax.annotation.PostConstruct;

@SpringBootConfiguration
public class ElasticSearchConfig {
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
}
