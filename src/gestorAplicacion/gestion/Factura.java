package gestion;
import java.util.ArrayList;
import produccion.*;
public class Factura {
    private Tienda tienda; 
    private Cliente cliente; 
    private Transporte transporte; 
    private int fecha; 
    private int id;
    private ArrayList<Producto> listaProductos;  
    private static int totalCreadas=0; 
    public static ArrayList<Factura> listaFacturas; 

  public static void mostrarFacturas(){
    int n=1; 
    for (Factura f: listaFacturas){
      System.out.println(n+" "+f.cliente.getNombre()+" ID:"+f.id);
      n++;
    }
}
  public static void mostrarProductos(Factura f){
    int n=1; 
    for (Producto p: f.getListaProductos()){
      if (p.estado.equals("DEVUELTO")){
      System.out.println(n+" "+p.nombre + "(devuelto)");
      }
      else{
        System.out.println(n+" "+p.nombre);
      }
      n++;
    }
}
public  static Factura seleccionarFactura(int n){
  return listaFacturas.get(n-1);
}

public Producto seleccionarProducto(int n){
  return this.getListaProductos().get(n-1);
}

public boolean todosDevueltos(){
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
}
