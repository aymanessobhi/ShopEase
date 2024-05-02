package com.ideracloud.salewell.utils;

public class Test {

    public static void main (String[] args){
        try{
            System.out.println(6/0);
        }catch(Exception e){
            throw e;
        }

        System.out.println("6/0 failed");
    }
}
