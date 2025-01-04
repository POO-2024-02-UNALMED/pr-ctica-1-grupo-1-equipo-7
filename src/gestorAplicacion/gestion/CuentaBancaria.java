package gestion;

public class CuentaBancaria {
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

    public int calcularPago(Persona persona){ // por cada trabajo se le sumaran 7000 al salario base
        int saldoTrabajo = persona.getCantidadTrabajo() * 7000;
        return saldoTrabajo;
    }

    public void devolverDinero(double total, Cliente cliente){
        cliente.getCuentaBancaria().añadirDinero(total);                //REVISAR ESTE METODO DESPUES
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

