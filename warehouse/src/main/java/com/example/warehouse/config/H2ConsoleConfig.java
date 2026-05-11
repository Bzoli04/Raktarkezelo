package com.example.warehouse.config;

import org.h2.server.web.JakartaWebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Kulon konfiguracio a bongeszobol elerheto H2 adatbazis konzolhoz.
@Configuration
public class H2ConsoleConfig {

    // Regisztralja a H2 konzol servletet a /h2-console/* utvonalra.
    @Bean
    public ServletRegistrationBean<JakartaWebServlet> h2ConsoleServletRegistration() {
        // A ServletRegistrationBean mondja meg, milyen servlet milyen URL mintan fusson.
        ServletRegistrationBean<JakartaWebServlet> registration =
                new ServletRegistrationBean<>(new JakartaWebServlet(), "/h2-console/*");
        // Indulaskor rogton betolti a servletet, nem csak az elso keresnel.
        registration.setLoadOnStartup(1);
        return registration;
    }
}
