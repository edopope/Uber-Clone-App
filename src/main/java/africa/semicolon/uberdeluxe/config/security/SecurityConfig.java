package africa.semicolon.uberdeluxe.config.security;


import africa.semicolon.uberdeluxe.config.security.filters.UberDeluxeAuthenticationFilter;
import africa.semicolon.uberdeluxe.config.security.manager.UberDeluxeAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final AuthenticationManager authenticationManager;
    private final String[] AUTHENTICATION_WHITE_LIST = {"/api/v1/driver/register", "/api/v1/passenger", "/api/v1/login"};
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        UsernamePasswordAuthenticationFilter authenticationFilter = new UberDeluxeAuthenticationFilter(authenticationManager);
        authenticationFilter.setFilterProcessesUrl("/api/v1/login");
        return http.csrf().disable().cors().and()
                .addFilterAt(authenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST, AUTHENTICATION_WHITE_LIST)
                .permitAll()
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .build();
    }


}
