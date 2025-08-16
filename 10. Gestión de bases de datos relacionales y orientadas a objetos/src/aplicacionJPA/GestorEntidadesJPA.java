package aplicacionJPA;

import util.EntradaTeclado;
import util.Color;
import JPA.GestorDePersistenciaGenerico;
import entidadesJPA.*;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que gestiona las operaciones CRUD sobre las entidades Sección, Producto
 * y Empleado utilizando el gestor de persistencia genérico con JPA.
 *
 * <p>
 * Proporciona métodos para insertar, consultar, modificar y eliminar registros
 * de cada entidad mediante entrada desde consola.</p>
 *
 * @author IES Aguadulce
 * @version abril/2025
 */
public class GestorEntidadesJPA {

    /**
     * DAO (Data Access Object) para la entidad Seccion
     */
    private static final GestorDePersistenciaGenerico<Seccion> seccionDAO = new GestorDePersistenciaGenerico<>(Seccion.class);
    /**
     * DAO (Data Access Object) para la entidad producto
     */
    private static final GestorDePersistenciaGenerico<Producto> productoDAO = new GestorDePersistenciaGenerico<>(Producto.class);
    /**
     * DAO (Data Access Object) para la entidad producto
     */
    private static final GestorDePersistenciaGenerico<Empleado> empleadoDAO = new GestorDePersistenciaGenerico<>(Empleado.class);
    
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Inserta una nueva sección mediante datos introducidos por el usuario.
     */
    public static void insertarSeccion() {
        String idSeccion = EntradaTeclado.cadenaLimitada("Código de sección:", 2,2);
        String descripcion = EntradaTeclado.cadenaLimitada("Descripción:", 0,50);

        Seccion seccion = new Seccion();
        seccion.setIdSeccion(idSeccion);
        seccion.setDescripcion(descripcion);

        System.out.println("---------------------------------------------");
        if (seccionDAO.insertar(seccion)) {
            System.out.println(Color.verde("OK: Sección añadida."));
        } else {
            System.out.println(Color.rojo("ERROR: No se pudo insertar la sección."));
            if (seccionDAO.buscar(idSeccion) != null) {
                System.out.println(Color.rojo(String.format("  --> La sección %s ya existe.", idSeccion)));
            }
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * Consulta e imprime todas las secciones almacenadas.
     */
    public static void consultarSecciones() {
        List<Seccion> secciones = seccionDAO.listarTodos();

        // Imprimir los encabezados de las columnas
        System.out.println(Color.azul(String.format("%-7s %-15s", "SECCIÓN", "DESCRIPCIÓN")));
        // Imprimir los valores de las secciones
        for (Seccion s : secciones) {
            System.out.printf("%-7s %-15s%n",
                    s.getIdSeccion(),
                    s.getDescripcion());
        }
    }

    /**
     * Elimina una sección identificada por su código.
     */
    public static void eliminarSeccion() {
        System.out.print("ATENCIÓN: se eliminarán todos los productos de la sección.\n");
        String idSeccion = EntradaTeclado.cadenaLimitada("Código de sección", 2,2);

        // Usando el DAO genérico
        System.out.println("---------------------------------------------");
        if (seccionDAO.eliminar(idSeccion)) {
            System.out.println(Color.verde("OK: Sección eliminada con éxito."));
        } else {
            System.out.println(Color.rojo("ERROR: No se pudo eliminar la sección."));
            if (seccionDAO.buscar(idSeccion) == null) {
                System.out.println(Color.rojo(String.format("  --> La sección %s no existe.", idSeccion)));
            }
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * Modifica la descripción de una sección existente.
     */
    public static void modificarSeccion() {
        String idSeccion = EntradaTeclado.cadenaLimitada("Código de sección:", 2,2);
        Seccion seccion = seccionDAO.buscar(idSeccion);
        if (seccion == null) {
            System.out.printf(Color.rojo("ERROR: La sección %s no existe.\n"), idSeccion);
        } else {
            System.out.printf("Descripción actual:%s\n", seccion.getDescripcion());
            String descripcion = EntradaTeclado.cadenaLimitada("Nueva descripción, [ENTER] para dejar igual:",0, 15);

            //Si el usuario pulsó la tecla [ENTER] con la sección en blanco se deja como estaba
            if (!descripcion.isEmpty()) {
                seccion.setDescripcion(descripcion);
            }

            // Usando el DAO genérico
            System.out.println("---------------------------------------------");
            if (seccionDAO.actualizar(seccion)) {
                System.out.println(Color.verde("OK: Sección actualizada."));
            } else {
                System.out.println(Color.rojo("ERROR: No se pudo actualizar la sección."));
            }
            System.out.println("---------------------------------------------");
        }
    }

    /**
     * Inserta un nuevo producto mediante datos introducidos por el usuario.
     */
    public static void insertarProducto() {
        String idProducto = EntradaTeclado.cadenaLimitada("Código de producto:", 4, 4); //Longitud del código de producto 4 caracteres
        String descripcion = EntradaTeclado.cadenaLimitada("Descripción:", 0, 40);
        double precio = EntradaTeclado.nDoublePositivo("Precio:", 2);
        int stockActual = EntradaTeclado.nEnteroPositivo("Stock actual:");
        String idSeccion = EntradaTeclado.cadenaLimitada("Código de sección:", 2, 2);

        Seccion seccion = seccionDAO.buscar(idSeccion);

        Producto producto = new Producto();
        producto.setIdProducto(idProducto);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStockActual(stockActual);
        producto.setSeccion(seccion);

        // Usando el DAO genérico
        System.out.println("---------------------------------------------");
        if (productoDAO.insertar(producto)) {
            System.out.println(Color.verde("OK: Producto añadido."));
        } else {
            System.out.println(Color.rojo("ERROR: No se pudo insertar el producto."));
            if (productoDAO.buscar(idProducto) != null) {
                System.out.println(Color.rojo(String.format("  --> El producto %s ya existe.", idProducto)));
            }
            if (seccionDAO.buscar(idSeccion) == null) {
                System.out.println(Color.rojo(String.format("  --> La sección %s no existe.", idSeccion)));
            }
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * Consulta e imprime los productos almacenados, opcionalmente filtrados por
     * sección.
     */
    public static void listarProductos() {
        List<Producto> productos = productoDAO.listarTodos();
        String idSeccion = EntradaTeclado.cadenaLimitada("Código de sección (dejar en blanco para todas):", 0,2);

        // Imprimir los encabezados de las columnas
        System.out.printf(
                "%n%-6s %-40s %9s %6s %-15s %n",
                "CÓDIGO",
                "DESCRIPCIÓN",
                "PRECIO",
                "STOCK",
                "SECCIÓN");

        // Imprimir los valores de los productos
        for (Producto p : productos) {
            if (idSeccion.equals(p.getSeccion().getIdSeccion()) || idSeccion.isEmpty()) {
                System.out.printf("%-6s %-40s %9.2f %6d %-15s %n",
                        p.getIdProducto(),
                        p.getDescripcion(),
                        p.getPrecio(),
                        p.getStockActual(),
                        p.getSeccion().getDescripcion());
            }
        }
    }

    /**
     * Consulta e imprime los datos de un producto por su código.
     */
    public static void consultarProducto() {
        String idProducto = EntradaTeclado.cadenaLimitada("Código de producto:", 4, 4);
        Producto producto = productoDAO.buscar(idProducto);

        if (producto != null) {
            System.out.printf("Descripción: %s\n", producto.getDescripcion());
            System.out.printf("Precio: %.2f\n", producto.getPrecio());
            System.out.printf("Stock: %d\n", producto.getStockActual());
            System.out.printf("Sección: %s-%s\n", producto.getSeccion().getIdSeccion(), producto.getSeccion().getDescripcion());
        } else {
            System.out.println(Color.rojo("ERROR: No existe ningún producto con código " + idProducto + "."));
        }
    }

    /**
     * Elimina un producto identificado por su código.
     */
    public static void eliminarProducto() {
        String idProducto = EntradaTeclado.cadenaLimitada("Código de producto:", 4, 4);  // Limitar a 4 caracteres

        // Usando el DAO genérico
        System.out.println("---------------------------------------------");
        if (productoDAO.eliminar(idProducto)) {
            System.out.println(Color.verde("OK: Producto eliminado."));
        } else {
            System.out.println(Color.rojo("ERROR: No se pudo eliminar el producto."));
            if (productoDAO.buscar(idProducto) == null) {
                System.out.printf(Color.rojo("  --> El producto %s no existe.\n"), idProducto);
            }
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * Modifica los atributos de un producto existente.
     */
    public static void modificarProducto() {
        String idProducto = EntradaTeclado.cadenaLimitada("Código de producto:", 4, 4);  // Limitar a 10 caracteres
        Producto producto = productoDAO.buscar(idProducto);
        if (producto == null) {
            System.out.printf(Color.rojo("ERROR: El producto %s no existe.\n"), idProducto);
        } else {

            System.out.printf("Descripción actual:%s\n", producto.getDescripcion());
            String descripcion = EntradaTeclado.cadenaLimitada("Nueva descripción, [ENTER] para dejar igual:", 0, 40);
            if (!descripcion.isEmpty()) {
                producto.setDescripcion(descripcion);
            }

            System.out.printf("Precio actual:%.2f\n", producto.getPrecio());
            double precio = EntradaTeclado.nDoublePositivo("Nuevo precio:", 2);
            producto.setPrecio(precio);

            System.out.printf("Stock actual:%d\n", producto.getStockActual());
            int stockActual = EntradaTeclado.nEnteroPositivo("Nuevo stock actual:");
            producto.setStockActual(stockActual);

            System.out.printf("Código de sección actual:%s\n", producto.getSeccion().getIdSeccion());
            String idSeccion = EntradaTeclado.cadenaLimitada("Nueva sección, [ENTER] para dejar igual:", 2, 2);  // Limitar a 2 caracteres
            if (!idSeccion.isEmpty()) {
                Seccion seccion = seccionDAO.buscar(idSeccion);
                producto.setSeccion(seccion);
            }
            
            System.out.println("---------------------------------------------");
            if (productoDAO.actualizar(producto)) {
                System.out.println(Color.verde("OK: Producto actualizado."));
            } else {
                System.out.println(Color.rojo("ERROR: No se pudo actualizar el producto."));
                if (seccionDAO.buscar(idSeccion) == null) {
                    System.out.printf(Color.rojo("  --> La sección %s no existe.\n"), idSeccion);
                }
            }
            System.out.println("---------------------------------------------");
        }
    }
        
    /**
     * Inserta un nuevo empleado mediante datos introducidos por el usuario.
     */
    public static void insertarEmpleado() {
        String idEmpleado = EntradaTeclado.cadenaLimitada("Código de empleado: ", 4, 4); // Limitar a 4 caracteres
        String nombre = EntradaTeclado.cadenaLimitada("Nombre: ", 0, 30);
        int salarioAnual = EntradaTeclado.nEnteroPositivo("Salario anual: ");
        String idSeccion = EntradaTeclado.cadenaLimitada("Código de sección: ", 2, 2);

        Seccion seccion = seccionDAO.buscar(idSeccion);

        Empleado empleado = new Empleado();
        empleado.setIdEmpleado(idEmpleado);
        empleado.setNombre(nombre);
        empleado.setSalarioAnual(salarioAnual);
        empleado.setSeccion(seccion);

        // Usando el DAO genérico
        System.out.println("---------------------------------------------");
        if (empleadoDAO.insertar(empleado)) {
            System.out.println(Color.verde("OK: Empleado añadido."));
        } else {
            System.out.println(Color.rojo("ERROR: No se pudo insertar el empleado."));
            if (empleadoDAO.buscar(idEmpleado) != null) {
                System.out.println(Color.rojo(String.format("  --> El empleado %s ya existe.", idEmpleado)));
            }
            if (seccionDAO.buscar(idSeccion) == null) {
                System.out.println(Color.rojo(String.format("  --> La sección %s no existe.", idSeccion)));
            }
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * Consulta e imprime los datos de un empleado por su código.
     */
    public static void consultarEmpleado() {
        String idEmpleado = EntradaTeclado.cadenaLimitada("Código de empleado: ", 4, 4);
        Empleado empleado = empleadoDAO.buscar(idEmpleado);

        if (empleado != null) {
            System.out.printf("Nombre: %s\n", empleado.getNombre());
            System.out.printf("Salario anual: %d\n", empleado.getSalarioAnual());
            System.out.printf("Sección: %s-%s\n", empleado.getSeccion().getIdSeccion(), empleado.getSeccion().getDescripcion());
        } else {
            System.out.println(Color.rojo("ERROR: No existe ningún empleado con código " + idEmpleado + "."));
        }
    } 

    /* Consulta e imprime los empleados almacenados, opcionalmente filtrados por sección.
     */
    public static void listarEmpleados() {
        List<Empleado> empleados = empleadoDAO.listarTodos();
        String idSeccion = EntradaTeclado.cadenaLimitada("Código de sección (dejar en blanco para todas):", 0,2);

        // Imprimir los encabezados de las columnas
        System.out.printf(
                "%n%-6s %-30s %13s %-7s %n",
                "CÓDIGO",
                "NOMBRE",
                "SALARIO",
                "SECCIÓN");

        // Imprimir los valores de los productos
        for (Empleado e : empleados) {
            if (idSeccion.equals(e.getSeccion().getIdSeccion()) || idSeccion.isEmpty()) {
                System.out.printf("%-6s %-30s %13s %-16s %n",
                        e.getIdEmpleado(),
                        e.getNombre(),
                        e.getSalarioAnual(),
                       (e.getSeccion().getIdSeccion() + "-" + e.getSeccion().getDescripcion()));
            }
        }
    }

    /**
     * Elimina un empleado identificado por su código.
     */
    public static void eliminarEmpleado() {
        String idEmpleado = EntradaTeclado.cadenaLimitada("Código de empleado:", 4, 4);  // Limitar a 4 caracteres

        // Usando el DAO genérico
        System.out.println("---------------------------------------------");
        if (empleadoDAO.eliminar(idEmpleado)) {
            System.out.println(Color.verde("OK: Empleado eliminado."));
        } else {
            System.out.println(Color.rojo("ERROR: No se pudo eliminar el empleado."));
            if (empleadoDAO.buscar(idEmpleado) == null) {
                System.out.printf(Color.rojo("  --> El empleado %s no existe.\n"), idEmpleado);
            }
        }
        System.out.println("---------------------------------------------");
    }

    /**
     * Modifica los atributos de un empleado existente.
     */
    public static void modificarEmpleado() {
        String idEmpleado = EntradaTeclado.cadenaLimitada("Código de empleado:", 4, 4);  // Limitar a 4 caracteres
        Empleado empleado = empleadoDAO.buscar(idEmpleado);
        if (empleado == null) {
            System.out.printf(Color.rojo("ERROR: El empleado %s no existe.\n"), idEmpleado);
        } else {
            System.out.printf("Nombre actual:%s\n", empleado.getNombre());
            String nombre = EntradaTeclado.cadenaLimitada("Nueva nombre, [ENTER] para dejar igual:", 0, 30);
            if (!nombre.isEmpty()) {
                empleado.setNombre(nombre);
            }

            System.out.printf("Salario actual:%d\n", empleado.getSalarioAnual());
            int salario = EntradaTeclado.nEnteroPositivo("Nuevo salario, [ENTER] para dejar igual:");
            empleado.setSalarioAnual(salario);

            System.out.printf("Código de sección actual:%s\n", empleado.getSeccion().getIdSeccion());
            String idSeccion = EntradaTeclado.cadenaLimitada("Nueva sección, [ENTER] para dejar igual:", 2, 2);  
            if (!idSeccion.isEmpty()) {
                Seccion seccion = seccionDAO.buscar(idSeccion);
                empleado.setSeccion(seccion);
            }
            
            System.out.println("---------------------------------------------");
            if (empleadoDAO.actualizar(empleado)) {
                System.out.println(Color.verde("OK: Empleado actualizado."));
            } else {
                System.out.println(Color.rojo("ERROR: No se pudo actualizar el empleado."));
                if (seccionDAO.buscar(idSeccion) == null) {
                    System.out.printf(Color.rojo("  --> La sección %s no existe.\n"), idSeccion);
                }
            }
            System.out.println("---------------------------------------------");
        }
    }
}
