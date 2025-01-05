
package base;
import gestion.CuentaBancaria;

public abstract class Persona{
    static final int salarioBase = 20000;  //Salario base que despues se modifica por cantidad de veces trabajadas y por bonos 

    public String nombre; 
    public int cedula; 
    protected int edad;
    public int cantidadTrabajo; // debe incrementar cada que realice un trabajo, 
                                // tener encuenta para las funcionalidades que interactuen con los trabajadores
    public CuentaBancaria cuentaBancaria;
                                  

    protected Persona(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria){
        this.nombre=nombre;
        this.cedula=cedula;
        this.edad = edad;
        this.cuentaBancaria = cuentaBancaria;
        this.cantidadTrabajo = 0;
    }

    public Persona(){}

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
}