package uiMain;

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
    //cada quien ingrese su funcionalidad muchachos...
    public static void enviarPedidos() {
        // Implementar la funcionalidad de enviar pedidos
    }

    public static void devoluciones() {
        // Implementar la funcionalidad de devoluciones
    }

    public static void pagoTrabajadores() {
        // Implementar la funcionalidad de pago de trabajadores
    }

    public static void estadisticas() {
        // Implementar la funcionalidad de estadísticas
<<<<<<< HEAD
=======
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
    static Tienda tienda2 = new Tienda("Consumibles de la Abuela Tata", vendedor2, cuentaFabrica,1000,100,100);
    static Tienda tienda3 = new Tienda("Miss Músculo Aseo", vendedor3, cuentaFabrica, 1000, 1000,1000);

    // Crear productos para cada tienda
    static Producto producto1 = new Producto("Cemento", 50, 100, estadosProducto.DISPONIBLE, "Material", "Construcción", 25.0);
    static Producto producto2 = new Producto("Madera", 30, 50, estadosProducto.DISPONIBLE, "Material", "Construcción", 20.0);
    static Producto producto3 = new Producto("Adhesivo", 20, 200, estadosProducto.DISPONIBLE, "Material", "Construcción", 5.0);

    static Producto producto4 = new Producto("Pan", 10, 150, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);
    static Producto producto5 = new Producto("Leche", 8, 100, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 2.0);
    static Producto producto6 = new Producto("Arroz", 5, 300, estadosProducto.DISPONIBLE, "Consumible", "Alimentos", 1.0);

    static Producto producto7 = new Producto("Detergente", 15, 120, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 3.0);
    static Producto producto8 = new Producto("Esponja", 5, 200, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 0.5);
    static Producto producto9 = new Producto("Limpiador", 12, 150, estadosProducto.DISPONIBLE, "Limpieza", "Hogar", 2.0);

    // Inicializar y añadir productos a las tiendas
    static ArrayList<Producto> listaProductosTienda1 = new ArrayList<>(Arrays.asList(producto1, producto2, producto3));
    static ArrayList<Producto> listaProductosTienda2 = new ArrayList<>(Arrays.asList(producto4, producto5, producto6));
    static ArrayList<Producto> listaProductosTienda3 = new ArrayList<>(Arrays.asList(producto7, producto8, producto9));



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

    {
        operario1.setFabrica(fabrica);
    }
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
        

    //CORREGIRRRR:
    static {
        // Mostrar resumen de inicialización
        System.out.println("Sistema inicializado con los siguientes datos:");
        System.out.println("Fábrica: " + fabrica.getNombre());
        System.out.println("Tiendas:" + Fabrica.mostrarTiendas());
        //listaTiendas.forEach(System.out::println);
        //System.out.println("\nProductos en la fábrica: ");innecesario
        //productosFabrica.forEach(System.out::println);
>>>>>>> 9bf949d63e05a458795bb860ce33ef48d34d8d7f
    }
    public static void abastecerTiendas() {
        // Implementar la funcionalidad de abastecer tiendas
}

        //Método main, desde el que se va a ejecutar el programa principal, de aca se muestra el menu 
        //con las 5 funcionalidades, se hace el switch y se llama a los metodos de cada funcionalidad 
        //depeniendo de la que se elija.  

}

