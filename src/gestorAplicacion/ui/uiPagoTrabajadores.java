package ui;
import java.util.Scanner;
import java.util.ArrayList;
import gestion.Conductor;
import gestion.Operario;
import gestion.Persona;
import gestion.Vendedor;
import produccion.Fabrica;

public class uiPagoTrabajadores {
    public static void pagarTrabajadores(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            ArrayList<Persona> listaTrabajadores = null;  //Se crea para usarla cuando se verifique el tipo de empleado que escogio
            System.out.println("Eligió la opción de pagar a sus trabajadores.\nSeleccione el tipo de empleado que desea pagarle. Oprima 0 para salir.");
            System.out.println("1. Operarios \n2. Conductores \n3. Vendedores\n0. Volver al menú");
            int opcion;
            int opcion2;
            int opcion3;
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

            // Validacion de la opcion del usuario, si la entrada es valida 
            // muestra la lista de los posibles trabajadores
            if (opcion > 0 && opcion <= 3){
                switch (opcion) {
                    case 1:
                        listaTrabajadores = Operario.getListaPersonas();
                        break;
                    case 2:
                        listaTrabajadores = Conductor.getListaPersonas();
                        break;
                    case 3:
                        listaTrabajadores = Vendedor.getListaPersonas();
                        break;
                }
                while(true){
                    ArrayList<Persona> trabajadores = Fabrica.busquedaTrabajo(listaTrabajadores);
                    System.out.println(Fabrica.mostrarPersonas(trabajadores));
                    System.out.println("\nElija el trabajador que desea pagarle. Seleccione un numero entre: [1 - " + trabajadores.size()+']');
                    //Para validar que el usuario ingrese un numero valido otra vez
                    try {
                        opcion2 = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número.");
                        sc.nextLine(); // Limpiar el buffer
                        continue; 
                    } 

                    //Confirmación de que el numero este dentro del rango
                    if(opcion2 < 1 && opcion2 > trabajadores.size()){
                        System.out.println("Escoja un numero que este dentro del rango.");
                        continue;
                    }else{
                        //Muestra el Pago que se le mandara al Trabajador sin ver las metas
                        Persona trabajadorSeleccionado = trabajadores.get(opcion2-1);
                        double pagoPotencial = trabajadorSeleccionado.getCuentaBancaria().calcularPago(trabajadorSeleccionado) + trabajadorSeleccionado.getSalarioBase();
                        System.out.println("El trabajdor" + trabajadorSeleccionado.getNombre() + "Se le debe hacer un pago de: "
                                            + pagoPotencial + "por haber trabajado" + trabajadorSeleccionado.getCantidadTrabajo() + "veces.\n");
                        
                        while(true){
                            //Preguntar si quiere revisar metas o seguir con el pago.
                            System.out.println("¿Desea continuar con el pago o desea revisar las metas del trabajador?.\n1. Si\n2> No");
                            //Verificación de que se ingrese un número.
                            try {
                                opcion3 = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                sc.nextLine(); // Limpiar el buffer
                                continue; 
                            } 
                            //Verificación de opción escogida.
                            if(opcion3 != 1 && opcion3 != 2){
                                System.out.println("Seleccione una opción valida.");
                                continue;

                            }else if(opcion3 == 1){
                                //Codiigo donde se verifican las metas

                            }else if(opcion3 == 2){
                                trabajadorSeleccionado.recibirSueldo(pagoPotencial);
                            }
                            //falta poner el comprobante de pago pero se pondra ya cuando tambien pase si el usuario selecciona 1
                            //para mostrar cuanto se le pago y por que cosas
                        }
                    }
                }
            }else{
                System.out.println("Entrada inválida. Por favor, ingrese un número que este en el rango [0-3].");
                continue;
            }
        //Aca se cierra el scanner pero hace falta cambiar esos ciclos while true para que tengan una validación para cerrarse
        }
    }   
}
