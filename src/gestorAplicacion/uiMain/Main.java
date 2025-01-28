package uiMain;
import gestion.*;
import produccion.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import gestion.Factura;

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
            System.out.println("¡Bienvenido al sistema de gestión!");
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

    public static void enviarPedidos() {
        uiMain.uiEnviarPedidos.enviar();
    }

    public static void devoluciones() {
        // Implementar la funcionalidad de devoluciones
        uiMain.uiDevoluciones.devolver();
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
