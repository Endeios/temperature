package plants.temperature.controller

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import plants.temperature.dao.SampleDao
import plants.temperature.entity.Sample
import plants.temperature.service.Internationalization
import plants.temperature.web.StaticOption;

import java.sql.Timestamp

@Controller
@RequestMapping(value="/")
class Home {

	private static String HOME = "home"

	private static Logger log = LogManager.getLogger(Home.class)

	private final SampleDao sampleDao
	private final Internationalization internationalization

	@Autowired
	public Home(SampleDao sampleDao,Internationalization internationalization){
		Objects.nonNull(sampleDao)
		Objects.nonNull(internationalization)
		this.sampleDao = sampleDao
		this.internationalization = internationalization
	}

	@RequestMapping(value="/")
	public String index(Model model){
		model.addAttribute("samples",sampleDao.findAll())
		model.addAttribute("options",StaticOption.getOptions())
		log.debug "Model completed, returning"
		return HOME
	}
}
