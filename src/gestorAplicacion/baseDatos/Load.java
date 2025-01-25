package baseDatos;

import java.util.ArrayList;
import java.util.HashMap;

import gestion.Cliente;
import gestion.Conductor;
import gestion.Factura;
import gestion.Vendedor;
import produccion.Fabrica;
import produccion.Producto;
import produccion.Tienda;
import produccion.Transporte;

public class Load {
    
     public static ArrayList<Producto> catalogo = new ArrayList<Producto>();
     public static ArrayList<Tienda> tiendas = new ArrayList<Tienda>();
     public static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
     public static ArrayList<Vendedor> vendedores = new ArrayList<Vendedor>();
     public static ArrayList<Factura> facturas = new ArrayList<Factura>();
    public static ArrayList<Conductor> conductores = new ArrayList<Conductor>();
     public static Fabrica fabrica;
     public static Transporte transporteAbastecer;

    
    public static void guardar(){

      facturas = Factura.getListaFacturas();
      clientes = Cliente.getListaClientes();
      catalogo = Producto.getListaProductos();
      conductores = Conductor.getListaConductores();

      Serializador.guardarTiendas();
      Serializador.guardarCatalogo();
      Serializador.guardarFabrica();
      Serializador.guardarFacturas();
      Serializador.guardarTransporte();
      Serializador.guardarVendedores();
      Serializador.guardarClientes();
      Serializador.guardarConductores();

    }

    public static void cargar(){

      try{

       tiendas =  Deserializador.cargarTiendas();
       catalogo = Deserializador.cargarCatalogo();
       fabrica = Deserializador.cargarFabrica();
       clientes = Deserializador.cargarClientes();
       transporteAbastecer = Deserializador.cargarTransporte();
       vendedores =  Deserializador.cargarVendedores();
       facturas =  Deserializador.cargarFacturas();
       conductores = Deserializador.cargaConductores();

       Factura.setListaFacturas(facturas);
       Cliente.setListaClientes(clientes);
       Producto.setListaProductos(catalogo);
       Conductor.setListaConductores(conductores);

      }catch(Exception e){
        System.out.println("Ha ocurrido un error en la deserialización");
        e.printStackTrace();
      }
    }

}
