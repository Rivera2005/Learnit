package com.Learnit.controller;

public class UIUtils {

    public static void printHeader(String titulo){
        System.out.println("\n==============================");
        System.out.println("=== " + titulo);
        System.out.println("==============================");
    }

    public static void printSubHeader(String titulo){
        System.out.println("\n--- " + titulo + " ---");
    }

    public static void printSuccess(String mensaje){
        System.out.println("✔ " + mensaje);
    }

    public static void printError(String mensaje){
        System.out.println("✖ " + mensaje);
    }

    public static void printLine(){
        System.out.println("------------------------------");
    }
}