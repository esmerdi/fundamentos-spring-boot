package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.usecase.GetUsers;
import com.fundamentos.springboot.fundamentos.usecase.GetUserImplement;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUsers getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
