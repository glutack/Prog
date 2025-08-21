package tarea06;

/**
 * Interfaz Regateable.
 * 
 * Será implementada por cualquier embarcación capaz de participar en una regata, permite:
 * iniciarRegata(), que permite iniciar una regata de un barco con otro
 * 
 * @author nombre_apellidos_Alumno
 */
public interface Regateable {
    String iniciarRegata(Velero otroVelero);
}
