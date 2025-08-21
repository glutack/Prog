package tarea05;

// ------------------------------------------------------------
//                   Clase Velero
// ------------------------------------------------------------
/**
 * Representa un velero que puede navegar y cambiar de rumbo según ciertas reglas.
 * 
 * La clase Velero permite controlar el estado de navegación de un velero, definir
 * su nombre, y establecer o modificar su rumbo dentro de los valores permitidos
 * ("ceñida" o "empopada").
 * Proporciona mecanismos para garantizar que el velero solo pueda cambiar de rumbo
 * si está navegando y se cumplen las reglas definidas.
 *
 * @author Irene Moreno Ladrón de Guevara
 */
public class Velero {

    // ------------------------------------------------------------------------
    // Atributos estáticos públicos (inmutables)
    // Pueden ser accedidos desde cualquier caso
    // ------------------------------------------------------------------------
    /**
     * Mínimo de mástiles.
     * Indica el mínimo de mástiles que puede tener un barco: @value
     */
    public static final int MIN_MASTILES = 1;
    /**
     * Máximo de mástiles.
     * Indica el máximo de mástiles que puede tener un barco: @value
     */
    public static final int MAX_MASTILES = 4;
    /**
     * Velocidad mínima.
     * Indica la velocidad mínima a la que puede ir un barco: @value
     */
    public static final int MIN_VELOCIDAD = 2;
    /**
     * Velocidad máxima.
     * Indica la velocidad máxima a la que puede ir un barco: @value
     */
    public static final int MAX_VELOCIDAD = 30;
    /**
     * Patrón por defecto.
     * Guarda el valor por defecto de patrón en caso de declararse uno: @value
     */
    public static final String PATRON_POR_DEFECTO = "Sin patrón";
    /**
     * Rumbo por defecto.
     * Guarda el valor por defecto de rumbo en caso de no declararse uno: @value
     */
    public static final String RUMBO_POR_DEFECTO = "Sin rumbo";
    /**
     * Mínimo de tripulantes.
     * Indica el mínimo de tripulantes que puede haber en un barco: @value
     */
    public static final int MIN_TRIPULANTES = 0;

    // ------------------------------------------------------------------------
    // Atributos estáticos privados (mutables)
    // No dependen de instancias de objetos particulares y sólo pueden 
    // modificarse desde la propia clase
    // ------------------------------------------------------------------------
    /**
     * Número de barcos.
     * Indica el número total de barcos que se han creado.
     */
    public static int numBarcos;
    /**
     * Número de barcos navegando.
     * Indica el número total de barcos que hay navegando en un mismo instante.
     */
    public static int numBarcosNavegando;
    /**
     * Tiempo total de navegación.
     * Indica el tiempo total que han navegado todos los barcos juntos.
     */
    public static float tiempoTotalNavegacion;

    // ------------------------------------------------------------------------
    // Atributos de objeto inmutables (privados)
    // Representan el estado del objeto pero no pueden cambiar su valor
    // ------------------------------------------------------------------------
    /**
     * Nombre del velero.
     * Identifica al velero de forma única.
     */
    private final String Nombre;
    /**
     * Número de mástiles.
     * Indica el número de mastiles del velero.
     */
    private final int NumMastiles;
    /**
     * Número máximo de tripulantes.
     * Indica el número máximo de tripulantes que puede llevar el velero.
     */
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
    /**
     * Navegando.
     * Indica si el barco está navegando o no en este momento.
     */
    private boolean navegando;
    /**
     * Tiempo total de navegación del barco.
     * Indica el tiempo total de navegación del barco.
     */
    private int tiempoTotalNavegacionBarco;

    // ------------------------------------------------------------------------
    // Atributos de la información de navegación
    // ------------------------------------------------------------------------
    // Almacenan información sobre los parámetros de navegación
    // ------------------------------------------------------------------------
    /**
     * 
     */
    /**
     * Velocidad.
     * Indica la velocidad a la que va el barcoen un momento.
     */
    private int velocidad;
    
    /**
     * Patrón.
     * Indica el patrón que lleva el barco en un momento.
     */
    private String patron;
    
    /**
     * Rumbo.
     * Indica el rumbo que lleva el barco en un momento.
     */
    private String rumbo;
    
    /**
     * Tripulantes.
     * Indica la cantidad de tripulantes que se encuentran en el barco en un momento.
     */
    private int tripulantes;

    // ------------------------------------------------------------------------
    // Constructores de la clase
    // ------------------------------------------------------------------------
    // Constructor con tres parámetros
    /**
     * Constructor de la clase Velero con 3 parámetros.
     * Construye un objeto de tipo Velero inicializando los parámetros de nombre,
     * numeroMastiles y maxTripulantes con los parámetros indicados por el usuario.
     * Inicializa los demás atributos de la clase mediante los valores por defecto,
     * navegando se inicializa en false y tiempoTotalNavegacionBarco y velocidad
     * se inicializan a 0.
     * 
     * @param nombre
     * @param numeroMastiles
     * @param maxTripulantes 
     * 
     * Sus posibles excepciones son:
     * @throws NullPointerException El nombre del velero no puede ser nulo.
     * @throws IllegalArgumentException El nombre del velero no puede estar vacío.
     * @throws IllegalArgumentException El número de mastiles debe estar entre 1 y 4.
     * @throws IllegalArgumentException El número de tripulantes debe ser, como mínimo, 0.
     */
    public Velero(String nombre, int numeroMastiles, int maxTripulantes) {
        if (nombre == null) {
            throw new NullPointerException("El nombre del velero no puede ser nulo.");
        }

        if (nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del velero no puede estar vacío.");
        }

        if (numeroMastiles < MIN_MASTILES || numeroMastiles > MAX_MASTILES) {
            throw new IllegalArgumentException("El número de mastiles debe estar entre 1 y 4.");
        }

        if (maxTripulantes < MIN_TRIPULANTES) {
            throw new IllegalArgumentException("El número de tripulantes debe ser, como mínimo, 0.");
        }

        // Asigno a cada variable de la clase el valor que le corresponde
        this.Nombre = nombre;
        this.NumMastiles = numeroMastiles;
        this.MaxTripulantes = maxTripulantes;
        this.navegando = false;
        this.tiempoTotalNavegacionBarco = 0;
        this.velocidad = 0;
        this.patron = PATRON_POR_DEFECTO;
        this.rumbo = RUMBO_POR_DEFECTO;
        this.tripulantes = MIN_TRIPULANTES;

        // A numBarcos no le asigno ningún valor, sino que indico que siempre qu ese use el constructor se le sume 1
        numBarcos++;
    }

    // Constructor por defecto
    /**
     * Constructor sin parámetros de la clase Velero.
     * Construye un objeto tipo velero sin requerir datos, inicializando
     * automáticamente su nombre según el número de barcos que haya creados y 
     * declarando el mínimo de mástiles y de tripulantes.
     */
    public Velero() {
        this("Velero " + (numBarcos + 1), MIN_MASTILES, MIN_TRIPULANTES);
    }

    // Método fábrica
    /**
     * Método fábrica de la clase Velero.
     * Método que crea x número de objetos de la clase Velero según el parámetro
     * indicado por el usuario.
     * 
     * @param cantidad Cantidad de objetos de clase Velero que se quieren crear.
     * @return Array en el que se han guardado los objetos creados. 
     */
    public static Velero[] crearArrayVelero(int cantidad) {
        if (cantidad < 1 || cantidad > 10) {
            throw new IllegalArgumentException("Número de barcos incorrecto " + cantidad + ", debe ser mayor o igual que 1 y menor o igual que 10");
        }

        /*  Creo un array velero con la cantidad que el usuario haya introducido
            y un bucle for que llamará al constructor de Velero en cada vuelta */
        Velero[] veleros = new Velero[cantidad];
        for (int i = 0; i < cantidad; i++) {
            veleros[i] = new Velero();
        }
        return veleros;
    }

    // ------------------------------------------------------------------------
    // Getters (consultan el estado del objeto)
    // ------------------------------------------------------------------------
    // Getters que consultan el estado en un momento dado
    /**
     * Método getter para consultar el atributo nombre.
     *
     * @return El nombre del barco.
     */
    public final String getNombreBarco() {
        return Nombre;
    }

    /**
     * Método getter para consultar el atributo numMastiles.
     * 
     * @return El número de mástiles del barco.
     */
    public final int getNumMastiles() {
        return NumMastiles;
    }
    
    /**
     * Método getter para consultar el atributo maxTripulantes.
     * 
     * @return El número máximo de tripulantes que puede llevar el barco.
     */
    public final int getMaxTripulantes() {
        return MaxTripulantes;
    }

    /**
     * Método getter para consultar el atributo navegando.
     * 
     * @return Si el barco está navegando o no en ese momento.
     */
    public boolean isNavegando() {
        return navegando;
    }

    /**
     * Método getter para consultar el atributo tiempoTotalNavegacionBarco.
     * 
     * @return El tiempo total de navegación del barco.
     */
    public int getTiempoTotalNavegacionBarco() {
        return tiempoTotalNavegacionBarco;
    }

    /**
     * Método getter para consultar el atributo velocidad.
     * 
     * @return La velocidad a la que va el barco en ese momento.
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Método getter para consultar el atributo rumbo.
     * 
     * @return El rumbo que lleva el barco.
     */
    public String getRumbo() {
        return rumbo;
    }

    /**
     * Método getter para consultar el atributo patron.
     * 
     * @return El patrón que lleva el barco en ese momento.
     */
    public String getPatron() {
        return patron;
    }

    /**
     * Método getter para consultar el atributo tripulacion.
     * 
     * @return El número de tripulantes que se encuentran en el barco.
     */
    public int getTripulacion() {
        return tripulantes;
    }

    // ------------------------------------------------------------------------
    // Métodos getter estáticos (acceden a los atributos estáticos de la clase)

    /**
     * Método getter para consultar el atributo numBarcos.
     * 
     * @return El número de objetos de la clase Velero que se han creado.
     */
    public static int getNumBarcos() {
        return numBarcos;
    }

    /**
     * Método getter para consultar el atributo numBarcosNavegando.
     * 
     * @return El número de barcos que están navegando en ese momento.
     */
    public static int getNumBarcosNavegando() {
        return numBarcosNavegando;
    }

    /**
     * Método getter para consultar el atributo tiempoTotalNavegacion.
     * 
     * @return El tiempo total que han navegado todos los barcos juntos.
     */
    public static float getTiempoTotalNavegacion() {
        return tiempoTotalNavegacion;
    }

    // ------------------------------------------------------------------------
    // ------------------------------------------------------------------------
    // Setters (modifican el estado del objeto)
    // ------------------------------------------------------------------------

    /**
     * Método setter para establecer el rumbo del Velero.
     * 
     * @param rumbo El rumbo que se quiere indicar para el barco.
     * 
     * @throws IllegalStateException El velero no está navegando, no se puede cambiar el rumbo.
     * @throws NullPointerException El rumbo no puede ser nulo, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.
     * @throws IllegalArgumentException El rumbo no es correcto, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.
     * @throws IllegalArgumentException El velero ya está navegando con ese rumbo, debes indicar un rumbo distinto para poder modificarlo.
     */
    public void setRumbo(String rumbo) {
        if (this.navegando == false) {
            throw new IllegalStateException("El velero " + this.Nombre + " no está navegando, no se puede cambiar el rumbo.");
        }

        if (rumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.");
        }
        
        if (rumbo.isEmpty() || (!rumbo.equals("ceñida") && !rumbo.equals("empopada"))) {
            throw new IllegalArgumentException("El rumbo no es correcto, debes indicar el rumbo (ceñida o empopada) para poder modificarlo.");
        }

        if (rumbo.equals(this.rumbo)) {
            throw new IllegalArgumentException("El velero " + this.Nombre + " ya está navegando con ese rumbo (" + rumbo + "), debes indicar un rumbo distinto para poder modificarlo.");
        }
        this.rumbo = rumbo;
    }

    // ------------------------------------------------------------------------
    // Métodos de "acción" (almacenan la lógica y el comportamiento del objeto)
    // ------------------------------------------------------------------------

    /**
     * Método para iniciar la navegación del barco.
     * 
     * @param velocidad Velocidad que se le quiere iniciar al barco.
     * @param rumbo Rumbo que se le quiere dar al barco.
     * @param patron Patrón que está llevando el barco.
     * @param tripulantes Cantidad de tripulantes que se encuentran en el barco.
     * 
     * @throws IllegalArgumentException La velocidad de navegación es incorrecta.
     * @throws IllegalStateException El velero ya está navegando y se encuentra fuera de puerto.
     * @throws NullPointerException El rumbo no puede ser nulo, debes indicar el rumbo para inciar la navegación.
     * @throws IllegalArgumentException El rumbo no puede estar vacío, debes indicar el rumbo para iniciar la navegación.
     * @throws NullPointerException El patrón del barco no puede ser nulo, se necesita un patrón para iniciar la navegación.
     * @throws IllegalArgumentException El patrón del barco no puede estar vacío, se necesita un patrón para iniciar la navegación.
     * @throws IllegalArgumentException El número de tripulantes debe estar entre 0 y el máximo de tripulantes que admite el barco.
     */
    public void iniciarNavegacion(int velocidad, String rumbo, String patron, int tripulantes) {
        if (velocidad < MIN_VELOCIDAD || velocidad > MAX_VELOCIDAD) {
            throw new IllegalArgumentException("La velocidad de navegación de " + velocidad + " es incorrecta.");
        }

        if (this.navegando == true) {
            throw new IllegalStateException("El velero " + this.Nombre + " ya está navegando y se encuentra fuera de puerto.");
        }

        if (rumbo == null) {
            throw new NullPointerException("El rumbo no puede ser nulo, debes indicar el rumbo para inciar la navegación.");
        }

        if (rumbo.isEmpty()) {
            throw new IllegalArgumentException("El rumbo no puede estar vacío, debes indicar el rumbo para iniciar la navegación.");
        }

        if (patron == null) {
            throw new NullPointerException("El patrón del barco no puede ser nulo, se necesita un patrón para iniciar la navegación.");
        }

        if (patron.isEmpty()) {
            throw new IllegalArgumentException("El patrón del barco no puede estar vacío, se necesita un patrón para iniciar la navegación.");
        }

        /*  En este caso, para indicar los límites en este caso, uso la constante
            que hemos creado para el mínimo y el valor que se ha registrado en
            el propio objeto al crearlo como máximo */
        if (tripulantes < MIN_TRIPULANTES) {
            throw new IllegalArgumentException("El número de tripulantes debe estar entre " + MIN_TRIPULANTES + " y " + this.MaxTripulantes);
        }

        // Una vez hechas las comprobaciones, inicio la navegación
        this.navegando = true;
        this.velocidad = velocidad;
        this.rumbo = rumbo;
        this.patron = patron;
        this.tripulantes = tripulantes;
        numBarcosNavegando++;
    }

    /**
     * Método para parar la navegación del barco.
     * 
     * @param tiempoNavegacion Tiempo en minutos que ha pasado navegando el barco.
     * 
     * @throws IllegalStateException El velero no está navegando.
     * @throws IllegalArgumentException Tiempo navegando incorrecto, debe ser mayor que cero.
     */
    public void pararNavegacion(int tiempoNavegacion) {
        if (this.navegando == false) {
            throw new IllegalStateException("El velero " + this.Nombre + " no está navegando.");
        }
        if (tiempoNavegacion <= 0) {
            throw new IllegalArgumentException("Tiempo navegando incorrecto, debe ser mayor que cero.");
        }

        this.navegando = false;
        this.velocidad = 0;
        this.rumbo = RUMBO_POR_DEFECTO;
        this.patron = PATRON_POR_DEFECTO;
        this.tripulantes = MIN_TRIPULANTES;
        this.tiempoTotalNavegacionBarco += tiempoNavegacion;
        numBarcosNavegando--;
        tiempoTotalNavegacion += tiempoNavegacion;
    }

    /**
     * Método para iniciar una regata entre el barco que ejecuta el método y el 
     * indicado por el usuario.
     * 
     * @param velero Objeto Velero que va a participar en la regata.
     * @return String Cadena con el resultado de la regata.
     * 
     * @throws NullPointerException Si el barco con el que se intenta regatear no existe.
     * @throws IllegalStateException No se puede iniciar la regata, el barco no está navegando.
     * @throws IllegalStateException No se puede iniciar la regata, los barcos deben navegar con el mismo rumbo.
     */
    public String iniciarRegata(Velero velero) {
        String salida = "";
        if (velero == null) {
            throw new NullPointerException("El barco con el que se intenta regatear no existe.");
        }

        if (this.navegando == false) {
            throw new IllegalStateException("No se puede iniciar la regata, el barco " + this.Nombre + " no está navegando.");
        }

        if (velero.navegando == false) {
            throw new IllegalStateException("No se puede iniciar la regata, el barco " + velero.Nombre + " no está navegando.");
        }

        if (!velero.rumbo.equals(this.rumbo)) {
            throw new IllegalStateException("No se puede iniciar la regata, los barcos " + this.Nombre + " y " + velero.Nombre + " deben navegar con el mismo rumbo.");
        }

        if (velero.NumMastiles != this.NumMastiles) {
            throw new IllegalStateException("No se puede iniciar la regata, los barcos " + this.Nombre + " y " + velero.Nombre + " no tienen el mismo número de mástiles.");
        }

        if (velero.velocidad > this.velocidad) {
            salida = "El barco " + velero.Nombre + " ha llegado primero a la línea de llegada.";
            return salida;
        } else if (velero.velocidad < this.velocidad) {
            salida = "El barco " + this.Nombre + " ha llegado primero a la línea de llegada.";
            return salida;
        } else if (velero.velocidad == this.velocidad) {
            salida = "Los barcos " + this.Nombre + " y " + velero.Nombre + " han llegado a la vez a la línea de llegada.";
            return salida;
        }
        return salida;
    }

    // ------------------------------------------------------------------------
    // Método toString (imprime el estado del objeto)
    // ------------------------------------------------------------------------

    /**
     * Método que devuelve el estado del obejto en un mensaje formateado en un String.
     * 
     * @return String Estado del objeto.
     */
    @Override
    public String toString() {
        /*  Como la cadena del estado de navegación requiere de más información, creo
        un String aparte para más comodidad e indico que si navegando es true,
        el String será la frase requerida y si no, será No. */
        String estadoNavegacion = navegando
                ? String.format("Sí, con el patrón %s en %s a %d nudos", patron, rumbo, velocidad)
                : "No";

        return String.format(
                "{Nombre del barco: %s, Número de mástiles: %d, Tripulación: %d, Navegando: %s, Tiempo total de navegación del barco: %.2f horas}",
                Nombre, NumMastiles, tripulantes, estadoNavegacion, tiempoTotalNavegacionBarco / 60.0
        );
    }

}
