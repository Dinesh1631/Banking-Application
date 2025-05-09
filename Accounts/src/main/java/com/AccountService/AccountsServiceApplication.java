package com.AccountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableJpaRepositories(basePackages="com.AccountService.Repository")
@EnableJpaAuditing(auditorAwareRef="auditimpl")
@OpenAPIDefinition(
		info = @Info(title="This is DineshBank AccountService",
		             version="V1",
		             description="This application contains the all the implentation of dineshbank account service" ,
		             contact=@Contact(
		            		 name="Dinesh",
		            		 email="DineshGolla@gmail.com"	 
		            		 ),
		
		             license = @License(
		            		 name ="Apache 2.0",
		            		 url ="chat.com"
		            		 )
				),
		 externalDocs = @ExternalDocumentation(
				 url ="chat.com"
      		   )
		
		
		
		)
@EnableFeignClients
public class AccountsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsServiceApplication.class, args);
	}

}


