package uiMain;
import java.util.ArrayList;
import java.util.Scanner;

import gestion.Factura;
import produccion.*;
import gestion.Cliente;

public class uiDevoluciones {

        public static void devolver() {
            Scanner sc = new Scanner(System.in);
    
            while (true) {
                System.out.println("Eligió la opción de devoluciones.\nSeleccione la factura que desea consultar. Oprima 0 para salir.");
                System.out.println("0. Salir");
                String txt=Factura.mostrarFacturas();
                System.out.println(txt);
    
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
                    Tienda tienda=factura.getTienda();

    
                    while (true) {
                        System.out.println("Seleccione el producto que desea devolver o presione 0 para regresar al menú anterior: ");
                        String productos=Factura.mostrarProductosFactura(factura);
                        System.out.println(productos);
    
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
                            Producto producto = factura.seleccionarProducto(opcion2);
                            if (producto.getEstado().equals("DEVUELTO")) {
                                System.out.println("El producto ya ha sido devuelto, elija otro.");
                            } else{
                                System.out.println("Eligió el producto: " + producto.getNombre());
                                System.out.println("Indique el motivo de la devolución: ");
                                String motivosDevolucion=Producto.mostrarMotivosDeDevolucion();
                                System.out.println("Motivos de devolución: \n" + motivosDevolucion);
                                int motivoDevolucion=sc.nextInt();
                                String motivo=Producto.obtenerMotivoDeDevolucion(motivoDevolucion);
                                producto.setMotivoDevolucion(motivo);


                                if (motivoDevolucion ==1 || motivoDevolucion==2 || motivoDevolucion==3 ){

                                    System.out.println("Por el motivo indicado, se le hará el reembolso del dinero.");
                                    Cliente cliente=tienda.devolverProducto(factura, producto);
                                    double valorADevolver=Fabrica.descontarDineroCuenta(producto); // Se descuenta el dinero de la cuenta de la fábrica
                                    // y se obtiene el valor del producto a devolver.
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
                                            uiMain.uiDevoluciones.devolver();
                                        default:
                                            System.out.println("Opción inválida. Intente nuevamente.");
                                            break;
                                    }
                
                                }
                                else if (motivoDevolucion==Producto.motivosDevolucion.size()){
                                        System.out.println("Especifique su causa de la devolución: ");
                                        sc.nextLine(); // Limpiar el buffer antes de leer el motivo
                                        String causa =sc.nextLine();
                                        producto.setMotivoDevolucion(causa);
                                        Producto.motivosDevolucion.add(Producto.motivosDevolucion.size()-1, causa);
                                    }
                                    System.out.println("Por el motivo indicado, se le hará el cambio del producto.");
                                    System.out.println("Si el producto que seleccione tiene un precio mayor al que desea cambiar, puede agregar otro producto para completar el valor restante. Es posible que tenga que pagar un excedente.\nNO se le devolverá el dinero restante.");
                                    System.out.println("Seleccione el producto por el cual desea cambiar: ");
                                    double precio=producto.getPrecio();
                                    System.out.println("El precio de su producto es de: $"+precio);
                                    ArrayList<Integer> seleccionProductos = new ArrayList<>();
                                    ArrayList<Producto> carrito = new ArrayList<>();
                                    double subtotal = 0;
                                    while (true) {
                                        //Se muestran los productos 
                                        //que el cliente puede cambiar, ubicando primero los que sean de la misma categoria que el que desea cambiar. 
                                        System.out.println("\nProductos disponibles para cambio:");
                                        ArrayList<Producto> productosDisponibles = tienda.mostrarProductosFiltrados(producto); 
                                        for (int i = 0; i < productosDisponibles.size(); i++) {
                                            Producto p = productosDisponibles.get(i);
                                            System.out.println((i + 1) + ". " + p.getNombre() + " - Precio: $" + p.getPrecio());
                                        }
                                        // Pedir al usuario que seleccione un producto
                                        System.out.println("Ingrese el número del producto que desea añadir al carrito (o 0 para finalizar):");
                                        int opcion4 = sc.nextInt();
                            
                                        // Salir si el cliente no quiere añadir más productos
                                        if (opcion4 == 0) {
                                            System.out.println("Ha decidido no añadir más productos.");
                                            break;
                                        }
                            
                                        // Agregar la selección a la lista
                                        seleccionProductos.add(opcion4);
                            
                                        // Llamar al método de la tienda para procesar la selección.
                                        carrito = tienda.agregarProductosParaCambio(precio, seleccionProductos);
                            
                                        // Mostrar los productos seleccionados
                                        System.out.println("\nResumen del cambio:");
                                        subtotal = 0;
                                        for (Producto p : carrito) {
                                            System.out.println("- " + p.getNombre() + ": $" + p.getPrecio());
                                            subtotal += p.getPrecio();
                                        }
                                        System.out.println("Subtotal actual: $" + subtotal);
                            
                                        // Verificar si el subtotal alcanzó el límite permitido
                                        if (subtotal >= precio) {
                                            System.out.println("El subtotal ha alcanzado el valor límite. El proceso ha finalizado.");
                                            break;
                                        }
                            
                                        // Preguntar si desea continuar
                                        System.out.println("¿Desea continuar añadiendo productos? (1: Sí, 0: No)");
                                        int continuar = sc.nextInt();
                                        if (continuar == 0) {
                                            System.out.println("Proceso finalizado. Su carrito de cambio está listo.");
                                            break;
                                        }
                                    }

                                    double excedente = Fabrica.calcularExcedente(carrito, precio); //Calcula el excedente que debe pagar el cliente (si debe hacerlo) por su cambio.
                                    if (excedente > 0) {
                                        System.out.println("El valor total de los productos seleccionados supera el precio del producto a cambiar.");
                                        System.out.println("El excedente a pagar es de: $" + excedente);
                                    } else {
                                        System.out.println("El valor total de los productos seleccionados no supera el precio del producto a cambiar. Le recordamos que no se le devolverá el dinero restante.");
                                    }

                                    Cliente cliente=factura.getCliente();
                                    cliente.cuentaBancaria.transferirDinero(excedente, Fabrica.cuentaBancaria); // Se transfiere el excedente de la cuenta del cliente a la cuenta de la fábrica.
                                    cliente.removerProducto(producto); // Se remueve el producto de la lista de productos del cliente.
                                    for (Producto p : carrito) {
                                        cliente.listaProductos.add(p); // Se añaden los productos seleccionados al cliente.
                                    }
                            
                                    // Mostrar resumen final del cambio
                                    System.out.println("\n----- Resumen final del cambio -----");
                                    for (Producto p : carrito) {
                                        System.out.println("- " + p.getNombre() + ": $" + p.getPrecio());
                                    }
                                    System.out.println("Total del carrito: $" + subtotal+"\nExcedente pagado: "+excedente);
                                    
                                }
                            }
                                else{
                                    System.out.println("Motivo de devolución inválido. Intente nuevamente.");
                                }
                            }
                        } else {
                            System.out.println("Opción inválida. Intente nuevamente.");
                        }
                    }
                } 
                 {
                    System.out.println("Opción inválida. Intente nuevamente.");
                }
            }
        
    