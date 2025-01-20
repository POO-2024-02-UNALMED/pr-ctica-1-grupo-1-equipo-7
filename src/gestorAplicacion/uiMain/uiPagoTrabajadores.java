package uiMain;
import java.util.Scanner;
import java.util.ArrayList;
import gestion.Conductor;
import gestion.Operario;
import gestion.Persona;
import gestion.Vendedor;
import produccion.Fabrica;
import gestion.Meta;

public class uiPagoTrabajadores {
    public static void pagarTrabajadores(){
        System.out.println("Eligió la opción de pagar a sus trabajadores.\n");
        Scanner sc = new Scanner(System.in);
        // Etiqueta del primer loop
        LOOP_PRINCIPAL:
        while (true) {
            ArrayList<Persona> listaTrabajadores = null;  // Se crea para usarla cuando se verifique el tipo de empleado que escogió.
            System.out.println("Seleccione el tipo de empleado que desea pagarle.");
            System.out.println("1. Operarios \n2. Conductores \n3. Vendedores\n0. Volver al menú.");
            int opcion;
            int opcion2;
            int opcion3;
            int opcion4;
            int opcion5;
            int opcion6;
            int pagoPorMetas = 0;
            try {
                opcion = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                sc.nextLine(); // Limpiar el buffer
                continue; 
            }
            
            if (opcion == 0) {
                System.out.println("Volviendo al menú principal.");
                break;
            }

            else if(opcion != 1 && opcion != 2 && opcion != 3){
                System.out.println("\nEntrada inválida. Por favor, ingrese un número que esté en el rango [0-3].");
                continue;
            }

            // Validación de la opción del usuario: si la entrada es válida,
            // muestra la lista de los posibles trabajadores.
            else if (opcion > 0 && opcion <= 3){
                switch (opcion) {
                    case 1:
                        listaTrabajadores = Operario.getListaOperario();
                        break;
                    case 2:
                        listaTrabajadores = Conductor.getListaConductores();
                        break;
                    case 3:
                        listaTrabajadores = Vendedor.getListaVendedores();
                        break;
                }

                // Etiqueta del segundo loop
                LOOP_SECUNDARIO:
                while(true){
                    ArrayList<Persona> trabajadores = Fabrica.busquedaTrabajo(listaTrabajadores);
                    if(trabajadores.isEmpty() == true){
                        System.out.println("No hay trabajadores de este tipo para pagarles.");
                        break LOOP_SECUNDARIO;
                    }
                    System.out.println(Fabrica.mostrarPersonas(trabajadores));
                    System.out.println("Elija el trabajador que desea pagarle. Seleccione un número entre: [1 - " + trabajadores.size() + "] \n0. Volver al menú.");
                    // Para validar que el usuario ingrese un número válido otra vez
                    try {
                        opcion2 = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                        sc.nextLine(); // Limpiar el buffer
                        continue; 
                    } 

                    // Validación de que el usuario quiere volver al menú principal.
                    if(opcion2 == 0){
                        System.out.println("Volviendo al menú principal.");
                        break LOOP_PRINCIPAL;
                    }

                    // Confirmación de que el número está dentro del rango.
                    if(opcion2 < 1 || opcion2 > trabajadores.size()){
                        System.out.println("Escoja un número que esté dentro del rango.");
                        continue;

                    } else {
                        // Muestra el pago que se le mandará al trabajador sin ver las metas.
                        Persona trabajadorSeleccionado = trabajadores.get(opcion2 - 1);
                        double pagoPotencial = trabajadorSeleccionado.getCuentaBancaria().calcularPago(trabajadorSeleccionado) + trabajadorSeleccionado.getSalarioBase();
                        System.out.println("\nTrabajador(a) seleccionado(a): " + trabajadorSeleccionado.getNombre() + ". Se le debe hacer un pago de: "
                                            + pagoPotencial + " por haber trabajado " + trabajadorSeleccionado.getCantidadTrabajo() + " veces.\n");

                        // Verificar si quiere revisar metas o continuar
                        LOOP_TERCIARIO:
                        while(true){
                            // Preguntar si quiere revisar metas o seguir con el pago.
                            System.out.println("¿Quiere revisar las metas del trabajador?\n1. Sí\n2. No\n3. Cambiar de Trabajador\n0. Volver al menú principal.");

                            // Verificación de que se ingrese un número.
                            try {
                                opcion3 = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("\nEntrada inválida. Por favor, ingrese un número.\n");
                                sc.nextLine(); // Limpiar el buffer
                                continue; 
                            } 

                            // Validación de que el usuario quiere volver al menú principal.
                            if(opcion3 == 0){
                                System.out.println("Volviendo al menú principal.");
                                break LOOP_PRINCIPAL;
                            }

                            // Validación de que el usuario quiere cambiar de trabajador.
                            if(opcion3 == 3){
                                System.out.println("Volviendo a mostrar la lista de trabajadores:");
                                break;
                            }

                            // Verificación de opción escogida.
                            if(opcion3 != 1 && opcion3 != 2 && opcion3 != 3 && opcion3 != 0){
                                System.out.println("Seleccione una opción válida.\n");
                                continue;

                            }else if(opcion3 == 1){

                                // LOOP para verificar metas
                                while(true){
                                    ArrayList<Meta> metasTrabajador = trabajadorSeleccionado.getMeta();
                                    for(Meta i : metasTrabajador){
                                        if(i.getVerificador() == true){
                                            metasTrabajador.remove(i);
                                        }
                                    }
                                    if(metasTrabajador.isEmpty() == true){
                                        System.out.println("El trabajador no tiene metas en este momento.\nProcediendo con el pago.");
                                        break LOOP_TERCIARIO;
                                    }
                                    System.out.println(trabajadorSeleccionado.mostrarMetas());
                                    System.out.println("\nElija la meta que desea revisar. Seleccione un número entre: [1 - " + metasTrabajador.size() + "]\n"+ (metasTrabajador.size()+1) +". Proceder con el pago. \n0. Volver al menú.");

                                    // Para validar que el usuario ingrese un número válido otra vez
                                    try {
                                        opcion4 = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                                        sc.nextLine(); // Limpiar el buffer
                                        continue; 
                                    } 

                                    // Validación de que el usuario quiere volver al menú principal.
                                    if(opcion4 == 0){
                                        System.out.println("Volviendo al menú principal.");
                                        break LOOP_PRINCIPAL;
                                    }

                                    // Confirmación de que el número está dentro del rango
                                    if(opcion4 < 1 || opcion4 > metasTrabajador.size()){
                                        System.out.println("Escoja un número que esté dentro del rango.\n");
                                        continue;
                                    }
                                    else if(opcion4 == (metasTrabajador.size()+1)){
                                        break LOOP_TERCIARIO;
                                    }
                                    else{
                                        Meta metaSeleccionada = metasTrabajador.get(opcion4-1);
                                        String mensajeMeta = "\n" + metaSeleccionada.porcentajeCumplidos(trabajadorSeleccionado.getIndiceMeta()) + "\n";
                                        if (metaSeleccionada.cumpleMeta(trabajadorSeleccionado.getIndiceMeta()) == true){
                                            System.out.println("\nLa meta ha sido cumplida exitosamente.\nSumaremos el pago indicado por haberlo conseguido." + mensajeMeta);
                                            pagoPorMetas += metaSeleccionada.getPago();
                                            metaSeleccionada.setVerificador(true);
                                        }else{
                                            System.out.println(mensajeMeta);
                                        }
                                    }

                                    // LOOP para verificar si quiere seguir viendo metas
                                    LOOP_QUINTO:
                                    while (true) {    
                                        System.out.println("¿Qué desea hacer? \n1. Revisar otra meta \n2. Proceder con el pago.");
                                        try {
                                            opcion5 = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                                            sc.nextLine(); // Limpiar el buffer
                                            continue; 
                                        }
                                        if(opcion5 != 1 && opcion5 != 2){
                                            System.out.println("Escoja alguna de las opciones.\n");
                                            continue;

                                        }else if(opcion5 == 1){
                                            System.out.println("\nVolviendo a mostrar metas.");
                                            break LOOP_QUINTO;

                                        }else{
                                            break LOOP_TERCIARIO;
                                        }

                                    }
                                }
                            }else if(opcion3 == 2){
                                break LOOP_TERCIARIO;
                            }
                        }
                        if (opcion3 != 3){
                            double pagoTotal = pagoPotencial + pagoPorMetas;
                            Fabrica.cuentaBancaria.descontarDinero(pagoTotal);
                            trabajadorSeleccionado.recibirSueldo(pagoTotal);
                            System.out.println("\n------------------------------");
                            System.out.println("COMPROBANTE \nTrabajador(a): " + trabajadorSeleccionado.getNombre() 
                            + " se le envió un total de: " + pagoTotal 
                            + "\n" + pagoPotencial + " Por las veces trabajadas\n" +
                            pagoPorMetas + " Por las metas cumplidas");
                            System.out.println("------------------------------");
                            break LOOP_SECUNDARIO;
                        }
                    }
                }
            }
            ULTIMO_LOOP:
            while(true){
                System.out.println("\n¿Qué desea hacer? \n1. Pagar a otro trabajador. \n0. Volver al menú principal.");
                try {
                    opcion6 = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                    continue; 
                }
                if (opcion6 != 1 && opcion6 != 0){
                    System.out.println("Seleccione una opción válida.");
                    continue;
                }
                else if (opcion6 == 1){
                    System.out.println("\n");
                    break ULTIMO_LOOP;
                } 
                else{
                    System.out.println("Volviendo al menú principal.");
                    break LOOP_PRINCIPAL;
                }
            }
        }sc.close();     
    }  
}
