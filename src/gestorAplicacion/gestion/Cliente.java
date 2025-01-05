package gestion;
import java.util.ArrayList;
import produccion.Producto;

public class Cliente{
    public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();//Lista de clientes
    private ArrayList<Factura> listaFacturas;
    private ArrayList<Producto> listaProductos;
    private String nombre;
    private int edad;
    private int cedula;
    private CuentaBancaria cuentaBancaria;
    private int id;
    private static int totalCreados=0;
    public Cliente(String nombre, int edad, int cedula, CuentaBancaria cuentaBancaria){
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
        this.cuentaBancaria = cuentaBancaria;
        listaFacturas = new ArrayList<Factura>();
        this.id = totalCreados;
        totalCreados++;
        listaClientes.add(this);
    }
    //getters y setters 
    public String getNombre(){ 
    return this.nombre;
    }
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
    public void setCuentaBancatia(CuentaBancaria cuentaBancaria){
        this.cuentaBancaria = cuentaBancaria;
    }

    //metodos
    
    public static void mostrarClientes(){  //Imprime la lista de clientes, comenzando desde 1 para facilitar al usurario a la hora de escojer. La forma de imprimir es : 1.Cliente
    if (listaClientes.isEmpty()) {
        System.out.println("No hay clientes registrados.");
        return;
    }
    for (int i = 0; i < listaClientes.size(); i++) {
        System.out.println((i + 1) + ". " + listaClientes.get(i).getNombre());
        }
    }
}


