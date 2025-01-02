package base; 
public abstract class Persona{
    public String nombre; 
    private int cedula; 
    protected int edad;
    
    Persona(){}

    Persona(String nombre,int cedula){
        this.nombre=nombre;
        this.cedula=cedula;
    }

}