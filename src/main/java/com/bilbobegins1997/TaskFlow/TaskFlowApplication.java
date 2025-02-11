package com.bilbobegins1997.TaskFlow;

import com.bilbobegins1997.TaskFlow.Configuration.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class TaskFlowApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskFlowApplication.class, args);

	}

}
