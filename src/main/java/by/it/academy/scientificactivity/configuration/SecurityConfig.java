package by.it.academy.scientificactivity.configuration;

import by.it.academy.scientificactivity.service.impl.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private final UserDetailsServiceImpl userDetailsService;

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService
//            , BCryptPasswordEncoder bCryptPasswordEncoder
            ) {
        this.userDetailsService = userDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }




//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("user").password("{noop}pass").roles("USER")
//                .and()
//                .withUser("admin").password("{noop}pass").roles("USER", "ADMIN");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/employees").hasRole("USER")
//                .antMatchers(HttpMethod.GET, "/employees/**/delete").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/employees/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/employees/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
////                .antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers(HttpMethod.GET, "/employees").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/employees/**/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/employees/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/employees/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/employees/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/employees/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN")
//                .antMatchers("/api/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin();
    }
}
