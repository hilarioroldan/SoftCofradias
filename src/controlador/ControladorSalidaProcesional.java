package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.SalidaProcesional;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.SalidaProcesionalVista;

public class ControladorSalidaProcesional implements ActionListener {

    SalidaProcesional sp;
    SalidaProcesionalVista spvista;
    
    
    public void iniciar() {
        sp = new SalidaProcesional();
        spvista = new SalidaProcesionalVista();
        spvista.setSize(1000, 700);
        spvista.setVisible(true);
        spvista.setLocationRelativeTo(null);
        
        cargarTablaFicha();
        
        cargarCmbBD1();
        cargarCmbBD2();
        
          
        spvista.txtIdentificador5.setEnabled(false);
        spvista.txtIdentificador7.setEnabled(false);
        spvista.txtIdentificador8.setEnabled(false);
        

        //se añade las acciones a los controles del formulario padre
        spvista.btnIngresar.setActionCommand("INGRESAR");
        spvista.btnBuscar.setActionCommand("BUSCAR1");
        spvista.btnBuscar2.setActionCommand("BUSCAR2");
        spvista.btnEliminar.setActionCommand("ELIMINAR");
        spvista.btnSalir.setActionCommand("SALIR1");
        spvista.btnSalir2.setActionCommand("SALIR2");
        spvista.btnSalir3.setActionCommand("SALIR3");
        spvista.btnSalir4.setActionCommand("SALIR4");
        spvista.modificar3.setActionCommand("MODIFICAR");
        
        //Se pone a escuchar las acciones del usuario
        spvista.btnIngresar.addActionListener(this);
        spvista.btnBuscar.addActionListener(this);
        spvista.btnBuscar2.addActionListener(this);
        spvista.btnEliminar.addActionListener(this);
        spvista.btnSalir.addActionListener(this);
        spvista.btnSalir2.addActionListener(this);
        spvista.btnSalir3.addActionListener(this);
        spvista.btnSalir4.addActionListener(this);
        spvista.modificar3.addActionListener(this);
        
        //Al Hacer click a una fila de la tabla los valores se cargaran en los cuadros de texto correspondientes
        spvista.tablaEntidadConocida1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
            }
        });
        
        spvista.tablaEntidadConocida2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida2MousePressed(evt);
            }
        });
        
        spvista.tablaEntidadConocida3.addMouseListener(new java.awt.event.MouseAdapter() {
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
                ingresar();
                break;
                
            case "BUSCAR1":
                cargarTablaFicha2();
                break;
            case "BUSCAR2":
                cargarTablaFicha3();
                break;
            case "ELIMINAR":
                eliminarSeleccion();
                break;
            case "MODIFICAR":
                modificar();               
                break;
            case "SALIR1":
                spvista.dispose();
                break;
            case "SALIR2":
                spvista.dispose();
                break;
            case "SALIR3":
                spvista.dispose();
                break;
            case "SALIR4":
                spvista.dispose();
                break;
        }

    }
    
    public void ingresar() {
                try {
                    Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
                    cbd.resultado = cbd.un_st.executeQuery("select max(identificador) from salidaprocesional");
                    int identificador = 1;
                    if (cbd.resultado.next()) {
                        identificador = cbd.resultado.getInt(1)+1;
                    } else {
                        identificador = 1;
                    }
                    // trabajando con fechas
                    String fecha = "";
                    if (spvista.jDateChooser1.getDate()!=null) {
                        int año = spvista.jDateChooser1.getCalendar().get(Calendar.YEAR);
                        int mes = spvista.jDateChooser1.getCalendar().get(Calendar.MONTH);
                        int dia = spvista.jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                        fecha = año + "-" + mes + "-" + dia;
                    }
                    // fin trabajando con fechas
                    String descripcion = String.valueOf(spvista.txtdescripcion.getText());
                    
                    try {
                        agregarSalida(identificador, fecha, descripcion);
                        JOptionPane.showMessageDialog(null, "Insertado Correctamente");
                        limpiarTexto();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCospadias", JOptionPane.ERROR_MESSAGE);
                    }
                    cargarTablaFicha();
                    cargarTablaFicha2();
                    cargarTablaFicha3();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    Logger.getLogger(ControladorSalidaProcesional.class.getName()).log(Level.SEVERE, null, ex);
                }   
    }
    
    public void modificar() {
        int identificador3 = Integer.parseInt(spvista.txtIdentificador7.getText());
                // trabajando con fechas
                String fecha = "";
                if (spvista.jDateChooser3.getDate()!=null) {
                int año = spvista.jDateChooser3.getCalendar().get(Calendar.YEAR);
                int mes = spvista.jDateChooser3.getCalendar().get(Calendar.MONTH);
                int dia = spvista.jDateChooser3.getCalendar().get(Calendar.DAY_OF_MONTH);
                fecha = año + "-" + mes + "-" + dia;
                }
                // fin trabajando con fechas
                String descripcion3 = String.valueOf(spvista.txtdescripcion4.getText());
                
                
                try {
                    modificarSalida(identificador3,fecha,descripcion3);
                    JOptionPane.showMessageDialog(null, "Modificado Correctamente");
                    cargarTablaFicha();
                    cargarTablaFicha2();
                    cargarTablaFicha3();
                    limpiarTexto();    
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
    }
    
    private void eliminarSeleccion() {
        String seleccion = spvista.txtIdentificador8.getText();
        try {
            eliminarSalida(Integer.parseInt(seleccion));
            JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
            spvista.txtFiltro2.setText("");
            cargarTablaFicha();
            cargarTablaFicha2();
            cargarTablaFicha3();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorSalidaProcesional.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiarTexto2();
        //spvista.tablaEntidadConocida3.setModel(new DefaultTableModel());
        
    }
    
    
    private void cargarCmbBD1() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE salidaprocesional;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            spvista.cmbBD1.removeAllItems();
            
            while(cbd.resultado.next()){
                spvista.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorSalidaProcesional.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void cargarCmbBD2() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE salidaprocesional;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            spvista.cmbBD2.removeAllItems();
            
            while(cbd.resultado.next()){
                spvista.cmbBD2.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorSalidaProcesional.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            
            int clic = spvista.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
            
            spvista.txtIdentificador5.setText((String) spvista.tablaEntidadConocida1.getValueAt(clic, 0));
            String id = (String) spvista.tablaEntidadConocida1.getValueAt(clic, 0);
            cbd.resultado = cbd.un_st.executeQuery("select fecha from salidaprocesional where identificador="+id);
            // trabajando con fecha
            Date fecha = null;
            if (cbd.resultado.next()) {
                fecha = cbd.resultado.getDate(1);
            }
            spvista.jDateChooser2.setDate(fecha);
            // fin trabajando con fecha
            spvista.txtdescripcion2.setText((String) spvista.tablaEntidadConocida1.getValueAt(clic, 2));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorSalidaProcesional.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) { 
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            int clic = spvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
            
            
            spvista.txtIdentificador7.setText((String) spvista.tablaEntidadConocida2.getValueAt(clic, 0));
            String id = (String) spvista.tablaEntidadConocida2.getValueAt(clic, 0);
            cbd.resultado = cbd.un_st.executeQuery("select fecha from salidaprocesional where identificador="+id);
            // trabajando con fecha
            Date fecha = null;
            if (cbd.resultado.next()) {
                fecha = cbd.resultado.getDate(1);
            }
            spvista.jDateChooser3.setDate(fecha);
            // fin trabajando con fecha
            spvista.txtdescripcion4.setText((String) spvista.tablaEntidadConocida2.getValueAt(clic, 2));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorSalidaProcesional.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) { 
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            int clic = spvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
            
            spvista.txtIdentificador8.setText((String) spvista.tablaEntidadConocida3.getValueAt(clic, 0));
            String id = (String) spvista.tablaEntidadConocida3.getValueAt(clic, 0);       
            cbd.resultado = cbd.un_st.executeQuery("select fecha from salidaprocesional where identificador="+id);
// trabajando con fecha
            Date fecha = null;
            if (cbd.resultado.next()) {
                fecha = cbd.resultado.getDate(1);
            }
            spvista.jDateChooser4.setDate(fecha);
            // fin trabajando con fecha
            spvista.txtdescripcion5.setText((String) spvista.tablaEntidadConocida3.getValueAt(clic, 2));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorSalidaProcesional.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cargarTablaFicha() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Año", "Descripcion"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[3];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from salidaprocesional order by identificador";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            while(cbd.resultado.next()){
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("fecha");
                fila[2] = cbd.resultado.getString("descripcion_salida_procesional");
                
                m.addRow(fila);
            }
            
            spvista.tablaEntidadConocida1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            spvista.tablaEntidadConocida1.setRowSorter(ordenar);
            spvista.tablaEntidadConocida1.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e+"Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaFicha2() {
        SalidaProcesional ficha = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Año", "Descripcion"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[3];
            ArrayList <SalidaProcesional> x;
            String campo = (String) spvista.cmbBD1.getSelectedItem();
            String filtro = spvista.txtFiltro1.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
                
                x = sp.buscarFiltro(filtro, campo);
                
                if (x.size()>0) {
                    Iterator <SalidaProcesional> it = x.iterator();
            while(it.hasNext()){
                ficha = it.next();
                fila[0] = String.valueOf(ficha.getIdentificador());
                fila[1] = String.valueOf(ficha.getAnio());
                fila[2] = String.valueOf(ficha.getDescripcion());
                m.addRow(fila);
            }           
            
            spvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            spvista.tablaEntidadConocida2.setRowSorter(ordenar);
            spvista.tablaEntidadConocida2.setModel(m);
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                }
            
            
            
            } else {
                
            }
            
            
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e+"Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaFicha3() {
        SalidaProcesional ficha = null;
        DefaultTableModel m;
        try {
                        
            String[] titulo = {"Nro", "Año", "Descripcion"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[3];
            ArrayList <SalidaProcesional> x;
            String campo = (String) spvista.cmbBD2.getSelectedItem();
            String filtro = spvista.txtFiltro2.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
                
                x = sp.buscarFiltro(filtro, campo);
                
                if (x.size()>0) {
                    
                     Iterator <SalidaProcesional> it = x.iterator();
            while(it.hasNext()){
                ficha = it.next();
                fila[0] = String.valueOf(ficha.getIdentificador());
                fila[1] = String.valueOf(ficha.getAnio());
                fila[2] = String.valueOf(ficha.getDescripcion());
                
                m.addRow(fila);
            }           
            
            spvista.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            spvista.tablaEntidadConocida3.setRowSorter(ordenar);
            spvista.tablaEntidadConocida3.setModel(m);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                }
            
           
            
            } else {
                
            }
            
            
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }

    /*Limpiar Texto*/
    public void limpiarTexto() {
        spvista.jDateChooser1.setDate(null);
        spvista.txtdescripcion.setText("");
    }
    
    /*Limpiar Texto*/
    public void limpiarTexto2() {
        spvista.txtIdentificador8.setText("");
        spvista.jDateChooser4.setDate(null);
        spvista.txtdescripcion5.setText("");
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<SalidaProcesional> buscarFicha(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        sp = new SalidaProcesional();
        return sp.buscarFiltro(filtro, campo);
    }
    
    /*Metodo para agregar una Entidad Conocida*/
 

    
    
    public void modificarSalida(int identificador, String fecha, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        sp = new SalidaProcesional(identificador, fecha, descripcion);
        sp.actualizar();
    }

    
    public void eliminarSalida(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        sp = new SalidaProcesional(identificador);
        sp.borrar();
    }

    
    public ArrayList<SalidaProcesional> leerTodas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        sp = new SalidaProcesional();
        return sp.leerTodos();
    }
    
    
    private void agregarSalida(int identificador, String fecha, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        sp = new SalidaProcesional(identificador,fecha,descripcion);
        sp.grabar();
    }
}