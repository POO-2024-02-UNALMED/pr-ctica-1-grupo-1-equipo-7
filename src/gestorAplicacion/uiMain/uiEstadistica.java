package uiMain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
//import java.util.ArrayList;
import java.util.Scanner;

import gestion.Factura;

//Clase para la funcionalidad Estadisticas

public class uiEstadistica {
    
    private static Scanner scanner = new Scanner(System.in);
    private static LocalDate fechaInicio;
    private static LocalDate fechaFin;

    public static void asignarFecha() {
        System.out.println("Bienvenido al menú de Estadísticas. la fecha de inicio general de facturación es: " + Factura.getFechaMin() + " y la fecha de fin general de facturación es: " + Factura.getFechaMax());

        System.out.println("Usar fechas por defecto? (s/n): ");

        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            fechaInicio = Factura.getFechaMin();
            fechaFin = Factura.getFechaMax();
            return;
            
        }else {
            System.out.println("Ingrese la fecha de inicio (dd/mm/yyyy): ");
            String SFechaInicio = scanner.nextLine();
            try {
                fechaInicio = Factura.convertirStrADate(SFechaInicio);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Intente de nuevo.");
                asignarFecha();
            }

            System.out.println("Ingrese la fecha de fin (dd/mm/yyyy): ");
            String SFechaFin = scanner.nextLine();
            try {
                fechaFin = Factura.convertirStrADate(SFechaFin);
            } catch (DateTimeParseException e) {
                System.out.println("Fecha inválida. Intente de nuevo.");
                asignarFecha();
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("=== Opciones ===");
        asignarFecha();
        System.out.println("1. Mostrar ganancias discretas entre fechas");
        System.out.println("2. Mostrar ganancias totales entre fechas");
        System.out.println("3. Mostrar promedio de ganancias por día entre fechas");
        System.out.println("4. Mostrar aumento porcentual de ganancias entre fechas");
        System.out.println("5. Mostrar moda de productos entre fechas");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public static void mostar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Las ganancias discretas son: ");
                    System.out.println(Factura.gananciasDiscretas(fechaInicio, fechaFin));
                    break;
                case 2:
                    System.out.println("La ganancia total es:");
                    System.out.println(Factura.gananciasTotales(fechaInicio, fechaFin));
                    break;
                case 3:
                    System.out.println("El promedio es: ");
                    System.out.println(Factura.promedioGanancias(fechaInicio, fechaFin));
                    break;
                case 4:
                    System.out.println("El aumento porcentual es: ");
                    System.out.println(Factura.aumentoPorcentual(fechaInicio, fechaFin));
                    break;
                case 5:
                    System.out.println("El producto más vendido es: ");
                    System.out.println(Factura.modaProductos(fechaInicio, fechaFin));
                    System.out.println("La tienda que más vendió es: ");
                    System.out.println(Factura.modaTiendas(fechaInicio, fechaFin));
                    System.out.println("El cliente que más compró es: ");
                    System.out.println(Factura.modaClientes(fechaInicio, fechaFin));
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 6);
    }

    

}