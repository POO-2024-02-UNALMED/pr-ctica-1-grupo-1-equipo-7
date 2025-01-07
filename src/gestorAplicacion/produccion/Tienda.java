package produccion;
import gestion.Vendedor;
import gestion.Cliente;
import gestion.CuentaBancaria;
import gestion.Factura;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Tienda {
    //atributos
    private String nombre;
    private Vendedor vendedor;
    private CuentaBancaria cuentaBancaria;
    private ArrayList<Producto> productosDevueltos;
    private static int numTiendas = 0; 
    private static  ArrayList<Producto> listaProducto; //Cada tienda tiene una lista de productos DIFERENTES, este atributo NO puede ser static. 

    private ArrayList<Object[]> productosPorCategoria = new ArrayList<>(); // Lista de [Producto, Categoria]
<<<<<<< HEAD
    private ArrayList<Producto> cantidadProductos;//duda aqui ya que puede ser un integer (con el UM).
    //para la funcionalidad productosPorCategoria:
    private List<String> categorias = new ArrayList<>();
    private List<Integer> conteoCategorias = new ArrayList<>();
=======
    private ArrayList<Producto> cantidadProductos;
    Scanner sc = new Scanner(System.in);
    //duda aqui del integer con el UML
    //lo que yo yhan considera que deberia ponerse:
    // private List<String> categorias = new ArrayList<>();
    //private List<Integer> conteoCategorias = new ArrayList<>();
>>>>>>> ec36156a22893944b962e40802bb3ba07e87fd51
    // constructor
    public Tienda(String nombre,Vendedor vendedor, CuentaBancaria cuentaBancaria, int numTiendas){
        this.nombre=nombre;
        this.vendedor=vendedor;
        this.cuentaBancaria=cuentaBancaria;
        numTiendas++;
        this.cantidadProductos=new ArrayList<>();
        this.productosDevueltos=new ArrayList<>();
        Tienda.listaProducto=new ArrayList<>();
    }
    //getters y setters

    
// Atributo nombre
public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

// Atributo vendedor
public Vendedor getVendedor() {
    return vendedor;
}

public void setVendedor(Vendedor vendedor) {
    this.vendedor = vendedor;
}

// Atributo cuentaBancaria
public CuentaBancaria getCuentaBancaria() {
    return cuentaBancaria;
}

public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
    this.cuentaBancaria = cuentaBancaria;
}

// Atributo productosDevueltos
public ArrayList<Producto> getProductosDevueltos() {
    return productosDevueltos;
}

public void setProductosDevueltos(ArrayList<Producto> productosDevueltos) {
    this.productosDevueltos = productosDevueltos;
}

// Atributo numTiendas
public int getNumTiendas() {
    return numTiendas;
}

// Atributo listaProducto
public  ArrayList<Producto> getListaProducto() {
    return this.listaProducto;
}

public void setListaProducto(ArrayList<Producto> listaProducto) {
    this.listaProducto = listaProducto;
}

// Atributo cantidadProductos
public ArrayList<Producto> getCantidadProductos() {
    return cantidadProductos;
}

public void setCantidadProductos(ArrayList<Producto> cantidadProductos) {
    this.cantidadProductos = cantidadProductos;
    }  


public  void mostrarProductos(){
    if (this.listaProducto.isEmpty()) {
        System.out.println("No hay productos registrados.");
        return;
    }
    for (int i = 0; i < listaProducto.size(); i++) {
        System.out.println((i + 1) + ". " + this.listaProducto.get(i).getNombre());
    }
}
public String cantidadProductos(){
    if (listaProducto == null) {
        return "El inventario no está disponible.";
    }
    return "En el inventario se encuentran " + listaProducto.size() + " productos.";
}
public void agregarProductosPorCategoria(Producto producto, int categoria){
    Object[] productoCategoria = {producto, categoria};
    productosPorCategoria.add(productoCategoria);
}

    return productos;*/
<<<<<<< HEAD
/*public String productosPorCategoria(){
=======

public Producto seleccionarProducto(int n){
    return this.getListaProducto().get(n-1);
}

public String productosPorCategoria(){
>>>>>>> ec36156a22893944b962e40802bb3ba07e87fd51
    if (productosPorCategoria.isEmpty()) {
        return "No hay productos registrados.";
    }
    String productos = "";
    for (Object[] productoCategoria : productosPorCategoria) {
        productos += ((Producto) productoCategoria[0]).getNombre() + " - Categoría: " + productoCategoria[1] + "\n";
    }*/
        // Método para calcular e imprimir productos por categoría
        public void productosPorCategoria(List<Producto> productos) {
            // Limpiar las listas antes de procesar(por si se manipulo anteriormente)
            categorias.clear();
            conteoCategorias.clear();
    
            // Procesar la lista de productos
            for (Producto producto : productos) {
                String categoria = producto.getCategoria(); // Obtener la categoría del producto
    
                int index = categorias.indexOf(categoria); // Buscar si la categoría ya existe
                if (index == -1) {
                    // Si no existe, agregarla junto con el conteo inicial
                    categorias.add(categoria);
                    conteoCategorias.add(1);
                } else {
                    // Si ya existe, incrementar el conteo correspondiente
                    conteoCategorias.set(index, conteoCategorias.get(index) + 1);
                }
            }
            // Construir el resultado(con el stringbuilder para manejar mejor la concatenación)
            StringBuilder resultado = new StringBuilder();
            for (int i = 0; i < categorias.size(); i++) {
                resultado.append(categorias.get(i))
                         .append(": ")
                         .append(conteoCategorias.get(i))
                         .append(" productos\n");
            }
            System.out.println("Productos por categoría:");
            System.out.println(resultado.toString());
        }
    }    
}
//Funcionalidad a la que pertenece: Devoluciones
 public Cliente devolverProducto(Factura factura, Producto producto){
    productosDevueltos.add(producto);
    
    producto.setEstado("DEVUELTO");
    productosDevueltos.add(producto);
    return factura.getCliente();
}
//Funcionalidad a la que pertenece: Devoluciones
public String mostrarProductosConExcedente(Factura factura, Producto producto){
    
  int precio=producto.getPrecio();
  Tienda tienda=factura.getTienda();
  String texto="";
  int n=1;
  for (Producto p: listaProducto){
    int excedente=p.getPrecio()-producto.getPrecio();
    if (excedente>0){
      texto+=n+". "+p.getNombre()+"(excedente:$"+excedente+")\n";
    }
    else{
        texto+=n+". "+p.getNombre()+"\n";
    }  
}
return texto;
}
}
