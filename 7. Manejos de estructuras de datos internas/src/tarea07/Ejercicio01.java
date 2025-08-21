package tarea07;

import java.util.Set;
import java.util.HashSet;

/**
 * Ejercicio 1. Creando conjuntos de ciclos DAW y DAM con sus módulos
 * @author Irene Moreno Ladrón de Guevara
 */
public class Ejercicio01 {
    
    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        
        // Constantes

        // Variables de entrada

        // Variables auxiliares
                
        // Variables de salida
        Set<String> modulosDAW;
        Set<String> modulosDAM;
        Set<String> unionModulos;
        Set<String> interseccionModulos;
        Set<String> diferenciaModulos;

     
        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        
        // No hay entrada de datos, pues se usa un número fijo de elementos
        
        System.out.println("CONJUNTOS DE MÓDULOS DE DAW Y DAM");
        System.out.println("--------------------------------");

        //----------------------------------------------
        //                  Procesamiento
        //----------------------------------------------
        
        // Inicializamos los arrays que vamos a utilizar


        
        // Inicialimando las colecciones que vamos autilizar
        // Al usar conjuntos (Set) garantizamos que no se pueden repetir elementos

        
        // Rellenamos los conjuntos de los ciclos con sus módulos correspondientes
        /* Relleno los conjuntos de String que almacenan datos usando tablas hash (Set) para almacenar los módulos */
        modulosDAW = new HashSet<>(Set.of(Utilidades.getArrayModulosDAW()));
        modulosDAM = new HashSet<>(Set.of(Utilidades.getArrayModulosDAM()));

        // Unión de los dos conjuntos
        /*  Relleno un conjunto de String con Set igual que los anteriores, le asigno en primero lugar modulosDAW y después uso la operación 
            addAll() para añadir también el conjunto modulosDAM */ 
        unionModulos = new HashSet<>(modulosDAW);
        unionModulos.addAll(modulosDAM);


        // Intersección de los conjuntos
        interseccionModulos = new HashSet<>(modulosDAW);
        interseccionModulos.retainAll(modulosDAM);

        
        // Diferencia de los conjuntos
        diferenciaModulos = new HashSet<>(modulosDAM);
        diferenciaModulos.removeAll(modulosDAW);

        
        //----------------------------------------------
        //              Salida de Resultados 
        //----------------------------------------------
        
        // Recorremos los conjuntos y mostramos su contenido por pantalla
        System.out.printf ("Conjunto C1 (módulos DAW):\n");
        Utilidades.mostrarModulos(modulosDAW);

        System.out.printf ("\nConjunto C2 (módulos DAM):\n");
        Utilidades.mostrarModulos(modulosDAM);
        
        System.out.printf ("\nUnión C1 y C2:\n");
        Utilidades.mostrarModulos(unionModulos);
        
        System.out.printf ("\nInterseccion C1 y C2:\n");
        Utilidades.mostrarModulos(interseccionModulos);
        
        System.out.printf ("\nDiferencia C1 y C2:\n");
        Utilidades.mostrarModulos(diferenciaModulos);
    }
}