package ui;
import java.util.Scanner;
import produccion.Fabrica;
import produccion.Tienda;
import produccion.Producto;


public class uiAbastacerTiedas {
    public static void abastecer() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Eligió la opción de abastecer tienda.\nSeleccione la Tienda que desea abastecer. Oprima 0 para salir.");
            System.out.println(" 0. Salir");
            Fabrica.mostrarTiendas();
            int tiendaSeleccionadaIndex;
            Tienda tiendaSeleccionada;

            try {
                tiendaSeleccionadaIndex = sc.nextInt();
                if (tiendaSeleccionadaIndex == 0) {
                    System.out.println("Saliendo del programa...");
                    break;
                }
                if (tiendaSeleccionadaIndex < 1 || tiendaSeleccionadaIndex > Fabrica.getListaTiendas().size()) {
                    System.out.println("Número inválido. Por favor, ingrese un número entre 1 y " + Fabrica.getListaTiendas().size() + ".");
                    continue;
                }
                tiendaSeleccionada = Fabrica.getListaTiendas().get(tiendaSeleccionadaIndex - 1);
                System.out.println("Tienda seleccionada: " + tiendaSeleccionada.getNombre());

                // Selección de producto
                System.out.println("Seleccione el producto que desea mandar a la tienda. Oprima 0 para salir.");
                System.out.println(" 0. Salir");
                Tienda.mostrarProductos();
                int productoSeleccionadoIndex;
                Producto productoSeleccionado;

                try {
                    productoSeleccionadoIndex = sc.nextInt();
                    if (productoSeleccionadoIndex == 0) {
                        System.out.println("Saliendo del programa...");
                        break;
                    }
                    if (productoSeleccionadoIndex < 1 || productoSeleccionadoIndex > Tienda.getListaProducto().size()) {
                        System.out.println("Número inválido. Por favor, ingrese un número entre 1 y " + Tienda.getListaProducto().size() + ".");
                        continue;
                    }
                    productoSeleccionado = Tienda.getListaProducto().get(productoSeleccionadoIndex - 1);
                    System.out.println("Producto seleccionado: " + productoSeleccionado.getNombre());
                    // Aquí puedes agregar el código para abastecer la tienda seleccionada con el producto seleccionado

                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                }

            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                sc.nextLine(); // Limpiar el buffer
            }
        }

        sc.close();
    }
}