package produccion;
import gestion.Vendedor;
import gestion.Cliente;
import gestion.CuentaBancaria;
import gestion.Factura;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
//import java.util.Scanner;
public class Tienda {
    //atributos
    private String nombre;
    private Vendedor vendedor;
    private CuentaBancaria cuentaBancaria;
    private ArrayList<Producto> productosDevueltos;
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
public void descargarProducto(Transporte transporteSeleccionado) {
    ArrayList<Producto> productosTransportados = transporteSeleccionado.getListaDeProductos();
    for (Producto producto : productosTransportados) {
        this.listaProducto.add(producto);
    }
    transporteSeleccionado.getListaDeProductos().clear(); // Vaciar la lista de productos del transporte
}

public double venderProducto(Producto productoSeleccionado) {
    for (Producto producto : listaProducto) {
        if (producto.equals(productoSeleccionado)) {
            double pesoProducto = producto.getPeso(); // Obtener el peso antes de eliminar
            listaProducto.remove(producto);          // Eliminar el producto de la lista
            return pesoProducto;                     // Retornar el peso del producto
        }
    }
    return 0.0;                                      // Si no se encuentra, retorna 0.0
}

public ArrayList<ArrayList<Object>> listaProductosTienda() {
    ArrayList<ArrayList<Object>> listaProductos = new ArrayList<>();
    
    for (Producto producto : listaProducto) {  // Asegúrate de que 'listaProducto' esté correctamente definida
        ArrayList<Object> productoCantidad = new ArrayList<>();
        boolean encontrado = false;

        if (listaProductos.isEmpty()) {
            productoCantidad.add(producto);
            productoCantidad.add(1);  // Agregar el producto con cantidad 1
            listaProductos.add(productoCantidad);
        } else {
            for (ArrayList<Object> listaAux : listaProductos) {  // Iterar sobre las sublistas
                // Comparar los productos usando 'equals'
                if (listaAux.get(0).equals(producto)) {
                    // Incrementar la cantidad del producto
                    listaAux.set(1, (Integer) listaAux.get(1) + 1);  // Cambié a Integer
                    encontrado = true;
                    break;  // Salir del ciclo si ya encontramos el producto
                }
            }

            if (!encontrado) {
                productoCantidad.add(producto);  // Agregar el producto
                productoCantidad.add(1);         // Inicializar la cantidad en 1
                listaProductos.add(productoCantidad);  // Agregar a la lista
            }
        }
    }
    return listaProductos;  // Regresar la lista de productos y sus cantidades
}
public String mostrarListaProductosTienda(ArrayList<ArrayList<Object>> listaProductos) {
    if (listaProductos == null || listaProductos.isEmpty()) {
        return "Actualmente no hay productos registrados en el sistema.";
    }

    StringBuilder texto = new StringBuilder();
    int contador = 1;

    // Recorrer listaProductos para mostrar cada producto y su cantidad
    for (ArrayList<Object> listaAux : listaProductos) {
        Producto producto = (Producto) listaAux.get(0);  // Obtener el producto
        Integer cantidad = (Integer) listaAux.get(1);   // Obtener la cantidad

        texto.append(contador).append(". Producto: ").append(producto.getNombre()).append("\n")
             .append(" - Cantidad: ").append(cantidad).append("\n")
             .append(" - Peso: ").append(producto.getPeso()).append("\n\n");
        contador++;
    }

    return texto.toString().trim();
}
public String enviarPedido(ArrayList<Producto> listaProductosPedidos, Transporte transporteSeleccionado,Cliente clienteSeleccionado,LocalDate dia ){
    Factura factura = new Factura(this, clienteSeleccionado, transporteSeleccionado, listaProductosPedidos, dia);
    return factura.toString();
}
}


