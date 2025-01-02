package gestion;
import java.util.ArrayList;
import base.Persona;
import produccion.Producto;

 public class Cliente extends Persona{
    private ArrayList<Factura> listaFacturas;
    private ArrayList<Producto> listaProductos;
    private int id;
    private static int totalCreados=0;
    public Cliente(String nombre, int edad, int cedula, CuentaBancaria cuentaBancaria){
         super(nombre, edad, cedula, cuentaBancaria);
        listaFacturas = new ArrayList<Factura>();
        id = totalCreados;
        totalCreados++;
     }
    
     //getters y setters 
     public String getNombre(){
         return nombre;
     }
 }


