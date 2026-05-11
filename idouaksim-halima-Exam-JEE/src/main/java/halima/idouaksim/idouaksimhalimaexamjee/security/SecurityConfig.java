package halima.idouaksim.idouaksimhalimaexamjee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // ================= PASSWORD ENCODER =================
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ================= IN MEMORY USERS =================
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {

        return new InMemoryUserDetailsManager(

                User.builder()
                        .username("client")
                        .password(encoder.encode("1234"))
                        .roles("CLIENT")
                        .build(),

                User.builder()
                        .username("employe")
                        .password(encoder.encode("1234"))
                        .roles("EMPLOYE")
                        .build(),

                User.builder()
                        .username("admin")
                        .password(encoder.encode("1234"))
                        .roles("ADMIN")
                        .build()
        );
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    // ================= SECURITY FILTER =================
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth

                        // PUBLIC (ADD SWAGGER HERE)
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/v3/api-docs",
                                "/api-docs/**"
                        ).permitAll()

                        // CLIENT
                        .requestMatchers("/api/clients/**")
                        .hasAnyRole("ADMIN", "EMPLOYE", "CLIENT")

                        // CONTRATS
                        .requestMatchers("/api/contrats/**")
                        .hasAnyRole("ADMIN", "EMPLOYE", "CLIENT")

                        // PAIEMENTS
                        .requestMatchers("/api/paiements/**")
                        .hasAnyRole("ADMIN", "EMPLOYE", "CLIENT")

                        // ADMIN ONLY
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                )

                .headers(headers -> headers.frameOptions(f -> f.sameOrigin()))

                .csrf(csrf -> csrf.ignoringRequestMatchers(
                        "/h2-console/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ));

        return http.build();
    }
}