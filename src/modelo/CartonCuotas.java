
package modelo;

import java.sql.SQLException;
import java.util.ArrayList;

public class CartonCuotas {
    
    private int identificador;
    private boolean enero;
    private boolean febrero;
    private boolean marzo;
    private boolean abril;
    private boolean mayo;
    private boolean junio;
    private boolean julio;
    private boolean agosto;
    private boolean septiembre;
    private boolean octubre;
    private boolean noviembre;
    private boolean diembre;
    private int año;
    private int numero_hermano;

    public CartonCuotas(int identificador, boolean enero, boolean febrero, boolean marzo, boolean abril, boolean mayo, boolean junio, boolean julio, boolean agosto, boolean septiembre, boolean octubre, boolean noviembre, boolean diembre, int año, int numero_hermano) {
        this.identificador = identificador;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.abril = abril;
        this.mayo = mayo;
        this.junio = junio;
        this.julio = julio;
        this.agosto = agosto;
        this.septiembre = septiembre;
        this.octubre = octubre;
        this.noviembre = noviembre;
        this.diembre = diembre;
        this.año = año;
        this.numero_hermano = numero_hermano;
    }

    public CartonCuotas(int identificador, int año, int numero_hermano) {
        this.identificador = identificador;
        this.año = año;
        this.numero_hermano = numero_hermano;
    }

    public CartonCuotas(int identificador) {
        this.identificador = identificador;
    }   
    
    public CartonCuotas() {
    }   

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public boolean isEnero() {
        return enero;
    }

    public void setEnero(boolean enero) {
        this.enero = enero;
    }

    public boolean isFebrero() {
        return febrero;
    }

    public void setFebrero(boolean febrero) {
        this.febrero = febrero;
    }

    public boolean isMarzo() {
        return marzo;
    }

    public void setMarzo(boolean marzo) {
        this.marzo = marzo;
    }

    public boolean isAbril() {
        return abril;
    }

    public void setAbril(boolean abril) {
        this.abril = abril;
    }

    public boolean isMayo() {
        return mayo;
    }

    public void setMayo(boolean mayo) {
        this.mayo = mayo;
    }

    public boolean isJunio() {
        return junio;
    }

    public void setJunio(boolean junio) {
        this.junio = junio;
    }

    public boolean isJulio() {
        return julio;
    }

    public void setJulio(boolean julio) {
        this.julio = julio;
    }

    public boolean isAgosto() {
        return agosto;
    }

    public void setAgosto(boolean agosto) {
        this.agosto = agosto;
    }

    public boolean isSeptiembre() {
        return septiembre;
    }

    public void setSeptiembre(boolean septiembre) {
        this.septiembre = septiembre;
    }

    public boolean isOctubre() {
        return octubre;
    }

    public void setOctubre(boolean octubre) {
        this.octubre = octubre;
    }

    public boolean isNoviembre() {
        return noviembre;
    }

    public void setNoviembre(boolean noviembre) {
        this.noviembre = noviembre;
    }

    public boolean isDiembre() {
        return diembre;
    }

    public void setDiembre(boolean diembre) {
        this.diembre = diembre;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getNumero_hermano() {
        return numero_hermano;
    }

    public void setNumero_hermano(int numero_hermano) {
        this.numero_hermano = numero_hermano;
    }

    @Override
    public String toString() {
        return "CartonCuotas{" + "identificador=" + identificador + ", enero=" + enero + ", febrero=" + febrero + ", marzo=" + marzo + ", abril=" + abril + ", mayo=" + mayo + ", junio=" + junio + ", julio=" + julio + ", agosto=" + agosto + ", septiembre=" + septiembre + ", octubre=" + octubre + ", noviembre=" + noviembre + ", diembre=" + diembre + ", a\u00f1o=" + año + ", numero_hermano=" + numero_hermano + '}';
    }   
    
    /*Instanciar metodos creados en EntidadesConocidasBD*/
    /*Esta clase me permite instanciar la clase EntidadesConocidasBD y llamar al metodo grabar para hacer uso de el*/
    
    public void grabar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CartonCuotasBD cc = new CartonCuotasBD(this);        
        cc.grabar();
    }

    public void actualizar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CartonCuotasBD cc = new CartonCuotasBD(this);        
        cc.modificar();
    }
    
    /*Este metodo retorna un conjunto de datos de la tabla entidadesconocidas*/
    public void leer() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CartonCuotasBD cc = new CartonCuotasBD(this);       
        cc.leer();
    }
    
    /*Este metodo me permite instanciar la clase EntidadesConocidasBD y llamar al metodo borrar para hacer uso de el*/
    public void borrar() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CartonCuotasBD cc = new CartonCuotasBD(this);        
        cc.borrar();
    }
    
    /*Este metodo lee todos los datos de la tabla entidadesconocidas*/
    public ArrayList leerTodos() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CartonCuotasBD cc = new CartonCuotasBD(this);         
        return cc.leerTodos();
    }
    
    /*Este metodo busca una EntidadConocida indicando el identificador y porque campo se va a filtrar*/
    public ArrayList <CartonCuotas> buscarFiltro(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        CartonCuotasBD cc = new CartonCuotasBD(this);         
        return cc.buscar(filtro, campo);
    }
    
}
