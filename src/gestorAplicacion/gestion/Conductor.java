package gestion;
import produccion.Transporte;
import java.util.ArrayList;
import produccion.Fabrica;

public class Conductor extends Persona {
    private Transporte transporte;
    private Fabrica fabrica;
    private static ArrayList<Conductor> listaConductores = new ArrayList<>();
    private ArrayList<Meta> metaConductor;

    public Conductor(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica, Transporte transporte){
        this.transporte = transporte;
        this.fabrica = fabrica;
        this.metaConductor = new ArrayList<>();
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

    public void setMetaConductor(ArrayList<Meta> metaConductor) { 
        this.metaConductor = metaConductor;
    }
}

