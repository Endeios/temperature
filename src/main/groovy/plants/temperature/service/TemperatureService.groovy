package plants.temperature.service

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service

import plants.temperature.Temperature;
import plants.temperature.dao.SampleDao

@Service
class TemperatureService {
	
	private SampleDao sampleDao
	@Autowired
	public TemperatureService(SampleDao sampleDao){
		this.sampleDao = sampleDao
	}
	
	public List<Temperature> getData(){
		return sampleDao.findAll()
	}
}
