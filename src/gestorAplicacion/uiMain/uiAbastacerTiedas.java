package uiMain;
import java.util.Scanner;
import produccion.Fabrica;
import produccion.Tienda;
import produccion.Producto;
import produccion.TipoTransporte;
import produccion.Transporte;
import gestion.Conductor;

import java.util.ArrayList;
import java.util.List;

public class uiAbastacerTiedas {
    public static void abastecer() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Eligió la opción de abastecer tienda.\nSeleccione la Tienda que desea abastecer. Oprima 0 para salir.");
            System.out.println(" 0. Salir");
            Fabrica.mostrarTiendas();
            int tiendaSeleccionadaIndex = -1;
            Tienda tiendaSeleccionada = null;

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

            // Lista para almacenar los productos seleccionados y sus cantidades
            ArrayList<Producto> productosGenerados = new ArrayList<>();
            // Lista temporal para almacenar los cambios en la cantidad de productos por categoría
            List<Integer> conteoCategoriasTemporal = new ArrayList<>(tiendaSeleccionada.getConteoCategorias());

            // Bucle para añadir productos al abastecimiento
            int pesoTotalProductos = 0;
            while (true) {
                // Selección de producto
                System.out.println("Seleccione el producto que desea mandar a la tienda. Oprima 0 para salir.");
                System.out.println(" 0. Salir");
                Fabrica.mostrarProductos();
                int productoSeleccionadoIndex = -1;
                Producto productoSeleccionado = null;

                // Bucle para seleccionar el producto
                while (productoSeleccionadoIndex < 0 || productoSeleccionadoIndex > Fabrica.getProductosDisponibles().size()) {
                    try {
                        productoSeleccionadoIndex = sc.nextInt();
                        if (productoSeleccionadoIndex == 0) {
                            System.out.println("Saliendo del programa...");
                            sc.close();
                            return;
                        }
                        if (productoSeleccionadoIndex < 1 || productoSeleccionadoIndex > Fabrica.getProductosDisponibles().size()) {
                            System.out.println("Número inválido. Por favor, ingrese un número entre 1 y " + Fabrica.getProductosDisponibles().size() + ".");
                        } else {
                            productoSeleccionado = Fabrica.getProductosDisponibles().get(productoSeleccionadoIndex - 1);
                            System.out.println("Producto seleccionado: " + productoSeleccionado.getNombre());

                            // Selección de cantidad de productos a enviar
                            String categoriaProducto = productoSeleccionado.getCategoria();
                            int indexCategoria = tiendaSeleccionada.getCategorias().indexOf(categoriaProducto);
                            int cantidadActual = conteoCategoriasTemporal.get(indexCategoria);
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
                                        
                                        // Generar los productos y agregarlos a la lista
                                        productosGenerados.addAll(Fabrica.cantidadProductos(productoSeleccionado, cantidadAEnviar));

                                        // Calcular el peso total de los productos seleccionados
                                        pesoTotalProductos += productoSeleccionado.getPeso() * cantidadAEnviar;

                                        // Actualizar la cantidad de productos en la lista temporal
                                        conteoCategoriasTemporal.set(indexCategoria, cantidadActual + cantidadAEnviar);
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
                System.out.println(tiendaSeleccionada.productosPorCategoria(tiendaSeleccionada.getListaProducto(), conteoCategoriasTemporal));

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
                            continue;
                        }

                        // Confirmar el abastecimiento
                        System.out.println("¿Desea confirmar el abastecimiento? (s/n)");
                        String confirmar = sc.next();
                        if (confirmar.equalsIgnoreCase("s")) {
                            // Aplicar los cambios a la tienda
                            tiendaSeleccionada.setConteoCategorias(conteoCategoriasTemporal);

                            // Cargar productos en el transporte del conductor seleccionado
                            Transporte transporte = conductorSeleccionado.getTransporte();
                            transporte.abastecerProducto(tiendaSeleccionada, productosGenerados);

                            // Descargar productos en la tienda
                            tiendaSeleccionada.descargarProducto(transporte);
                            System.out.println("Productos descargados en la tienda " + tiendaSeleccionada.getNombre());
                            System.out.println("Ha seleccionado el transporte:  " + transporteSeleccionadoIndex);
                            System.out.println("La tienda " + tiendaSeleccionada.getNombre() + " se abastecera por: "+ transporteSeleccionado.getNombre());
                            System.out.println("El producto fue enviado con exito ahora la tienda tiene");
                            System.out.println("       PRODUCTOS:");
                            System.out.println(tiendaSeleccionada.cantidadProductos());//se tiene que mostrar cantidadProductos
                        } else {
                            System.out.println("Abastecimiento cancelado.");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                }
            }

            // Preguntar si desea volver al menú principal o realizar otro proceso de abastecer
            System.out.println("¿Desea volver al menú principal o realizar otro proceso de abastecer alguna tienda?");
            System.out.println("1. Volver al menú principal");
            System.out.println("0. Realizar otro proceso de abastecemiento de tiendas");
            int opcion = sc.nextInt();
            if (opcion == 1) {
                System.out.println("Volviendo al menú principal...");
                break;
            } else if (opcion == 0) {
                System.out.println("Realizando otro proceso de abastecer alguna tienda...");
                // Continuar con el bucle para realizar otro proceso de abastecer
            } else {
                System.out.println("Opción inválida. Volviendo al menú principal...");
                break;
            }
        }
        sc.close();
    }
}