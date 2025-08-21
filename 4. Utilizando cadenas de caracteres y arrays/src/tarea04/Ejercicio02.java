/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea04;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Ejercicio 2. Rotar matrices cuadradas.
 *
 * @author Irene Moreno Ladrón de Guevara
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Constantes
        /*  Creo una variable constante que contiene la expresión regular aceptada
            en el programa, indicando: Que al inicio de la cadena(^) se aceptan
            caracteres de la 'a' a la 'z' en minúscula y mayúscula, la ñ y Ñ y
            números del 1 al 9, y que los siguientes caracteres coincidan 1 ó más
            veces con este patrón (*) hasta el final ($) */
        String regex = "^[a-zA-ZñÑ0-9]+";

        // Variables de entrada
        String input;

        // Variables de salida
        String[][] matriz;
        String[][] matrizRotada;

        // Variables auxiliares
        String[] linea;
        int size;
        int count = 1;
        int i;

        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //      Entrada de datos y procesamiento
        //----------------------------------------------
        System.out.println("Ejercicio 2. Rotar matrices cuadradas.");
        System.out.println("--------------------------------------");
        System.out.printf("Introduce la 1ª fila de valores separados por comas: ");
        input = teclado.nextLine();

        /*  Utilizo el método trim() para que elimine los espacios antes o después
            de los valores y split para que cada vez que encuentre una coma
            guarde el String antes de esta en distintas posiciones del array. */
        linea = input.trim().split(",");
        size = linea.length;
        matriz = new String[size][size];

        /*  Compruebo que el tamaño sea mayor a 1, para que la matriz sea rotable,
            si no devuelvo un mensaje de error al usuario y el programa finaliza. */
        if (size > 1) {
            /*  Creo un bucle for que recorra el largo del array, comprobando si
                los elementos coinciden con la expresión regular indicada y los
                guardo en la matriz, si no, se devuelve un mensaje de error al
                usuario y el programa finaliza. */
            for (i = 0; i < size; i++) {
                if (!linea[i].matches(regex)) {
                    System.out.println("Error: Los elementos deben ser números o letras.\n");
                    return;
                }
                matriz[0][i] = linea[i];
            }

            System.out.printf("Vamos a realizar una matriz de %dx%d\n", size, size);
            /*  Creo un bucle while que sólo avanzará si el tamaño del input
                introducido por el usuario coincide con el de la línea introducida
                anteriormente y repito con su input las mismas acciones. 
                En este caso, si el número de valores no coincide el programa
                continuará hasta que se introduzca el número correcto, y si se 
                introducen caracteres no válidos se terminará*/
            while (count < size) {
                System.out.printf("Introduce la %dª fila de valores separados por comas: ", count + 1);
                input = teclado.nextLine();
                linea = input.trim().split(",");

                if (linea.length == size) {
                    for (i = 0; i < size; i++) {
                        if (!linea[i].matches(regex)) {
                            System.out.printf("Error: La fila %d contiene valores no permitidos.\n", count + 1);
                            return;
                        }
                        matriz[count][i] = linea[i];
                    }
                    count++;
                } else {
                    System.out.printf("Error: El número de elementos de la fila debe ser %d\n", size);
                }
            }
        } else {
            System.out.println("Error: La matriz no puede ser de tamaño 0x0 ni 1x1.");
            return;
        }

        /*  Para rotar la matriz, creo un nuevo array bidimensional con el mismo
            tamaño que la matriz original. Creo un bucle for que recorre la fila
            de la matriz, con un bucle for dentro que recorre las columnas de 
            ésta fila y va asignándolos a matrizRotada. Para que la matriz rote
            90º, el índice de la fila al que se asigna será el índice de la 
            columna en la matriz original y el índice de la columna será el 
            tamaño total de la matriz menos 1, ya que las posiciones empiezan
            desde 0 y no 1, menos el incremento (i), de manera que empezaremos
            desde el índice mayor.
        Ej: matriz =    a, b, c
                        aa, bb, cc
                        aaa, bbb, ccc
            El valor en matriz[0][1] se moverá a matrizRotada[1][3 - 1 - 0] */
        matrizRotada = new String[size][size];
        for (i = 0; i < size; i++) {
            for (count = 0; count < size; count++) {
                matrizRotada[count][size - 1 - i] = matriz[i][count];
            }
        }
        
//----------------------------------------------
//              Salida de resultados 
//----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        System.out.println("Matriz original:");
        for (i = 0; i < size; i++) {
            for (count = 0; count < size; count++) {
                System.out.print(matriz[i][count] + " ");
            }
            System.out.println();
        }
        System.out.println("\nMatriz rotada 90 grados a la derecha:");
        for (i = 0; i < size; i++) {
            for (count = 0; count < size; count++) {
                System.out.print(matrizRotada[i][count] + " ");
            }
            System.out.println();
        }
    }
}
