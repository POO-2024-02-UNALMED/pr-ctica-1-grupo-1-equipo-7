package gestion;
import produccion.Fabrica;


public class Operario extends Persona {
    private Fabrica fabrica;

    public Operario(String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Fabrica fabrica){
        super(nombre, cedula, edad, cuentaBancaria);
        this.fabrica = fabrica;
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
}
