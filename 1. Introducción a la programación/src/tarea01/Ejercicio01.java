package tarea01;

import java.util.Scanner;

/**
 * Ejercicio 1. Cálculos aritméticos.
 *
 * @author Irene Moreno Ladrón de Guevara
 */
public class Ejercicio01 {
    
    // Definición del enum
    public enum Operaciones {OPERACION_1, OPERACION_2, OPERACION_3};

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Variables de entrada
        double valorX, valorY, valorZ;
        
        // Variables de salida
        double resultado1, resultado2, resultado3;
        
        // Variables auxiliares
        
        
        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("CÁLCULOS ARITMÉTICOS");
        System.out.println("--------------------");
        System.out.println("Introduzca tres números reales:");
        

        System.out.print("Valor para la X: ");
        valorX = teclado.nextDouble();
        
        System.out.print("Valor para la Y: ");
        valorY = teclado.nextDouble();
        
        System.out.print("Valor para la Z: ");
        valorZ = teclado.nextDouble();

        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        
        // Primera expresión
        resultado1 = valorX / 3 + 8;
        
        // Segunda expresión
        resultado2 = (valorX * valorX) / (valorY * valorY) + (valorY * valorY) / (valorZ * valorZ);

        // Tercera expresión
        resultado3 = (valorX * valorX + 3 * valorX * valorY + valorY * valorY) / (1 / (valorX * valorX));
        
        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        System.out.println(Operaciones.OPERACION_1 + ": " + resultado1);
        System.out.println(Operaciones.OPERACION_2 + ": " + resultado2);
        System.out.println(Operaciones.OPERACION_3 + ": " + resultado3);
    }
}
