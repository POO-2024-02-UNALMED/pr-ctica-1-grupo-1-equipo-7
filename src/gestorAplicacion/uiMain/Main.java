package uiMain;
import gestion.*;
import produccion.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import baseDatos.Load;

public class Main {
    public static void main(String[] args) {

        Load.cargar();

        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("¡Bienvenido al sistema de gestión!");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Enviar Pedidos");
            System.out.println("2. Devoluciones");
            System.out.println("3. Abastecer Tiendas");
            System.out.println("4. Pago Trabajadores");
            System.out.println("5. Estadísticas");
            System.out.println("0. Salir");
            System.out.print("» ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    // Llamar al método para enviar pedidos
                    enviarPedidos();
                    break;
                case 2:
                    // Llamar al método para devoluciones
                    devoluciones();
                    break;
                case 3:
                    // Llamar al método para abastecer tiendas
                    abastecerTiendas();
                    break;
                case 4:
                    // Llamar al método para pago de trabajadores
                    pagoTrabajadores();
                    break;
                case 5:
                    // Llamar al método para estadísticas
                    estadisticas();
                    break;
                case 0:
                    Load.guardar();
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número entre 0 y 5.");
                    break;
            }
        }
        sc.close();
    }

    public static void enviarPedidos() {
        uiMain.uiEnviarPedidos.enviar();
    }

    public static void devoluciones() {
        // Implementar la funcionalidad de devoluciones
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
                            if (producto.getEstado().equals(estadosProducto.DEVUELTO)) {
                                System.out.println("El producto ya ha sido devuelto, elija otro.");
                            } else{
                                System.out.println("Eligió el producto: " + producto.getNombre());
                                System.out.println("Indique el motivo de la devolución: ");
                                String motivosDevolucion=Producto.mostrarMotivosDeDevolucion();
                                System.out.println("Motivos de devolución: \n" + motivosDevolucion); // Se imprimen los motivos de devolucion validos más la opcion de agregar un motivo propio
                                int motivoDevolucion=sc.nextInt();
                                String motivo=Producto.obtenerMotivoDeDevolucion(motivoDevolucion);
                                producto.setMotivoDevolucion(motivo); //Se agrega el motivo de la devolucion del producto. 


                                if (motivoDevolucion ==1 || motivoDevolucion==2 || motivoDevolucion==3 ){

                                    System.out.println("Por el motivo indicado, se le hará el reembolso del dinero.");
                                    Cliente cliente=tienda.devolverProducto(factura, producto);
                                    double valorADevolver=Fabrica.descontarDineroCuenta(producto); // Se descuenta el dinero de la cuenta de la fábrica
                                    // y se obtiene el valor del producto a devolver.
                                    Fabrica.cuentaBancaria.devolverDinero(valorADevolver, cliente); 
                                    cliente.removerProducto(producto);// Se remueve el producto de la lista de productos del cliente.
                                    System.out.println("Se le devolverá el valor de su producto, que es de $ "+ producto.getPrecio());
                                    System.out.println("----Devolviendo el dinero---"); 
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("El producto ha sido devuelto exitosamente y se ha reembolsado su dinero.");
                                    System.out.println("¿Qué desea hacer? \n1. Devolver otro producto \n0. Regresar al menú de facturas");
                                    int opcion3;
                                    switch (opcion3 = sc.nextInt()) {
                                        case 0:
                                            System.out.println("Saliendo del menú de devoluciones.");
                                            break;
                                        case 1:
                                            devoluciones();
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
                                    System.out.println("Si el producto que seleccione tiene un precio menor al que desea cambiar, puede agregar otro producto para completar el valor restante. Es posible que tenga que pagar un excedente.\nNO se le devolverá el dinero restante.");
                                    System.out.println("Seleccione el producto por el cual desea cambiar: ");
                                    double precio=producto.getPrecio();
                                    System.out.println("El precio de su producto es de: $"+precio);
                                    ArrayList<Integer> seleccionProductos = new ArrayList<>();
                                    ArrayList<Producto> carrito = new ArrayList<>();
                                    double subtotal = 0;
                                    System.out.println("\nProductos disponibles para cambio:");
                                    ArrayList<Producto> productosDisponibles = tienda.mostrarProductos(producto);
                                  
                                    
                                    while (true) {
                                        //Se muestran los productos 
                                        //que el cliente puede cambiar. 
                                        if (productosDisponibles.isEmpty()){
                                            System.out.println("Se han agotado los productos en la tienda disponibles para cambiar. Se procederá al cambio del producto con su carrito actual");
                                            break; //Se considera el caso (poco probable pero no imposible) de que la tienda se quede sin productos para cambiar. 
                                        }
                                        ArrayList<Producto> productosUnicos=new ArrayList<>(); //Productos disponibles para cambiar, pero aparecen solo una vez
                                        ArrayList<Integer> frecuencias= new ArrayList<>(); //Lista que contiene la frecuencia de cada producto en la lista de productosDisponibles
                                        for (Producto p: productosDisponibles){
                                            boolean encontrado=false; 

                                            for (int i = 0; i < productosUnicos.size(); i++) {
                                                if (productosUnicos.get(i).getNombre().equals(p.getNombre())) {
                                                    // Si el producto ya está en la lista, aumentar su frecuencia
                                                    frecuencias.set(i, frecuencias.get(i) + 1);
                                                    encontrado = true;
                                                    break;
                                                }
                                            }
                                        
                                            if (!encontrado) {
                                                // Si no se encontró, agregar el producto a productosUnicos
                                                productosUnicos.add(p);
                                                frecuencias.add(1);
                                            }
                                        }
                                        
                                        for (int i = 0; i < productosUnicos.size(); i++) {
                                            Producto p = productosUnicos.get(i);
                                            int cantidadProducto=frecuencias.get(i);
                                            System.out.println((i + 1) + ". " + p.getNombre() + " - Precio: $" + p.getPrecio()+ " - Productos disponibles: "+ cantidadProducto);
                                            //Se imprime cada producto de la tienda que se puede cambiar, su precio y la cantidad que hay. 
                                        }
                                        // Pedir al usuario que seleccione un producto
                                        System.out.println("Ingrese el número del producto que desea añadir al carrito (o 0 para finalizar):");
                                        int opcion4 = sc.nextInt();
                            
                                        // Salir si el cliente no quiere añadir más productos
                                        if (opcion4 == 0) {
                                            System.out.println("Ha decidido no añadir más productos.");
                                            return;
                                        }
                            
                                        // Agregar la selección a la lista
                                        seleccionProductos.add(opcion4);
                                        Producto productoSeleccionado=productosUnicos.get(opcion4-1); //Se usa para eliminar el producto de la lista de productos disponibles al final de la iteracion. 
                            
                                        // Llamar al método de la tienda para procesar la selección.
                                        carrito = tienda.agregarProductosParaCambio(precio, seleccionProductos,productosUnicos);
                            
                                        // Mostrar los productos seleccionados
                                        System.out.println("\nResumen del cambio:");
                                        subtotal = 0;
                                        for (Producto p : carrito) {
                                            System.out.println("- " + p.getNombre() + ": $" + p.getPrecio());
                                            subtotal += p.getPrecio();
                                        }
                                        System.out.println("Subtotal actual: $" + subtotal);
                            
                                        // Verificar si el subtotal alcanzó el límite permitido
                                        if (subtotal > precio) {
                                            break;
                                        }
                            
                                        // Preguntar si desea continuar
                                        System.out.println("¿Desea continuar añadiendo productos? (1: Sí, 0: No)");
                                        int continuar = sc.nextInt();
                                        if (continuar==1){
                                            Iterator<Producto> iterator = productosDisponibles.iterator();
                                            while (iterator.hasNext()){
                                                Producto p=iterator.next();
                                                if (p.getId()==productoSeleccionado.getId()) {
                                                    iterator.remove();
                                            }
                                          }
                                          System.out.println("\nProductos disponibles para cambio:");

                                        }  
                                        else if (continuar == 0) {
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
                                    producto.setEstado(estadosProducto.DEVUELTO);
                                    for (Producto p : carrito) {
                                        cliente.listaProductos.add(p); // Se añaden los productos seleccionados por el cambio al cliente.
                                        tienda.getListaProducto().remove(p); // Se eliminan los productos que el cliente seleccionó de la lista de la tienda 
                                    }
                                    System.out.println("Generando resumen final del cambio...");
                            
                                    // Mostrar resumen final del cambio
                                    try {
                                        Thread.sleep(2500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("\n----- Resumen final del cambio -----");
                                    System.out.println("Usted ha cambiado un "+ producto.getNombre()+" por:");

                                    for (Producto p : carrito) {
                                        System.out.println(" - " + p.getNombre() + ": $" + p.getPrecio());
                                    }
                                    System.out.println("Total del carrito: $" + subtotal+"\nExcedente pagado: "+excedente);
                                    System.out.println("---------------------------------------");
                                    
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
                }  {
                    System.out.println("Opción inválida. Intente nuevamente.");
                }
        
    

    public static void abastecerTiendas() {
        // Implementar la funcionalidad de abastecer tiendas
        uiMain.uiAbastacerTiedas.abastecer();
    }

    public static void pagoTrabajadores() {
        // Implementar la funcionalidad de pago de trabajadores
        uiMain.uiPagoTrabajadores.pagarTrabajadores();
    }

    public static void estadisticas() {
        // Implementar la funcionalidad de estadísticas
        uiMain.uiEstadistica.bienvenida();
        uiMain.uiEstadistica.asignarFecha();
        uiMain.uiEstadistica.mostrar();
    }
}

