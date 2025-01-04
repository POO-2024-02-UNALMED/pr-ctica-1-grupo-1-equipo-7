package gestion;
import base.Persona;
import produccion.Fabrica;

public class Vendedor extends Persona {
     public Vendedor (String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica){
        super(nombre, cedula, edad, cuentaBancaria);

    
    }
}
