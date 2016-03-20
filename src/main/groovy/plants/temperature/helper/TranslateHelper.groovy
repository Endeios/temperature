package plants.temperature.helper

import java.io.IOException;

import com.github.jknack.handlebars.Helper
import com.github.jknack.handlebars.Options

import plants.temperature.service.Internationalization;;;

class TranslateHelper implements Helper<String>{

	private Internationalization internationalization
	public static final String NAME = "tr"

	public TranslateHelper(Internationalization internationalization){
		this.internationalization = internationalization
	}

	@Override
	public CharSequence apply(String context, Options options) throws IOException {
		return this.internationalization.translate(context,options.params)
	}
}
