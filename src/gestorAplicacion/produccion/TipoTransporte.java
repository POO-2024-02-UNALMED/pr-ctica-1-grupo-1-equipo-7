package produccion;
    
    import java.util.ArrayList;
    
    public enum TipoTransporte {
        // Definición de los transportes con sus atributos
        CAMION(15000, 16329, "Camion"),
        AVION(30000, 64000, "Avion"),
        AUTOMOVIL(9000, 500, "Automovil"),
        CAMIONETA(12000, 650, "Camioneta"),
        BICICLETA(5000, 35, "Bicicleta"),
        PATINES(3000, 20, "Patines"),
        BARCO(20000, 3356835, "Barco"),
        HELICOPTERO(70000, 29000, "Helicoptero"),
        TREN(20000, 30000, "Tren"),
        CAMINANDO(5000, 15, "Caminando");
        //atributos basados en lo concordado, y metodos de prouebas, se podrian modificar
        private final int precioEnvio;
        private final int capacidadMax;
        private final String nombre;
    
        // Constructor
        TipoTransporte(int precioEnvio, int capacidadMax, String nombre) {
            this.precioEnvio = precioEnvio;
            this.capacidadMax = capacidadMax;
            this.nombre = nombre;
        }
    
        // Getters
        public int getPrecioEnvio() {
            return precioEnvio;
        }
    
        public int getCapacidadMax() {
            return capacidadMax;
        }
    
        public String getNombre() {
            return nombre;
        }
    
        // Método 1: Crear lista según la carga
        //El método filtra y devuelve una lista de tipos de transporte cuya capacidad máxima es mayor o igual al peso total de los productos.
        public static ArrayList<TipoTransporte> crearTipoTransporteSegunCarga(Double pesoTotalProductos) {
            ArrayList<TipoTransporte> listaFiltrada = new ArrayList<>();
            for (TipoTransporte transporte : TipoTransporte.values()) {
                if (pesoTotalProductos <= transporte.getCapacidadMax()) {
                    listaFiltrada.add(transporte);
                }
            }
            return listaFiltrada;
        }
        // Método 2: Mostrar opciones de transporte en formato String
        //El primer método, mostrarTipoTransporteSegunCarga, llama al segundo método con el parámetro envioGratisRecomendado por defecto en false. 
        public static String mostrarTipoTransporteSegunCarga(ArrayList<TipoTransporte> listaFiltrada) {
            return mostrarTipoTransporteSegunCarga(listaFiltrada, false); // Por defecto, el transporte recomendado no es gratis
        }
        
        // Método principal con el parámetro booleano
        //El segundo método muestra los detalles de cada tipo de transporte filtrado, incluyendo el precio de envío, la capacidad máxima y una etiqueta si es el transporte recomendado (con el precio más bajo). Si envioGratisRecomendado es true, el transporte recomendado se muestra con envío gratis
        public static String mostrarTipoTransporteSegunCarga(ArrayList<TipoTransporte> listaFiltrada, boolean envioGratisRecomendado) {
            StringBuilder sb = new StringBuilder("");
        
            // Inicializa las variables para determinar el transporte con el precio más bajo
            double precioMinimo = Double.MAX_VALUE;
            TipoTransporte transporteRecomendado = null;
        
            // Busca el transporte con el precio más bajo
            for (TipoTransporte transporte : listaFiltrada) {
                if (transporte.getPrecioEnvio() < precioMinimo) {
                    precioMinimo = transporte.getPrecioEnvio();
                    transporteRecomendado = transporte;
                }
            }
        
            // Añade los detalles de cada transporte y marcar el recomendado
            for (int i = 0; i < listaFiltrada.size(); i++) {
                TipoTransporte transporte = listaFiltrada.get(i);
                sb.append(i + 1).append(". ").append(transporte.getNombre())
                .append(" (Precio: ");
        
                // Si es el transporte recomendado y envioGratisRecomendado es true, hacer el envío gratis
                if (transporte == transporteRecomendado && envioGratisRecomendado) {
                    sb.append("0"); // Envío gratis para el recomendado
                } else {
                    sb.append(transporte.getPrecioEnvio());
                }
        
                sb.append(", Capacidad Máxima: ").append(transporte.getCapacidadMax());
        
                // Si es el transporte recomendado, agregar la etiqueta "Recomendado"
                if (transporte == transporteRecomendado) {
                    sb.append(" ---- RECOMENDADO");
                }
        
                sb.append(")\n");
            }

            return sb.toString();
        }
            
        
        
        // Método 3: Seleccionar un transporte de la lista
        public static TipoTransporte seleccionarTransporte(ArrayList<TipoTransporte> listaFiltrada, int opcion) {
            if (opcion > 0 && opcion <= listaFiltrada.size()) {
                return listaFiltrada.get(opcion - 1); // Las opciones empiezan desde 1
            } else {
                throw new IllegalArgumentException("Opción no válida. Seleccione un número entre 1 y " + listaFiltrada.size());
            }
        }
        
    }