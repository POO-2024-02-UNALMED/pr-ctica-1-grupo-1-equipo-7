package ui;
import java.util.Scanner;
import gestion.Cliente;
public class uiEnviarPedidos {
    public static void enviar() {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Eligió la opción de envio de pedidos. \nSeleccione al cliente que realizó el pedido. Oprima  0 para salir.");
            System.out.println("0. Salir");
            Cliente.mostrarClientes();
            
            int seleccion = -1; 

            while (true) {
                try {
                    seleccion = sc.nextInt();
                    if (seleccion == 0) {
                        System.out.println("Saliendo...");
                        sc.close();
                        return;
                    } else if (seleccion > 0 && seleccion <= Cliente.clientes.size()+1) {
                        break;
                    } else {
                        System.out.println("Número fuera de rango. Por favor, elija un cliente válido.");
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine();
                }
            }

            System.out.println("Ha seleccionado el cliente: "+ Cliente.clientes.get(seleccion - 1).getNombre());







        }
        
    }
    
}
