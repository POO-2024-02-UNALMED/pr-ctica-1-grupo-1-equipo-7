package gestion;
import produccion.Tienda;
import java.util.ArrayList;

public class Vendedor extends Persona {
    private Tienda tienda;
    private static ArrayList<Persona> listaVendedores = new ArrayList<>();
    private ArrayList<Meta> metaVendedor;

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

    //Getter y Setter 'listavendedores'
    public static ArrayList<Persona> getListaVendedores(){
        return Vendedor.listaVendedores;
    }
}
