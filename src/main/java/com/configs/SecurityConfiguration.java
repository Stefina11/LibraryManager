package com.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration(
        JwtAuthenticationFilter jwtAuthenticationFilter,
        AuthenticationProvider authenticationProvider
    ) {
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    	
//        http
//            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//            .csrf(csrf -> csrf.disable())
//            .formLogin(formLogin -> formLogin.disable())
//            .httpBasic(httpBasic -> httpBasic.disable())
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers("/auth/**").permitAll()
//                .requestMatchers("/auth/signup").permitAll()
//                .anyRequest().authenticated()
//            )
//            .sessionManagement(session -> session
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            )
//          .authenticationProvider(authenticationProvider)
//            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable);


        httpSecurity.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/auth/**").permitAll());

//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/utente").hasAuthority(Ruolo.ADMIN.name()));
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/utente/delete/**").hasAuthority(Ruolo.ADMIN.name()));
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/utente/**").permitAll());
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET,"/insegnante/**").permitAll());
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/insegnante/**").hasAuthority(Ruolo.ADMIN.name()));
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/turno/**").permitAll());
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET,"/corso/**").permitAll());
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/corso/**").hasAuthority(Ruolo.ADMIN.name()));
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/prenotazione/**").permitAll());
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers(HttpMethod.GET,"/abbonamento/**").permitAll());
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/abbonamento/**").hasAuthority(Ruolo.ADMIN.name()));
//        httpSecurity.authorizeHttpRequests(request -> request.requestMatchers("/**").denyAll());


        return httpSecurity.build();
    }

    

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:8081"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
