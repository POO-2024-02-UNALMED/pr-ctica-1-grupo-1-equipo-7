package gestion;
import produccion.Tienda;
import produccion.Producto;
import produccion.Transporte;
//Se añaden dos importaciones para usar en el metodo 'crearFactura'
import java.util.ArrayList;
import java.time.LocalDate;

public class Vendedor extends Persona {
    private Tienda tienda;
    private static ArrayList<Persona> listaVendedores = new ArrayList<>();
    private ArrayList<Meta> metaVendedor;

    //Se coloca de esta forma para que el constructor de la clase 'Persona' pueda ser llamado desde un objeto de tipo 'Tienda'
    public Vendedor (String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria){
        super(nombre, cedula, edad, cuentaBancaria);
        this.metaVendedor = new ArrayList<>();

    }

     public Vendedor (String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Tienda tienda){
        super(nombre, cedula, edad, cuentaBancaria);
        this.tienda = tienda;
        this.metaVendedor = new ArrayList<>();
        listaVendedores.add(this);

    }

    public Vendedor() {
        //TODO Auto-generated constructor stub
    }

    public String mostrarMetas(){
        String texto = "";
        int indice = 1;

        for (Meta i: this.metaVendedor) {
            if(i.getVerificador() == false){
                texto += "\n" + "Meta "+ indice + i.toString() + "\n";  //Uso de ligadura dinámica
                indice++;  
            }             
        }

        return texto;
    }
   
    @Override
    public String toString(){
        String texto = "\nNombre:" + this.getNombre() +
                       "\nCedula:" + this.getCedula() +
                       "\nEdad:" + this.getEdad() +
                       "\nTienda" + this.getTienda();
        return texto;
    }

        public void recibirSueldo(double valor){
            this.getCuentaBancaria().añadirDinero(valor);
            this.setCantidadTrabajo(0);
    }

    // Getter y Setter 'Tienda'
    public void setTienda(Tienda tienda){
        this.tienda = tienda;
    }

    public Tienda getTienda(){
        return this.tienda;
    }

    //Getter 'metaVendedor'
    public ArrayList<Meta> getMeta(){
        return this.metaVendedor;
    }

    public void setMetaVendedor(Meta meta){     
        this.metaVendedor.add(meta);          
    }

    public Factura crearFactura(Tienda tienda, Cliente cliente, Transporte transporte, ArrayList<Producto> productos, LocalDate fecha){
        Factura factura = new Factura(tienda, cliente, transporte, productos, fecha);
        return factura;
    }

    //Getter y Setter 'listavendedores'
    public static ArrayList<Persona> getListaVendedores(){
        return Vendedor.listaVendedores;
    }
}
