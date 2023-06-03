package entidades;

class Colectivo {
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

    @Override
    public String toString() {
        return "Colectivo{" + "kilometraje=" + kilometraje + ", cantidadPasajeros=" + cantidadPasajeros + ", modelo=" + modelo + ", patente=" + patente + '}';
    }
}
