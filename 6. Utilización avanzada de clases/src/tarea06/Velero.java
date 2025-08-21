package tarea06;

// ------------------------------------------------------------
//                   Clase Velero
// ------------------------------------------------------------
/**
 * <p>Clase que representa una embarcación de tipo <strong>velero</strong> que se encarga de gestionar las diferentes operaciones de navegación.</p>
 * <p>Los objetos de esta clase permiten almacenar y gestionar la información relativa al estado de los veleros y a la navegación.</p>
 * <p>La clase también posee información general (estática), relativa a los diferentes veleros que existen en el puerto, como:</p>
 * <ul>
 * <li><strong>cantidad total de lanchas</strong> que existen</li>
 * </ul>
 * 
 * @author Moreno_LadróndeGuevara_Irene
 */
public final class Velero extends Embarcacion implements Regateable {
    // ------------------------------------------------------------------------
    // Atributos de la clase
    // ------------------------------------------------------------------------
    private final int NumMastiles;
    private static int numVeleros = 0;

    /**
     * Número mínimo de mástiles de un velero: {@value MIN_MASTILES}.
     */
    public static final int MIN_MASTILES = 1;
    /**
     * Número máximo de mástiles de un velero: {@value MAX_MASTILES}.
     */
    public static final int MAX_MASTILES = 4;
    /**
     * Velocidad mínima de navegación (en nudos): {@value MIN_VELOCIDAD_VELERO}.
     */
    public static final int MIN_VELOCIDAD_VELERO = 2;
    /**
     * Velocidad máxima de navegación (en nudos): {@value MAX_VELOCIDAD_VELERO}.
     */
    public static final int MAX_VELOCIDAD_VELERO = 30;

    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    /**
     * Constructor de tres parámetros de la clase <code>Velero</code>.
     * Crea un objeto <code>Velero</code> y almacena los datos básicos del barco:
     * <b>nombre</b>, <b>mástiles</b> y <b>maxTripulantes</b>.
     * 
     * @param nombre Nombre del barco
     * @param mastiles Número de mástiles del velero
     * @param maxTripulantes Número máximo de tripulantes del barco
     * 
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     */
    public Velero(String nombre, int mastiles, int maxTripulantes) throws IllegalArgumentException {  
        super(nombre, maxTripulantes);      
        if (mastiles < MIN_MASTILES || mastiles > MAX_MASTILES) {
            throw new IllegalArgumentException("El número de mástiles debe estar entre " + MIN_MASTILES + " y " + MAX_MASTILES + ".");
        }
        this.NumMastiles = mastiles;
        numVeleros++;
    }

    /**
     * Constructor por defecto de la clase <code>Velero</code>.
     * Crea un objeto <code>Velero</code> con los valores por defecto.
     * 
     */
    public Velero() {
        this("Velero " + (numVeleros + 1), MIN_MASTILES, MIN_TRIPULANTES);
    }

    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método de acceso (getter) para consultar el atributo <code>NumMastiles</code>.
     * @return Número de mástiles del velero
     */
    public int getNumMastiles() {
        return this.NumMastiles;
    }

    // ------------------------------------------------------------------------
    // Métodos estáticos (acceden a los atributos estáticos de la clase)
    // ------------------------------------------------------------------------
    /**
     * Método de acceso (getter) para consultar el atributo estático de clase <code>numVeleros</code>.
     * @return Número de veleros
     */
    public static int getNumVeleros() {
        return numVeleros;
    }

    // ------------------------------------------------------------------------
    // Setters (modifican el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método para establecer el rumbo del Velero.
     * 
     * @param rumbo Rumbo en el que navega el barco: ceñida o empopada.
     * 
     * @throws NullPointerException Si el rumbo es nulo
     * @throws IllegalArgumentException Si el rumbo no es válido
     * @throws IllegalStateException Si el velero ya se encuentra navegando en ese rumbo
     */
    @Override
    public void setRumbo(String rumbo) {
        if (rumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.");
        }
        if (!rumbo.equalsIgnoreCase("ceñida") && !rumbo.equalsIgnoreCase("empopada")) {
            throw new IllegalArgumentException("El rumbo no es correcto, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.");
        }
        super.setRumbo(rumbo);
    }

    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método que inicia la navegación del velero actual.
     * 
     * @param velocidad la velocidad del velero durante la navegación
     * @param rumbo el rumbo del velero durante la navegación
     * @param patron el patrón del velero en esta navegación
     * @param tripulantes el número de tripulantes (exceptuando el patrón) en esta navegación
     * 
     * @throws IllegalArgumentException Si la velocidad no está en el rango permitido.
     * 
     */
    @Override
    public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulantes) {
        if (velocidad < MIN_VELOCIDAD_VELERO || velocidad > MAX_VELOCIDAD_VELERO) {
            throw new IllegalArgumentException("La velocidad de navegación de " + velocidad + " nudos es incorrecta.");
        }
        super.iniciarNavegacion(velocidad, rumbo, patron, tripulantes);
    }
    
    /**
     * Método que inicia una regata entre el velero actual y otro velero.
     * 
     * @param otroVelero el velero contra el que se va a regatear
     * 
     * @throws IllegalStateException Si alguno de los veleros no está navegando o si no llevan el mismo rumbo.
     * @throws NullPointerException Si el velero que se pasa como parámetro es nulo.
     * 
     * @return String El resultado de la regata
     */
    public String iniciarRegata(Velero otroVelero) {
        if (otroVelero == null) {
            throw new NullPointerException("El barco con el que se intenta regatear no existe.");
        }
        if (!this.isNavegando()) {
            throw new IllegalStateException("No se puede iniciar la regata, el barco " + this.getNombreBarco() + " no está navegando.");
        }
        if (!otroVelero.isNavegando()) {
            throw new IllegalStateException("No se puede iniciar la regata, el barco " + otroVelero.getNombreBarco() + " no está navegando.");
        }
        if (!this.getRumbo().equals(otroVelero.getRumbo())) {
            throw new IllegalStateException("No se puede iniciar la regata, los barcos " + this.getNombreBarco() + " y " + otroVelero.getNombreBarco() + " deben navegar con el mismo rumbo.");
        }
        if (this.getNumMastiles() != otroVelero.getNumMastiles()) {
            throw new IllegalStateException("No se puede iniciar la regata, los barcos " + this.getNombreBarco() + " y " + otroVelero.getNombreBarco() + " no tienen el mismo número de mástiles.");
        }
        if (this.getVelocidad() > otroVelero.getVelocidad()) {
            return "El barco " + this.getNombreBarco() + " ha llegado antes a la línea de llegada.";
        } else if (this.getVelocidad() < otroVelero.getVelocidad()) {
            return "El barco " + otroVelero.getNombreBarco() + " ha llegado antes a la línea de llegada.";
        } else {
            return "Los barcos " + this.getNombreBarco() + " y " + otroVelero.getNombreBarco() + " han llegado a la vez a la línea de llegada.";
        }
    }
    // ------------------------------------------------------------------------
    // Método Abstracto sobreecrito
    // ------------------------------------------------------------------------
    /**
     * Indica la forma de señalización del velero.
     */
    public void señalizar() {
        System.out.println("AVISO del velero " + this.getNombreBarco() + " con banderas de señalización marítima.");
    }
    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Devuelve el estado del objeto en un mensaje formateado en un tipo String.
     */
    @Override
    public String toString() {
        return super.toString() + ", Número de mástiles: " + this.NumMastiles;
    }
}
