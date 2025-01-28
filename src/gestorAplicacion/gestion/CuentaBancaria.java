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
    //añade dinero a la cuenta de banco hace parte de la funcionalidad pago trabajdores
    public void añadirDinero(double valor){
        this.saldo += valor;
    }

    //Quita dinero a la cuenta de banco hace parte de la funcionalidad pago trabajdores
    public void descontarDinero(double valor){
        this.saldo -= valor;
    }
    
    public void transferirDinero(double valor, CuentaBancaria cuentaDestino){
        this.saldo -= valor;
        cuentaDestino.añadirDinero(valor);
    }

    /*Metodo de la funcion Pago trabajadores donde se ingresa una persona como parametro 
     * y se verifica que tipo de trabajdor es para asi calcular elpago potencial con base al número de veces que se haya trabajado
     */
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

