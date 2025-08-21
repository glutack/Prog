package tarea07;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


/** Ejercicio 3. Mapa de Ciclos y sus módulos
 * @author Irene Moreno Ladrón de Guevara
 */
public class Ejercicio03 {

    public static void main(String[] args) {
        
        //----------------------------------------------
        //    Declaración de variables y constantes
        //----------------------------------------------
        
        // Constantes
        
        // Variables de entrada
        /* Creo un mapa donde las claves un Integer y una lista de Integer (List<Integer>) */
        Map<Integer, List<Integer>> modulosPorCurso;
        
        // Variables auxiliares
        List<String> listaCodigosModulosDAW;

        // Variables de salida


        //----------------------------------------------
        //               Entrada de datos 
        //----------------------------------------------
        
        // No se piden datos al usuario, ya que se usa un array de elementos fijos
        
        System.out.println("CÓDIGOS DE LOS MÓDULOS DE DAW POR CURSOS");
        System.out.println("----------------------------------------");
        
        //----------------------------------------------
        //                  Procesamiento
        //----------------------------------------------
        
        // Instanciamos las variables con las que vamos a trabajar
        modulosPorCurso = new TreeMap<>();
        modulosPorCurso.put(1, new ArrayList<>());
        modulosPorCurso.put(2, new ArrayList<>());
        /* Convierto el array de Utilidades en una lista */
        listaCodigosModulosDAW = Arrays.asList(Utilidades.getArrayCodigosModulosDAW());

        // Recorremos la lista de códigos del ciclo de DAW (curso-codigo de modulo)
        // y creamos el mapa con cada curso y los codigos de los modulos correspondientes  
        for (String modulo : listaCodigosModulosDAW) {
            /* Divido el string para extraer el curso y el código y convierto el curso y el código a int */ 
            String[] partes = modulo.split("-");
            int curso = Integer.parseInt(partes[0]); 
            int codigoModulo = Integer.parseInt(partes[1]); 
            
            /* Añado el código del módulo a la lista correspondiente en el mapa */
            modulosPorCurso.get(curso).add(codigoModulo);
        }
        
        //----------------------------------------------
        //           Salida de resultados
        //----------------------------------------------
        
        System.out.printf("Contenido del mapa de códigos de módulos organizados por cursos:\n\n");
        for (Map.Entry<Integer, List<Integer>> modulo : modulosPorCurso.entrySet()) {
            System.out.println("Curso " + modulo.getKey() + ": " + modulo.getValue());
        }
        
 
    }
}