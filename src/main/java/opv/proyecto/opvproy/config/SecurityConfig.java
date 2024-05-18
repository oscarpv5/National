package opv.proyecto.opvproy.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
    AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers(
                headersConfigurer -> headersConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        http.authorizeHttpRequests(auth -> auth
                        //admin -> usuario, jugador, club, campo, liga, partido
                        //user -> club, campo, liga, partido
                        //publico general -> liga, partido
                .requestMatchers("/", "/liga", "/partido", "/usuario/registro").permitAll()
                .requestMatchers("/club", "/campo").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/club/**", "/campo/**", "/liga/**", "/partido/**",
                                        "/jugadores/**", "/usuario/**", "/h2-console", "/h2-console/**","/api/v1/auth/admin").hasRole("ADMIN")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll() // para rutas: /css, /js /images
                .anyRequest().permitAll())
                        .formLogin(formLogin -> formLogin
                        .defaultSuccessUrl("/", true)
                        .permitAll())
                .logout((logout) -> logout
                        .logoutSuccessUrl("/").permitAll())
                        // .csrf(csrf -> csrf.disable())
                .rememberMe(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        http.exceptionHandling(exceptions -> exceptions.accessDeniedPage("/error/403"));
        return http.build();
    }
}