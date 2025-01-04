package gestion;
import java.util.ArrayList;
import produccion.Producto;

public class Cliente extends Persona{
    public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();//Lista de clientes
    private ArrayList<Factura> listaFacturas;
    private ArrayList<Producto> listaProductos;
    private int id;
    private static int totalCreados=0;
    public Cliente(String nombre, int edad, int cedula, CuentaBancaria cuentaBancaria){
        super(nombre, edad, cedula, cuentaBancaria);
        listaFacturas = new ArrayList<Factura>();
        id = totalCreados;
        totalCreados++;
        listaClientes.add(this);
    }
    //getters y setters 
    public String getNombre(){ 
    return nombre;
    }
    public int getId(){
    return this.id;
    }
    public void setId(int nuevoId){ 
    this.id = nuevoId;
    }
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



