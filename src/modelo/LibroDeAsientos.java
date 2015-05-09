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
public class LibroDeAsientos {

    private int identificador;
    private String fecha;
    private String concepto;
    private int ingresar;
    private int deber;
    private int mayordomia_id;
    private int cuenta_bancaria_id;
    
    public LibroDeAsientos(){}

    public LibroDeAsientos(int identificador, String fecha, String concepto, int ingresar, int deber, int mayordomia_id, int cuenta_bancaria_id) {
        this.identificador = identificador;
        this.fecha = fecha;
        this.concepto = concepto;
        this.ingresar = ingresar;
        this.deber = deber;
        this.mayordomia_id = mayordomia_id;
        this.cuenta_bancaria_id = cuenta_bancaria_id;
    }

   

 

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public int getIngresar() {
        return ingresar;
    }

    public void setIngresar(int ingresar) {
        this.ingresar = ingresar;
    }

    public int getDeber() {
        return deber;
    }

    public void setDeber(int deber) {
        this.deber = deber;
    }

    public int getMayordomia_id() {
        return mayordomia_id;
    }

    public void setMayordomia_id(int mayordomia_id) {
        this.mayordomia_id = mayordomia_id;
    }

    public int getCuenta_bancaria_id() {
        return cuenta_bancaria_id;
    }

    public void setCuenta_bancaria_id(int cuenta_bancaria_id) {
        this.cuenta_bancaria_id = cuenta_bancaria_id;
    }
    
    
    

    
   
     public void insertar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
          
          LibroDeAsientosBD l2 = new LibroDeAsientosBD(this);         
          l2.insertar();
        
    }


    
    
    
}
