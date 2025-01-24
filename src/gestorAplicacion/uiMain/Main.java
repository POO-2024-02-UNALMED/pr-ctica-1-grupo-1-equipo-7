package uiMain;
import gestion.*;
import produccion.*;
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
    static Tienda tienda1 = new Tienda("Hefesto Construcciones", vendedor1, cuentaFabrica);
    static Tienda tienda2 = new Tienda("Consumibles de la Abuela Tata", vendedor2, cuentaFabrica);
    static Tienda tienda3 = new Tienda("Miss Músculo Aseo", vendedor3, cuentaFabrica);

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
    static Fabrica fabrica = new Fabrica("F001", "Fábrica Principal", "Calle Principal 123", cuentaFabrica, productosFabrica, listaTiendas);

    static {
        operario1.setFabrica(fabrica);
    }

    static {
        // Mostrar resumen de inicialización
        System.out.println("Sistema inicializado con los siguientes datos:");
        System.out.println("Fábrica: " + fabrica);
        System.out.println("Tiendas: ");
        listaTiendas.forEach(System.out::println);
        System.out.println("\nProductos en la fábrica: ");
        productosFabrica.forEach(System.out::println);
    }
}

