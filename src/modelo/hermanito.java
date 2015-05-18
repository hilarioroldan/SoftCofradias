package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class hermanito extends persona {

    private int numero_hermano;
    private String forma_de_pago;
    private String tipo_de_pagox;
    private String direccion;
    private int cp;
    private String fecha;

    public hermanito(int numero_hermano, String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria, int tipo_pago_id, int forma_pago_id) {
        super(nombre, nif, apellido, municipio, provincia, pais, tfno, email, banco, cuenta_bancaria, tipo_pago_id, forma_pago_id);
        this.numero_hermano = numero_hermano;

    }

    public hermanito(int numero_hermano, String direccion, int cp, String fecha, String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria, int tipo_pago_id, int forma_pago_id) {
        super(nombre, nif, apellido, municipio, provincia, pais, tfno, email, banco, cuenta_bancaria, tipo_pago_id, forma_pago_id);
        this.numero_hermano = numero_hermano;
        this.direccion = direccion;
        this.cp = cp;
        this.fecha = fecha;
    }
    
     public hermanito(int numero_hermano, String direccion, int cp, String fecha, String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria, int forma_pago_id) {
        super(nombre, nif, apellido, municipio, provincia, pais, tfno, email, banco, cuenta_bancaria, forma_pago_id);
        this.numero_hermano = numero_hermano;
        this.direccion = direccion;
        this.cp = cp;
        this.fecha = fecha;
    }     
     
     public hermanito(int numero_hermano, String direccion, int cp, String fecha, String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria) {
        super(nombre, nif, apellido, municipio, provincia, pais, tfno, email, banco, cuenta_bancaria);
        this.numero_hermano = numero_hermano;
        this.direccion = direccion;
        this.cp = cp;
        this.fecha = fecha;
    }

    public hermanito() {

    }

    //CONSTRUCTOR PERSONALIZADO PARA LA TABLA FORMA DE PAGOS
    public hermanito(int forma_pago_id, String forma) {
        super(forma_pago_id);
        this.forma_de_pago = forma;

    }

    public hermanito(int numero_hermano, int forma_pago_id) {
        super(forma_pago_id);
        this.numero_hermano = numero_hermano;
        this.forma_pago_id = forma_pago_id;

    }

    public hermanito(int numero_hermano) {
        this.numero_hermano = numero_hermano;
    }
    
    

    public int getNumero_hermano() {
        return numero_hermano;
    }

    public void setNumero_hermano(int numero_hermano) {
        this.numero_hermano = numero_hermano;
    }

    public String getForma_de_pago() {
        return forma_de_pago;
    }

    public void setForma_de_pago(String forma_de_pago) {
        this.forma_de_pago = forma_de_pago;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo_de_pagox() {
        return tipo_de_pagox;
    }

    public void setTipo_de_pagox(String tipo_de_pagox) {
        this.tipo_de_pagox = tipo_de_pagox;
    }
    
    

    @Override
    public String toString() {
        return super.toString() + "hermanito{" + "numero_hermano=" + numero_hermano + ", forma_de_pago=" + forma_de_pago + ", direccion=" + direccion + ", cp=" + cp + ", fecha=" + fecha + '}';
    }

    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        hermanitoBD h2 = new hermanitoBD(this);
        h2.grabar();

    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h = new hermanitoBD(this);
        h.modificar();
    }
    
    public void actualizarSinFormaPago() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h = new hermanitoBD(this);
        h.modificarSinFormaPago();
    }
    
    public void actualizarSinTipoPago() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h = new hermanitoBD(this);
        h.modificarSinTipoPago();
    }
    
    public void actualizarSinTipoPagoSinFormaPago() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h = new hermanitoBD(this);
        h.modificarSinFormaPagoSinTipoPago();
    }
    

    /*Este metodo me permite instanciar la clase proveedordb y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h1 = new hermanitoBD(this);
        h1.borrar();
    }

    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h = new hermanitoBD(this);
        return h.leerTodos();
    }

    /*Este metodo busca un proveedor indicando el ruc y porque campo se va a filtrar*/
    public ArrayList<hermanito> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hermanitoBD h = new hermanitoBD(this);
        return h.buscar(filtro, campo);
    }

}
