package base; 
public abstract class Persona{
    public String nombre; 
    private int cedula; 
    protected int edad;
    
    public Persona(){}//Jose Luis:Publico para que las clases hijas puedan tener el constructor sin parametros

    Persona(String nombre,int cedula){
        this.nombre=nombre;
        this.cedula=cedula;
    }

}