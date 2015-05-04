
package modelo;

public class FormaPago {
    
    int identificador;
    String forma_pago;

    public FormaPago(int identificador, String forma_pago) {
        this.identificador = identificador;
        this.forma_pago = forma_pago;
    }

    public FormaPago() {
    }   

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(String forma_pago) {
        this.forma_pago = forma_pago;
    }

    @Override
    public String toString() {
        return "FormaPago{" + "identificador=" + identificador + ", forma_pago=" + forma_pago + '}';
    }
    
    
    
    
}
