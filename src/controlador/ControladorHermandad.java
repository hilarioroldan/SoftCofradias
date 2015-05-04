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
import modelo.FormaPago;
import modelo.Hermandad;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermandadConfigurarVista;
import vista.HermandadVista;

public class ControladorHermandad implements ActionListener {

    Hermandad h;
    HermandadVista hv;
    HermandadConfigurarVista hcv;
    DefaultTableModel m;

    public enum di {

        GUARDAR, MODIFICAR, NUEVO, SALIR, CONFIGURAR, INSERTARFP, MOSTRARFP, MODIFICARFP, ELIMINARFP, INSERTARTP, MOSTRARTP, MODIFICARTP, ELIMINARTP, btnInsertarFormaPago, btnSalir1, btnSalir2, btnSalir3, btnSalir4, btnModificar1, btnEliminar1;
    }

    public void iniciar() {
        h = new Hermandad();
        hv = new HermandadVista();
        hcv = new HermandadConfigurarVista();
        hv.setVisible(true);
        hv.setLocationRelativeTo(null);
        cargarTablaHermandades();

        //se añade las acciones a los controles del formulario padre
        hv.btnGuardar.setActionCommand("GUARDAR");
        hv.btnModificar.setActionCommand("MODIFICAR");
        hv.btnNuevo.setActionCommand("NUEVO");
        hv.btnSalir.setActionCommand("SALIR");
        hv.btnConfigurar.setActionCommand("CONFIGURAR");
        hcv.btnInsertarFP.setActionCommand("INSERTARFP");
        hcv.btnMostrarFP.setActionCommand("MOSTRARFP");
        hcv.btnModificarFP.setActionCommand("MODIFICARFP");
        hcv.btnEliminarFP.setActionCommand("ELIMINARFP");
        hcv.btnInsertarTP.setActionCommand("INSERTARTP");
        hcv.btnMostrarTP.setActionCommand("MOSTRARTP");
        hcv.btnModificarTP.setActionCommand("MODIFICARTP");
        hcv.btnEliminarTP.setActionCommand("ELIMINARTP");
        hcv.btnInsertarFormaPago.setActionCommand("btnInsertarFormaPago");
        hcv.btnSalir1.setActionCommand("btnSalir1");
        hcv.btnSalir2.setActionCommand("btnSalir2");
        hcv.btnSalir3.setActionCommand("btnSalir3");
        hcv.btnSalir4.setActionCommand("btnSalir4");
        hcv.btnModificar1.setActionCommand("btnModificar1");
        hcv.btnEliminar1.setActionCommand("btnEliminar1");
        //Se pone a escuchar las acciones del usuario
        hv.btnGuardar.addActionListener(this);
        hv.btnModificar.addActionListener(this);
        hv.btnNuevo.addActionListener(this);
        hv.btnSalir.addActionListener(this);
        hv.btnConfigurar.addActionListener(this);
        hcv.btnInsertarFP.addActionListener(this);
        hcv.btnMostrarFP.addActionListener(this);
        hcv.btnModificarFP.addActionListener(this);
        hcv.btnEliminarFP.addActionListener(this);
        hcv.btnInsertarTP.addActionListener(this);
        hcv.btnMostrarTP.addActionListener(this);
        hcv.btnModificarTP.addActionListener(this);
        hcv.btnEliminarTP.addActionListener(this);
        hcv.btnInsertarFormaPago.addActionListener(this);
        hcv.btnSalir1.addActionListener(this);
        hcv.btnSalir2.addActionListener(this);
        hcv.btnSalir3.addActionListener(this);
        hcv.btnSalir4.addActionListener(this);
        hcv.btnModificar1.addActionListener(this);
        hcv.btnEliminar1.addActionListener(this);
        hv.tblHermandad.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla hermandad           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        
        hcv.tblFormaPago1.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla forma pago           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed2(evt);
            }
        });
        
        hcv.tblFormaPago2.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla forma pago 2          
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed3(evt);
            }
        });
        
        hcv.tblFormaPago3.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla forma pago 3          
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed4(evt);
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
                break;

            case SALIR:
                hv.dispose();
                break;

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
                break;
                
            case NUEVO:
                limpiarTexto();
                break;
                
            case CONFIGURAR:
                abrirConfigurar();
                break;
                
            case INSERTARFP:
                insertarFP();
                break;
                
            case MOSTRARFP:
                mostrarFP();
                break;
                
            case MODIFICARFP:
                modificarFP();
                break;
                
            case ELIMINARFP:
                eliminarFP();
                break;
                
            case INSERTARTP:
            insertarTP();
                break;
                
            case MOSTRARTP:
                mostrarTP();
                break;
                
            case MODIFICARTP:
                modificarTP();
                break;
                
            case ELIMINARTP:
                eliminarTP();
                break;
                
            case btnInsertarFormaPago:
                insertarFormaDePago();
                actualizarTblFormaPago1();
                break;
                
            case btnModificar1:
                modificarFormaDePago();
                actualizarTblFormaPago2();
                actualizarTblFormaPago1();
                break;
                
            case btnEliminar1:
                eliminarFormaPago1();
                actualizarTblFormaPago3();
                actualizarTblFormaPago2();
                actualizarTblFormaPago1();
                break;
                
            case btnSalir1:
                hcv.formaPagoInsertar.dispose();
                hcv.setVisible(true);
                break;
                
            case btnSalir2:
                hcv.formaPagoMostrar.dispose();
                hcv.setVisible(true);
                break;
                
                case btnSalir3:
                hcv.formaPagoModificar.dispose();
                hcv.setVisible(true);
                break;
                    
                    case btnSalir4:
                hcv.formaPagoEliminar.dispose();
                hcv.setVisible(true);
                break;
        }
    }
    
    public void eliminarFormaPago1() {
        int identificador = Integer.parseInt(hcv.txtIdentificador4.getText());
        try {
            eliminarFormaPago(identificador);
            JOptionPane.showMessageDialog(null, "¡Eliminado correctamente!");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
    }
    
    public void modificarFormaDePago() {
        try {
            int identificador = Integer.parseInt(hcv.txtIdentificador3.getText());
            String descripcion = hcv.txtDescripcion3.getText();
            modificarFormaPago(identificador, descripcion);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insertarFormaDePago() {
        try {
            int identificador = Integer.parseInt(hcv.txtIdentificador1.getText());
        String descripcion = hcv.txtDescripcion1.getText();
        agregarFormaPago(identificador, descripcion);  
        } catch (Exception e) {
        }
             
    }
    
    public void actualizarTblFormaPago1(){
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Forma de pago"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[2];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from formapago";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                m.addRow(fila);
            }

            hcv.tblFormaPago1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hcv.tblFormaPago1.setRowSorter(ordenar);
            hcv.tblFormaPago1.setModel(m);
            
        }  catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarTblFormaPago2(){
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Forma de pago"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[2];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from formapago";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                m.addRow(fila);
            }

            hcv.tblFormaPago2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hcv.tblFormaPago2.setRowSorter(ordenar);
            hcv.tblFormaPago2.setModel(m);
            
        }  catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void actualizarTblFormaPago3(){
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Forma de pago"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[2];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from formapago";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                m.addRow(fila);
            }

            hcv.tblFormaPago3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hcv.tblFormaPago3.setRowSorter(ordenar);
            hcv.tblFormaPago3.setModel(m);
            
        }  catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void insertarFP(){
        hcv.formaPagoInsertar.setVisible(true);
        hcv.formaPagoInsertar.setLocation(500,200);
        hcv.formaPagoInsertar.setSize(500, 300);
        hcv.setVisible(false);
    }
    
    public void mostrarFP(){
        hcv.formaPagoMostrar.setVisible(true);
        hcv.formaPagoMostrar.setLocation(500, 200);
        hcv.formaPagoMostrar.setSize(500, 400);
        hcv.setVisible(false);
        actualizarTblFormaPago1();
    }
    
    public void modificarFP(){
        hcv.formaPagoModificar.setVisible(true);
        hcv.formaPagoModificar.setLocation(500, 200);
        hcv.formaPagoModificar.setSize(500, 400);        
        hcv.setVisible(false);
        actualizarTblFormaPago2();
    }
    
    public void eliminarFP(){
        hcv.formaPagoEliminar.setVisible(true);
        hcv.formaPagoEliminar.setLocation(500, 200);
        hcv.formaPagoEliminar.setSize(500, 400);
        hcv.setVisible(false);
        actualizarTblFormaPago3();
    }
    
    public void insertarTP(){
        JOptionPane.showMessageDialog(null, "insertartp");
    }
    
    public void mostrarTP(){
        JOptionPane.showMessageDialog(null, "mostrartp");
    }
    
    public void modificarTP(){
        JOptionPane.showMessageDialog(null, "modificartp");
    }
    
    public void eliminarTP(){
        JOptionPane.showMessageDialog(null, "eliminartp");
    }
    
    public void abrirConfigurar() {        
        hcv.setVisible(true);
        hcv.setLocationRelativeTo(hv);
    }
    
    /*Metodo para agregar un pago de cuota*/
    public void agregarFormaPago(int identificador, String tipo_pago) {
        FormaPago x = new FormaPago(identificador, tipo_pago);
        try {
            x.grabar();
            JOptionPane.showMessageDialog(null, "Pago de cuota insertada correctamente");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Identificador " + identificador + " ya existe, ingrese un identificador distinto " + ex, "SofCofradias", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /*Metodo para modificar un pago de cuota*/
    public void modificarFormaPago(int identificador, String nombre) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            FormaPago x = new FormaPago(identificador, nombre);        
            x.actualizar();
            JOptionPane.showMessageDialog(null, "¡Modificado correctamente!");
        } catch (Exception e) {
        }
    }
    
    /*Metodo para borrar una forma de pago*/
    public void eliminarFormaPago(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        FormaPago x = new FormaPago(identificador);
        x.borrar();
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
            try {
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
            } catch (Exception e) {
            }
        }
    }
    
    private void jTable1MousePressed2(java.awt.event.MouseEvent evt) {
        int clic = hcv.tblFormaPago1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic!=-1) {
            try {
                hcv.txtIdentificador2.setText(hcv.tblFormaPago1.getValueAt(clic, 0).toString());
                hcv.txtDescripcion2.setText(hcv.tblFormaPago1.getValueAt(clic, 1).toString());
            } catch (Exception e) {
            }
        }
    }
    
    private void jTable1MousePressed3(java.awt.event.MouseEvent evt) {
        int clic = hcv.tblFormaPago2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic!=-1) {
            try {
                hcv.txtIdentificador3.setText(hcv.tblFormaPago2.getValueAt(clic, 0).toString());
                hcv.txtDescripcion3.setText(hcv.tblFormaPago2.getValueAt(clic, 1).toString());
            } catch (Exception e) {
            }
        }
    }
    
    private void jTable1MousePressed4(java.awt.event.MouseEvent evt) {
        int clic = hcv.tblFormaPago3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic!=-1) {
            try {
                hcv.txtIdentificador4.setText(hcv.tblFormaPago3.getValueAt(clic, 0).toString());
                hcv.txtDescripcion4.setText(hcv.tblFormaPago3.getValueAt(clic, 1).toString());
            } catch (Exception e) {
            }
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
