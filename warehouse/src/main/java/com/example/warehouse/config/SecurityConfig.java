package com.example.warehouse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

// Az alkalmazas bejelentkezesi es jogosultsagi beallitasai.
@Configuration
public class SecurityConfig {

    // A HTTP biztonsagi lanc mondja meg, mely oldalak publikusak es melyekhez kell belepes.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        // A login oldal, a CSS fajlok es a H2 konzol bejelentkezes nelkul elerhetok.
                        .requestMatchers("/login", "/css/**", "/h2-console/**").permitAll()
                        // Minden mas oldalhoz hitelesitett felhasznalo kell.
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        // Sajat login oldalt hasznalunk a Spring Security alap oldala helyett.
                        .loginPage("/login")
                        // Sikeres belepes utan mindig a fooldalra kerul a felhasznalo.
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout(logout -> logout
                        // Kijelentkezes utan a login oldal logout uzenetet kap.
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                // A H2 konzol POST kereseihez kikapcsoljuk a CSRF vedelmet, mert fejlesztoi adatbazis-konzol.
                .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
                // A H2 konzol iframe-ben nyilik, ezert same-origin frame engedely kell.
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
                .build();
    }

    // Egyszeru, memoriaban tarolt teszt felhasznalo az alkalmazas kiprobalasahoz.
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN")
                .build());
    }

    // BCrypt hasheli a jelszot, igy a Spring Security nem sima szovegkent hasonlitja.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
