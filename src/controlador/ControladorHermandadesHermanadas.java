package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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
import modelo.HermandadesHermanadas;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermandadesHermanadasVista;

public class ControladorHermandadesHermanadas implements ActionListener {

    HermandadesHermanadas hH;
    HermandadesHermanadasVista hHvista;
    
    /** BOTONES: INGRESAR-SALIR **/
    
    public void iniciar() {
        hH = new HermandadesHermanadas();
        hHvista = new HermandadesHermanadasVista();
        hHvista.setVisible(true);
        hHvista.setLocationRelativeTo(null);
        
        cargarTablaEntidadConocida1();
        
        cargarCmbBD1();
        cargarCmbBD2();

        //se añade las acciones a los controles del formulario padre
        hHvista.btnIngresar.setActionCommand("INGRESAR");
        hHvista.btnBuscar.setActionCommand("BUSCAR1");
        hHvista.btnBuscar2.setActionCommand("BUSCAR2");
        hHvista.btnEliminar.setActionCommand("ELIMINAR");
        hHvista.btnSalir.setActionCommand("SALIR1");
        hHvista.btnSalir2.setActionCommand("SALIR2");
        hHvista.btnSalir3.setActionCommand("SALIR3");
        hHvista.btnSalir4.setActionCommand("SALIR4");
        //Se pone a escuchar las acciones del usuario
        hHvista.btnIngresar.addActionListener(this);
        hHvista.btnBuscar.addActionListener(this);
        hHvista.btnBuscar2.addActionListener(this);
        hHvista.btnEliminar.addActionListener(this);
        hHvista.btnSalir.addActionListener(this);
        hHvista.btnSalir2.addActionListener(this);
        hHvista.btnSalir3.addActionListener(this);
        hHvista.btnSalir4.addActionListener(this);
        
        //Al Hacer click a una fila de la tabla los valores se cargaran en los cuadros de texto correspondientes
        hHvista.tablaEntidadConocida1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
            }
        });
        
        hHvista.tablaEntidadConocida2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida2MousePressed(evt);
            }
        });
        
        hHvista.tablaEntidadConocida3.addMouseListener(new java.awt.event.MouseAdapter() {
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
                String identificador = hHvista.txtIdentificador.getText();
                String nombre = hHvista.txtNombreHh.getText();
                String localidad = hHvista.txtLocalidad.getText();
                String domicilio = hHvista.txtDomicilio.getText();
                String telf1 = hHvista.txtTelf1.getText();
                String telf2 = hHvista.txTelf2.getText();
                String parroquia = hHvista.txtParroquia.getText();
           
                try {
                    agregarHermandadHermanadas(Integer.parseInt(identificador), nombre, parroquia, localidad, domicilio, telf1, telf2);
                    JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                }   
                cargarTablaEntidadConocida1();
                cargarTablaEntidadConocida2();
                cargarTablaEntidadConocida3();
                break;
                
            case "BUSCAR1":
                cargarTablaEntidadConocida2();
                break;
            case "BUSCAR2":
                cargarTablaEntidadConocida3();
                break;
            case "ELIMINAR":
                eliminarSeleccion();
                break;
            case "SALIR1":
                hHvista.dispose();
                break;
            case "SALIR2":
                hHvista.dispose();
                break;
            case "SALIR3":
                hHvista.dispose();
                break;
            case "SALIR4":
                hHvista.dispose();
                break;
        }

    }
    
    private void eliminarSeleccion() {
        String seleccion = hHvista.txtIdentificador4.getText();
        try {
            eliminarHermandadHermanadas(Integer.parseInt(seleccion));
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandadesHermanadas.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaEntidadConocida1();
        limpiarTexto2();
        hHvista.tablaEntidadConocida3.setModel(new DefaultTableModel());
        hHvista.txtFiltro2.setText("");
    }
    
    
    private void cargarCmbBD1() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE hermandadeshermanadas;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            hHvista.cmbBD1.removeAllItems();
            
            while(cbd.resultado.next()){
                hHvista.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandadesHermanadas.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void cargarCmbBD2() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE hermandadeshermanadas;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            hHvista.cmbBD2.removeAllItems();
            
            while(cbd.resultado.next()){
                hHvista.cmbBD2.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandadesHermanadas.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = hHvista.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        hHvista.txtIdentificador2.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 0));
        hHvista.txtNombreEntidad2.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 1));
        hHvista.txtLocalidad2.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 3));
        hHvista.txtDomicilio2.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 2));
        hHvista.txtParroquia2.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 6));
        hHvista.txtTelf12.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 4));
        hHvista.txtTelf22.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 5));
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = hHvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        hHvista.txtIdentificador3.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 0));
        hHvista.txtNombreEntidad3.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 1));
        hHvista.txtLocalidad3.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 3));
        hHvista.txtDomicilio3.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 2));
        hHvista.txtParroquia3.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 6));
        hHvista.txtTelf13.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 4));
        hHvista.txtTelf23.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 5));
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = hHvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        hHvista.txtIdentificador4.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 0));
        hHvista.txtNombreEntidad4.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 1));
        hHvista.txtLocalidad4.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 3));
        hHvista.txtDomicilio4.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 2));
        hHvista.txtParroquia4.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 6));
        hHvista.txtTelf14.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 4));
        hHvista.txtTelf24.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 5));
    }
    
    public void cargarTablaEntidadConocida1() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "Telf1", "Telf2", "Parroquia"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[7];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from hermandadeshermanadas";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            while(cbd.resultado.next()){
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("nombre");
                fila[2] = cbd.resultado.getString("domicilio");
                fila[3] = cbd.resultado.getString("localidad");
                fila[4] = cbd.resultado.getString("telf1");
                fila[5] = cbd.resultado.getString("telf2");
                fila[6] = cbd.resultado.getString("parroquia");
           
                m.addRow(fila);
            }
            
            hHvista.tablaEntidadConocida1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            hHvista.tablaEntidadConocida1.setRowSorter(ordenar);
            hHvista.tablaEntidadConocida1.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaEntidadConocida2() {
        HermandadesHermanadas hermandadesh = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "Telf1", "Telf2", "Parroquia"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[7];
            ArrayList <HermandadesHermanadas> x;
            String campo = (String) hHvista.cmbBD1.getSelectedItem();
            String filtro = hHvista.txtFiltro1.getText();
            x = hH.buscarFiltro(filtro, campo);
            
            Iterator <HermandadesHermanadas> it = x.iterator();
            while(it.hasNext()){
                hermandadesh = it.next();
                fila[0] = String.valueOf(hermandadesh.getId());
                fila[1] = hermandadesh.getNombre();
                fila[2] = hermandadesh.getDomicilio();
                fila[3] = hermandadesh.getLocalidad();
                fila[4] = hermandadesh.getTelf1();
                fila[5] = hermandadesh.getTelf2();
                fila[6] = hermandadesh.getParroquia();
                m.addRow(fila);
            }           
            
            hHvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            hHvista.tablaEntidadConocida2.setRowSorter(ordenar);
            hHvista.tablaEntidadConocida2.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaEntidadConocida3() {
        HermandadesHermanadas hermandadesh = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "Telf1", "Telf2", "Parroquia"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[7];
            ArrayList <HermandadesHermanadas> x;
            String campo = (String) hHvista.cmbBD2.getSelectedItem();
            String filtro = hHvista.txtFiltro2.getText();
            x = hH.buscarFiltro(filtro, campo);
            
            Iterator <HermandadesHermanadas> it = x.iterator();
            while(it.hasNext()){
                hermandadesh = it.next();
                fila[0] = String.valueOf(hermandadesh.getId());
                fila[1] = hermandadesh.getNombre();
                fila[2] = hermandadesh.getDomicilio();
                fila[3] = hermandadesh.getLocalidad();
                fila[4] = hermandadesh.getTelf1();
                fila[5] = hermandadesh.getTelf2();
                fila[6] = hermandadesh.getParroquia();
                m.addRow(fila);
            }           
            
            hHvista.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            hHvista.tablaEntidadConocida3.setRowSorter(ordenar);
            hHvista.tablaEntidadConocida3.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }

    /*Limpiar Texto*/
    public void limpiarTexto() {
        hHvista.txtIdentificador.setText("");
        hHvista.txtNombreHh.setText("");
        hHvista.txtLocalidad.setText("");
        hHvista.txtDomicilio.setText("");
        hHvista.txtTelf1.setText("");
        hHvista.txTelf2.setText("");
        hHvista.txtParroquia.setText("");
        hHvista.txtParroquia.setText("");
    }
    
    /*Limpiar Texto*/
    public void limpiarTexto2() {
        hHvista.txtIdentificador4.setText("");
        hHvista.txtNombreEntidad4.setText("");
        hHvista.txtLocalidad4.setText("");
        hHvista.txtDomicilio4.setText("");
        hHvista.txtTelf14.setText("");
        hHvista.txtTelf24.setText("");
        hHvista.txtParroquia4.setText("");
        hHvista.txtParroquia4.setText("");
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<HermandadesHermanadas> buscarHermandadesHermanadas(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hH = new HermandadesHermanadas();
        return hH.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar una Entidad Conocida*/
    public void agregarHermandadHermanadas(int identificador, String nombre, String domicilio, String localidad, String telf1, String telf2, String parroquia) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hH = new HermandadesHermanadas(identificador, nombre, domicilio, localidad, telf1, telf2, parroquia);
        hH.grabar();
    }

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarHermandad(String nombre, String localidad, String domicilio, String telf1, String telf2, String parroquia) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hH = new HermandadesHermanadas(nombre, domicilio, localidad, telf1, telf2, parroquia);
        hH.actualizar();
    }

    /*Metodo para borrar una Entidad Conocida*/
    public void eliminarHermandadHermanadas(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hH = new HermandadesHermanadas(identificador);
        hH.borrar();
    }

    /*Metodo para leer todas las Entidades Conocidas*/
    public ArrayList<HermandadesHermanadas> leerTodas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hH = new HermandadesHermanadas();
        return hH.leerTodos();
    }
}