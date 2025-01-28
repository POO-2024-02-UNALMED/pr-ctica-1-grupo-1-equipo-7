package produccion;

import java.io.Serializable;
import java.util.ArrayList;
import gestion.Conductor;

public class Transporte implements Serializable {
    private static final long serialVersionUID = 9L;

    // Atributos
    private TipoTransporte tipoTransporte;
    private double capacidad;
    private double costo;
    private Conductor conductor;
    private static ArrayList<Transporte> listaTransportes;
    private Tienda tienda;
    private ArrayList<Producto> listaDeProductos;
    final static int montoEnvioGratis=100000;

    public Transporte(TipoTransporte tipoTransporte, double capacidad, double costo){
        this.tipoTransporte = tipoTransporte;
        this.capacidad = capacidad;
        this.costo = costo;
        Transporte.listaTransportes = new ArrayList<>();
        this.listaDeProductos = new ArrayList<>();
    }

    // Getters y setters

    // Para el atributo tipoTransporte
    public TipoTransporte getTipoTransporte() {
        return tipoTransporte;
    }

    public void setTipoTransporte(TipoTransporte tipoTransporte) { 
        this.tipoTransporte = tipoTransporte;
    }

    // Para el atributo capacidad
    public double getCapacidad() { 
        return capacidad;
    }

    public void setCapacidad(double capacidad) { 
        this.capacidad = capacidad;
    }

    // Para el atributo costo
    public double getCosto() { 
        return costo;
    }

    public void setCosto(double costo) { 
        this.costo = costo;
    }

    // Para el atributo conductor
    public Conductor getConductor() { 
        return conductor;
    }

    public void setConductor(Conductor conductor) { 
        this.conductor = conductor;
        //conductor.setTransporte(this);
    }

    // Para el atributo listaTransportes
    public static ArrayList<Transporte> getListaTransportes() {
        return Transporte.listaTransportes;
    }

    public static void setListaTransportes(ArrayList<Transporte> listaTransportes) { 
        Transporte.listaTransportes = listaTransportes;
    }
    
    // Para el atributo tienda
    public Tienda getTienda() { 
        return tienda;
    }

    public void setTienda(Tienda tienda) { 
        this.tienda = tienda;
    }

    // Para el atributo listaDeProductos
    public ArrayList<Producto> getListaDeProductos() { 
        return listaDeProductos;
    }

    public void setListaDeProductos(ArrayList<Producto> listaDeProductos) { 
        this.listaDeProductos = listaDeProductos;
    }

     // Método para cargar productos en el transporte y asignar la tienda de destino
    public void abastecerProducto(Tienda tiendaSeleccionada, ArrayList<Producto> productosSeleccionados) {
        this.listaDeProductos.addAll(productosSeleccionados);
        this.tienda = tiendaSeleccionada;
     }
     //Metodo startic perteneciente a la funcionalidad Envio Pedidos
     //El método devuelve true si el precio total de los productos supera el monto de envío gratuito, de lo contrario, false.
    public static boolean enviarGratis(ArrayList<Producto> listaProductos) {
        int precioTotal = 0;
        for (Producto producto : listaProductos) {
            precioTotal += producto.getPrecio();
        }
        return precioTotal > montoEnvioGratis;
    }
    //Metodo static perteneciente a la funcionalidad Envio Pedidos
    //El método calcula y devuelve el peso total de una lista de productos, sumando solo los productos con peso positivo. Si un producto tiene un peso inválido (no positivo), muestra un mensaje de error.
    public static double calcularTotalPeso(ArrayList<Producto> listaProductosPedidos){
        double totalPeso=0;
        for (Producto producto : listaProductosPedidos) {
            double peso = producto.getPeso();
            if (peso > 0) { // Validamos que el peso sea positivo
                totalPeso += peso;
            } else {
                System.out.println("\nError: Peso inválido para el producto " + producto.getNombre());
            }
        }
        return totalPeso;
    }

/*      public ArrayList<Producto> cantidadProductos(Producto producto ,int cantidadAenviar){
        for (int i=0; i<=int cantidadAEnviar; i++){
            productosSeleccionados.add(producto);
        }
     }
*/
    
}
