package baseDatos;
import java.io.*;

import baseDatos.temp.Load;

public class Serializador {
    
    public static void serializar(Object objeto, String nombreArchivo) {
        try {
            FileOutputStream fileOut = new FileOutputStream(nombreArchivo);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(objeto);
            out.close();
            fileOut.close();
            System.out.println("Serialización exitosa");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void guardarFacturas(){

            serializar(Load.facturas,
             "src/baseDatos/temp/facturas.txt");

            }

        public static void guardarTiendas(){

            serializar(Load.tiendas, "src/baseDatos/temp/tiendas.txt");

        }

        public static void guardarFabrica(){

            serializar(Load.fabrica, "src/baseDatos/temp/fabrica.txt");
        }

        public static void guardarCatalogo(){
            serializar(Load.catalogo, "src/baseDatos/temp/catalogo.txt");
        }

        public static void guardarClientes(){
            serializar(Load.clientes, "src/baseDatos/temp/clientes.txt");
        }

         public static void guardarVendedores(){
            serializar(Load.vendedores, "src/baseDatos/temp/vendedores.txt");
         }

         public static void guardarTransporte(){
             serializar(Load.transporteAbastecer, "src/baseDatos/temp/transporte.txt");
         }

         public static void guardarConductores(){
            serializar(Load.conductores, "src/baseDatos/temp/conductores.txt");
         }

}
