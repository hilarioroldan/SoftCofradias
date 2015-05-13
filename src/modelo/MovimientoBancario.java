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
public class MovimientoBancario {
    
    
    private int identificador;
    private int id_bancario;
    private int año;
    private String mes;
    private int ingreso;
    private int retiro;
    private int dia;
    
    
    
    
    public MovimientoBancario(int identificador, int id_bancario, int año, String mes,int dia, int ingreso, int retiro) {
        this.identificador = identificador;
        this.id_bancario = id_bancario;
        this.año = año;
        this.mes = mes;
        this.dia=dia;
        this.ingreso = ingreso;
        this.retiro = retiro;
    }
            
    
    
    public MovimientoBancario(){}

 
   

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public int getId_bancario() {
        return id_bancario;
    }

    public void setId_bancario(int id_bancario) {
        this.id_bancario = id_bancario;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getIngreso() {
        return ingreso;
    }

    public void setIngreso(int ingreso) {
        this.ingreso = ingreso;
    }

    public int getRetiro() {
        return retiro;
    }

    public void setRetiro(int retiro) {
        this.retiro = retiro;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "MovimientoBancario{" + "identificador=" + identificador + ", id_bancario=" + id_bancario + ", a\u00f1o=" + año + ", mes=" + mes + ", ingreso=" + ingreso + ", retiro=" + retiro + ", dia=" + dia + '}';
    }
    
    

   
       public void insertar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
          
          MovimientoBancarioBD l2 = new MovimientoBancarioBD(this);         
          l2.insertar();
        
    }
    
    
    
}
