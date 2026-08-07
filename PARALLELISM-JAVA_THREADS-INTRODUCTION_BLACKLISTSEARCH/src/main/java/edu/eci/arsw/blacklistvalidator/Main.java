/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        HostBlackListsValidator hblv = new HostBlackListsValidator();
        int nucleos = Runtime.getRuntime().availableProcessors();

        long inicio = System.currentTimeMillis();

        // Hilos: 1
        // List<Integer> resultados = hblv.checkHost("202.24.34.55", 1);

        // Hilos: número de núcleos
        // List<Integer> resultados = hblv.checkHost("202.24.34.55", nucleos);

        // Hilos: doble del número de núcleos
        // List<Integer> resultados = hblv.checkHost("202.24.34.55", nucleos * 2);

        // Hilos: 50
        // List<Integer> resultados = hblv.checkHost("202.24.34.55", 50);

        // Hilos: 100
         List<Integer> resultados = hblv.checkHost("202.24.34.55", 100);

        long fin = System.currentTimeMillis();

        System.out.println("Núcleos: " + nucleos);
        System.out.println("Tiempo: " + (fin - inicio) + " ms");
        System.out.println("Resultados: " + resultados);
    }
}