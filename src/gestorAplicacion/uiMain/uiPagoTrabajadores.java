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
        System.out.println("\nEligió la opción de pagar a sus trabajadores.");
        Scanner sc = new Scanner(System.in);
        // Etiqueta del primer loop
        LOOP_PRINCIPAL:
        while (true) {
            ArrayList<Persona> listaTrabajadores = new ArrayList<>();  // Se crea para usarla cuando se verifique el tipo de empleado que escogió.
            System.out.println("Seleccione el tipo de empleado que desea pagarle.");
            System.out.println("1. Operarios \n2. Conductores \n3. Vendedores\n0. Volver al menú.");
            System.out.print("» ");
            int opcionPT;
            int opcionPT2;
            int opcionPT3;
            int opcionPT4;
            int opcionPT5;
            int opcionPT6;
            int pagoPorMetas = 0;
            try {
                opcionPT = sc.nextInt();
            } catch (Exception e) {
                System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
                sc.nextLine(); // Limpiar el buffer
                continue; 
            }
            
            if (opcionPT == 0) {
                System.out.println("Volviendo al menú principal.\n");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException o) {
                    o.printStackTrace();
                }
                break;
            }

            else if(opcionPT != 1 && opcionPT != 2 && opcionPT != 3){
                System.out.println("\nEntrada inválida. Por favor, ingrese un número que esté en el rango [0-3].");
                continue;
            }

            // Validación de la opción del usuario: si la entrada es válida,
            // muestra la lista de los posibles trabajadores.
            else if (opcionPT > 0 && opcionPT <= 3){
                switch (opcionPT) {
                    case 1:
                        for(Operario i : Operario.getListaOperario()){
                            listaTrabajadores.add(i);
                        }
                        
                        break;
                    case 2:
                        for(Conductor i : Conductor.getListaConductores()){
                            listaTrabajadores.add(i);
                        }
                        break;
                    case 3:
                        for(Vendedor i : Vendedor.getListaVendedores()){
                            listaTrabajadores.add(i);
                        }
                        break;
                }

                // Etiqueta del segundo loop
                LOOP_SECUNDARIO:
                while(true){
                    ArrayList<Persona> trabajadores = Fabrica.busquedaTrabajo(listaTrabajadores);
                    if(trabajadores.isEmpty() == true){
                        System.out.println("\nNo hay trabajadores de este tipo para pagarles.");
                        try {
                            Thread.sleep(650);
                        } catch (InterruptedException o) {
                            o.printStackTrace();
                        }
                        break LOOP_SECUNDARIO;
                    }
                    System.out.println("Mostrando trabajadores...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Fabrica.mostrarPersonas(trabajadores));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException o) {
                        o.printStackTrace();
                    }
                    System.out.println("Elija el trabajador que desea pagarle. Seleccione un número entre: [1 - " + trabajadores.size() + "] \n0. Volver al menú.");
                    System.out.print("» ");
                    // Para validar que el usuario ingrese un número válido otra vez
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
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException o) {
                            o.printStackTrace();
                        }
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

                        // Verificar si quiere revisar metas o continuar
                        LOOP_TERCIARIO:
                        while(true){
                            // Preguntar si quiere revisar metas o seguir con el pago.
                            System.out.println("¿Quiere revisar las metas del trabajador?\n1. Sí\n2. No\n3. Cambiar de Trabajador\n0. Volver al menú principal.");
                            System.out.print("» ");

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
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException o) {
                                    o.printStackTrace();
                                }
                                break LOOP_PRINCIPAL;
                            }

                            // Validación de que el usuario quiere cambiar de trabajador.
                            if(opcionPT3 == 3){
                                System.out.println("Volviendo a mostrar la lista de trabajadores:");
                                try {
                                    Thread.sleep(700);
                                } catch (InterruptedException o) {
                                    o.printStackTrace();
                                }
                                break;
                            }

                            // Verificación de opción escogida.
                            if(opcionPT3 != 1 && opcionPT3 != 2 && opcionPT3 != 3 && opcionPT3 != 0){
                                System.out.println("Seleccione una opción válida.\n");
                                continue;

                            }else if(opcionPT3 == 1){

                                // LOOP para verificar metas
                                while(true){
                                    ArrayList<Meta> metasTrabajador = trabajadorSeleccionado.getMeta();
                                    ArrayList<Meta> metasTrabajadorNoPagas = new ArrayList<>();
                                    for(Meta i : metasTrabajador){
                                        if(i.getVerificador() == false){
                                            metasTrabajadorNoPagas.add(i);
                                        }
                                    }
                                    if(metasTrabajadorNoPagas.isEmpty() == true){
                                        System.out.println("El trabajador no tiene metas en este momento.\nProcediendo con el pago.");
                                        break LOOP_TERCIARIO;
                                    }
                                    System.out.println(trabajadorSeleccionado.mostrarMetas());
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException o) {
                                        o.printStackTrace();
                                    }
                                    System.out.println("\nElija la meta que desea revisar. Seleccione un número entre: [1 - " + metasTrabajadorNoPagas.size() + "]\n"+ (metasTrabajadorNoPagas.size()+1) +". Proceder con el pago. \n0. Volver al menú.");
                                    System.out.print("» ");

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
                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException o) {
                                            o.printStackTrace();
                                        }
                                        break LOOP_PRINCIPAL;
                                    }

                                    // Confirmación de que el número está dentro del rango
                                    if(opcionPT4 < 1 || opcionPT4 > metasTrabajadorNoPagas.size()){
                                        System.out.println("Escoja un número que esté dentro del rango.\n");
                                        continue;
                                    }
                                    else if(opcionPT4 == (metasTrabajadorNoPagas.size()+1)){
                                        break LOOP_TERCIARIO;
                                    }
                                    else{
                                        Meta metaSeleccionada = metasTrabajadorNoPagas.get(opcionPT4-1);
                                        String mensajeMeta = "\n" + metaSeleccionada.porcentajeCumplidos(trabajadorSeleccionado.getIndiceMeta()) + "\n";
                                        System.out.println("\nINFORMACIÓN DE LA META SELECCIONADA:");
                                        if (metaSeleccionada.cumpleMeta(trabajadorSeleccionado.getIndiceMeta()) == true){
                                            System.out.println("La meta ha sido cumplida exitosamente.\nSumaremos el pago indicado por haberlo conseguido." + mensajeMeta);
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
                                        System.out.print("» ");
                                        try {
                                            opcionPT5 = sc.nextInt();
                                        } catch (Exception e) {
                                            System.out.println("Entrada inválida. Por favor, ingrese un número.\n");
                                            sc.nextLine(); // Limpiar el buffer
                                            continue; 
                                        }
                                        if(opcionPT5 != 1 && opcionPT5 != 2){
                                            System.out.println("Escoja alguna de las opcionPTes.\n");
                                            continue;

                                        }else if(opcionPT5 == 1){
                                            System.out.println("\nVolviendo a mostrar metas.");
                                            try {
                                                Thread.sleep(600);
                                            } catch (InterruptedException o) {
                                                o.printStackTrace();
                                            }
                                            break LOOP_QUINTO;

                                        }else{
                                            break LOOP_TERCIARIO;
                                        }

                                    }
                                }
                            }else if(opcionPT3 == 2){
                                break LOOP_TERCIARIO;
                            }
                        }
                        if (opcionPT3 != 3){
                            double pagoTotal = pagoPotencial + pagoPorMetas;
                            Fabrica.cuentaBancaria.descontarDinero(pagoTotal);
                            trabajadorSeleccionado.recibirSueldo(pagoTotal);
                            System.out.println("Procesando pago...");
                            try {
                                Thread.sleep(1500);
                            } catch (InterruptedException o) {
                                o.printStackTrace();
                            }
                            System.out.println("\n------------------------------------------------------------");
                            System.out.println("COMPROBANTE \nTrabajador(a): " + trabajadorSeleccionado.getNombre() 
                            + " se le envió un total de: " + pagoTotal 
                            + "\n" + pagoPotencial + " Por las veces trabajadas\n" +
                            pagoPorMetas + " Por las metas cumplidas");
                            System.out.println("------------------------------------------------------------");
                            break LOOP_SECUNDARIO;
                        }
                    }
                }
            }
            ULTIMO_LOOP:
            while(true){
                System.out.println("\n¿Qué desea hacer? \n1. Pagar a otro trabajador. \n0. Volver al menú principal.");
                System.out.print("» ");
                try {
                    opcionPT6 = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                    continue; 
                }
                if (opcionPT6 != 1 && opcionPT6 != 0){
                    System.out.println("Seleccione una opción válida.");
                    continue;
                }
                else if (opcionPT6 == 1){
                    System.out.println("\n");
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException o) {
                        o.printStackTrace();
                    }
                    break ULTIMO_LOOP;
                } 
                else{
                    System.out.println("Volviendo al menú principal.\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException o) {
                        o.printStackTrace();
                    }
                    break LOOP_PRINCIPAL;
                }
            }
        }    
    }  
}
