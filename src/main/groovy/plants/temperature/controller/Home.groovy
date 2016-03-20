package plants.temperature.controller

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

import plants.temperature.dao.SampleDao
import plants.temperature.entity.Sample

import java.sql.Timestamp

@Controller
@RequestMapping(value="/")
class Home {

	private static String HOME = "home"

	private static Logger log = LogManager.getLogger(Home.class)

	private final SampleDao sampleDao

	@Autowired
	public Home(SampleDao sampleDao){
		Objects.nonNull(sampleDao)
		this.sampleDao = sampleDao
	}

	@RequestMapping(value="/")
	public String index(Model model){
		model.addAttribute("title","Temperature")
		def sample = new Sample()
		def date = new Date()
		sample.acquisition = new Timestamp(date.getTime())
		sample.setValue(new BigDecimal("25.40"))
		sampleDao.save(sample)
		log.debug sample
		model.addAttribute("samples",sampleDao.findAll())
		log.debug "Model completed, returning"
		return HOME
	}
}
