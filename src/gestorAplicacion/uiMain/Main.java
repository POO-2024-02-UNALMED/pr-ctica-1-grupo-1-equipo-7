package uiMain;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
    //cada quien ingrese su funcionalidad muchachos...
    public static void enviarPedidos() {
        // Implementar la funcionalidad de enviar pedidos
    }

    public static void devoluciones() {
        // Implementar la funcionalidad de devoluciones
    }

    public static void pagoTrabajadores() {
        // Implementar la funcionalidad de pago de trabajadores
    }

    public static void estadisticas() {
        // Implementar la funcionalidad de estadísticas
    }
    public static void abastecerTiendas() {
        // Implementar la funcionalidad de abastecer tiendas
}

        //Método main, desde el que se va a ejecutar el programa principal, de aca se muestra el menu 
        //con las 5 funcionalidades, se hace el switch y se llama a los metodos de cada funcionalidad 
        //depeniendo de la que se elija.  

}

