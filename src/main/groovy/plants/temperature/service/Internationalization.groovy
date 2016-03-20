package plants.temperature.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.support.RequestContext

@Service
class Internationalization {
	private MessageSource messageSource
	private LocaleResolver localeResolver
	@Autowired
	public Internationalization(MessageSource messageSource,LocaleResolver localeResolver){
		this.messageSource = messageSource
		this.localeResolver= localeResolver
	}

	public String translate(String placeHolder,Object[] args){
		messageSource.getMessage(placeHolder, args, placeHolder, new RequestContext().getLocale())
	}
	public String translate(String placeHolder){
		translate(placeHolder,new Object())
	}
}
