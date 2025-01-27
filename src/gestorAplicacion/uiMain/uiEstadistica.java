package uiMain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import gestion.Factura;

//Clase para la funcionalidad Estadisticas

public class uiEstadistica {
    
    private static Scanner scanner = new Scanner(System.in);
    private static LocalDate fechaInicio;
    private static LocalDate fechaFin;
    private static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static int num = 0;

    public static void bienvenida() {
        System.out.println("=== Bienvenido al módulo de estadísticas ===");

    }

    public static void asignarFecha() {
        
        // Convertir la fecha a String con el nuevo formato
        String fechaMinFormateada = Factura.getFechaMin().format(formato);
        String fechaMaxFormateada = Factura.getFechaMax().format(formato);
        
        System.out.println("La fecha mínima es: " + fechaMinFormateada);
        System.out.println("La fecha máxima es: " + fechaMaxFormateada);
        System.out.println("Usar fechas por defecto (las mostradas anteriormente)? (s/n): ");

        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            fechaInicio = Factura.getFechaMin();
            fechaFin = Factura.getFechaMax();
            return;
            
        }else if (respuesta.equalsIgnoreCase("n")) {
            while (true) {
                System.out.println("Ingrese la fecha de inicio (dd/mm/yyyy): ");
                String SFechaInicio = scanner.nextLine();
                try {
                    fechaInicio = Factura.convertirStrADate(SFechaInicio);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Formato o Fecha inválida. Intente de nuevo.");
                }
            }
            
            while (true) {
                System.out.println("Ingrese la fecha de fin (dd/mm/yyyy): ");
                String SFechaFin = scanner.nextLine();
                try {
                    fechaFin = Factura.convertirStrADate(SFechaFin);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Formato o Fecha inválida. Intente de nuevo.");
                }
            }
        } else {
            System.out.println("Respuesta no válida. Intente de nuevo.");
            asignarFecha();
        }
    }

    private static void mostrarMenu() {
        System.out.println("=== Opciones ===");
        System.out.println("1. Mostrar ganancias discretas entre fechas");
        System.out.println("2. Mostrar ganancias totales entre fechas");
        System.out.println("3. Mostrar promedio de ganancias por día entre fechas");
        System.out.println("4. Mostrar aumento porcentual de ganancias entre fechas");
        System.out.println("5. Mostrar moda de productos entre fechas");
        if (num > 0) {
            System.out.println("6. Cambiar fechas");
        }
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        num++;
    }

    public static void mostrar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Las ganancias discretas son: ");
                    for (Object g : Factura.gananciasDiscretas(fechaInicio, fechaFin)) {
                        ArrayList<Object> ganancias = (ArrayList<Object>) g;
                        LocalDate f = (LocalDate) ganancias.get(0);
                        String fecha = f.format(formato);
                        System.out.println("Fecha: " + fecha + " Ganancia: " + ganancias.get(1));
                    }
                    
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
                    asignarFecha();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            while (opcion != 0) {
                System.out.println("Desea realizar otra operación? (s/n): ");
                String respuesta = scanner.nextLine().toLowerCase();
                if (respuesta.equals("s")) {
                    break;
                } else if (respuesta.equals("n")) {
                    System.out.println("Saliendo...");
                    opcion = 0;
                    break;
                } else {
                    System.out.println("Respuesta no válida. Intente de nuevo.");
                }
                
            }

        } while (opcion != 0);
    }

    

}