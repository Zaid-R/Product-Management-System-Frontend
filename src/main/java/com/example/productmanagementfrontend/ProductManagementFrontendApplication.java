package com.example.productmanagementfrontend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import jakarta.faces.webapp.FacesServlet;

@SpringBootApplication
public class ProductManagementFrontendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementFrontendApplication.class, args);
	}

	 @Bean
    public ServletRegistrationBean<FacesServlet> facesServletRegistration() {
        ServletRegistrationBean<FacesServlet> registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
        registration.setLoadOnStartup(1);
        return registration;
    }
}
