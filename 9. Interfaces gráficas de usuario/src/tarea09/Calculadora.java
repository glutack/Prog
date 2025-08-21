package tarea09;

//Librerías para poder utilizar JavaFX
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

//Librerías específicas para evaluar expresiones exp4j
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * La típica calculadora básica para realizar cálculos artitméticos como la
 * suma, resta, multiplicación y división, en la que se permite el cálculo de
 * expresiones combinadas.
 *
 * @author profesorado
 */
public class Calculadora extends Application {

    //----------------------------------------------
    //          Declaración de variables 
    //----------------------------------------------
    TextField campo = new TextField();
    Button cero = new Button();
    Button uno = new Button();
    Button dos = new Button();
    Button tres = new Button();
    Button cuatro = new Button();
    Button cinco = new Button();
    Button seis = new Button();
    Button siete = new Button();
    Button ocho = new Button();
    Button nueve = new Button();
    Button c = new Button();
    Button borrar = new Button();
    Button decimal = new Button();
    Button suma = new Button();
    Button resta = new Button();
    Button multi = new Button();
    Button divis = new Button();
    Button abreParent = new Button();
    Button cierraParent = new Button();
    Button resultado = new Button();

    //----------------------------------------------
    //          Declaración de variables auxiliares 
    //----------------------------------------------  
    String actual;
    Expression res;
    int contador;
    double total;

    /*El método start es el punto de entrada para una aplicación JavaFX.
    Su función principal es inicializar y mostrar la interfaz 
    gráfica de usuario (GUI) de la aplicación. Se crea un diseño (layout), 
    se añaden controles (botones, etiquetas, campos, ...) y se crea la escena
    con un estilo, y se muestra el escenario.*/
    @Override
    public void start(Stage escenario) {
        // Creo el panel que irá en el escenario e indico unos márgenes apropiados
        // StackPane apila los botones uno encima de otro, GridPane dispone en filas y columnas
        GridPane root = new GridPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);
        // Indico que el tamaño del escenario no se pueda cambiar
        escenario.setResizable(false);
        // Añado el logo de la calculadora
        escenario.getIcons().add(new Image("file:src/tarea09/logoCalcu.png"));
        // Creo la escena
        Scene scene = new Scene(root, 350, 400);
        // Indico el título para la escena
        escenario.setTitle("Mi calculadora");
        // Añado la escena al escenario
        escenario.setScene(scene);
        // Cargo la hoja de estilo
        scene.getStylesheets().add(getClass().getResource("calculadora.css").toExternalForm());

        // Añado el texto a los botones
        cero.setText("0");
        uno.setText("1");
        dos.setText("2");
        tres.setText("3");
        cuatro.setText("4");
        cinco.setText("5");
        seis.setText("6");
        siete.setText("7");
        ocho.setText("8");
        nueve.setText("9");
        c.setText("C");
        borrar.setText("<");
        decimal.setText(".");
        suma.setText("+");
        resta.setText("-");
        multi.setText("*");
        divis.setText("/");
        abreParent.setText("(");
        cierraParent.setText(")");
        resultado.setText("=");

        // Añado e indico la posición de cada botón
        root.add(campo, 0, 0, 5, 1);
        root.add(siete, 0, 1);
        root.add(ocho, 1, 1);
        root.add(nueve, 2, 1);
        root.add(divis, 3, 1);
        root.add(abreParent, 4, 1);
        root.add(cuatro, 0, 2);
        root.add(cinco, 1, 2);
        root.add(seis, 2, 2);
        root.add(multi, 3, 2);
        root.add(cierraParent, 4, 2);
        root.add(uno, 0, 3);
        root.add(dos, 1, 3);
        root.add(tres, 2, 3);
        root.add(resta, 3, 3);
        root.add(decimal, 4, 3);
        root.add(cero, 0, 4);
        root.add(c, 1, 4);
        root.add(borrar, 2, 4);
        root.add(suma, 3, 4);
        root.add(resultado, 4, 4);

        // Añado la clase de mi hoja de estilos que quiero que se les aplique
        borrar.getStyleClass().add("limpiar");
        c.getStyleClass().add("limpiar");
        decimal.getStyleClass().add("operador");
        suma.getStyleClass().add("operador");
        resta.getStyleClass().add("operador");
        multi.getStyleClass().add("operador");
        divis.getStyleClass().add("operador");
        abreParent.getStyleClass().add("operador");
        cierraParent.getStyleClass().add("operador");
        resultado.getStyleClass().add("igual");

        /*  Creo el manejador de evento para la acción que se debe hacer al click. 
        Todos los botones llamarán al método procesodeEntrada indicando su 
        entrada correspondiente */
        cero.setOnAction(e -> procesoDeEntrada("0"));
        uno.setOnAction(e -> procesoDeEntrada("1"));
        dos.setOnAction(e -> procesoDeEntrada("2"));
        tres.setOnAction(e -> procesoDeEntrada("3"));
        cuatro.setOnAction(e -> procesoDeEntrada("4"));
        cinco.setOnAction(e -> procesoDeEntrada("5"));
        seis.setOnAction(e -> procesoDeEntrada("6"));
        siete.setOnAction(e -> procesoDeEntrada("7"));
        ocho.setOnAction(e -> procesoDeEntrada("8"));
        nueve.setOnAction(e -> procesoDeEntrada("9"));
        decimal.setOnAction(e -> procesoDeEntrada("."));
        suma.setOnAction(e -> procesoDeEntrada("+"));
        resta.setOnAction(e -> procesoDeEntrada("-"));
        multi.setOnAction(e -> procesoDeEntrada("*"));
        divis.setOnAction(e -> procesoDeEntrada("/"));
        abreParent.setOnAction(e -> procesoDeEntrada("("));
        cierraParent.setOnAction(e -> procesoDeEntrada(")"));
        c.setOnAction(e -> procesoDeEntrada("C"));
        borrar.setOnAction(e -> procesoDeEntrada("<"));
        resultado.setOnAction(e -> procesoDeEntrada("="));

        // Muestro el escenario
        escenario.show();
    }

    /**
     * El método procesoDeEntrada maneja la entrada de datos en una calculadora.
     * Toma una cadena de texto (entrada) y realiza diferentes acciones según el
     * contenido de esa cadena, agregando al campo de texto, estableciendo el
     * estado, controlando la adición de puntos decimales para evitar múltiples
     * decimales en un número o evaluando la expresión mátemática para mostrar
     * el resultado haciendo uso de la librería Exp4J.
     *
     * @param entrada Texto recogido de los diferentes textoBotones de la
     * calculadora.
     */
    public void procesoDeEntrada(String entrada) {
        actual = campo.getText();
        
        

        // Utilizo un condicional if-else para comprobar qué entrada estamos recibiendo
        try {
            if (entrada.equals("C")) {
                // Si C se borra todo el contenido
                campo.setText("");
            } else if (entrada.equals("<")) {
                // Si < se borra el último carácter si existe
                if (!actual.isEmpty()) {
                    /*  Con substring() creo otro string a partir de la posición 0 de 
                    actual hasta la penúltima posición (largo actual - 1) */
                    campo.setText(actual.substring(0, actual.length() - 1));
                }
            } else if (entrada.equals("(")) {
                campo.setText(actual + entrada);
            } else if (entrada.equals(")")) {
                // Si ) se agrega un paréntesis de cierre si existe uno abierto sin cerrar
                if (checkParentesis(actual) > 0) {
                    campo.setText(actual + entrada);
                }
            } else if (entrada.equals("=") && (actual.matches("[0-9+\\-*/().]+"))) {
                //  Si = se realiza el cálculo y se muestra el resultado.
                /* Compruebo que todos los caracteres que hay en entrada sean válidos */
                if (checkParentesis(actual) != 0) {
                    campo.setText("Error en la expresión");
                } else {
                    res = new ExpressionBuilder(actual).build();
                    total = res.evaluate();
                    campo.setText(actual + "=" + total);
                }
            } else if ((entrada.equals("+") || entrada.equals("-") || entrada.equals("*") || entrada.equals("/")) && !actual.isEmpty()) {
                // Si +, -, * ó /, se añadirá el operador si el último carácter es un dígito o un paréntesis de cierre.

                if (Character.isDigit(actual.charAt(actual.length() - 1)) || actual.charAt(actual.length() - 1) == ')') {
                    campo.setText(actual + entrada);
                }
            } else if (entrada.equals(".") && !actual.isEmpty()) {
                // Sólo se añade . si el último caracter es un dígito

                if (Character.isDigit(actual.charAt(actual.length() - 1))) {
                    campo.setText(actual + entrada);
                }
            } else if (Character.isDigit(entrada.charAt(entrada.length() - 1))) {

                campo.setText(actual + entrada);
            }
            campo.positionCaret(campo.getText().length());
        } catch (Exception e) {
            campo.setText("Error en la expresión");
        }
    }

    /**
     * Método que comprueba si los paréntesis están cerrados
     *
     * @param entrada Entrada del usuario
     */
    private int checkParentesis(String entrada) {
        /*  Recorro cada caracter de la cadena entrada y si encuentro una apertura
            de paréntesis, sumo, si encuentro un cierre, resto. Si todos los 
            paréntesis están abiertos y cerrados correctamente, debe devolver un
            0, si no devolverá el número de paréntesis sin cerrar que hay */
        contador = 0;

        for (char ch : entrada.toCharArray()) {
            if (ch == '(') {
                contador++;
            } else if (ch == ')') {
                contador--;
            }
        }
        return contador;
    }

    /**
     * Programa principal, lanza la aplicación.
     *
     * @param args Argumentos o parámetros (no hay en este caso)
     */
    public static void main(String[] args) {
        launch(args);
    }
}
