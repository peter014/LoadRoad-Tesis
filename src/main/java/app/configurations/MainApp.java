package app.configurations;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan({ "app.controllers","app.dto","app.interfaces","app.services","app.configurations" })
@EnableJpaRepositories("app.repository")
@EntityScan("app.entities")
public class MainApp {

	public static void main(String[] args) {
		 SpringApplication.run(MainApp.class, args);
	}

}
