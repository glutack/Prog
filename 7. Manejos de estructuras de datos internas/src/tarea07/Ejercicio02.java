package tarea07;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/** Ejercicio 2. Trabajando con listas de módulos
 * @author Irene Moreno Ladrón de Guevara
 */
public class Ejercicio02 {

    public static void main(String[] args) {
        
        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        
        // Constantes
        
        // Variables de entrada
        
        // Variables auxiliares
        ListIterator<String> iterador;
        
        // Variables de salida
        List<String> modulosDAW;
        List<String> modulosDAM;
        List<String> modulosMatriculados;
        Set<String> modulosMatriculadosUnicos;
        List<String> modulosDAMOrdenadosPorNombre;
        List<String> modulosDAMOrdenadosPorCodigo;
        
        //----------------------------------------------
        //               Entrada de datos 
        //----------------------------------------------
        
        // No hay entrada de datos, pues se usa un número fijo de elementos
        System.out.println("LISTAS DE MÓDULOS");
        System.out.println("-----------------------------");

        // Inicializamos los arrays que vamos a utilizar
        /* Como en este caso sólo voy a insertar y consultar elementos, utilizo ArrayList() */
        modulosDAW = new ArrayList<>(List.of(Utilidades.getArrayModulosDAW()));
        modulosDAM = new ArrayList<>(List.of(Utilidades.getArrayModulosDAM()));

        // Instanciamos las colecciones para trabajar con ellas
        modulosMatriculados = new ArrayList<>();
        /* Utilizo listIterator() para recorrer cómodamente la lista */
        iterador = modulosDAW.listIterator();

        /* Utilizo un conjunto como tipo de colección en el que no se puedan repetir los elementos */ 
        modulosMatriculadosUnicos = new HashSet<>();
        
        // Rellenamos las listas de los ciclos con sus módulos correspondientes

        //----------------------------------------------
        //               Procesamiento
        //----------------------------------------------

        // Instanciamos las variables necesarias

        System.out.println();
        System.out.printf("Contenido inicial de la lista de módulos de DAW:  \n");
        Utilidades.mostrarModulos(modulosDAW);
        
        System.out.println();   
        System.out.printf("Contenido inicial de la lista de módulos de DAM:  \n");
        Utilidades.mostrarModulos(modulosDAM);
        
        // Recorro la lista de modulos de DAW
        // Iterador para recorrer la lista de DAW
        while (iterador.hasNext()) {
            String modulo = iterador.next();
            // Extraigo solo el nombre del módulo
            String nombreModulo = modulo.split("-")[1];
            
            // Si el nombre del módulo NO contiene 'i', se añade a las listas correspondientes
            if (!nombreModulo.contains("i")) {
                modulosMatriculados.add(modulo);
                modulosMatriculadosUnicos.add(modulo);
                // Marco el módulo en la lista original
                iterador.set("**" + modulo + "**");
            }
        }
        
        // Ordenación de la lista por nombre del módulo (alfabético) y la mostramos por pantalla
        System.out.println();  
        System.out.printf ("Ordenación de la lista de módulos de DAM por nombre (alfabético):\n");
        modulosDAM.sort(new ComparadorModuloPorNombre());
        modulosDAMOrdenadosPorNombre = new ArrayList<>(modulosDAM);
        Utilidades.mostrarModulos(modulosDAMOrdenadosPorNombre);
               
        // Ordenación de la lista por código del módulo y la mostramos por pantalla
        System.out.println();  
        System.out.printf ("Ordenación de la lista de módulos de DAM por código:\n");
        modulosDAM.sort(new ComparadorModuloPorCodigo());
        modulosDAMOrdenadosPorCodigo = new ArrayList<>(modulosDAM);
        Utilidades.mostrarModulos(modulosDAMOrdenadosPorCodigo);

        //----------------------------------------------
        //            Salida de resultados
        //----------------------------------------------
        
        System.out.println();     
        System.out.printf("Contenido final de la lista de módulos de DAW:  \n");
        Utilidades.mostrarModulos(modulosDAW);
        
        System.out.println();   
        System.out.printf("Contenido final de la lista de módulos matriculados (DAW): \n");
        Utilidades.mostrarModulos(modulosMatriculados);
                
        System.out.println();   
        System.out.printf("Contenido final del conjunto de módulos matriculados (DAW): \n");
        Utilidades.mostrarModulos(modulosMatriculadosUnicos);
    }
}

/**
 * Clase que permite comparar dos módulos usando como criterio
 * de comparación su nombre. Se trata de una comparación alfabética.
 * @author Irene Moreno Ladrón de Guevara
 */

class ComparadorModuloPorNombre implements Comparator<String> {
    @Override
    public int compare(String mod1, String mod2) {
        return mod1.split("-")[1].compareTo(mod2.split("-")[1]);
    }
}

/**
 * Clase que permite comparar dos módulos usando como criterio
 * de comparación su código.
 * @author Irene Moreno Ladrón de Guevara
 */
class ComparadorModuloPorCodigo implements Comparator<String> {
    @Override
    public int compare(String mod1, String mod2) {
        return Integer.compare(Integer.parseInt(mod1.split("-")[0]), Integer.parseInt(mod2.split("-")[0]));
    }
}