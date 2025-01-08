package uiMain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class uiEstadistica {
    public static void main(String[] args) { //??? 
                                            //El main no va aca, wtf

        //Esto será una parte de un nuevo metodo para factura
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato de fecha: día/mes/año

        LocalDate fecha = null;
        boolean fechaValida = false;

        while (!fechaValida) {
            System.out.print("Ingrese una fecha (dd/MM/yyyy): ");
            String entradaFecha = scanner.nextLine();

            try {
                fecha = LocalDate.parse(entradaFecha, formatter);
                fechaValida = true;
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Intente nuevamente.");
            }
        }

        System.out.println("La fecha ingresada es: " + fecha);
        scanner.close();
    }
}
