package gestion;
import java.util.ArrayList;
import produccion.Producto;
import produccion.Tienda;
import produccion.Transporte;

public class Factura {
    private Tienda tienda; 
    private Cliente cliente; 
    private Transporte transporte; 
    private int fecha; 
    private int id;
    private ArrayList<Producto> listaProductos;  
    private static int totalCreadas=0; 
    public static ArrayList<Factura> listaFacturas; 

    //Funcionalidad a la que pertenece: Devoluciones
    //Se encarga de imprimir la lista de las facturas que contiene la tienda, con el nombre del cliente y el id de la factura.
  public static String mostrarFacturas(){
    int n=1; 
    String texto="";
    for (Factura f: listaFacturas){
      texto+=n+". "+f.cliente.getNombre().toUpperCase()+" ID:"+f.id+"\n";
      n++;
    }
    return texto;
}
//Funcionalidad a la que pertenece: Devoluciones
//Método que se encarga de mostrar, por medio de un String, los productos de la factura, indicando si han sido devueltos o no. 

  public static String mostrarProductos(Factura f){
    int n=1; 
    String texto="";
    for (Producto p: f.getListaProductos()){
      if (p.estado.equals("DEVUELTO")){
      texto+=n+". "+p.nombre + "(devuelto)"+"\n";
      }
      else{
        texto+=n+". "+p.nombre+"\n";
      }
      n++;
    } return texto;
}
public  static Factura seleccionarFactura(int n){
  return listaFacturas.get(n-1);
}

public Producto seleccionarProducto(int n){
  return this.getListaProductos().get(n-1);
}

//Funcionalidad a la que pertenece: Devoluciones
//Método que se encarga de verificar si todos los productos de una factura han sido devueltos o no.
public static boolean todosDevueltos(ArrayList<Producto> listaProductos){
  for (Producto p: listaProductos){
    if (!p.estado.equals("DEVUELTO")){
      return false;
    }
  }
  return true;
}

//getters y setters
public ArrayList<Producto> getListaProductos(){
  return listaProductos;
}

public Tienda getTienda() {
  return this.tienda;
}
public Cliente getCliente(){
  return this.cliente;
  }
 
}
