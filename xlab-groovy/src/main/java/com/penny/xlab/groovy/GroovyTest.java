package com.penny.xlab.groovy;

import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * @author 0-Vector
 * @date 2019/5/9 11:16
 */
@Component
public class GroovyTest {
    // commonpath + api_name +
//    static String root[] = new String[]{"xlab-groovy/src/main/resources/bin/groovy/"};
    static String root[] = new String[]{"http://192.168.68.45:8080/"};
    static GroovyScriptEngine groovyScriptEngine;

    static {
        try {
            groovyScriptEngine = new GroovyScriptEngine(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    public boolean testInvoke() {
        try {
            Person person = new Person("wchi", "nanjing", 30);
            Class scriptClass = groovyScriptEngine.loadScriptByName("hello.groovy");

            GroovyObject scriptInstance = (GroovyObject)scriptClass.newInstance();
            Object ret = scriptInstance.invokeMethod("helloWithParam", new Object[]{person,"lxi"});
            System.out.println("testGroovy3:" + ret);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception e="+e.toString());
        }
        return true;
    }
}
