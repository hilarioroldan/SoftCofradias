package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Hermandad;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermandadConfigurarVista;
import vista.HermandadVista;

public class ControladorHermandad implements ActionListener {

    Hermandad h;
    HermandadVista hv;
    DefaultTableModel m;

    public enum di {

        GUARDAR, MODIFICAR, NUEVO, SALIR, CONFIGURAR;
    }

    public void iniciar() {
        h = new Hermandad();
        hv = new HermandadVista();
        hv.setVisible(true);
        hv.setLocationRelativeTo(null);
        cargarTablaHermandades();

        //se añade las acciones a los controles del formulario padre
        hv.btnGuardar.setActionCommand("GUARDAR");
        hv.btnModificar.setActionCommand("MODIFICAR");
        hv.btnNuevo.setActionCommand("NUEVO");
        hv.btnSalir.setActionCommand("SALIR");
        hv.btnConfigurar.setActionCommand("CONFIGURAR");
        //Se pone a escuchar las acciones del usuario
        hv.btnGuardar.addActionListener(this);
        hv.btnModificar.addActionListener(this);
        hv.btnNuevo.addActionListener(this);
        hv.btnSalir.addActionListener(this);
        hv.btnConfigurar.addActionListener(this);
        hv.tblHermandad.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla hermandad           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
    }

    // ACCIONES DEL USUARIO
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (di.valueOf(e.getActionCommand())) {
            case GUARDAR:
                String identificador = hv.txtIdentificador.getText();
                String nombre_hermandad = hv.txtNombreHermandad.getText();
                String año_fundacion = hv.txtAñoFundacion.getText();
                String domicilio = hv.txtDomicilio.getText();
                String municipio = hv.txtMunicipio.getText();
                String provincia = hv.txtProvincia.getText();
                String telf1 = hv.txtTelefono1.getText();    //String en bd
                String telf2 = hv.txtTelefono2.getText();    //String en bd
                String fax = hv.txtFax.getText();    //String bd
                String descripcion = hv.txtDescripcion.getText();

                 {
                    try {
                        agregarHermandad(Integer.parseInt(identificador), nombre_hermandad, Integer.parseInt(año_fundacion), domicilio, municipio, provincia, telf1, telf2, fax, descripcion);
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                        Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                cargarTablaHermandades();

            case SALIR:
                hv.dispose();

            case MODIFICAR:
                String identificadorM = hv.txtIdentificador.getText();
                String nombre_hermandadM = hv.txtNombreHermandad.getText();
                String año_fundacionM = hv.txtAñoFundacion.getText();
                String domicilioM = hv.txtDomicilio.getText();
                String municipioM = hv.txtMunicipio.getText();
                String provinciaM = hv.txtProvincia.getText();
                String telf1M = hv.txtTelefono1.getText();    //String en bd
                String telf2M = hv.txtTelefono2.getText();    //String en bd
                String faxM = hv.txtFax.getText();    //String bd
                String descripcionM = hv.txtDescripcion.getText();
                
                 
                try {
                    modificarHermandad(Integer.parseInt(identificadorM), nombre_hermandadM, Integer.parseInt(año_fundacionM), domicilioM, municipioM, provinciaM, telf1M, telf2M, faxM, descripcionM);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {                        
                    JOptionPane.showMessageDialog(null, "El identificador " + hv.txtIdentificador.getText() + "ya existe, ingrese un identificador distinto", "Error", JOptionPane.ERROR_MESSAGE);
                } catch(NumberFormatException x){
                    
                }                
                cargarTablaHermandades();
                limpiarTexto();
                
            case NUEVO:
                limpiarTexto();
                
            case CONFIGURAR:
                abrirConfigurar();
        }
    }
    
    public void abrirConfigurar() {
        HermandadConfigurarVista hcv = new HermandadConfigurarVista();
        hcv.setVisible(true);
        hcv.setLocationRelativeTo(null);
    }

    /*Metodo para buscar una Hermandad indicando el campo y el valor*/
    public ArrayList<Hermandad> buscarHermandad(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        h = new Hermandad();
        return h.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar una Hermandad*/
    public void agregarHermandad(int identificador, String nombre, int año_fundacion, String domicilio, String municipio, String provincia, String telf1, String telf2, String fax, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        h = new Hermandad(identificador, nombre, año_fundacion, domicilio, municipio, provincia, telf1, telf2, fax, descripcion);
        h.grabar();
    }

    /*Metodo para modificar una Hermandad*/
    public void modificarHermandad(int identificador, String nombre, int año_fundacion, String domicilio, String municipio, String provincia, String telf1, String telf2, String fax, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        h = new Hermandad(identificador, nombre, año_fundacion, domicilio, municipio, provincia, telf1, telf2, fax, descripcion);
        h.actualizar();
    }

    /*Metodo para borrar una Hermandad*/
    public void eliminarHermandad(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Hermandad h;
        h = new Hermandad(identificador);
        h.borrar();
    }

    /*Metodo para leer todas las hermandades*/
    public ArrayList<Hermandad> recargarHermandades() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Hermandad h;
        h = new Hermandad();
        return h.leerTodos();
    }

    public void cargarTablaHermandades() {
        DefaultTableModel m;

        try {
            String titulo[] = {"Nro", "Nombre hermandad", "Año fundacion", "Domicilio", "Municipio", "Provincia", "Telf1", "Telf2", "Fax", "Descripcion"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[10];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from hermandad";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                fila[2] = cdb.resultado.getString(3);
                fila[3] = cdb.resultado.getString(4);
                fila[4] = cdb.resultado.getString(5);
                fila[5] = cdb.resultado.getString(6);
                fila[6] = cdb.resultado.getString(7);
                fila[7] = cdb.resultado.getString(8);
                fila[8] = cdb.resultado.getString(9);
                fila[9] = cdb.resultado.getString(10);
                m.addRow(fila);
            }

            hv.tblHermandad.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hv.tblHermandad.setRowSorter(ordenar);
            hv.tblHermandad.setModel(m);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {
        int clic = hv.tblHermandad.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic!=-1) {
            hv.txtIdentificador.setText(hv.tblHermandad.getValueAt(clic, 0).toString());
            hv.txtNombreHermandad.setText(hv.tblHermandad.getValueAt(clic, 1).toString());
            hv.txtAñoFundacion.setText(hv.tblHermandad.getValueAt(clic, 2).toString());
            hv.txtDomicilio.setText(hv.tblHermandad.getValueAt(clic, 3).toString());
            hv.txtMunicipio.setText(hv.tblHermandad.getValueAt(clic, 4).toString());
            hv.txtProvincia.setText(hv.tblHermandad.getValueAt(clic, 5).toString());
            hv.txtTelefono1.setText(hv.tblHermandad.getValueAt(clic, 6).toString());
            hv.txtTelefono2.setText(hv.tblHermandad.getValueAt(clic, 7).toString());
            hv.txtFax.setText(hv.tblHermandad.getValueAt(clic, 8).toString());
            hv.txtDescripcion.setText(hv.tblHermandad.getValueAt(clic, 9).toString());
        }
    }
    
    public void limpiarTexto() {
        hv.txtIdentificador.setText("");
        hv.txtNombreHermandad.setText("");
        hv.txtAñoFundacion.setText("");
        hv.txtDomicilio.setText("");
        hv.txtMunicipio.setText("");
        hv.txtProvincia.setText("");
        hv.txtTelefono1.setText("");
        hv.txtTelefono2.setText("");
        hv.txtFax.setText("");
        hv.txtDescripcion.setText("");
        hv.txtNumeroDeHermanos.setText("");
    }

}
