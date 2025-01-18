package gestion;

public class Meta {
    private String nivelDeDificultad;
    private double indice;
    private double pago;
    private boolean verificador;

    public Meta(String nivelDeDificultad, double indice, double pago){
        this.nivelDeDificultad = nivelDeDificultad;
        this.indice = indice;
        this.pago = pago;
        this.verificador = false;
    }

    public boolean cumpleMeta(double indiceDeTrabajo){
        if(this.getIndice() < indiceDeTrabajo){
            return true;
        }else{
            return false;
        }
    }

    public String porcentajeCumplidos(double indiceDeTrabajo){
        double porcentajeIndice = (indiceDeTrabajo * 100)/this.indice;
        String mensaje = "Porcentaje de la meta logrado:" + porcentajeIndice + "%";
        if(porcentajeIndice < 100){
            mensaje += "\nPorcentaje faltante: " + (100-porcentajeIndice) + "%";
            mensaje += "\nCantidad faltante del indice indicado: " + (this.indice - indiceDeTrabajo); 
        }
        return mensaje;
    }

    @Override
    public String toString(){
        return "\nNivel de dificultad:" + this.nivelDeDificultad +
        "\nIndice requerido para cumplir la meta:" + this.indice + 
        "\nRecompensa por meta lograda:" + this.pago;
    }


    // Getter y Setter

    //Getter y Setter para verificador
    public boolean getVerificador(){
        return this.verificador;
    }

    public void setVerificador(boolean bool){
        this.verificador = bool;
    }

    // Getter y Setter para nivelDeDificultad
    public String getNivelDeDificultad() { 
        return nivelDeDificultad;
    }

    public void setNivelDeDificultad(String nivelDeDificultad) { 
        this.nivelDeDificultad = nivelDeDificultad;
    }

    // Getter y Setter para indice
    public double getIndice() { 
        return indice;
    }

    public void setIndice(double indice) { 
        this.indice = indice;
    }

    // Getter y Setter para pago
    public double getPago() { 
        return pago;
    }

    public void setPago(double pago) { 
        this.pago = pago;
    }
}
