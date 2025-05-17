package jp.co.yamaha_motor.gimac.tools;

import com.ymsl.solid.jpa.repository.JpaExtensionRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author YMSLX
 * @version 1.0
 */
@SpringBootApplication
@EntityScan(basePackageClasses = {TrainingApplication.class})
@EnableJpaRepositories(basePackageClasses = {TrainingApplication.class}, repositoryFactoryBeanClass = JpaExtensionRepositoryFactoryBean.class)
public class TrainingApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TrainingApplication.class);
        app.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(TrainingApplication.class);
    }
}
