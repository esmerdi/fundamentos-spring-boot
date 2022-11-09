package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwoImplement implements ComponentDependency{

    @Override
    public void saludar() {
        System.out.println("Hello word desde mi componente 2");
    }
}
