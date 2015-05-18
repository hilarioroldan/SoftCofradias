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
public class CuentaBancaria {
    
    private int identificador;
    private String descripcion;
    private String titulo;
    private String banco;
    private String direccion;
    private String municipio;
    private String codigo_postal;
    private String provincia;
    private String pais;
    private String num_cuenta;
    private String iban;
    private String bic;
    private int cantidad;

  
    
    
    public CuentaBancaria(){}
    

    public CuentaBancaria(int identificador, String descripcion, String titulo, String banco, String direccion, String municipio,String codigo_postal, String provincia, String pais,String num_cuenta, String iban, String bic, int cantidad) {
        this.identificador = identificador;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.banco = banco;
        this.direccion = direccion;
        this.municipio = municipio;
        this.codigo_postal = codigo_postal;
        this.provincia = provincia;
        this.pais = pais;
        this.num_cuenta = num_cuenta;
        this.iban = iban;
        this.bic = bic;
        this.cantidad = cantidad;
    }
    
    public CuentaBancaria(int identificador, String descripcion, String titulo, String banco,int cantidad){
     this.identificador = identificador;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.banco = banco;
           this.cantidad = cantidad;
    }
    //controlador para modificar datos de cuenta bancaria sin modificar el identificador
       public CuentaBancaria(String descripcion, String titulo, String banco, String direccion, String municipio,String codigo_postal, String provincia, String pais,String num_cuenta, String iban, String bic, int cantidad) {
  
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.banco = banco;
        this.direccion = direccion;
        this.municipio = municipio;
        this.codigo_postal = codigo_postal;
        this.provincia = provincia;
        this.pais = pais;
        this.num_cuenta = num_cuenta;
        this.iban = iban;
        this.bic = bic;
        this.cantidad = cantidad;
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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getNum_cuenta() {
        return num_cuenta;
    }

    public void setNum_cuenta(String num_cuenta) {
        this.num_cuenta = num_cuenta;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" + "identificador=" + identificador + ", descripcion=" + descripcion + ", titulo=" + titulo + ", banco=" + banco + ", direccion=" + direccion + ", municipio=" + municipio + ", cod_postal=" + codigo_postal + ", provincia=" + provincia + ", pais=" + pais +  ", iban=" + iban + ", bic=" + bic + ", cantidad=" + cantidad +", num_cuenta=" + num_cuenta + '}';
    }
      
     public void insertar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
          
          CuentaBancariaBD c2 = new CuentaBancariaBD(this);         
          c2.insertar();
        
    }
     
      public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CuentaBancariaBD c = new CuentaBancariaBD(this);        
        c.modificar();
    }
    
}
