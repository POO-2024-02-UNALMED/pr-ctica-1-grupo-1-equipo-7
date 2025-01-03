package ui;
import java.util.Scanner;

import gestion.Factura;
import gestion.Operario;

public class uiPagoTrabajadores {
    public static void pagarTrabajadores(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Eligió la opción de pagar a sus trabajadores.\nSeleccione el tipo de empleado que desea pagarle. Oprima 0 para salir.");
            System.out.println("1. Operarios \n2. Conductores \n3. Vendedores\n0. Volver al menú");
            int opcion;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                sc.nextLine(); // Limpiar el buffer
                continue;
            }
            
            if (opcion == 0) {
                System.out.println("Saliendo al menú principal.");
                break;
            }
            if (opcion > 0 && opcion <= 3){
                
            }
            else{
                System.out.println("Entrada inválida. Por favor, ingrese un número que este en el rango [0-3].");
            }
        }
    }
}
