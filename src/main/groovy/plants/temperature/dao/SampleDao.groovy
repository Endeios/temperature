package plants.temperature.dao

import org.springframework.data.repository.CrudRepository;
import plants.temperature.entity.Sample

interface SampleDao extends CrudRepository<Sample, Long>{
	
}
