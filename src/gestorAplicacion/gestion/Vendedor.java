package gestion;
import produccion.Tienda;
import produccion.Producto;
import produccion.Transporte;
//Se añaden dos importaciones para usar en el metodo 'crearFactura'
import java.util.ArrayList;
import java.time.LocalDate;

public class Vendedor extends Persona {
    private Tienda tienda;
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

    }

    @Override
    public String toString(){
        String texto = "Nombre:" + this.getNombre() +
                       "\nCedula:" + this.getCedula() +
                       "\nEdad:" + this.getEdad() +
                       "\nTienda" + this.getTienda();
        return texto;
    }

        public void recibirSueldo(double valor){
            this.getCuentaBancaria().añadirDinero(valor);
    }

    // Getter y Setter 'Tienda'
    public void setTienda(Tienda tienda){
        this.tienda = tienda;
    }

    public Tienda getTienda(){
        return this.tienda;
    }

    //Getter 'metaVendedor'
    public ArrayList<Meta> getMetaVendedor(){
        return this.metaVendedor;
    }

    public void setVendedor(Meta meta){     
        this.metaVendedor.add(meta);          
    }

    public Factura crearFactura(Tienda tienda, Cliente cliente, Transporte transporte, ArrayList<Producto> productos, LocalDate fecha){
        Factura factura = new Factura(tienda, cliente, transporte, productos, fecha);
        return factura;
    }

}
