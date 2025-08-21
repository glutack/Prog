
package tarea01;

/**
 * Ejercicio 2. Operaciones con constantes y variables.
 *
 * @author Irene Moreno Ladrón de Guevara
 */
public class Ejercicio02 {

    public static void main(String[] args) {

        //---------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        // Constantes
        final double CONSTANTE1 = 80.3;
        final int CONSTANTE2 = 3;

        // Variables de entrada
        
        // Variables de salida
        
        // Variables auxiliares
        boolean valorBool;
        byte enteroByte;
        short enteroShort;
        int enteroInt, producto;
        long enteroLong;
        double decimalDoble;
        float decimalSimple;

        //----------------------------------------------
        //                Entrada de datos 
        //----------------------------------------------
        // No hay entrada de datos
        System.out.println("OPERACIONES CON CONSTANTES Y VARIABLES.");
        System.out.println("---------------------------------------");
        System.out.println(" ");

        //----------------------------------------------
        //     Procesamiento 
        //----------------------------------------------
        
        //----------------------------------------------
        // Ejemplos que se proporcionan como modelo:
        
        // Las variables booleanas solo pueden tener los valores true o false
        // valorBool = 0;
        
        // decimalSimple = 9.9 * 4.6;       
        // SOLUCIÓN: CASTING EXPLÍCITO
        decimalSimple = (float) (9.9 * 4.6);

        // CASTING IMPLÍCITO: de tipo char a tipo int
        enteroInt = 'a';
        //----------------------------------------------

        // Error en el uso de la coma en lugar del punto decimal
        // SOLUCIÓN: Usar el punto decimal
        // decimalDoble = 5,17;
        decimalDoble = 5.17;
        
        enteroLong = 25_369L;

        /*  La multiplicación de enteroLong (long) y enteroInt (int) devuelve
            un long, pero la variable producto es int y de números enteros a 
            números en coma no se realiza el casting implícito directamente por
            la pérdida de precisión que puede suponer */
        //  SOLUCIÓN: CASTING EXPLÍCITO
        // producto = enteroLong * enteroInt;
        producto = (int) (enteroLong * enteroInt);

        /*  Se está tratando de hacer una comparación entre un booleano y un
            entero, ya que 97 == 'a' primero devolvería un booleano y luego se
            compararía este resultado con == 97, para recibir un único booleano,
            deberíamos separar las comparaciones y unirlas con && ó ||
            valorBool = (97 == 'a') && ('a' == 97);
            SOLUCIÓN: en este caso la comparación es redundante, así que es más
            sencillo eliminar una de las comparaciones */
        // valorBool = (97 == 'a' == 97);
        valorBool = (97 == 'a');        

        /*  Las variables constantes de tipo final no pueden cambiar su valor tras
            declararse */
        // SOLUCIÓN: borrar esta línea */
        // CONSTANTE1 = 100.3;

        /*  La variable decimalSimple es de tipo float, pero se le está asignando un char,
            que posee un valor de número entero */
        // SOLUCIÓN: CASTING IMPLÍCITO
        decimalSimple = 'c';

        decimalDoble = 3.2 * 4.7;

        // SOLUCIÓN: CASTING EXPLÍCITO como se indica en el ejemplo
        // decimalSimple = 9.9 * 4.6;       
        decimalSimple = (float) (9.9 * 4.6);

        /* La variable producto es de tipo int y se le está intentando asignar 
            un valor long, que es lo que se produciría al multiplicar una
            variable double por una int */
        //  SOLUCIÓN: CASTING EXPLÍCITO
        //  producto = CONSTANTE1 * CONSTANTE2;
        producto = (int) (CONSTANTE1 * CONSTANTE2);

        // CASTING IMPLÍCITO: de float a double
        decimalDoble = 5.67F;

        // CASTING IMPLÍCITO: de int a double
        decimalDoble = 8;

        enteroInt = 1 / 2;
    }
}
