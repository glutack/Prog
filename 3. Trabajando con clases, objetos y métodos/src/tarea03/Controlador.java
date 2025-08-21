package tarea03;

import libtarea3.Aeronave;
import libtarea3.Aeropuerto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// ------------------------------------------------------------
//                   Clase Controlador
// ------------------------------------------------------------
/**
 * <p>
 * Clase que representa al <strong>controlador</strong>, que será la clase que
 * utilizaremos y donde se escribirán las diferentes operaciones en aeronaves y
 * aeropuertos que se nos pide en el enunciado de la tarea.</p>
 *
 * @author Irene Moreno Ladrón de Guevara
 */
public class Controlador {

    public static void main(String[] args) {

        //----------------------------------------------
        //          Declaración de variables 
        //----------------------------------------------
        /*  Declaro las variables de clase Aeropuerto, Aeronave y también LocalDateTime
            para poder utilizar su método now() para las fechas y horas de los
            despegues, DateTimeFormatter para utilizar su método format() y darles
            el formato requerido a las anteriores. */
        Aeropuerto barcelona, madrid, granada;
        Aeronave avion1, avion2, avion3;
        LocalDateTime ahora;
        DateTimeFormatter formato;

        //----------------------------------------------
        //          Creación de objetos
        //----------------------------------------------
        // Con el método ofPattern)= indico el formato de la fecha que aplicaré
        formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Instanciar los 3 aeropuertos: Barcelona, Madrid, Granada.
        System.out.printf("-------------------------\n"
                + "---Creando Aeropuertos---\n"
                + "-------------------------\n\n");
        barcelona = new Aeropuerto("El Prat", "Barcelona");
        madrid = new Aeropuerto("Barajas", "Madrid");
        granada = new Aeropuerto("Federico García Lorca", "Granada");

        System.out.printf("**Aeropuertos creados**\n\n");

        // Instanciar las 3 aeronaves: Avion1, Avion2, Avion3.
        System.out.printf("-------------------------\n"
                + "----Creando Aeronaves----\n"
                + "-------------------------\n\n");
        avion1 = new Aeronave();
        avion2 = new Aeronave("EC-123", "Boing747");
        avion3 = new Aeronave("EC-456", "Boing787", madrid);

        System.out.printf("**Aeronaves creadas**\n\n");

        //----------------------------------------------
        //   Inicio de la secuencia de instrucciones
        //----------------------------------------------
        System.out.printf("-------------------------\n"
                + "-Secuencia instrucciones-\n"
                + "-------------------------\n\n");

        //Avion1 despega con velocidad 1500, altitud 1750, rumbo 50 y fechaHora actual
        /*  Utilizo el método despegar() para introducir los datos del despegue y
            varios métodos get que me ofrece la clase para mostrar distintos datos
            del despegue. Primero había creado una única variable que guardaba
            un LocalDateTime que introducía en los métodos, después lo cambié a
            que se realizase en el método mismo porque, aunque en este caso no
            aplica porque todo el código se ejecuta a la vez, si ese no fuese el
            caso todos los métodos tendrían la misma fecha y hora aún despegando
            en distintas fechas y horas */
        avion1.despegar(1500, 1750, 50, LocalDateTime.now().format(formato));
        System.out.printf("Avión1 ha despegado a fecha %s convelocidad %d, altitud"
                + " %d y rumbo %d desde %s.\n", avion1.getFechaHoraDespegue(),
                avion1.getVelocidad(), avion1.getAltitud(), avion1.getRumbo(), avion1.getAeropuerto());

        //Avion2 despega con velocidad 1500, altitud 1850 y rumbo 75
        avion2.despegar(1500, 1850, 75, LocalDateTime.now().format(formato));
        System.out.printf("Avión2 ha despegado a fecha %s con velocidad %d, altitud"
                + " %d y rumbo %d desde %s.\n", avion2.getFechaHoraDespegue(),
                avion2.getVelocidad(), avion2.getAltitud(), avion2.getRumbo(), avion2.getAeropuerto());

        //Avion3 despega con velocidad 1500, altitud 1000 y rumbo 180
        avion3.despegar(1500, 1000, 180, LocalDateTime.now().format(formato));
        System.out.printf("Avión3 ha despegado a fecha %s con velocidad %d, altitud"
                + " %d y rumbo %d desde %s.\n\n", avion3.getFechaHoraDespegue(),
                avion3.getVelocidad(), avion3.getAltitud(), avion3.getRumbo(), avion3.getAeropuerto());

        //Comprobar si Avion1 está volando
        System.out.printf("¿Avión1 está volando?: %s.\n", avion1.isVolando());

        //Mostrar la matrícula del Avion2
        System.out.printf("Matrícula Avión2: %s.\n", avion2.getMatricula());

        //Mostrar modelo del Avion3
        System.out.printf("Model Avión3: %s.\n", avion3.getModelo());

        //Modificar rumbo del Avion2 a 90º y mostrarlo
        avion2.setRumbo(90);
        System.out.printf("El rumbo de Avión2 ha cambiado a %dº.\n\n", avion2.getRumbo());

        //Avion3 aterriza en el aeropuerto de Barcelona despues de 75 minutos
        avion3.aterrizar(barcelona, 75);
        System.out.printf("Avión3 ha aterrizado en %s tras %d minutos de vuelo.\n",
                avion3.getAeropuerto(), avion3.getTiempoTotalVuelo());

        //Avion2 aterriza en el aeropuerto de Madrid despues de 80 minutos
        avion2.aterrizar(madrid, 80);
        System.out.printf("Avion2 ha aterrizado en %s tras %d minutos de vuelo.\n\n",
                avion2.getAeropuerto(), avion2.getTiempoTotalVuelo());

        //Comprobar si Avion2 está volando
        System.out.printf("¿Avión2 está volando? %s.\n", avion2.isVolando());

        //Modificar altitud del Avion1 a 1250 metros y mostrarlo
        avion1.setAltitud(1250);
        System.out.printf("La altitud de Avión1 ha cambiado a %d.\n", avion1.getAltitud());

        //Mostrar el tiempo toal de vuelo del Avion2
        System.out.printf("Tiempo total de vuelo Avión2: %d minutos.\n", avion2.getTiempoTotalVuelo());

        //Mostrar el aeropuerto del Avion3
        System.out.printf("Avión3 se encuentra en %s.\n", avion3.getAeropuerto());

        //Mostrar la fecha y hora de despegue del Avion3
        System.out.printf("El despegue de Avión3 se realizó con fecha %s.\n\n", avion3.getFechaHoraDespegue());

        //Avion3 despega con velocidad 860, altitud 1420 y rumbo 270
        avion3.despegar(860, 1420, 270, LocalDateTime.now().format(formato));
        System.out.printf("Avión3 ha despegado a fecha %s con velocidad %d, altitud"
                + " %d y rumbo %d.\n", avion3.getFechaHoraDespegue(),
                avion3.getVelocidad(), avion3.getAltitud(), avion3.getRumbo());

        //Avion1 aterriza en el aeropuerto de Granada despues de 260 minutos
        avion1.aterrizar(granada, 260);
        System.out.printf("Avión1 ha aterrizado en %s tras %d minutos de vuelo.\n\n",
                avion1.getAeropuerto(), avion1.getTiempoTotalVuelo());

        //Modificar velocidad del Avion3 a 950km/h y mostrarlo
        avion3.setVelocidad(950);
        System.out.printf("La velocidad de Avión3 ha cambiado a %dkm/h.\n", avion3.getVelocidad());

        //Mostrar el nombre del aeropuerto de Madrid
        System.out.printf("Nombre del aeropuerto de Madrid: %s.\n", madrid.getNombre());

        //Mostrar el número de aeronaves en este momento en el aeropuerto de Granada
        System.out.printf("Aviones en el aeropuerto de Granada: %d.\n\n", granada.getNumeroAeronaves());

        //Mostrar toda la información del Avion1
        System.out.printf("Avión1: %s.\n", avion1.toString());

        //Mostrar toda la información del Avion2
        System.out.printf("Avión2: %s.\n", avion2.toString());

        //Mostrar toda la información del Avion3
        System.out.printf("Avión3: %s.\n\n", avion3.toString());

        //----------------------------------------------
        //          Llamadas a métodos estáticos
        //----------------------------------------------
        System.out.printf("-------------------------\n"
                + "----Métodos estáticos----\n"
                + "-------------------------\n\n");

        //Mostrar el número total de aeronaves volando ahora mismo
        System.out.printf("Aviones volando: %d.\n", avion1.getNumAeronavesVolando());

        //Mostrar el tiempo total de aeronaves volando en horas
        System.out.printf("Tiempo total de vuelo de los aviones: %s horas.\n", avion1.getNumHorasVuelo());

        //Mostrar el número total de aeronaves
        System.out.printf("Total de aviones: %d.\n", avion1.getNumAeronaves());
    }
}
