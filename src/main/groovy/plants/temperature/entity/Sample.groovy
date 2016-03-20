package plants.temperature.entity


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id
import javax.persistence.PersistenceUnit;
import javax.persistence.Table;

import groovy.transform.Canonical;;

@Canonical
@Entity
@PersistenceUnit(name="temperature")
@Table(name="sample")
class Sample {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id

	Timestamp acquisition;
	BigDecimal value;
}
