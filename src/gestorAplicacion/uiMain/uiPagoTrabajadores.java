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
            ArrayList<Persona> listaTrabajadores = new ArrayList<>();  // Se crea para almacenar la lista de trabajadores.


            //BLOQUE DONDE SE SELECCIONA EL TIPO DE TRABAJADOR
            System.out.println("Seleccione el tipo de empleado que desea pagarle.");
            System.out.println("1. Operarios \n2. Conductores \n3. Vendedores\n0. Volver al menú.");
            int opcionPT;
            int opcionPT2;
            int opcionPT3;
            int opcionPT4;
            int opcionPT5;
            int opcion6PT;
            int pagoPorMetas = 0;
            try {
                opcionPT = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                sc.nextLine(); // Limpiar el buffer
                continue; 
            }
            
            if (opcionPT == 0) {
                System.out.println("Volviendo al menú principal.\n");
                break;
            }

            else if(opcionPT != 1 && opcionPT != 2 && opcionPT != 3){
                System.out.println("\nEntrada inválida. Por favor, ingrese un número que esté en el rango [0-3].");
                continue;
            }
            else if (opcionPT > 0 && opcionPT <= 3){
                switch (opcionPT) {
                    case 1:
                        listaTrabajadores = Operario.getListaOperario();
                        break;
                    case 2:
                        for(Conductor i : Conductor.getListaConductores()){
                            listaTrabajadores.add(i);
                        }
                        break;
                    case 3:
                        listaTrabajadores = Vendedor.getListaVendedores();
                        break;
                }



                //BLOQUE DONDE SE MUESTRAN LOS POSIBLES TRABAJADORES Y SE SELECCIONA EL TRABAJDOR DESEADO
                LOOP_SECUNDARIO:
                while(true){
                    ArrayList<Persona> trabajadores = Fabrica.busquedaTrabajo(listaTrabajadores);
                    if(trabajadores.isEmpty() == true){
                        System.out.println("No hay trabajadores de este tipo para pagarles.");
                        break LOOP_SECUNDARIO;
                    }
                    System.out.println(Fabrica.mostrarPersonas(trabajadores));
                    System.out.println("Elija el trabajador que desea pagarle. Seleccione un número entre: [1 - " + trabajadores.size() + "] \n0. Volver al menú.");

                    // Para validar que el usuario ingrese un número válido
                    try {
                        opcionPT2 = sc.nextInt();
                    } catch (Exception e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                        sc.nextLine(); // Limpiar el buffer
                        continue; 
                    } 

                    // Validación de que el usuario quiere volver al menú principal.
                    if(opcionPT2 == 0){
                        System.out.println("Volviendo al menú principal.\n");
                        break LOOP_PRINCIPAL;
                    }

                    // Confirmación de que el número está dentro del rango.
                    if(opcionPT2 < 1 || opcionPT2 > trabajadores.size()){
                        System.out.println("Escoja un número que esté dentro del rango.");
                        continue;

                    } else {
                        // Muestra el pago que se le mandará al trabajador sin ver las metas.
                        Persona trabajadorSeleccionado = trabajadores.get(opcionPT2 - 1);
                        double pagoPotencial = trabajadorSeleccionado.getCuentaBancaria().calcularPago(trabajadorSeleccionado) + trabajadorSeleccionado.getSalarioBase();
                        System.out.println("\nTrabajador(a) seleccionado(a): " + trabajadorSeleccionado.getNombre() + ". Se le debe hacer un pago de: "
                                            + pagoPotencial + " por haber trabajado " + trabajadorSeleccionado.getCantidadTrabajo() + " veces.\n");




                        // SE PREGUNTA SI EL USUARIO QUIERE REVISAR LAS METAS DEL TRABAJDOR SELECCIONADO
                        LOOP_TERCIARIO:
                        while(true){
                            // Preguntar si quiere revisar metas o seguir con el pago.
                            System.out.println("¿Quiere revisar las metas del trabajador?\n1. Sí\n2. No\n3. Cambiar de Trabajador\n0. Volver al menú principal.");

                            // Verificación de que se ingrese un número.
                            try {
                                opcionPT3 = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("\nEntrada inválida. Por favor, ingrese un número.\n");
                                sc.nextLine(); // Limpiar el buffer
                                continue; 
                            } 

                            // Validación de que el usuario quiere volver al menú principal.
                            if(opcionPT3 == 0){
                                System.out.println("Volviendo al menú principal.\n");
                                break LOOP_PRINCIPAL;
                            }

                            // Verifica si el usuario quiere cambiar de trabajador.
                            if(opcionPT3 == 3){
                                System.out.println("Volviendo a mostrar la lista de trabajadores:");
                                break;
                            }

                            // Verificación de opción escogida.
                            if(opcionPT3 != 1 && opcionPT3 != 2 && opcionPT3 != 3 && opcionPT3 != 0){
                                System.out.println("Seleccione una opción válida.\n");
                                continue;

                            }else if(opcionPT3 == 2){
                                    break LOOP_TERCIARIO;

                            }else if(opcionPT3 == 1){


                                // LOOP PARA ANALIZAR LAS METAS DEL TRABAJADOR
                                while(true){
                                    ArrayList<Meta> metasTrabajador = trabajadorSeleccionado.getMeta();

                                    //verifica que las metas no se hayan pagado
                                    for(Meta i : metasTrabajador){
                                        if(i.getVerificador() == true){
                                            metasTrabajador.remove(i);
                                        }
                                    }

                                    //validacion de que hayan metas para mostrar
                                    if(metasTrabajador.isEmpty() == true){
                                        System.out.println("El trabajador no tiene metas en este momento.\nProcediendo con el pago.");
                                        break LOOP_TERCIARIO;
                                    }
                                    System.out.println(trabajadorSeleccionado.mostrarMetas());
                                    System.out.println("\nElija la meta que desea revisar. Seleccione un número entre: [1 - " + metasTrabajador.size() + "]\n"+ (metasTrabajador.size()+1) +". Proceder con el pago. \n0. Volver al menú.");

                                    // Para validar que el usuario ingrese un número válido otra vez
                                    try {
                                        opcionPT4 = sc.nextInt();
                                    } catch (Exception e) {
                                        System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                                        sc.nextLine(); // Limpiar el buffer
                                        continue; 
                                    } 

                                    // Validación de que el usuario quiere volver al menú principal.
                                    if(opcionPT4 == 0){
                                        System.out.println("Volviendo al menú principal.\n");
                                        break LOOP_PRINCIPAL;
                                    }

                                    // Confirmación de que el número está dentro del rango
                                    if(opcionPT4 < 1 || opcionPT4 > metasTrabajador.size()){
                                        System.out.println("Escoja un número que esté dentro del rango.\n");
                                        continue;
                                    }
                                    else if(opcionPT4 == (metasTrabajador.size()+1)){
                                        break LOOP_TERCIARIO;
                                    }
                                    else{
                                        //se muestra la informacion de la meta y si el trabajador la cumple
                                        Meta metaSeleccionada = metasTrabajador.get(opcionPT4-1);
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
                                            opcionPT5 = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                                            sc.nextLine(); // Limpiar el buffer
                                            continue; 
                                        }
                                        //validación de que la opcion sea valida
                                        if(opcionPT5 != 1 && opcionPT5 != 2){
                                            System.out.println("Escoja alguna de las opciones.\n");
                                            continue;

                                        //vuelve a mostrar las metas
                                        }else if(opcionPT5 == 1){
                                            System.out.println("\nVolviendo a mostrar metas.");
                                            break LOOP_QUINTO;

                                        //procede con el pago
                                        }else{
                                            break LOOP_TERCIARIO;
                                        }

                                    }
                                }
                            }
                        }
                        //MUESTRA EL COMPROBANTE CUANDO SE REVISAN METAS Y CUANDO NO
                        if (opcionPT3 != 3){
                            System.err.println("Realizando el pago...");
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
                    opcion6PT = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                    continue; 
                }
                if (opcion6PT != 1 && opcion6PT != 0){
                    System.out.println("Seleccione una opción válida.");
                    continue;
                }
                else if (opcion6PT == 1){
                    System.out.println("\n");
                    break ULTIMO_LOOP;
                } 
                else{
                    System.out.println("Volviendo al menú principal.\n");
                    break LOOP_PRINCIPAL;
                }
            }
        }
        sc.nextLine();
    }  
}
