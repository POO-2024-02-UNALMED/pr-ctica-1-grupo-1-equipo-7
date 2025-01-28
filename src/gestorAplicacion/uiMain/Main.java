package uiMain;
import gestion.*;
import produccion.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.management.AttributeChangeNotificationFilter;


import baseDatos.Load;

public class Main {
        static LocalDate fechaInicio;
        static LocalDate fechaFin;
        static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        static int num = 0;
        static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Load.cargar();
        Scanner sc = new Scanner(System.in);
        
        boolean salir = false;

        while (!salir) {
            System.out.println("\n¡Bienvenido al sistema de gestión!");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Enviar Pedidos");
            System.out.println("2. Devoluciones");
            System.out.println("3. Abastecer Tiendas");
            System.out.println("4. Pago Trabajadores");
            System.out.println("5. Estadísticas");
            System.out.println("0. Salir");
            System.out.print("» ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    // Llamar al método para enviar pedidos
                    enviarPedidos();
                    break;
                case 2:
                    // Llamar al método para devoluciones
                    devoluciones();
                    break;
                case 3:
                    // Llamar al método para abastecer tiendas
                    abastecer();
                    break;
                case 4:
                    // Llamar al método para pago de trabajadores
                    pagoTrabajadores();
                    break;
                case 5:
                    // Llamar al método para estadísticas
                    estadisticas();
                    break;
                case 0:
                    Load.guardar();
                    salir = true;
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, ingrese un número entre 0 y 5.");
                    break;
            }
        }
        sc.close();
    }

    // Método principal para el envío de pedidos
    public static void enviarPedidos() {
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
                            System.out.println("\nPor favor, seleccione nuevamente al cliente para continuar.\n");
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
                                return;
                            } 
                            // Validar que la elección esté dentro del rango correcto
                            else if (eleccion > 0 && eleccion <= listaProductosTienda.size()) {
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
                            } 
                            // Si el número ingresado está fuera del rango, mostrar un mensaje de error
                            else {
                                System.out.println("\nNúmero fuera de rango. Por favor, elija un producto válido.");
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("\nEntrada inválida. Por favor, ingrese un número.");
                            sc.nextLine(); // Limpiar el buffer de entrada
                        }
                    }
                }

                // Calcular el peso total de los productos seleccionados
                double totalPeso = Transporte.calcularTotalPeso(listaProductosPedidos);
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

                //Aumentar el indice de meta para el vendendor y conducto
                tiendaSeleccionada.getVendedor().aumentarIndiceMeta();
                transporteSeleccionado.getConductor().aumentarIndiceMeta(totalPeso);


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


    public static void devoluciones() {
        // Implementar la funcionalidad de devoluciones
        Scanner sc = new Scanner(System.in);
    
            while (true) {
                System.out.println("Eligió la opción de devoluciones.\nSeleccione la factura que desea consultar. Oprima 0 para salir.");
                System.out.println("0. Salir");
                String txt=Factura.mostrarFacturas();
                System.out.println(txt);
    
                int opcion;
                try {
                    opcion = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                    continue;
                }
    
                if (opcion == 0) {
                    System.out.println("Saliendo del menú de devoluciones.");
                    break;
                }
    
                if (opcion > 0 && opcion <= Factura.listaFacturas.size()) {
                    System.out.println("Eligió la factura con el número: " + opcion);
                    Factura factura = Factura.seleccionarFactura(opcion);
                    Tienda tienda=factura.getTienda();

    
                    while (true) {
                        System.out.println("Seleccione el producto que desea devolver o presione 0 para regresar al menú anterior: ");
                        String productos=Factura.mostrarProductosFactura(factura);
                        System.out.println(productos);
    
                        int opcion2;
                        try {
                            opcion2 = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, ingrese un número.");
                            sc.nextLine(); // Limpiar el buffer
                            continue;
                        }
    
                        if (opcion2 == 0) {
                            System.out.println("Regresando al menú de facturas.");
                            break;
                        }
    
                        if (opcion2 > 0 && opcion2 <= factura.getListaProductos().size()) {
                            if (Factura.todosDevueltos(factura.getListaProductos())) {
                                System.out.println("Todos los productos de esta factura ya han sido devueltos.");
                                break;
                            }
                            Producto producto = factura.seleccionarProducto(opcion2);
                            if (producto.getEstado().equals(estadosProducto.DEVUELTO)) {
                                System.out.println("El producto ya ha sido devuelto, elija otro.");
                            } else{
                                System.out.println("Eligió el producto: " + producto.getNombre());
                                System.out.println("Indique el motivo de la devolución: ");
                                String motivosDevolucion=Producto.mostrarMotivosDeDevolucion();
                                System.out.println("Motivos de devolución: \n" + motivosDevolucion); // Se imprimen los motivos de devolucion validos más la opcion de agregar un motivo propio
                                int motivoDevolucion=sc.nextInt();
                                String motivo=Producto.obtenerMotivoDeDevolucion(motivoDevolucion);
                                producto.setMotivoDevolucion(motivo); //Se agrega el motivo de la devolucion del producto. 


                                if (motivoDevolucion ==1 || motivoDevolucion==2 || motivoDevolucion==3 ){

                                    System.out.println("Por el motivo indicado, se le hará el reembolso del dinero.");
                                    Cliente cliente=tienda.devolverProducto(factura, producto);
                                    double valorADevolver=Fabrica.descontarDineroCuenta(producto); // Se descuenta el dinero de la cuenta de la fábrica
                                    // y se obtiene el valor del producto a devolver.
                                    Fabrica.cuentaBancaria.devolverDinero(valorADevolver, cliente); 
                                    cliente.removerProducto(producto);// Se remueve el producto de la lista de productos del cliente.
                                    System.out.println("Se le devolverá el valor de su producto, que es de $ "+ producto.getPrecio());
                                    System.out.println("----Devolviendo el dinero---"); 
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("El producto ha sido devuelto exitosamente y se ha reembolsado su dinero.");
                                    System.out.println("¿Qué desea hacer? \n1. Devolver otro producto \n0. Regresar al menú de facturas");
                                    int opcion3;
                                    switch (opcion3 = sc.nextInt()) {
                                        case 0:
                                            System.out.println("Saliendo del menú de devoluciones.");
                                            break;
                                        case 1:
                                            devoluciones();
                                        default:
                                            System.out.println("Opción inválida. Intente nuevamente.");
                                            break;
                                    }
                
                                }
                                else if (motivoDevolucion==Producto.motivosDevolucion.size()){
                                        System.out.println("Especifique su causa de la devolución: ");
                                        sc.nextLine(); // Limpiar el buffer antes de leer el motivo
                                        String causa =sc.nextLine();
                                        producto.setMotivoDevolucion(causa);
                                        Producto.motivosDevolucion.add(Producto.motivosDevolucion.size()-1, causa);
                                    }
                                    System.out.println("Por el motivo indicado, se le hará el cambio del producto.");
                                    System.out.println("Si el producto que seleccione tiene un precio menor al que desea cambiar, puede agregar otro producto para completar el valor restante. Es posible que tenga que pagar un excedente.\nNO se le devolverá el dinero restante.");
                                    System.out.println("Seleccione el producto por el cual desea cambiar: ");
                                    double precio=producto.getPrecio();
                                    System.out.println("El precio de su producto es de: $"+precio);
                                    ArrayList<Integer> seleccionProductos = new ArrayList<>();
                                    ArrayList<Producto> carrito = new ArrayList<>();
                                    double subtotal = 0;
                                    System.out.println("\nProductos disponibles para cambio:");
                                    ArrayList<Producto> productosDisponibles = tienda.mostrarProductos(producto);
                                  
                                    
                                    while (true) {
                                        //Se muestran los productos 
                                        //que el cliente puede cambiar. 
                                        if (productosDisponibles.isEmpty()){
                                            System.out.println("Se han agotado los productos en la tienda disponibles para cambiar. Se procederá al cambio del producto con su carrito actual");
                                            break; //Se considera el caso (poco probable pero no imposible) de que la tienda se quede sin productos para cambiar. 
                                        }
                                        ArrayList<Producto> productosUnicos=new ArrayList<>(); //Productos disponibles para cambiar, pero aparecen solo una vez
                                        ArrayList<Integer> frecuencias= new ArrayList<>(); //Lista que contiene la frecuencia de cada producto en la lista de productosDisponibles
                                        for (Producto p: productosDisponibles){
                                            boolean encontrado=false; 

                                            for (int i = 0; i < productosUnicos.size(); i++) {
                                                if (productosUnicos.get(i).getNombre().equals(p.getNombre())) {
                                                    // Si el producto ya está en la lista, aumentar su frecuencia
                                                    frecuencias.set(i, frecuencias.get(i) + 1);
                                                    encontrado = true;
                                                    break;
                                                }
                                            }
                                        
                                            if (!encontrado) {
                                                // Si no se encontró, agregar el producto a productosUnicos
                                                productosUnicos.add(p);
                                                frecuencias.add(1);
                                            }
                                        }
                                        
                                        for (int i = 0; i < productosUnicos.size(); i++) {
                                            Producto p = productosUnicos.get(i);
                                            int cantidadProducto=frecuencias.get(i);
                                            System.out.println((i + 1) + ". " + p.getNombre() + " - Precio: $" + p.getPrecio()+ " - Productos disponibles: "+ cantidadProducto);
                                            //Se imprime cada producto de la tienda que se puede cambiar, su precio y la cantidad que hay. 
                                        }
                                        // Pedir al usuario que seleccione un producto
                                        System.out.println("Ingrese el número del producto que desea añadir al carrito (o 0 para finalizar):");
                                        int opcion4 = sc.nextInt();
                            
                                        // Salir si el cliente no quiere añadir más productos
                                        if (opcion4 == 0) {
                                            System.out.println("Ha decidido no añadir más productos.");
                                            return;
                                        }
                            
                                        // Agregar la selección a la lista
                                        seleccionProductos.add(opcion4);
                                        Producto productoSeleccionado=productosUnicos.get(opcion4-1); //Se usa para eliminar el producto de la lista de productos disponibles al final de la iteracion. 
                            
                                        // Llamar al método de la tienda para procesar la selección.
                                        carrito = tienda.agregarProductosParaCambio(precio, seleccionProductos,productosUnicos);
                            
                                        // Mostrar los productos seleccionados
                                        System.out.println("\nResumen del cambio:");
                                        subtotal = 0;
                                        for (Producto p : carrito) {
                                            System.out.println("- " + p.getNombre() + ": $" + p.getPrecio());
                                            subtotal += p.getPrecio();
                                        }
                                        System.out.println("Subtotal actual: $" + subtotal);
                            
                                        // Verificar si el subtotal alcanzó el límite permitido
                                        if (subtotal > precio) {
                                            break;
                                        }
                            
                                        // Preguntar si desea continuar
                                        System.out.println("¿Desea continuar añadiendo productos? (1: Sí, 0: No)");
                                        int continuar = sc.nextInt();
                                        if (continuar==1){
                                            Iterator<Producto> iterator = productosDisponibles.iterator();
                                            while (iterator.hasNext()){
                                                Producto p=iterator.next();
                                                if (p.getId()==productoSeleccionado.getId()) {
                                                    iterator.remove();
                                            }
                                          }
                                          System.out.println("\nProductos disponibles para cambio:");

                                        }  
                                        else if (continuar == 0) {
                                            System.out.println("Proceso finalizado. Su carrito de cambio está listo.");
                                            break;
                                        }
                                    }

                                    double excedente = Fabrica.calcularExcedente(carrito, precio); //Calcula el excedente que debe pagar el cliente (si debe hacerlo) por su cambio.
                                    if (excedente > 0) {
                                        System.out.println("El valor total de los productos seleccionados supera el precio del producto a cambiar.");
                                        System.out.println("El excedente a pagar es de: $" + excedente);
                                    } else {
                                        System.out.println("El valor total de los productos seleccionados no supera el precio del producto a cambiar. Le recordamos que no se le devolverá el dinero restante.");
                                    }

                                    Cliente cliente=factura.getCliente();
                                    cliente.cuentaBancaria.transferirDinero(excedente, Fabrica.cuentaBancaria); // Se transfiere el excedente de la cuenta del cliente a la cuenta de la fábrica.
                                    cliente.removerProducto(producto); // Se remueve el producto de la lista de productos del cliente.
                                    producto.setEstado(estadosProducto.DEVUELTO);
                                    for (Producto p : carrito) {
                                        cliente.listaProductos.add(p); // Se añaden los productos seleccionados por el cambio al cliente.
                                        tienda.getListaProducto().remove(p); // Se eliminan los productos que el cliente seleccionó de la lista de la tienda 
                                    }
                                    System.out.println("Generando resumen final del cambio...");
                            
                                    // Mostrar resumen final del cambio
                                    try {
                                        Thread.sleep(2500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    System.out.println("\n----- Resumen final del cambio -----");
                                    System.out.println("Usted ha cambiado un "+ producto.getNombre()+" por:");

                                    for (Producto p : carrito) {
                                        System.out.println(" - " + p.getNombre() + ": $" + p.getPrecio());
                                    }
                                    System.out.println("Total del carrito: $" + subtotal+"\nExcedente pagado: "+excedente);
                                    System.out.println("---------------------------------------");
                                    
                                }
                            }
                                else{
                                    System.out.println("Motivo de devolución inválido. Intente nuevamente.");
                                }
                            }
                        } else {
                            System.out.println("Opción inválida. Intente nuevamente.");
                        }
                    }
                }  {
                    System.out.println("Opción inválida. Intente nuevamente.");
                }
        
    

        public static void abastecer() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        
        //INICIO DEL PROCESO DE ABASTECIMIENTO
        while (!salir) {
            System.out.println("========================================");
            System.out.println("¡Bienvenido a la opción de abastecer tienda!");
            System.out.println("Seleccione la tienda que desea abastecer (0 para salir):");
            System.out.println("========================================");
            System.out.println("0. Salir");
            System.out.println(Fabrica.mostrarTiendas());
            System.out.println("========================================");

            int tiendaSeleccionadaIndex = -1;
            Tienda tiendaSeleccionada = null;
            
            // Bucle para seleccionar la tienda
            while (tiendaSeleccionadaIndex < 0 || tiendaSeleccionadaIndex > Fabrica.getListaTienda().size()) {
                try {
                    System.out.print("» ");
                    tiendaSeleccionadaIndex = sc.nextInt();
                    if (tiendaSeleccionadaIndex == 0) {
                        try {
                            try {
                                Thread.sleep(1000);
                                System.out.println("Saliendo...");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                        }
                        return;
                    }
                    if (tiendaSeleccionadaIndex < 1 || tiendaSeleccionadaIndex > Fabrica.getListaTienda().size()) {
                        System.out.println("Número inválido. Ingrese un número entre 1 y " + Fabrica.getListaTienda().size() + ".");
                    } else {
                        boolean confirmacionTienda= false;
                        while(!confirmacionTienda){
                            tiendaSeleccionada= Fabrica.getListaTienda().get(tiendaSeleccionadaIndex - 1);
                            System.out.println("Tienda seleccionada: " + tiendaSeleccionada.getNombre());

                            System.out.println("¿Es correcta esta selección? (1 para sí, 2 para no)"); 
                            System.out.println("1. Sí, proceder");  
                            System.out.println("2. No, seleccionar otra tienda");
                            
                            try{
                                int confirmacion = sc.nextInt();
                                if (confirmacion == 1){
                                    System.out.println("Procediendo con la tienda seleccionada...");
                                    confirmacionTienda = true;
                                }else if (confirmacion == 2){
                                    System.out.println("========================================");
                                    System.out.println("Seleccione la tienda que desea abastecer (0 para salir):");
                                    System.out.println("========================================");
                                    System.out.println("0. Salir");
                                    System.out.println(Fabrica.mostrarTiendas());
                                    System.out.println("========================================");
                                    System.out.print("» ");
                                    tiendaSeleccionadaIndex = sc.nextInt();
                                
                                    if (tiendaSeleccionadaIndex == 0) {
                                        try {
                                            Thread.sleep(1000);
                                            System.out.println("Saliendo...");
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }
                                        return;
                                    }
                                    if (tiendaSeleccionadaIndex < 1 || tiendaSeleccionadaIndex > Fabrica.getListaTienda().size()) {
                                        System.out.println("Número inválido. Ingrese un número entre 1 y " + Fabrica.getListaTienda().size() + ".");
                                    } else {
                                        System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                        //confirmacionTienda = true;
                                    }

                                }

                            }catch(Exception e){
                                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                sc.nextLine(); // Limpiar el buffer
                            }
                    }
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                }
            }
                    // Mostrar los productos por categoría
                    System.out.println("========================================");
                    System.out.println("Productos por categoría en la tienda seleccionada:");
                    System.out.println(tiendaSeleccionada.productosPorCategoria(tiendaSeleccionada.getListaProducto()));
 
                    
                    // Lista para almacenar los productos seleccionados y sus cantidades
                    ArrayList<Producto> productosGenerados = new ArrayList<>();
                    // Lista temporal para almacenar los cambios en la cantidad de productos por categoría
                    ArrayList<Integer> conteoCategoriasTemporal = new ArrayList<>();
                    conteoCategoriasTemporal.add(tiendaSeleccionada.getCantidadActualPorCategoria("Herramientas"));
                    conteoCategoriasTemporal.add(tiendaSeleccionada.getCantidadActualPorCategoria("Muebles"));
                    conteoCategoriasTemporal.add(tiendaSeleccionada.getCantidadActualPorCategoria("Aseo"));
                    
                    // Bucle para añadir productos al abastecimiento
                    Double pesoTotalProductos = 0d;
                    while (true) {
                        // Selección de producto
                        System.out.println("========================================");
                        System.out.println("Seleccione el producto que desea enviar a la tienda (0 para salir):");
                        System.out.println("========================================");
                        System.out.println("0. Salir");
                        System.out.println(Fabrica.mostrarProductos());
                        System.out.println("========================================");
                        int productoSeleccionadoIndex = -1;
                        Producto productoSeleccionado = null;
                    
                        // Bucle para seleccionar el producto
                        while (productoSeleccionadoIndex < 0 || productoSeleccionadoIndex > Fabrica.getProductosDisponibles().size()) {
                            try {
                                System.out.print("» ");
                                productoSeleccionadoIndex = sc.nextInt();
                                if (productoSeleccionadoIndex == 0) {
                                    try {
                                        Thread.sleep(1000);
                                        System.out.println("Saliendo...");
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    return;
                                }
                                if (productoSeleccionadoIndex < 1 || productoSeleccionadoIndex > Fabrica.getProductosDisponibles().size()) {
                                    System.out.println("Número inválido. Ingrese un número entre 1 y " + Fabrica.getProductosDisponibles().size() + ".");
                                } else {
                                    System.out.println("========================================");
                                    productoSeleccionado = Fabrica.getProductosDisponibles().get(productoSeleccionadoIndex - 1);
                                    System.out.println("PRODUCTO SELECCIONADO: " + productoSeleccionado.getNombre());
                    
                                    // Selección de cantidad de productos a enviar
                                    String categoriaProducto = productoSeleccionado.getCategoria();
                                    int cantidadActual = tiendaSeleccionada.getCantidadActualPorCategoria(categoriaProducto);
                                    
                                    int cantidadMaxima;
                                    
                                    switch (categoriaProducto) {
                                        case "Construcción":
                                            cantidadActual = conteoCategoriasTemporal.get(0);
                                            cantidadMaxima = tiendaSeleccionada.getCapacidadMaximaMaterial();
                                            break;
                                        case "Muebles":
                                            cantidadActual = conteoCategoriasTemporal.get(1);
                                            cantidadMaxima = tiendaSeleccionada.getCapacidadMaximaConsumible();
                                            break;
                                        case "Aseo":
                                            cantidadActual = conteoCategoriasTemporal.get(2);
                                            cantidadMaxima = tiendaSeleccionada.getCapacidadMaximaLimpieza();
                                            break;
                                        default:
                                            cantidadMaxima = 0; 
                                            cantidadActual = 0;
                                            break;
                                    }
                                    int cantidadDisponible = cantidadMaxima - cantidadActual;
                                    
                                    //se establece el limite de productos a enviar
                                    System.out.println("Cantidad máxima de productos en la categoría " + categoriaProducto + ": " + cantidadDisponible);
                                    System.out.println("Ingrese la cantidad de productos a enviar:");
                    
                                    int cantidadAEnviar = -1;
                                    // Bucle para seleccionar cantidad de productos a enviar
                                    while (cantidadAEnviar < 0 || cantidadAEnviar > cantidadDisponible) {
                                        try {
                                            System.out.print("» ");
                                            cantidadAEnviar = sc.nextInt();
                                            if (cantidadAEnviar < 0 || cantidadAEnviar > cantidadDisponible) {
                                                System.out.println("Cantidad inválida. Ingrese un número entre 0 y " + cantidadDisponible + ".");
                                            } else {
                                                System.out.println("========================================");
                                                System.out.println("Enviando " + cantidadAEnviar + " productos de la categoría " + categoriaProducto + " a la tienda " + tiendaSeleccionada.getNombre());
                                                
                                                // Generar los productos y agregarlos a la lista
                                                productosGenerados.addAll(Fabrica.cantidadProductos(productoSeleccionado, cantidadAEnviar));
                    
                                                // Calcular el peso total de los productos seleccionados
                                                pesoTotalProductos += productoSeleccionado.getPeso() * cantidadAEnviar;
                    
                                                // Actualizar la cantidad de productos en la lista temporal
                                                switch (categoriaProducto) {
                                                    case "Herramientas":
                                                        conteoCategoriasTemporal.set(0, conteoCategoriasTemporal.get(0) + cantidadAEnviar);
                                                        break;
                                                    case "Muebles":
                                                        conteoCategoriasTemporal.set(1, conteoCategoriasTemporal.get(1) + cantidadAEnviar);
                                                        break;
                                                    case "Aseo":
                                                        conteoCategoriasTemporal.set(2, conteoCategoriasTemporal.get(2) + cantidadAEnviar);
                                                        break;
                                                }
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                            sc.nextLine(); // Limpiar el buffer
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                                sc.nextLine(); // Limpiar el buffer
                            }
                        }
                    
                        // Mostrar productos por categoría actualizados
                        System.out.println("Productos por categoría en la tienda seleccionada:");
                        //Sobeecarga de método productosPorCategoria
                        System.out.println(tiendaSeleccionada.productosPorCategoria(tiendaSeleccionada.getListaProducto(), conteoCategoriasTemporal));
                    
                        // Preguntar si desea añadir más productos
                        System.out.println("========================================");
                        System.out.println("¿Desea añadir más productos?");
                        System.out.println("(s) para sí, (v) para volver a elegir productos, cualquier otra tecla para continuar):");
                        System.out.println("========================================");
                       
                        try {
                            String respuesta = sc.next();
                            if (respuesta.equalsIgnoreCase("v")) {
                                //Reinicio de la lista de productos generados y la lista temporal de conteo de categorías
                                productosGenerados.clear();
                                conteoCategoriasTemporal.clear();
                                conteoCategoriasTemporal.add(tiendaSeleccionada.getCantidadActualPorCategoria("Herramientas"));
                                conteoCategoriasTemporal.add(tiendaSeleccionada.getCantidadActualPorCategoria("Muebles"));
                                conteoCategoriasTemporal.add(tiendaSeleccionada.getCantidadActualPorCategoria("Aseo"));
                                pesoTotalProductos = 0d; // Reiniciar el peso total
                                continue;
                            }
                            if (!respuesta.equalsIgnoreCase("s")) {
                                break;
                            }
                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Ingrese una letra.");
                            sc.nextLine(); // Limpiar el buffer
                        }
                    }            

            // Crear lista de transportes según el peso total de los productos
            ArrayList<TipoTransporte> listaTransportes = TipoTransporte.crearTipoTransporteSegunCarga(pesoTotalProductos);
            //Realizar ajuste a tipotransporte para que se pueda seleccionar el transporte segun el peso de los productos
            System.out.println("========================================");
            System.out.println(TipoTransporte.mostrarTipoTransporteSegunCarga(listaTransportes));
            System.out.println("========================================");

            // Selección de transporte
            System.out.println("Seleccione el tipo de transporte para enviar los productos. (0 para salir):");
            int transporteSeleccionadoIndex = -1;
            TipoTransporte transporteSeleccionado = null;

            while (transporteSeleccionadoIndex < 0 || transporteSeleccionadoIndex > listaTransportes.size()) {
                try {
                    System.out.print("» ");
                    transporteSeleccionadoIndex = sc.nextInt();
                    if (transporteSeleccionadoIndex == 0) {
                        try {
                            Thread.sleep(1000);
                            System.out.println("Saliendo...");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    if (transporteSeleccionadoIndex < 1 || transporteSeleccionadoIndex > listaTransportes.size()) {
                        System.out.println("Número inválido. Ingrese un número entre 1 y " + listaTransportes.size() + ".");
                    } else {
                        transporteSeleccionado = TipoTransporte.seleccionarTransporte(listaTransportes, transporteSeleccionadoIndex);
                        System.out.println("Transporte seleccionado: " + transporteSeleccionado.getNombre());
                        
                        // Buscar un conductor con el transporte seleccionado
                        Conductor conductorSeleccionado = null;
                        for (Conductor conductor : Conductor.getListaConductores()) {
                            if (conductor.getTransporte().getTipoTransporte().equals(transporteSeleccionado)) {
                                conductorSeleccionado = conductor;
                                break;
                            }
                        }

                        if (conductorSeleccionado == null) {
                            System.out.println("No se encontró un conductor con el transporte seleccionado.");
                            transporteSeleccionado = null;
                            continue;
                        }

                        // Confirmar el abastecimiento
                        System.out.println("========================================");
                        System.out.println("¿Desea confirmar el abastecimiento?");
                        System.out.println("(s) para sí, (v) para volver al paso anterior, cualquier otra tecla para cancelar):");
                        System.out.println("========================================");
                        String confirmar = sc.next();
                        if (confirmar.equalsIgnoreCase("v")) {
                            conductorSeleccionado= null;
                            transporteSeleccionado=null;
                            continue;
                        }   
                        if (confirmar.equalsIgnoreCase("s")) {
                            // Aplicar los cambios a la tienda oficialmente
                                                        tiendaSeleccionada.setConteoCategorias(conteoCategoriasTemporal);
                            tiendaSeleccionada.setListaProducto(tiendaSeleccionada.getListaProducto());
                            
                            try {
                                Thread.sleep(1000);
                                System.out.println("========================================");
                                System.out.println("Abastecimiento confirmado.");
                                
                                System.out.println("Enviando productos a la tienda " + tiendaSeleccionada.getNombre() + "...");
                                
                                System.out.println("========================================");
                            
                                // Cargar productos en el transporte del conductor seleccionado
                                Transporte transporte = conductorSeleccionado.getTransporte();
                                transporte.abastecerProducto(tiendaSeleccionada, productosGenerados);
                                        
                                //Cumplir con la meta del conductor y del operario
                                conductorSeleccionado.setIndiceMeta(conductorSeleccionado.getIndiceMeta() + pesoTotalProductos);
                                conductorSeleccionado.cantidadTrabajo += 1;
                                Fabrica.getOperario().setIndiceMeta(Fabrica.getOperario().getIndiceMeta() + 1);
                                Fabrica.getOperario().setCantidadTrabajo(Fabrica.getOperario().getCantidadTrabajo() + 1);
                            
                                // Descargar productos en la tienda
                                tiendaSeleccionada.descargarProducto(transporte);
                                System.out.println("PRODUCTOS DESCARGADOS EN LA TIENDA " + tiendaSeleccionada.getNombre());             
                                System.out.println("Ha seleccionado el transporte: #" + transporteSeleccionadoIndex);               
                                System.out.println("La tienda " + tiendaSeleccionada.getNombre() + " se abastecerá por: " + transporteSeleccionado.getNombre());                               
                                System.out.println("\n¡¡¡EL PRODUCTO FUE ENVIADO CON EXITO!!!. Ahora la tienda tiene:\n");                  
                                System.out.println("    PRODUCTOS:");                             
                                System.out.println(tiendaSeleccionada.cantidadProductos());
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        } else {
                            System.out.println("Abastecimiento cancelado.");
                            continue;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer

                }
            }

            // Preguntar si desea volver al menú principal o realizar otro proceso de abastecer
            System.out.println("========================================");
            System.out.println("¿Desea volver al menú principal o realizar otro proceso de abastecer alguna tienda?");
            System.out.println("1. Volver al menú principal");
            System.out.println("0. Realizar otro proceso de abastecer alguna tienda");
            System.out.println("Cualquier otro número: Salir del programa");
            System.out.println("========================================");
            try {
                int opcion = sc.nextInt();
                if (opcion == 1) {
                    System.out.println("========================================");
                    System.out.println("ABASTECIMIENTO FINALIZADO");
                    System.out.println("Volviendo al menú principal...");
                    salir = true;
                } else if (opcion == 0) {
                    System.out.println("========================================");
                    System.out.println("Realizando otro proceso de abastecer alguna tienda...");
                    System.out.println("========================================");

                    // Continuar con el bucle para realizar otro proceso de abastecer
                } else {
                    System.out.println("Opción inválida. Volviendo al menú principal...");
                    salir = true;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Volviendo al menú principal...");
                salir = true;
                sc.nextLine(); // Limpiar el buffer
            }
        }
    }

    public static void pagoTrabajadores() {
        // Implementar la funcionalidad de pago de trabajadores
        uiMain.uiPagoTrabajadores.pagarTrabajadores();
    }
        //FUNCIONALIDAD ESTADISTICAS
        public static void estadisticas() {
        bienvenida();
        asignarFecha();
        mostrar();
    }

    public static void bienvenida() {
        System.out.println("=== Bienvenido al módulo de estadísticas ===");
    }

    public static void asignarFecha() {
        // Convertir la fecha a String con el nuevo formato
        String fechaMinFormateada = Factura.getFechaMin().format(formato);
        String fechaMaxFormateada = Factura.getFechaMax().format(formato);

        System.out.println("La fecha mínima es: " + fechaMinFormateada);
        System.out.println("La fecha máxima es: " + fechaMaxFormateada);
        System.out.println("Usar fechas por defecto (las mostradas anteriormente)? (s/n): ");

        String respuesta = sc.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {
            fechaInicio = Factura.getFechaMin();
            fechaFin = Factura.getFechaMax();
            return;
            
        } else if (respuesta.equalsIgnoreCase("n")) {
            while (true) {
                System.out.println("Ingrese la fecha de inicio (dd/mm/yyyy): ");
                String SFechaInicio = sc.nextLine();
                fechaInicio = Factura.convertirStrADate(SFechaInicio);
                try {
                    if (SFechaInicio.equals("0")) {
                        System.out.println("Saliendo...");
                        break;
                    } else if (fechaInicio.isBefore(Factura.getFechaMin()) || fechaInicio.isAfter(Factura.getFechaMax()) || fechaInicio.isAfter(fechaFin)) {
                        System.out.println("Fecha inválida. Intente de nuevo.");
                    } else {
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Intente de nuevo.");
                }
            }
            
            while (true) {
                System.out.println("Ingrese la fecha de fin (dd/mm/yyyy): ");
                String SFechaFin = sc.nextLine();
                fechaFin = Factura.convertirStrADate(SFechaFin);
                try {
                    if (SFechaFin.equals("0")) {
                        System.out.println("Saliendo...");
                        break;
                    } else if (fechaFin.isBefore(Factura.getFechaMin()) || fechaFin.isAfter(Factura.getFechaMax()) || fechaFin.isBefore(fechaInicio)) {
                        System.out.println("Fecha inválida. Intente de nuevo.");
                    } else {
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Formato inválido. Intente de nuevo.");
                }
            }
        } else {
            System.out.println("Respuesta no válida. Intente de nuevo.");
            asignarFecha();
        }
    }

    private static void mostrarMenu() {
        System.out.println("=== Opciones ===");
        System.out.println("1. Mostrar ganancias discretas");
        System.out.println("2. Mostrar ganancias totales");
        System.out.println("3. Mostrar promedio de ganancias por día");
        System.out.println("4. Mostrar aumento porcentual de ganancias");
        System.out.println("5. Mostrar moda de productos, tiendas y clientes");
        if (num > 0) {
            System.out.println("6. Cambiar fechas");
        }
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        num++;
    }

    public static void mostrar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.println("Las ganancias discretas son: ");
                    for (Object g : Factura.gananciasDiscretas(fechaInicio, fechaFin)) {
                        ArrayList<Object> ganancias = (ArrayList<Object>) g;
                        LocalDate f = (LocalDate) ganancias.get(0);
                        String fecha = f.format(formato);
                        System.out.println("Fecha: " + fecha + " Ganancia: " + ganancias.get(1));
                    }
                    break;
                case 2:
                    System.out.println("La ganancia total es:");
                    System.out.println(Factura.gananciasTotales(fechaInicio, fechaFin));
                    break;
                case 3:
                    System.out.println("El promedio es: ");
                    System.out.println(Factura.promedioGanancias(fechaInicio, fechaFin));
                    break;
                case 4:
                    System.out.println("El aumento porcentual es: ");
                    System.out.println(Factura.aumentoPorcentual(fechaInicio, fechaFin));
                    break;
                case 5:
                    System.out.println("El producto más vendido es: ");
                    System.out.println(Factura.modaProductos(fechaInicio, fechaFin));
                    System.out.println("La tienda que más vendió es: ");
                    System.out.println(Factura.modaTiendas(fechaInicio, fechaFin));
                    System.out.println("El cliente que más compró es: ");
                    System.out.println(Factura.modaClientes(fechaInicio, fechaFin));
                    break;
                case 6:
                    asignarFecha();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }

            while (opcion != 0) {
                System.out.println("Desea realizar otra operación? (s/n): ");
                String respuesta = sc.nextLine().toLowerCase();
                if (respuesta.equals("s")) {
                    break;
                } else if (respuesta.equals("n")) {
                    System.out.println("Saliendo...");
                    opcion = 0;
                    break;
                } else {
                    System.out.println("Respuesta no válida. Intente de nuevo.");
                }
            }
        } while (opcion != 0);
    }
}

