package produccion;

import java.util.ArrayList;
import gestion.Conductor;

public class Transporte {
    private TipoTransporte tipoTransporte;
    private double capacidad;
    private double costo;
    private Conductor conductor;
    private ArrayList<TipoTransporte> listaTransportes;
    private Tienda tienda;
    private ArrayList<Producto> listaDeProductos;

    public Transporte(TipoTransporte tipoTransporte, double capacidad, double costo, Conductor conductor, Tienda tienda){
        this.tipoTransporte = tipoTransporte;
        setTipoTransporte(tipoTransporte);//para enlazar un transporte con un tipo de transporte
        this.capacidad = capacidad;
        this.costo = costo;
        this.conductor = conductor;
        setConductor(conductor);//para enlazar un transporte con un conductor
        this.tienda = tienda;
        this.listaTransportes = new ArrayList<>();
        this.listaDeProductos = new ArrayList<>();

    }
    public Transporte(){}

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
        conductor.setTransporte(this);
    }

    // Para el atributo listaTransportes
    public ArrayList<TipoTransporte> getListaTransportes() {
        return this.listaTransportes;
    }

    public void setListaTransportes(ArrayList<TipoTransporte> listaTransportes) { 
        this.listaTransportes = listaTransportes;
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
/*      public ArrayList<Producto> cantidadProductos(Producto producto ,int cantidadAenviar){
        for (int i=0; i<=int cantidadAEnviar; i++){
            productosSeleccionados.add(producto);
        }
     }
*/
}   
