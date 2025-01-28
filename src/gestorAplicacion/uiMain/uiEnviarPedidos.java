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

    // Método principal para el envío de pedidos
    public static void enviar() {
        Scanner sc = new Scanner(System.in);
        
        // Bucle principal para manejar el flujo del envío de pedidos
        while (true){
            System.out.println("\nEligió la opción de envio de pedidos. \n\nSeleccione al cliente que realizó el pedido. Oprima  0 para salir.");
            System.out.println("\n0. Salir");
            System.out.println(Cliente.mostrarClientes());
            int seleccion = -1;
            
            // Selección de cliente
            while(true){
                Cliente clienteSeleccionado = null;
                int confirmacionCliente = 0;

                // Bucle para confirmar la selección del cliente
                while(confirmacionCliente == 0){
                    while (true) {
                        try {
                            System.out.print("» ");
                            seleccion = sc.nextInt();
                            
                            if (seleccion == 0) { // Opción para salir
                                System.out.println("\nSaliendo...");
                                try {
                                    Thread.sleep(2000); // Pausa de 2 segundos
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return; // Salir del método
                            } 
                            else if (seleccion > 0 && seleccion <= Cliente.listaClientes.size()){
                                break; // Cliente seleccionado correctamente
                            } 
                            else {
                                System.out.println("\nNúmero fuera de rango. Por favor, elija un cliente válido.");
                            }
                        } 
                        catch (Exception e) {
                            System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
                            sc.nextLine(); // Limpiar buffer
                        }
                    }

                    // Confirmación de cliente
                    System.out.println("\nHa seleccionado el cliente: "+ Cliente.getListaClientes().get(seleccion - 1).getNombre());
                    System.out.println("\nPara confirmar, ingrese '1'. Para regresar al menú anterior, ingrese '0'.");

                    while (true) {
                        System.out.print("\n» ");
                        String eleccion = sc.next();

                        if (eleccion.equals("1")) { // Confirmar cliente
                            clienteSeleccionado = Cliente.getListaClientes().get(seleccion - 1);
                            System.out.println("\nCliente confirmado: " + clienteSeleccionado.getNombre());
                            confirmacionCliente = 1; // Confirmación de selección
                            break;
                        } 
                        else if (eleccion.equals("0")) { // Regresar al menú anterior
                            System.out.println("\nRegresando al menú anterior...");
                            try {
                                Thread.sleep(2000); // Pausa de 2 segundos
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        } 
                        else {
                            System.out.println("\nPor favor, ingrese '1' para confirmar o '0' para volver al menú anterior.");
                        }
                    }
                }

                // Selección de tienda desde la cual se enviará el pedido
                System.out.println("\nSeleccione la tienda desde la cual se enviará el pedido. Si no desea continuar, presione 0 para salir.");
                System.out.println("\nListado de Tiendas:");
                System.out.println("0. Salir");
                System.out.println(Fabrica.mostrarTiendas(true));

                int opcion = -1;
                Tienda tiendaSeleccionada = null;
                int confirmacionTienda = 0;

                // Bucle para confirmar la tienda seleccionada
                while(confirmacionTienda == 0){
                    while (true) {
                        try {
                            System.out.print("\n» ");
                            opcion = sc.nextInt();
                            if (opcion == 0) { // Opción para salir
                                System.out.println("\nSaliendo...");
                                try {
                                    Thread.sleep(2000); // Pausa de 2 segundos
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                return;
                            } 
                            else if (opcion > 0 && opcion <= Fabrica.getListaTienda().size()){
                                tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                                break; // Tienda seleccionada correctamente
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

                    // Confirmación de tienda
                    System.out.println("\nTienda Seleccionada: "+ tiendaSeleccionada.getNombre());
                    System.out.println("\nPara confirmar la tienda seleccionada, ingrese '1'. Si desea cambiar su selección, ingrese '0'.");

                    while (true) {
                        System.out.print("\n» ");
                        String eleccion = sc.next();

                        if (eleccion.equals("1")) { // Confirmar tienda
                            tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                            System.out.println("\nTienda confirmada: " + tiendaSeleccionada.getNombre());
                            confirmacionTienda = 1;
                            break;
                        } 
                        else if (eleccion.equals("0")) { // Regresar al menú anterior
                            System.out.println("\nRegresando al menú anterior...");
                            try {
                                Thread.sleep(2000); // Pausa de 2 segundos
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            break;
                        } 
                        else {
                            System.out.println("\nLa opción ingresada no es válida. Por favor, ingrese '1' para confirmar la tienda seleccionada o '0' para cambiarla.");
                        }
                    }
                }

                // Selección de la cantidad de productos a enviar
                System.out.println("\nIndique la cantidad de productos que desea enviar (máximo 5).");
                int cantidadProductosSeleccionados = -1;
                int confirmacionCantidadProductos = 0;
                while (confirmacionCantidadProductos == 0) {
                    try {
                        System.out.print("\n» ");
                        cantidadProductosSeleccionados = sc.nextInt();
                        if (cantidadProductosSeleccionados > 0 && cantidadProductosSeleccionados <= 5) { // Validación de cantidad
                            System.out.println("\nHa ingresado que desea enviar " + cantidadProductosSeleccionados + " productos. Para confirmar, ingrese '1'. Si desea cambiar la cantidad, ingrese '0'.");
                            while (true) {
                                System.out.print("\n» ");
                                String eleccion = sc.next();
                        
                                if (eleccion.equals("1")) { // Confirmación de cantidad de productos
                                    tiendaSeleccionada = Fabrica.getListaTienda().get(opcion - 1);
                                    System.out.println("\nHa confirmado que desea enviar " + cantidadProductosSeleccionados + " productos.");
                                    confirmacionCantidadProductos = 1;
                                    break;
                                } 
                                else if (eleccion.equals("0")) { // Volver a ingresar cantidad
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

                // Lista para almacenar los productos que el cliente ha seleccionado para su pedido
                ArrayList<Producto> listaProductosPedidos = new ArrayList<>();

                // Lista de productos de la tienda seleccionada
                ArrayList<ArrayList<Object>> listaProductosTienda = tiendaSeleccionada.listaProductosTienda();

                // Instrucción al cliente para seleccionar productos
                System.out.println("\nPor favor, seleccione los productos de manera individual. Si desea cancelar el envío, ingrese 0.");

                // Bucle para solicitar los productos uno por uno
                for (int i = 0; i < cantidadProductosSeleccionados; i++) {
                    if (i != 0) {
                        System.out.println("\nPor favor, seleccione el siguiente producto para enviar.");
                    }
                    while (true) {
                        System.out.println("0. Salir");
                        try {
                            // Mostrar la lista de productos disponibles
                            System.out.println(tiendaSeleccionada.mostrarListaProductosTienda(listaProductosTienda));
                            System.out.print("\n» ");
                            int eleccion = sc.nextInt();

                            // Si el usuario elige salir, termina el proceso
                            if (eleccion == 0) {
                                System.out.println("\nSaliendo...");
                                try {
                                    Thread.sleep(2000); // Pausa de 2000 milisegundos (2 segundos)
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                sc.close();
                                return;
                            } else if (eleccion > 0 && eleccion <= listaProductosTienda.size()) {
                                // Obtener el producto seleccionado y la cantidad disponible
                                Producto productoSeleccionado = (Producto) listaProductosTienda.get(eleccion - 1).get(0);
                                int cantidadProducto = (int) listaProductosTienda.get(eleccion - 1).get(1);

                                // Verificar si hay stock disponible
                                if (cantidadProducto <= 0) {
                                    System.out.println("\nEl producto seleccionado ya no tiene stock disponible. Por favor, elija otro.");
                                    continue; // Volver a pedir otro producto
                                }

                                System.out.println("\nPara confirmar, ingrese 1. Si desea volver a ingresar el producto, ingrese 0.");

                                while (true) {
                                    System.out.print("\n» ");
                                    int confirmacionProductoSeleccionado = sc.nextInt();
                                    if (confirmacionProductoSeleccionado == 1) {
                                        // Agregar el producto a la lista de pedidos y actualizar el stock
                                        listaProductosPedidos.add(productoSeleccionado);
                                        listaProductosTienda.get(eleccion - 1).set(1, cantidadProducto - 1);

                                        System.out.println("\nProducto agregado: " + productoSeleccionado.getNombre());
                                        try {
                                            Thread.sleep(500); // Pausa de 500 milisegundos (0.5 segundo)
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
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

                // Calcular el peso total de los productos seleccionados
                double totalPeso = 0.0;
                for (Producto producto : listaProductosPedidos) {
                    double peso = producto.getPeso();
                    if (peso > 0) { // Validamos que el peso sea positivo
                        totalPeso += peso;
                    } else {
                        System.out.println("\nError: Peso inválido para el producto " + producto.getNombre());
                    }
                }

                // Determinar los tipos de transporte posibles según el peso total
                ArrayList<TipoTransporte> listaPosibleTransporte = TipoTransporte.crearTipoTransporteSegunCarga(totalPeso);
                ArrayList<TipoTransporte> listaTransporteFiltrada = new ArrayList<>();

                // Filtrar los transportes disponibles que coinciden con los de los conductores
                for (Conductor conductor : Conductor.getListaConductores()) {
                    TipoTransporte conductorTipoTransporte = conductor.getTransporte().getTipoTransporte();
                    for (TipoTransporte posibleTransporte : listaPosibleTransporte) {
                        if (conductorTipoTransporte.equals(posibleTransporte)) {
                            listaTransporteFiltrada.add(conductor.getTransporte().getTipoTransporte());
                        }
                    }
                }

                // Verificar si el envío es gratis
                boolean envioGratis = Transporte.enviarGratis(listaProductosPedidos);

                // Solicitar al cliente que seleccione el tipo de transporte
                System.out.println("\nPor favor, elija el transporte que desea utilizar para su envío:");
                System.out.println("\nOpciones de transporte disponibles:");
                System.out.println("0. Salir");
                System.out.println(TipoTransporte.mostrarTipoTransporteSegunCarga(listaTransporteFiltrada, envioGratis));

                // Bucle para pedir la opción de transporte hasta que sea válida
                TipoTransporte tipoTransporteSeleccionado;
                while (true) {
                    try {
                        System.out.print("\n» ");
                        opcion = sc.nextInt();
                        if (opcion == 0) {
                            System.out.println("\nSaliendo...");
                            try {
                                Thread.sleep(1000); // Pausa de 1000 milisegundos (1 segundos)
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            return;
                        } else if (opcion > 0 && opcion <= listaTransporteFiltrada.size()) {
                            tipoTransporteSeleccionado = listaTransporteFiltrada.get(opcion - 1);
                            break;
                        } else {
                            System.out.println("\nNúmero fuera de rango. Por favor, elija un transporte válido.");
                        }
                    } catch (Exception e) {
                        System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
                        sc.nextLine();
                    }
                }

                // Determinar el transporte seleccionado
                Transporte transporteSeleccionado = null;
                for (Conductor conductor : Conductor.getListaConductores()) {
                    if (conductor.getTransporte().getTipoTransporte() == tipoTransporteSeleccionado)
                        transporteSeleccionado = conductor.getTransporte();
                }

                // Mostrar detalles del transporte seleccionado
                if (envioGratis) {
                    System.out.println("\nHa escogido el transporte: " + transporteSeleccionado.getTipoTransporte().getNombre() + "\n" + "- Precio: 0.0");
                } else {
                    System.out.println("\nHa escogido el transporte: " + transporteSeleccionado.getTipoTransporte().getNombre() + "\n" + "- Precio: " + transporteSeleccionado.getTipoTransporte().getPrecioEnvio() + "\n");
                }

                // Solicitar y validar la fecha de la venta
                String formatoFecha = "dd/MM/yyyy";
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formatoFecha);
                boolean fechaValida = false;
                LocalDate fechaVenta = null;

                System.out.println("\nPor favor, ingrese el día en que se realiza la venta (formato: DD/MM/AAAA). Asegúrese de que la fecha sea válida.");
                sc.nextLine();
                while (!fechaValida) {
                    System.out.print("\nIngrese una fecha:  »");
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

                // Generar la factura
                System.out.println("\nGenerando Factura...");
                try {
                    Thread.sleep(1000); // Pausa de 1000 milisegundos (1 segundo)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("\n¡Factura creada con éxito! A continuación, se mostrará la factura:\n");
                System.out.println(tiendaSeleccionada.enviarPedido(listaProductosPedidos, transporteSeleccionado, clienteSeleccionado, transporteSeleccionado.getTipoTransporte().getPrecioEnvio(), fechaVenta));

                // Aumentar la carga de trabajo del vendedor y conductor
                tiendaSeleccionada.getVendedor().aumentarCargaTrabajo();
                transporteSeleccionado.getConductor().aumentarCargaTrabajo();

                // Eliminar los productos vendidos del inventario
                tiendaSeleccionada.eliminarProductosPorNombre(listaProductosPedidos);

                // Mensaje final
                System.out.println("¡Genial! 🎉 Los productos han sido enviados con éxito.");
                System.out.println("Si desea volver al menú principal, ingrese 1.");
                int opcionSalir = 0;

                while (opcionSalir != 1) {
                    System.out.print("\n» ");
                    opcionSalir = sc.nextInt();

                    if (opcionSalir == 1) {
                        System.out.println("Volviendo al menú principal...");
                        try {
                            Thread.sleep(2000); // Pausa de 2000 milisegundos (2 segundos)
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return;  // Sale del método, pero no del ciclo principal
                    } else {
                        System.out.println("Opción no válida. ¡Intenta de nuevo! 🤔");
                        System.out.println("Si desea volver al menú principal, ingrese 1.");
                    }
                }
            }
        }
    }
}