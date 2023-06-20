package com.managementemployee.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
public class AdminApplication   {


	public static void main(String[] args) {


//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasename("messages");
//		messageSource.setDefaultEncoding("UTF-8");
//		System.out.println(messageSource.getMessage("hello", null, Locale.ENGLISH));

		SpringApplication.run(AdminApplication.class, args);
	}


}
