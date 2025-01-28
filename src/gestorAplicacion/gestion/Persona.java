
package gestion;

import java.io.Serializable;
import java.util.ArrayList;


public abstract class Persona implements Serializable{
    private static final long serialVersionUID = 3L;

    private static final int SALARIOBASE = 10000;  //Salario base que despues se modifica por cantidad de veces trabajadas y por bonos 

    private String nombre; 
    private int cedula; 
    private int edad;
    public int cantidadTrabajo; // debe incrementar cada que realice un trabajo, 
                                // tener encuenta para las funcionalidades que interactuen con los trabajadores
    private CuentaBancaria cuentaBancaria;
    private double indiceMeta;
    private static int personasTotales = 0;
    private static ArrayList<Persona> listaPersonas = new ArrayList<>();
                                  


    protected Persona(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria){
        this.nombre=nombre;
        this.cedula=cedula;
        this.edad = edad;
        this.cuentaBancaria = cuentaBancaria;
        this.cantidadTrabajo = 0;
        this.indiceMeta = 0;
        Persona.personasTotales += 1;
        Persona.listaPersonas.add(this);

    }

    public Persona(){}

    public abstract void recibirSueldo(double valor);  // se agrego el parametro para que no generara error en las clases hijas

    public abstract String mostrarMetas();

    public abstract ArrayList<Meta> getMeta();

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

    // Getter para personasTotales
    public static int getPersonasTotales() {
        return personasTotales;
    }

    // Getter para listaPersonas
    public static ArrayList<Persona> getListaPersonas() {
        return listaPersonas;
    }
}