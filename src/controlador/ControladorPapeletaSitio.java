package controlador;

import java.awt.HeadlessException;
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
import modelo.PapeletaSitio;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.PapeletaSitioVista;
import validaciones.*;

public class ControladorPapeletaSitio implements ActionListener {

    PapeletaSitio ps;
    PapeletaSitioVista psvista;
    
    public void iniciar() {
        ps = new PapeletaSitio();
        psvista = new PapeletaSitioVista();
        psvista.setVisible(true);
        psvista.setLocationRelativeTo(null);
        
        psvista.txtNombre.setEnabled(false);
        psvista.txtApellidos.setEnabled(false);
        
        psvista.txtIdentificador1.setEnabled(false);
        psvista.txtIdentificador4.setEnabled(false);
        psvista.txtIdentificador5.setEnabled(false);
        
        // validaciones
        soloNumerosSoloLetras x = new soloNumerosSoloLetras();
        x.SNumeros(psvista.txtnumpapeleta1);
        x.SNumeros(psvista.txtnumpapeleta2);
        x.SNumeros(psvista.txtnumpapeleta3);
        x.SNumeros(psvista.txtnumhermanoid6);
        x.SNumeros(psvista.txtnumhermanoid4);
        x.SNumeros(psvista.txtnumhermanoid5);
        x.SLetras(psvista.txtNombre);
        x.SLetras(psvista.txtApellidos);
        x.SNumeros(psvista.txtdonativo3);
        x.SNumeros(psvista.txtdonativo2);
        x.SNumeros(psvista.txtdonativo1);
        cargarTablaPapeleta();
        
        psvista.jComboBox2.setSelectedItem(null);
        
        cargarCmbBD1();
        cargarCmbBD2();

        //se añade las acciones a los controles del formulario padre
        psvista.btnIngresar.setActionCommand("INGRESAR");
        psvista.btnBuscar.setActionCommand("BUSCAR1");
        psvista.btnBuscar2.setActionCommand("BUSCAR2");
        psvista.btnEliminar.setActionCommand("ELIMINAR");
        psvista.btnSalir.setActionCommand("SALIR1");
        psvista.btnSalir2.setActionCommand("SALIR2");
        psvista.btnSalir3.setActionCommand("SALIR3");
        psvista.btnSalir4.setActionCommand("SALIR4");
        psvista.modificar3.setActionCommand("MODIFICAR");
        psvista.btnComprobar.setActionCommand("COMPROBAR");
        
        //Se pone a escuchar las acciones del usuario
        psvista.btnIngresar.addActionListener(this);
        psvista.btnBuscar.addActionListener(this);
        psvista.btnBuscar2.addActionListener(this);
        psvista.btnEliminar.addActionListener(this);
        psvista.btnSalir.addActionListener(this);
        psvista.btnSalir2.addActionListener(this);
        psvista.btnSalir3.addActionListener(this);
        psvista.btnSalir4.addActionListener(this);
        psvista.modificar3.addActionListener(this);
        psvista.btnComprobar.addActionListener(this);
        
        //Al Hacer click a una fila de la tabla los valores se cargaran en los cuadros de texto correspondientes
        psvista.tablaEntidadConocida1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
            }
        });
        
        psvista.tablaEntidadConocida2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida2MousePressed(evt);
            }
        });
        
        psvista.tablaEntidadConocida3.addMouseListener(new java.awt.event.MouseAdapter() {
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
                cargarTablaPapeleta2();
                break;
            case "BUSCAR2":
                cargarTablaPapeleta3();
                break;
            case "ELIMINAR":
                eliminarSeleccion();
                break;
            case "MODIFICAR":
                modificar();
                
                break;
            case "SALIR1":
                psvista.dispose();
                break;
            case "SALIR2":
                psvista.dispose();
                break;
            case "SALIR3":
                psvista.dispose();
                break;
            case "SALIR4":
                psvista.dispose();
                break;
            case "COMPROBAR":
                comprobar();
                break;
        }

    }
    
    public void comprobar () {
        int numHno = 0;
        if (!psvista.txtnumhermanoid6.getText().equalsIgnoreCase("")) {
        numHno = Integer.parseInt(psvista.txtnumhermanoid6.getText());
        
            localizarNombreApellidosHermano(numHno);
        
        } else {
            JOptionPane.showMessageDialog(null, "Debes de introducir un numero de hermano para poder comprobar su nombre y apellidos");
        
        }
    }
    
    public void ingresar() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select max(identificador) from papeletasitio");
            
        int identificador = 1;
            if (cbd.resultado.next()) {
                identificador = cbd.resultado.getInt(1)+1;
            } else {
                identificador = 1;
            }
        
                //trabajando con fechas
            String fecha = "";
                if (psvista.jDateChooser1.getDate() != null) {
                    int año = psvista.jDateChooser1.getCalendar().get(Calendar.YEAR);
                    int mes = psvista.jDateChooser1.getCalendar().get(Calendar.MONTH);
                    int dia = psvista.jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                    fecha = año + "-" + mes + "-" + dia;
                    //
                }
                // fin fecha
                String num_hermano_id = psvista.txtnumhermanoid6.getText();
                String sale = (String) psvista.jComboBox1.getSelectedItem();
                String donativo = psvista.txtdonativo3.getText();
                String numero_papeleta = psvista.txtnumpapeleta3.getText();
                
                boolean correcto = comprobarExisteHermano(Integer.parseInt(num_hermano_id));
                
                if (correcto == true) {              
                
                    agregarPapeleta(identificador, Integer.parseInt(num_hermano_id), fecha, sale, Integer.parseInt(donativo), 1, Integer.parseInt(numero_papeleta));
                    JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();
                    
                } else {
                    JOptionPane.showMessageDialog(null, "El número de hermano introducido no existe");
                }
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | NumberFormatException | HeadlessException ex) {
                }   
                cargarTablaPapeleta();
                cargarTablaPapeleta2();
                cargarTablaPapeleta3();
    }
    
    public void modificar() {
        
        if (psvista.jComboBox2.getSelectedIndex()!=-1) {
            try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            String identificador3 = psvista.txtIdentificador4.getText();
            String num_hermano_id4 = psvista.txtnumhermanoid4.getText();
            String fecha4 = "";
            //trabajando con fechas
            if (psvista.jDateChooser3.getDate() != null) {
                int año = psvista.jDateChooser3.getCalendar().get(Calendar.YEAR);
                int mes = psvista.jDateChooser3.getCalendar().get(Calendar.MONTH);
                int dia = psvista.jDateChooser3.getCalendar().get(Calendar.DAY_OF_MONTH);
                fecha4 = año + "-" + mes + "-" + dia;
                //
            }
            // fin fecha
            String sale4 = (String) psvista.jComboBox2.getSelectedItem();
            String donativo4 = psvista.txtdonativo1.getText();
            String num_papeleta4 = psvista.txtnumpapeleta1.getText();
            cbd.un_st.executeUpdate("update papeletasitio set numero_hermano_id='"+num_hermano_id4+"', donativo='"+donativo4+"', sale='"+sale4+"', numero_papeleta='"+num_papeleta4+"', fecha='"+fecha4+"' where identificador="+identificador3+";");
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
            cargarTablaPapeleta();
            cargarTablaPapeleta2();
            cargarTablaPapeleta3();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else {
            try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            String identificador3 = psvista.txtIdentificador4.getText();
            String num_hermano_id4 = psvista.txtnumhermanoid4.getText();
            String fecha4 = "";
            //trabajando con fechas
            if (psvista.jDateChooser3.getDate() != null) {
                int año = psvista.jDateChooser3.getCalendar().get(Calendar.YEAR);
                int mes = psvista.jDateChooser3.getCalendar().get(Calendar.MONTH);
                int dia = psvista.jDateChooser3.getCalendar().get(Calendar.DAY_OF_MONTH);
                fecha4 = año + "-" + mes + "-" + dia;
                //
            }
            // fin fecha
            String sale4 = (String) psvista.jComboBox2.getSelectedItem();
            String donativo4 = psvista.txtdonativo1.getText();
            String num_papeleta4 = psvista.txtnumpapeleta1.getText();
            cbd.un_st.executeUpdate("update papeletasitio set numero_hermano_id='"+num_hermano_id4+"', donativo='"+donativo4+"', numero_papeleta='"+num_papeleta4+"', fecha='"+fecha4+"' where identificador="+identificador3+";");
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
            cargarTablaPapeleta();
            cargarTablaPapeleta2();
            cargarTablaPapeleta3();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        
               
    }
    
    private void eliminarSeleccion() {
        String seleccion = psvista.txtIdentificador1.getText();
        try {
            eliminarPapeleta(Integer.parseInt(seleccion));
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaPapeleta();
        limpiarTexto2();
        psvista.tablaEntidadConocida3.setModel(new DefaultTableModel());
        psvista.txtFiltro2.setText("");
    }
    
    
    private void cargarCmbBD1() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE papeletasitio;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            psvista.cmbBD1.removeAllItems();
            
            while(cbd.resultado.next()){
                psvista.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void cargarCmbBD2() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE papeletasitio;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            psvista.cmbBD2.removeAllItems();
            
            while(cbd.resultado.next()){
                psvista.cmbBD2.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {   
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            
            int clic = psvista.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
            
            psvista.txtIdentificador5.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 0));
            int identificador = Integer.parseInt((String) psvista.tablaEntidadConocida1.getValueAt(clic, 0));
            cbd.resultado = cbd.un_st.executeQuery("select fecha from papeletasitio where identificador="+identificador);
            Date fecha = null;
            if (cbd.resultado.next()) {
                fecha = cbd.resultado.getDate(1);
            }
            psvista.jDateChooser2.setDate(fecha);
            psvista.txtnumhermanoid5.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 1));
            
            psvista.txtdonativo2.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 4));
            psvista.txtnumpapeleta2.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 6));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {   
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            int clic = psvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
            
            
            psvista.txtIdentificador4.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 0));
            int identificador = Integer.parseInt((String) psvista.tablaEntidadConocida1.getValueAt(clic, 0));
            cbd.resultado = cbd.un_st.executeQuery("select fecha from papeletasitio where identificador="+identificador);
            Date fecha = null;
            if (cbd.resultado.next()) {
                fecha = cbd.resultado.getDate(1);
            }
            psvista.jDateChooser3.setDate(fecha);
            psvista.txtnumhermanoid4.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 1));
            //psvista.txtsale1.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 3));
            psvista.txtdonativo1.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 4));
            psvista.txtnumpapeleta1.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 6));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {        
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            int clic = psvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
            
            psvista.txtIdentificador1.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 0));
            int identificador = Integer.parseInt((String) psvista.tablaEntidadConocida1.getValueAt(clic, 0));
            cbd.resultado = cbd.un_st.executeQuery("select fecha from papeletasitio where identificador="+identificador);
            Date fecha = null;
            if (cbd.resultado.next()) {
                fecha = cbd.resultado.getDate(1);
            }
            psvista.jDateChooser4.setDate(fecha);
            psvista.txtnumhermanoid1.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 1));
            psvista.txtdonativo.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 4));
            psvista.txtnumpapeleta.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 6));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarTablaPapeletaModificar() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Num hermano id", "fecha", "Sale", "Donativo", "Salida procesional id", "Numero papeleta"};   
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[7];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from papeletasitio order by identificador";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            while(cbd.resultado.next()){
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("numero_hermano_id");
                fila[2] = cbd.resultado.getString("fecha");
                fila[3] = cbd.resultado.getString("sale");
                fila[4] = cbd.resultado.getString("donativo");
                fila[5] = cbd.resultado.getString("salida_procesional_id");
                fila[6] = cbd.resultado.getString("numero_papeleta");
                
                m.addRow(fila);
            }
            
            psvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            psvista.tablaEntidadConocida2.setRowSorter(ordenar);
            psvista.tablaEntidadConocida2.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaPapeleta() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Num hermano id", "fecha", "Sale", "Donativo", "Salida procesional id", "Numero papeleta"};   
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[7];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from papeletasitio order by identificador";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            while(cbd.resultado.next()){
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("numero_hermano_id");
                fila[2] = cbd.resultado.getString("fecha");
                fila[3] = cbd.resultado.getString("sale");
                fila[4] = cbd.resultado.getString("donativo");
                fila[5] = cbd.resultado.getString("salida_procesional_id");
                fila[6] = cbd.resultado.getString("numero_papeleta");
                
                m.addRow(fila);
            }
            
            psvista.tablaEntidadConocida1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            psvista.tablaEntidadConocida1.setRowSorter(ordenar);
            psvista.tablaEntidadConocida1.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaPapeleta2() {
        PapeletaSitio papeleta = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Num hermano id", "fecha", "Sale", "Donativo", "Salida procesional id", "Numero papeleta"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[7];
            ArrayList <PapeletaSitio> x;
            String campo = (String) psvista.cmbBD1.getSelectedItem();
            String filtro = psvista.txtFiltro1.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
            x = ps.buscarFiltro(filtro, campo);
            
            if (x.size()>0) {
            
            Iterator <PapeletaSitio> it = x.iterator();
            while(it.hasNext()){
                papeleta = it.next();
                fila[0] = String.valueOf(papeleta.getIdentificador());
                fila[1] = String.valueOf(papeleta.getNumero_hermano_id());
                fila[2] = papeleta.getFecha();
                fila[3] = String.valueOf(papeleta.getSale());
                fila[4] = String.valueOf(papeleta.getDonativo());
                fila[5] = String.valueOf(papeleta.getSalida_procesional_id());
                fila[6] = String.valueOf(papeleta.getNumero_papeleta());
                m.addRow(fila);
            }           
            
            psvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            psvista.tablaEntidadConocida2.setRowSorter(ordenar);
            psvista.tablaEntidadConocida2.setModel(m);
            
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
            }
            
            } else {
                
            }
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaPapeleta3() {
        PapeletaSitio papeleta = null;
        DefaultTableModel m;
        try {
                        
            String[] titulo = {"Nro", "Num hermano id", "fecha", "Sale", "Donativo", "Salida procesional id", "Numero papeleta"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[7];
            ArrayList <PapeletaSitio> x;
            String campo = (String) psvista.cmbBD2.getSelectedItem();
            String filtro = psvista.txtFiltro2.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
            
            x = ps.buscarFiltro(filtro, campo);
            
            if (x.size()>0) {
            
            Iterator <PapeletaSitio> it = x.iterator();
            while(it.hasNext()){
                papeleta = it.next();
                fila[0] = String.valueOf(papeleta.getIdentificador());
                fila[1] = String.valueOf(papeleta.getNumero_hermano_id());
                fila[2] = papeleta.getFecha().toString();
                fila[3] = String.valueOf(papeleta.getSale());
                fila[4] = String.valueOf(papeleta.getDonativo());
                fila[5] = String.valueOf(papeleta.getSalida_procesional_id());
                fila[6] = String.valueOf(papeleta.getNumero_papeleta());
                
                m.addRow(fila);
            }           
            
            psvista.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            psvista.tablaEntidadConocida3.setRowSorter(ordenar);
            psvista.tablaEntidadConocida3.setModel(m);
            
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
        psvista.txtIdentificador4.setText("");
        psvista.txtnumhermanoid4.setText("");
        psvista.txtdonativo1.setText("");
        psvista.txtnumpapeleta1.setText("");
    }
    
    /*Limpiar Texto*/
    public void limpiarTexto2() {
        psvista.txtIdentificador4.setText("");
       // psvista.txtNombre4.setText("");
        //psvista.txtCantidad4.setText("");
        //psvista.txtFecha4.setText("");
        //psvista.txtPrecioUnidad4.setText("");
    }
    
    public boolean comprobarExisteHermano(int NumHermano) {
        boolean existe = false;
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select numero_hermano from hermanos where numero_hermano="+NumHermano);
            // si da algun resultado estonces el hermano existira
            if (cbd.resultado.next()) {
                existe=true;
            } else {
                existe=false;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
        
    }
    
    public void localizarNombreApellidosHermano(int NHno) {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select nombre, apellidos from hermanos where numero_hermano="+NHno);
            if (cbd.resultado.next()) {
                psvista.txtNombre.setText(cbd.resultado.getString(1));
                psvista.txtApellidos.setText(cbd.resultado.getString(2));
            } else {
                JOptionPane.showMessageDialog(null, "El número de hermano introducido no es correcto");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPapeletaSitio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<PapeletaSitio> buscarPapeleta(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ps = new PapeletaSitio();
        return ps.buscarFiltro(filtro, campo);
    }
    
    /*Metodo para agregar una Entidad Conocida*/
 

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarPapeleta(int identificador, int numero_hermano_id, String fecha, String sale, Double donativo, int numero_papeleta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PapeletaSitio ps = new PapeletaSitio(identificador, numero_hermano_id,fecha,sale,donativo,numero_papeleta);
        ps.actualizar();
    }

    /*Metodo para borrar una Entidad Conocida*/
    public void eliminarPapeleta(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ps = new PapeletaSitio(identificador);
        ps.borrar();
    }

    /*Metodo para leer todas las Entidades Conocidas*/
    public ArrayList<PapeletaSitio> leerTodas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ps = new PapeletaSitio();
        return ps.leerTodos();
    }

    private void agregarPapeleta(int identificador, int numero_hermano_id, String fecha, String sale, int donativo, int salida_procesional_id, int numero_papeleta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ps = new PapeletaSitio(identificador,numero_hermano_id,fecha,sale,donativo,salida_procesional_id,numero_papeleta);
        ps.grabar();
    }

}