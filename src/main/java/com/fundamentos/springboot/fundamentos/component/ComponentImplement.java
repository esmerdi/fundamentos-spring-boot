/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

/**
 *
 * @author sdu02
 */
@Component
public class ComponentImplement implements ComponentDependency {

    @Override
    public void saludar() {
        System.out.println("Hola mundo desde mi componente");
    }
    
}
