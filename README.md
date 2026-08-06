# ARSW_Lab1
**Santiago Cajamarca y Sebastian Gonzalez**

Parte I - Introducción a Hilos en Java

1. De acuerdo con lo revisado en las lecturas, complete las clases CountThread, 
para que las mismas definan el ciclo de vida de un hilo que imprima por pantalla los números entre A y B.
![Punto 1.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/Punto%201.png)

2. Complete el método main de la clase CountMainThreads para que:
- Cree 3 hilos de tipo CountThread, asignándole al primero el intervalo [0..99], al segundo [99..199], 
y al tercero [200..299].

![Punto 2.1.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/Punto%202.1.png)

- Inicie los tres hilos con 'start()'.

![Punto 2.2.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/Punto%202.2.png)

- Ejecute y revise la salida por pantalla.

![Punto 2.3.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/Punto%202.3.png)

- Cambie el incio con 'start()' por 'run()'. Cómo cambia la salida?, por qué?.

Al ejecutar con el método start() los números aparecen on un orden aleatorio esto porque los hilos estan 
en una "condición de carrera". Por otro lado, con el método run() los números salen en orden y esto es 
porque los hilos se ejecutan uno a la vez.

