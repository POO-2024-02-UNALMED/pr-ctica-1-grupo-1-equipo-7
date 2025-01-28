package gestion;
import produccion.Transporte;
import java.util.ArrayList;
import produccion.Fabrica;

public class Conductor extends Persona {
    private static ArrayList<Conductor> listaConductores = new ArrayList<>();
    private Transporte transporte;
    private Fabrica fabrica;
    private ArrayList<Meta> metaConductor;
    private String licencia;

    public Conductor(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica, Transporte transporte){
        super(nombre,cedula,edad,cuentaBancaria);
        this.transporte = transporte;
        transporte.setConductor(this);        
        this.fabrica = fabrica;
        this.metaConductor = new ArrayList<>();
        listaConductores.add(this);
    }
    public Conductor(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica, Transporte transporte,String licencia){
    this(nombre,cedula,edad,cuentaBancaria,fabrica,transporte);
    this.licencia=licencia;
    
    }

   public Conductor(){}

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
        //transporte.setConductor(this);
    }

    // Para el atributo fabrica
    public Fabrica getFabrica() { 
        return fabrica;
    }

    public void setFabrica(Fabrica fabrica) { 
        this.fabrica = fabrica;
    }

    // Para el atributo listaConductores (static)
    public static ArrayList<Conductor> getListaConductores() { 
        return listaConductores;
    }

    public static void setListaConductores(ArrayList<Conductor> listaConductores) { 
        Conductor.listaConductores = listaConductores;
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
    public void aumentarCargaTrabajo(){
        this.cantidadTrabajo += 1;
    }
    public void aumentarIndiceMeta(double peso){
        setIndiceMeta(getIndiceMeta()+peso);;
    }
    // Getter para licencia
    public String getLicencia() {
        return licencia;
    }

    // Setter para licencia
    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }
}


