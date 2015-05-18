/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.SQLException;

/**
 *
 * @author alex
 */
public class Protocolo {
    
   private int identificador;
   private String descripcion;

   private int numero_hermano;
   private int salida_profesional_id;

   public Protocolo(){}
   
    public Protocolo(int identificador, String descripcion, int numero_hermano_id, int salida_profesional_id) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.numero_hermano = numero_hermano;
        this.salida_profesional_id = salida_profesional_id;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

    public int getNumero_hermano() {
        return numero_hermano;
    }

    public void setNumero_hermano(int numero_hermano) {
        this.numero_hermano = numero_hermano;
    }

    public int getSalida_profesional_id() {
        return salida_profesional_id;
    }

    public void setSalida_profesional_id(int salida_profesional_id) {
        this.salida_profesional_id = salida_profesional_id;
    }

    @Override
    public String toString() {
        return "Protocolo{" + "identificador=" + identificador + ", descripcion=" + descripcion + ", numero_hermano=" + numero_hermano + ", salida_profesional_id=" + salida_profesional_id + '}';
    }

   
    
     public void guardar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ProtocoloBD p = new ProtocoloBD(this);        
        p.guardar();
    }
}
