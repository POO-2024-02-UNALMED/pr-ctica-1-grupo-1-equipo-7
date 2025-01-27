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
            System.out.println("\nEligió la opción de envio de pedidos. \n\nSeleccione al cliente que realizó el pedido. Oprima  0 para salir.");
            System.out.println("\n0. Salir");
            System.out.println(Cliente.mostrarClientes());
            int seleccion = -1;
            while(true){
                Cliente clienteSeleccionado = null;
                int confirmacionCliente = 0;
                while(confirmacionCliente == 0){
                    while (true) {
                        try {
                            System.out.print("» ");
                            seleccion = sc.nextInt();
                            if (seleccion == 0) {
                                System.out.println("\nSaliendo...");
                                return;
                            } 
                            else if (seleccion > 0 && seleccion <= Cliente.listaClientes.size()){
                                break;
                            } 
                            else {
                                System.out.println("\nNúmero fuera de rango. Por favor, elija un cliente válido.");
                            }
                        } 
                        catch (Exception e) {
                            System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
                            sc.nextLine();
                        }
                    }
                    System.out.println("\nHa seleccionado el cliente: "+ Cliente.getListaClientes().get(seleccion - 1).getNombre());
                    System.out.println("\nPara confirmar, ingrese '1'. Para regresar al menú anterior, ingrese '0'.");

                    while (true) {
                        System.out.print("\n» ");
                        String eleccion = sc.next();

                        if (eleccion.equals("1")) {
                            clienteSeleccionado = Cliente.getListaClientes().get(seleccion - 1);
                            System.out.println("\nCliente confirmado: " + clienteSeleccionado.getNombre());
                            confirmacionCliente = 1;
                            break;
                        } 
                        else if (eleccion.equals("0")) {
                            System.out.println("\nRegresando al menú anterior...");
                            break;
                        } 
                        else {
                            System.out.println("\nPor favor, ingrese '1' para confirmar o '0' para volver al menú anterior.");
                        }
                    }
                }
                System.out.println("\nSeleccione la tienda desde la cual se enviará el pedido. Si no desea continuar, presione 0 para salir.");
                System.out.println("\nListado de Tiendas:");
                System.out.println("0. Salir");
                System.out.println(Fabrica.mostrarTiendas(true));

                int opcion = -1;
                Tienda tiendaSeleccionada = null;
                int confirmacionTienda = 0;
                while(confirmacionTienda == 0){
                    while (true) {
                        try {
                            System.out.print("\n» ");
                            opcion = sc.nextInt();
                            if (opcion == 0) {
                                System.out.println("\nSaliendo...");
                                return;
                            } 
                            else if (opcion > 0 && opcion <= Fabrica.getListaTienda().size()){
                                tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                                break;
                            } 
                            else {
                                System.out.println("\nNúmero fuera de rango. Por favor, elija una tienda válida.");
                            }
                        } 
                        catch (Exception e) {
                            System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
                            sc.nextLine();
                        }
                    }
                    System.out.println("\nTienda Seleccionada: "+ tiendaSeleccionada.getNombre());
                    System.out.println("\nPara confirmar la tienda seleccionada, ingrese '1'. Si desea cambiar su selección, ingrese '0'.");

                    while (true) {
                        System.out.print("\n» ");
                        String eleccion = sc.next();

                        if (eleccion.equals("1")) {
                            tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                            System.out.println("\nTienda confirmada: " + tiendaSeleccionada.getNombre());
                            confirmacionTienda = 1;
                            break;
                        } 
                        else if (eleccion.equals("0")) {
                            System.out.println("\nRegresando al menú anterior...");
                            break;
                        } 
                        else {
                            System.out.println("\nLa opción ingresada no es válida. Por favor, ingrese '1' para confirmar la tienda seleccionada o '0' para cambiarla.");
                        }
                    }
                }
                System.out.println("\nIndique la cantidad de productos que desea enviar (máximo 5).");
                int cantidadProductosSeleccionados = -1;
                int confirmacionCantidadProductos = 0;
                while (confirmacionCantidadProductos == 0) {
                    try {
                        System.out.print("\n» ");
                        cantidadProductosSeleccionados = sc.nextInt();
                        if (cantidadProductosSeleccionados > 0 && cantidadProductosSeleccionados <= 5) {
                            System.out.println("\nHa ingresado que desea enviar " + cantidadProductosSeleccionados + " productos. Para confirmar, ingrese '1'. Si desea cambiar la cantidad, ingrese '0'.");
                            while (true) {
                                String eleccion = sc.next();
                        
                                if (eleccion.equals("1")) {
                                    tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                                    System.out.println("\nHa confirmado que desea enviar " + cantidadProductosSeleccionados + " productos.");
                                    confirmacionCantidadProductos = 1;
                                    break;
                                } 
                                else if (eleccion.equals("0")) {
                                    System.out.println("\nPor favor, ingrese nuevamente la cantidad de productos que desea enviar.");
                                    break;
                                } 
                                else {
                                    System.out.println("\nLa opción ingresada no es válida. Por favor, ingrese '1' para confirmar la cantidad de productos a enviar o '0' si desea modificarla.");
                                }
                            }
                        }
                        else {
                            System.out.println("\nEl valor ingresado está fuera del rango permitido. Recuerde que el máximo de productos a enviar es 5.");
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
                        sc.nextLine();
                    }
                }
                ArrayList<Producto> listaProductosPedidos = new ArrayList<>();
                ArrayList<ArrayList<Object>> listaProductosTienda = tiendaSeleccionada.listaProductosTienda();

                System.out.println("\nPor favor, seleccione los productos de manera individual. Si desea cancelar el envío, ingrese 0.");
                for (int i = 0; i < cantidadProductosSeleccionados; i++) {
                    if (i != 0) {
                        System.out.println("\nPor favor, seleccione el siguiente producto para enviar.");
                    }
                    while (true) {
                        System.out.println("0. Salir");
                        try {
                            // Mostrar los productos filtrados
                            System.out.println(tiendaSeleccionada.mostrarListaProductosTienda(listaProductosTienda));
                            System.out.print("\n» ");
                            int eleccion = sc.nextInt();

                            if (eleccion == 0) {
                                System.out.println("\nSaliendo...");
                                sc.close();
                                return;
                            } else if (eleccion > 0 && eleccion <= listaProductosTienda.size()) {
                                // Obtener el producto seleccionado
                                Producto productoSeleccionado = (Producto) listaProductosTienda.get(eleccion - 1).get(0);
                                int cantidadProducto = (int) listaProductosTienda.get(eleccion - 1).get(1);

                                // Validar que haya stock
                                if (cantidadProducto <= 0) {
                                    System.out.println("\nEl producto seleccionado ya no tiene stock disponible. Por favor, elija otro.");
                                    continue; // Volver al inicio del bucle para pedir otro producto
                                }

                                System.out.println("\nPara confirmar, ingrese 1. Si desea volver a ingresar el producto, ingrese 0.");

                                while (true) {
                                    System.out.print("\n» ");
                                    int confirmacionProductoSeleccionado = sc.nextInt();
                                    if (confirmacionProductoSeleccionado == 1) {
                                        // Agregar el producto a la lista de pedidos y actualizar la cantidad en stock
                                        listaProductosPedidos.add(productoSeleccionado);
                                        listaProductosTienda.get(eleccion - 1).set(1, cantidadProducto - 1);

                                        System.out.println("\nProducto agregado: " + productoSeleccionado.getNombre());
                                        break;
                                    } else if (confirmacionProductoSeleccionado == 0) {
                                        break; // Permitir al usuario seleccionar otro producto
                                    } else {
                                        System.out.println("\nPor favor, ingrese 1 para confirmar su selección o 0 para volver a ingresar el producto.");
                                    }
                                }
                                break; // Salir del bucle principal tras confirmar o rechazar el producto
                            } else {
                                System.out.println("\nNúmero fuera de rango. Por favor, elija un producto válido.");
                            }
                        } catch (Exception e) {
                            System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
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
                        System.out.println("\nError: Peso inválido para el producto " + producto.getNombre());
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
                System.out.println("\nPor favor, elija el transporte que desea utilizar para su envío:");
                System.out.println("\nOpciones de transporte disponibles:");
                System.out.println("0. Salir");
                System.out.println(TipoTransporte.mostrarTipoTransporteSegunCarga(listaTransporteFiltrada, envioGratis));
                TipoTransporte tipoTransporteSeleccionado;
                while (true) {
                    try {
                        System.out.print("\n» ");
                        opcion = sc.nextInt();
                        if (opcion == 0) {
                            System.out.println("\nSaliendo...");
                            sc.close();
                            return;
                        } 
                        else if (opcion > 0 && opcion <= listaTransporteFiltrada.size()){
                            tipoTransporteSeleccionado = listaTransporteFiltrada.get(opcion -1);
                            break;
                        } 
                        else {
                            System.out.println("\nNúmero fuera de rango. Por favor, elija un transporte válido.");
                        }
                    } 
                    catch (Exception e) {
                        System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
                        sc.nextLine();
                    }
                }
                Transporte transporteSeleccionado = null;
                for (Conductor conductor : Conductor.getListaConductores()){
                    if (conductor.getTransporte().getTipoTransporte() == tipoTransporteSeleccionado)
                    transporteSeleccionado = conductor.getTransporte();
                }
                if(envioGratis==true){
                    System.out.println("\nHa escogido el transporte: " + transporteSeleccionado.getTipoTransporte().getNombre() + "\n" + "- Precio: 0.0");
                }
                else{
                    System.out.println("\nHa escogido el transporte: " + transporteSeleccionado.getTipoTransporte().getNombre() + "\n" + "- Precio: " + transporteSeleccionado.getTipoTransporte().getPrecioEnvio()+"\n");
                }
                String formatoFecha = "dd/MM/yyyy";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoFecha);

                boolean fechaValida = false;
                LocalDate fechaVenta = null;

                System.out.println("\nPor favor, ingrese el día en que se realiza la venta (formato: DD/MM/AAAA). Asegúrese de que la fecha sea válida.");
                while (!fechaValida) {
                    System.out.print("\nIngrese una fecha:  » ");
                    String entrada = sc.nextLine();
        
                    try {
                        // Convierte la fecha en formato String a LocalDate
                        fechaVenta = LocalDate.parse(entrada, formatter);
                        System.out.println("\nLa fecha ingresada es válida: " + fechaVenta.format(formatter));
                        fechaValida = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("\nLa fecha ingresada no es válida o no cumple con el formato DD/MM/AAAA. Intente nuevamente.");
                    }
                }
                System.out.println("\nGenerando Factura...");
                System.out.println("\n¡Factura creada con éxito! A continuación, se mostrará la factura:\n");
                System.out.println(tiendaSeleccionada.enviarPedido(listaProductosPedidos, transporteSeleccionado, clienteSeleccionado,fechaVenta));
                tiendaSeleccionada.getVendedor().aumentarCargaTrabajo();
                transporteSeleccionado.getConductor().aumentarCargaTrabajo();
                tiendaSeleccionada.eliminarProductosPorNombre(listaProductosPedidos);
                System.out.println(tiendaSeleccionada.mostrarListaProductosTienda(listaProductosTienda));
                //transporteSeleccionado.getConductor().aumentarPesoTransportado(totalPeso);s
                return;
            }

        }
    }
}








                
                
                                
                  
