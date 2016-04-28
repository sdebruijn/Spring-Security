package nl.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
            	.antMatchers("/console/**").access("hasRole('ROLE_ADMIN')")
            	.and()
            .authorizeRequests()
            	.antMatchers("/product/new").access("hasRole('ROLE_ADMIN')")
            	.and()
            .authorizeRequests()
            	.antMatchers("/admin").access("hasRole('ROLE_ADMIN')")
            	.antMatchers("/user").access("hasRole('ROLE_USER')")
            	.and()
            .authorizeRequests()
        		.anyRequest().permitAll()
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
    
//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.setTemplateResolver(templateResolver());
//        templateEngine.addDialect(new TilesDialect());
//        templateEngine.addDialect(new SpringSecurityDialect());
//        return templateEngine;
//    }

//	@Bean(name = "passwordEncoder")
//	public PasswordEncoder passwordencoder() {
//		return new BCryptPasswordEncoder();
//	}

}