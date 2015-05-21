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
import validaciones.*;

public class ControladorEntidadesConocidas implements ActionListener {

    EntidadesConocidas ec;
    EntidadesConocidasVista ecv;
    int identificadorModificar = 0;
    int identificadorEliminar = 0;

    /**
     * BOTONES: INGRESAR-SALIR *
     */
    public void iniciar() {
        ec = new EntidadesConocidas();
        ecv = new EntidadesConocidasVista();
        ecv.setVisible(true);
        ecv.setLocationRelativeTo(null);
        
        //validaciones
        soloNumerosSoloLetras x = new soloNumerosSoloLetras();
        x.SLetras(ecv.txtNombreEntidad);
        x.SLetras(ecv.txtNombreEntidad3);
        x.SLetras(ecv.txtNombreEntidad4);
        x.SLetras(ecv.txtNombreEntidad5);
        x.SLetras(ecv.txtLocalidad);
        x.SLetras(ecv.txtLocalidad3);
        x.SLetras(ecv.txtLocalidad4);
        x.SLetras(ecv.txtLocalidad5);
        x.SLetras(ecv.txtProvincia);
        x.SLetras(ecv.txtProvincia3);
        x.SLetras(ecv.txtProvincia4);
        x.SLetras(ecv.txtProvincia5);
        x.SNumeros(ecv.txtCp);
        x.SNumeros(ecv.txtCp3);
        x.SNumeros(ecv.txtCp4);
        x.SNumeros(ecv.txtCp5);
        x.SNumeros(ecv.txtTelf1);
        x.SNumeros(ecv.txtTelf13);
        x.SNumeros(ecv.txtTelf14);
        x.SNumeros(ecv.txtTelf15);
        x.SNumeros(ecv.txtTelf2);
        x.SNumeros(ecv.txtTelf23);
        x.SNumeros(ecv.txtTelf24);
        x.SNumeros(ecv.txtTelf25);
        
        ecv.txtCp.setDocument(new limitarNumeroTexfield(ecv.txtCp, 5));
        ecv.txtCp3.setDocument(new limitarNumeroTexfield(ecv.txtCp3, 5));
        ecv.txtCp4.setDocument(new limitarNumeroTexfield(ecv.txtCp4, 5));
        ecv.txtCp5.setDocument(new limitarNumeroTexfield(ecv.txtCp5, 5));
        
        ecv.txtTelf1.setDocument(new limitarNumeroTexfield(ecv.txtTelf1, 9));
        ecv.txtTelf13.setDocument(new limitarNumeroTexfield(ecv.txtTelf13, 9));
        ecv.txtTelf14.setDocument(new limitarNumeroTexfield(ecv.txtTelf14, 9));
        ecv.txtTelf15.setDocument(new limitarNumeroTexfield(ecv.txtTelf15, 9));
        
        ecv.txtTelf2.setDocument(new limitarNumeroTexfield(ecv.txtTelf2, 9));
        ecv.txtTelf23.setDocument(new limitarNumeroTexfield(ecv.txtTelf23, 9));
        ecv.txtTelf24.setDocument(new limitarNumeroTexfield(ecv.txtTelf24, 9));
        ecv.txtTelf25.setDocument(new limitarNumeroTexfield(ecv.txtTelf25, 9));
        

        cargarTablaEntidadConocidaPrincipal();
        cargarTablaEntidadConocidaEliminar();
        cargarTablaEntidadConocidaModificar();

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
        ecv.btnModificar.setActionCommand("MODIFICAR");
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
        ecv.btnModificar.addActionListener(this);

        //Al Hacer click a una fila de la tabla los valores se cargaran en los cuadros de texto correspondientes
       

        ecv.tablaEntidadConocida2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida2MousePressed(evt);
            }
        });
        
        ecv.tablaEntidadConocida1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
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
                ingresarEntidadConocida();
                cargarTablaEntidadConocidaPrincipal(); // recargamos la tabla cuando se inserta
                break;

            case "MOSTRAR":
                cargarTablaEntidadConocidaUno();
                break;

            case "BUSCAR1":
                cargarTablaEntidadConocidaDos();
                break;
            case "BUSCAR2":
                cargarTablaEntidadConocidaTres();
                break;
            case "ELIMINAR":
                eliminarSeleccion();
                cargarTablaEntidadConocidaModificar();
                cargarTablaEntidadConocidaPrincipal();
                cargarTablaEntidadConocidaEliminar();
                break;
            case "MODIFICAR":
                modificarEntidadConocida();
                cargarTablaEntidadConocidaModificar();
                cargarTablaEntidadConocidaPrincipal();
                cargarTablaEntidadConocidaEliminar();
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

    public void ingresarEntidadConocida() {
        
        if (!ecv.txtNombreEntidad.getText().equalsIgnoreCase("") && !ecv.txtLocalidad.getText().equalsIgnoreCase("") && !ecv.txtDomicilio.getText().equalsIgnoreCase("") && !ecv.txtProvincia.getText().equalsIgnoreCase("") && !ecv.txtCp.getText().equalsIgnoreCase("") && !ecv.txtTelf1.getText().equalsIgnoreCase("")) {
        
        try {
            
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select max(identificador) from entidadesconocidas";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            int identificador = 0;
            
            if (cbd.resultado.next()) {
                identificador = cbd.resultado.getInt(1)+1;
            } else {
                identificador=1;
            }            
          
            String nombre = ecv.txtNombreEntidad.getText();
            String localidad = ecv.txtLocalidad.getText();
            String domicilio = ecv.txtDomicilio.getText();
            
            // validacion de numeros
            int telf1=0;
            int telf2=0;
            int cp=00000;
            if (!ecv.txtTelf1.getText().equalsIgnoreCase("")) {
                telf1 = Integer.parseInt(ecv.txtTelf1.getText());
            }
            if (!ecv.txtTelf2.getText().equalsIgnoreCase("")) {
                telf2 = Integer.parseInt(ecv.txtTelf2.getText());
            }  
            if (!ecv.txtCp.getText().equalsIgnoreCase("")) {
                cp = Integer.parseInt(ecv.txtCp.getText());
            } 
            // fin de validacion de numeros
            String provincia = ecv.txtProvincia.getText();
            String email = ecv.txtEmail.getText();
            
            
            try {
                agregarEntidadConocida(identificador, nombre, localidad, domicilio, telf1, telf2, cp, provincia, email);
                JOptionPane.showMessageDialog(null, "Insertado Correctamente");
                limpiarTextoPantallaInsertar();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "El Identificador " + identificador + " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                limpiarTextoPantallaInsertar();
            }        
            
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | java.lang.NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Parametro incorrecto introducido. Revise los campos", "Error", JOptionPane.ERROR_MESSAGE);
            limpiarTextoPantallaInsertar();
        }
        
        cargarTablaEntidadConocidaEliminar();
        cargarTablaEntidadConocidaModificar();
        cargarTablaEntidadConocidaPrincipal();
        
        } else {
            JOptionPane.showMessageDialog(null, "Asegúrese de haber introducido todos los campos obligatorios");
        }
    }

    public void modificarEntidadConocida() {
        try {
            
            int clic = ecv.tablaEntidadConocida1.getSelectedRow();
            
            if (clic!=-1) {
                
            String nombre = ecv.txtNombreEntidad5.getText();
            String localidad = ecv.txtLocalidad5.getText();
            String domicilio = ecv.txtDomicilio5.getText();
            int telf1 = Integer.parseInt(ecv.txtTelf15.getText());
            int telf2 = Integer.parseInt(ecv.txtTelf25.getText());
            int cp = Integer.parseInt(ecv.txtCp5.getText());
            String provincia = ecv.txtProvincia5.getText();
            String email = ecv.txtEmail5.getText();
            modificarEntidadConocida(identificadorModificar, nombre, localidad, domicilio, telf1, telf2, cp, provincia, email);
            
            } else {
                JOptionPane.showMessageDialog(null, "Debes de seleccionar una fila de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Has introducido un parámetro un incorrecto. Revisa los campos correctamente");
        }
        
    }

    private void eliminarSeleccion() {
        try {
            
            int clic = ecv.tablaEntidadConocida3.getSelectedRow();
            
            if (clic!=-1) {
                 eliminarEntidadConocida(identificadorEliminar);
                JOptionPane.showMessageDialog(null, "Eliminado Correctamente");
        eliminarEntidadConocida(identificadorEliminar);
            } else {
                JOptionPane.showMessageDialog(null, "Debes de seleccionar una fila de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
            
            }
            
            
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiarTextoPantallaEliminar();
        ecv.tablaEntidadConocida3.setModel(new DefaultTableModel());
        ecv.txtFiltro2.setText("");
    }

    private void cargarCmbBD1() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE entidadesconocidas;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            ecv.cmbBD1.removeAllItems();

            while (cbd.resultado.next()) {
                ecv.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCmbBD2() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE entidadesconocidas;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            ecv.cmbBD2.removeAllItems();

            while (cbd.resultado.next()) {
                ecv.cmbBD2.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCmbBD3() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE entidadesconocidas;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            ecv.cmbBD3.removeAllItems();

            while (cbd.resultado.next()) {
                ecv.cmbBD3.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {
        int clic = ecv.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla

        
        ecv.txtNombreEntidad3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 1));
        ecv.txtLocalidad3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 3));
        ecv.txtDomicilio3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 2));
        ecv.txtProvincia3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 5));
        ecv.txtCp3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 4));
        ecv.txtTelf13.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 6));
        ecv.txtTelf23.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 7));
        ecv.txtEmail3.setText((String) ecv.tablaEntidadConocida2.getValueAt(clic, 8));
    }
    
    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {
        int clic = ecv.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        // guardarmos en la variable identificadorModificar el identificador para despues usarlo a la hora de eliminar
        identificadorModificar = Integer.parseInt((String) ecv.tablaEntidadConocida1.getValueAt(clic, 0));
        ecv.txtNombreEntidad5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 1));
        ecv.txtLocalidad5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 3));
        ecv.txtDomicilio5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 2));
        ecv.txtProvincia5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 5));
        ecv.txtCp5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 4));
        ecv.txtTelf15.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 6));
        ecv.txtTelf25.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 7));
        ecv.txtEmail5.setText((String) ecv.tablaEntidadConocida1.getValueAt(clic, 8));
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {
        int clic = ecv.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
       // guardarmos en la variable identificadorEliminar el identificador para despues usarlo a la hora de eliminar
        identificadorEliminar = Integer.parseInt((String) ecv.tablaEntidadConocida3.getValueAt(clic, 0));
        ecv.txtNombreEntidad4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 1));
        ecv.txtLocalidad4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 3));
        ecv.txtDomicilio4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 2));
        ecv.txtProvincia4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 5));
        ecv.txtCp4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 4));
        ecv.txtTelf14.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 6));
        ecv.txtTelf24.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 7));
        ecv.txtEmail4.setText((String) ecv.tablaEntidadConocida3.getValueAt(clic, 8));
    }

    
    

    public void cargarTablaEntidadConocidaUno() {
        if (!ecv.txtFiltro1.getText().equalsIgnoreCase("")) {
            
            EntidadesConocidas entidad = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[9];
            ArrayList<EntidadesConocidas> x;
            String campo = (String) ecv.cmbBD1.getSelectedItem();
            String filtro = ecv.txtFiltro1.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
            
            x = ec.buscarFiltro(filtro, campo);
            
            if (x.size()>0) {
                Iterator<EntidadesConocidas> it = x.iterator();
            while (it.hasNext()) {
                entidad = it.next();
                fila[0] = String.valueOf(entidad.getIdentificador());
                fila[1] = entidad.getNombre();
                fila[2] = entidad.getDomicilio();
                fila[3] = entidad.getLocalidad();
                fila[4] = String.valueOf(entidad.getCP());
                fila[5] = entidad.getProvincia();
                fila[6] = String.valueOf(entidad.getTelf1());
                fila[7] = String.valueOf(entidad.getTelf2());
                fila[8] = entidad.getEmail();
                m.addRow(fila);
            }

            ecv.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            ecv.tablaEntidadConocida2.setRowSorter(ordenar);
            ecv.tablaEntidadConocida2.setModel(m);
            
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                cargarTablaEntidadConocidaPrincipal();
                limpiarTextoPantallaBuscar();
                
            }  
            
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
           //e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
        ecv.txtFiltro1.setText(""); // vaciamos el contenido de texlfield filtro
        
        } else {
            
            cargarTablaEntidadConocidaPrincipal();
            
        }
        

    }
    
     public void cargarTablaEntidadConocidaDos() {
         if (!ecv.txtFiltro3.getText().equalsIgnoreCase("")) {
             
             EntidadesConocidas entidad = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[9];
            ArrayList<EntidadesConocidas> x;
            String campo = (String) ecv.cmbBD3.getSelectedItem();           
            String filtro = ecv.txtFiltro3.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
            
            x = ec.buscarFiltro(filtro, campo);

            if (x.size()>0) {
                
                Iterator<EntidadesConocidas> it = x.iterator();
            while (it.hasNext()) {
                entidad = it.next();
                fila[0] = String.valueOf(entidad.getIdentificador());
                fila[1] = entidad.getNombre();
                fila[2] = entidad.getDomicilio();
                fila[3] = entidad.getLocalidad();
                fila[4] = String.valueOf(entidad.getCP());
                fila[5] = entidad.getProvincia();
                fila[6] = String.valueOf(entidad.getTelf1());
                fila[7] = String.valueOf(entidad.getTelf2());
                fila[8] = entidad.getEmail();
                m.addRow(fila);
            }     
            
            ecv.tablaEntidadConocida1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            ecv.tablaEntidadConocida1.setRowSorter(ordenar);
            ecv.tablaEntidadConocida1.setModel(m);
            
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                cargarTablaEntidadConocidaModificar();
                limpiarTextoPantallaModificar();
            }
            
            }      
            
            ecv.txtFiltro3.setText("");           

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
           
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
             
         } else {
             cargarTablaEntidadConocidaModificar();
         }
        

    }
     
      public void cargarTablaEntidadConocidaTres() {
         if (!ecv.txtFiltro2.getText().equalsIgnoreCase("")) {
             
             EntidadesConocidas entidad = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[9];
            ArrayList<EntidadesConocidas> x;
            String campo = (String) ecv.cmbBD2.getSelectedItem();
            String filtro = ecv.txtFiltro2.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
            
            x = ec.buscarFiltro(filtro, campo);

            if (x.size()>0) {
                
                Iterator<EntidadesConocidas> it = x.iterator();
            while (it.hasNext()) {
                entidad = it.next();
                fila[0] = String.valueOf(entidad.getIdentificador());
                fila[1] = entidad.getNombre();
                fila[2] = entidad.getDomicilio();
                fila[3] = entidad.getLocalidad();
                fila[4] = String.valueOf(entidad.getCP());
                fila[5] = entidad.getProvincia();
                fila[6] = String.valueOf(entidad.getTelf1());
                fila[7] = String.valueOf(entidad.getTelf2());
                fila[8] = entidad.getEmail();
                m.addRow(fila);
            }     
            
            ecv.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            ecv.tablaEntidadConocida3.setRowSorter(ordenar);
            ecv.tablaEntidadConocida3.setModel(m);
            
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                cargarTablaEntidadConocidaEliminar();
                limpiarTextoPantallaEliminar();
            }     
            
            }
            
            ecv.txtFiltro2.setText("");          

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
           //e.printStackTrace();
            // JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
             
         } else {
             cargarTablaEntidadConocidaEliminar();
         }

    }

    public void cargarTablaEntidadConocidaPrincipal() {
      DefaultTableModel ff;

        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[9];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from entidadesconocidas";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString("identificador");
                fila[1] = cdb.resultado.getString("nombre");
                fila[2] = cdb.resultado.getString("domicilio");
                fila[3] = cdb.resultado.getString("localidad");
                fila[4] = cdb.resultado.getString("cp");
                fila[5] = cdb.resultado.getString("provincia");
                fila[6] = cdb.resultado.getString("telf1");
                fila[7] = cdb.resultado.getString("telf2");
                fila[8] = cdb.resultado.getString("email");

                ff.addRow(fila);
            }

            ecv.tablaEntidadConocida2.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            ecv.tablaEntidadConocida2.setRowSorter(ordenar);
            ecv.tablaEntidadConocida2.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla"+e, "Error", JOptionPane.ERROR_MESSAGE);
        }      

    }
    
    public void cargarTablaEntidadConocidaModificar() {
      DefaultTableModel ff;

        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[9];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from entidadesconocidas";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString("identificador");
                fila[1] = cdb.resultado.getString("nombre");
                fila[2] = cdb.resultado.getString("domicilio");
                fila[3] = cdb.resultado.getString("localidad");
                fila[4] = cdb.resultado.getString("cp");
                fila[5] = cdb.resultado.getString("provincia");
                fila[6] = cdb.resultado.getString("telf1");
                fila[7] = cdb.resultado.getString("telf2");
                fila[8] = cdb.resultado.getString("email");

                ff.addRow(fila);
            }

            ecv.tablaEntidadConocida1.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            ecv.tablaEntidadConocida1.setRowSorter(ordenar);
            ecv.tablaEntidadConocida1.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla"+e, "Error", JOptionPane.ERROR_MESSAGE);
        }      

    }
    
    public void cargarTablaEntidadConocidaEliminar() {
      DefaultTableModel ff;

        try {
            String[] titulo = {"Nro", "Nombre", "Domicilio", "Localidad", "C.P", "Provicia", "Telf1", "Telf2", "Email"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[9];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from entidadesconocidas";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString("identificador");
                fila[1] = cdb.resultado.getString("nombre");
                fila[2] = cdb.resultado.getString("domicilio");
                fila[3] = cdb.resultado.getString("localidad");
                fila[4] = cdb.resultado.getString("cp");
                fila[5] = cdb.resultado.getString("provincia");
                fila[6] = cdb.resultado.getString("telf1");
                fila[7] = cdb.resultado.getString("telf2");
                fila[8] = cdb.resultado.getString("email");

                ff.addRow(fila);
            }

            ecv.tablaEntidadConocida3.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            ecv.tablaEntidadConocida3.setRowSorter(ordenar);
            ecv.tablaEntidadConocida3.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla"+e, "Error", JOptionPane.ERROR_MESSAGE);
        }      

    }

    /*Limpiar Texto*/
    public void limpiarTextoPantallaInsertar() {
        ecv.txtNombreEntidad.setText("");
        ecv.txtLocalidad.setText("");
        ecv.txtDomicilio.setText("");
        ecv.txtTelf1.setText("");
        ecv.txtTelf2.setText("");
        ecv.txtCp.setText("");
        ecv.txtProvincia.setText("");
        ecv.txtEmail.setText("");
    }
    
     /*Limpiar Texto*/
    public void limpiarTextoPantallaModificar() {
        ecv.txtNombreEntidad5.setText("");
        ecv.txtLocalidad5.setText("");
        ecv.txtDomicilio5.setText("");
        ecv.txtTelf15.setText("");
        ecv.txtTelf25.setText("");
        ecv.txtCp5.setText("");
        ecv.txtProvincia5.setText("");
        ecv.txtEmail5.setText("");
    }
    
     /*Limpiar Texto*/
    public void limpiarTextoPantallaEliminar() {
        ecv.txtNombreEntidad4.setText("");
        ecv.txtLocalidad4.setText("");
        ecv.txtDomicilio4.setText("");
        ecv.txtTelf14.setText("");
        ecv.txtTelf24.setText("");
        ecv.txtCp4.setText("");
        ecv.txtProvincia4.setText("");
        ecv.txtEmail4.setText("");
    }
    
     public void limpiarTextoPantallaBuscar() {
        ecv.txtNombreEntidad3.setText("");
        ecv.txtLocalidad3.setText("");
        ecv.txtDomicilio3.setText("");
        ecv.txtTelf13.setText("");
        ecv.txtTelf23.setText("");
        ecv.txtCp3.setText("");
        ecv.txtProvincia3.setText("");
        ecv.txtEmail3.setText("");
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<EntidadesConocidas> buscarEntidadesConocidas(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ec = new EntidadesConocidas();
        return ec.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar una Entidad Conocida*/
    public void agregarEntidadConocida(int identificador, String nombre, String localidad, String domicilio, int telf1, int telf2, int cp, String provincia, String email) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ec = new EntidadesConocidas(identificador, nombre, localidad, domicilio, telf1, telf2, cp, provincia, email);
        ec.grabar();
    }
    
    /*Metodo para agregar una Entidad Conocida sin telf2*/
    public void agregarEntidadConocidaSintelf2(int identificador, String nombre, String localidad, String domicilio, int telf1, int cp, String provincia, String email) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ec = new EntidadesConocidas(identificador, nombre, localidad, domicilio, telf1, cp, provincia, email);
        ec.grabar();
    }

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarEntidadConocida(int identificador, String nombre, String localidad, String domicilio, int telf1, int telf2, int cp, String provincia, String email) {
        ec = new EntidadesConocidas(identificador, nombre, localidad, domicilio, telf1, telf2, cp, provincia, email);
        try {
            ec.actualizar();
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }
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
