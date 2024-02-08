package com.example.deleonperezisefeneyenmyikea.Security;

import com.example.deleonperezisefeneyenmyikea.Services.UserService;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class BasicConf
{
    private final UserService  userService;
    public BasicConf(UserService userService)
    {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().accessDeniedPage("/permission-denied").and()
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**").permitAll() // Ignora recursos estáticos
                        .requestMatchers("/").permitAll()
                        .anyRequest().authenticated()

                )
                .formLogin(login -> login
                        .loginPage("/login") // Página de inicio de sesión personalizada
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL de cierre de sesión personalizada
                        .permitAll()
                )
                .userDetailsService(userService); // Especificar el servicio de usuario personalizado
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
