package plants.temperature.helper

import java.io.IOException

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options

import plants.temperature.service.Internationalization;;;

class TranslateHelper implements Helper<String>{

	private Internationalization internationalization
	public static final String NAME = "tr"
	private static Logger log = LogManager.getLogger(TranslateHelper.class)

	public TranslateHelper(Internationalization internationalization){
		this.internationalization = internationalization
	}

	@Override
	public CharSequence apply(String context, Options options) throws IOException {
		String returnValue =this.internationalization.translate(context,options.params)
		log.debug "Translating $context in $returnValue"
		return returnValue
	}
}
