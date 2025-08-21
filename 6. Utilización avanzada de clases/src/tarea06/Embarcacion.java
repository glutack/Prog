package tarea06;

// ------------------------------------------------------------
//                   Clase Embarcacion
// ------------------------------------------------------------
/**
 * <p>Clase que representa un <strong>sistema de gestión de un puerto deportivo</strong> que se encarga de gestionar las diferentes operaciones de navegación de las embarcaciones del puerto.</p>
 * <p>Los objetos de esta clase permiten almacenar y gestionar la información relativa al estado de las embarcaciones y a la navegación.</p>
 * <p>La clase también posee información general (estática), relativa a las diferentes embarcaciones que existen en el puerto, como:</p>
 * <ul> 
 * <li><strong>cantidad total de embarcaciones</strong> que existen en el puerto</li>
 * <li><strong>cantidad de embarcaciones navegando</strong> en el momento actual</li>
 * <li><strong>cantidad total</strong> de minutos de navegación de todas los embarcaciones</li>
 * </ul>
 * 
 * @author Moreno_LadróndeGuevara_Irene
 */
public abstract class Embarcacion implements Navegable {

    // ------------------------------------------------------------------------
    // Atributos estáticos públicos (inmutables)
    // Pueden ser accedidos desde fuera de la clase
    // ------------------------------------------------------------------------
    /**
     * Nombre del patrón por defecto durante la navegación: {@value PATRON_POR_DEFECTO}.
     */
    public static final String PATRON_POR_DEFECTO = "Sin patrón";
    /**
     * Rumbo por defecto durante la navegación: {@value RUMBO_POR_DEFECTO}.
     */
    public static final String RUMBO_POR_DEFECTO = "Sin rumbo";
    /**
     * Número mínimo de tripulantes (sin incluir el patrón): {@value MIN_TRIPULANTES}.
     */
    public static final int MIN_TRIPULANTES = 0;

    // ------------------------------------------------------------------------
    // Atributos estáticos privados (mutables)
    // No dependen de instancias de objetos particulares y sólo pueden 
    // modificarse desde la propia clase
    // ------------------------------------------------------------------------
    /** Cantidad total de barcos */
    private static int barcosCreados = 0;
    /** Cantidad total de barcos que están navegando */
    private static int barcosNavegando = 0;
    /** Número total de minutos de navegación de todos los barcos */
    private static float tiempoTotalNavegacion = 0;

    // ------------------------------------------------------------------------
    // Atributos de objeto inmutables (privados)
    // Representan el estado del objeto pero no pueden cambiar su valor
    // ------------------------------------------------------------------------
    /** Nombre del barco */
    private final String NombreBarco;           
    /** Número máximo de tripulantes del barco (sin incluir el patrón) */
    private final int MaxTripulantes;

    // ------------------------------------------------------------------------
    // Atributos de objeto variables (privados)
    // Representan el estado del objeto y pueden cambiar su valor
    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // Atributos del estado del barco
    // ------------------------------------------------------------------------
    // Representan el estado básico del barco en un momento dado
    // ------------------------------------------------------------------------
    /** Indica si el barco está navegando o no (TRUE / FALSE) */
    protected boolean navegando; 

    // ------------------------------------------------------------------------
    // Atributos de la información de navegación
    // ------------------------------------------------------------------------
    // Almacenan información sobre los parámetros de navegación
    // ------------------------------------------------------------------------
    /** Velocidad del barco (en nudos) */
    protected int velocidad;    
    /** Nombre del patron del barco */
    protected String patron;    
    /** Rumbo que tomará el barco mientras está navegando */
    protected String rumbo;                  
    /** Número de tripulantes del barco */
    protected int tripulacion;                 
    /** Número total de minutos de navegación del barco */
    protected int tiempoTotalNavegacionBarco;   
    
    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    /**
     * Constructor de la clase <code>Embarcacion</code>.
     * 
     * @param nombre Nombre de la embarcación.
     * @param tripulantes Número máximo de tripulantes (sin incluir el patrón).
     * 
     * @throws NullPointerException Si el nombre es nulo.
     * @throws IllegalArgumentException Si el nombre está vacío o el número de tripulantes es menor al mínimo permitido.
     */
    public Embarcacion(String nombre, int tripulantes) throws NullPointerException, IllegalArgumentException {
        if (nombre == null) {
            throw new NullPointerException("El nombre de la embarcación no puede ser nulo.");
        }

        // Utilizo trim() para eliminar los posibles espacios que se introduzcan
        if (nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la embarcación no puede estar vacío.");
        }

        if (tripulantes < MIN_TRIPULANTES) {
            throw new IllegalArgumentException("El número de tripulantes debe ser, como mínimo, " + MIN_TRIPULANTES + ".");
        }

        this.NombreBarco = nombre;
        this.MaxTripulantes = tripulantes;
        this.navegando = false;
        this.velocidad = 0;
        this.patron = PATRON_POR_DEFECTO;
        this.rumbo = RUMBO_POR_DEFECTO;
        this.tripulacion = MIN_TRIPULANTES;
        this.tiempoTotalNavegacionBarco = 0;

        barcosCreados++;
    }

    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método de acceso (getter) para consultar el atributo <code>NombreBarco</code>
     * @return Nombre del velero 
     */
    public String getNombreBarco() {
        return this.NombreBarco;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>maxTripulantes</code>
     * @return Número máximo de tripulantes del velero (sin incluir el patrón)
     */
    public int getMaxTripulantes() {
        return this.MaxTripulantes;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>navegando</code>
     * @return El velero está navegando o no (TRUE / FALSE)
     */
    public boolean isNavegando() {
        return this.navegando;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>velocidad</code>
     * @return Velocidad de navegación del velero
     */
    public int getVelocidad() {
        return this.velocidad;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>rumbo</code>
     * @return Rumbo de navegación del velero
     */
    public String getRumbo() {
        return this.rumbo;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>patron</code>
     * @return Patrón del velero
     */
    public String getPatron() {
        return this.patron;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>tripulacion</code>
     * @return Número de tripulantes del velero
     */
    public int getTripulacion() {
        return this.tripulacion;
    }

    /**
     * Método de acceso (getter) para consultar el atributo <code>tiempoTotalNavegacionBarco</code>
     * @return Minutos de navegación del velero
     */
    public int getTiempoTotalNavegacionBarco() {
        return this.tiempoTotalNavegacionBarco;
    }

    // ------------------------------------------------------------------------
    // Métodos estáticos (acceden a los atributos estáticos de la clase)
    // ------------------------------------------------------------------------
    /**
     * Método de acceso (getter) para consultar el atributo estático de clase <code>barcosCreados</code>
     * @return Número de barcos creados
     */
    public static int getNumBarcos() {
        return barcosCreados;
    }

    /**
     * Método de acceso (getter) para consultar el atributo estático de clase <code>barcosNavegando</code>
     * @return Número de barcos navegando en el momento actual
     */
    public static int getNumBarcosNavegando() {
        return barcosNavegando;
    }

    /**
     * Método de acceso (getter) para consultar el atributo estático de clase <code>tiempoTotalNavegacion</code>
     * @return Minutos de navegación de todos los barcos 
     */
    public static float getTiempoTotalNavegacion() {
        return tiempoTotalNavegacion;
    }

    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método para establecer el rumbo de la Embarcación.
     * 
     * @param rumbo Rumbo en el que navega el barco.
     * 
     * @throws IllegalStateException Si la embaración no se encuentra navegando
     * @throws NullPointerException Si el rumbo es nulo
     * @throws IllegalArgumentException Si el rumbo está vacío
     * @throws IllegalStateException Si la embarcación ya se encuentra navegando en ese rumbo
     */
    public void setRumbo(String rumbo) throws IllegalStateException, NullPointerException, IllegalArgumentException {        
        if (rumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo para iniciar la navegación.");
        }

        if (rumbo.trim().isEmpty()) {
            throw new IllegalArgumentException("El rumbo no puede estar vacío.");
        }

        if (rumbo.equals(this.rumbo)) {
            throw new IllegalStateException("La embarcación " + this.NombreBarco + " ya está navegando con ese rumbo (" + this.rumbo + "), debes indicar un rumbo distinto para poder modificarlo.");
        }

        if (!this.navegando) {
            throw new IllegalStateException("La embarcación " + this.NombreBarco + " no está navegando, no se puede cambiar el rumbo.");
        }

        this.rumbo = rumbo;
    }

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
    public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulacion) throws IllegalStateException, NullPointerException, IllegalArgumentException {
        if (this.navegando) {
            throw new IllegalStateException("La embarcación " + this.NombreBarco + " ya está navegando y se encuentra fuera de puerto.");
        }

        if (patron == null) {
            throw new NullPointerException("El patrón de la embarcación no puede ser nulo, se necesita un patrón para iniciar la navegación.");
        }

        if (patron.trim().isEmpty()) {
            throw new IllegalArgumentException("El patrón de la embarcación no puede estar vacío, se necesita un patrón para iniciar la navegación.");
        }

        if (tripulacion < MIN_TRIPULANTES || tripulacion > this.MaxTripulantes) {
            throw new IllegalArgumentException("El número de tripulantes debe estar entre" + MIN_TRIPULANTES + " y " + this.MaxTripulantes + ".");
        }

        this.navegando = true;
        // Para el rumbo no creo las excepciones ya que se controlan desde setRumbo()
        this.setRumbo(rumbo);
        this.velocidad = velocidad;
        this.patron = patron;
        this.tripulacion = tripulacion;

        barcosNavegando++;
    }

    /**
     * Método que para la navegación del barco actual.
     * 
     * @param tiempo (en minutos) que ha estado el barco navegando
     * 
     * @throws IllegalArgumentException Si el tiempo navegando es incorrecto
     * @throws IllegalStateException Si el barco no se encuentra navegando
     */
    public void pararNavegacion(int tiempo) throws IllegalStateException, IllegalArgumentException {
        if (!this.navegando) {
            throw new IllegalStateException("La embarcación " + this.NombreBarco + " no está navegando.");
        }

        if (tiempo <= 0) {
            throw new IllegalArgumentException("Tiempo navegando incorrecto, debe ser mayor que cero.");
        }

        this.navegando = false;
        this.velocidad = 0;
        this.rumbo = RUMBO_POR_DEFECTO;
        this.patron = PATRON_POR_DEFECTO;
        this.tripulacion = 0;
        this.tiempoTotalNavegacionBarco += tiempo;
        tiempoTotalNavegacion += tiempo;
        
        barcosNavegando--;
    }

    // ------------------------------------------------------------------------
    // Método Abstracto (sin implementación)
    // ------------------------------------------------------------------------
    /**
     * Metodo que indica la forma de señalización de la embarcación.
     */
    public abstract void señalizar();

    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Devuelve el estado del objeto en un mensaje formateado en un tipo String
     */
    @Override
    public String toString() {
        String estadoNavegacion = navegando ? "Sí" : "No";
        String infoNavegacion = navegando ? "con el patrón " + this.patron + " en " + this.rumbo + " a " + this.velocidad + " nudos, " : ""; 
        return "Nombre de la embarcación: " + this.NombreBarco + ", Tripulación: " + this.tripulacion + ", Navegando: " + estadoNavegacion + ", " + infoNavegacion + "Tiempo total de navegación del barco: " + (this.tiempoTotalNavegacionBarco / 60.00) + "horas";
    }
}