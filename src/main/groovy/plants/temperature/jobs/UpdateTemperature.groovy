package plants.temperature.jobs

import java.sql.Timestamp
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import plants.temperature.dao.SampleDao
import plants.temperature.entity.Sample
import plants.temperature.service.Device
import plants.temperature.service.ParticleService



class UpdateTemperature{
	private static Logger logger = LogManager.getLogger(UpdateTemperature.class)
	@Autowired
	private SampleDao sampleDao
	@Autowired
	private Device device
	@Autowired
	private ParticleService particleService

	public void executeUpdate() {
		def sample = new Sample()
		def date = new Date()
		def temperatureValue = particleService.getVariable("temperature",device)
		sample.acquisition = new Timestamp(date.getTime())
		sample.setValue(new BigDecimal(temperatureValue))
		sampleDao.save(sample)
		logger.debug sample
	}
}
