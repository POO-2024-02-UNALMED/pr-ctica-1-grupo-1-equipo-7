package gestion;

//Se importan librerias para el uso de fechas con su respectivo formato
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.Serializable;
import java.util.ArrayList;
import produccion.Producto;
import produccion.Tienda;
import produccion.Transporte;
import produccion.estadosProducto;

public class Factura implements Serializable{
    private static final long serialVersionUID = 2L;

    //Atributos
    private Tienda tienda; 
    private Cliente cliente; 
    private Transporte transporte; 
    private LocalDate fecha; 
    private int id;
    private int precioEnvio;
    private double total; //Precio total de la factura. 
    private ArrayList<Producto> listaProductos;
    private static int totalCreadas=0; 
    public static ArrayList<Factura> listaFacturas=new ArrayList<>(); 

    public Factura(Tienda tienda, Cliente cliente, Transporte transporte, ArrayList<Producto> listaProductos,int precioEnvio, LocalDate fecha) {
        this.tienda = tienda;
        this.cliente = cliente;
        this.transporte = transporte;
        this.listaProductos = listaProductos; 
        this.precioEnvio= precioEnvio;
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
    //Calcular envio no se usara en el total factura
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
//Método que se encarga de mostrar, por medio de un String, los productos de la factura, indicando si han sido devueltos o no. Se llama desde la implementacion de la 
//interfaz IMostrarProductos

  public String mostrarProductos(){
    return IMostrarProductos.mostrarProductos(this);
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
    if (f.getFecha().isAfter(fecha1) && f.getFecha().isBefore(fecha2) || f.getFecha().isEqual(fecha1) || f.getFecha().isEqual(fecha2)) {
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
  double ganancias2 = gananciasTotales(Factura.getFechaMin(), Factura.getFechaMax());
  return ((ganancias2 - ganancias1) / ganancias1) * 100;
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene la moda de una lista de productos
public static String modaProductos(LocalDate fecha1, LocalDate fecha2) {
  ArrayList<Factura> facturas = getFacturasEntreFechas(fecha1, fecha2);
  ArrayList<String> productos = new ArrayList<>();
  for (Factura f: facturas) {
    for (Producto p: f.getListaProductos()) {
      productos.add(p.getNombre());
    }
  }
  int maxCount = 0;
  String maxStr = "";
  for (String s: productos) {
    int count = 0;
    for (String s2: productos) {
      if (s.equals(s2)) {
        count++;
      }
    }
    if (count > maxCount) {
      maxCount = count;
      maxStr = s;
    }
  }
  return maxStr;
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene la moda de una lista de clientes
public static Object modaClientes(LocalDate fecha1, LocalDate fecha2) {
  ArrayList<Factura> facturas = getFacturasEntreFechas(fecha1, fecha2);
  ArrayList<String> clientes = new ArrayList<>();
  for (Factura f: facturas) {
    clientes.add(f.getCliente().getNombre());
  }
  int maxCount = 0;
  String maxStr = "";
  for (String s: clientes) {
    int count = 0;
    for (String s2: clientes) {
      if (s.equals(s2)) {
        count++;
      }
    }
    if (count > maxCount) {
      maxCount = count;
      maxStr = s;
    }
  }
  return maxStr;
}

//Funcionalidad a la que pertenece: Estadistica
//Obtiene la moda de una lista de tiendas
public static Object modaTiendas(LocalDate fecha1, LocalDate fecha2) {
  ArrayList<Factura> facturas = getFacturasEntreFechas(fecha1, fecha2);
  ArrayList<String> tiendas = new ArrayList<>();
  for (Factura f: facturas) {
    tiendas.add(f.getTienda().getNombre());
  }
  int maxCount = 0;
  String maxStr = "";
  for (String s: tiendas) {
    int count = 0;
    for (String s2: tiendas) {
      if (s.equals(s2)) {
        count++;
      }
    }
    if (count > maxCount) {
      maxCount = count;
      maxStr = s;
    }
  }
  return maxStr;
}

@Override
public String toString() {
    StringBuilder factura = new StringBuilder();
    double totalPrecio = 0;
    double totalPeso = 0;
    double precioEnvio = this.precioEnvio;

    // Borde superior
    factura.append("=====================================\n");
    factura.append("|                                   |\n");
    factura.append(String.format("| %-33s |\n", tienda.getNombre())); // Corrección aquí
    factura.append("|                                   |\n");
    factura.append("=====================================\n");

    // Encabezado del cliente y detalles
    factura.append(String.format("| ID Factura: %-24d |\n", this.id));
    factura.append(String.format("| Cliente: %-26s |\n", cliente.getNombre()));
    factura.append(String.format("| Cédula: %-26s |\n", cliente.getCedula()));
    factura.append(String.format("| Fecha: %-28s |\n", fecha));
    factura.append(String.format("| Transporte: %-22s |\n", transporte.getTipoTransporte().getNombre()));
    factura.append("========================================================\n");

    // Encabezado de los productos
    factura.append("| Producto                     | Precio    | Peso (kg) |\n");
    factura.append("|------------------------------|-----------|-----------|\n");

    // Detalles de los productos
    for (Producto producto : listaProductos) {
        if (producto != null) {
            factura.append(String.format(
                "| %-28s | $%-8.2f | %-8.2f |\n",
                producto.getNombre(), (double) producto.getPrecio(), producto.getPeso()
            ));
            totalPrecio += (double) producto.getPrecio();  // Convertir a double si no lo es
            totalPeso += producto.getPeso();
        }
    }

    // Totales
    totalPrecio += (double) precioEnvio;  // Convertir a double si no lo es
    factura.append("|------------------------------|-----------|-----------|\n");
    factura.append(String.format("| Envío                       | $%-8.2f | %-8s |\n", (double) precioEnvio, "N/A"));
    factura.append(String.format("| Total                       | $%-8.2f | %-8.2f |\n", totalPrecio, totalPeso));
    factura.append("=======================================================\n");

    return factura.toString();
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
public int getPrecioEnvio(){
  return precioEnvio;
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
public void setPrecioEnvio(int precioEnvio){
  this.precioEnvio = precioEnvio;
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
