package com.avinash.ProjectDEMO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

public class SecurityClass extends WebSecurityConfigurerAdapter {

@Autowired
    private MyUserDetailsService MyUser;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(MyUser);
    }
}
