package gestion;
import java.util.ArrayList;
import produccion.Producto;
import gestion.CuentaBancaria;

public class Cliente {
    public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();//Lista de clientes
    private ArrayList<Factura> listaFacturas;
    public ArrayList<Producto> listaProductos;
    private int id;
    public CuentaBancaria cuentaBancaria;
    private static int totalCreados=0;
    private String nombre;
    private int edad;
    private int cedula;

    //Constructores
    public Cliente(String nombre, int edad, int cedula, CuentaBancaria cuentaBancaria){
            this.nombre = nombre;
            this.edad = edad;
            this.cedula = cedula;
            this.cuentaBancaria = cuentaBancaria;
            listaFacturas = new ArrayList<Factura>();
            id = totalCreados;
            totalCreados++;
            listaClientes.add(this);}
    public Cliente(){}

    //Métodos: 
    public static String mostrarClientes(){  //Imprime la lista de clientes, comenzando desde 1 para facilitar al usurario a la hora de escojer. La forma de imprimir es : 1.Cliente
        String texto = null;
        if (listaClientes.isEmpty()) {
            
            return "No hay clientes registrados.";
        }
        for (int i = 0; i < listaClientes.size(); i++) {
            texto += (i + 1) + ". " + listaClientes.get(i).getNombre() + "\n";
            }
        return texto;
    }
    public void removerProducto(Producto producto){ //Remueve un producto de la lista de productos del cliente.  Funcionalidad a la que pertenece: Devoluciones
        this.listaProductos.remove(producto);
    }
    
    //getters y setters 
    public String getNombre(){ 
    return nombre;}
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public int getCedula() {
        return cedula;
    }
    
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public int getId(){
    return this.id;
    }
    public void setId(int nuevoId){ 
    this.id = nuevoId;
    }
    public CuentaBancaria getCuentaBancaria(){
    return cuentaBancaria;
    }
    public void setCuentaBancaria(CuentaBancaria cuentaBancaria){
        this.cuentaBancaria = cuentaBancaria;
    }
    public static ArrayList<Cliente> getListaClientes(){
        return listaClientes;
    }

}



