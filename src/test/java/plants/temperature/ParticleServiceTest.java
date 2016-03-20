package plants.temperature;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import plants.temperature.service.Device;
import plants.temperature.service.ParticleService;

@Test
public class ParticleServiceTest {

	private static final String	accessToken		= "80d2240a2b0f31fb23e8bf0a116e5826e70c1ecd";
	private static final String	deviceName		= "doctor_who";
	private static final String	serverPrefix	= "https://api.particle.io/v1/devices/";
	private ParticleService			particleService;

	@BeforeMethod
	public void before() {

		particleService = new ParticleService(accessToken, serverPrefix);
	}

	@Test
	public void testDevice() {

		System.out.println(particleService.getDevices());
	}
	
	@Test
	public void testDocsTemperature() {
		Device device = particleService.getDeviceByName(deviceName);
		System.out.println(device);
		System.out.println(particleService.getVariable("temperature", device));
	}
}