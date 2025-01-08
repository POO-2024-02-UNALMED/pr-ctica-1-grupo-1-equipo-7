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

        //Etiqueta primer loop
        LOOP_PRINCIPAL:
        while (true) {
            Scanner sc = new Scanner(System.in);
            ArrayList<Persona> listaTrabajadores = null;  //Se crea para usarla cuando se verifique el tipo de empleado que escogio
            System.out.println("Eligió la opción de pagar a sus trabajadores.\nSeleccione el tipo de empleado que desea pagarle. Oprima 0 para salir.");
            System.out.println("1. Operarios \n2. Conductores \n3. Vendedores\n0. Volver al menú");
            int opcion;
            int opcion2;
            int opcion3;
            int opcion4;
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

            else if(opcion != 1 && opcion != 2 && opcion != 3){
                System.out.println("Entrada inválida. Por favor, ingrese un número que este en el rango [0-3].");
                continue;
            }

            // Validacion de la opcion del usuario, si la entrada es valida 
            // muestra la lista de los posibles trabajadores
            else if (opcion > 0 && opcion <= 3){
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

                //Etiqueta segundo loop
                LOOP_SECUNDARIO:
                while(true){
                    ArrayList<Persona> trabajadores = Fabrica.busquedaTrabajo(listaTrabajadores);
                    System.out.println(Fabrica.mostrarPersonas(trabajadores));
                    System.out.println("\nElija el trabajador que desea pagarle. Seleccione un numero entre: [1 - " + trabajadores.size()+"] \n 0. Volver al menu.");
                    //Para validar que el usuario ingrese un numero valido otra vez
                    try {
                        opcion2 = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número.");
                        sc.nextLine(); // Limpiar el buffer
                        continue; 
                    } 

                    //Validacion de que el usuario quiere volver al menu principal.
                    if(opcion2 == 0){
                        System.out.println("Volviendo al menu principal.");
                        break LOOP_PRINCIPAL;
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
                        
                        //Etiqueta tercer loop
                        LOOP_TERCIARIO:
                        while(true){
                            //Preguntar si quiere revisar metas o seguir con el pago.
                            System.out.println("¿Desea continuar con el pago o desea revisar las metas del trabajador?.\n1. Si\n2. No\n3. Cambiar de Trabajador\n0. Volver al menu principal.");

                            //Verificación de que se ingrese un número.
                            try {
                                opcion3 = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                sc.nextLine(); // Limpiar el buffer
                                continue; 
                            } 

                            //Validacion de que el usuario quiere volver al menu principal.
                            if(opcion3 == 0){
                                System.out.println("Volviendo al menu principal.");
                                break LOOP_PRINCIPAL;
                            }

                            //Validacion de que el usuario quiere cambiar de trabajador.
                            if(opcion3 == 3){
                                System.out.println("Volviendo a mostrar la lista de trabajadores:\n");
                                break;
                            }

                            //Verificación de opción escogida.
                            if(opcion3 != 1 && opcion3 != 2 && opcion3 != 3 && opcion3 != 0){
                                System.out.println("Seleccione una opción valida.");
                                continue;

                            }else if(opcion3 == 1){
                                //Codiigo donde se verifican las metas

                            }else if(opcion3 == 2){
                                trabajadorSeleccionado.recibirSueldo(pagoPotencial);
                                trabajadorSeleccionado.setCantidadTrabajo(0);
                                System.out.println('-'*30);
                                System.out.println("COMPROBANTE \nAl trabajador: " + trabajadorSeleccionado.getNombre() + "se le envio un total de:" + pagoPotencial);
                                System.out.println('-'*30);
                                break LOOP_SECUNDARIO;
                            }
                        }
                    }
                }
            }
            while(true){
                System.out.println("¿Que desea hacer? \n1. Pagar a otro trabajador. \n0. Volver al menu principal.");
                try {
                    opcion4 = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                    continue; 
                }
                if (opcion4 != 1 || opcion4 != 0){
                    System.out.println("Seleccione una opción valida.");
                    continue;
                }
                else if (opcion4 == 1){
                    break;
                } 
                else{
                    break LOOP_PRINCIPAL;
                }
            }
            sc.close();
        }     
    }   
}
