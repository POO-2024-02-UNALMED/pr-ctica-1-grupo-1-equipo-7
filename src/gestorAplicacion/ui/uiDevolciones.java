
package ui;
import java.util.Scanner;

import gestion.Factura;
import produccion.*;

public class uiDevolciones {

        public static void devolver() {
            Scanner sc = new Scanner(System.in);
    
            while (true) {
                System.out.println("Eligió la opción de devoluciones.\nSeleccione la factura que desea consultar. Oprima 0 para salir.");
                System.out.println("0. Salir");
                Factura.mostrarFacturas();
    
                int opcion;
                try {
                    opcion = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                    continue;
                }
    
                if (opcion == 0) {
                    System.out.println("Saliendo del menú de devoluciones.");
                    break;
                }
    
                if (opcion > 0 && opcion <= Factura.listaFacturas.size()) {
                    System.out.println("Eligió la factura con el número: " + opcion);
                    Factura factura = Factura.seleccionarFactura(opcion);
    
                    while (true) {
                        System.out.println("Seleccione el producto que desea devolver o presione 0 para regresar al menú anterior: ");
                        Factura.mostrarProductos(factura);
    
                        int opcion2;
                        try {
                            opcion2 = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número.");
                            sc.nextLine(); // Limpiar el buffer
                            continue;
                        }
    
                        if (opcion2 == 0) {
                            System.out.println("Regresando al menú de facturas.");
                            break;
                        }
    
                        if (opcion2 > 0 && opcion2 <= factura.getListaProductos().size()) {
                            if (factura.todosDevueltos()) {
                                System.out.println("Todos los productos de esta factura ya han sido devueltos.");
                                break;
                            }
                            Producto producto = factura.seleccionarProducto(opcion2-1);
                            if (producto.getEstado().equals("DEVUELTO")) {
                                System.out.println("El producto ya ha sido devuelto, elija otro.");
                            } else{
                                System.out.println("Eligió el producto: " + producto.getNombre());
                                //logica para devolver el producto
                            }
                        } else {
                            System.out.println("Opción inválida. Intente nuevamente.");
                        }
                    }
                } else {
                    System.out.println("Opción inválida. Por favor, elija un número de factura válido.");
                }
            }
            sc.close();
        }
}    