package org.groory;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

import java.util.HashSet;

public class InvokeGroovy {

    public static void main(String[] args) {
        ClassLoader cl = new InvokeGroovy().getClass().getClassLoader();
        GroovyClassLoader groovyCl = new GroovyClassLoader(cl);
        try
        {

            String script = "class Foo  \n" +
                    "{  \n" +
                    "    public boolean run(int foo)  \n" +
                    "    {  \n" +
                    "       \n" +
                    "       return 3+2 >  foo\n" +
                    "    }  \n" +
                    "}  " ;

            Class groovyClass = groovyCl.parseClass(script);//这个返回true
            GroovyObject clazzObj = null;
            try {
                clazzObj = (GroovyObject)groovyClass.newInstance();
            } catch (Exception e) {
                 e.printStackTrace();
            }
            System.out.println(clazzObj.invokeMethod("run", 1));


        } catch (Exception e) {
            e.printStackTrace();
        }
        HashSet
    }
}
