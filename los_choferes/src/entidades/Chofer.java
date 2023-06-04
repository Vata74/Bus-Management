package entidades;

public class Chofer {

    private int nroSocio;
    private String nombre;
    private String apellido;
    private Colectivo colectivo;

    public Chofer() {
    }

    public Chofer(int nroSocio, String nombre, String apellido, Colectivo colectivo) {
        this.nroSocio = nroSocio;
        this.nombre = nombre;
        this.apellido = apellido;
        this.colectivo = colectivo;
    }

    public int getNroSocio() {
        return nroSocio;
    }

    public void setNroSocio(int nroSocio) {
        this.nroSocio = nroSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Colectivo getColectivo() {
        return colectivo;
    }

    public void setColectivo(Colectivo colectivo) {
        this.colectivo = colectivo;
    }

    public Chofer validar(String nroSocio, String nombre, String apellido, Integer nroSocioVal) {
        try {
            if (nroSocioVal != null) {
                if (Integer.parseInt(nroSocio) != nroSocioVal) {
                    this.nroSocio = Integer.parseInt(nroSocio);
                }else{
                    this.nroSocio = Integer.parseInt(nroSocio);
                }
            }
            this.nombre = nombre;
            this.apellido = apellido;
            return this;
        } catch (Exception e) {
            return null;
        }
    }

    public Chofer obtenerChofer(String lineaChofer, String lineaColectivo){
        String[] lineas  = lineaChofer.split(",");
        Colectivo colectivo = new Colectivo();
        colectivo = colectivo.obtenerColectivo(lineaColectivo);
        return new Chofer(Integer.parseInt(lineas[0]), lineas[1],  lineas[2], colectivo);
    }
    
    @Override
    public String toString() {
        return "Chofer{" + "nroSocio=" + nroSocio + ", nombre=" + nombre + ", apellido=" + apellido + ", colectivo=" + colectivo + '}';
    }
}
