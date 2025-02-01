package br.edu.br.meuprimeirospringboot.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/css/**", "/js/**", "/image/**", "/webjars/**").permitAll() // Libera acesso para a URL "/"
                .requestMatchers("/alunos/cadastrar").hasRole("ADMIN") // Apenas ADMIN pode acessar
                .anyRequest().authenticated()    
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")                     
                    .logoutSuccessUrl("/login?logout=true")  
                    .invalidateHttpSession(true)             
                    .deleteCookies("JSESSIONID")             
                )
            .formLogin(form -> form          
                .loginPage("/login")         
                .permitAll()                 
            )
            .httpBasic(httpBasic -> httpBasic.disable()); 
        return http.build();
    }
	
	@Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user1 = User.builder()
            .username("admin")
            .password(passwordEncoder().encode("admin123"))
            .roles("ADMIN")
            .build();
        UserDetails user2 = User.builder()
            .username("user")
            .password(passwordEncoder().encode("user123"))
            .roles("USER") 
            .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); 
    }
}