import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Prueba {
    public static void main(String[] args) {

        System.out.println("Eligió la opción de pagar a sus trabajadores.");
        LOOP_PRINCIPAL:
        while (true) {
            ArrayList<Integer> listaNumeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
            Scanner sc = new Scanner(System.in);
            System.out.println("\nSeleccione el tipo de empleado que desea pagarle.");
            System.out.println("1. Operarios \n2. Conductores \n3. Vendedores\n0. Volver al menú");
            int opcion;
            int opcion2;
            int opcion3;
            int opcion4;
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
                System.out.println("Entrada inválida. Por favor, ingrese un número que este en el rango [0-3].");
                continue;
            }

            // Validacion de la opcion del usuario, si la entrada es valida 
            // muestra la lista de los posibles trabajadores
            else if (opcion > 0 && opcion <= 3){
                switch (opcion) {
                    case 1:
                        
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        
                        break;
                }

                //Etiqueta segundo loop
                LOOP_SECUNDARIO:
                while(true){
                    System.out.println("\nElija el trabajador que desea pagarle. Seleccione un numero entre: [1 - " + listaNumeros.size()+"] \n 0. Volver al menu.");
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
                    if(opcion2 < 1 || opcion2 >listaNumeros.size()){
                        System.out.println("Escoja un numero que este dentro del rango.");
                        continue;

                    }else{
                        //Muestra el Pago que se le mandara al Trabajador sin ver las metas
                        System.out.println("\nSe selecciona el trabajdor y se hace lo de mostrar el pago potencial\n");
                        
                        //Etiqueta tercer loop
                        LOOP_TERCIARIO:
                        while(true){
                            //Preguntar si quiere revisar metas o seguir con el pago.
                            System.out.println("¿Desea continuar con el pago o desea revisar las metas del trabajador?.\n1. Si\n2. No\n3. Cambiar de Trabajador\n0. Volver al menu principal.");

                            //Verificación de que se ingrese un número.
                            try {
                                opcion3 = sc.nextInt();
                            } catch (Exception e) {
                                System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
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
                                System.out.println("revision de metas que falta");

                            }else if(opcion3 == 2){
                                
                                System.out.println("se hace el pago cuando el usuario no quiere pagarle a nadie mas");
                                System.out.println("COMPROBANTE \nAl trabajador:  se le envio un total de:" );
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
                if (opcion4 != 1 && opcion4 != 0){
                    System.out.println("Seleccione una opción valida.");
                    continue;
                }
                else if (opcion4 == 1){
                    break;
                } 
                else{
                    System.out.println("Volviendo al menu principal.");
                    break LOOP_PRINCIPAL;
                }
            }
        }     
    }   
}
