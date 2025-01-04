
package gestion;
import java.util.ArrayList;

public abstract class Persona{
    private static final int SALARIOBASE = 20000;  //Salario base que despues se modifica por cantidad de veces trabajadas y por bonos 

    private String nombre; 
    private int cedula; 
    protected int edad;
    public int cantidadTrabajo; // debe incrementar cada que realice un trabajo, 
                                // tener encuenta para las funcionalidades que interactuen con los trabajadores
    private CuentaBancaria cuentaBancaria;
    private double indiceMeta;
    private ArrayList<Boolean> verificadorMetasCumplidas;
    private static int personasTotales = 0;
    private static ArrayList<Persona> listaPersonas = new ArrayList<>();
                                  

    protected Persona(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria){
        this.nombre=nombre;
        this.cedula=cedula;
        this.edad = edad;
        this.cuentaBancaria = cuentaBancaria;
        this.cantidadTrabajo = 0;
        this.indiceMeta = 0;
        this.indiceMeta = 0;
        this.verificadorMetasCumplidas = new ArrayList<>();
        Persona.personasTotales += 1;
        Persona.listaPersonas.add(this);

    }

    public Persona(){}

    public abstract void recibirSueldo(double valor);  // se agrego el parametro para que no generara error en las clases hijas

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y Setter para 'cedula'
    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    // Getter y Setter para 'edad'
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Getter y Setter para 'cantidadTrabajo'
    public int getCantidadTrabajo() {
        return cantidadTrabajo;
    }

    public void setCantidadTrabajo(int cantidadTrabajo) {
        this.cantidadTrabajo = cantidadTrabajo;
    }

    // Getter y Setter para 'cuentaBancaria'
    public CuentaBancaria getCuentaBancaria(){
        return this.cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancaria cuentaBancaria){
        this.cuentaBancaria = cuentaBancaria;
    }

    // Getter 'SalarioBase'
    public int getSalarioBase(){
        return Persona.SALARIOBASE;
    }

    // Getter y Setter para indiceMeta
    public double getIndiceMeta() {
        return indiceMeta;
    }

    public void setIndiceMeta(double indiceMeta) {
        this.indiceMeta = indiceMeta;
    }

    // Getter y Setter para verificadorMetasCumplidas
    public ArrayList<Boolean> getVerificadorMetasCumplidas() {
        return verificadorMetasCumplidas;
    }

    public void setVerificadorMetasCumplidas(ArrayList<Boolean> verificadorMetasCumplidas) {
        this.verificadorMetasCumplidas = verificadorMetasCumplidas;
    }

    // Getter para personasTotales
    public static int getPersonasTotales() {
        return personasTotales;
    }

    // Getter para listaPersonas
    public static ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }
}