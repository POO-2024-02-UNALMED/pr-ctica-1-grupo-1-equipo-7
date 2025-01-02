package produccion;

public class Producto {
    public String nombre;
    private int precio;
    private int cantidad;
    private int id; 
    private static int totalCreados=0;
    public String estado; // Disponible, vendido, devuelto
    private String tipo; // Tipo de producto

    //getters y setters
    public String getNombre() {
        return nombre;
    }
    public String getEstado() {
        return estado;
    }
}