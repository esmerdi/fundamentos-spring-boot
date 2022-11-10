package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfigurationBean {
    //Interfaces que devuelven métodos que fueron implementados
    @Bean
    public MyBean beanOperation(){
        return new MyBean2Implement();
    }

    @Bean
    public MyOperation myBeanOperation() {
        return new MyOperationImplement();
    }

    @Bean
    public MyBeanWithDependency myBeanOperationWithDependency(MyOperation myOperation) {
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
