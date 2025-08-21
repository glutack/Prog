package tarea06;

// ------------------------------------------------------------
//                   Clase Lancha
// ------------------------------------------------------------
/**
 * <p>Clase que representa una embarcación de tipo <strong>lancha</strong> que se encarga de gestionar las diferentes operaciones de navegación.</p>
 * <p>Los objetos de esta clase permiten almacenar y gestionar la información relativa al estado de las lanchas y a la navegación.</p>
 * <p>La clase también posee información general (estática), relativa a los diferentes veleros que existen en el puerto, como:</p>
 * <ul>
 * <li><strong>cantidad total de veleros</strong> que existen</li>
 * </ul>
 * @author Moreno_LadróndeGuevara_Irene
 */
// La declaro como final para que no pueda ser heredable
public final class Lancha extends Embarcacion {
    // ------------------------------------------------------------------------
    // Atributos de la clase
    // ------------------------------------------------------------------------
    // Número de motores de la lancha
    private final int NumMotores;
    // Combustible disponible
    private int cantidadCombustible;
    // Cantidad total de lanchas
    private static int numLanchas = 0;
    /**
     * Número mínimo de motores de un lancha: {@value MIN_MOTORES}.
     */
    public static final int MIN_MOTORES = 1;
    /**
     * Número máximo de motores de un lancha: {@value MAX_MOTORES}.
     */
    public static final int MAX_MOTORES = 2;
    /**
     * Cantidad mínima de combustible de una lancha: {@value MIN_COMBUSTIBLE}.
     */
    public static final int MIN_COMBUSTIBLE = 8;
    /**
     * Cantidad máxima de combustible de una lancha: {@value MAX_COMBUSTIBLE}.
     */
    public static final int MAX_COMBUSTIBLE = 50;
    /**
     * Factor que se aplica a la hora de calcular el combustible restante en una lancha: {@value FACTOR_COMBUSTIBLE}.
     */
    public static final double FACTOR_COMBUSTIBLE = 0.026;
    /**
     * Velocidad mínima de navegación (en nudos): {@value MIN_VELOCIDAD_LANCHA}.
     */
    public static final int MIN_VELOCIDAD_LANCHA = 1;
    /**
     * Velocidad máxima de navegación (en nudos): {@value MAX_VELOCIDAD_LANCHA}.
     */
    public static final int MAX_VELOCIDAD_LANCHA = 50;
    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    /**
     * Constructor de cuatro parámetros de la clase Lancha. Crea un objeto Lancha y almacena los datos básicos de la misma nombre, motores, combustible y tripulantes.
     * 
     * @param nombre Nombre de la lancha
     * @param motores Número de motores del lancha
     * @param cantidadCombustible Nivel de combustible de la lancha
     * @param maxTripulantes Número máximo de tripulantes del barco
     * 
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     */
    public Lancha(String nombre, int motores, int cantidadCombustible, int maxTripulantes) throws IllegalArgumentException {
        // Para llamar al constructor de la superclase, uso super()
        super(nombre, maxTripulantes);
        
        if (motores < MIN_MOTORES || motores > MAX_MOTORES) {
            throw new IllegalArgumentException("El número de motores debe estar entre " + MIN_MOTORES + " y " + MAX_MOTORES + ".");
        }
        if (cantidadCombustible < MIN_COMBUSTIBLE || cantidadCombustible > MAX_COMBUSTIBLE) {
            throw new IllegalArgumentException("El nivel de combustible debe estar entre " + MIN_COMBUSTIBLE + " y " + MAX_COMBUSTIBLE + ".");
        }
        
        this.NumMotores = motores;
        this.cantidadCombustible = cantidadCombustible;
        numLanchas++;
    }

    /**
     * Constructor por defecto de la clase Lancha. Crea un objeto Lancha con los valores por defecto.
     */
    public Lancha() {
        this("Lancha " + (numLanchas + 1), MIN_MOTORES, MAX_COMBUSTIBLE, MIN_TRIPULANTES);
    }

    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método de acceso (getter) para consultar el atributo NumMotores.
     * @return int Número de motores de la lancha
     */
    public int getNumMotores() {
        return this.NumMotores;
    }

    /**
     * Método de acceso (getter) para consultar el atributo cantidadCombustible.
     * @return int Cantidad de combustible de la lancha
     */
    public int getCantidadCombustible() {
        return this.cantidadCombustible;
    }

    // ------------------------------------------------------------------------
    // Métodos estáticos (acceden a los atributos estáticos de la clase)
    // ------------------------------------------------------------------------
    /**
     * Método de acceso (getter) para consultar el atributo estático de clase numLanchas.
     * @return int Número de lanchas creadas hasta el momento
     */
    public static int getNumLanchas() {
        return numLanchas;
    }

    // ------------------------------------------------------------------------
    // Setters (modifican el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método para establecer el rumbo de la Lancha.
     * 
     * @param rumbo Rumbo en el que navega la lancha: norte, sur, este u oeste.
     * 
     * @throws NullPointerException Si el rumbo es nulo
     * @throws IllegalArgumentException Si alguno de los parámetros no es válido
     * 
     */
    @Override
    public void setRumbo(String rumbo) throws NullPointerException, IllegalArgumentException {
        if (rumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo (norte, sur, este u oeste) para poder modificarlo.");
        }

        // Uso equalsIgnoreCase() para que independientemente de cómo se introduzca el dato sea comparable
        if (!rumbo.equalsIgnoreCase("norte") && !rumbo.equalsIgnoreCase("sur") &&
            !rumbo.equalsIgnoreCase("este") && !rumbo.equalsIgnoreCase("oeste")) {
            throw new IllegalArgumentException("El rumbo no es correcto, debes indicar el rumbo (norte, sur, este u oeste) para poder modificarlo.");
        }
        super.setRumbo(rumbo);
    }

    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------
    /**
     * Método para iniciar la navegación de una lancha. 
     * 
     * @param velocidad la velocidad de la lancha durante la navegación
     * @param rumbo el rumbo de la lancha durante la navegación
     * @param patron el patrón de la lancha en esta navegación
     * @param tripulacion el número de tripulantes (exceptuando el patrón) en esta navegación
     * 
     * @throws IllegalArgumentException Si la velocidad no está en el rango permitido.
     * @throws IllegalStateException Si el nivel de combustible no está en el rango permitido.
     * 
     */
    @Override
    public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulacion) throws IllegalStateException {
        if (this.cantidadCombustible < MIN_COMBUSTIBLE || this.cantidadCombustible > MAX_COMBUSTIBLE) {
            throw new IllegalStateException("La lancha " + super.getNombreBarco() + " no tiene un nivel de combustible válido para iniciar la navegación (solo " + this.cantidadCombustible + " litros).");
        }

        if (velocidad < MIN_VELOCIDAD_LANCHA || velocidad > MAX_VELOCIDAD_LANCHA) {
            throw new IllegalArgumentException("La velocidad de navegación de " + velocidad + " nudos asignada a " + super.getNombreBarco() + " es incorrecta.");
        }
        super.iniciarNavegacion(velocidad, rumbo, patron, tripulacion);
    }

    /**
     * Método para parar la navegación del barco actual.
     * 
     * @param tiempoNavegando Tiempo (en minutos) que ha estado el barco navegando
     * 
     * @throws IllegalArgumentException Si el tiempo navegando es incorrecto
     * @throws IllegalStateException Si el barco no se encuentra navegando
     * 
     */
    @Override
    public void pararNavegacion(int tiempoNavegando) {
        // Para calcular el combustible consumido utilizo la fórmula que se proporciona. Uso Math.round() para que redondee el resultado y lo transformo a de double a (int)
        int combustibleConsumido = (int) Math.round(getVelocidad() * tiempoNavegando * FACTOR_COMBUSTIBLE);
        // Le resto el resultado al combustible con el que contaba la lancha. Uso Math.max() usando como valores el 0 y el resultado para que no podamos obtener valores negativos, si el resultado es negativo, usará el valor máximo 0
        this.cantidadCombustible = Math.max(0, this.cantidadCombustible - combustibleConsumido);
        super.pararNavegacion(tiempoNavegando);
    }    

    // ------------------------------------------------------------------------
    // Método Abstracto sobreecrito
    // ------------------------------------------------------------------------
    /**
     * Indica la forma de señalización de la lancha.
     * 
     */
    public void señalizar() {
        System.out.println("AVISO de señalización de la lancha " + super.getNombreBarco() + " con bocinas y luces intermitentes.");
    }

    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------
    /**
     * Devuelve el estado del objeto en un mensaje formateado en un tipo String.
     */
    @Override
    public String toString() {
        return super.toString() + ", Número de motores: " + this.NumMotores + ", Nivel de combustible: " + this.cantidadCombustible;
    }

}