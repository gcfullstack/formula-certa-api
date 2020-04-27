package corp.gruposfa.novo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
    	http.headers().frameOptions().disable().and().cors().and().csrf().disable().authorizeRequests()
	    .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
	    .antMatchers(HttpMethod.GET, SecurityConstants.PUBLIC_URL).permitAll()
	    .antMatchers(HttpMethod.POST, SecurityConstants.PUBLIC_URL).permitAll()
	    .antMatchers("/h2-console/**").permitAll()
	    .antMatchers("/favicon.ico").permitAll()
	    .anyRequest().authenticated().and()
	    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
	    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}


	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration().applyPermitDefaultValues();
		config.addAllowedMethod(HttpMethod.DELETE);
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}