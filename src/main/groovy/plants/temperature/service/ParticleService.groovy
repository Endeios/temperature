package plants.temperature.service

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service

import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest

import groovy.transform.Canonical;

@Service
class ParticleService {
	private String accessToken
	private String serverPrefix
	
	
	private static Logger log = LogManager.getLogger(ParticleService.class)

	public ParticleService(String accessToken,String serverPrefix){
		this.accessToken = accessToken
		this.serverPrefix = serverPrefix
	}
	
	public String getVariable(String variableName,Device device){
		HttpResponse<JsonNode> jsonResponse = Unirest.get("${serverPrefix}${device.id}/${variableName}")
		.header("accept", "application/json")
		.queryString("access_token", accessToken)
		.asJson();
		
		def data = jsonResponse.getBody().getObject()
		return data.get("result")
	}

	public List<Device> getDevices(){
		HttpResponse<JsonNode> jsonResponse = Unirest.get(serverPrefix)
				.header("accept", "application/json")
				.queryString("access_token", accessToken)
				.asJson();
		JsonNode data = jsonResponse.getBody()
		List<Device> returnDevices = new ArrayList<Device>()
		def devices = data.getArray()
		for(int i=0;i<devices.length();i++){
			def device_map = devices.get(i)
			def device = new Device(device_map.get("name"),device_map.get("id"))
			log.debug device
			returnDevices.add(device)
		}
		returnDevices
	}
	
	public Device getDeviceByName(String name){ 
		getDevices().find{el->el.name==name}
	}
}

@Canonical
class Device{
	String name
	String id
}
