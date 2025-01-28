package baseDatos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import gestion.*;
import produccion.*;


public class Load {
    
     public static Fabrica fabricaa;
     public static ArrayList<Producto> catalogo = new ArrayList<Producto>();
     public static ArrayList<Tienda> listaTiendas = new ArrayList<Tienda>();
     public static ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
     public static ArrayList<Vendedor> listaVendedores = new ArrayList<Vendedor>();
     public static ArrayList<Factura> listaFacturas = new ArrayList<Factura>();
     public static ArrayList<Conductor> listaConductores = new ArrayList<Conductor>();
     public static ArrayList<Transporte> listaTransportes = new ArrayList<Transporte>();
    

    public static void guardar(){

      //cargarPorDefecto();
      
      listaTiendas = Fabrica.getListaTiendas();
      listaFacturas = Factura.getListaFacturas();
      listaClientes = Cliente.getListaClientes();
      catalogo = Producto.getListaProductos();
      listaVendedores = Vendedor.getListaVendedores();
      listaConductores = Conductor.getListaConductores();
      listaTransportes = Transporte.getListaTransportes();

      Serializador.guardarTiendas();
      Serializador.guardarCatalogo();
      Serializador.guardarFabrica();
      Serializador.guardarFacturas();
      Serializador.guardarTransportes();
      Serializador.guardarVendedores();
      Serializador.guardarClientes();
      Serializador.guardarConductores();

    }

    /*public static void cargarPorDefecto(){

      // Crear cuentas bancarias
     CuentaBancaria cuentaFabrica = new CuentaBancaria(9999999, 1000000000);
     CuentaBancaria cuentaVendedor1 = new CuentaBancaria(56932, 100);
     CuentaBancaria cuentaVendedor2 = new CuentaBancaria(45728, 200);
     CuentaBancaria cuentaVendedor3 = new CuentaBancaria(95687, 200);

    // Crear vendedores
     Vendedor vendedor1 = new Vendedor("Maria Beatriz", 577935, 20, cuentaVendedor1);
     Vendedor vendedor2 = new Vendedor("Adriana Alexia Putellas", 89235, 21, cuentaVendedor2);
     Vendedor vendedor3 = new Vendedor("Lionel Andres Messi", 14720, 22, cuentaVendedor3);

    listaVendedores.add(vendedor1);
    listaVendedores.add(vendedor2);
    listaVendedores.add(vendedor3);

    // Crear tiendas
     Tienda tienda1 = new Tienda("Herramientas UNAL", vendedor1, cuentaFabrica,100,100,100);
     Tienda tienda2 = new Tienda("Muebles comodisimo", vendedor2, cuentaFabrica,100,100,100);
     Tienda tienda3 = new Tienda("Limpieza UNAL", vendedor3, cuentaFabrica, 100, 100,100);

    // Crear productos para cada tienda
   
    //tienda 1
     Producto producto1 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 25.0);
     Producto producto2 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 25.0);
     Producto producto3 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 25.0);
     Producto producto4 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 25.0);
     Producto producto5 = new Producto("Cemento Gris", 50000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 25.0);
    
     Producto producto6 = new Producto("Cemento Blanco", 55000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 25.0);
     Producto producto7 = new Producto("Cemento Blanco", 55000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 25.0);
    
     Producto producto8 = new Producto("Adhesivo Cerámico", 20000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 5.0);
     Producto producto9 = new Producto("Adhesivo Cerámico", 20000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 5.0);
    
     Producto producto10 = new Producto("Pintura Interior", 35000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 18.0);
     Producto producto11 = new Producto("Pintura Interior", 35000, estadosProducto.DISPONIBLE, "Material", "Herramientas", 18.0);
    
    //tienda 2
     Producto producto12 = new Producto("Sillas", 10000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 1.0);
     Producto producto13 = new Producto("Sillas", 10000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 1.0);
     Producto producto14 = new Producto("Sillas", 10000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 1.0);

     Producto producto15 = new Producto("Mesas", 8000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 2.0);
     Producto producto16 = new Producto("Mesas", 8000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 2.0);
     Producto producto17 = new Producto("Mesas", 8000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 2.0);

     Producto producto18 = new Producto("Sofa", 5000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 1.0);
     Producto producto19 = new Producto("Sofa", 5000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 1.0);
     Producto producto20 = new Producto("Sofa", 5000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 1.0);

     Producto producto21 = new Producto("Mesa de noche", 12000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 0.5);
     Producto producto22 = new Producto("Mesa de noche", 12000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 0.5);
     Producto producto23 = new Producto("Mesa de noche", 12000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 0.5);

     Producto producto24 = new Producto("Cama", 7000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 0.25);
     Producto producto25 = new Producto("Cama", 7000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 0.25);
     Producto producto26 = new Producto("Cama", 7000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 0.25);

     Producto producto27 = new Producto("Escritorio", 15000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 1.0);
     Producto producto28 = new Producto("Escritorio", 15000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 1.0);
     Producto producto29 = new Producto("Escritorio", 15000, estadosProducto.DISPONIBLE, "Comodidad", "Muebles", 1.0);

    //tienda 3
     Producto producto30 = new Producto("Detergente", 15000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 3.0);
     Producto producto31 = new Producto("Detergente", 15000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 3.0);
     Producto producto32 = new Producto("Detergente", 15000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 3.0);

     Producto producto33 = new Producto("Esponja", 5000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 0.5);
     Producto producto34 = new Producto("Esponja", 5000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 0.5);
     Producto producto35 = new Producto("Esponja", 5000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 0.5);

     Producto producto36 = new Producto("Limpiador", 12000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 2.0);
     Producto producto37 = new Producto("Limpiador", 12000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 2.0);
     Producto producto38 = new Producto("Limpiador", 12000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 2.0);

     Producto producto39 = new Producto("Jabón Líquido", 10000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 1.5);
     Producto producto40 = new Producto("Jabón Líquido", 10000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 1.5);
     Producto producto41 = new Producto("Jabón Líquido", 10000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 1.5);

     Producto producto42 = new Producto("Trapeador", 25000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 0.8);
     Producto producto43 = new Producto("Trapeador", 25000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 0.8);
     Producto producto44 = new Producto("Trapeador", 25000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 0.8);

     Producto producto45 = new Producto("Cloro", 8000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 2.0);
     Producto producto46 = new Producto("Cloro", 8000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 2.0);
     Producto producto47 = new Producto("Cloro", 8000, estadosProducto.DISPONIBLE, "Limpieza", "Aseo", 2.0);

     ArrayList<Producto> listaProductosTienda1 = new ArrayList<>(Arrays.asList(
        producto1, producto2, producto3, producto4, producto5,  // Cemento Gris
        producto6, producto7,                                 // Cemento Blanco
        producto8, producto9,                                 // Adhesivo Cerámico
        producto10, producto11                                // Pintura Interior
));
     ArrayList<Producto> listaProductosTienda2 = new ArrayList<>(Arrays.asList(
        producto12, producto13, producto14, // Sillas
        producto15, producto16, producto17, // Mesas
        producto18, producto19, producto20, // Sofa
        producto21, producto22, producto23, // Mesa de noche
        producto24, producto25, producto26, // Cama
        producto27, producto28, producto29  // Escritorio
    ));
     ArrayList<Producto> listaProductosTienda3 = new ArrayList<>(Arrays.asList(
        producto30, producto31, producto32, // Detergente
        producto33, producto34, producto35, // Esponja
        producto36, producto37, producto38, // Limpiador
        producto39, producto40, producto41, // Jabón Líquido
        producto42, producto43, producto44, // Trapeador
        producto45, producto46, producto47  // Cloro
));



     {
        tienda1.getListaProducto().addAll(listaProductosTienda1);
        tienda2.getListaProducto().addAll(listaProductosTienda2);
        tienda3.getListaProducto().addAll(listaProductosTienda3);

        listaTiendas.add(tienda1);
        listaTiendas.add(tienda2);
        listaTiendas.add(tienda3);
    }

    // Crear lista de tiendas para la fábrica
     ArrayList<Tienda> listaTiendas = new ArrayList<>(Arrays.asList(tienda1, tienda2, tienda3));

    // Crear lista de productos disponibles para la fábrica
     ArrayList<Producto> catalogo = new ArrayList<>(Arrays.asList(producto1, producto6, producto8, producto10, producto12, producto15, producto18, producto21, producto24, producto27,producto30,producto33,producto36,producto39,producto42,producto45));

    // Crear operario
     CuentaBancaria cuentaOperario = new CuentaBancaria(55555, 100000);
     Operario operario1 = new Operario("Jaime", 97890, 20, cuentaOperario, null);

    // Crear fábrica
     Fabrica fabrica = new Fabrica("F001", "Fábrica Principal", "Calle Principal 123", cuentaFabrica, catalogo, listaTiendas, operario1);

        // Crear cuentas bancarias para los conductores
         CuentaBancaria cuentaConductor1 = new CuentaBancaria(12345, 5000);
         CuentaBancaria cuentaConductor2 = new CuentaBancaria(23456, 6000);
         CuentaBancaria cuentaConductor3 = new CuentaBancaria(34567, 7000);
         CuentaBancaria cuentaConductor4 = new CuentaBancaria(45678, 8000);
         CuentaBancaria cuentaConductor5 = new CuentaBancaria(56789, 9000);
         CuentaBancaria cuentaConductor6 = new CuentaBancaria(67890, 10000);
         CuentaBancaria cuentaConductor7 = new CuentaBancaria(78901, 11000);
         CuentaBancaria cuentaConductor8 = new CuentaBancaria(89012, 12000);
         CuentaBancaria cuentaConductor9 = new CuentaBancaria(90123, 13000);
         CuentaBancaria cuentaConductor10 = new CuentaBancaria(123456, 14000);

    // Crear transportes
         Transporte transporte1 = new Transporte(TipoTransporte.CAMION, 15000, 16329);
         Transporte transporte2 = new Transporte(TipoTransporte.AVION, 30000, 64000);
         Transporte transporte3 = new Transporte(TipoTransporte.AUTOMOVIL, 9000, 500);
         Transporte transporte4 = new Transporte(TipoTransporte.CAMIONETA, 12000, 650);
         Transporte transporte5 = new Transporte(TipoTransporte.BICICLETA, 5000, 35);
         Transporte transporte6 = new Transporte(TipoTransporte.PATINES, 3000, 20);
         Transporte transporte7 = new Transporte(TipoTransporte.BARCO, 20000, 3356835);
         Transporte transporte8 = new Transporte(TipoTransporte.HELICOPTERO, 70000, 29000);
         Transporte transporte9 = new Transporte(TipoTransporte.TREN, 20000, 30000);
         Transporte transporte10 = new Transporte(TipoTransporte.CAMINANDO, 5000, 15);

    // Crear conductores
         Conductor conductor1 = new Conductor("Julian Lopez", 19658, 30, cuentaConductor1, fabrica, transporte1);
         Conductor conductor2 = new Conductor("Oscar Rodriguez", 27932, 31, cuentaConductor2, fabrica, transporte2);
         Conductor conductor3 = new Conductor("Pablo Estrada", 37431, 32, cuentaConductor3, fabrica, transporte3);
         Conductor conductor4 = new Conductor("Camilo Henriquez", 4496, 33, cuentaConductor4, fabrica, transporte4);
         Conductor conductor5 = new Conductor("Juan Zamora", 55865, 34, cuentaConductor5, fabrica, transporte5);
         Conductor conductor6 = new Conductor("Miguel Zuluaga", 69636, 35, cuentaConductor6, fabrica, transporte6);
         Conductor conductor7 = new Conductor("Juan Herrera", 76970, 36, cuentaConductor7, fabrica, transporte7);
         Conductor conductor8 = new Conductor("Adres Guerra", 80497, 37, cuentaConductor8, fabrica, transporte8);
         Conductor conductor9 = new Conductor("Yhan Jaramillo", 93049, 38, cuentaConductor9, fabrica, transporte9);
         Conductor conductor10 = new Conductor("Jose Sanchez", 10101, 39, cuentaConductor10, fabrica, transporte10);

        //crear lista de transportes
        ArrayList<Transporte> listaTransportes = new ArrayList<>();
        {
            listaTransportes.add(transporte1);
            listaTransportes.add(transporte2);
            listaTransportes.add(transporte3);
            listaTransportes.add(transporte4);
            listaTransportes.add(transporte5);
            listaTransportes.add(transporte6);
            listaTransportes.add(transporte7);
            listaTransportes.add(transporte8);
            listaTransportes.add(transporte9);
            listaTransportes.add(transporte10);
        }

        // Crear lista de conductores
         ArrayList<Conductor> listaConductores = new ArrayList<>();
         {
            listaConductores.add(conductor1);
            listaConductores.add(conductor2);
            listaConductores.add(conductor3);
            listaConductores.add(conductor4);
            listaConductores.add(conductor5);
            listaConductores.add(conductor6);
            listaConductores.add(conductor7);
            listaConductores.add(conductor8);
            listaConductores.add(conductor9);
            listaConductores.add(conductor10);
        }

    // Instancias estáticas de las cuentas bancarias
      CuentaBancaria cuentaCliente1 = new CuentaBancaria(10001, 5000);
      CuentaBancaria cuentaCliente2 = new CuentaBancaria(10002, 15000);
      CuentaBancaria cuentaCliente3 = new CuentaBancaria(10003, 8000);
      CuentaBancaria cuentaCliente4 = new CuentaBancaria(10004, 2000);
      CuentaBancaria cuentaCliente5 = new CuentaBancaria(10005, 12000);

    // Instancias estáticas de los clientes
      Cliente cliente1 = new Cliente("Juan Pérez", 30, 987654321, cuentaCliente1);
      Cliente cliente2 = new Cliente("María López", 25, 123456789, cuentaCliente2);
      Cliente cliente3 = new Cliente("Carlos García", 40, 567890123, cuentaCliente3);
      Cliente cliente4 = new Cliente("Ana Rodríguez", 35, 654321987, cuentaCliente4);
      Cliente cliente5 = new Cliente("Luis Fernández", 28, 192837465, cuentaCliente5);


    //Instancias estáticas de las metas para operario
      Meta metaOperario1 = new Meta("Facil",5, 10000);
      Meta metaOperario2 = new Meta("Normal", 10, 17000);
      Meta metaOperario3 = new Meta("Dificil", 15, 25000);
      Meta metaOperario4 = new Meta("Muy Dificil", 20, 35000);

    //Instancias estáticas de las metas para Vendedor
      Meta metaVendedor1 = new Meta("Facil",5, 9000);
      Meta metaVendedor2= new Meta("Normal", 10, 15000);
      Meta metaVendedor3 = new Meta("Dificil", 15, 22000);
      Meta metaVendedor4 = new Meta("Muy Dificil", 20, 30000);

    //Instancias estáticas de las metas para operario
      Meta metaConductor1 = new Meta("Facil",25, 8000);
      Meta metaConductor2= new Meta("Normal", 40, 13500);
      Meta metaConductor3 = new Meta("Dificil", 55, 21000);
      Meta metaConductor4 = new Meta("Muy Dificil", 70, 28500);
    
    //Factura
     LocalDate fecha = LocalDate.of(2024,10,2);
     LocalDate fecha2 = LocalDate.of(2024,10,5);
     LocalDate fecha3 = LocalDate.of(2024,10,8);
     

     Factura f1 =new Factura(tienda1, cliente1, transporte1, listaProductosTienda1,transporte1.getTipoTransporte().getPrecioEnvio(), fecha);
     Factura f2 =new Factura(tienda2, cliente2, transporte2, listaProductosTienda2,transporte2.getTipoTransporte().getPrecioEnvio(), fecha2);
     Factura f3 =new Factura(tienda2, cliente1, transporte3, listaProductosTienda3,transporte3.getTipoTransporte().getPrecioEnvio(),fecha3);

    {
        operario1.setMetaOperario(metaOperario1);
        operario1.setMetaOperario(metaOperario2);
        operario1.setMetaOperario(metaOperario3);
        operario1.setMetaOperario(metaOperario4);

        for(Conductor i: Conductor.getListaConductores()){
            i.setMetaConductor(metaConductor1);
            i.setMetaConductor(metaConductor2);
            i.setMetaConductor(metaConductor3);
            i.setMetaConductor(metaConductor4);
        }

        for(Vendedor i : Vendedor.getListaVendedores()){
            i.setMetaVendedor(metaVendedor1);
            i.setMetaVendedor(metaVendedor2);
            i.setMetaVendedor(metaVendedor3);
            i.setMetaVendedor(metaVendedor4);
        }

      }
        
    }
*/

    public static void cargar(){
      try{

       listaTiendas =  Deserializador.cargarTiendas();
       catalogo = Deserializador.cargarCatalogo();
       fabricaa = Deserializador.cargarFabrica();
       listaClientes = Deserializador.cargarClientes();
       listaTransportes = Deserializador.cargarTransportes();
       listaVendedores =  Deserializador.cargarVendedores();
       listaFacturas =  Deserializador.cargarFacturas();
       listaConductores = Deserializador.cargaConductores();
        
       Fabrica.setListaTiendas(listaTiendas);
       Factura.setListaFacturas(listaFacturas);
       Cliente.setListaClientes(listaClientes);
       Producto.setListaProductos(catalogo);
       Conductor.setListaConductores(listaConductores);
       Transporte.setListaTransportes(listaTransportes);

      }catch(Exception e){
        System.out.println("Ha ocurrido un error en la deserialización");
        e.printStackTrace();
      }
    }

    public static void main(String[] args) {
        cargar();
        System.out.println("Carga exitosa");
        System.out.println(Producto.getListaProductos().size());
    }
}
