/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.SQLException;

/**
 *
 * @author alex
 */
public class PagoCuotas {
    
    
    private int identificador;
    private int numero_hermano_id;
    private String fecha;
    private int mayordomia_id;
    private String pagado;
    private int precio;

    public PagoCuotas(int identificador, int numero_hermano_id, String fecha, int mayordomia_id, int precio,String pagado) {
        this.identificador = identificador;
        this.numero_hermano_id = numero_hermano_id;
        this.fecha = fecha;
        this.mayordomia_id = mayordomia_id;
        this.pagado = pagado;
        this.precio = precio;
    }

    public PagoCuotas(int identificador, int numero_hermano_id, String pagado,String date, int precio) {
       this.pagado = pagado;
        this.precio = precio;
          this.identificador = identificador;
        this.numero_hermano_id = numero_hermano_id;
        
    
    }

    
    public PagoCuotas(){}

  

    public PagoCuotas(int identificador, int numero_hermano_id, int mayordomia_id, String pagado, String date, int precio) {
     this.identificador = identificador;
        this.numero_hermano_id = numero_hermano_id;
        this.fecha = fecha;
        this.mayordomia_id = mayordomia_id;
        this.pagado = pagado;
        this.precio = precio; }
    
    
    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getNumero_hermano_id() {
        return numero_hermano_id;
    }

    public void setNumero_hermano_id(int numero_hermano_id) {
        this.numero_hermano_id = numero_hermano_id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getMayordomia_id() {
        return mayordomia_id;
    }

    public void setMayordomia_id(int mayordomia_id) {
        this.mayordomia_id = mayordomia_id;
    }

    public String isPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    

    @Override
    public String toString() {
        return "PagoCuotas{" + "identificador=" + identificador + ", numero_hermano_id=" + numero_hermano_id + ", fecha=" + fecha + ", mayordomia_id=" + mayordomia_id + ", pagado=" + pagado + ", precio=" + precio + '}';
    }

   

    
        public void guardar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
    
         PagoCuotasBD j = new PagoCuotasBD(this);         
         j.guardar();
         
    }
    
    
    
    
}
