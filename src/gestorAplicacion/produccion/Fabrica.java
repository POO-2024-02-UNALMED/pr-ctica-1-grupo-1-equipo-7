package produccion;

import java.util.ArrayList;
import java.util.List;
import gestion.CuentaBancaria;
import gestion.Operario;
import gestion.Persona;

public class Fabrica {

    // Atributos
    private  String idFabrica;
    private  String nombre;
    private  String direccion;
    public static  CuentaBancaria cuentaBancaria;  //se quito el static ya que solo existe 1 fabrica no hace falta que el atributo sea de clase
                                                //RTA: Para la de devoluciones necesito que sea static:). Att: Andres.
    private  Operario operario;       //lo mismo para operario 
    private  List<Producto> productosDisponibles;
    private static ArrayList<Tienda> listaTienda = new ArrayList<Tienda>();

    // Constructor
    public Fabrica(String idFabrica, String nombre, String direccion, CuentaBancaria cuentaBancariaFabrica, Operario operario, List<Producto> productosDisponibles) {
        this.idFabrica = idFabrica;
        this.nombre = nombre;
        this.direccion = direccion;
        cuentaBancaria = cuentaBancariaFabrica;
        this.operario = operario;
        this.productosDisponibles = productosDisponibles;
    }
    public Fabrica() {}

    //Metodos:

    //Funcionalidad a la que pertenece: Pago trabajadores
    //Se encarga de devolver una lista de las personas las cuales han realizado su trabajo para poder pagarles.

    public static ArrayList<Persona> busquedaTrabajo(ArrayList<Persona> listaTrabajadores){
        ArrayList<Persona> trabajadores = new ArrayList<>();
        for(Persona e : listaTrabajadores){
            if(e.getCantidadTrabajo() > 0){
                trabajadores.add(e);
            }
        }
        return trabajadores;
    }

    public static String mostrarPersonas(ArrayList<Persona> listaTrabajadores){
        String texto = "";
        int indice = 1;

        for (Persona i: listaTrabajadores) {
            texto += "\n" + "Trabajador "+ indice + i.toString();  //Uso de ligadura dinámica
            indice++;               
        }

        return texto;
    }

    //Funcionalidad a la que pertenece: Devoluciones 
    //Método que se encarga de descontar el dinero de la cuenta bancaria de la fábrica cuando se realiza una devolución 
    // y retorna el precio del producto que se va a devolver.
    
    public static double descontarDineroCuenta(Producto producto){
        double precio = producto.getPrecio();
        double nuevoSaldo= Fabrica.cuentaBancaria.getSaldo()-precio;
        Fabrica.cuentaBancaria.setSaldo(nuevoSaldo);
        return precio; 
    }
    public void AgregarListaTienda(Tienda tienda){
        if (tienda != null && !listaTienda.contains(tienda)) { // Validación para evitar duplicados
            listaTienda.add(tienda);
        } else {
            System.out.println("La tienda ya está registrada o es nula.");
        }
    }

       public static String mostrarTiendas() {
        String resultado = "Listado de Tiendas:\n";
        for (int i = 0; i < listaTienda.size(); i++) {
            resultado += (i + 1) + ". " + listaTienda.get(i).getNombre() + "\n";
        }
        return resultado;
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

    public void setCuentaBancaria(CuentaBancaria nuevaCuentaBancaria) {
        cuentaBancaria = nuevaCuentaBancaria;
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
          public static ArrayList<Tienda> getListaTiendas(){
            return listaTienda;
    }
    public static ArrayList<Tienda> getListaTienda(){
        return listaTienda;
    }
}

