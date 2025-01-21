package gestion;

//Se importan librerias para el uso de fechas con su respectivo formato
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import produccion.Producto;
import produccion.Tienda;
import produccion.Transporte;
import produccion.estadosProducto;
import gestion.Moda;

public class Factura {
    private Tienda tienda; 
    private Cliente cliente; 
    private Transporte transporte; 
    private LocalDate fecha; 
    private int id;
    private double total;
    private ArrayList<Producto> listaProductos=new ArrayList<>();  
    private static int totalCreadas=0; 
    public static ArrayList<Factura> listaFacturas=new ArrayList<>(); 

    public Factura(Tienda tienda, Cliente cliente, Transporte transporte, ArrayList<Producto> listaProductos, LocalDate fecha) {
        this.tienda = tienda;
        this.cliente = cliente;
        this.transporte = transporte;
        this.listaProductos = listaProductos;
        if (Factura.listaFacturas.size() > 2) {
          Factura.ordenarFacturasPorFecha();
        }

        this.fecha = fecha;

        this.total = calcularTotal();
        listaFacturas.add(this);

        this.id = ++totalCreadas;

        
    }

  //Metodo para ordenar las facturas por fecha
  public static void ordenarFacturasPorFecha() {
    int n = listaFacturas.size();
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (listaFacturas.get(j).getFecha().isAfter(listaFacturas.get(j + 1).getFecha())) {
                Factura temp = listaFacturas.get(j);
                listaFacturas.set(j, listaFacturas.get(j + 1));
                listaFacturas.set(j + 1, temp);
            }
        }
    }
  }
  //Metodo para calcular el valor del envio

  private double calcularValorEnvio(){

    double precioEnvio = transporte.getTipoTransporte().getPrecioEnvio();

    return precioEnvio;
  }


    public double calcularTotal(){

    double totalParcial = 0;
    for(int i=0; i<listaProductos.size(); i++){
        double precioProducto = listaProductos.get(i).getPrecio();
        totalParcial+=precioProducto;
    }

    return totalParcial + calcularValorEnvio();
}

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
      if (p.estado.equals(estadosProducto.DEVUELTO)){
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
    if (!p.estado.equals(estadosProducto.DEVUELTO)){
      return false;
    }
  }
  return true;
}

//Se hace la conversion de tipo string a tipo LocalDate
public static LocalDate convertirStrADate(String f) {
  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formato de fecha: día/mes/año
  
  LocalDate fecha = LocalDate.parse(f, formatter);
  return fecha;
} 
//Obtiene la fecha maxima y minima de las facturas
public static LocalDate getFechaMax(){
  LocalDate fechaMax = LocalDate.of(0, 1, 1);
  for (Factura f: listaFacturas){
    if (f.getFecha().isAfter(fechaMax)){
      fechaMax = f.getFecha();
    }
  }
  return fechaMax;
}
public static LocalDate getFechaMin(){
  LocalDate fechaMin = LocalDate.of(9999, 12, 31);
  for (Factura f: listaFacturas){
    if (f.getFecha().isBefore(fechaMin)){
      fechaMin = f.getFecha();
    }
  }
  return fechaMin;
}
//Funcionalidad a la que pertenece: Estadistica
//Crea un array con las facturas que se encuentran entre dos fechas
public static ArrayList<Factura> getFacturasEntreFechas(LocalDate fecha1, LocalDate fecha2) {
  ArrayList<Factura> facturas = new ArrayList<>();
  for (Factura f: listaFacturas) {
    if (f.getFecha().isAfter(fecha1) && f.getFecha().isBefore(fecha2)) {
      facturas.add(f);
    }
  }
  return facturas;
}

//Funcionalidad a la que pertenece: Estadistica
//Crea un array con las fechas de las facturas que se encuentran entre dos fechas
public static ArrayList<LocalDate> getListaFechas(LocalDate fecha1, LocalDate fecha2) {
  ArrayList<LocalDate> fechas = new ArrayList<>();
  for (Factura f: listaFacturas) {
    if (f.getFecha().isAfter(fecha1) && f.getFecha().isBefore(fecha2)) {
      fechas.add(f.getFecha());
    }
  }
  return fechas;
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene las ganancias discretas de las facturas entre dos fechas
public static ArrayList<Object> gananciasDiscretas(LocalDate fecha1, LocalDate fecha2) {
  ArrayList<Factura> facturas = getFacturasEntreFechas(fecha1, fecha2);
  ArrayList<Object> ganancias = new ArrayList<>();
  for (Factura f: facturas) {
    ArrayList<Object> ganancia = new ArrayList<>();
    ganancia.add(f.getFecha());
    ganancia.add(f.getTotal());
    ganancias.add(ganancia);
  }
  return ganancias;
}
//Funcionalidad a la que pertenece: Estadistica
//Obtiene las ganancias discretas de las facturas entre la fecha minima y maxima
public static ArrayList<Object> gananciasDiscretas() {
  ArrayList<Factura> facturas = listaFacturas;
  ArrayList<Object> ganancias = new ArrayList<>();
  for (Factura f: facturas) {
    ArrayList<Object> ganancia = new ArrayList<>();
    ganancia.add(f.getFecha());
    ganancia.add(f.getTotal());
    ganancias.add(ganancia);
  }
  return ganancias;
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene las ganancias totales de las facturas entre dos fechas
public static double gananciasTotales(LocalDate fecha1, LocalDate fecha2) {
  ArrayList<Object> ganancias = gananciasDiscretas(fecha1, fecha2);
  double total = 0;
  for (Object g: ganancias) {
    ArrayList<Object> ganancia = (ArrayList<Object>) g;
    total += (double) ganancia.get(1);
  }
  return total;
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene las ganancias totales de las facturas entre la fecha minima y maxima
public static double gananciasTotales() {
  ArrayList<Object> ganancias = gananciasDiscretas();
  double total = 0;
  for (Object g: ganancias) {
    ArrayList<Object> ganancia = (ArrayList<Object>) g;
    total += (double) ganancia.get(1);
  }
  return total;
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene el promedio de las ganancias de las facturas entre dos fechas
public static double promedioGanancias(LocalDate fecha1, LocalDate fecha2) {
  ArrayList<Object> ganancias = gananciasDiscretas(fecha1, fecha2);
  double total = 0;
  for (Object g: ganancias) {
    ArrayList<Object> ganancia = (ArrayList<Object>) g;
    total += (double) ganancia.get(1);
  }
  return total / ganancias.size();
}


//Funcionalidad a la que pertenece: Estadistica
//Obtiene el aumento porcentual de las ganancias de las facturas entre dos fechas
public static double aumentoPorcentual(LocalDate fecha1, LocalDate fecha2) {
  double ganancias1 = gananciasTotales(fecha1, fecha2);
  double ganancias2 = gananciasTotales();
  return ((ganancias2 - ganancias1) / ganancias1) * 100;
}


//Funcionalidad a la que pertenece: Estadistica
//Obtiene el producto mas comun de una lista de productos
public static Object masComun(ArrayList<Object> productos) {
  Object masComun = productos.get(0);
  int max = 0;
  for (Object p: productos) {
    int count = 0;
    for (Object p2: productos) {
      if (p.equals(p2)) {
        count++;
      }
    }
    if (count > max) {
      max = count;
      masComun = p;
    }
  }
  return masComun;
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene la moda de una lista de productos
public static Object modaProductos(LocalDate fecha1, LocalDate fecha2) {
  ArrayList<Factura> facturas = getFacturasEntreFechas(fecha1, fecha2);
  ArrayList<Object> productos = new ArrayList<>();
  for (Factura f: facturas) {
    for (Producto p: f.getListaProductos()) {
      productos.add(p);
    }
  }
  return masComun(productos);
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene la moda de una lista de clientes
public static Object modaClientes(LocalDate fecha1, LocalDate fecha2) {
  return new Object();
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene la moda de una lista de tiendas
public static Object modaTiendas(LocalDate fecha1, LocalDate fecha2) {
  return new Object();
}


//getters
public ArrayList<Producto> getListaProductos(){
  return listaProductos;
}

public Tienda getTienda() {
  return this.tienda;
}
public Cliente getCliente(){
  return this.cliente;
  }

public LocalDate getFecha() {
  return fecha;
}

public int getId() {
  return id;
}

public double getTotal() {
  return total;
}

public static int getTotalCreadas() {
  return totalCreadas;
}

public static ArrayList<Factura> getListaFacturas() {
  return listaFacturas;
}



//setters

public void setTienda(Tienda tienda) {
  this.tienda = tienda;

}

public void setCliente(Cliente cliente) {
  this.cliente = cliente;

}

public void setTransporte(Transporte transporte) {
  this.transporte = transporte;

}

public void setFecha(LocalDate fecha) {
  this.fecha = fecha;

}

public void setId(int id) {
  this.id = id;

}

public void setTotal(double total) {
  this.total = total;

}

public void setListaProductos(ArrayList<Producto> listaProductos) {
  this.listaProductos = listaProductos;

}

public static void setTotalCreadas(int totalCreadas) {
  Factura.totalCreadas = totalCreadas;

}

public static void setListaFacturas(ArrayList<Factura> listaFacturas) {
  Factura.listaFacturas = listaFacturas;

}

}