package jp.co.yamaha_motor.gimac.tools.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ymsl.solid.jpa.repository.JpaExtensionRepositoryFactoryBean;
import com.ymsl.solid.websocket.config.WebSocketConfiguration;

import jp.co.yamaha_motor.gimac.tools.PJRedisConfiguration;
import jp.co.yamaha_motor.gimac.tools.TrainingApplication;

/**
 * Custom spring boot start class.
 * Different from {@link TrainingApplication}, this class is used for test.
 * So unnecessary modules can be excluded by using specific profile,
 * scanning only the specific packages, or excluding the unnecessary configurations.
 * The package path of this class is different from the original one,
 * so it would not be scanned by spring boot automatically, and special functions can be activated.
 */
@SpringBootApplication
@Import({TestSecurityConfiguration.class, PJRedisConfiguration.class})
@Profile("development-test")
@ComponentScan(basePackages = {"jp.co.yamaha_motor.gimac.tools.test", "jp.co.yamaha_motor.gimac.tools"})
@EnableJpaRepositories(basePackages = "jp.co.yamaha_motor.gimac.tools.repository", repositoryFactoryBeanClass = JpaExtensionRepositoryFactoryBean.class)
@EnableAutoConfiguration(exclude = {WebSocketConfiguration.class})
public class TraningTestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TrainingApplication.class);
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TrainingApplication.class);
    }
}
