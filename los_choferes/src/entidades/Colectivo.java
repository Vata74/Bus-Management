package entidades;

import java.util.Arrays;

public class Colectivo {
    private int kilometraje;
    private int cantidadPasajeros;
    private String modelo;
    private String patente;

    public Colectivo() {
    }

    public Colectivo(int kilometraje, int cantidadPasajeros, String modelo, String patente) {
        this.kilometraje = kilometraje;
        this.cantidadPasajeros = cantidadPasajeros;
        this.modelo = modelo;
        this.patente = patente;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public int getCantidadPasajeros() {
        return cantidadPasajeros;
    }

    public void setCantidadPasajeros(int cantidadPasajeros) {
        this.cantidadPasajeros = cantidadPasajeros;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }
    
    public Colectivo validar(String modelo, String patente, String cantidadPasajeros, String patenteVal){
        try {
            this.kilometraje = 0;
            this.cantidadPasajeros = Integer.valueOf(cantidadPasajeros);
            this.modelo = modelo;
            if(patenteVal != null){
                if(!patenteVal.equalsIgnoreCase(patente)){
                    this.patente = patente;
                }
            }else{
                this.patente = patente;
            }
            return this;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Colectivo obtenerColectivo(String colectivo){
        String[] colectivoClean = colectivo.split(",");
        return new Colectivo(Integer.parseInt(colectivoClean[0]), Integer.parseInt(colectivoClean[1]), colectivoClean[2], colectivoClean[3]);
    }
    
    @Override
    public String toString() {
        return "Colectivo{" + "kilometraje=" + kilometraje + ", cantidadPasajeros=" + cantidadPasajeros + ", modelo=" + modelo + ", patente=" + patente + '}';
    }
}
