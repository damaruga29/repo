package com.test.servicea.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.test.servicea.entity.Dog;

@Component
public class PostAndPreProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        // This runs before Spring initializes the beans.
        if (bean instanceof Dog) {
            Dog dog = (Dog) bean;
            dog.setName("Buddy"); // Set default name
            System.out.println("Before Initialization: Setting name to 'Buddy'");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        // This runs after Spring initializes the beans.
        if (bean instanceof Dog) {
            System.out.println("After Initialization: " + bean);
        }
        return bean;
    }
}
