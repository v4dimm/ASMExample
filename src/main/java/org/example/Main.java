package org.example;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {

//    private static String name;

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);

        try {
            System.out.println("Enter class name (hint: use 'org.example.model.Algorithm'):");
            String name = in.next();
            NewClassLoader cl = new NewClassLoader();
            Class c = cl.findClass(name);
//            Class c = cl.findClass("org.example.model.Algorithm");
            Method m = c.getDeclaredMethod("run");
            Object algorithm = c.newInstance();
            m.invoke(algorithm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
