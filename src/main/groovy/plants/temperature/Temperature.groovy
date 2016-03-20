package plants.temperature


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.ImportResource
import org.springframework.context.annotation.PropertySource
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import org.springframework.web.servlet.ViewResolver;

import com.github.jknack.handlebars.springmvc.HandlebarsViewResolver

import plants.temperature.helper.TranslateHelper;
import plants.temperature.service.Internationalization;

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
	public ViewResolver viewResolver(){
		HandlebarsViewResolver viewResolver = new HandlebarsViewResolver();
		viewResolver.registerHelper(TranslateHelper.NAME,new TranslateHelper(internationalization()))
		viewResolver.setPrefix("classpath:/handlebars/")
		return viewResolver
	}

	@Bean
	public MessageSource messageSource(){
		ReloadableResourceBundleMessageSource messages = new ReloadableResourceBundleMessageSource()
		messages.setBasename("classpath:/internationalization/messages")
		messages.setDefaultEncoding("UTF-8")
		return messages
	}

	@Bean
	public LocaleResolver localeResolver(){
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en_US"));
		resolver.setCookieName("locale4temperature");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}
	
	@Bean
	public Internationalization internationalization(){
		return new Internationalization(messageSource(),localeResolver())
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
