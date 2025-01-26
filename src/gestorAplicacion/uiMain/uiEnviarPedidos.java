package uiMain;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import javax.management.AttributeChangeNotificationFilter;

import gestion.*;
import produccion.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public interface uiEnviarPedidos {
    public static void enviar() {
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Eligió la opción de envio de pedidos. \nSeleccione al cliente que realizó el pedido. Oprima  0 para salir.");
            System.out.println("0. Salir");
            System.out.println(Cliente.mostrarClientes());
            int seleccion = -1;
            while(true){
                Cliente clienteSeleccionado = null;
                int confirmacionCliente = 0;
                while(confirmacionCliente == 0){
                    while (true) {
                        try {
                            seleccion = sc.nextInt();
                            if (seleccion == 0) {
                                System.out.println("Saliendo...");
                                sc.close();
                                return;
                            } 
                            else if (seleccion > 0 && seleccion <= Cliente.listaClientes.size()){
                                break;
                            } 
                            else {
                                System.out.println("Número fuera de rango. Por favor, elija un cliente válido.");
                            }
                        } 
                        catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número.");
                            sc.nextLine();
                        }
                    }
                    System.out.println("Ha seleccionado el cliente: "+ Cliente.getListaClientes().get(seleccion - 1).getNombre());
                    System.out.println("Para confirmar, ingrese 'Aceptar'. Para regresar al menú anterior, ingrese 'Regresar'.");
                    
                    while (true) {
                        String eleccion = sc.next().toLowerCase();
        
                        if (eleccion.equals("aceptar")) {
                            clienteSeleccionado = Cliente.getListaClientes().get(seleccion - 1);
                            System.out.println("Cliente confirmado: " + clienteSeleccionado.getNombre());
                            confirmacionCliente = 1;
                            break;
                        } 
                        else if (eleccion.equals("regresar")) {
                            System.out.println("Regresando al menú anterior...");
                            break;
                        } 
                        else {
                            System.out.println("Por favor, ingrese 'Aceptar' para confirmar o 'Regresar' para volver al menú anterior.");
                        }
                    }
                }
                System.out.println("Seleccione la tienda desde la cual se enviará el pedido. Si no desea continuar, presione 0 para salir.");
                System.out.println("Listado de Tiendas:");
                System.out.println("0. Salir");
                System.out.println(Fabrica.mostrarTiendas());

                int opcion = -1;
                Tienda tiendaSeleccionada = null;
                int confirmacionTienda = 0;
                while(confirmacionTienda == 0){
                    while (true) {
                        try {
                            opcion = sc.nextInt();
                            if (opcion == 0) {
                                System.out.println("Saliendo...");
                                sc.close();
                                return;
                            } 
                            else if (opcion > 0 && opcion <= Fabrica.getListaTienda().size()){
                                tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                                break;
                            } 
                            else {
                                System.out.println("Número fuera de rango. Por favor, elija una tienda válida.");
                            }
                        } 
                        catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número.");
                            sc.nextLine();
                        }
                    }
                    System.out.println("Tienda Seleccionada: "+ tiendaSeleccionada.getNombre());
                    System.out.println("Para confirmar la tienda seleccionada, escriba 'Aceptar'. Si desea cambiar su selección, escriba 'Regresar'.");
                    
                    while (true) {
                        String eleccion = sc.next().toLowerCase();
        
                        if (eleccion.equals("aceptar")) {
                            tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                            System.out.println("Tienda confirmada: " + tiendaSeleccionada.getNombre());
                            confirmacionTienda = 1;
                            break;
                        } 
                        else if (eleccion.equals("regresar")) {
                            System.out.println("Regresando al menú anterior...");
                            break;
                        } 
                        else {
                            System.out.println("La opción ingresada no es válida. Por favor, ingrese 'Aceptar' para confirmar la tienda seleccionada o 'Regresar' para cambiarla.");
                        }
                    }
                }
                System.out.println("Indique la cantidad de productos que desea enviar (máximo 5).");
                int cantidadProductosSeleccionados = -1;
                int confirmacionCantidadProductos = 0;
                while (confirmacionCantidadProductos == 0) {
                    try {
                        cantidadProductosSeleccionados = sc.nextInt();
                        if (cantidadProductosSeleccionados > 0 && cantidadProductosSeleccionados<=5) {
                            System.out.println("Ha ingresado que desea enviar "+ cantidadProductosSeleccionados +" productos. Para confirmar, ingrese 'Aceptar'. Si desea cambiar la cantidad, ingrese 'Regresar'");
                            while (true) {
                                String eleccion = sc.next().toLowerCase();

                                if (eleccion.equals("aceptar")) {
                                    tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                                    System.out.println("Ha confirmado que desea enviar "+ cantidadProductosSeleccionados+" productos.");
                                    confirmacionTienda = 1;
                                    break;
                                } 
                                else if (eleccion.equals("regresar")) {
                                    System.out.println("\"Por favor, ingrese nuevamente la cantidad de productos que desea enviar");
                                    break;
                                } 
                                else {
                                    System.out.println("La opción ingresada no es válida. Por favor, ingrese 'Aceptar' para confirmar la cantidad de productos a enviar o 'Regresar' si desea modificarla.");
                                }
                            }
                        }
                        else {
                            System.out.println("El valor ingresado está fuera del rango permitido. Recuerde que el máximo de productos a enviar es 5.");
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número.");
                        sc.nextLine();
                    }
                }
                ArrayList<Producto> listaProductosPedidos = new ArrayList<>();
                ArrayList<ArrayList<Object>> listaProductosTienda = tiendaSeleccionada.listaProductosTienda();
                // Itera segun la cantidad de productos seleccionados por el usuario
                for (int i = 0; i < cantidadProductosSeleccionados; i++) {
                    System.out.println("Por favor, seleccione los productos de manera individual. Si desea cancelar el envío, ingrese 0.");
                    while (true) {
                        System.out.println("0. Salir");
                        try {
                            // Mostrar los productos filtrados
                            tiendaSeleccionada.mostrarListaProductosTienda(listaProductosTienda);

                            int eleccion = sc.nextInt();

                            if (eleccion == 0) {
                                System.out.println("Saliendo...");
                                sc.close(); 
                                return;
                            } else if (eleccion > 0 && eleccion <= listaProductosTienda.size()) {
                                Producto productoSeleccionado = (Producto) listaProductosTienda.get(eleccion - 1).get(0);
                                System.out.println("Para confirmar, ingrese 0. Si desea volver a ingresar el producto, ingrese 1.");
                
                                while (true) {
                                    int confirmacionProductoSeleccionado = sc.nextInt();
                                    if (confirmacionProductoSeleccionado == 0) {
                                        listaProductosPedidos.add(productoSeleccionado);
                                        System.out.println("Producto agregado: " + productoSeleccionado.getNombre());
                                        break;
                                    } else if (confirmacionProductoSeleccionado == 1) {
                                        break; // Permitir al usuario seleccionar otro producto
                                    } else {
                                        System.out.println("Por favor, ingrese 0 para confirmar su selección o 1 para volver a ingresar el producto.");
                                    }

                                }
                                break; // Salir del bucle principal tras confirmar o rechazar el producto
                            } else {
                                System.out.println("Número fuera de rango. Por favor, elija un producto válido.");
                            }
                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número.");
                            sc.nextLine(); 
                        }
                    }
                }
                double totalPeso = 0.0;
                for (Producto producto : listaProductosPedidos) {
                    double peso = tiendaSeleccionada.venderProducto(producto);
                    if (peso > 0) { // Validamos que el peso sea positivo
                        totalPeso += peso;
                    } else {
                        System.out.println("Error: Peso inválido para el producto " + producto.getNombre());
                    }
                }
                ArrayList<TipoTransporte> listaPosibleTransporte = TipoTransporte.crearTipoTransporteSegunCarga(totalPeso);
                ArrayList<TipoTransporte>listaTransporteFiltrada = new ArrayList<>();
                for (Conductor conductor : Conductor.getListaConductores()) {
                    TipoTransporte conductorTipoTransporte = conductor.getTransporte().getTipoTransporte();
                    for (TipoTransporte posibleTransporte : listaPosibleTransporte) {
                        if (conductorTipoTransporte.equals(posibleTransporte)) {
                            listaTransporteFiltrada.add(conductor.getTransporte().getTipoTransporte());
                        }
                    }
                }
                boolean envioGratis = Transporte.enviarGratis(listaProductosPedidos);
                System.out.println("Por favor, elija el transporte que desea utilizar para su envío:");
                System.out.println("0. Salir");
                System.out.println(TipoTransporte.mostrarTipoTransporteSegunCarga(listaTransporteFiltrada, envioGratis));
                TipoTransporte tipoTransporteSeleccionado;
                while (true) {
                    try {
                        opcion = sc.nextInt();
                        if (opcion == 0) {
                            System.out.println("Saliendo...");
                            sc.close();
                            return;
                        } 
                        else if (opcion > 0 && opcion <= listaTransporteFiltrada.size()){
                            tipoTransporteSeleccionado = listaTransporteFiltrada.get(opcion -1);
                            break;
                        } 
                        else {
                            System.out.println("Número fuera de rango. Por favor, elija un transporte válido.");
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("Entrada inválida. Por favor, ingrese un número.");
                        sc.nextLine();
                    }
                }
                Transporte transporteSeleccionado = null;
                for (Conductor conductor : Conductor.getListaConductores()){
                    if (conductor.getTransporte().getTipoTransporte() == tipoTransporteSeleccionado)
                    transporteSeleccionado = conductor.getTransporte();
                }
                if(envioGratis==true){
                    System.out.println("Ha escogido el transporte: " + transporteSeleccionado.getTipoTransporte().getNombre() + "\n" + "- Precio: 0.0");
                }
                else{
                    System.out.println("Ha escogido el transporte: " + transporteSeleccionado.getTipoTransporte().getNombre() + "\n" + "- Precio: " + transporteSeleccionado.getTipoTransporte().getPrecioEnvio());
                }
                String formatoFecha = "dd/MM/yyyy";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoFecha);

                boolean fechaValida = false;
                LocalDate fechaVenta = null;

                System.out.println("Por favor, ingrese el día en que se realiza la venta (formato: DD/MM/AAAA). Asegúrese de que la fecha sea válida.");
                while (!fechaValida) {
                    System.out.print("Ingrese una fecha: ");
                    String entrada = sc.nextLine();
        
                    try {
                        // Convierte la fecha en formato String a LocalDate
                        fechaVenta = LocalDate.parse(entrada, formatter);
                        System.out.println("La fecha ingresada es válida: " + fechaVenta.format(formatter));
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("La fecha ingresada no es válida o no cumple con el formato DD/MM/AAAA. Intente nuevamente.");
                    }
                }
                System.out.println("Generando Factura...");
                System.out.println("¡Factura creada con éxito! A continuación, se mostrará la factura:\n");
                tiendaSeleccionada.enviarPedido(listaProductosPedidos, transporteSeleccionado, clienteSeleccionado,fechaVenta);
        }
    }
}
}






                
                
                                
                  
