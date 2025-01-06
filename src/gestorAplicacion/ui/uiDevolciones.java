
package ui;
import java.util.Scanner;

import gestion.Factura;
import produccion.*;
import gestion.Cliente;

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
                            if (Factura.todosDevueltos(factura.getListaProductos())) {
                                System.out.println("Todos los productos de esta factura ya han sido devueltos.");
                                break;
                            }
                            Producto producto = factura.seleccionarProducto(opcion2-1);
                            if (producto.getEstado().equals("DEVUELTO")) {
                                System.out.println("El producto ya ha sido devuelto, elija otro.");
                            } else{
                                System.out.println("Eligió el producto: " + producto.getNombre());
                                
                                double valorADevolver=Fabrica.descontarDineroCuenta(producto); // Se descuenta el dinero de la cuenta de la fábrica
                                // y se obtiene el valor del producto a devolver.
                                Tienda tienda=factura.getTienda();
                                Cliente cliente=tienda.devolverProducto(factura, producto); // Se devuelve el producto a la tienda y se obtiene el cliente.
                                Fabrica.cuentaBancaria.devolverDinero(valorADevolver, cliente); 
                                cliente.removerProducto(producto); // Se remueve el producto de la lista de productos del cliente.
                                System.out.println("El producto ha sido devuelto exitosamente.");
                                System.out.println("¿Qué desea hacer? \n1. Devolver otro producto \n0. Regresar al menú de facturas");
                                int opcion3;
                                switch (opcion3 = sc.nextInt()) {
                                    case 0:
                                        System.out.println("Saliendo del menú de devoluciones.");
                                        break;
                                    case 1:
                                        ui.uiDevolciones.devolver();
                                    default:
                                        System.out.println("Opción inválida. Intente nuevamente.");
                                        break;
                                }
                            
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