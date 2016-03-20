package plants.temperature.controller

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import plants.temperature.entity.Sample
import plants.temperature.service.TemperatureService;;;

@RequestMapping("/v1")
@RestController
class Rest {
	private static Logger log = LogManager.getLogger(Rest.class)
	private TemperatureService temperatureService
	
	@Autowired
	public Rest(TemperatureService temperatureService){
		this.temperatureService = temperatureService
	}

	@RequestMapping(value="/samples",method=RequestMethod.GET)
	public List<Sample> allSamples(){
		log.info "Retrieving all data"
		return this.temperatureService.getData()
	}
}
