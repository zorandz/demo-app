package com.zoran.demo.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class DatabaseService implements HealthIndicator {

	@Override
	public Health health() {
		if (isDatabaseServiceUp()) {
			return Health.up().withDetail("Database Service", "Service is running").build();
		}
		return Health.down().withDetail("Database Service", "Service is down").build();
	}
	
	private boolean isDatabaseServiceUp() {
		return true;
	}
	
	

}
