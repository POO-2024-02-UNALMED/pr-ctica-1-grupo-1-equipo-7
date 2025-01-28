package gestion; 

import java.util.ArrayList;
import gestion.*;
import produccion.*;

public interface IMostrarProductos {

    /**
     * Método default que, por defecto, lanza una excepción.
     * Cada clase puede sobrescribirlo con su propia lógica.
     * Aquí aplicaría la lógica de la Tienda, que omite un "producto" por ID.
     */
    default ArrayList<Producto> mostrarProductos(Producto productoOmitir) {
        throw new UnsupportedOperationException(
            "Este método debe ser sobreescrito en la clase que implemente la interfaz."
        );
    }

    /**
     * Método estático sobrecargado que muestra los productos de una Factura.
     * Simula la lógica de "mostrarProductosFactura(Factura f)".
     */

    static String mostrarProductos(Factura f) {
        if (f == null || f.getListaProductos() == null || f.getListaProductos().isEmpty()) {
            return "La factura no tiene productos.";
        }

        StringBuilder texto = new StringBuilder();
        int n = 1;

        for (Producto p : f.getListaProductos()) {
            if (p.getEstado().equals(estadosProducto.DEVUELTO)) {
                texto.append(n).append(". ").append(p.getNombre())
                     .append(" (devuelto)\n");
            } else {
                texto.append(n).append(". ").append(p.getNombre()).append("\n");
            }
            n++;
        }

        return texto.toString();
    }

    /**
     * Método estático sobrecargado que muestra los productos de una lista
     * (simulando la lógica de Fabrica.productosDisponibles).
     */
    static String mostrarProductos(ArrayList<Producto> listaProductos) {
        if (listaProductos == null || listaProductos.isEmpty()) {
            return "No hay productos registrados o disponibles.";
        }

        StringBuilder productos = new StringBuilder();
        int index = 1;

        for (Producto producto : listaProductos) {
            productos.append(index).append(". ")
                     .append(producto.getNombre()).append(" - ")
                     .append(producto.getPeso()).append("kg - $")
                     .append(producto.getPrecio()).append(" - ")
                     .append(producto.getCategoria()).append("\n");
            index++;
        }

        return productos.toString();
    }
}