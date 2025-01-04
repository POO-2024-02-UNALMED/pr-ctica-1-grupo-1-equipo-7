package gestion;
import produccion.Tienda;
import java.util.ArrayList;

public class Vendedor extends Persona {
    private Tienda tienda;
    private ArrayList<Meta> metaVendedor;

     public Vendedor (String nombre,int cedula, int edad, CuentaBancaria cuentaBancaria, Tienda tienda){
        super(nombre, cedula, edad, cuentaBancaria);
        this.tienda = tienda;
        this.metaVendedor = new ArrayList<>();
        
    }
        public void recibirSueldo(double valor){
            this.getCuentaBancaria().añadirDinero(valor);
    }
}
