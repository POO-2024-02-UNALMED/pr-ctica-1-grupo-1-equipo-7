package produccion;

public class Producto {
    public String nombre;
    int precio;
    private int cantidad;
    private int id; 
    private static int totalCreados=0;
    public String estado; //disponible,vendido,devuelto
    private String tipo;
    private String categoria; 
    private double peso; 
    private double tamano;
    private String motivoDevolucion=null; //No borren los atributos de los demas.
    private boolean devuelto; // Tipo de producto //?
    //Constructores: 
    public Producto(String nombre, int precio, int cantidad, int id, String estado, String tipo, String categoria){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
        this.categoria = categoria;
        totalCreados++;
        
    }
    public Producto(){}

    //getters y setters
    public String getNombre() {
        return nombre; }
    public void setNombre(String nombre){
            this.nombre = nombre;
     }
   
    public String getEstado() {
        return estado;
    }
   
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getPrecio() {
        return this.precio;
    }
    public void setPrecio(int precio){
        this.precio = precio;
    }
    public int getCantidad(){
        return this.cantidad;
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
    public double getTamano(){
        return this.tamano;
    }
    public void setTamano(double tamano){
        this.tamano = tamano;
    }
}