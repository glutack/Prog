/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tarea04;

import java.util.Scanner;

/**
 * Ejercicio 1. Comprimir cadenas.
 *
 * @author Irene Moreno Ladrón de Guevara
 */
public class Ejercicio01 {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Constantes
        /*  Creo una variable constante que contiene la expresión regular aceptada
            en el programa, indicando: Que al inicio de la cadena(^) se aceptan
            caracteres de la 'a' a la 'z' en minúscula y mayúscula, sin indicar 
            la ñ, y que los siguientes caracteres coinciden 1 ó más veces con este
            patrón (+) hasta el final ($) */
        final String regex = "^[a-zA-Z]+$";

        // Variables de entrada
        String input;

        // Variables de salida
        String resultado = "";

        // Variables auxiliares
        int i;
        int count = 1;

        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("Ejercicio 1. Comprimir cadenas.");
        System.out.println("-------------------------------");

        //  Solicito la cadena al usuario
        System.out.println("Introduce una cadena de caracteres:");
        input = teclado.nextLine();

        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        /*  Si el input  coincide con el regex el programa continúa sin problemas
            y, si no se devuelve un mensaje al usuario indicándole el error */
        if (input.matches(regex)) {
            /*  Creo un bucle for que usará la variable i para recorrer los caracteres
                del input y que durará mientras i sea menor que el tamaño del input */
            for (i = 0; i < input.length(); i++) {
                /*  Si hay un caracter tras el caracter actual y es igual a este,
                    sumo 1 a count, si no es igual, añado el caracter actual a
                    resultado y si se ha encontrado más de 1 vez, añado el 
                    resultado de count al string también y posteriormente reinicio
                    el contador a 1 para el siguiente caracter */
                if (i + 1 < input.length() && input.charAt(i) == input.charAt(i + 1)) {
                    count++;
                } else {
                    /*  Añado el carácter actual y el número de repeticiones si es
                        mayor a 1 */
                    resultado += input.charAt(i);

                    if (count > 1) {
                        resultado += count;
                    }
                    count = 1;
                }
            }
        } else {
            System.out.println("Error: Solo se permiten letras.");
            return;
        }

        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        /*  Indico que si el resultado tiene mayor tamaño que la cadena introducida,
            se imprima la cadena original, si no, se imprime la cadena comprimida
            en resultado */
        if (resultado.length() > input.length()) {
            System.out.println(input);
        } else {
            System.out.println(resultado);
        }
    }
}
