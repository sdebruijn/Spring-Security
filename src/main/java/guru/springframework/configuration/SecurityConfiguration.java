package guru.springframework.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
        	.authorizeRequests()
        		.antMatchers("/").permitAll()
        		.and()
            .authorizeRequests()
            	.antMatchers("/console/**").permitAll()
            	.and()
            .authorizeRequests()
            	.antMatchers("/product/new").access("hasRole('ROLE_ADMIN')")
            	.and()
            .formLogin()
            	.usernameParameter("username")
                .passwordParameter("password");
//                .and()
//            .logout()
//				.logoutSuccessUrl("/login?logout").permitAll()
//				.and()
//			.exceptionHandling().accessDeniedPage("/403");

        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
    }
//    
//	@Bean(name = "passwordEncoder")
//	public PasswordEncoder passwordencoder() {
//		return new BCryptPasswordEncoder();
//	}

}