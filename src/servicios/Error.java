
package servicios;

public class Error extends Exception {
    
    private int codigo;
    private String mensaje;

    public Error(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }
    
    public int getCodigo(){
        return codigo;
    }
    
    public String getMensaje(){
        return mensaje;
    }
    
}
