package entidadesJPA;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entidad JPA que representa a un empleado del supermercado.
 * 
 * <p>Contiene información sobre su identificador, nombre, sueldo y sección 
 * asociada. Cada empleado está vinculado a una {@link Seccion}.</p>
 * 
 * <p>La clave primaria es el campo {@code idEmpleado}.</p>
 * 
 * @author Irene Moreno Ladrón de Guevara
 * @version mayo/2025
 */
@Entity
public class Empleado implements Serializable {

    /**
     * Identificador del empleado. 
     * Longitud máxima: 4 caracteres.
     */
    @Id
    @Column(name = "id_empleado", length = 4, nullable = false)
    private String idEmpleado;

    /**
     * Nombre del empleado.
     * Longitud máxima: 30 caracteres. No puede ser nulo.
     */
    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;
    
    /**
     * Salario anual del empleado. No puede ser nulo.
     */
    @Column(name = "salario_anual", nullable = false)
    private int salarioAnual;

    /**
     * Sección a la que pertenece. No puede ser nulo.
     * Asociación muchos-a-uno con la entidad {@link Seccion}.
     */
    @ManyToOne
    @JoinColumn(name = "id_seccion", nullable = false)
    private Seccion seccion;
    
    /**
     * Constructor vacío requerido por JPA.
     */
    public Empleado() {
    }
    
    /**
     * Constructor completo para crear un nuevo empleado con todos los atributos.
     * 
     * @param idEmpleado Identificador del empleado
     * @param nombre Nombre
     * @param salarioAnual Salario anual
     * @param seccion Sección a la que pertenece
     */
    public Empleado(String idEmpleado, String nombre, int salarioAnual, Seccion seccion) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.salarioAnual = salarioAnual;
        this.seccion = seccion;
    }
    
    /**
     * Devuelve el identificador del empleado.
     * @return ID del empleado.
     */
    public String getIdEmpleado() {
        return idEmpleado;
    }
    
    /**
     * Devuelve el nombre del empleado.
     * @return nombre del empleado.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Devuelve el salario anual del emp0leado.
     * @return salario del empleado.
     */
    public int getSalarioAnual() {
        return salarioAnual;
    }
    
    /**
     * Devuelve la sección asociada al empleado.
     * @return sección asociada al empleado.
     */
    public Seccion getSeccion() {
        return seccion;
    }

    /**
     * Establece el identificador del empleado.
     * @param idEmpleado Nuevo ID del empleado.
     */
    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    
    /**
     * Establece el nombre del empleado.
     * @param nombre Nuevo nombre del empleado.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Establece el salario anual del empleado.
     * @param salarioAnual Nuevo salario del empleado. 
     */
    public void setSalarioAnual(int salarioAnual) {
        this.salarioAnual = salarioAnual;
    }
    
    /**
     * Establece la sección del empleado.
     * @param seccion Nueva sección a la que pertenece el empleado.
     */
    public void setSeccion(Seccion seccion) {
        this.seccion = seccion;
    }
    
    /**
     * Devuelve una representación en texto del producto.
     * @return Cadena con los datos del producto
     */
    @Override
    public String toString() {
        return this.getIdEmpleado() + " "
                + this.getNombre() + " "
                + this.getSalarioAnual() + " "
                + this.getSeccion().toString();
    }
}
