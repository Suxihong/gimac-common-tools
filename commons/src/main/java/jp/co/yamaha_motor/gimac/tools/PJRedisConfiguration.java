package jp.co.yamaha_motor.gimac.tools;

import com.ymsl.solid.redis.config.RedisConfiguration;
import com.ymsl.solid.redis.config.RedisLockConfiguration;
import org.redisson.spring.starter.RedissonAutoConfigurationV2;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.session.MapSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author don't know
 */
@Configuration
public class PJRedisConfiguration {

    /**
     * default
     */
    public PJRedisConfiguration() {
        // default constructor
    }

    /**
     * @author don't know
     */
    @Profile("redis")
    @EnableSpringHttpSession
    @Configuration
    public static class EnablePJRedisConfiguration {
    }

    /**
     * @author  don't know
     */
    @Profile("!redis")
    @EnableSpringHttpSession
    @EnableAutoConfiguration(exclude = {RedissonAutoConfigurationV2.class, RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class, RedisConfiguration.class, RedisLockConfiguration.class})
    public static class DisablePJRedisConfiguration {
        // disable redis and use memory session

        /**
         *
         * @return mapSession
         */
        @Bean
        public SessionRepository<MapSession> sessionRepository() {
            return new MapSessionRepository(new ConcurrentHashMap<>());
        }
    }
}
