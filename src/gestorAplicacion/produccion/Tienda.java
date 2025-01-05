package produccion;
import gestion.Vendedor;
import gestion.CuentaBancaria;
import java.util.ArrayList;
public class Tienda {
    //atributos
    private String nombre;
    private Vendedor vendedor;
    private CuentaBancaria cuentaBancaria;
    private ArrayList<Producto> productosDevueltos;
    private int numTiendas; //no es necesario el static porque solo habran 3 tiendas
    private ArrayList<Producto> listaProducto;
    private ArrayList<Producto> cantidadProductos;//duda aqui del integer con el UML

    //falta un map y hashmap que sale en el UML pero no es correcto usarlos
    // constructor
    Tienda(String nombre,Vendedor vendedor, CuentaBancaria cuentaBancaria, int numTiendas){
        this.nombre=nombre;
        this.vendedor=vendedor;
        this.cuentaBancaria=cuentaBancaria;
        this.numTiendas=numTiendas;
        this.cantidadProductos=new ArrayList<>();
        this.listaProducto=new ArrayList<>();
        this.productosDevueltos=new ArrayList<>();
    
    //getters y setters


// Atributo nombre
public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

// Atributo vendedor
public Vendedor getVendedor() {
    return vendedor;
}

public void setVendedor(Vendedor vendedor) {
    this.vendedor = vendedor;
}

// Atributo cuentaBancaria
public CuentaBancaria getCuentaBancaria() {
    return cuentaBancaria;
}

public void setCuentaBancaria(CuentaBancaria cuentaBancaria) {
    this.cuentaBancaria = cuentaBancaria;
}

// Atributo productosDevueltos
public ArrayList<Producto> getProductosDevueltos() {
    return productosDevueltos;
}

public void setProductosDevueltos(ArrayList<Producto> productosDevueltos) {
    this.productosDevueltos = productosDevueltos;
}

// Atributo numTiendas
public int getNumTiendas() {
    return numTiendas;
}

public void setNumTiendas(int numTiendas) {
    this.numTiendas = numTiendas;
}

// Atributo listaProducto
public ArrayList<Producto> getListaProducto() {
    return listaProducto;
}

public void setListaProducto(ArrayList<Producto> listaProducto) {
    this.listaProducto = listaProducto;
}

// Atributo cantidadProductos
public ArrayList<Producto> getCantidadProductos() {
    return cantidadProductos;
}

public void setCantidadProductos(ArrayList<Producto> cantidadProductos) {
    this.cantidadProductos = cantidadProductos;
    }  
}
