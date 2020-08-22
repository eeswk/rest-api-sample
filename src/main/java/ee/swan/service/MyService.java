package ee.swan.service;

import ee.swan.config.annotation.MyAnnotation;


public class MyService {

    @MyAnnotation(strValue = "hello", intValue = 0001)
    public void service() {
        System.out.println("my service!");
    }
}
