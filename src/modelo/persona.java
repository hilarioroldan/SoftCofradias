package modelo;

public class persona {

    private String nombre;
    private String nif;
    private String apellido;
    private String municipio;
    private String provincia;
    private String pais;
    private int tfno;
    private String email;
    private String banco;
    private String cuenta_bancaria;
    private int tipo_pago_id;
    int forma_pago_id;
    private int id_hermandad;

    public persona() {

    }

    public persona(String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria, int tipo_pago_id, int forma_pago_id, int id_hermandad) {

        this.nombre = nombre;
        this.nif = nif;
        this.apellido = apellido;
        this.municipio = municipio;
        this.provincia = provincia;
        this.pais = pais;
        this.tfno = tfno;
        this.email = email;
        this.banco = banco;
        this.cuenta_bancaria = cuenta_bancaria;
        this.tipo_pago_id = tipo_pago_id;
        this.forma_pago_id = forma_pago_id;
        this.id_hermandad = id_hermandad;

    }

    public persona(String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria, int tipo_pago_id, int forma_pago_id) {

        this.nombre = nombre;
        this.nif = nif;
        this.apellido = apellido;
        this.municipio = municipio;
        this.provincia = provincia;
        this.pais = pais;
        this.tfno = tfno;
        this.email = email;
        this.banco = banco;
        this.cuenta_bancaria = cuenta_bancaria;
        this.tipo_pago_id = tipo_pago_id;
        this.forma_pago_id = forma_pago_id;

    }
    
    
    
    public persona(String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria, int forma_pago_id) {

        this.nombre = nombre;
        this.nif = nif;
        this.apellido = apellido;
        this.municipio = municipio;
        this.provincia = provincia;
        this.pais = pais;
        this.tfno = tfno;
        this.email = email;
        this.banco = banco;
        this.cuenta_bancaria = cuenta_bancaria;
        this.forma_pago_id = forma_pago_id;

    }
    
     public persona(String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria) {

        this.nombre = nombre;
        this.nif = nif;
        this.apellido = apellido;
        this.municipio = municipio;
        this.provincia = provincia;
        this.pais = pais;
        this.tfno = tfno;
        this.email = email;
        this.banco = banco;
        this.cuenta_bancaria = cuenta_bancaria;

    }

    public persona(String nombre, String apellido) {

        this.nombre = nombre;
        this.apellido = apellido;

    }

    public persona(String nombre, String nif, String apellido, String municipio, String provincia, String pais, int tfno, String email, String banco, String cuenta_bancaria, int tipo_pago_id, int id_hermandad, String x) {
        this.nombre = nombre;
        this.nif = nif;
        this.apellido = apellido;
        this.municipio = municipio;
        this.provincia = provincia;
        this.pais = pais;
        this.tfno = tfno;
        this.email = email;
        this.banco = banco;
        this.cuenta_bancaria = cuenta_bancaria;
        this.tipo_pago_id = tipo_pago_id;
        this.id_hermandad = id_hermandad;
    }
    
    

    //constructor personalizado para insercion de datos en la tabla formadepagos

    public persona(int forma_pago_id) {
        this.forma_pago_id = forma_pago_id;
    }

    public int getId_hermandad() {
        return id_hermandad;
    }

    public void setId_hermandad(int id_hermandad) {
        this.id_hermandad = id_hermandad;
    }

    public int getTfno() {
        return tfno;
    }

    public void setTfno(int tfno) {
        this.tfno = tfno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCuenta_bancaria() {
        return cuenta_bancaria;
    }

    public void setCuenta_bancaria(String cuenta_bancaria) {
        this.cuenta_bancaria = cuenta_bancaria;
    }

    public int getTipo_pago_id() {
        return tipo_pago_id;
    }

    public void setTipo_pago_id(int tipo_pago_id) {
        this.tipo_pago_id = tipo_pago_id;
    }

    public int getForma_pago_id() {
        return forma_pago_id;
    }

    public void setForma_pago_id(int forma_pago_id) {
        this.forma_pago_id = forma_pago_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
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

    @Override
    public String toString() {
        return "persona{" + "nombre=" + nombre + ", nif=" + nif + ", apellido=" + apellido + ", municipio=" + municipio + ", provincia=" + provincia + ", pais=" + pais + ", tfno=" + tfno + ", email=" + email + ", banco=" + banco + ", cuenta_bancaria=" + cuenta_bancaria + ", tipo_pago_id=" + tipo_pago_id + ", forma_pago_id=" + forma_pago_id + ", id_hermandad=" + id_hermandad + '}';
    }

}
