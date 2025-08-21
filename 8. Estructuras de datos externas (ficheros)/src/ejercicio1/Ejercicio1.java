package ejercicio1;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

/**
 * Ejercicio 1: Lectura/escritura de una biblioteca de libros en ficheros de
 * texto.
 *
 * @author apellidos y nombre del alumno
 */
public class Ejercicio1 {

    /**
     * Método principal.
     *
     * @param args argumentos que recibe el método
     */
    public static void main(String args[]) throws IOException {

        // ----------------------------------------------
        // Declaracion de variables
        // ----------------------------------------------
        // Constantes
        String rutaLibros = System.getProperty("user.dir") + "/recursos/ListadoLibros.txt";
        String rutaBiblioteca = System.getProperty("user.dir") + "/recursos/Biblioteca.txt";
        File archivo = new File(rutaLibros);
        FileReader fr = new FileReader(archivo);
        FileWriter fw = new FileWriter(rutaBiblioteca);
        Biblioteca biblioteca = new Biblioteca();

        // Variables de entrada
        BufferedReader br;
        BufferedWriter bw;

        // Variables de salida

        // Variables auxiliares
        String linea;
        String[] datos;
        LocalDate fecha;
        List<String> capitulos;
        String[] auxBiblioteca;
        String titulo;


        // ----------------------------------------------
        // Entrada de datos + Procesamiento
        // ----------------------------------------------
        // Abrimos archivo de contactos ListadoLibros.txt
        System.out.println("Abriendo archivo de libros...");
        br = new BufferedReader(fr);

        /*  Creo un bucle while que continua mientras siga habiendo líneas que leer en el
            archivo, es decir, no se reciba un null. Creo un array con los datos recibidos
            separándolos por ;, parseo los datos a los necesarios y creo un nuevo objeto
            libro que guardo en el objeto biblioteca */
        while ((linea = br.readLine()) != null) {
            datos = linea.split(";");
            fecha = LocalDate.parse(datos[2]);
            capitulos = List.of(datos[4].split(","));
            Libro libro = new Libro(datos[0], datos[1], fecha, capitulos, datos[3]);
            biblioteca.add(libro);
        }

        System.out.println("Cerrando archivo de libros...");
        br.close();
        System.out.println();

        // ----------------------------------------------
        // Salida de resultados
        // ----------------------------------------------

        // Abrimos el archivo de la Biblioteca de libros Biblioteca.txt
        System.out.println("Abriendo archivo de la biblioteca...");
        bw = new BufferedWriter(fw);
        
        // Escribimos el formato personalizado dentro del main
        bw.write("****************************************************************************************\n");
        bw.write("LIBRO DE LIBROS\n");
        bw.write("****************************************************************************************\n");
        
        /*  Como la clase biblioteca no tiene ningún método que devuelva los libros, recojo el resultado de su
            toString() y lo guardo en un array diviéndolo por los saltos de línea.*/ 
        auxBiblioteca = (biblioteca.toString()).split("\n");
        /*  Creo un bucle for que recorra el array anterior y realiza lo mismo con el array que en el bucle
            while de lectura de líneas */
        for (String auxLibro : auxBiblioteca) {
            datos = auxLibro.split(";");
            titulo = datos[0].trim();
            /*  Quito la almohadilla que aparece al principio del título, usando substring() para que comience en 
                la posición 1 en lugar de la 0*/
            if (titulo.startsWith("#")) {
               titulo = titulo.substring(1); 
            }
            bw.write("\nTITULO DEL LIBRO: " + titulo + "\n");
            bw.write("AUTOR: " + datos[1] + "\n");
            bw.write("FECHA DE CREACIÓN: " + datos[2] + "\n");
            bw.write("GENERO: " + datos[3] + "\n");
            bw.write("CAPITULOS: " + datos[4] + "\n");
            bw.write("****************************************************************************************\n");
        }
        
        System.out.println("Cerrando archivo de la biblioteca...");
        bw.close();
        System.out.println();
        System.out.println("Archivos cerrados y procesamiento finalizado");
        System.out.println("---------");
        System.out.println();
        System.out.println("Fin del programa.");
    }
}
