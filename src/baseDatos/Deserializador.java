package baseDatos;

import java.io.ObjectInputStream;
import java.util.ArrayList;

import gestion.*;
import produccion.*;
import java.io.*;


public class Deserializador {

    public static Serializable deserializar(String strArchivo) throws IOException, ClassNotFoundException{

        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;

            fileInputStream = new FileInputStream(strArchivo);
           objectInputStream = new ObjectInputStream(fileInputStream);
        
            Serializable s = (Serializable) objectInputStream.readObject();
            objectInputStream.close();
      
        return s;
    }

    public static ArrayList<Factura> cargarFacturas() throws IOException, ClassNotFoundException{
        @SuppressWarnings("unchecked")

        ArrayList<Factura> listaFacturas = (ArrayList<Factura>) deserializar("src/baseDatos/temp/facturas.txt");

        return listaFacturas;
    }


    public static Fabrica cargarFabrica() throws IOException, ClassNotFoundException{

        Fabrica fabrica = (Fabrica) deserializar("src/baseDatos/temp/fabrica.txt");

        return fabrica;
    }


    public static ArrayList<Producto> cargarCatalogo() throws IOException, ClassNotFoundException{

        ArrayList<Producto> catalogo = (ArrayList<Producto>) deserializar("src/baseDatos/temp/catalogo.txt");

        return catalogo;
    }


    public static ArrayList<Cliente> cargarClientes() throws IOException, ClassNotFoundException{

        ArrayList<Cliente> listaClientes= (ArrayList<Cliente>) deserializar("src/baseDatos/temp/clientes.txt");

        return listaClientes;
    }


    public static ArrayList<Vendedor> cargarVendedores() throws IOException, ClassNotFoundException{

        ArrayList<Vendedor> listaVendedores = (ArrayList<Vendedor>) deserializar("src/baseDatos/temp/vendedores.txt");

        return listaVendedores;
    }


    public static ArrayList<Tienda> cargarTiendas() throws IOException, ClassNotFoundException{

        ArrayList<Tienda> listaTiendas = (ArrayList<Tienda>) deserializar("src/baseDatos/temp/tiendas.txt");

        return listaTiendas;
    }


    public static ArrayList<Transporte> cargarTransportes() throws IOException, ClassNotFoundException{

        ArrayList<Transporte> listaTransportes = (ArrayList<Transporte>) deserializar("src/baseDatos/temp/transporte.txt");

        return listaTransportes;
    }


    public static ArrayList<Conductor> cargaConductores() throws IOException, ClassNotFoundException{

        ArrayList<Conductor> listaConductores = (ArrayList<Conductor>) deserializar("src/baseDatos/temp/conductores.txt");

        return listaConductores;
    }

}