package de.baerb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpatialserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpatialserviceApplication.class, args);
	}
	
    
    @Bean
    CommandLineRunner init(AddressRepository repo) {
        return (String... strings) -> 
        {
			GeometryFactory factory = new GeometryFactory();
		            
		    Address adr1 = new Address();
			adr1.setStreet("Dingolfingerstr.");
			adr1.setNumber(11);
			adr1.setLocation(factory.createPoint(new Coordinate(11.615049,48.132873)));
			adr1.getLocation().setSRID(4326);
			repo.save(adr1);
			
		    Address adr2 = new Address();
			adr2.setStreet("Dingolfingerstr.");
			adr2.setNumber(9);
			adr2.setLocation(factory.createPoint(new Coordinate(11.614490,48.132632)));
			adr2.getLocation().setSRID(4326);
			repo.save(adr2);
			
		    Address adr3 = new Address();
			adr3.setStreet("Dingolfingerstr.");
			adr3.setNumber(7);
			adr3.setLocation(factory.createPoint(new Coordinate(11.614051, 48.132444)));
			adr3.getLocation().setSRID(4326);
			repo.save(adr3);
			
		    Address adr4 = new Address();
			adr4.setStreet("Dingolfingerstr.");
			adr4.setNumber(5);
			adr4.setLocation(factory.createPoint(new Coordinate(11.613666, 48.132249)));
			adr4.getLocation().setSRID(4326);
			repo.save(adr4);
		
			// Read all addresses
			List<Address> listadr;
			listadr = repo.findAll();
			System.out.println("Number of saved addresses: "+listadr.size());        
		};
    }
    
    // do not use in production!!!!
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Collections.singletonList("*"));
        config.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "OPTIONS", "DELETE", "PATCH"));
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
