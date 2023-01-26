package com.security.forma_security;

import com.security.forma_security.Model.AppUser;
import com.security.forma_security.Model.ERole;
import com.security.forma_security.Model.Role;
import com.security.forma_security.Service.UserService.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class FormaSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(FormaSecurityApplication.class, args);
	}


	@Bean
	CommandLineRunner run(UserService userService) {
		return  args -> {
			userService.saveRole(new Role( ERole.ROLE_ADMIN));
			userService.saveRole(new Role(ERole.ROLE_USER));
//
//
			userService.saveUser(new AppUser(null,"Wilfried SANON","will","will@gmail.com", "1234",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"AZAER","test","azaer@gmail.com","1234",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"SANON","able","Sanon@gmail.com","1234",new ArrayList<>()));
			userService.saveUser(new AppUser(null,"Wilfried","well","user@gmail.com","1234",new ArrayList<>()));

			userService.addRoleToUser("will",ERole.ROLE_ADMIN.name());
			userService.addRoleToUser("test",ERole.ROLE_ADMIN.name());
			userService.addRoleToUser("able",ERole.ROLE_USER.name());
			userService.addRoleToUser("well",ERole.ROLE_USER.name());
		};
	}


	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		//corsConfiguration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200","http://13.37.112.147","http://www.tosucceed.site" ,"http://tosucceed.site"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers", "application/x-www-form-urlencoded"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}


	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}




}
