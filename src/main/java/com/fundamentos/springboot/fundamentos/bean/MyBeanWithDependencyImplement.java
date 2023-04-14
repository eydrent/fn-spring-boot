package com.fundamentos.springboot.fundamentos.bean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependencyImplement implements MyBeanWithDependency{

    Log LOGGER = LogFactory.getLog(MyBeanWithDependencyImplement.class);
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    public void printWithDependency() {
        LOGGER.info("Hola desde la implementación de un bean con dependencia");
        int number = 1;
        LOGGER.debug("El número enviado a la operación es: " + number);
        System.out.println(myOperation.sum(number));
        System.out.println("Hola desde la implementación de un bean con dependencia");
    }
}
