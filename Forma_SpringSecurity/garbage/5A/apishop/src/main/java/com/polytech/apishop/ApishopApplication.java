package com.polytech.apishop;

import com.polytech.apishop.Entities.article;
import com.polytech.apishop.ServiceImpl.articleServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@SpringBootApplication
public class ApishopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApishopApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(articleServiceImpl service){
		return args -> {

			service.addArticle("Pants","zhsfbiudfbjdsfe",20,"L",5,"ddfdd");
			service.addArticle("dkdfd","zhsfbiudfbjdsfe",200,"L",15,"dvddvv");
			service.addArticle("dvdlvee","zhsfbiudfbjdsfe",40,"L",55,"dvd");
			service.addArticle("fe,efe","zhsfbiudfbjdsfe",20,"L",5,"ddfdd");
			service.addArticle("vrvrg","rgrgr",200,"L",15,"dvddvv");
			service.addArticle("rglrg","rgrcd",40,"L",55,"dvd");

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
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}






}
