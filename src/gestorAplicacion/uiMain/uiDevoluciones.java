package uiMain;

import java.util.ArrayList;
import java.util.Scanner;
import gestion.Factura;
import produccion.*;
import gestion.Cliente;

public class uiDevoluciones {

    /**
     * Este es el punto de entrada principal para gestionar las devoluciones.
     * Permite al usuario seleccionar una factura para proceder con las devoluciones o intercambios.
     */
    public static void devolver() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Eligió la opción de devoluciones.");
            System.out.println("Seleccione la factura que desea consultar. Oprima 0 para salir.");
            Factura.mostrarFacturas();

            int opcion = leerOpcion(sc);
            if (opcion == 0) {
                System.out.println("Saliendo del menú de devoluciones.");
                break;
            }

            if (opcion > 0 && opcion <= Factura.listaFacturas.size()) {
                Factura factura = Factura.seleccionarFactura(opcion);
                manejarFactura(sc, factura);

                // Preguntar si desea realizar otra operación después de completar
                System.out.println("¿Desea realizar otra operación? (1: Sí, 0: No)");
                int continuar = leerOpcion(sc);
                if (continuar == 0) {
                    System.out.println("Saliendo del menú de devoluciones.");
                    break;
                }
            } else {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    /**
     * Gestiona la lógica de selección de productos dentro de una factura.
     * Proporciona al usuario la opción de regresar al menú de facturas.
     * 
     * @param sc Scanner para leer entradas del usuario.
     * @param factura Factura seleccionada por el usuario.
     */
    private static void manejarFactura(Scanner sc, Factura factura) {
        while (true) {
            System.out.println("Seleccione el producto que desea devolver o presione 0 para regresar al menú anterior: ");
            Factura.mostrarProductosFactura(factura);

            int opcionProducto = leerOpcion(sc);
            if (opcionProducto == 0) {
                System.out.println("Regresando al menú de facturas.");
                break;
            }

            if (opcionProducto > 0 && opcionProducto <= factura.getListaProductos().size()) {
                Producto producto = factura.seleccionarProducto(opcionProducto - 1);
                manejarProducto(sc, factura, producto);

                // Preguntar si desea realizar otra operación después de completar
                System.out.println("¿Desea realizar otra operación? (1: Sí, 0: No)");
                int continuar = leerOpcion(sc);
                if (continuar == 0) {
                    System.out.println("Regresando al menú de facturas.");
                    break;
                }
            } else {
                System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    /**
     * Gestiona la lógica de devolución de un producto específico.
     * Diferencia entre reembolsos y cambios según el motivo indicado por el usuario.
     * 
     * @param sc Scanner para leer entradas del usuario.
     * @param factura Factura a la que pertenece el producto.
     * @param producto Producto seleccionado para la devolución.
     */
    private static void manejarProducto(Scanner sc, Factura factura, Producto producto) {
        if (producto.getEstado().equals("DEVUELTO")) {
            System.out.println("El producto ya ha sido devuelto. Elija otro producto.");
            return;
        }

        System.out.println("Eligió el producto: " + producto.getNombre());
        System.out.println("Indique el motivo de la devolución: ");
        String motivosDevolucion = Producto.mostrarMotivosDeDevolucion();
        System.out.println("Motivos de devolución: \n" + motivosDevolucion);

        int motivoDevolucion = leerOpcion(sc);
        String motivo = Producto.obtenerMotivoDeDevolucion(motivoDevolucion);

        if (motivo == null) {
            System.out.println("Motivo de devolución inválido. Intente nuevamente.");
            return;
        }

        producto.setMotivoDevolucion(motivo);

        if (motivoDevolucion == 1 || motivoDevolucion == 2 || motivoDevolucion == 3) {
            procesarReembolso(sc, factura, producto);
        } else if (motivoDevolucion == 4 || motivoDevolucion == 5) {
            procesarCambio(sc, factura, producto);
        } else {
            System.out.println("Motivo de devolución no soportado. Intente nuevamente.");
        }
    }

    /**
     * Realiza el reembolso del dinero al cliente por el producto devuelto.
     * Actualiza las cuentas y elimina el producto de la lista del cliente.
     * 
     * @param sc Scanner para leer entradas del usuario.
     * @param factura Factura a la que pertenece el producto.
     * @param producto Producto que se devuelve.
     */
    private static void procesarReembolso(Scanner sc, Factura factura, Producto producto) {
        Tienda tienda = factura.getTienda();
        Cliente cliente = tienda.devolverProducto(factura, producto);
        double valorADevolver = Fabrica.descontarDineroCuenta(producto);
        Fabrica.cuentaBancaria.devolverDinero(valorADevolver, cliente);
        cliente.removerProducto(producto);

        System.out.println("El producto ha sido devuelto exitosamente y se ha reembolsado el dinero.");
    }

    /**
     * Administra el proceso de cambio de producto.
     * Permite al usuario seleccionar productos de reemplazo respetando los límites de precio.
     * 
     * @param sc Scanner para leer entradas del usuario.
     * @param factura Factura a la que pertenece el producto original.
     * @param producto Producto que se desea cambiar.
     */
    private static void procesarCambio(Scanner sc, Factura factura, Producto producto) {
        Tienda tienda = factura.getTienda();
        double precio = producto.getPrecio();

        System.out.println("Seleccione los productos por los cuales desea realizar el cambio.");
        ArrayList<Integer> seleccionProductos = new ArrayList<>();
        ArrayList<Producto> carrito;

        while (true) {
            System.out.println("Productos disponibles para cambio:");
            tienda.mostrarProductos();

            int opcionCambio = leerOpcion(sc);
            if (opcionCambio == 0) {
                System.out.println("Saliendo del menú de cambio.");
                break;
            }

            seleccionProductos.add(opcionCambio);
            carrito = tienda.añadirProductosParaCambio(precio, seleccionProductos);

            double subtotal = 0;
            System.out.println("\nResumen del cambio:");
            for (Producto p : carrito) {
                System.out.println("- " + p.getNombre() + ": $" + p.getPrecio());
                subtotal += p.getPrecio();
            }
            System.out.println("Subtotal actual: $" + subtotal);

            if (subtotal >= precio) {
                System.out.println("El subtotal ha alcanzado o superado el valor límite. El proceso ha finalizado.");
                break;
            }

            System.out.println("¿Desea continuar añadiendo productos? (1: Sí, 0: No)");
            int continuar = leerOpcion(sc);
            if (continuar == 0) {
                System.out.println("Proceso finalizado. Su carrito de cambio está listo.");
                break;
            }
        }

        // Preguntar si desea realizar otra operación
        System.out.println("¿Desea realizar otra operación? (1: Sí, 0: No)");
        int continuar = leerOpcion(sc);
        if (continuar == 0) {
            System.out.println("Saliendo del menú de devoluciones.");
            return;
        }
    }

    /**
     * Lee una opción ingresada por el usuario desde el teclado.
     * Maneja entradas inválidas y proporciona un valor predeterminado en caso de error.
     * 
     * @param sc Scanner para leer entradas.
     * @return Número entero ingresado por el usuario o -1 si ocurre un error.
     */
    private static int leerOpcion(Scanner sc) {
        try {
            return sc.nextInt();
        } catch (Exception e) {
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            sc.nextLine(); // Limpiar el buffer
            return -1;
        }
    }
}
