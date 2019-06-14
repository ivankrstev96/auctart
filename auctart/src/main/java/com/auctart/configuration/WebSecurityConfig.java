package com.auctart.configuration;

import com.auctart.configuration.jwt.JwtProperties;
import com.auctart.configuration.jwt.JwtTokenAuthenticationFilter;
import com.auctart.configuration.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtProperties jwtProperties;

    WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JwtProperties jwtProperties) {
        this.userDetailsService = userDetailsService;
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, exception) -> {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                })
                .and()
                .addFilter(
                        new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtProperties))
                .addFilterAfter(
                        new JwtTokenAuthenticationFilter(jwtProperties, userDetailsService),
                        UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, jwtProperties.getUri())
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .antMatchers(getPublicPaths())
                .permitAll()
                .anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String[] getPublicPaths() {
        return new String[]{
                "/api/image/public/**",
                "/api/user/register"
        };
    }
}
