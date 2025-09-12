package controllers;

public class Recursion {
    public static long factorial(int n){
        if(n==0) {
            return 1;
        }
        return n*factorial(n-1);
    }
    public static long fibonacci(int n){
        if(n==0) {
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fibonacci(n-1)+fibonacci(n-2);
    }
}
