package distribucion;
import gestion.*;

public class Tienda {
    private static int numTiendas = 0;
    private String nombre;
    private Vendedor vendedor;
    private CuentaBancaria cuentaBancaria;
    private ArrayList<Producto> listaProductos = new ArrayList<>();
    private ArrayList<Object[]> productosPorCategoria = new ArrayList<>(); // Lista de [Producto, Categoria]
    //no se puede HashMap para los atributos cantidadPorCategoria y productosPorCategoria,Jose Luis: una posible solucion puede ser una tabla donde se puede tener por ejemplo [[producto1,categoria],[producto2,categoria]]
    private ArrayList<Producto> productosDevueltos = new ArrayList<Producto>();

    public Tienda(String nombre, Vendedor vendedor, CuentaBancaria cuentaBancaria) {
        this.nombre = nombre;
        this.vendedor = vendedor;
        this.cuentaBancaria = cuentaBancaria;
        numTiendas++;
    }
    public static void mostrarProductos(){
        if (this.listaProductos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }
        for (int i = 0; i < clientes.size(); i++) {
            System.out.println((i + 1) + ". " + this.listaProductos.get(i).getNombre());
        }
    }
    public String cantidadProductos(){
        if (listaProductos == null) {
            return "El inventario no está disponible.";
        }
        return "En el inventario se encuentran " + listaProductos.size() + " productos.";
    }
    public void agregarProductosPorCategoria(Producto producto, int categoria){
        Object[] productoCategoria = {producto, categoria};
        productosPorCategoria.add(productoCategoria);
    }
    

    


    

}
