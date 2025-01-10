package gestion;

//Se importan librerias para el uso de fechas con su respectivo formato
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import produccion.Producto;
import produccion.Tienda;
import produccion.Transporte;

//JE quedo pendiente de hacer el constructor de Factura, con la conversion de fecha dentro de este

public class Factura {
    private Tienda tienda; 
    private Cliente cliente; 
    private Transporte transporte; 
    private LocalDate fecha; 
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

  public static String mostrarProductosFactura(Factura f){
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

//temporal, se usa como guia para los metodos necesarios en la funcionalidad Estadistica

/*
getFacturasEntreFechas(int fecha1, int fecha2): ArrayList<Factura> (static)

+getListaFechas(int fecha1, int fecha2): ArrayList<Integer> (static)
+getListaFechas(): ArrayList<Integer> (static)

+ gananciasDiscretas(int fecha1, int fecha2): HashMap<Integer, Double> (static)
+ gananciasDiscretas(ArrayList<Integer> fechas): HashMap<Integer, Double> (static)

+gananciasTotales(HashMap<Integer, Double> dictGananciasDiscretas): double (static)

+promedioPorDia(HashMap<Integer, Double> dictGananciasDiscretas): double (static)

+aumentoPorcentual(HashMap<Integer, Double> dictGananciasDiscretas): double (static)

-masComun(List<T> list): T (static)

+moda(int fecha1, int fecha2, String atributo): Moda (static)

+getFechaMax(): int (static)
+getFechaMin(): int (static)
 

 */

//Se hace la conversion de tipo string a tipo LocalDate
public static LocalDate convertirStrADate(String f) {
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato de fecha: día/mes/año
  
  LocalDate fecha = LocalDate.parse(f, formatter);
  return fecha;
} 

//Por completar. Crea una lista ordenada con una fecha de inicio y una fecha final
public static ArrayList<Factura> getFacturasEntreFechas(LocalDate fecha1, LocalDate fecha2) {

  return new ArrayList<>();
}
 
}
