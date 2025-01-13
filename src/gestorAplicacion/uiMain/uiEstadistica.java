package uiMain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import gestion.Factura;

//Este es un modelo para el uiEstadistica, no es el definitivo

public class uiEstadistica {
    
    private static Scanner scanner = new Scanner(System.in);



    public static void main(String[] args) {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    mostrarGananciasDiscretas();
                    break;
                case 2:
                    mostrarGananciasTotales();
                    break;
                case 3:
                    mostrarPromedioPorDia();
                    break;
                case 4:
                    mostrarAumentoPorcentual();
                    break;
                case 5:
                    mostrarModaProductos();
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("=== Menú de Estadísticas ===");
        System.out.println("1. Mostrar ganancias discretas entre fechas");
        System.out.println("2. Mostrar ganancias totales entre fechas");
        System.out.println("3. Mostrar promedio de ganancias por día entre fechas");
        System.out.println("4. Mostrar aumento porcentual de ganancias entre fechas");
        System.out.println("5. Mostrar moda de productos entre fechas");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

}