package tarea01;

import java.util.Scanner;

/**
 * Ejercicio 3. Validación de contraseñas.
 *
 * @author Irene Moreno Ladrón de Guevara
 */
public class Ejercicio03 {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Variables de entrada
        String contrasena; 
        
        // Variables de salida
        String resultado;
        
        // Variables auxiliares
        boolean lengthVal;
        boolean empiezaVocal;
        boolean terminaConsonante;
        boolean valorEspecial;
        char primerChar;
        int length;
        
        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        System.out.println("VALIDACIÓN DE CONTRASEÑAS");
        System.out.println("-------------------------");
        System.out.print("Introduzca la contraseña: ");
        contrasena = teclado.nextLine();

        //----------------------------------------------
        //                 Procesamiento 
        //----------------------------------------------
        
        // Cálculo de información auxiliar previa
        length = contrasena.length();
        primerChar = contrasena.charAt(0);
       
        // Comprobamos si tiene como mínimo 12 caracteres
        lengthVal = (length >= 12);
        
        // Comprobamos si comienza por una vocal (mayúscula o minúscula)
        /*  Utilizo indexOf() para comprobar si el caracter en el primer lugar de
            contrasena está en la cadena de vocales minúsculas, que devolverá un
            valor mayor a -1 si se encuentra. Para comprobarlo aunque sea una
            mayúscula utilizo toLowerCase() para que siempre compare minúsculas */
        empiezaVocal = ("aeiou".indexOf(Character.toLowerCase(primerChar)) != -1);
        
        // Comprobamos si termina con una consonante (mayúscula o minúscula)
        /*  Para la consonante realizo la misma comprobación que para la vocal,
            esta vez indicando la última posición */
        terminaConsonante = "bcdefghjklmnpqrstvwxyz".indexOf(Character.toLowerCase(contrasena.charAt(length - 1))) != -1 ;
        
        // Comprobamos si contiene al menos uno de los caracteres especiales requeridos
        /*  Como en este caso no se sabe la posición específica en la que se
            encontrará el caracter, utilizo el método contains() que comprobará
            si el char indicado se encuentra en el string de contrasena */
        valorEspecial = (contrasena.contains("@") || contrasena.contains("#") || 
                contrasena.contains("_") || contrasena.contains(".") || contrasena.contains(";"));
               
        resultado = (lengthVal && empiezaVocal && terminaConsonante && valorEspecial)
                ? "La contraseña es VÁLIDA" 
                : "La contraseña es NO VÁLIDA";
        
        //----------------------------------------------
        //              Salida de resultados 
        //----------------------------------------------
        System.out.println();
        System.out.println("RESULTADO");
        System.out.println("---------");
        System.out.println(resultado);
    }
}
