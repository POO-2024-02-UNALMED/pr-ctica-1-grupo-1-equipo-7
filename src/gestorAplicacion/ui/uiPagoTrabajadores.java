package ui;
import java.util.Scanner;
import java.util.ArrayList;
import gestion.Conductor;
import gestion.Operario;
import gestion.Persona;
import gestion.Vendedor;

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
                ArrayList<Persona> listaTrabajadores = null;
                if(opcion == 1){
                    listaTrabajadores = Operario.getListaPersonas();
                }

                if(opcion == 2){
                    listaTrabajadores = Conductor.getListaPersonas();        //Verificación del tipo de trabajador seleccionado
                }
                if(opcion == 3){
                    listaTrabajadores = Vendedor.getListaPersonas();
                }
                for(Persona e : listaTrabajadores){
                    if(e.getCantidadTrabajo() > 0){
                        e.toString();
                    }
                }
            }   








            else{
                System.out.println("Entrada inválida. Por favor, ingrese un número que este en el rango [0-3].");
            }
        }
        sc.close();
    }
}
