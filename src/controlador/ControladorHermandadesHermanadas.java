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
import modelo.HermandadesHermanadas;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermandadesHermanadasVista;

public class ControladorHermandadesHermanadas implements ActionListener {

    HermandadesHermanadas hH;
    HermandadesHermanadasVista hHvista;
    
    int identificadorEliminar = 0;
    int identificadorBuscar = 0;
    int identificadorModificar = 0;
    
    /** BOTONES: INGRESAR-SALIR **/
    
    public void iniciar() {
        hH = new HermandadesHermanadas();
        hHvista = new HermandadesHermanadasVista();
        hHvista.setVisible(true);
        hHvista.setLocationRelativeTo(null);
        
        cargarTablaEntidadConocidaBuscar();
        
        // cargamos los combo
        cargarCmbBD1();
        cargarCmbBD2();
        cargarCmbBD3();

        //se añade las acciones a los controles del formulario padre
        hHvista.btnIngresar.setActionCommand("INGRESAR");
        hHvista.btnBuscar.setActionCommand("BUSCAR1");
        hHvista.btnBuscar2.setActionCommand("BUSCAR2");
        hHvista.btnBuscar3.setActionCommand("BUSCAR3");
        hHvista.btnModificar.setActionCommand("MODIFICAR");
        hHvista.btnEliminar.setActionCommand("ELIMINAR");
        hHvista.btnSalir.setActionCommand("SALIR1");
        hHvista.btnSalir2.setActionCommand("SALIR2");
        hHvista.btnSalir3.setActionCommand("SALIR3");
        hHvista.btnSalir4.setActionCommand("SALIR4");
        //Se pone a escuchar las acciones del usuario
        hHvista.btnIngresar.addActionListener(this);
        hHvista.btnBuscar.addActionListener(this);
        hHvista.btnBuscar2.addActionListener(this);
        hHvista.btnBuscar3.addActionListener(this);
        hHvista.btnModificar.addActionListener(this);
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
                ingresarHermandadesHermanadas();
                limpiarTexto();   
                cargarTablaEntidadConocidaBuscar();
                cargarTablaEntidadConocida2();
                cargarTablaEntidadConocida3();
                break;
                
            case "BUSCAR1":
                cargarTablaEntidadConocida2();
                break;
            case "BUSCAR2":
                cargarTablaEntidadConocida3();
                break;
            case "BUSCAR3":
                buscarTablaEntidadConocidaModificar();
                break;
            case "MODIFICAR":
                modificarEntidadConocida();                
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
    
    public void modificarEntidadConocida() {
        
        int clic = hHvista.tablaEntidadConocida1.getSelectedRow();
        
        if (clic!=-1) {
            
            try {
            String nombre = hHvista.txtNombreEntidad2.getText();
            String localidad = hHvista.txtLocalidad2.getText();
            String domicilio = hHvista.txtDomicilio2.getText();
            String parroquia = hHvista.txtParroquia2.getText();
            int telf1 = Integer.parseInt(hHvista.txtTelf12.getText());
            int telf2 = Integer.parseInt(hHvista.txtTelf22.getText());
            modificarHermandad(identificadorModificar, nombre, localidad, domicilio, parroquia, telf1, telf2);
       
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
            
            cargarTablaEntidadConocidaModificar();
                cargarTablaEntidadConocidaBuscar();
                limpiarTextoPantallaModificar();
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandadesHermanadas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Has introducido un parámetro incorrecto. Revisa correctamente los campos");
        }
            
        } else {
            JOptionPane.showMessageDialog(null, "Debes de seleccinar una fila de la Tabla");
        }
        
        
        
    }
    
    public void ingresarHermandadesHermanadas() {
        try {
            int identificador = 0;
            
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select max(identificador) from hermandadeshermanadas";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            if (cbd.resultado.next()) {
                identificador = cbd.resultado.getInt(1)+1;
            } else {
                identificador = 1;
            }
            
            String nombre = hHvista.txtNombreHh.getText();
            String localidad = hHvista.txtLocalidad.getText();
            String domicilio = hHvista.txtDomicilio.getText();
            
            // validacion de numeros
            int telf1 = 000000000;
            int telf2 = 000000000;
            if (!hHvista.txtTelf1.getText().equalsIgnoreCase("")) {
                telf1 = Integer.parseInt(hHvista.txtTelf1.getText());
            }
            if (!hHvista.txTelf2.getText().equalsIgnoreCase("")) {
                telf2 = Integer.parseInt(hHvista.txTelf2.getText());
            }            
            // fin de validacion de numeros
            
            String parroquia = hHvista.txtParroquia.getText();
            
            agregarHermandadHermanadas(identificador, nombre, parroquia, localidad, domicilio, telf1, telf2);
            JOptionPane.showMessageDialog(null, "Insertado Correctamente");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        } catch (java.lang.NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Has introducido un parámetro incorrecto. Revisa correctamente los campos");
        }

    }
    
    private void eliminarSeleccion() {
        int clic = hHvista.tablaEntidadConocida3.getSelectedRow();
        
        if (clic!=-1) {
            
            try {
            eliminarHermandadHermanadas(identificadorEliminar);
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
        }
            
        limpiarTexto2();
        cargarTablaEntidadConocidaEliminar();
        cargarTablaEntidadConocidaBuscar();
        
        hHvista.txtFiltro2.setText(""); 
        
        } else {
            JOptionPane.showMessageDialog(null, "Debes de seleccionar una fila de la Tabla");
        }      
       
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
        hHvista.cmbBD1.removeItemAt(7);
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
        hHvista.cmbBD2.removeItemAt(7);
    }
    
    private void cargarCmbBD3() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE hermandadeshermanadas;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            hHvista.cmbBD3.removeAllItems();
            
            while(cbd.resultado.next()){
                hHvista.cmbBD3.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandadesHermanadas.class.getName()).log(Level.SEVERE, null, ex);
        }  
        hHvista.cmbBD3.removeItemAt(7);
    }
    
    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = hHvista.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        identificadorModificar = Integer.parseInt((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 0));
        hHvista.txtNombreEntidad2.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 1));
        hHvista.txtLocalidad2.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 3));
        hHvista.txtDomicilio2.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 2));
        hHvista.txtParroquia2.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 6));
        hHvista.txtTelf12.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 4));
        hHvista.txtTelf22.setText((String) hHvista.tablaEntidadConocida1.getValueAt(clic, 5));
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = hHvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        identificadorBuscar = Integer.parseInt((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 0));
        hHvista.txtNombreEntidad3.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 1));
        hHvista.txtLocalidad3.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 3));
        hHvista.txtDomicilio3.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 2));
        hHvista.txtParroquia3.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 6));
        hHvista.txtTelf13.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 4));
        hHvista.txtTelf23.setText((String) hHvista.tablaEntidadConocida2.getValueAt(clic, 5));
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = hHvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        identificadorEliminar = Integer.parseInt((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 0));
        hHvista.txtNombreEntidad4.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 1));
        hHvista.txtLocalidad4.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 3));
        hHvista.txtDomicilio4.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 2));
        hHvista.txtParroquia4.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 6));
        hHvista.txtTelf14.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 4));
        hHvista.txtTelf24.setText((String) hHvista.tablaEntidadConocida3.getValueAt(clic, 5));
    }
    
    public void cargarTablaEntidadConocidaBuscar() {
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
            
            hHvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            hHvista.tablaEntidadConocida2.setRowSorter(ordenar);
            hHvista.tablaEntidadConocida2.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
     public void cargarTablaEntidadConocidaModificar() {
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
     
      public void cargarTablaEntidadConocidaEliminar() {
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
            
            hHvista.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            hHvista.tablaEntidadConocida3.setRowSorter(ordenar);
            hHvista.tablaEntidadConocida3.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaEntidadConocida2() {
        
        if (!hHvista.txtFiltro1.getText().equalsIgnoreCase("")) {
            
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
            
            if (x.size()>0) {
                Iterator <HermandadesHermanadas> it = x.iterator();
            while(it.hasNext()){
                hermandadesh = it.next();
                fila[0] = String.valueOf(hermandadesh.getId());
                fila[1] = hermandadesh.getNombre();
                fila[2] = hermandadesh.getDomicilio();
                fila[3] = hermandadesh.getLocalidad();
                fila[4] = String.valueOf(hermandadesh.getTelf1());
                fila[5] = String.valueOf(hermandadesh.getTelf2());
                fila[6] = hermandadesh.getParroquia();
                m.addRow(fila);
            }           
            
            hHvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            hHvista.tablaEntidadConocida2.setRowSorter(ordenar);
            hHvista.tablaEntidadConocida2.setModel(m);
            
            hHvista.txtFiltro1.setText("");
            
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                cargarTablaEntidadConocidaBuscar();
                hHvista.txtFiltro1.setText("");
            }
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        } 
        
        } else {
            cargarTablaEntidadConocidaBuscar();
        }   
        
    }
    
    public void buscarTablaEntidadConocidaModificar() {
        
        if (!hHvista.txtFiltro3.getText().equalsIgnoreCase("")) {
            
           HermandadesHermanadas hermandadesh = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "Telf1", "Telf2", "Parroquia"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[7];
            ArrayList <HermandadesHermanadas> x;
            String campo = (String) hHvista.cmbBD3.getSelectedItem();
            String filtro = hHvista.txtFiltro3.getText();
            x = hH.buscarFiltro(filtro, campo);
            
            if (x.size()>0) {
                Iterator <HermandadesHermanadas> it = x.iterator();
            while(it.hasNext()){
                hermandadesh = it.next();
                fila[0] = String.valueOf(hermandadesh.getId());
                fila[1] = hermandadesh.getNombre();
                fila[2] = hermandadesh.getDomicilio();
                fila[3] = hermandadesh.getLocalidad();
                fila[4] = String.valueOf(hermandadesh.getTelf1());
                fila[5] = String.valueOf(hermandadesh.getTelf2());
                fila[6] = hermandadesh.getParroquia();
                m.addRow(fila);
            }           
            
            hHvista.tablaEntidadConocida1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            hHvista.tablaEntidadConocida1.setRowSorter(ordenar);
            hHvista.tablaEntidadConocida1.setModel(m);
            
            hHvista.txtFiltro3.setText("");
            
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                cargarTablaEntidadConocidaModificar();
                hHvista.txtFiltro3.setText("");
            }
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        } 
        
        } else {
            cargarTablaEntidadConocidaModificar();
        }
        
    }
    
    public void cargarTablaEntidadConocida3() {
        if (!hHvista.txtFiltro2.getText().equalsIgnoreCase("")) {
            
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
            
            if (x.size()>0) {
                
                
            Iterator <HermandadesHermanadas> it = x.iterator();
            while(it.hasNext()){
                hermandadesh = it.next();
                fila[0] = String.valueOf(hermandadesh.getId());
                fila[1] = hermandadesh.getNombre();
                fila[2] = hermandadesh.getDomicilio();
                fila[3] = hermandadesh.getLocalidad();
                fila[4] = String.valueOf(hermandadesh.getTelf1());
                fila[5] = String.valueOf(hermandadesh.getTelf2());
                fila[6] = hermandadesh.getParroquia();
                m.addRow(fila);
            }           
            
            hHvista.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            hHvista.tablaEntidadConocida3.setRowSorter(ordenar);
            hHvista.tablaEntidadConocida3.setModel(m);
                
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                cargarTablaEntidadConocidaEliminar();
                hHvista.txtFiltro2.setText("");
            }            
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
            
        } else {
            cargarTablaEntidadConocidaEliminar();
        }
        
        
    }

    /*Limpiar Texto*/
    public void limpiarTexto() {
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
        hHvista.txtNombreEntidad4.setText("");
        hHvista.txtLocalidad4.setText("");
        hHvista.txtDomicilio4.setText("");
        hHvista.txtTelf14.setText("");
        hHvista.txtTelf24.setText("");
        hHvista.txtParroquia4.setText("");
        hHvista.txtParroquia4.setText("");
    }
    
    public void limpiarTextoPantallaModificar() {
        hHvista.txtNombreEntidad2.setText("");
        hHvista.txtLocalidad2.setText("");
        hHvista.txtDomicilio2.setText("");
        hHvista.txtTelf12.setText("");
        hHvista.txtTelf22.setText("");
        hHvista.txtParroquia2.setText("");
        hHvista.txtParroquia2.setText("");
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<HermandadesHermanadas> buscarHermandadesHermanadas(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hH = new HermandadesHermanadas();
        return hH.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar una Entidad Conocida*/
    public void agregarHermandadHermanadas(int identificador, String nombre, String parroquia, String localidad, String domicilio, int telf1, int telf2) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hH = new HermandadesHermanadas(identificador, nombre, parroquia, localidad, domicilio, telf1, telf2);
        hH.grabar();
    }

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarHermandad(int identificador, String nombre, String parroquia, String localidad, String domicilio, int telf1, int telf2) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        hH = new HermandadesHermanadas(identificador, nombre, parroquia, localidad, domicilio, telf1, telf2);
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