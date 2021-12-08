package com.zoran.demo.actuator;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Endpoint(id="mojEndpoint")  //tako će se zvat /mojEndpoint
@Component
public class CustomActuatorEndpoint {

	
	@ReadOperation
	public Object myCustomEndpoint() {
		
		Map<String, String> map = new HashMap<>();
		map.put("key", "freaking value");
		return map;
	}
	
	
}
