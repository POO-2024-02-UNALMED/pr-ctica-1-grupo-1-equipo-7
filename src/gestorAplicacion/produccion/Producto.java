package produccion;

public class Producto {
    public String nombre;
    int precio;
    private int cantidad;
    private int id; 
    private static int totalCreados=0;
    public String estado; //disponible,vendido,devuelto
    private String tipo; // Tipo de producto
    //Constructores: 
    public Producto(String nombre, int precio, int cantidad, int id, String estado, String tipo){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
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
}