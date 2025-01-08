
package uiMain;
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
                        Factura.mostrarProductosFactura(factura);
    
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
                                System.out.println("Indique el motivo de la devolución: ");
                                System.out.println(">1. El producto no es lo que esperaba.\n>2. El producto llegó en mal estado.\n>3. Hubo un retraso en la entrega.\n>4. Otro.");
                                int motivoDevolucion=sc.nextInt();
                                switch (motivoDevolucion) {
                                    case 1:
                                        producto.setMotivoDevolucion("El producto no es lo que esperaba.");
                                        break;
                                    case 2:
                                        producto.setMotivoDevolucion("El producto llegó en mal estado.");
                                        break;
                                    case 3:
                                        producto.setMotivoDevolucion("Hubo un retraso en la entrega.");
                                        break;
                                    case 4:
                                        String motivo = sc.nextLine();
                                    producto.setMotivoDevolucion(motivo);
                                    break;
                                }
                                Tienda tienda=factura.getTienda();
                                System.out.println("¿Desea cambiar el producto o que se le devuelva el dinero? \n1. Cambiar el producto \n2. Devolver el dinero");
                                int opcion3;
                                opcion3=sc.nextInt();
                                switch (opcion3){
                                    case 1:
                             
                                    Cliente cliente=tienda.devolverProducto(factura, producto);
                                    double valorADevolver=Fabrica.descontarDineroCuenta(producto); // Se descuenta el dinero de la cuenta de la fábrica
                                    // y se obtiene el valor del producto a devolver.
                                    break ; 
                                
                                    case 2: 
                                    String texto=tienda.mostrarProductosConExcedente(factura, producto);
                                    System.out.println(texto);
                                    int opcion4=sc.nextInt();
                                    Producto p =tienda.seleccionarProducto(opcion4);
                                    int excedenteAcumulado=producto.getPrecio()-p.getPrecio();
                                    while() //PENDIENTE EL CICLO WHILE 
                                    if (p.getPrecio()<producto.getPrecio()){
                                        System.out.println("El producto que seleccionó tiene un preccio menor al que desea devolver, ¿Desea añadir otro? \nNota: El dinero restante NO se le devolverá y es posible que le toque pagar un excedente.\n 1. Sí \n 2. No");
                                        int opcion5=sc.nextInt();
                                        if (opcion5==1)

                                        //QUE FALTA POR HACER: 
                                        //CREAR EL METODO QUE CALCULE EL EXCEDENTE ACUMULADO DEL ARRAYLIST DE PRODUCTOS SELECCIONADOS POR EL CLIENTE 
                                        //CREAR EL METODO QUE DEVUELVA EL DINERO AL CLIENTE
                                    
                                    }

                                    break;
                                }
                              
                                                                    // Se devuelve el producto a la tienda y se obtiene el cliente.
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