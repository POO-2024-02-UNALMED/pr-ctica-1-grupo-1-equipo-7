package gestion;
import produccion.Transporte;
import java.util.ArrayList;
import produccion.Fabrica;

public class Conductor extends Persona {
    private Transporte transporte;
    private Fabrica fabrica;
    private static ArrayList<Persona> listaConductores = new ArrayList<>();
    private ArrayList<Meta> metaConductor;
    private int pesoTransportado;

    public Conductor(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica, Transporte transporte){
        this.transporte = transporte;
        this.fabrica = fabrica;
        this.metaConductor = new ArrayList<>();
        this.pesoTransportado=0;
        listaConductores.add(this);

    }

    public String mostrarMetas(){
        String texto = "";
        int indice = 1;

        for (Meta i: this.metaConductor) {
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
                       "\nTransporte" + this.getTransporte();
        return texto;
    }

    public void recibirSueldo(double valor){
        this.getCuentaBancaria().añadirDinero(valor);
        this.setCantidadTrabajo(0);
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
    public static ArrayList<Persona> getListaConductores() { 
        return listaConductores;
    }

    // Para el atributo metaConductor
    public ArrayList<Meta> getMeta() { 
        return metaConductor;
    }

    public void setMetaConductor(Meta meta){
        this.metaConductor.add(meta);
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

