package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import modelo.PlaningSecretaria;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.PlaningSecretariaVista;

public class ControladorPlaningSecretaria implements ActionListener {

    PlaningSecretaria pS;
    PlaningSecretariaVista pSvista;
    
    
    public void iniciar() {
        //Planing
        pS = new PlaningSecretaria();
        pSvista = new PlaningSecretariaVista();
        pSvista.setVisible(true);
        pSvista.setLocationRelativeTo(null);
        
        cargarTablaSecretaria();
        
        cargarCmbBD1();
        cargarCmbBD2();
        cargarCmbBD3();
        //se añade las acciones a los controles del formulario padre
        pSvista.btnIngresar.setActionCommand("INGRESAR");
        pSvista.btnBuscar.setActionCommand("BUSCAR1");
        pSvista.btnBuscar2.setActionCommand("BUSCAR2");
        pSvista.btnBuscar3.setActionCommand("BUSCAR3");
        pSvista.btnEliminar.setActionCommand("ELIMINAR");
        pSvista.btnSalir.setActionCommand("SALIR1");
        pSvista.btnSalir5.setActionCommand("SALIR2");
        pSvista.btnSalir3.setActionCommand("SALIR3");
        pSvista.btnSalir4.setActionCommand("SALIR4");
        pSvista.modificar3.setActionCommand("MODIFICAR");
        //Se pone a escuchar las acciones del usuario
        pSvista.btnIngresar.addActionListener(this);
        pSvista.btnBuscar.addActionListener(this);
        pSvista.btnBuscar2.addActionListener(this);
        pSvista.btnBuscar3.addActionListener(this);
        pSvista.btnEliminar.addActionListener(this);
        pSvista.btnSalir.addActionListener(this);
        pSvista.btnSalir5.addActionListener(this);
        pSvista.btnSalir3.addActionListener(this);
        pSvista.btnSalir4.addActionListener(this);
        pSvista.modificar3.addActionListener(this);
        
        //Al Hacer click a una fila de la tabla los valores se cargaran en los cuadros de texto correspondientes
        pSvista.tablaEntidadConocida4.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
            }
        });
        
        pSvista.tablaEntidadConocida2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida2MousePressed(evt);
            }
        });
        
        pSvista.tablaEntidadConocida3.addMouseListener(new java.awt.event.MouseAdapter() {
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
                String identificador = pSvista.txtIdentificador.getText();
                String hora = pSvista.txtHora.getText();
                String fecha = pSvista.txtFecha.getText();
                String descripcion = pSvista.txtDescripcion.getText();
                
                try {
                   
                    agregarPlaning(Integer.parseInt(identificador), hora, Date.valueOf(fecha), descripcion);
                    JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                }   
                cargarTablaSecretaria();
                cargarTablaSecretaria2();
                cargarTablaSecretaria3();
                break;
                
            case "BUSCAR1":
                cargarTablaSecretaria2();
                break;
            case "BUSCAR2":
                cargarTablaSecretaria3();
                break;
            case "BUSCAR3":
                cargarTabla4();
                break;
            case "ELIMINAR":
                eliminarSeleccion();
                break;
            case "MODIFICAR":
                String identificador3 = pSvista.txtIdentificador5.getText();
                String hora3= pSvista.txthora5.getText();
                String fecha3 = pSvista.txtfecha5.getText();
                String descripcion3 = pSvista.txtDescripcion5.getText();
                
                try {
                    
                    modificarPlaning(Integer.parseInt(identificador3),hora3, Date.valueOf(fecha3), descripcion3);
                    JOptionPane.showMessageDialog(null, "¡Modificado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();
                    
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                }
                
                
                break;
            case "SALIR1":
                pSvista.dispose();
                break;
            case "SALIR2":
                pSvista.dispose();
                break;
            case "SALIR3":
                pSvista.dispose();
                break;
            case "SALIR4":
                pSvista.dispose();
                break;
        }

    }
    
    private void eliminarSeleccion() {
        String seleccion = pSvista.txtIdentificador4.getText();
        try {
            eliminarHermandadHermanadas(Integer.parseInt(seleccion));
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaSecretaria();
        limpiarTexto2();
        pSvista.tablaEntidadConocida3.setModel(new DefaultTableModel());
        pSvista.txtFiltro2.setText("");
    }
    
    
    private void cargarCmbBD1() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE planingsecretaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pSvista.cmbBD1.removeAllItems();
            
            while(cbd.resultado.next()){
                pSvista.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void cargarCmbBD2() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE planingsecretaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pSvista.cmbBD2.removeAllItems();
            
            while(cbd.resultado.next()){
                pSvista.cmbBD2.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void cargarCmbBD3() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE planingsecretaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pSvista.cmbBD3.removeAllItems();
            
            while(cbd.resultado.next()){
                pSvista.cmbBD3.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = pSvista.tablaEntidadConocida4.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        pSvista.txtIdentificador5.setText((String) pSvista.tablaEntidadConocida4.getValueAt(clic, 0));
        pSvista.txthora5.setText((String) pSvista.tablaEntidadConocida4.getValueAt(clic, 1));
        pSvista.txtfecha5.setText((String) pSvista.tablaEntidadConocida4.getValueAt(clic, 3));
        pSvista.txtDescripcion5.setText((String) pSvista.tablaEntidadConocida4.getValueAt(clic, 2));
        
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = pSvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        pSvista.txtIdentificador3.setText((String) pSvista.tablaEntidadConocida2.getValueAt(clic, 0));
        pSvista.txtHora3.setText((String) pSvista.tablaEntidadConocida2.getValueAt(clic, 1));
        pSvista.txtFecha3.setText((String) pSvista.tablaEntidadConocida2.getValueAt(clic, 3));
        pSvista.txtDescripcion3.setText((String) pSvista.tablaEntidadConocida2.getValueAt(clic, 2));
        
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = pSvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        pSvista.txtIdentificador4.setText((String) pSvista.tablaEntidadConocida3.getValueAt(clic, 0));
        pSvista.txtNombreEntidad4.setText((String) pSvista.tablaEntidadConocida3.getValueAt(clic, 1));
        pSvista.txtLocalidad4.setText((String) pSvista.tablaEntidadConocida3.getValueAt(clic, 3));
        pSvista.txtDescripcion4.setText((String) pSvista.tablaEntidadConocida2.getValueAt(clic, 2));
        
    }
    
    public void cargarTablaSecretaria() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Hora", "Descripcion", "Fecha"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[4];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from planingsecretaria";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            while(cbd.resultado.next()){
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("hora");
                fila[3] = cbd.resultado.getString("fecha");
                fila[2] = cbd.resultado.getString("descripcion");                
                m.addRow(fila);
            }
            
            pSvista.tablaEntidadConocida4.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            pSvista.tablaEntidadConocida4.setRowSorter(ordenar);
            pSvista.tablaEntidadConocida4.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTabla4() {
        PlaningSecretaria planingsecretaria = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Hora", "Descripcion", "Fecha"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[4];
            ArrayList <PlaningSecretaria> x;
            String campo = (String) pSvista.cmbBD3.getSelectedItem();
            String filtro = pSvista.txtFiltro3.getText();
            x = pS.buscarFiltro(filtro, campo);
            
            Iterator <PlaningSecretaria> it = x.iterator();
            while(it.hasNext()){
                planingsecretaria = it.next();
                fila[0] = String.valueOf(planingsecretaria.getId());
                fila[1] = planingsecretaria.getHora();
                fila[3] = planingsecretaria.getFecha().toString();
                fila[2] = planingsecretaria.getDescripcion();
                m.addRow(fila);
            }           
            
            pSvista.tablaEntidadConocida4.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            pSvista.tablaEntidadConocida4.setRowSorter(ordenar);
            pSvista.tablaEntidadConocida4.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaSecretaria2() {
        PlaningSecretaria planingsecretaria = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Hora", "Descripcion", "Fecha"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[4];
            ArrayList <PlaningSecretaria> x;
            String campo = (String) pSvista.cmbBD1.getSelectedItem();
            String filtro = pSvista.txtFiltro1.getText();
            x = pS.buscarFiltro(filtro, campo);
            
            Iterator <PlaningSecretaria> it = x.iterator();
            while(it.hasNext()){
                planingsecretaria = it.next();
                fila[0] = String.valueOf(planingsecretaria.getId());
                fila[1] = planingsecretaria.getHora();
                fila[3] = planingsecretaria.getFecha().toString();
                fila[2] = planingsecretaria.getDescripcion();
                m.addRow(fila);
            }           
            
            pSvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            pSvista.tablaEntidadConocida2.setRowSorter(ordenar);
            pSvista.tablaEntidadConocida2.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaSecretaria3() {
        PlaningSecretaria planing = null;
        DefaultTableModel m;
        try {
                        
            String[] titulo = {"Nro", "Hora", "Descripcion", "Fecha"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[4];
            ArrayList <PlaningSecretaria> x;
            String campo = (String) pSvista.cmbBD2.getSelectedItem();
            String filtro = pSvista.txtFiltro2.getText();
            x = pS.buscarFiltro(filtro, campo);
            
            Iterator <PlaningSecretaria> it = x.iterator();
            while(it.hasNext()){
                planing = it.next();
                fila[0] = String.valueOf(planing.getId());
                fila[1] = planing.getHora();
                fila[3] = planing.getFecha().toString();
                fila[2] = planing.getDescripcion();
           
                m.addRow(fila);
            }           
            
            pSvista.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            pSvista.tablaEntidadConocida3.setRowSorter(ordenar);
            pSvista.tablaEntidadConocida3.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }

    /*Limpiar Texto*/
    public void limpiarTexto() {
        pSvista.txtIdentificador.setText("");
        pSvista.txtHora.setText("");
        pSvista.txtFecha.setText("");
        pSvista.txtDescripcion.setText("");
    }
    
    /*Limpiar Texto*/
    public void limpiarTexto2() {
        pSvista.txtIdentificador4.setText("");
        pSvista.txtNombreEntidad4.setText("");
        pSvista.txtLocalidad4.setText("");
        pSvista.txtDescripcion4.setText("");
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<PlaningSecretaria> buscarPlaning(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        pS = new PlaningSecretaria();
        return pS.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar una Entidad Conocida*/
    public void agregarPlaning(int id,String hora, Date fecha, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        pS = new PlaningSecretaria(id,hora,fecha,descripcion);
        pS.grabar();
    }

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarPlaning(int identificador,String hora,Date fecha, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        pS = new PlaningSecretaria(identificador,hora,fecha,descripcion);
        pS.actualizar();
    }

    /*Metodo para borrar una Entidad Conocida*/
    public void eliminarHermandadHermanadas(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        pS = new PlaningSecretaria(identificador);
        pS.borrar();
    }

    /*Metodo para leer todas las Entidades Conocidas*/
    public ArrayList<PlaningSecretaria> leerTodas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        pS = new PlaningSecretaria();
        return pS.leerTodos();
    }
}