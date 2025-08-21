package ejercicio2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

/**
 * Clase que permite serializar un objeto Biblioteca al formato XML y 
 viceversa.
 *   
 * @author profe
 */
public class BibliotecaXML {
    
     // Ruta del archivo donde se lee y escribe el objeto Biblioteca
    private String rutaArchivo;
    
    
    // Objeto Xstream que permite la L/E con archivos XML
    private XStream xstream;

    /**
     * Metodo constructor
     * @param nombreArchivo Ruta del archivo donde se lee y escribe el objeto Biblioteca
     */
    public BibliotecaXML(String nombreArchivo) {
        this.rutaArchivo = nombreArchivo;
        this.xstream = new XStream();
        //Permite asignar privilegios para poder operar con los archivos XML
        this.xstream.allowTypesByWildcard(new String[] { 
            "ejercicio2.**"
        });
    }

    
    // -----------------------------------------------------
    // Ejercicio 2: Metodos que debe implementar el alumnado
    // -----------------------------------------------------
    
    // 3.1. Metodo escribir()
    /**
     * Metodo que escribe, en un archivo de texto, un objeto Biblioteca serializable.
     * @param biblioteca Objeto Biblioteca serializable para almacenar en el archivo de texto.
     */    
    public void escribir(Biblioteca biblioteca) {
        String xml = xstream.toXML(biblioteca);

        try {
            FileWriter writer = new FileWriter(rutaArchivo);
            writer.write(xml);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo XML: " + e.getMessage());
        }
    }

    // 3.2. Metodo leer()
     /**
     * Metodo que lee, desde un archivo de texto, un objeto Biblioteca serializado.
     * @return Objecto Biblioteca que estaba almacenado en el archivo de texto.
     */
    public Biblioteca leer() {
        Biblioteca biblioteca = null;

        try {
            // Leer el contenido del archivo XML
            FileReader reader = new FileReader(rutaArchivo);
            BufferedReader bufferedReader = new BufferedReader(reader);

            StringBuilder xml = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                xml.append(line);
            }

            // Deserializar el contenido del archivo XML a un objeto Biblioteca
            biblioteca = (Biblioteca) xstream.fromXML(xml.toString());
            bufferedReader.close();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo XML: " + e.getMessage());
        }

        return biblioteca;
    }
}
