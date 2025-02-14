package produccion;

import gestion.Moda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


public class Producto implements Moda, Serializable{
    private static final long serialVersionUID = 7L;

    //Atributos
    public String nombre;
    int precio;
    private int cantidad;
    private int id=0; 
    private static int totalCreados=0;
    private static ArrayList<Producto> listaProductos = new ArrayList<Producto>(); 
    public estadosProducto estado; //disponible,vendido,devuelto
    private String tipo;
    private String categoria; 
    private double peso; 
    private String motivoDevolucion=null; //No borren los atributos de los demas.
    private boolean devuelto; // Tipo de producto //?
    public static ArrayList<String> motivosDevolucion  = new ArrayList<>(Arrays.asList(
            "No llego a tiempo",
            "Llego en mal estado",
            "Se envio el producto equivocado",
            "El cliente cambió de opinión con la compra",
            "El producto no era lo que esperaba", 
            "Otro motivo"
        ));
    //Constructores: 
    public Producto(String nombre, int precio,  estadosProducto estado, String tipo, String categoria,double peso){
        this.nombre = nombre;
        this.precio = precio;
        this.estado = estado;
        this.tipo = tipo;
        this.categoria = categoria;
        this.peso=peso;
        this.id=totalCreados;
        Producto.listaProductos.add(this);
        totalCreados++;

    }
    
    //este es un constructor de copia para generar un nuevo producto con los mismos atributos de otro producto
    //funcionalidad a la que pertenece: Abastecer tiendas
    public Producto(Producto producto){
       this(producto.nombre,producto.precio,producto.estado,producto.tipo,producto.categoria,producto.peso);
    }

    public Producto(){
        totalCreados++;
    }


   // Metodos
   public static String mostrarMotivosDeDevolucion() {
    StringBuilder resultado = new StringBuilder();

    for (int i = 0; i < motivosDevolucion.size(); i++) {
        resultado.append(i + 1).append(". ").append(motivosDevolucion.get(i));
        if (i < motivosDevolucion.size() - 1) {
            resultado.append("\n"); // Agregar nueva línea excepto en el último elemento
        }
    }

    return resultado.toString();
    }
    public static String obtenerMotivoDeDevolucion(int index) {
        if (index < 1 || index > motivosDevolucion.size()) {
            return "Motivo no válido.";
        }
        return motivosDevolucion.get(index - 1);
    }

    //getters y setters
    public String getNombre() {
        return nombre; }

    public void setNombre(String nombre){
            this.nombre = nombre;
     }
   
    public estadosProducto getEstado() {
        return estado;
    }
   
    public void setEstado(estadosProducto estado) {
        this.estado = estado;
    }
    public int getPrecio() {
        return this.precio;
    }
    public void setPrecio(int precio){
        this.precio = precio;
    }

    public int getId(){
        return this.id;
    }
    public String getTipo(){
        return this.tipo;
    }
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public String getCategoria(){
        return this.categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }public boolean getDevuelto(){
        return this.devuelto;
    }public void setDevuelto(boolean devuelto){
        this.devuelto = devuelto;
    }
    public String getMotivoDevolucion(){
        return this.motivoDevolucion;
    }
    public void setMotivoDevolucion(String motivoDevolucion){
        this.motivoDevolucion = motivoDevolucion;
    }
    public double getPeso(){
        return this.peso;
    }
    public void setPeso(double peso){
        this.peso = peso;
    }

    public static ArrayList<Producto> getListaProductos(){
        return Producto.listaProductos;
    }

    public static void setListaProductos(ArrayList<Producto> listaProductos){
        Producto.listaProductos = listaProductos;
    }

  
    @Override
    public String toString(){
        return "Nombre: "+this.nombre+
        "\nPrecio: "+this.precio+
        "\nCantidad: "+this.cantidad+
        "\nId: "+this.id+
        "\nEstado: "+this.estado+
        "\nTipo: "+this.tipo+
        "\nCategoria: "+this.categoria;
    }
}