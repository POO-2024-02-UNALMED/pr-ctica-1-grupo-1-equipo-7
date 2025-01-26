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
    private static int numTiendas = 0; 
    private ArrayList<Producto> listaProducto; //Cada tienda tiene una lista de productos DIFERENTES, este atributo NO puede ser static. 

    private ArrayList<Object[]> productosPorCategoria = new ArrayList<>(); // Lista de [Producto, Categoria]
    private ArrayList<String> categorias = new ArrayList<>();
    private ArrayList<Integer> conteoCategorias = new ArrayList<>();//conteo de productos por categoria
    private int capacidadMaximaMaterial;//Es la cantidad maxima de productos que puede tener una tienda por la categoria Construccion
    private int capacidadMaximaConsumible;//Es la cantidad maxima de productos que puede tener una tienda por la categoria Alimentos
    private int capacidadMaximaLimpieza;//Es la cantidad maxima de productos que puede tener una tienda por la categoria Hogar

    // constructor
    public Tienda(String nombre,Vendedor vendedor, CuentaBancaria cuentaBancaria, int capacidadMaximaMaterial, int capacidadMaximaConsumible, int capacidadMaximaLimpieza){
        this.nombre=nombre;
        this.vendedor=vendedor;
        this.vendedor.setTienda(this); //Se asigna la tienda al vendedor 
        this.cuentaBancaria=cuentaBancaria;
        numTiendas++;
        this.listaProducto=new ArrayList<>();
        this.capacidadMaximaMaterial = capacidadMaximaMaterial;
        this.capacidadMaximaConsumible = capacidadMaximaConsumible;
        this.capacidadMaximaLimpieza = capacidadMaximaLimpieza;
    }
    public Tienda(){}

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

/*public void setVendedor(Vendedor vendedor) {
    this.vendedor = vendedor;
}*/
public void setVendedor(Vendedor vendedor) {
    this.vendedor = vendedor;
    if (vendedor.getTienda() != this) { // Evitar referencias duplicadas
        vendedor.setTienda(this);
    }
}

// Atributo cuentaBancaria
public CuentaBancaria getCuentaBancaria() {
    return cuentaBancaria;
}

public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
    this.cuentaBancaria = cuentaBancaria;
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

public ArrayList<String> getCategorias() {
    return categorias;
}
public ArrayList<Integer> getConteoCategorias() {
    return conteoCategorias;
}
public void setConteoCategorias(ArrayList<Integer> conteoCategorias) {
    this.conteoCategorias = conteoCategorias;
}
public int getCapacidadMaximaConsumible() {
    return capacidadMaximaConsumible;
}
public int getCapacidadMaximaLimpieza() {
    return capacidadMaximaLimpieza;
}
public int getCapacidadMaximaMaterial() {
    return capacidadMaximaMaterial;
}
public void setCapacidadMaximaConsumible(int capacidadMaximaConsumible) {
    this.capacidadMaximaConsumible = capacidadMaximaConsumible;
}
public void setCapacidadMaximaLimpieza(int capacidadMaximaLimpieza) {
    this.capacidadMaximaLimpieza = capacidadMaximaLimpieza;
}
public void setCapacidadMaximaMaterial(int capacidadMaximaMaterial) {
    this.capacidadMaximaMaterial = capacidadMaximaMaterial;
}
//metodos

//cambio de mostrar productos ahora retorna un string.
public String mostrarProductos() {
    String productos = "";
    if (this.listaProducto == null || this.listaProducto.isEmpty()) {
        return "No hay productos registrados o disponibles.";
    }
    for (Producto producto : this.listaProducto) {
        productos += producto.getNombre() + "-Precio: $"+producto.getPrecio()+"\n";
    }
    return productos;
}


public String cantidadProductosTotal(){
    if (listaProducto == null) {
        return "El inventario no está disponible.";
    }
    return "En el inventario se encuentran " + listaProducto.size() + " productos.";
}
public void agregarProductosPorCategoria(Producto producto, int categoria){
    Object[] productoCategoria = {producto, categoria};
    productosPorCategoria.add(productoCategoria);
}
//Funcionalidad a la que pertenece: Abastecer tiendas
//Metodo que se encarga de mostrar los productos por categoria en formato: (cantidad actual/capacidad maxima)
public String productosPorCategoria(ArrayList<Producto> productos) {
    // Limpiar las listas antes de procesar
    categorias.clear();
    conteoCategorias.clear();

    // Lista de todas las categorías posibles
    ArrayList<String> todasLasCategorias = new ArrayList<>();
    todasLasCategorias.add("Construcción");
    todasLasCategorias.add("Alimentos");
    todasLasCategorias.add("Hogar");

    // Inicializar el conteo de todas las categorías a 0
    for (String categoria : todasLasCategorias) {
        categorias.add(categoria);
        conteoCategorias.add(0);
    }

    // Procesar la lista de productos
    for (Producto producto : productos) {
        String categoria = producto.getCategoria(); // Obtener la categoría del producto

        int index = categorias.indexOf(categoria); // Buscar si la categoría ya existe
        if (index != -1) {
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
                 .append("/");

        // Dependiendo de la categoría, agregar la capacidad máxima correspondiente
        switch (categorias.get(i)) {
            case "Construcción":
                resultado.append(capacidadMaximaMaterial);
                break;
            case "Alimentos":
                resultado.append(capacidadMaximaConsumible);
                break;
            case "Hogar":
                resultado.append(capacidadMaximaLimpieza);
                break;
            default:
                resultado.append("N/A");
                break;
        }

        resultado.append(" productos\n");
    }

    return resultado.toString();
}
//Funcionalidad a la que pertenece: Abastecer tiendas
//Metodo que se encarga de mostrar los productos por categoria en formato: (cantidad actual/capacidad maxima)
//sobrecarga del metodo anterior
public String productosPorCategoria(ArrayList<Producto> productos, List<Integer> conteoTemporal) {
    // Limpiar las listas antes de procesar
    categorias.clear();

    // Lista de todas las categorías posibles
    ArrayList<String> todasLasCategorias = new ArrayList<>();
    todasLasCategorias.add("Construcción");
    todasLasCategorias.add("Alimentos");
    todasLasCategorias.add("Hogar");

    // Inicializar el conteo de todas las categorías a 0
    for (String categoria : todasLasCategorias) {
        categorias.add(categoria);
    }

    // Procesar la lista de productos
    for (Producto producto : productos) {
        String categoria = producto.getCategoria(); // Obtener la categoría del producto

        if (!categorias.contains(categoria)) {
            // Si no existe, agregarla
            categorias.add(categoria);
        }
    }

    // Construir el resultado
    StringBuilder resultado = new StringBuilder();
    for (int i = 0; i < categorias.size(); i++) {
        resultado.append(categorias.get(i))
                 .append(": ")
                 .append(conteoTemporal.get(i))
                 .append("/");

        // Dependiendo de la categoría, agregar la capacidad máxima correspondiente
        switch (categorias.get(i)) {
            case "Construcción":
                resultado.append(capacidadMaximaMaterial);
                break;
            case "Alimentos":
                resultado.append(capacidadMaximaConsumible);
                break;
            case "Hogar":
                resultado.append(capacidadMaximaLimpieza);
                break;
            default:
                resultado.append("N/A");
                break;
        }

        resultado.append(" productos\n");
    }
    return resultado.toString();
}
//Funcionalidad a la que pertenece: Abastecer tiendas
//Metodo que se encarga de calcular la cantidad actual de por su categoria
public int getCantidadActualPorCategoria(String categoria) {
    int cantidad = 0;
    for (Producto producto : this.listaProducto) {
        if (producto.getCategoria().equals(categoria)) {
            cantidad++;
        }
    }
    return cantidad;
}
//Funcionalidad a la que pertenece: Devoluciones
public Cliente devolverProducto(Factura factura, Producto producto){
    productosDevueltos.add(producto);
    
    producto.setEstado(estadosProducto.DEVUELTO);
    productosDevueltos.add(producto);
    return factura.getCliente();
}

/**
  * Funcionalidad: Devoluciones
  * Método principal para gestionar los productos seleccionados para un cambio.
  * 
  * @param valor              El valor máximo permitido para el cambio.
  * @param seleccionProductos Lista de índices seleccionados por el cliente.
  * @return ArrayList de productos seleccionados para el cambio.
  */
  public ArrayList<Producto> agregarProductosParaCambio(double precioCambio, ArrayList<Integer> seleccionProductos, ArrayList<Producto> productosDisponibles) {
    ArrayList<Producto> productosSeleccionados = new ArrayList<>();
    double subtotal = 0;

    for (int indice : seleccionProductos) {
        // Validar índice y obtener el producto correspondiente
        if (indice < 1 || indice > productosDisponibles.size()) {
            continue; // Ignorar índices inválidos
        }

        Producto productoSeleccionado = productosDisponibles.get(indice - 1); //Probar con indice normal. 

        // Evitar agregar productos duplicados
        if (!productosSeleccionados.contains(productoSeleccionado)) {
            productosSeleccionados.add(productoSeleccionado);
            subtotal += productoSeleccionado.getPrecio();
        }

        // Verificar si el subtotal supera el precio permitido después de añadir al menos un producto
        if (subtotal > precioCambio) {
            System.out.println("El subtotal ha excedido el valor límite después de añadir: " + productoSeleccionado.getNombre());
            break;
        }
    }

    return productosSeleccionados;
}

    
    
//Funcionalidad a la que pertencece: Devoluciones 

//Método que se encarga de filtrar los productos que puede seleccionar el usuario para cambiar
//Devuelve un ArrayList con los productos disponibles para la venta de la tienda, menos el producto que desea cambiar y mostrando primero los productos
//de la misma categoria que el que se desea cambiar. 

public ArrayList<Producto> mostrarProductos(Producto producto) {
    ArrayList<Producto> productosParaMostrar=new ArrayList<>();
    for (Producto p : this.getListaProducto()) {
        // Comparar por ID para omitir el producto seleccionado
        if (p.getId()==(producto.getId())) {
            continue;
        }
        else{
            productosParaMostrar.add(p);
        }
    }
    return productosParaMostrar;
}
//Funcionalidad a la que pertenece: Abastecer tiendas
//Metodo que se encarga de mostrar los productos de la TIENDA de forma ordenada(producto:cantidad)
public String cantidadProductos() {
    // Lista para almacenar los nombres de los productos ya contados
    ArrayList<String> nombresContados = new ArrayList<>();
    // Construir el resultado
    StringBuilder resultado = new StringBuilder();

    for (Producto producto : listaProducto) {
        if (!nombresContados.contains(producto.getNombre())) {
            int cantidad = 0;
            for (Producto p : listaProducto) {
                if (p.getNombre().equals(producto.getNombre())) {
                    cantidad++;
                }
            }
            nombresContados.add(producto.getNombre());
            resultado.append(producto.getNombre())
                     .append(": ")
                     .append(cantidad)
                     .append(" unidades\n");
        }
    }

    return resultado.toString(); // Retorna el resultado 
}
//Funcionalidad a la que pertenece: Abastecer tiendas
//Metodo que se encarga de descargar los productos de un transporte a la tienda
public void descargarProducto(Transporte transporteSeleccionado) {
    ArrayList<Producto> productosTransportados = transporteSeleccionado.getListaDeProductos();
    for (Producto producto : productosTransportados) {
        this.listaProducto.add(producto);
    }
    transporteSeleccionado.getListaDeProductos().clear(); // Vaciar la lista de productos del transporte
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


