package gestion;
import produccion.Tienda;
import java.util.ArrayList;

public class Vendedor extends Persona {
    private Tienda tienda;
    private static ArrayList<Vendedor> listaVendedores = new ArrayList<>();
    private ArrayList<Meta> metaVendedor;

    public Vendedor (String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria){
        super(nombre, cedula, edad, cuentaBancaria);
        this.metaVendedor = new ArrayList<>();
        listaVendedores.add(this);
    }
    public Vendedor() {
        //TODO Auto-generated constructor stub
    }

    //Metodo de la funcionalidad pago trabajdores que devuelve un String con todas las metas que no se han cumplido 
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

    //sobreescritura del metod de personas para la funcionalidad pago a trabajadores
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
    public static ArrayList<Vendedor> getListaVendedores(){
        return Vendedor.listaVendedores;
    }
    public void aumentarCargaTrabajo(){
        this.cantidadTrabajo += 1;
    }
    public void aumentarIndiceMeta(){
        setIndiceMeta(getIndiceMeta()+1);;
    }
}
