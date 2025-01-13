package uiMain;
import java.util.ArrayList;
import java.util.Scanner;
import gestion.Cliente;
import produccion.*;
public class uiEnviarPedidos {
    public static void enviar() {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Eligió la opción de envio de pedidos. \nSeleccione al cliente que realizó el pedido. Oprima  0 para salir.");
            System.out.println("0. Salir");
            System.out.println(Cliente.mostrarClientes());
            int seleccion = -1;
            while(true){
                Cliente clienteSeleccionado;
                int confirmacionCliente = 0;
                while(confirmacionCliente == 0){
                    while (true) {
                        try {
                            seleccion = sc.nextInt();
                            if (seleccion == 0) {
                                System.out.println("Saliendo...");
                                sc.close();
                                return;
                            } 
                            else if (seleccion > 0 && seleccion <= Cliente.listaClientes.size()){
                                break;
                            } 
                            else {
                                System.out.println("Número fuera de rango. Por favor, elija un cliente válido.");
                            }
                        } 
                        catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número.");
                            sc.nextLine();
                        }
                    }
                    System.out.println("Ha seleccionado el cliente: "+ Cliente.getListaClientes().get(seleccion - 1).getNombre());
                    System.out.println("Para confirmar, ingrese 'Aceptar'. Para regresar al menú anterior, ingrese 'Regresar'.");
                    
                    while (true) {
                        String eleccion = sc.next().toLowerCase();
        
                        if (eleccion.equals("aceptar")) {
                            clienteSeleccionado = Cliente.getListaClientes().get(seleccion - 1);
                            System.out.println("Cliente confirmado: " + clienteSeleccionado.getNombre());
                            confirmacionCliente = 1;
                            break;
                        } 
                        else if (eleccion.equals("regresar")) {
                            System.out.println("Regresando al menú anterior...");
                            break;
                        } 
                        else {
                            System.out.println("Por favor, ingrese 'Aceptar' para confirmar o 'Regresar' para volver al menú anterior.");
                        }
                    }
                }
                System.out.println("Seleccione la tienda desde la cual se enviará el pedido. Si no desea continuar, presione 0 para salir.");
                System.out.println("Listado de Tiendas:");
                System.out.println("0. Salir");
                System.out.println(Fabrica.mostrarTiendas());

                int opcion = -1;
                Tienda tiendaSeleccionada;
                int confirmacionTienda = 0;
                while(confirmacionTienda == 0){
                    while (true) {
                        try {
                            opcion = sc.nextInt();
                            if (opcion == 0) {
                                System.out.println("Saliendo...");
                                sc.close();
                                return;
                            } 
                            else if (opcion > 0 && opcion <= Fabrica.getListaTienda().size()){
                                tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                                break;
                            } 
                            else {
                                System.out.println("Número fuera de rango. Por favor, elija una tienda válida.");
                            }
                        } 
                        catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número.");
                            sc.nextLine();
                        }
                    }
                    System.out.println("Tienda Seleccionada: "+ tiendaSeleccionada.getNombre());
                    System.out.println("Para confirmar la tienda seleccionada, escriba 'Aceptar'. Si desea cambiar su selección, escriba 'Regresar'.");
                    
                    while (true) {
                        String eleccion = sc.next().toLowerCase();
        
                        if (eleccion.equals("aceptar")) {
                            tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                            System.out.println("Tienda confirmada: " + tiendaSeleccionada.getNombre());
                            confirmacionTienda = 1;
                            break;
                        } 
                        else if (eleccion.equals("regresar")) {
                            System.out.println("Regresando al menú anterior...");
                            break;
                        } 
                        else {
                            System.out.println("La opción ingresada no es válida. Por favor, ingrese 'Aceptar' para confirmar la tienda seleccionada o 'Regresar' para cambiarla.");
                        }
                    }
                }
                System.out.println("Indique la cantidad de productos que desea enviar (máximo 5).");
                int cantidadProductos = -1;
                int confirmacionCantidadProductos = 0;
                while (confirmacionCantidadProductos == 0) {
                    try {
                        cantidadProductos = sc.nextInt();
                        if (cantidadProductos > 0 && cantidadProductos<=5) {
                            System.out.println("Ha ingresado que desea enviar "+ cantidadProductos +" productos. Para confirmar, ingrese 'Aceptar'. Si desea cambiar la cantidad, ingrese 'Regresar'");
                            while (true) {
                                String eleccion = sc.next().toLowerCase();

                                if (eleccion.equals("aceptar")) {
                                    tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                                    System.out.println("Ha confirmado que desea enviar "+ cantidadProductos+" productos.");
                                    confirmacionTienda = 1;
                                    break;
                                } 
                                else if (eleccion.equals("regresar")) {
                                    System.out.println("\"Por favor, ingrese nuevamente la cantidad de productos que desea enviar");
                                    break;
                                } 
                                else {
                                    System.out.println("La opción ingresada no es válida. Por favor, ingrese 'Aceptar' para confirmar la cantidad de productos a enviar o 'Regresar' si desea modificarla.");
                                }
                            }
                        }
                        else {
                            System.out.println("El valor ingresado está fuera del rango permitido. Recuerde que el máximo de productos a enviar es 5.");
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número.");
                        sc.nextLine();
                    }
                }
                ArrayList<Producto> ListaProductosPedidos = new ArrayList<>();
                for (int i = 0; i < cantidadProductos; i++) {
                    


                }
                }
            }
        }
    }


