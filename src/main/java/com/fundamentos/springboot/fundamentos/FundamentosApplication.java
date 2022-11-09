package com.fundamentos.springboot.fundamentos;

import com.fundamentos.springboot.fundamentos.component.ComponentDependency;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner{
    
    private final ComponentDependency componentDependency;
    
    public FundamentosApplication(@Qualifier("componentTwoImplement") ComponentDependency componentDependency){
        this.componentDependency = componentDependency;
    }
    
    public static void main(String[] args) {
            SpringApplication.run(FundamentosApplication.class, args);
    }

    @Override
    public void run(String... args) {
        componentDependency.saludar();
    }

}