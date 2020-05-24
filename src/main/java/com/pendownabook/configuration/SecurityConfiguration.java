package com.pendownabook.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.pendownabook.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserService userService;   
    
    @Autowired
	private AuthenticationSuccessHandler successHandler;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        		.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/registration**", "/js/**","/css/**","/images/**").permitAll()
                    .antMatchers("/webjars/**").permitAll()
                    .antMatchers("/").permitAll()
                    .antMatchers("/home/**").hasAuthority("USER")
                    .antMatchers("/admin/**").hasAuthority("ADMIN")
                    .antMatchers("/publisher/**").hasAuthority("PUBLISHER")
                    .antMatchers(HttpMethod.GET, "/payment/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/payment/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/book/**").permitAll()                    
                    .antMatchers(HttpMethod.POST, "/book/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/service/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/previewbook/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/previewbook/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/profile/**").permitAll()
                    .antMatchers(HttpMethod.GET, "/genre/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/subscription/**").permitAll()
                    .antMatchers(HttpMethod.GET,"/reviewstatus/**").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .successHandler(successHandler)
                        .failureUrl("/login?error")
                        .permitAll()
                .and()
                    .logout()
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))                        
                        .logoutSuccessUrl("/login?logout")
                        .deleteCookies("JSESSIONID")
                .permitAll();           
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }

}