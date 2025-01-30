package com.test.servicea.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.servicea.entity.PrototypeBean;
import com.test.servicea.entity.SingletonBean;

@RestController
public class ScopeController {
    
    @Autowired
    ApplicationContext context;
    

    @GetMapping("/scope")
    public String testScopes() {
    	SingletonBean singletonBean = context.getBean(SingletonBean.class);
    	PrototypeBean prototypeBean = context.getBean(PrototypeBean.class);
        return "Singleton ID: " + singletonBean.getId() + 
               "<br>Prototype ID: " + prototypeBean.getId();
    }
    
}
