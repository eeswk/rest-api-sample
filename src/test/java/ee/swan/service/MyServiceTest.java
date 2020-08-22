package ee.swan.service;

import ee.swan.config.annotation.MyAnnotation;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyServiceTest {


    @Test
    public void testAnnotation() throws ClassNotFoundException {
        Method[] methods = Class.forName(MyService.class.getName()).getMethods();

        for (int i=0; i<methods.length; i++) {
            if (methods[i].isAnnotationPresent(MyAnnotation.class)) {
                MyAnnotation annotation = methods[i].getAnnotation(MyAnnotation.class);
                System.out.println("my annotation str value:" + annotation.strValue());
                System.out.println("my annotation int value:" +annotation.intValue());
            }
        }

    }

}