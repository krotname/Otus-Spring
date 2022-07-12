package ru.otus.books.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String API_BOOKS_ALL = "/api/books/all";
    public static final String API_BOOKS = "/api/books/**";

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().authorizeRequests()

                .antMatchers(HttpMethod.GET, API_BOOKS_ALL).permitAll()
                .antMatchers(HttpMethod.GET, API_BOOKS).access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.POST, API_BOOKS).access("hasRole('ADMIN')")
                .antMatchers(HttpMethod.DELETE, API_BOOKS).access("hasRole('ADMIN')")
                .and().formLogin().defaultSuccessUrl("/", false);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return charSequence.toString().equals(s);
            }
        };
    }
}
