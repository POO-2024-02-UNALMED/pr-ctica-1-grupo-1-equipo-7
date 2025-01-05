package produccion;

import java.util.ArrayList;
import java.util.List;
import gestion.CuentaBancaria;
import gestion.Operario;

public class Fabrica {

    private ArrayList<Tienda>listaTienda =  new ArrayList<>();
    private String idFabrica;
    private String nombre;
    private String direccion;
    private CuentaBancaria cuentaBancaria;  //se quito el static ya que solo existe 1 fabrica no hace falta que el atributo sea de clase
    private Operario operario;       //lo mismo para operario 
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
    public void descontarDineroCuenta(Producto producto){//Este metodo si debe ir aca??
        double precio = producto.getPrecio();
        double nuevoSaldo= cuentaBancaria.getSaldo();
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
    public ArrayList<Tienda> getListaTiendas(){
        return listaTienda;
    }
    public void AgregarListaTienda(Tienda tienda){
        if (tienda != null && !listaTienda.contains(tienda)) { // Validación para evitar duplicados
            listaTienda.add(tienda);
        } else {
            System.out.println("La tienda ya está registrada o es nula.");
        }
    }
}

