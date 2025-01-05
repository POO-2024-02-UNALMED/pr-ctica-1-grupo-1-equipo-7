package produccion;

public class Producto {
    public String nombre;
    private int precio;
    private int cantidad;
    private int id; 
    private static int totalCreados=0;
    public String estado; // Disponible, vendido, devuelto
    private String tipo;// Tipo de producto

    public Producto(String nombre, int precio, int cantidad, int id, String estado, String tipo){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.id = id;
        this.estado = estado;
        this.tipo = tipo;
        totalCreados++;

    }

    //getters y setters
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public String getEstado() {
        return this.estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getPrecio(){
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

}