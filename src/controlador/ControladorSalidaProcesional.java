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
        spvista.setVisible(true);
        spvista.setLocationRelativeTo(null);
        
        cargarTablaFicha();
        
        cargarCmbBD1();
        cargarCmbBD2();

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
                int identificador = Integer.parseInt(spvista.txtIdentificador.getText());
                Date anio = Date.valueOf(spvista.txt_anio.getText());
                String descripcion = String.valueOf(spvista.txtdescripcion.getText());
                
                try {
                    agregarSalida(identificador, anio, descripcion);
                    JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!", "SoftCospadias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCospadias", JOptionPane.ERROR_MESSAGE);
                }   
                cargarTablaFicha();
                cargarTablaFicha2();
                cargarTablaFicha3();
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
                int identificador3 = Integer.parseInt(spvista.txtIdentificador7.getText());
                Date anio3 = Date.valueOf(spvista.txt_anio4.getText());
                String descripcion3 = String.valueOf(spvista.txtdescripcion4.getText());
                
                
                try {
                    modificarSalida(identificador3,anio3,descripcion3);
                    JOptionPane.showMessageDialog(null, "¡Modificado Correctamente!", "SoftCospadias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCospadias", JOptionPane.ERROR_MESSAGE);
                }
                
                
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
    
    private void eliminarSeleccion() {
        String seleccion = spvista.txtIdentificador8.getText();
        try {
            eliminarSalida(Integer.parseInt(seleccion));
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCospadias", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorSalidaProcesional.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaFicha();
        limpiarTexto2();
        spvista.tablaEntidadConocida3.setModel(new DefaultTableModel());
        spvista.txtFiltro2.setText("");
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
        int clic = spvista.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        spvista.txtIdentificador5.setText((String) spvista.tablaEntidadConocida1.getValueAt(clic, 0));
        spvista.txt_anio2.setText((String) spvista.tablaEntidadConocida1.getValueAt(clic, 1));
        spvista.txtdescripcion2.setText((String) spvista.tablaEntidadConocida1.getValueAt(clic, 2));
        
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = spvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        
        spvista.txtIdentificador7.setText((String) spvista.tablaEntidadConocida2.getValueAt(clic, 0));
        spvista.txt_anio4.setText((String) spvista.tablaEntidadConocida2.getValueAt(clic, 1));
        spvista.txtdescripcion4.setText((String) spvista.tablaEntidadConocida2.getValueAt(clic, 2));
        
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = spvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
       spvista.txtIdentificador8.setText((String) spvista.tablaEntidadConocida3.getValueAt(clic, 0));
       spvista.txt_anio5.setText((String) spvista.tablaEntidadConocida3.getValueAt(clic, 1));
       spvista.txtdescripcion5.setText((String) spvista.tablaEntidadConocida3.getValueAt(clic, 2));
        
    }
    
    public void cargarTablaFicha() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Año", "Descripcion"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[3];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from salidaprocesional";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            while(cbd.resultado.next()){
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("anio");
                fila[2] = cbd.resultado.getString("descripcion");
                
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
            x = sp.buscarFiltro(filtro, campo);
            
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
            x = sp.buscarFiltro(filtro, campo);
            
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
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }

    /*Limpiar Texto*/
    public void limpiarTexto() {
        spvista.txtIdentificador.setText("");
        spvista.txt_anio.setText("");
        spvista.txtdescripcion.setText("");
    }
    
    /*Limpiar Texto*/
    public void limpiarTexto2() {
        spvista.txtIdentificador8.setText("");
        spvista.txt_anio5.setText("");
        spvista.txtdescripcion5.setText("");
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<SalidaProcesional> buscarFicha(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        sp = new SalidaProcesional();
        return sp.buscarFiltro(filtro, campo);
    }
    
    /*Metodo para agregar una Entidad Conocida*/
 

    
    
    public void modificarSalida(int identificador, Date fecha, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
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
    
    
    private void agregarSalida(int identificador, Date fecha, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        sp = new SalidaProcesional(identificador,fecha,descripcion);
        sp.grabar();
    }
}