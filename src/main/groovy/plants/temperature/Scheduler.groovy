package plants.temperature

import org.quartz.JobDetail
import org.quartz.SchedulerFactory
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import plants.temperature.jobs.UpdateTemperature
import plants.temperature.service.TemperatureService;

import org.quartz.CronTrigger

import static org.quartz.JobBuilder.*
import static org.quartz.TriggerBuilder.*
import static org.quartz.CronScheduleBuilder.*


//@Configuration
class Scheduler {
	@Bean
	public org.quartz.Scheduler schedulerForPlants(TemperatureService temperatureService){
		SchedulerFactory factory = new StdSchedulerFactory()
		org.quartz.Scheduler myscheduler = factory.getScheduler()
		
		JobDetail job = newJob(UpdateTemperature.class)
										.withIdentity("Update temperature","Core")
										.usingJobData("temperatureService",temperatureService)
										.build()
										
		CronTrigger trigger = newTrigger()
										.withIdentity("Update temperature","Core")
										.withSchedule(cronSchedule("* * * * * ?"))
										.build()
		myscheduler.scheduleJob(job,trigger)
		myscheduler.start()
		return myscheduler
	}
	
}
