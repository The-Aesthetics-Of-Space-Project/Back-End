package com.example.capstone;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.TimeZone;

@SpringBootApplication
public class CapstoneApplication {
	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}

	@PostConstruct
	public void init(){
		// 애플리케이션의 전역 시간대를 UTC로 설정
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
