package by.it.academy.scientificactivity.configuration;

import by.it.academy.scientificactivity.security.MySimpleUrlAuthenticationSuccessHandler;
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

    private final MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler;

    private final UserDetailsServiceImpl userDetailsService;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, MySimpleUrlAuthenticationSuccessHandler mySimpleUrlAuthenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.mySimpleUrlAuthenticationSuccessHandler = mySimpleUrlAuthenticationSuccessHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/403.html", "/40**").permitAll()
                .antMatchers(HttpMethod.GET, "/employees").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/employees/**/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/publications/**/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/employees/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/employees/**/publications/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/").hasAnyRole("ADMIN", "USER")

                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/403.html")
                .and()
                .formLogin()
                .successHandler(mySimpleUrlAuthenticationSuccessHandler);
    }
}
