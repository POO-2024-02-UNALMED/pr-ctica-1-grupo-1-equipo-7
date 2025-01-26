package gestion;
import java.util.ArrayList;
import produccion.Fabrica;


public class Operario extends Persona {
    private Fabrica fabrica;
    private static ArrayList<Operario> listaOperario = new ArrayList<>();
    private ArrayList<Meta> metaOperario;

    public Operario(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica){
        super(nombre, cedula, edad, cuentaBancaria);
        this.fabrica = fabrica;
        this.metaOperario = new ArrayList<>();
        listaOperario.add(this);
    }

    public Operario() {
        //TODO Auto-generated constructor stub
    }

    public String mostrarMetas(){
        String texto = "";
        int indice = 1;

        for (Meta i: this.metaOperario) {
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
                       "\nCedula:" + this.getCedula() +     //toString Operario
                       "\nEdad:" + this.getEdad() +
                       "\nFabrica" + this.getFabrica();
        return texto;
    }
    
    public void recibirSueldo(double valor){
        this.getCuentaBancaria().añadirDinero(valor);
        this.setCantidadTrabajo(0);
    }

    // Getter y Setter 'Fabrica'
    public void setFabrica(Fabrica fabrica){
        this.fabrica = fabrica;
    }

    public Fabrica getFabrica(){
        return this.fabrica;
    }

    //Getter y Setter 'metaOperario'
    public ArrayList<Meta> getMeta(){
        return this.metaOperario;
    }

    public void setMetaOperario(Meta meta){     
        this.metaOperario.add(meta);          
    }

    //Getter y Setter 'listaOperario'
    public static ArrayList<Operario> getListaOperario(){
        return Operario.listaOperario;
    }

}

