package gestion;
import base.Persona;
import produccion.Fabrica;
import gestion.CuentaBancaria;


public class Operario extends Persona {
    private Fabrica fabrica;

    public Operario(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica){
        super(nombre, cedula, edad, cuentaBancaria);
        this.fabrica = fabrica;
    }

    public void recibirSueldo(int valor){
        this.cuentaBancaria.añadirDinero(valor);  //SOLUCIONAR PROBLEMA CON EL METODO AÑADIR DINERO
    }
}
