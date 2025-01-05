package ui;
import java.util.Scanner;
import gestion.Cliente;
import produccion.*;
public class uiEnviarPedidos {
    public static void enviar() {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Eligió la opción de envio de pedidos. \nSeleccione al cliente que realizó el pedido. Oprima  0 para salir.");
            System.out.println("0. Salir");
            Cliente.mostrarClientes();
            
            int seleccion = -1;
            while(true){
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
                
                Cliente clienteSeleccionado;
                
                while (true) {
                    String eleccion = sc.next().toLowerCase();
    
                    if (eleccion.equals("aceptar")) {
                        clienteSeleccionado = Cliente.getListaClientes().get(seleccion - 1);
                        System.out.println("Cliente confirmado: " + clienteSeleccionado.getNombre());
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
                System.out.println("Seleccione la tienda desde la cual se enviará el pedido. Si no desea continuar, presione 0 para salir.");
                System.out.println("Listado de Tiendas:");
                System.out.println("0. Salir");
                Fabrica.mostrarTiendas();

                int opcion = -1;
                Tienda tiendaSeleccionada;
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

            }
        }
    }
}

