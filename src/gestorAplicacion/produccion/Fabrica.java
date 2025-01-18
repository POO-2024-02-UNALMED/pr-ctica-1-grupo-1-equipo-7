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
    private static Operario operario;       //lo mismo para operario 
    private static List<Producto> productosDisponibles; //se traen todos productos que pueden ser producidos en la fabrica a partir de una lista que tiene la cual se le pasa en el constructor de la fábrica la cual contiene todos los productos que puede abastecer 
    private static ArrayList<Tienda> listaTienda = new ArrayList<Tienda>();

    // Constructor
    public Fabrica(String idFabrica, String nombre, String direccion, CuentaBancaria cuentaBancariaFabrica, Operario operario, List<Producto> productosDisponibles) {
        this.idFabrica = idFabrica;
        this.nombre = nombre;
        this.direccion = direccion;
        cuentaBancaria = cuentaBancariaFabrica;
        Fabrica.operario = operario;
        Fabrica.productosDisponibles = productosDisponibles;
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
            texto += "\n" + "Trabajador "+ indice + i.toString()+"\n";  //Uso de ligadura dinámica
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

    //Funcionalidad a la que pertenece: Devoluciones
    //Método que calcula el excedente que debe pagar el cliente (si debe hacerlo) al realizar un cambio de productos.
    public static double calcularExcedente(ArrayList<Producto> productos, double valor) {
        double subtotal = 0;
        for (Producto producto : productos) {
            subtotal += producto.getPrecio();
        }
        if (subtotal <= valor) {
            return 0;
        }
        return subtotal - valor;
    }


    public void AgregarListaTienda(Tienda tienda){
        if (tienda != null && !listaTienda.contains(tienda)) { // Validación para evitar duplicados
            listaTienda.add(tienda);
        } else {
            System.out.println("La tienda ya está registrada o es nula.");
        }
    }

    public static String mostrarTiendas() {
        if (listaTienda.isEmpty()) {
            return "No hay tiendas disponibles.";
        }

        StringBuilder resultado = new StringBuilder("Listado de Tiendas:\n");
        for (int i = 0; i < listaTienda.size(); i++) {
            Tienda tienda = listaTienda.get(i);
            resultado.append((i + 1)).append(". ").append(tienda.getNombre()).append("\n");
            resultado.append("Productos actuales:\n");
            resultado.append(tienda.cantidadProductos()).append("\n"); // Usar el nuevo método cantidadProductos
        }
        return resultado.toString();
    }
    public static String mostrarProductos() {//Método que se encarga de mostrar los productos disponibles en la fábrica.
        String productos = "";
        if (Fabrica.productosDisponibles == null || Fabrica.productosDisponibles.isEmpty()) {
            return "No hay productos registrados o disponibles.";
        }
        for (Producto producto : Fabrica.productosDisponibles) {
            productos += producto.getNombre() + "\n";
        }
        return productos;
    }
       public static ArrayList<Producto> cantidadProductos(Producto producto, int cantidadAEnviar) {
        ArrayList<Producto> productosGenerados = new ArrayList<>();
        for (int i = 0; i < cantidadAEnviar; i++) {
            productosGenerados.add(new Producto(producto)); // Crear una nueva instancia de Producto
        }
        return productosGenerados;
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

    public static Operario getOperario() {
        return operario;
    }

    public void setOperario(Operario operario) {
        Fabrica.operario = operario;
    }

    public static List<Producto> getProductosDisponibles() {
        return productosDisponibles;
    }

    public static void setProductosDisponibles(List<Producto> productosDisponibles) {
        Fabrica.productosDisponibles = productosDisponibles;
    }
          public static ArrayList<Tienda> getListaTiendas(){
            return listaTienda;
    }
    public static ArrayList<Tienda> getListaTienda(){
        return listaTienda;
    }

}

