package uiMain;

import java.time.LocalDate;
import java.util.ArrayList;

import gestion.Cliente;
import gestion.Conductor;
import gestion.CuentaBancaria;
import gestion.Factura;
import gestion.Vendedor;
import produccion.Producto;
import produccion.Tienda;
import produccion.TipoTransporte;
import produccion.Transporte;
//import gestion.Factura;

//Clase creada para probar la interfaz de usuario de Estadisticas
//Se requiere que se hayan creado objetos de las clases Factura, Vendedor, Cliente, Conductor, Tienda, Transporte y Producto
//Hacer revision de algunas clases y sus constructores para evitar redundancias
public class pruebaUIEstadistica {
    public static void main(String[] args) {

        Vendedor vendedor = new Vendedor("Mauro", 0, 30, null);

        Cliente cliente = new Cliente();

        Conductor conductor = new Conductor("Rogelio", 12345, 30, null, null, null);

        Tienda tienda = new Tienda("Tienda de prueba", vendedor, new CuentaBancaria(1000), 0);

        Transporte transporte = new Transporte(TipoTransporte.CAMION, 100, 100, conductor, tienda);

        ArrayList<Producto> productos1 = new ArrayList<Producto>();
        productos1.add(new Producto("Producto1", 100, 10, 0, "disponible", "tipo1", "categoria1"));
        productos1.add(new Producto("Producto2", 200, 10, 1, "disponible", "tipo2", "categoria2"));
        productos1.add(new Producto("Producto3", 300, 10, 2, "disponible", "tipo3", "categoria3"));
        productos1.add(new Producto("Producto4", 400, 10, 3, "disponible", "tipo4", "categoria4"));

        ArrayList<Producto> productos2 = new ArrayList<Producto>();
        productos2.add(new Producto("Producto5", 500, 10, 4, "disponible", "tipo5", "categoria5"));
        productos2.add(new Producto("Producto6", 600, 10, 5, "disponible", "tipo6", "categoria6"));
        productos2.add(new Producto("Producto7", 700, 10, 6, "disponible", "tipo7", "categoria7"));
        productos2.add(new Producto("Producto8", 800, 10, 7, "disponible", "tipo8", "categoria8"));

        ArrayList<Producto> productos3 = new ArrayList<Producto>();
        productos3.add(new Producto("Producto9", 900, 10, 8, "disponible", "tipo9", "categoria9"));
        productos3.add(new Producto("Producto10", 1000, 10, 9, "disponible", "tipo10", "categoria10"));

        ArrayList<Producto> productos4 = new ArrayList<Producto>();
        productos4.add(new Producto("Producto11", 1100, 10, 10, "disponible", "tipo11", "categoria11"));


        Factura f1 = vendedor.crearFactura(tienda, cliente, transporte, productos1, LocalDate.of(2021, 1, 1));
        Factura f2 = vendedor.crearFactura(tienda, cliente, transporte,productos2, LocalDate.of(2021, 1, 2));
        Factura f3 = vendedor.crearFactura(tienda, cliente, transporte,productos3, LocalDate.of(2021, 1, 3));
        Factura f4 = vendedor.crearFactura(tienda, cliente, transporte,productos4, LocalDate.of(2021, 1, 4));

        System.out.println("===Totales Facturas ===");
        ArrayList<Factura> facturas = Factura.getListaFacturas();
        for (Factura factura : facturas) {
            System.out.println(factura.calcularTotal());
        }

        uiEstadistica.mostrar();
    }
    
}
