package plants.temperature


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.ViewResolver;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver;

import org.apache.logging.log4j.Logger
import org.apache.tomcat.jdbc.pool.DataSourceFactory
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.sql.DataSource

import org.apache.logging.log4j.LogManager;

@SpringBootApplication
@PropertySource("classpath:/conf/temperature.properties")
@ImportResource("classpath:/conf/database.xml")
class Temperature {

	private static Logger log = LogManager.getLogger(Temperature.class)

	@Bean
	public ViewResolver getModelViewController(){
		HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
		viewResolver.setPrefix("classpath:/handlebars/")
		return viewResolver
	}

	static main(args) {

		ApplicationContext ctx = SpringApplication.run(Temperature.class, args);

		log.debug "Loaded Beans"

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			log.debug  beanName
		}
	}
}
