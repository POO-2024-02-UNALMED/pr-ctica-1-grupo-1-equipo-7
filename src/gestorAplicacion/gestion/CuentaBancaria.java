package gestion;
import base.Persona;

public class CuentaBancaria {
    private int numeroCuenta;
    private int saldo;

    //constructores
    public CuentaBancaria (int numeroCuenta){
        this.numeroCuenta = numeroCuenta;
        this.saldo = 0;
    }
    
    public CuentaBancaria (int numeroCuenta, int saldo){
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldo;
    }

    
    //metodos
    public void añadirDinero(int valor){
        this.saldo += valor;
    }

    public void descontarDinero(int valor){
        this.saldo -= valor;
    }

    public int calcularPago(Persona persona){ // por cada trabajo se le sumaran 10000 al salario base
        int saldoTrabajo = persona.getCantidadTrabajo() * 10000;
        return saldoTrabajo;
    }

    public void devolverDinero(double total, Cliente cliente){
        cliente.cuentaBancaria.añadirDinero(total);                 //REVISAR ESTE METODO DESPUES
    }


    //Getters y Setters

    public int getNumeroCuenta(){
        return this.numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta){
        this.numeroCuenta = numeroCuenta;
    }

    //Getter y Setter 'saldo'

    public int getSaldo(){
        return this.saldo;
    }

    public void setSaldo(int saldo){
        this.saldo = saldo;
    }
}

