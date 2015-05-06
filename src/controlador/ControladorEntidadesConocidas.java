package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.EntidadesConocidas;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.EntidadesConocidasVista;

public class ControladorEntidadesConocidas implements ActionListener {

    EntidadesConocidas ec;
    EntidadesConocidasVista ecv;
    
    /** BOTONES: INGRESAR-SALIR **/
    
    public void iniciar() {
        ec = new EntidadesConocidas();
        ecv = new EntidadesConocidasVista();
        ecv.setVisible(true);
        ecv.setLocationRelativeTo(null);
        
        cargarTablaEntidadConocida1();
        
        cargarCmbBD1();
        cargarCmbBD2();
        cargarCmbBD3();

        //se añade las acciones a los controles del formulario padre
        ecv.btnIngresar.setActionCommand("INGRESAR");
        ecv.btnBuscar.setActionCommand("MOSTRAR");
        ecv.btnBuscar1.setActionCommand("BUSCAR1");
        ecv.btnBuscar2.setActionCommand("BUSCAR2");
        ecv.btnEliminar.setActionCommand("ELIMINAR");
        ecv.btnSalir.setActionCommand("SALIR1");
        ecv.btnSalir2.setActionCommand("SALIR2");
        ecv.btnSalir3.setActionCommand("SALIR3");
        ecv.btnSalir4.setActionCommand("SALIR4");
        //Se pone a escuchar las acciones del usuario
        ecv.btnIngresar.addActionListener(this);
        ecv.btnBuscar.addActionListener(this);
        ecv.btnBuscar1.addActionListener(this);
        ecv.btnBuscar2.addActionListener(this);
        ecv.btnEliminar.addActionListener(this);
        ecv.btnSalir.addActionListener(this);
        ecv.btnSalir2.addActionListener(this);
        ecv.btnSalir3.addActionListener(this);
        ecv.btnSalir4.addActionListener(this);
        
        //Al Hacer click a una fila de la tabla los valores se cargaran en los cuadros de texto correspondientes
        ecv.tablaEntidadConocida1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
            }
        });
        
        ecv.tablaEntidadConocida2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida2MousePressed(evt);
            }
        });
        
        ecv.tablaEntidadConocida3.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida3MousePressed(evt);
            }
        });
    }   

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "INGRESAR":
                String identificador = ecv.txtIdentificador.getText();
                String nombre = ecv.txtNombreEntidad.getText();
                String localidad = ecv.txtLocalidad.getText();
                String domicilio = ecv.txtDomicilio.getText();
                String telf1 = ecv.txtTelf1.getText();
                String telf2 = ecv.txTelf2.getText();
                String cp = ecv.txtCp.getText();
                String provincia = ecv.txtProvincia.getText();
                String email = ecv.txtEmail.getText();
                try {
                    agregarEntidadConocida(Integer.parseInt(identificador), nombre, localidad, domicilio, telf1, telf2, Integer.parseInt(cp), provincia, email);
                    JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    
                    JOptionPane.showMessageDialog(null, "El Identificador " + ecv.txtIdentificador.getText() + " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                }   
                cargarTablaEntidadConocida1();
                cargarTablaEntidadConocida2();
                cargarTablaEntidadConocida3();
                break;
                
            case "MOSTRAR":
                cargarTablaEntidadConocida2();
                break;
                
                case "BUSCAR1":
                cargarTablaEntidadConocida4();
                break;
            case "BUSCAR2":
                cargarTablaEntidadConocida3();
                break;
            case "ELIMINAR":
                eliminarSeleccion();
                break;
            case "SALIR1":
                ecv.dispose();
                break;
            case "SALIR2":
                ecv.dispose();
                break;
            case "SALIR3":
                ecv.dispose();
                break;
            case "SALIR4":
                ecv.dispose();
                break;
        }

    }
    
    private void eliminarSeleccion() {
        String seleccion = ecv.txtIdentificador4.getText();
        try {
            eliminarEntidadConocida(Integer.parseInt(seleccion));
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaEntidadConocida1();
        limpiarTexto2();
        ecv.tablaEntidadConocida3.setModel(new DefaultTableModel());
        ecv.txtFiltro2.setText("");
    }
    
    
    private void cargarCmbBD1() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE entidadesconocidas;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            ecv.cmbBD1.removeAllItems();
            
            while(cbd.resultado.next()){
                ecv.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void cargarCmbBD2() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE entidadesconocidas;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            ecv.cmbBD2.removeAllItems();
            
            while(cbd.resultado.next()){
                ecv.cmbBD2.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void cargarCmbBD3() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE entidadesconocidas;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            ecv.cmbBD3.removeAllItems();
            
            while(cbd.resultado.next()){
                ecv.cmbBD3.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = ecv.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        ecv.txtIdentificador5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 0));
        ecv.txtNombreEntidad5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 1));
        ecv.txtLocalidad5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 3));
        ecv.txtDomicilio5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 2));
        ecv.txtProvincia5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 5));
        ecv.txtCp5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 4));
        ecv.txtTelf15.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 6));
        ecv.txtTelf25.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 7));
        ecv.txtEmail5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 8));
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = ecv.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        ecv.txtIdentificador3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 0));
        ecv.txtNombreEntidad3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 1));
        ecv.txtLocalidad3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 3));
        ecv.txtDomicilio3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 2));
        ecv.txtProvincia3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 5));
        ecv.txtCp3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 4));
        ecv.txtTelf13.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 6));
        ecv.txtTelf23.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 7));
        ecv.txtEmail3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 8));
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = ecv.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        ecv.txtIdentificador4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 0));
        ecv.txtNombreEntidad4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 1));
        ecv.txtLocalidad4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 3));
        ecv.txtDomicilio4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 2));
        ecv.txtProvincia4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 5));
        ecv.txtCp4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 4));
        ecv.txtTelf14.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 6));
        ecv.txtTelf24.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 7));
        ecv.txtEmail4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 8));
    }
    
    public void cargarTablaEntidadConocida1() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[9];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from entidadesconocidas";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            while(cbd.resultado.next()){
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("nombre");
                fila[2] = cbd.resultado.getString("domicilio");
                fila[3] = cbd.resultado.getString("localidad");
                fila[4] = cbd.resultado.getString("cp");
                fila[5] = cbd.resultado.getString("provincia");
                fila[6] = cbd.resultado.getString("telf1");
                fila[7] = cbd.resultado.getString("telf2");
                fila[8] = cbd.resultado.getString("email");
                m.addRow(fila);
            }
            
            ecv.tablaEntidadConocida1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            ecv.tablaEntidadConocida1.setRowSorter(ordenar);
            ecv.tablaEntidadConocida1.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaEntidadConocida2() {
        EntidadesConocidas entidad = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[9];
            ArrayList <EntidadesConocidas> x;
            String campo = (String) ecv.cmbBD1.getSelectedItem();
            String filtro = ecv.txtFiltro1.getText();
            x = ec.buscarFiltro(filtro, campo);
            
            Iterator <EntidadesConocidas> it = x.iterator();
            while(it.hasNext()){
                entidad = it.next();
                fila[0] = String.valueOf(entidad.getIdentificador());
                fila[1] = entidad.getNombre();
                fila[2] = entidad.getDomicilio();
                fila[3] = entidad.getLocalidad();
                fila[4] = String.valueOf(entidad.getCP());
                fila[5] = entidad.getProvincia();
                fila[6] = entidad.getTelf1();
                fila[7] = entidad.getTelf2();
                fila[8] = entidad.getEmail();
                m.addRow(fila);
            }           
            
            ecv.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            ecv.tablaEntidadConocida2.setRowSorter(ordenar);
            ecv.tablaEntidadConocida2.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaEntidadConocida3() {
        EntidadesConocidas entidad = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[9];
            ArrayList <EntidadesConocidas> x;
            String campo = (String) ecv.cmbBD2.getSelectedItem();
            String filtro = ecv.txtFiltro2.getText();
            x = ec.buscarFiltro(filtro, campo);
            
            Iterator <EntidadesConocidas> it = x.iterator();
            while(it.hasNext()){
                entidad = it.next();
                fila[0] = String.valueOf(entidad.getIdentificador());
                fila[1] = entidad.getNombre();
                fila[2] = entidad.getDomicilio();
                fila[3] = entidad.getLocalidad();
                fila[4] = String.valueOf(entidad.getCP());
                fila[5] = entidad.getProvincia();
                fila[6] = entidad.getTelf1();
                fila[7] = entidad.getTelf2();
                fila[8] = entidad.getEmail();
                m.addRow(fila);
            }           
            
            ecv.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            ecv.tablaEntidadConocida3.setRowSorter(ordenar);
            ecv.tablaEntidadConocida3.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaEntidadConocida4() {
        EntidadesConocidas entidad = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[9];
            ArrayList <EntidadesConocidas> x;
            String campo = (String) ecv.cmbBD3.getSelectedItem();
            String filtro = ecv.txtFiltro3.getText();
            x = ec.buscarFiltro(filtro, campo);
            
            Iterator <EntidadesConocidas> it = x.iterator();
            while(it.hasNext()){
                entidad = it.next();
                fila[0] = String.valueOf(entidad.getIdentificador());
                fila[1] = entidad.getNombre();
                fila[2] = entidad.getDomicilio();
                fila[3] = entidad.getLocalidad();
                fila[4] = String.valueOf(entidad.getCP());
                fila[5] = entidad.getProvincia();
                fila[6] = entidad.getTelf1();
                fila[7] = entidad.getTelf2();
                fila[8] = entidad.getEmail();
                m.addRow(fila);
            }           
            
            ecv.tablaEntidadConocida1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            ecv.tablaEntidadConocida1.setRowSorter(ordenar);
            ecv.tablaEntidadConocida1.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }

    /*Limpiar Texto*/
    public void limpiarTexto() {
        ecv.txtIdentificador.setText("");
        ecv.txtNombreEntidad.setText("");
        ecv.txtLocalidad.setText("");
        ecv.txtDomicilio.setText("");
        ecv.txtTelf1.setText("");
        ecv.txTelf2.setText("");
        ecv.txtCp.setText("");
        ecv.txtProvincia.setText("");
        ecv.txtEmail.setText("");
    }
    
    /*Limpiar Texto*/
    public void limpiarTexto2() {
        ecv.txtIdentificador4.setText("");
        ecv.txtNombreEntidad4.setText("");
        ecv.txtLocalidad4.setText("");
        ecv.txtDomicilio4.setText("");
        ecv.txtTelf14.setText("");
        ecv.txtTelf24.setText("");
        ecv.txtCp4.setText("");
        ecv.txtProvincia4.setText("");
        ecv.txtEmail4.setText("");
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<EntidadesConocidas> buscarEntidadesConocidas(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ec = new EntidadesConocidas();
        return ec.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar una Entidad Conocida*/
    public void agregarEntidadConocida(int identificador, String nombre, String localidad, String domicilio, String telf1, String telf2, int cp, String provincia, String email) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ec = new EntidadesConocidas(identificador, nombre, localidad, domicilio, telf1, telf2, cp, provincia, email);
        ec.grabar();
    }

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarEntidadConocida(String nombre, String localidad, String domicilio, String telf1, String telf2, int cp, String provincia, String email) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ec = new EntidadesConocidas(nombre, localidad, domicilio, telf1, telf2, cp, provincia, email);
        ec.actualizar();
    }

    /*Metodo para borrar una Entidad Conocida*/
    public void eliminarEntidadConocida(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ec = new EntidadesConocidas(identificador);
        ec.borrar();
    }

    /*Metodo para leer todas las Entidades Conocidas*/
    public ArrayList<EntidadesConocidas> leerTodas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ec = new EntidadesConocidas();
        return ec.leerTodos();
    }

}