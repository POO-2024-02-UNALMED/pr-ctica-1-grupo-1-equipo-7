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
        boolean salir = false;
        

        while (!salir) {
            System.out.println("========================================");
            System.out.println("¡Bienvenido a la opción de abastecer tienda!");
            System.out.println("Seleccione la tienda que desea abastecer (0 para salir):");
            System.out.println("========================================");
            System.out.println(" 0. Salir");
            System.out.println(Fabrica.mostrarTiendas());
            System.out.println("========================================");


            int tiendaSeleccionadaIndex = -1;
            Tienda tiendaSeleccionada = null;
//salir es para regresar al menu principal y el otro(return) es para romper el bucle.(truque escrii mal). deberia implementarse la opcion de volver al menu principal o realizar el abastecimientod desde cero
            // Bucle para seleccionar la tienda
            while (tiendaSeleccionadaIndex < 0 || tiendaSeleccionadaIndex > Fabrica.getListaTienda().size()) {
                try {
                    tiendaSeleccionadaIndex = sc.nextInt();
                    if (tiendaSeleccionadaIndex == 0) {
                        System.out.println("Saliendo...");
                        sc.close();
                        return;
                    }
                    if (tiendaSeleccionadaIndex < 1 || tiendaSeleccionadaIndex > Fabrica.getListaTienda().size()) {
                        System.out.println("Número inválido. Ingrese un número entre 1 y " + Fabrica.getListaTienda().size() + ".");
                    } else {
                        tiendaSeleccionada = Fabrica.getListaTienda().get(tiendaSeleccionadaIndex - 1);
                        System.out.println("Tienda seleccionada: " + tiendaSeleccionada.getNombre());

                        // Preguntar si desea regresar o continuar
                        try {
                            System.out.println("========================================");
                            System.out.println("¿Desea volver al paso anterior?");
                            System.out.println("Escriba la letra (v) para volver o cualquier otra tecla para continuar.");
                            System.out.println("========================================");
                            String respuestaTienda = sc.next();
                            if (respuestaTienda.equalsIgnoreCase("v")) {
                                tiendaSeleccionada = null;
                                tiendaSeleccionadaIndex = -1;
                                continue;
                            }
                        } catch (Exception e) {
                            System.out.println("Entrada inválida. Por favor, ingrese una letra.");
                            sc.nextLine(); // Limpiar el buffer
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Entrada inválida. Por favor, ingrese un número.");
                    sc.nextLine(); // Limpiar el buffer
                }
            }

            if (salir) break; // Esta línea se utiliza para verificar si salir se ha establecido en true en cualquier punto del bucle.

            // Mostrar productos por categoría
            System.out.println("========================================");
            System.out.println("Productos por categoría en la tienda seleccionada:");
            System.out.println(tiendaSeleccionada.productosPorCategoria(tiendaSeleccionada.getListaProducto()));
            System.out.println("========================================");
            
            // Lista para almacenar los productos seleccionados y sus cantidades
            ArrayList<Producto> productosGenerados = new ArrayList<>();
            // Lista temporal para almacenar los cambios en la cantidad de productos por categoría
            List<Integer> conteoCategoriasTemporal = new ArrayList<>(tiendaSeleccionada.getConteoCategorias());

            // Bucle para añadir productos al abastecimiento
            int pesoTotalProductos = 0;
            while (true) {
                // Selección de producto
                System.out.println("========================================");
                System.out.println("Seleccione el producto que desea enviar a la tienda (0 para salir):");
                System.out.println("========================================");
                System.out.println(" 0. Salir");
                System.out.println(Fabrica.mostrarProductos());
                System.out.println("========================================");
                int productoSeleccionadoIndex = -1;
                Producto productoSeleccionado = null;

                // Bucle para seleccionar el producto
                while (productoSeleccionadoIndex < 0 || productoSeleccionadoIndex > Fabrica.getProductosDisponibles().size()) {
                    try {
                        productoSeleccionadoIndex = sc.nextInt();
                        if (productoSeleccionadoIndex == 0) {
                            System.out.println("Saliendo...");
                            sc.close();
                            return;
                        }
                        if (productoSeleccionadoIndex < 1 || productoSeleccionadoIndex > Fabrica.getProductosDisponibles().size()) {
                            System.out.println("Número inválido. Ingrese un número entre 1 y " + Fabrica.getProductosDisponibles().size() + ".");
                        } else {
                            productoSeleccionado = Fabrica.getProductosDisponibles().get(productoSeleccionadoIndex - 1);
                            System.out.println("Producto seleccionado: " + productoSeleccionado.getNombre());

                            // Selección de cantidad de productos a enviar
                            String categoriaProducto = productoSeleccionado.getCategoria();
                            int indexCategoria = tiendaSeleccionada.getCategorias().indexOf(categoriaProducto);
                            int cantidadActual = conteoCategoriasTemporal.get(indexCategoria);
                            int cantidadMaxima = tiendaSeleccionada.getCantidadMaximaPorCategoria();
                            int cantidadDisponible = cantidadMaxima - cantidadActual;

                            System.out.println("Cantidad máxima de productos en la categoría " + categoriaProducto + ": " + cantidadDisponible);
                            System.out.println("Ingrese la cantidad de productos a enviar:");

                            int cantidadAEnviar = -1;
                            //Bucle para seleccionar cantidad de productos a enviar
                            while (cantidadAEnviar < 0 || cantidadAEnviar > cantidadDisponible) {
                                try {
                                    cantidadAEnviar = sc.nextInt();
                                    if (cantidadAEnviar < 0 || cantidadAEnviar > cantidadDisponible) {
                                        System.out.println("Cantidad inválida. Ingrese un número entre 0 y " + cantidadDisponible + ".");
                                    } else {
                                        System.out.println("Enviando " + cantidadAEnviar + " productos de la categoría " + categoriaProducto + " a la tienda " + tiendaSeleccionada.getNombre());
                                        
                                        // Generar los productos y agregarlos a la lista
                                        productosGenerados.addAll(Fabrica.cantidadProductos(productoSeleccionado, cantidadAEnviar));//aqui cantidadProductos crea nuevos prodcutos asi que no se debe hacer ya que aun no se confirma el abastecimiento

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

                if (salir) break; // Esta línea se utiliza para verificar si salir se ha establecido en true en cualquier punto del bucle.

                // Mostrar productos por categoría actualizados
                System.out.println("Productos por categoría en la tienda seleccionada:");
                System.out.println(tiendaSeleccionada.productosPorCategoria(tiendaSeleccionada.getListaProducto(), conteoCategoriasTemporal)); // Usar productosPorCategoria

                // Preguntar si desea añadir más productos
                System.out.println("========================================");
                System.out.println("¿Desea añadir más productos?");
                System.out.println("s para sí, v para volver a elegir productos, cualquier otra tecla para continuar):");
                System.out.println("========================================");
               
                try {
                    String respuesta = sc.next();
                    if (respuesta.equalsIgnoreCase("v")) {
                        productosGenerados.clear();
                        conteoCategoriasTemporal = new ArrayList<>(tiendaSeleccionada.getConteoCategorias());
                        pesoTotalProductos = 0; //si se vuelve al paso anterior se borra el peso aunque en el primer producto si se alla confirmado
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

            if (salir) break; // Esta línea se utiliza para verificar si salir se ha establecido en true en cualquier punto del bucle.
            

            // Crear lista de transportes según el peso total de los productos
            ArrayList<TipoTransporte> listaTransportes = TipoTransporte.crearTipoTransporteSegunCarga(pesoTotalProductos);
            System.out.println("========================================");
            System.out.println(TipoTransporte.mostrarTipoTransporteSegunCarga(listaTransportes));
            System.out.println("========================================");

            // Selección de transporte
            System.out.println("Seleccione el tipo de transporte para enviar los productos. (0 para salir):");
            int transporteSeleccionadoIndex = -1;
            TipoTransporte transporteSeleccionado = null;

            while (transporteSeleccionadoIndex < 0 || transporteSeleccionadoIndex > listaTransportes.size()) {
                try {
                    transporteSeleccionadoIndex = sc.nextInt();
                    if (transporteSeleccionadoIndex == 0) {
                        System.out.println("Saliendo...");
                        sc.close();
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
                            // Aplicar los cambios a la tienda
                            tiendaSeleccionada.setConteoCategorias(conteoCategoriasTemporal);

                            // Cargar productos en el transporte del conductor seleccionado
                            Transporte transporte = conductorSeleccionado.getTransporte();
                            transporte.abastecerProducto(tiendaSeleccionada, productosGenerados);
                            
                            //Cumplir con la meta del conductor y del operario
                            conductorSeleccionado.setIndiceMeta(pesoTotalProductos);
                            conductorSeleccionado.cantidadTrabajo += 1;
                            Fabrica.getOperario().setIndiceMeta(Fabrica.getOperario().getIndiceMeta() + 1);
                            Fabrica.getOperario().cantidadTrabajo += 1;

                            // Descargar productos en la tienda
                            tiendaSeleccionada.descargarProducto(transporte);
                            System.out.println("========================================");
                            System.out.println("Productos descargados en la tienda " + tiendaSeleccionada.getNombre());
                            System.out.println("Ha seleccionado el transporte: " + transporteSeleccionadoIndex);
                            System.out.println("La tienda " + tiendaSeleccionada.getNombre() + " se abastecerá por: " + transporteSeleccionado.getNombre());
                            System.out.println("El producto fue enviado con éxito. Ahora la tienda tiene:");
                            System.out.println("       PRODUCTOS:");
                            System.out.println(tiendaSeleccionada.cantidadProductos());// Sobrecarga de método cantidadProductos
                            System.out.println("========================================"); 
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

            if (salir) break; // Esta línea se utiliza para verificar si salir se ha establecido en true en cualquier punto del bucle.


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
                    System.out.println("========================================");
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
        sc.close();
    }
}