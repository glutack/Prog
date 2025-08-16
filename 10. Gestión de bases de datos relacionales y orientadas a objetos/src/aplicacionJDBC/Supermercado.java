package aplicacionJDBC;

import util.Color;
import H2.H2JDBC;
import java.sql.*;

/**
 * Clase que contiene métodos estáticos para realizar diversas consultas y operaciones
 * sobre la base de datos del supermercado, tales como listar productos, empleados,
 * y calcular valores totales relacionados con los productos y empleados de diferentes secciones.
 * 
 * @author IES Aguadulce
 * @version abril/2025
 */
public class Supermercado {

    /**
     * Constructor privado para evitar la creación de instancias de esta clase.
     */
    private Supermercado() {
    }

    /**
     * Realiza una consulta de todos los productos de la base de datos y los ordena
     * según el parámetro proporcionado.
     * 
     * @param orderBy El criterio por el cual se ordenarán los productos
     * @return Un String con la lista de productos ordenada, o un mensaje de error si ocurre un fallo
     */
    public static String selectAllProductosOrderBy(String orderBy) {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Productos ordenados por %s%n", orderBy));

        // Definir el formato de las columnas
        String formatoCabecera = "%-6s %-40s %9s %6s %-7s%n";
        String formatoFila = "%-6s %-40s %9.2f %6d %7s%n";

        // Agregar cabecera
        resultadoSelect.append(Color.azul(String.format(formatoCabecera,
                "CÓDIGO",
                "DESCRIPCIÓN",
                "PRECIO",
                "STOCK",
                "SECCIÓN")));

        String sentenciaSQL = "SELECT * FROM producto ORDER BY " + orderBy + " ASC";

        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(sentenciaSQL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultadoSelect.append(String.format(formatoFila,
                        rs.getString("id_producto"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio"),
                        rs.getInt("stock_actual"),
                        rs.getString("id_seccion")));
            }
        } catch (SQLException ex) {
            resultadoSelect.append(Color.rojo(String.format("ERROR al ordenar por %s.%n", orderBy)));
        }
        return resultadoSelect.toString();
    }

    /**
     * Calcula el valor total de los productos en el supermercado (precio * stock_actual).
     * 
     * @return Un String con el valor total de los productos o un mensaje de error si ocurre un fallo
     */
    public static String valorStockTotal() {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Valor total de todos productos del supermercado: "));
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(String.format("SELECT SUM(precio*stock_actual) AS valorStock FROM producto"))) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultadoSelect.append(String.format("%,.2f€", rs.getDouble("valorStock")));
                resultadoSelect.append("\n");
            }
        } catch (SQLException ex) {
            resultadoSelect.append(String.format("ERROR: no se puede obtener el valor del supermercado.")).append("\n");
        }
        return resultadoSelect.toString();
    }

    /**
     * Calcula el valor total de los productos de una sección específica (precio * stock_actual).
     * 
     * @param id_seccion El identificador de la sección cuyo valor total se calculará
     * @return Un String con el valor total de los productos de la sección o un mensaje de error si ocurre un fallo
     */
    public static String valorStockSeccion(String id_seccion) {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Valor de los productos de la sección [%s]:", Supermercado.descripcionSeccion(id_seccion)));
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(String.format("SELECT SUM(precio*stock_actual) AS valorStockSeccion FROM producto WHERE id_seccion=?"))) {
            st.setString(1, id_seccion);
            ResultSet rs = st.executeQuery();
            if (Supermercado.descripcionSeccion(id_seccion).isEmpty()) { // Verifica que la sección existe
                resultadoSelect.append(Color.rojo("ERROR: La sección no existe."));
            } else {
                while (rs.next()) {
                    resultadoSelect.append(String.format("%,.2f€", rs.getDouble("valorStockSeccion")));
                    resultadoSelect.append("\n");
                }
            }
        } catch (SQLException ex) {
            resultadoSelect.append(Color.rojo(String.format("ERROR: No se puede obtener el valor de la sección.\n")));
        }
        return resultadoSelect.toString();
    }

    /**
     * Devuelve una lista de los productos de una sección específica.
     * 
     * @param id_seccion El identificador de la sección cuyos productos se mostrarán
     * @return Un String con la lista de productos de la sección o un mensaje de error si ocurre un fallo
     */
    public static String productosDeSección(String id_seccion) {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Lista de productos de la sección [%s]\n", Supermercado.descripcionSeccion(id_seccion)));
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement("SELECT * FROM producto WHERE UPPER(id_seccion) LIKE ? ORDER BY descripcion")) {
            st.setString(1, id_seccion);
            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst()) { // Verifica si hay al menos una fila en el resultset
                resultadoSelect.append(Color.rojo("No hay productos en esta sección o la sección no existe."));
            } else {
                resultadoSelect.append(String.format("%-40s %-6s %-5s\n", "DESCRIPCIÓN", "PRECIO", "STOCK"));

                // Filas de productos con formato de ancho fijo
                while (rs.next()) {
                    resultadoSelect.append(String.format("%-40s %6.2f %5d\n",
                            rs.getString("descripcion"),
                            rs.getFloat("precio"),
                            rs.getInt("stock_actual")));
                }
            }
        } catch (SQLException ex) {
            resultadoSelect.append(String.format("ERROR: no se pueden listar los productos de la sección %s.", id_seccion)).append("\n");
        }
        return resultadoSelect.toString();
    }

    /**
     * Devuelve la descripción de una sección según su identificador.
     * 
     * @param id_seccion El identificador de la sección
     * @return La descripción de la sección o un mensaje de error si no existe
     */
    public static String descripcionSeccion(String id_seccion) {
        StringBuilder resultadoSelect = new StringBuilder();

        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement("SELECT descripcion FROM seccion WHERE id_seccion = ?")) {
            st.setString(1, id_seccion);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultadoSelect.append(rs.getString("descripcion"));
            }
        } catch (SQLException ex) {
            resultadoSelect.append(String.format("ERROR: no se pueden listar los productos de la sección %s.", id_seccion)).append("\n");
        }
        return resultadoSelect.toString();
    }

    /**
     * Actualiza el precio de todos los productos de una sección, aplicando un porcentaje de aumento.
     * 
     * @param id_seccion El identificador de la sección cuyos productos se actualizarán
     * @param porcentaje El porcentaje de aumento a aplicar
     * @return Un String con el resultado de la operación
     */
    public static String actualizarPrecioSeccion(String id_seccion, double porcentaje) {
        StringBuilder resultado = new StringBuilder();

        String sentenciaSQL = "UPDATE producto SET precio = precio + (precio * ? / 100) WHERE id_seccion = ?";

        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(sentenciaSQL)) {
            st.setDouble(1, porcentaje);
            st.setString(2, id_seccion);
            int filasAfectadas = st.executeUpdate();

            resultado.append(String.format("Actualizando los precios de %d productos en la sección %s...", filasAfectadas, Supermercado.descripcionSeccion(id_seccion)));
            if (filasAfectadas > 0) {
                resultado.append(Color.verde("OK"));
            } else {
                resultado.append(Color.rojo(String.format("La sección no tiene productos.")));
            }
        } catch (SQLException e) {
            resultado.append(Color.rojo(String.format("ERROR: Falló la actualización de los precios en la sección %s: %s%n", id_seccion, e.getMessage())));
        }
        return resultado.toString();
    }

    /**
     * Realiza una consulta de todos los empleados de la base de datos y los
     * ordena según el parámetro proporcionado.
     * @param orderBy El criterio por el cual se ordenarán los empleados
     * @return Un String con la lista de empleados ordenada, o un mensaje de error si ocurre un fallo
     */
    public static String selectAllEmpleadosOrderBy(String orderBy) {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Empleados ordenados por %s%n", orderBy));

        // Definir el formato de las columnas
        String formato = "%-6s %-30s %13s %-7s %n";

        // Agregar cabecera
        resultadoSelect.append(Color.azul(String.format(formato,
                "CÓDIGO",
                "NOMBRE",
                "SALARIO ANUAL",
                "SECCIÓN")));

        String sentenciaSQL = "SELECT * FROM empleado ORDER BY " + orderBy + " ASC";

        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(sentenciaSQL)) {
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                resultadoSelect.append(String.format(formato,
                        rs.getString("id_empleado"),
                        rs.getString("nombre"),
                        rs.getInt("salario_anual"),
                        rs.getString("id_seccion")));
            }
        } catch (SQLException ex) {
            resultadoSelect.append(Color.rojo(String.format("ERROR al ordenar por %s.%n", orderBy)));
        }
        return resultadoSelect.toString();
    }
    
    /**
     * Devuelve una lista de los empleados de una sección específica.
     * @param id_seccion El identificador de la sección cuyos empleados se mostrarán
     * @return Un String con la lista de empleados de la sección o un mensaje de error si ocurre un fallo
     */
    public static String empleadosDeSección(String id_seccion) {
        StringBuilder resultadoSelect = new StringBuilder();

        resultadoSelect.append(String.format("Lista de empleados de la sección [%s]\n", Supermercado.descripcionSeccion(id_seccion)));
        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement("SELECT * FROM empleado WHERE UPPER(id_seccion) LIKE ? ORDER BY id_empleado")) {
            st.setString(1, id_seccion);
            ResultSet rs = st.executeQuery();
            if (!rs.isBeforeFirst()) { 
                resultadoSelect.append(Color.rojo("No hay empleados en esta sección o la sección no existe."));
            } else {
                resultadoSelect.append(Color.azul(String.format("%-30s %13s \n", "NOMBRE", "SALARIO ANUAL")));

                while (rs.next()) {
                    resultadoSelect.append(String.format("%-30s %13s \n",
                            rs.getString("nombre"),
                            rs.getInt("salario_anual")));
                }
            }
        } catch (SQLException ex) {
            resultadoSelect.append(String.format("ERROR: no se pueden listar los empleados de la sección %s.", id_seccion)).append("\n");
        }
        return resultadoSelect.toString();
    }
    
    /**
     * Aumenta el salario de todos los empleados de una sección aplicando un
     * porcentaje.
     * @param id_seccion - El identificador de la sección cuyos empleados tendrán su salario aumentado
     * @param porcentaje - El porcentaje de aumento a aplicar
     * @return Un String con el resultado de la operación
     */
    public static String aumentarSalarioSeccion(String id_seccion, double porcentaje) {
        StringBuilder resultado = new StringBuilder();

        String sentenciaSQL = "UPDATE empleado SET salario_anual = salario_anual + (salario_anual * ? / 100) WHERE id_seccion = ?";

        try (PreparedStatement st = H2JDBC.getConexion().prepareStatement(sentenciaSQL)) {
            st.setDouble(1, porcentaje);
            st.setString(2, id_seccion);
            int filasAfectadas = st.executeUpdate();

            resultado.append(String.format("Actualizando los salarios de %d empleados en la sección %s...", filasAfectadas, Supermercado.descripcionSeccion(id_seccion)));
            if (filasAfectadas > 0) {
                resultado.append(Color.verde("OK"));
            } else {
                resultado.append(Color.rojo(String.format("La sección no tiene empleados.")));
            }
        } catch (SQLException e) {
            resultado.append(Color.rojo(String.format("ERROR: Falló la actualización de los salarios en la sección %s: %s%n", id_seccion, e.getMessage())));
        }
        return resultado.toString();
    }
}
