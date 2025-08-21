package tarea06;

/**
 * Interfaz Navegable.
 * 
 * Será implementada por cualquier embarcación, permite:
 * iniciarNavegacion(), que permite iniciar la navegación.
 * pararNavegacion(), que permitirá detener la navegación.
 * 
 * @author Moreno_LadrondeGuevara_Irene
 */
public interface Navegable {
    /**
     * Método que inicia la navegación del barco actual.
     * 
     * @param velocidad la velocidad del barco durante la navegación
     * @param rumbo el rumbo del barco durante la navegación
     * @param patron el patrón del barco en esta navegación
     * @param tripulacion el número de tripulantes (exceptuando el patrón) en esta navegación
     * 
     * @throws IllegalStateException Si el rumbo o el patrón son nulos.
     * @throws NullPointerException Si el rumbo o patrón está vacío o si la tripulación no está en el rango permitido.
     * @throws IllegalArgumentException Si el barco ya se encuentra navegando o necesita mantenimiento
     */
    void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulacion);
    /**
     * Método que para la navegación del barco actual.
     * 
     * @param tiempo (en minutos) que ha estado el barco navegando
     * 
     * @throws IllegalArgumentException Si el tiempo navegando es incorrecto
     * @throws IllegalStateException Si el barco no se encuentra navegando
     */
    void pararNavegacion(int tiempo);
}
