package tarea02;

import java.util.Scanner;

/**
 * Ejercicio 1. SUELDOS DE EMPLEADOS.
 *
 * @author Irene Moreno Ladrón de Guevara
 */
public class Ejercicio01 {

    public static void main(String[] args) {

        // ----------------------------------------------
        // Declaración de variables
        // ----------------------------------------------
        // Constantes
        int SUELDOBASE = 1500;
        int MASCINCOANOS = 100;
        int MENOSCINCOANOS = 20;
        int ANOEXTRA = 50;

        // Variables de entrada
        int antiguedad;

        // Variables de salida
        double[] sueldoMensual = new double[3];
        double sueldoAnual = 0;

        // Variables auxiliares
        int opcion;
        int i;
        String error = "";

        // Clase Scanner para petición de datos de entrada
        Scanner teclado = new Scanner(System.in);

        // ----------------------------------------------
        // Entrada de datos
        // ----------------------------------------------
        System.out.println("Ejercicio 2: Sueldos de Empleados\n");
        System.out.println("-----------------------------------------");

        // Bloque 1: Sacamos por pantalla el menú de opciones y pedimos que introduzca
        // una. En caso de introducir una opción inválida, debemos indicarlo y volver a
        // pedirla.
        /*
         * Para que el programa siga pidiendo al usuario una opción válida y siga,
         * imprimiendo el menú una vez hemos terminado un proceso, creo un bucle
         * do-while que se repite mientras la opción introducida sea distinta a 3.
         * Para esto, inicializo la variable opcion en -1 en primer lugar.
         */
        do {
            opcion = -1;
            i = 0;
            System.out.println("Opciones:");
            System.out.println("1. Calcular el sueldo de los empleados en función de la antigüedad");
            System.out.println("2. Calcular el coste anual y mensual de un empleado nuevo");
            System.out.println("3. Salir del programa");
            System.out.println("Introduzca el número de la acción a realizar:");
            /*
             * Para evitar errores si se introducen datos de otro tipo, creo una
             * estructura if-else que primero comprueba si el dato introducido es int.
             * Si es int, se comprueba si se encuentra entre los valores válidos.
             * En ambos casos si las opciones son inválidas, se envía un mensaje
             * de error y se vuelve a pedir una entrada. En este caso guardo los
             * errores en una variable para poder hacer uso de una estructura
             * if-elseif-else posteriormente.
             */
            if (teclado.hasNextInt()) {
                opcion = teclado.nextInt();
                if (opcion < 1 || opcion > 3) {
                    error = "Por favor...................................................................................................................................................................................................................................., introduzca una opción válida (1, 2 o 3).";
                }
            } else {
                error = "Por favor, introduzca un número.";
                teclado.next(); // Descarta la entrada no válida
            }

            // ----------------------------------------------
            // Procesamiento
            // ----------------------------------------------
            // Cuando haya introducido una opción válida, llevamos a cabo la acción oportuna
            // -Si nos indica 'Salir', nos despedimos y terminamos
            // -Si nos indica las opciones 1 o 2
            // -Realizamos los cálculos oportunos
            /*
             * Utilizo una estructura switch que realizará una u otra acción
             * según la acción que introduzca el usuario. El proceso de salida
             * de la aplicación se hará en "Salida de resultados" para que quede
             * con el resto de posibles salidas
            */
            switch (opcion) {
                case 1:
                    /*
                     * Creo un bucle while para no tener que repetir las líneas de código.
                     * Este bucle se repetirá hasta que la variable i sume 3 veces, lo que
                     * sólo pasa si el dato introducido es correcto. Si no lo es, se
                     * imprime un mensaje de error y en el caso del primer if-else, uso
                     * teclado.next() ya que al no asignar el dato con nextInt primero
                     * entra en bucle. Si los datos son correctos, realiza los cálculos y
                     * los guarda en el array que contiene los valores de todos los
                     * empleados.
                     */ 
                    while (i < 3) {
                        System.out.println("Introduzca los años de antigüedad del empleado " + (i + 1) + ":");
                        if (teclado.hasNextInt()) {
                            antiguedad = teclado.nextInt();
                            // Comprueba que el valor introducido no sea negativo
                            if (antiguedad >= 0) {
                                if (antiguedad < 5) {
                                    sueldoMensual[i] = SUELDOBASE + MENOSCINCOANOS + (antiguedad * ANOEXTRA);
                                } else {
                                    sueldoMensual[i] = SUELDOBASE + MASCINCOANOS + (antiguedad * ANOEXTRA);
                                }
                                i++;
                            } else {
                                System.out.println("Entrada no válida. Por favor, introduzca un número positivo.");
                            }
                        } else {
                            System.out.println("Entrada no válida. Por favor, introduzca un número.");
                            teclado.next(); // Descarta la entrada no válida
                        }
                    }
                    break;

                case 2:
                    sueldoAnual = (SUELDOBASE + MENOSCINCOANOS) * 14;
                    sueldoMensual[0] = sueldoAnual / 12;
                    break;
            }

            // ----------------------------------------------
            // Salida de resultados
            // ----------------------------------------------
            /*
             * Esto podría haberse realizado anteriormente para no utilizar
             * muchas lí­neas de código, pero lo saco aquí para seguir el esquema
             * que pide el ejercicio. Como creo que ya he usado todas las estructuras
             * aprovecho para usar un if-elseif-else en este caso según el caso
             * que se reciba
             */
            if (opcion == 1) {
                System.out.println("Los salarios mensuales de los empleados son (sin prorrateo de pagas extra):");
                for (i = 0; i < 3; i++) {
                    System.out.printf("Empleado %d: %.2f euros.%n", i + 1, sueldoMensual[i]);
                }
                System.out.printf("%n");
            } else if (opcion == 2) {
                System.out.printf(
                        "El salario anual para un empleado sin antigüedad es: %.2f euros.%n",
                        sueldoAnual);
                System.out.printf(
                        "El salario mensual para un empleado sin antigüedad (con pagas extra prorrateadas) es: %.2f%n%n",
                        sueldoMensual[0]);
            } else if (opcion == 3) {
                System.out.println("Saliendo del programa...\nHasta pronto :)");
                System.exit(0);
            } else {
                System.out.println("Entrada no válida. " + error);
            }
        } while (opcion != 3);
    }
}
