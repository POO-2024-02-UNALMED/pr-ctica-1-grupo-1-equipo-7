package gestion;

import java.io.Serializable;

public class CuentaBancaria implements Serializable{
    private static final long serialVersionUID = 4L;

    //atributos
    private int numeroCuenta;
    private double saldo;

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
    public void añadirDinero(double valor){
        this.saldo += valor;
    }

    public void descontarDinero(double valor){
        this.saldo -= valor;
    }
    
    public void transferirDinero(double valor, CuentaBancaria cuentaDestino){
        this.saldo -= valor;
        cuentaDestino.añadirDinero(valor);
    }

    public int calcularPago(Persona persona) {
        int saldoTrabajo;
        if (persona instanceof Operario) {
            saldoTrabajo = persona.getCantidadTrabajo() * 6000;
        } else if (persona instanceof Vendedor) {
            saldoTrabajo = persona.getCantidadTrabajo() * 5000;
        } else {
            saldoTrabajo = persona.getCantidadTrabajo() * 4000;
        }
        return saldoTrabajo; 
    }

    public void devolverDinero(double total, Cliente cliente){
        cliente.getCuentaBancaria().añadirDinero(total);                
    }
    


    //Getters y Setters

    public int getNumeroCuenta(){
        return this.numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta){
        this.numeroCuenta = numeroCuenta;
    }

    //Getter y Setter 'saldo'

    public double getSaldo(){
        return this.saldo;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo; 
    }
}

