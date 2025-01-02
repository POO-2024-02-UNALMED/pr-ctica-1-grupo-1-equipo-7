package produccion;

import java.util.List;

public class Fabrica {

    // Atributos
    private String idFabrica;
    private String nombre;
    private String direccion;
    private static CuentaBancaria cuentaBancaria;
    private static Operario operario;
    private List<Producto> productosDisponibles;

    // Constructor
    public Fabrica(String idFabrica, String nombre, String direccion, CuentaBancaria cuentaBancaria, Operario operario, List<Producto> productosDisponibles) {
        this.idFabrica = idFabrica;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuentaBancaria = cuentaBancaria;
        this.operario = operario;
        this.productosDisponibles = productosDisponibles;
    }
    public Fabrica() {}

    //Metodos 
    descontarDineroCuenta(Producto producto){
        double precio = producto.getPrecio();
        double nuevoSaldo= Fabrica.cuentaBancaria.getSaldo();
    }


    // Getters y Setters

    public String getIdFabrica() {
        return idFabrica;
    }

    public void setIdFabrica(String idFabrica) {
        this.idFabrica = idFabrica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public CuentaBancaria getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        this.operario = operario;
    }

    public List<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public void setProductosDisponibles(List<Producto> productosDisponibles) {
        this.productosDisponibles = productosDisponibles;
    }
}

