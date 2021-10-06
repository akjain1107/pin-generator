package com.docom.pingenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PinGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinGeneratorApplication.class, args);
//		System.out.println("Hi");
//		new MyJob();
	}
//	@Bean(initMethod = "start", destroyMethod = "stop")
//	public Server h2Server2() throws SQLException {
//		System.out.println("h2 server");
//		return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
//	}
}
