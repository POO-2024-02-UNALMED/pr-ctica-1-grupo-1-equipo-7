package uiMain;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import gestion.Factura;

public class uiEstadistica {
    public static void mostrar() { 
                                            

        //Esto será una parte de un nuevo metodo para factura
        Scanner scanner = new Scanner(System.in);

        LocalDate fecha1 = null;
        boolean fechaValida1 = false;

        while (!fechaValida1) {
            System.out.print("Ingrese una fecha (dd/MM/yyyy): ");
            String entradaFecha1 = scanner.nextLine();
            fecha1 = Factura.convertirStrADate(entradaFecha1);

            try {
                
                fechaValida1 = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Intente nuevamente.");
            }
        }

        LocalDate fecha2 = null;
        boolean fechaValida2 = false;

        while (!fechaValida2) {
            System.out.print("Ingrese una fecha (dd/MM/yyyy): ");
            String entradaFecha2 = scanner.nextLine();
            fecha2 = Factura.convertirStrADate(entradaFecha2);

            try {
                
                fechaValida2 = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Intente nuevamente.");
            }
        }

         ArrayList<Factura> listaFacturas = Factura.getFacturasEntreFechas(fecha1, fecha2);
    
    scanner.close();
    }
}
