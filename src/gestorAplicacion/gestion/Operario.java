package gestion;
import java.util.ArrayList;
import produccion.Fabrica;


public class Operario extends Persona {
    private Fabrica fabrica;
    private ArrayList<Meta> metaOperario;

    public Operario(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica){
        super(nombre, cedula, edad, cuentaBancaria);
        this.fabrica = fabrica;
        this.metaOperario = new ArrayList<>();
    }

    public Operario() {
        //TODO Auto-generated constructor stub
    }

    @Override
    public String toString(){
        String texto = "Nombre:" + this.getNombre() +
                       "\nCedula:" + this.getCedula() +     //toString Operario
                       "\nEdad:" + this.getEdad() +
                       "\nFabrica" + this.getFabrica();
        return texto;
    }
    
    public void recibirSueldo(double valor){
        this.getCuentaBancaria().añadirDinero(valor);
    }

    // Getter y Setter 'Fabrica'
    public void setFabrica(Fabrica fabrica){
        this.fabrica = fabrica;
    }

    public Fabrica getFabrica(){
        return this.fabrica;
    }

    //Getter y Setter 'metaOperario'
    public ArrayList<Meta> getmetaOperario(){
        return this.metaOperario;
    }

    public void setOperario(Meta meta){     
        this.metaOperario.add(meta);          
    }
}
