package uiMain;
import java.util.ArrayList;
import java.util.Scanner;
import produccion.Fabrica;
import produccion.Tienda;
import produccion.Producto;
import produccion.TipoTransporte;

public class uiAbastacerTiedas {
    public static void abastecer() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Eligió la opción de abastecer tienda.\nSeleccione la Tienda que desea abastecer. Oprima 0 para salir.");
            System.out.println(" 0. Salir");
            System.out.println(Fabrica.mostrarTiendas());
            int tiendaSeleccionadaIndex = -1;
            Tienda tiendaSeleccionada = null;
//Duda: una cosa es la lista de productos que tiene la tienda y otra cosa es la lista de productos que tiene la fabrica(para abastecer((linea 18 clase fabrica))).
            // Bucle para seleccionar la tienda
            while (tiendaSeleccionadaIndex < 0 || tiendaSeleccionadaIndex > Fabrica.getListaTienda().size()) {
                try {
                    tiendaSeleccionadaIndex = sc.nextInt();
                    if (tiendaSeleccionadaIndex == 0) {
                        System.out.println("Saliendo del programa...");
                        sc.close();
                        return;
                    }
                    if (tiendaSeleccionadaIndex < 1 || tiendaSeleccionadaIndex > Fabrica.getListaTienda().size()) {
                        System.out.println("Número inválido. Por favor, ingrese un número entre 1 y " + Fabrica.getListaTienda().size() + ".");
                    } else {
                        tiendaSeleccionada = Fabrica.getListaTienda().get(tiendaSeleccionadaIndex - 1);
                        System.out.println("Tienda seleccionada: " + tiendaSeleccionada.getNombre());
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                }
            }

            // Mostrar productos por categoría
            System.out.println("Productos por categoría en la tienda seleccionada:");
            System.out.println(tiendaSeleccionada.productosPorCategoria(tiendaSeleccionada.getListaProducto()));

            // Bucle para añadir productos al abastecimiento
            int pesoTotalProductos = 0;
            while (true) {
                // Selección de producto
                System.out.println("Seleccione el producto que desea mandar a la tienda. Oprima 0 para salir.");
                System.out.println(" 0. Salir");
                tiendaSeleccionada.mostrarProductos();
                int productoSeleccionadoIndex = -1;
                Producto productoSeleccionado = null;

                // Bucle para seleccionar el producto
                while (productoSeleccionadoIndex < 0 || productoSeleccionadoIndex > tiendaSeleccionada.getListaProducto().size()) {
                    try {
                        productoSeleccionadoIndex = sc.nextInt();
                        if (productoSeleccionadoIndex == 0) {
                            System.out.println("Saliendo del programa...");
                            sc.close();
                            return;
                        }
                        if (productoSeleccionadoIndex < 1 || productoSeleccionadoIndex > tiendaSeleccionada.getListaProducto().size()) {
                            System.out.println("Número inválido. Por favor, ingrese un número entre 1 y " + tiendaSeleccionada.getListaProducto().size() + ".");
                        } else {
                            productoSeleccionado = tiendaSeleccionada.getListaProducto().get(productoSeleccionadoIndex - 1);
                            System.out.println("Producto seleccionado: " + productoSeleccionado.getNombre());

                            // Selección de cantidad de productos a enviar
                            String categoriaProducto = productoSeleccionado.getCategoria();
                            int indexCategoria = tiendaSeleccionada.getCategorias().indexOf(categoriaProducto);
                            int cantidadActual = tiendaSeleccionada.getConteoCategorias().get(indexCategoria);
                            int cantidadMaxima = tiendaSeleccionada.getCantidadMaximaPorCategoria();
                            int cantidadDisponible = cantidadMaxima - cantidadActual;

                            System.out.println("Cantidad máxima de productos que se pueden enviar en la categoría " + categoriaProducto + ": " + cantidadDisponible);
                            System.out.println("Ingrese la cantidad de productos a enviar:");

                            int cantidadAEnviar = -1;
                            while (cantidadAEnviar < 0 || cantidadAEnviar > cantidadDisponible) {
                                try {
                                    cantidadAEnviar = sc.nextInt();
                                    if (cantidadAEnviar < 0 || cantidadAEnviar > cantidadDisponible) {
                                        System.out.println("Cantidad inválida. Por favor, ingrese un número entre 0 y " + cantidadDisponible + ".");
                                    } else {
                                        System.out.println("Enviando " + cantidadAEnviar + " productos de la categoría " + categoriaProducto + " a la tienda " + tiendaSeleccionada.getNombre());
                                        // Aquí puedes agregar el código para actualizar la cantidad de productos en la tienda

                                        // Calcular el peso total de los productos seleccionados
                                        pesoTotalProductos += productoSeleccionado.getPeso() * cantidadAEnviar;
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

                // Preguntar si desea añadir más productos
                System.out.println("¿Desea añadir más productos a la tienda? (s/n)");
                String respuesta = sc.next();
                if (!respuesta.equalsIgnoreCase("s")) {
                    break;
                }
            }

            // Crear lista de transportes según el peso total de los productos
            ArrayList<TipoTransporte> listaTransportes = TipoTransporte.crearTipoTransporteSegunCarga(pesoTotalProductos);
            System.out.println(TipoTransporte.mostrarTipoTransporteSegunCarga(listaTransportes));

            // Selección de transporte
            System.out.println("Seleccione el tipo de transporte para enviar los productos. Oprima 0 para salir.");
            int transporteSeleccionadoIndex = -1;
            TipoTransporte transporteSeleccionado = null;

            while (transporteSeleccionadoIndex < 0 || transporteSeleccionadoIndex > listaTransportes.size()) {
                try {
                    transporteSeleccionadoIndex = sc.nextInt();
                    if (transporteSeleccionadoIndex == 0) {
                        System.out.println("Saliendo del programa...");
                        sc.close();
                        return;
                    }
                    if (transporteSeleccionadoIndex < 1 || transporteSeleccionadoIndex > listaTransportes.size()) {
                        System.out.println("Número inválido. Por favor, ingrese un número entre 1 y " + listaTransportes.size() + ".");
                    } else {
                        transporteSeleccionado = TipoTransporte.seleccionarTransporte(listaTransportes, transporteSeleccionadoIndex);
                        System.out.println("Transporte seleccionado: " + transporteSeleccionado.getNombre());
                        // Aquí puedes agregar el código para procesar el envío con el transporte seleccionado
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                }
            }
        }
    }
}