package com.yidatec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

/**
 * @author QuShengWen
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailService myUserDetailService;

    @Autowired
    private MyInvocationSecurityMetadataSource myInvocationSecurityMetadataSource;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/skin-config.html","/validateUserName","/validatePassword",
                        "/validateMobile","/validateInteger","/validateFloat","/validateEmail",
                        "/validateRoleName").permitAll();
        http.addFilterAt(myUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"))
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/").deleteCookies("remember-me") .invalidateHttpSession(true).permitAll();
        // 开启默认登录页面
        http
                .authorizeRequests().anyRequest().authenticated()
                .withObjectPostProcessor(
                new ObjectPostProcessor<FilterSecurityInterceptor>() {
            public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                fsi.setSecurityMetadataSource(myInvocationSecurityMetadataSource);
                fsi.setAccessDecisionManager(myAccessDecisionManager);
//                fsi.setAuthenticationManager(authenticationManager());
                return fsi;
            }
        });
        // 自定义accessDecisionManager访问控制器,并开启表达式语言
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler());
//        // 自定义登录页面
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/99/**","/**/*.js", "/**/favicon.ico","/img/**","/upload/**");
    }

    @Bean(name = "usernamePasswordAuthenticationFilter")
    UsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter() {
        UsernamePasswordAuthenticationFilter myUsernamePasswordAuthenticationFilter = new UsernamePasswordAuthenticationFilter();
        myUsernamePasswordAuthenticationFilter.setPostOnly(true);
        myUsernamePasswordAuthenticationFilter.setAuthenticationManager(authenticationManager());
        myUsernamePasswordAuthenticationFilter.setUsernameParameter("username");
        myUsernamePasswordAuthenticationFilter.setPasswordParameter("password");
        myUsernamePasswordAuthenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        myUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(simpleUrlAuthenticationSuccessHandler());
        myUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(simpleJsonAuthenticationFailureHandler());
        return myUsernamePasswordAuthenticationFilter;
    }

    @Bean
    AccessDeniedHandler accessDeniedHandler() {
//        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
//        accessDeniedHandler.setErrorPage("/securityException/accessDenied");
//        return accessDeniedHandler;
        return new MyAccessDeniedHandler();
    }

    @Bean
    public LoggerListener loggerListener() {
//        System.out.println("org.springframework.security.authentication.event.LoggerListener");
        return new LoggerListener();
    }

    @Bean
    public org.springframework.security.access.event.LoggerListener eventLoggerListener() {
        return new org.springframework.security.access.event.LoggerListener();
    }

    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManager() {
        AuthenticationProvider provider = getAuthenticationProvider();
        List<AuthenticationProvider> lp = new ArrayList<AuthenticationProvider>(1);
        lp.add(provider);
        return new ProviderManager(lp);
    }


    @Bean(name= "authenticationProvider")
    public AuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(myUserDetailService);
        return provider;
    }

    @Bean
    public Md5PasswordEncoder passwordEncoder()
    {
        return new Md5PasswordEncoder();
    }

    @Bean(name = "successHandler")
    public SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler() {
        return new SimpleUrlAuthenticationSuccessHandler("/login");
    }

    @Bean(name = "failureHandler")
    public MyAuthenticationFailureHandler simpleJsonAuthenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
    }
}