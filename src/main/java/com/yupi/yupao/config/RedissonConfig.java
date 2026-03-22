package com.yupi.yupao.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Redisson 配置
 * <a href="https://github.com/redisson/redisson#quick-start">文档</a>
 */
@Configuration
@ConfigurationProperties(prefix = "spring.redis") // 从 application.yml 中读取 spring.redis 的配置
@Data
public class RedissonConfig {

    private String host;

    private String port;

    /*
    * @Bean 的的使用：
    *   创建一个 Bean 对象，并交给 Spring 管理。其他地方可以通过 @Autowired，@Resource 来获取这个 Bean 对象。
    * */
    @Bean
    public RedissonClient redissonClient() {
        // 1. 创建配置
        Config config = new Config();
        // 2. 绑定地址和端口
        String redisAddress = String.format("redis://%s:%s", host, port);
        config.useSingleServer().setAddress(redisAddress).setDatabase(3);
        // 3. 创建实例
        return Redisson.create(config);
    }
}
