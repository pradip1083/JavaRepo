package com.example.notes.Notes;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication//(exclude= {DataSourceAutoConfiguration.class})
@EnableJpaAuditing
public class NotesApplication {
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(NotesApplication.class, args);
		
		
		
	}

}
