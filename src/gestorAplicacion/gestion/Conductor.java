package gestion;
import produccion.Transporte;
import java.util.ArrayList;
import produccion.Fabrica;

public class Conductor extends Persona {
    private Transporte transporte;
    private Fabrica fabrica;
    private static ArrayList<Conductor> listaConductores = new ArrayList<>();
    private ArrayList<Meta> metaConductor;
    private int pesoTransportado;

    public Conductor(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica, Transporte transporte){
        this.transporte = transporte;
        this.fabrica = fabrica;
        this.metaConductor = new ArrayList<>();
        this.pesoTransportado=0;

    }


    @Override
    public String toString(){
        String texto = "Nombre:" + this.getNombre() +
                       "\nCedula:" + this.getCedula() +
                       "\nEdad:" + this.getEdad() +
                       "\nTransporte" + this.getTransporte();
        return texto;
    }

    public void recibirSueldo(double valor){
        this.getCuentaBancaria().añadirDinero(valor);
    }


    //Getter y Setters

    // Para el atributo transporte
    public Transporte getTransporte() { 
        return transporte;
    }

    public void setTransporte(Transporte transporte) { 
        this.transporte = transporte;
    }

    // Para el atributo fabrica
    public Fabrica getFabrica() { 
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) { 
        this.fabrica = fabrica;
    }

    // Para el atributo listaConductores (static) solo Getter
    public static ArrayList<Conductor> getListaConductores() { 
        return listaConductores;
    }

    // Para el atributo metaConductor
    public ArrayList<Meta> getMetaConductor() { 
        return metaConductor;
    }

    public void setConductor(Meta meta){     
        this.metaConductor.add(meta);          
    }
    public void setpesoTransportado(int peso){
        this.pesoTransportado=peso;
    }
    public int getpesoTransportado(){
        return this.pesoTransportado;
    }
}

