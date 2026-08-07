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

**Parte II - Ejercicio Black List Search**

- De acuerdo con lo revisado en las lecturas, complete las clases CountThread, para que las mismas definan el ciclo de vida de un hilo que imprima por pantalla los números entre A y B.

Entonces ya hicimos la clase que es un hilo Thread que buscara una dirección IP en un segmento especifico de los servidores de las listas negras

![punto II 1.1.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/punto%20II%201.1.png)

Ya definimos el método run que iterara desde el startIndex hasta el endingIndex y se detendrá si se encuentra mas de 5 veces en las blackList y guardara el numero del servidor en blackListOcurrences.

![punto II 1.2.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/punto%20II%201.2.png)

Tambien definimos algunos get

![punto II 1.3.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/punto%20II%201.3.png)

**Parte II - Ejercicio Black List Search**


Para un software de vigilancia automática de seguridad informática se está desarrollando un componente encargado de validar las direcciones IP en varios miles de listas negras (de host maliciosos) conocidas, y reportar aquellas que existan en al menos cinco de dichas listas.

Dicho componente está diseñado de acuerdo con el siguiente diagrama, donde:

- HostBlackListsDataSourceFacade es una clase que ofrece una 'fachada' para realizar consultas en cualquiera de las N listas negras registradas (método 'isInBlacklistServer'), y que permite también hacer un reporte a una base de datos local de cuando una dirección IP se considera peligrosa. Esta clase NO ES MODIFICABLE, pero se sabe que es 'Thread-Safe'.

- HostBlackListsValidator es una clase que ofrece el método 'checkHost', el cual, a través de la clase 'HostBlackListDataSourceFacade', valida en cada una de las listas negras un host determinado. En dicho método está considerada la política de que al encontrarse un HOST en al menos cinco listas negras, el mismo será registrado como 'no confiable', o como 'confiable' en caso contrario. Adicionalmente, retornará la lista de los números de las 'listas negras' en donde se encontró registrado el HOST.


Al usarse el módulo, la evidencia de que se hizo el registro como 'confiable' o 'no confiable' se dá por lo mensajes de LOGs:

INFO: HOST 205.24.34.55 Reported as trustworthy

INFO: HOST 205.24.34.55 Reported as NOT trustworthy


Al programa de prueba provisto (Main), le toma sólo algunos segundos análizar y reportar la dirección provista (200.24.34.55), ya que la misma está registrada más de cinco veces en los primeros servidores, por lo que no requiere recorrerlos todos. Sin embargo, hacer la búsqueda en casos donde NO hay reportes, o donde los mismos están dispersos en las miles de listas negras, toma bastante tiempo.

Éste, como cualquier método de búsqueda, puede verse como un problema [vergonzosamente paralelo](https://en.wikipedia.org/wiki/Embarrassingly_parallel), ya que no existen dependencias entre una partición del problema y otra.

Para 'refactorizar' este código, y hacer que explote la capacidad multi-núcleo de la CPU del equipo, realice lo siguiente:

1. Cree una clase de tipo Thread que represente el ciclo de vida de un hilo que haga la búsqueda de un segmento del conjunto de servidores disponibles. Agregue a dicha clase un método que permita 'preguntarle' a las instancias del mismo (los hilos) cuantas ocurrencias de servidores maliciosos ha encontrado o encontró.

2. Agregue al método 'checkHost' un parámetro entero N, correspondiente al número de hilos entre los que se va a realizar la búsqueda (recuerde tener en cuenta si N es par o impar!). Modifique el código de este método para que divida el espacio de búsqueda entre las N partes indicadas, y paralelice la búsqueda a través de N hilos. Haga que dicha función espere hasta que los N hilos terminen de resolver su respectivo sub-problema, agregue las ocurrencias encontradas por cada hilo a la lista que retorna el método, y entonces calcule (sumando el total de ocurrencuas encontradas por cada hilo) si el número de ocurrencias es mayor o igual a _BLACK_LIST_ALARM_COUNT_. Si se da este caso, al final se DEBE reportar el host como confiable o no confiable, y mostrar el listado con los números de las listas negras respectivas. Para lograr este comportamiento de 'espera' revise el método [join](https://docs.oracle.com/javase/tutorial/essential/concurrency/join.html) del API de concurrencia de Java. Tenga también en cuenta:

    * Dentro del método checkHost Se debe mantener el LOG que informa, antes de retornar el resultado, el número de listas negras revisadas VS. el número de listas negras total (línea 60). Se debe garantizar que dicha información sea verídica bajo el nuevo esquema de procesamiento en paralelo planteado.

    * Se sabe que el HOST 202.24.34.55 está reportado en listas negras de una forma más dispersa, y que el host 212.24.24.55 NO está en ninguna lista negra.

Para esta parte reutilizamos la lógica del problema anterior

![punto II 2.1.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/punto%20II%202.1.png)

![punto II 2.2.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/punto%20II%202.2.png)

En el main le agregamos la variable N que representa el número de hilos que se usaran 

![punto II 2.3.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/punto%20II%202.3.png)

Al ejecutarlo antes se demoraba aproximadamente 2 minutos, pero con estos cambios se reduce a 5 segundos

![punto II 2.2.png](PARALLELISM-JAVA_THREADS-INTRODUCTION_BLACKLISTSEARCH/img/punto%20II%202.4.png)


- Cambie el incio con 'start()' por 'run()'. Cómo cambia la salida?, por qué?.

Al ejecutar con el método start() los números aparecen on un orden aleatorio esto porque los hilos estan 
en una "condición de carrera". Por otro lado, con el método run() los números salen en orden y esto es 
porque los hilos se ejecutan uno a la vez.

