/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 * @author David Cajamarca
 * @author Sebastian Gonzalez
 */


public class CountThreadsMain {
    int A = 0;
    int B = 300;
    int N = 3;
 
    public static void asignador(int A, int B, int N) {
        int total = B - A + 1;      
        int chunk = total / N;      
        int remainder = total % N;  
    
        CountThread[] threads = new CountThread[N];
        int start = A;
    
        for (int i = 0; i < N; i++) {
            int extra = (i < remainder) ? 1 : 0; 
            int end = start + chunk - 1 + extra;
    
            threads[i] = new CountThread(start, end);
            threads[i].run();
            start = end + 1;
        }
    }
    
    public static void main(String a[]) {
        int A = 0;
        int B = 300;
        int N = 3;
        asignador(A, B, N);
    }
    
}
