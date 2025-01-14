package com.megandemo.springboot_rest_demo;

public class CheckDerbyClasspath {
    public static void main(String[] args) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            System.out.println("Derby driver is in the classpath.");
        } catch (ClassNotFoundException e) {
            System.out.println("Derby driver is NOT in the classpath.");
        }
    }
}