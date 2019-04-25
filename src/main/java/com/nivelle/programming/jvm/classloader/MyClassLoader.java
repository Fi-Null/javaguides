package com.nivelle.programming.jvm.classloader;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.Objects;

public class MyClassLoader {


    public static void main(String args[]) {


        ClassLoader selfClassLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) {

                try {
                    String filename = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    System.out.println(filename);

                    InputStream is = getClass().getResourceAsStream(filename);

                    if (is == null) {
                        System.out.println("空的");
                        return super.loadClass(name);
                    }
                    System.out.println("testBean不为空");

                    byte[] b = new byte[is.available()];
                    is.read(b);

                    return defineClass(name, b, 0, b.length);

                } catch (Exception e) {
                    System.out.println(e);
                }
                return null;
            }
        };

        try {
            Object obj = selfClassLoader.loadClass("com.nivelle.programming.jvm.classloader.TestBean").newInstance();

            System.out.println(obj.getClass().getClassLoader().equals(TestBean.class.getClassLoader()));
            //不同类加载器加载进来的类，视为不同类，故不能强转


            System.out.println(obj.getClass().getMethods()[0]);


        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
