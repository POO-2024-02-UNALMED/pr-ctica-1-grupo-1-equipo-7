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
    private ArrayList<Producto> cantidadProductos;//
    //para la funcionalidad asbatecer (productosPorCategoria):
    private List<String> categorias = new ArrayList<>();
    private List<Integer> conteoCategorias = new ArrayList<>();//conteo de productos por categoria
    private Integer cantidadMaximaPorCategoria;//Es la cantidad maxima de productos que puede tener una tienda por categoria. (Es atributo auxiliar para la funcionalidad de abastecer.)
    //para la funcionalidad abastecer:
    private List<Producto> productosAbastecer = new ArrayList<>();//pueden ser remplazados estas dos listas por la logica del objet[].
    private List<Integer> cantidadesAbastecer = new ArrayList<>();

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
public ArrayList<Producto> getCantidadProductos() {
    return cantidadProductos;
}

public void setCantidadProductos(ArrayList<Producto> cantidadProductos) {
    this.cantidadProductos = cantidadProductos;
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



    return productos;
<<<<<<< HEAD

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
/*public String cantidadProductos(Producto producto, int cantidad) {
    // Buscar si el producto ya existe en la lista
    int index = productosAbastecer.indexOf(producto);

    if (index == -1) {
        // Si no existe, agregarlo con la cantidad inicial
        productosAbastecer.add(producto);
        cantidadesAbastecer.add(cantidad);
    } else {
        // Si ya existe, incrementar la cantidad correspondiente
        cantidadesAbastecer.set(index, cantidadesAbastecer.get(index) + cantidad);
    }

    // Construir el resultado
    StringBuilder resultado = new StringBuilder();
    for (int i = 0; i < productosAbastecer.size(); i++) {
        resultado.append(productosAbastecer.get(i).getNombre()) 
                 .append(": ")
                 .append(cantidadesAbastecer.get(i))
                 .append(" unidades\n");
    }

    return resultado.toString(); // Retorna el resultado en lugar de imprimirlo
}*/ //Deberia usarla logica de este metodo como mostrarProductoa sde la tienda, mas no asi.
public void descargarProducto(Transporte transporteSeleccionado) {
    ArrayList<Producto> productosTransportados = transporteSeleccionado.getListaDeProductos();
    for (Producto producto : productosTransportados) {
        this.listaProducto.add(producto);
    }
    transporteSeleccionado.getListaDeProductos().clear(); // Vaciar la lista de productos del transporte
}
}
}




