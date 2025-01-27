package uiMain;
import gestion.*;
import produccion.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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
                    abastecerTiendas();
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

    public static void abastecerTiendas() {
        // Implementar la funcionalidad de abastecer tiendas
        uiMain.uiAbastacerTiedas.abastecer();
    }

    public static void pagoTrabajadores() {
        // Implementar la funcionalidad de pago de trabajadores
        uiMain.uiPagoTrabajadores.pagarTrabajadores();
    }

    public static void estadisticas() {
        // Implementar la funcionalidad de estadísticas
        uiMain.uiEstadistica.bienvenida();
        uiMain.uiEstadistica.mostrar();
    }

    // Crear cuentas bancarias
    static CuentaBancaria cuentaFabrica = new CuentaBancaria(9999999, 1000000000);
    static CuentaBancaria cuentaVendedor1 = new CuentaBancaria(56932, 100);
    static CuentaBancaria cuentaVendedor2 = new CuentaBancaria(45728, 200);
    static CuentaBancaria cuentaVendedor3 = new CuentaBancaria(95687, 200);

    // Crear vendedores
    static Vendedor vendedor1 = new Vendedor("Maria Beatriz", 57793, 20, cuentaVendedor1);
    static Vendedor vendedor2 = new Vendedor("Adriana Alexia Putellas", 89235, 21, cuentaVendedor2);
    static Vendedor vendedor3 = new Vendedor("Lionel Andres Messi", 14720, 22, cuentaVendedor3);

    // Crear tiendas
    static Tienda tienda1 = new Tienda("Hefesto Construcciones", vendedor1, cuentaFabrica,100,100,100);
    static Tienda tienda2 = new Tienda("Consumibles de la Abuela Tata", vendedor2, cuentaFabrica,100,100,100);
    static Tienda tienda3 = new Tienda("Miss Músculo Aseo", vendedor3, cuentaFabrica, 100, 100,100);

    // Crear productos para cada tienda
   
    //tienda 1
    static Producto producto1 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Construcción", 25.0);
    static Producto producto2 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Construcción", 25.0);
    static Producto producto3 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Construcción", 25.0);
    static Producto producto4 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Construcción", 25.0);
    static Producto producto5 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Construcción", 25.0);
    
    static Producto producto6 = new Producto("Cemento Blanco", 55000, estadosProducto.DISPONIBLE, "Material", "Construcción", 25.0);
    static Producto producto7 = new Producto("Cemento Blanco", 55000, estadosProducto.DISPONIBLE, "Material", "Construcción", 25.0);
    
    static Producto producto8 = new Producto("Adhesivo Cerámico", 20000, estadosProducto.DISPONIBLE, "Material", "Construcción", 5.0);
    static Producto producto9 = new Producto("Adhesivo Cerámico", 20000, estadosProducto.DISPONIBLE, "Material", "Construcción", 5.0);
    
    static Producto producto10 = new Producto("Pintura Interior", 35000, estadosProducto.DISPONIBLE, "Material", "Construcción", 18.0);
    static Producto producto11 = new Producto("Pintura Interior", 35000, estadosProducto.DISPONIBLE, "Material", "Construcción", 18.0);
    
    //tienda 2
    static Producto producto12 = new Producto("Pan", 10000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);
    static Producto producto13 = new Producto("Pan", 10000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);
    static Producto producto14 = new Producto("Pan", 10000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);

    static Producto producto15 = new Producto("Leche Entera", 8000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 2.0);
    static Producto producto16 = new Producto("Leche Entera", 8000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 2.0);
    static Producto producto17 = new Producto("Leche Entera", 8000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 2.0);

    static Producto producto18 = new Producto("Arroz", 5000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);
    static Producto producto19 = new Producto("Arroz", 5000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);
    static Producto producto20 = new Producto("Arroz", 5000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);

    static Producto producto21 = new Producto("Galletas", 12000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 0.5);
    static Producto producto22 = new Producto("Galletas", 12000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 0.5);
    static Producto producto23 = new Producto("Galletas", 12000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 0.5);

    static Producto producto24 = new Producto("Mantequilla", 7000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 0.25);
    static Producto producto25 = new Producto("Mantequilla", 7000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 0.25);
    static Producto producto26 = new Producto("Mantequilla", 7000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 0.25);

    static Producto producto27 = new Producto("Queso", 15000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);
    static Producto producto28 = new Producto("Queso", 15000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);
    static Producto producto29 = new Producto("Queso", 15000, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);

    //tienda 3
    static Producto producto30 = new Producto("Detergente", 15000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 3.0);
    static Producto producto31 = new Producto("Detergente", 15000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 3.0);
    static Producto producto32 = new Producto("Detergente", 15000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 3.0);

    static Producto producto33 = new Producto("Esponja", 5000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 0.5);
    static Producto producto34 = new Producto("Esponja", 5000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 0.5);
    static Producto producto35 = new Producto("Esponja", 5000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 0.5);

    static Producto producto36 = new Producto("Limpiador", 12000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 2.0);
    static Producto producto37 = new Producto("Limpiador", 12000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 2.0);
    static Producto producto38 = new Producto("Limpiador", 12000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 2.0);

    static Producto producto39 = new Producto("Jabón Líquido", 10000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 1.5);
    static Producto producto40 = new Producto("Jabón Líquido", 10000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 1.5);
    static Producto producto41 = new Producto("Jabón Líquido", 10000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 1.5);

    static Producto producto42 = new Producto("Trapeador", 25000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 0.8);
    static Producto producto43 = new Producto("Trapeador", 25000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 0.8);
    static Producto producto44 = new Producto("Trapeador", 25000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 0.8);

    static Producto producto45 = new Producto("Cloro", 8000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 2.0);
    static Producto producto46 = new Producto("Cloro", 8000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 2.0);
    static Producto producto47 = new Producto("Cloro", 8000, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 2.0);

    static ArrayList<Producto> listaProductosTienda1 = new ArrayList<>(Arrays.asList(
        producto1, producto2, producto3, producto4, producto5,  // Cemento Gris
        producto6, producto7,                                 // Cemento Blanco
        producto8, producto9,                                 // Adhesivo Cerámico
        producto10, producto11                                // Pintura Interior
));
    static ArrayList<Producto> listaProductosTienda2 = new ArrayList<>(Arrays.asList(
        producto12, producto13, producto14, // Pan
        producto15, producto16, producto17, // Leche Entera
        producto18, producto19, producto20, // Arroz
        producto21, producto22, producto23, // Galletas
        producto24, producto25, producto26, // Mantequilla
        producto27, producto28, producto29  // Queso
    ));
    static ArrayList<Producto> listaProductosTienda3 = new ArrayList<>(Arrays.asList(
        producto30, producto31, producto32, // Detergente
        producto33, producto34, producto35, // Esponja
        producto36, producto37, producto38, // Limpiador
        producto39, producto40, producto41, // Jabón Líquido
        producto42, producto43, producto44, // Trapeador
        producto45, producto46, producto47  // Cloro
));



    static {
        tienda1.getListaProducto().addAll(listaProductosTienda1);
        tienda2.getListaProducto().addAll(listaProductosTienda2);
        tienda3.getListaProducto().addAll(listaProductosTienda3);
    }

    // Crear lista de tiendas para la fábrica
    static ArrayList<Tienda> listaTiendas = new ArrayList<>(Arrays.asList(tienda1, tienda2, tienda3));

    // Crear lista de productos disponibles para la fábrica
    static ArrayList<Producto> productosFabrica = new ArrayList<>(Arrays.asList(producto1, producto2, producto3, producto4, producto5, producto6, producto7, producto8, producto9));

    // Crear operario
    static CuentaBancaria cuentaOperario = new CuentaBancaria(55555, 100000);
    static Operario operario1 = new Operario("Jaime", 97890, 20, cuentaOperario, null);

    // Crear fábrica
    static Fabrica fabrica = new Fabrica("F001", "Fábrica Principal", "Calle Principal 123", cuentaFabrica, productosFabrica, listaTiendas,operario1);

        // Crear cuentas bancarias para los conductores
        static CuentaBancaria cuentaConductor1 = new CuentaBancaria(12345, 5000);
        static CuentaBancaria cuentaConductor2 = new CuentaBancaria(23456, 6000);
        static CuentaBancaria cuentaConductor3 = new CuentaBancaria(34567, 7000);
        static CuentaBancaria cuentaConductor4 = new CuentaBancaria(45678, 8000);
        static CuentaBancaria cuentaConductor5 = new CuentaBancaria(56789, 9000);
        static CuentaBancaria cuentaConductor6 = new CuentaBancaria(67890, 10000);
        static CuentaBancaria cuentaConductor7 = new CuentaBancaria(78901, 11000);
        static CuentaBancaria cuentaConductor8 = new CuentaBancaria(89012, 12000);
        static CuentaBancaria cuentaConductor9 = new CuentaBancaria(90123, 13000);
        static CuentaBancaria cuentaConductor10 = new CuentaBancaria(123456, 14000);

    // Crear transportes
        static Transporte transporte1 = new Transporte(TipoTransporte.CAMION, 15000, 16329);
        static Transporte transporte2 = new Transporte(TipoTransporte.AVION, 30000, 64000);
        static Transporte transporte3 = new Transporte(TipoTransporte.AUTOMOVIL, 9000, 500);
        static Transporte transporte4 = new Transporte(TipoTransporte.CAMIONETA, 12000, 650);
        static Transporte transporte5 = new Transporte(TipoTransporte.BICICLETA, 5000, 35);
        static Transporte transporte6 = new Transporte(TipoTransporte.PATINES, 3000, 20);
        static Transporte transporte7 = new Transporte(TipoTransporte.BARCO, 20000, 3356835);
        static Transporte transporte8 = new Transporte(TipoTransporte.HELICOPTERO, 70000, 29000);
        static Transporte transporte9 = new Transporte(TipoTransporte.TREN, 20000, 30000);
        static Transporte transporte10 = new Transporte(TipoTransporte.CAMINANDO, 5000, 15);

    // Crear conductores
        static Conductor conductor1 = new Conductor("Conductor 1", 11111, 30, cuentaConductor1, fabrica, transporte1);
        static Conductor conductor2 = new Conductor("Conductor 2", 22222, 31, cuentaConductor2, fabrica, transporte2);
        static Conductor conductor3 = new Conductor("Conductor 3", 33333, 32, cuentaConductor3, fabrica, transporte3);
        static Conductor conductor4 = new Conductor("Conductor 4", 44444, 33, cuentaConductor4, fabrica, transporte4);
        static Conductor conductor5 = new Conductor("Conductor 5", 55555, 34, cuentaConductor5, fabrica, transporte5);
        static Conductor conductor6 = new Conductor("Conductor 6", 66666, 35, cuentaConductor6, fabrica, transporte6);
        static Conductor conductor7 = new Conductor("Conductor 7", 77777, 36, cuentaConductor7, fabrica, transporte7);
        static Conductor conductor8 = new Conductor("Conductor 8", 88888, 37, cuentaConductor8, fabrica, transporte8);
        static Conductor conductor9 = new Conductor("Conductor 9", 99999, 38, cuentaConductor9, fabrica, transporte9);
        static Conductor conductor10 = new Conductor("Conductor 10", 101010, 39, cuentaConductor10, fabrica, transporte10);

        // Crear lista de conductores
        static ArrayList<Conductor> listaConductores = new ArrayList<>();
        static {
            listaConductores.add(conductor1);
            listaConductores.add(conductor2);
            listaConductores.add(conductor3);
            listaConductores.add(conductor4);
            listaConductores.add(conductor5);
            listaConductores.add(conductor6);
            listaConductores.add(conductor7);
            listaConductores.add(conductor8);
            listaConductores.add(conductor9);
            listaConductores.add(conductor10);
        }
    // Instancias estáticas de las cuentas bancarias
    public static CuentaBancaria cuentaCliente1 = new CuentaBancaria(10001, 5000);
    public static CuentaBancaria cuentaCliente2 = new CuentaBancaria(10002, 15000);
    public static CuentaBancaria cuentaCliente3 = new CuentaBancaria(10003, 8000);
    public static CuentaBancaria cuentaCliente4 = new CuentaBancaria(10004, 2000);
    public static CuentaBancaria cuentaCliente5 = new CuentaBancaria(10005, 12000);

    // Instancias estáticas de los clientes
    public static Cliente cliente1 = new Cliente("Juan Pérez", 30, 987654321, cuentaCliente1);
    public static Cliente cliente2 = new Cliente("María López", 25, 123456789, cuentaCliente2);
    public static Cliente cliente3 = new Cliente("Carlos García", 40, 567890123, cuentaCliente3);
    public static Cliente cliente4 = new Cliente("Ana Rodríguez", 35, 654321987, cuentaCliente4);
    public static Cliente cliente5 = new Cliente("Luis Fernández", 28, 192837465, cuentaCliente5);


    //Instancias estáticas de las metas para operario
    public static Meta metaOperario1 = new Meta("Facil",5, 10000);
    public static Meta metaOperario2 = new Meta("Normal", 10, 17000);
    public static Meta metaOperario3 = new Meta("Dificil", 15, 25000);
    public static Meta metaOperario4 = new Meta("Muy Dificil", 20, 35000);

    //Instancias estáticas de las metas para Vendedor
    public static Meta metaVendedor1 = new Meta("Facil",5, 9000);
    public static Meta metaVendedor2= new Meta("Normal", 10, 15000);
    public static Meta metaVendedor3 = new Meta("Dificil", 15, 22000);
    public static Meta metaVendedor4 = new Meta("Muy Dificil", 20, 30000);

    //Instancias estáticas de las metas para operario
    public static Meta metaConductor1 = new Meta("Facil",25, 8000);
    public static Meta metaConductor2= new Meta("Normal", 40, 13500);
    public static Meta metaConductor3 = new Meta("Dificil", 55, 21000);
    public static Meta metaConductor4 = new Meta("Muy Dificil", 70, 28500);
    //Factura
    static LocalDate fecha = LocalDate.of(2024,10,2);
    static Factura f1=new Factura(tienda1, cliente1, transporte1, listaProductosTienda1, fecha);

    static{
        operario1.setMetaOperario(metaOperario1);
        operario1.setMetaOperario(metaOperario2);
        operario1.setMetaOperario(metaOperario3);
        operario1.setMetaOperario(metaOperario4);

        for(Conductor i: Conductor.getListaConductores()){
            i.setMetaConductor(metaConductor1);
            i.setMetaConductor(metaConductor2);
            i.setMetaConductor(metaConductor3);
            i.setMetaConductor(metaConductor4);
        }

        for(Vendedor i : Vendedor.getListaVendedores()){
            i.setMetaVendedor(metaVendedor1);
            i.setMetaVendedor(metaVendedor2);
            i.setMetaVendedor(metaVendedor3);
            i.setMetaVendedor(metaVendedor4);
        }
    }
}
