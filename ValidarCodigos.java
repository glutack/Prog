
public class ValidarCodigos {
    public static void main(String[] args) {
        /* El patrón está dividio en pares e impares: 
         * 1 - Empieza (^()) por la letra X, seguido de una letra de la 
         *  A a la Z ([A-Z]) en mayúsculas, lo sigue un guion (-) y tras
         *  este irán los posibles años:
         * - 1970-79: 197 seguido de un 1, 3, 5, 7 ó 9 ([13579]) 
         * ó (|)
         * - 1980-99: 19 seguido de un 8 ó 9 ([89]) y los números impares
         * - 2000-19: 20 seguido de un 0 ó 1 ([01]) y los números impares
         * - 2020-25: 202 seguido de los números 1, 3 ó 5 ([135])
         * 2 - Se da una segunda opción al comienzo (|), que empieza por
         *  la letra Y, y se aplica el mismo patrón que al anterior pero
         *  cambiando los números impares por pares
         */
        final String PATRON = "^(X[A-Z]-(197[13579]|19[89][13579]|20[01][13579]|202[135])|Y[A-Z]-(197[02468]|19[89][02468]|20[01][02468]|202[024]))$";
        String entrada[] = {"ZA-2000", "XAZ2000", "XA2000", "XA-1969", "YH-1969", "XQ-1970", "XJ-2002", "YV-2021", "XB-2022", "YV-2042", "YA-1970", "YH-2002", "XB-2021"};

        System.out.println("VALIDADOR DE NÚMEROS DE SERIE");
        System.out.println("-----------------------------");
        System.out.println("Lista de números de serie de prueba es:");
        System.out.print("[");
        for (int i = 0; i < entrada.length; i++) {
            System.out.printf("%s", entrada[i]);
            if (i < entrada.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("\nRESULTADO");
        System.out.println("-----------------------------");
        System.out.println("El resultado de la comprobación de la validez de los números de serie es:");
        System.out.print("[");
        for (int i = 0; i < entrada.length; i++) {
            if (entrada[i].matches(PATRON)) {
                System.out.print("válido");
            } else {
                System.out.print("inválido");
            }
            if (i < entrada.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");

    }
}