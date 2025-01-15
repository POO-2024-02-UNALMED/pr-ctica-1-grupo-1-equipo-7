package produccion;
import gestion.Vendedor;
import gestion.Cliente;
import gestion.CuentaBancaria;
import gestion.Factura;

import java.util.ArrayList;
import java.util.List;
public class Tienda {
    //atributos
    private String nombre;
    private Vendedor vendedor;
    private CuentaBancaria cuentaBancaria;
    private ArrayList<Producto> productosDevueltos;
    private static int numTiendas = 0; 
    private ArrayList<Producto> listaProducto; //Cada tienda tiene una lista de productos DIFERENTES, este atributo NO puede ser static. 
    private ArrayList<Object[]> productosPorCategoria = new ArrayList<>(); // Lista de [Producto, Categoria]
    private ArrayList<Object[]> cantidadProductos = new ArrayList<>();//duda aqui ya que puede ser un integer (con el UM).
    //para la funcionalidad asbatecer (productosPorCategoria):
    private List<String> categorias = new ArrayList<>();
    private List<Integer> conteoCategorias = new ArrayList<>();//conteo de productos por categoria
    private Integer cantidadMaximaPorCategoria;//Es la cantidad maxima de productos que puede tener una tienda por categoria. (Es atributo auxiliar para la funcionalidad de abastecer.)
    
    // constructor
    public Tienda(String nombre,Vendedor vendedor, CuentaBancaria cuentaBancaria, int numTiendas){
        this.nombre=nombre;
        this.vendedor=vendedor;
        this.cuentaBancaria=cuentaBancaria;
        numTiendas++;
        this.cantidadProductos=new ArrayList<>();
        this.productosDevueltos=new ArrayList<>();
        this.listaProducto=new ArrayList<>();
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
public ArrayList<Object[]> getCantidadProductos() {
    return cantidadProductos;
}

public List<String> getCategorias() {
    return categorias;
}
public List<Integer> getConteoCategorias() {
    return conteoCategorias;
}
public Integer getCantidadMaximaPorCategoria() {
    return cantidadMaximaPorCategoria;
}
public void setCantidadMaximaPorCategoria(Integer cantidadMaximaPorCategoria) {
    this.cantidadMaximaPorCategoria = cantidadMaximaPorCategoria;
}
//metodos

//cambio de mostrar productos ahora retorna un string.
public String mostrarProductos() {
    String productos = "";
    if (this.listaProducto == null || this.listaProducto.isEmpty()) {
        return "No hay productos registrados o disponibles.";
    }
    for (Producto producto : this.listaProducto) {
        productos += producto.getNombre() + "\n";
    }
    return productos;
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
// Método para calcular e imprimir productos por categoría
public String productosPorCategoria(List<Producto> productos) {
    // Limpiar las listas antes de procesar
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

    // Construir el resultado
    StringBuilder resultado = new StringBuilder();
    for (int i = 0; i < categorias.size(); i++) {
        resultado.append(categorias.get(i))
                 .append(": ")
                 .append(conteoCategorias.get(i))
                 .append(" productos\n");
    }

    return resultado.toString();
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
public void agregarCantidadProducto(Producto producto, int cantidad){//[[producto1,cantidad],[producto2,cantidad]]
    boolean existe = false;
    for (Object[] item : cantidadProductos) {
        if (item[0].equals(producto)) {
            // Si el producto ya existe, actualizamos la cantidad
            item[1] = (int) item[1] + cantidad;
            existe = true;
            break;
        }
    }
    // Si el producto no existe, lo agregamos
    if (!existe) {
        Object[] listaAux = {producto, cantidad};
        cantidadProductos.add(listaAux);
    }
}
public String mostrarCantidadProductos() {
    if (cantidadProductos == null || cantidadProductos.isEmpty()) {
        return "Actualmente no hay productos registrados en el sistema.";
    }

    ArrayList<Object[]> listaFiltrada = new ArrayList<>();
    for (Object[] listaAux : cantidadProductos) {
        int cantidad = (int) listaAux[1];
        if (cantidad != 0) {
            listaFiltrada.add(listaAux);
        }
    }

    if (listaFiltrada.isEmpty()) {
        return "No hay productos disponibles con cantidad mayor a 0.";
    }

    StringBuilder texto = new StringBuilder();
    int contador = 1;
    for (Object[] lista : listaFiltrada) {
        Producto producto = (Producto) lista[0];
        int cantidad = (int) lista[1];

        texto.append(contador).append(". Producto: ").append(producto.getNombre()).append("\n")
             .append(" - Cantidad: ").append(cantidad).append("\n")
             .append(" - Peso: ").append(producto.getPeso()).append("\n\n");
        contador++;
    }

    return texto.toString().trim();
}
public double venderProducto(Producto productoSeleccionado) {
    for (Object[] item : cantidadProductos) {
        Producto producto = (Producto) item[0];
        int cantidad = (int) item[1];

        if (producto.equals(productoSeleccionado)) {
            if (cantidad > 0) {
                item[1] = cantidad - 1; // Reducir inventario
                return producto.getPeso(); // Retornar el peso del producto vendido
            } else {
                return 0.0;
            }
        }
    }
    return 0.0;
}
}
