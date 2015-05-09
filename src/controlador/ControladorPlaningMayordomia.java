package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import modelo.PlaningMayordomia;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.PlaningMayordomiaVista;

public class ControladorPlaningMayordomia implements ActionListener {

    PlaningMayordomia pm;
    PlaningMayordomiaVista pmv;

    public enum di {

        INSERTAR, BUSCAR, BUSCAR2, BUSCAR3, MODIFICAR, ELIMINAR, SALIR1, SALIR2, SALIR3, SALIR4
    }

    public void iniciar() {

        pm = new PlaningMayordomia();
        pmv = new PlaningMayordomiaVista();

        pmv.setVisible(true);
        pmv.setLocationRelativeTo(null);

        actualizarTbl1(); // cargamos las tablas
        actualizarTbl2();
        actualizarTbl3();

        //cargamos los combos
        cargarCmbBD1();
        cargarCmbBD2();
        cargarCmbBD3();

        //se añade las acciones a los controles del formulario padre
        pmv.btnInsertar.setActionCommand("INSERTAR");
        pmv.btnBuscar1.setActionCommand("BUSCAR");
        pmv.btnBuscar2.setActionCommand("BUSCAR2");
        pmv.btnBuscar3.setActionCommand("BUSCAR3");
        pmv.btnModificar.setActionCommand("MODIFICAR");
        pmv.btnEliminar.setActionCommand("ELIMINAR");
        pmv.btnSalir1.setActionCommand("SALIR1");
        pmv.btnSalir2.setActionCommand("SALIR2");
        pmv.btnSalir3.setActionCommand("SALIR3");
        pmv.btnSalir4.setActionCommand("SALIR4");
        //Se pone a escuchar las acciones del usuario
        pmv.btnInsertar.addActionListener(this);
        pmv.btnBuscar1.addActionListener(this);
        pmv.btnBuscar2.addActionListener(this);
        pmv.btnBuscar3.addActionListener(this);
        pmv.btnModificar.addActionListener(this);
        pmv.btnEliminar.addActionListener(this);
        pmv.btnSalir1.addActionListener(this);
        pmv.btnSalir2.addActionListener(this);
        pmv.btnSalir3.addActionListener(this);
        pmv.btnSalir4.addActionListener(this);

        pmv.tbl1.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla forma pago           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed1(evt);
            }
        });

        pmv.tbl2.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla forma pago           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed2(evt);
            }
        });

        pmv.tbl3.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla forma pago           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed3(evt);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String comando = e.getActionCommand();
        switch (comando) {

            case "INSERTAR":
                insertarPlaning();
                actualizarTbl1(); // cargamos las tablas
                actualizarTbl2();
                actualizarTbl3();
                limpiarPantalla1(); // limpiar pantalla
                break;

            case "BUSCAR":
                buscarPantalla();
                break;

            case "BUSCAR2":
                buscarPantalla2();
                break;

            case "BUSCAR3":
                buscarPantalla3();
                break;

            case "MODIFICAR":
                modificar();
                break;

            case "ELIMINAR":
                eliminar();
                limpiarPantalla4();
                break;

            case "SALIR1":
                pmv.dispose();
                break;

            case "SALIR2":
                pmv.dispose();
                break;

            case "SALIR3":
                pmv.dispose();
                break;

            case "SALIR4":
                pmv.dispose();
                break;
        }
    }

    public void insertarPlaning() {
        try {
            int identificador = Integer.parseInt(pmv.txtIdentificador1.getText());
            String hora = pmv.txtHora1.getText();

            // Trabajando con fecha
            int año = pmv.Jdate1.getCalendar().get(Calendar.YEAR);
            int mes = pmv.Jdate1.getCalendar().get(Calendar.MONTH);
            int dia = pmv.Jdate1.getCalendar().get(Calendar.DAY_OF_MONTH);
            String fecha = año + "-" + mes + "-" + dia;
            // fin

            String labor = pmv.txtLabor1.getText();

            agregarPlaningMayordomia(identificador, hora, fecha, labor);
        } catch (java.lang.NullPointerException e) {
            JOptionPane.showMessageDialog(null, "El campo fecha, debe contener algun valor", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void modificar() {
        try {
            int identificador = Integer.parseInt(pmv.txtIdentificador3.getText());
            String hora = pmv.txtHora3.getText();
            String labor = pmv.txtLabor3.getText();
            String fecha = pmv.txtFecha3.getText();

            modificarPlaningMayordomia(identificador, hora, fecha, labor);
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningMayordomia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Se ha introducido un parametro incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (java.lang.NullPointerException e) {
            // JOptionPane.showMessageDialog(null, "Fecha vacia");
        }

        actualizarTbl1(); // cargamos las tablas
        actualizarTbl2();
        actualizarTbl3();
    }

    public void eliminar() {
        try {
            int identificador = Integer.parseInt(pmv.txtIdentificador4.getText());
            eliminarPlaningMayordomia(identificador);
            JOptionPane.showMessageDialog(null, "Eliminado correctamente");
            this.actualizarTbl1();
            this.actualizarTbl2();
            this.actualizarTbl3();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningMayordomia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarPantalla() {
        //int contador = 1; // con este contador sacaremos el primero valor para devolverlo a la interfez (txt)
        PlaningMayordomia pm = null;
        DefaultTableModel m;

        String[] titulo = {"Nro", "Hora", "Fecha", "Labor"};
        m = new DefaultTableModel(null, titulo);
        JTable p = new JTable(m);
        String[] fila = new String[4];
        ArrayList<PlaningMayordomia> x = null;
        String campo = (String) pmv.cmb1.getSelectedItem();
        String filtro = pmv.txtFiltro1.getText();
        try {
            x = buscarPlaning(filtro, campo);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningMayordomia.class.getName()).log(Level.SEVERE, null, ex);
        }

        Iterator<PlaningMayordomia> it = x.iterator();
        while (it.hasNext()) {
            pm = it.next();
            fila[0] = String.valueOf(pm.getIdentificador());
            fila[1] = pm.getHora();
            fila[2] = pm.getFecha();
            fila[3] = pm.getLabor();
            m.addRow(fila);

            pmv.tbl1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            pmv.tbl1.setRowSorter(ordenar);
            pmv.tbl1.setModel(m);
        }

    }

    public void buscarPantalla2() {
        //int contador = 1; // con este contador sacaremos el primero valor para devolverlo a la interfez (txt)
        PlaningMayordomia pm = null;
        DefaultTableModel m;

        String[] titulo = {"Nro", "Hora", "Fecha", "Labor"};
        m = new DefaultTableModel(null, titulo);
        JTable p = new JTable(m);
        String[] fila = new String[4];
        ArrayList<PlaningMayordomia> x = null;
        String campo = (String) pmv.cmb2.getSelectedItem();
        String filtro = pmv.txtFiltro2.getText();
        try {
            x = buscarPlaning(filtro, campo);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningMayordomia.class.getName()).log(Level.SEVERE, null, ex);
        }

        Iterator<PlaningMayordomia> it = x.iterator();
        while (it.hasNext()) {
            pm = it.next();
            fila[0] = String.valueOf(pm.getIdentificador());
            fila[1] = pm.getHora();
            fila[2] = pm.getFecha();
            fila[3] = pm.getLabor();
            m.addRow(fila);

            pmv.tbl2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            pmv.tbl2.setRowSorter(ordenar);
            pmv.tbl2.setModel(m);
        }

    }

    public void buscarPantalla3() {
        PlaningMayordomia pm = null;
        DefaultTableModel m;

        String[] titulo = {"Nro", "Hora", "Fecha", "Labor"};
        m = new DefaultTableModel(null, titulo);
        JTable p = new JTable(m);
        String[] fila = new String[4];
        ArrayList<PlaningMayordomia> x = null;
        String campo = (String) pmv.cmb3.getSelectedItem();
        String filtro = pmv.txtFiltro3.getText();
        try {
            x = buscarPlaning(filtro, campo);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningMayordomia.class.getName()).log(Level.SEVERE, null, ex);
        }

        Iterator<PlaningMayordomia> it = x.iterator();
        while (it.hasNext()) {
            pm = it.next();
            fila[0] = String.valueOf(pm.getIdentificador());
            fila[1] = pm.getHora();
            fila[2] = pm.getFecha();
            fila[3] = pm.getLabor();
            m.addRow(fila);

            pmv.tbl3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            pmv.tbl3.setRowSorter(ordenar);
            pmv.tbl3.setModel(m);
        }

    }

    public void actualizarTbl1() {
        DefaultTableModel m;

        try {
            String[] titulo = {"Nro", "Hora", "Fecha", "Labor"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] x = new String[4];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT * FROM planingmayordomia";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                x[0] = cbd.resultado.getString("identificador");
                x[1] = cbd.resultado.getString("hora");
                x[2] = cbd.resultado.getString("fecha");
                x[3] = cbd.resultado.getString("labor");
                m.addRow(x);
            }
            pmv.tbl1.setModel(m);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void actualizarTbl2() {
        DefaultTableModel m;

        try {
            String[] titulo = {"Nro", "Hora", "Fecha", "Labor"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] x = new String[4];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT * FROM planingmayordomia";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                x[0] = cbd.resultado.getString("identificador");
                x[1] = cbd.resultado.getString("hora");
                x[2] = cbd.resultado.getString("fecha");
                x[3] = cbd.resultado.getString("labor");
                m.addRow(x);
            }
            pmv.tbl2.setModel(m);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void actualizarTbl3() {
        DefaultTableModel m;

        try {
            String[] titulo = {"Nro", "Hora", "Fecha", "Labor"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] x = new String[4];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT * FROM planingmayordomia";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                x[0] = cbd.resultado.getString("identificador");
                x[1] = cbd.resultado.getString("hora");
                x[2] = cbd.resultado.getString("fecha");
                x[3] = cbd.resultado.getString("labor");
                m.addRow(x);
            }
            pmv.tbl3.setModel(m);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla " + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void jTable1MousePressed1(java.awt.event.MouseEvent evt) {
        int clic = pmv.tbl1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            try {
                pmv.txtIdentificador2.setText((String) pmv.tbl1.getValueAt(clic, 0));
                pmv.txtHora2.setText((String) pmv.tbl1.getValueAt(clic, 1));
                pmv.txtFecha2.setText((String) pmv.tbl1.getValueAt(clic, 2));
                pmv.txtLabor2.setText((String) pmv.tbl1.getValueAt(clic, 3));
                // formateo de fecha en java           

            } catch (Exception e) {
            }
        }
    }

    private void jTable1MousePressed2(java.awt.event.MouseEvent evt) {
        int clic = pmv.tbl2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            try {
                pmv.txtIdentificador3.setText((String) pmv.tbl2.getValueAt(clic, 0));
                pmv.txtHora3.setText((String) pmv.tbl2.getValueAt(clic, 1));
                pmv.txtFecha3.setText((String) pmv.tbl2.getValueAt(clic, 2));
                pmv.txtLabor3.setText((String) pmv.tbl2.getValueAt(clic, 3));
                // formateo de fecha en java           

            } catch (Exception e) {
            }
        }
    }

    private void jTable1MousePressed3(java.awt.event.MouseEvent evt) {
        int clic = pmv.tbl3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            try {
                pmv.txtIdentificador4.setText((String) pmv.tbl3.getValueAt(clic, 0));
                pmv.txtHora4.setText((String) pmv.tbl3.getValueAt(clic, 1));
                pmv.txtFecha4.setText((String) pmv.tbl3.getValueAt(clic, 2));
                pmv.txtLabor4.setText((String) pmv.tbl3.getValueAt(clic, 3));
                // formateo de fecha en java           

            } catch (Exception e) {
            }
        }
    }

    private void cargarCmbBD1() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE planingmayordomia;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pmv.cmb1.removeAllItems();

            while (cbd.resultado.next()) {
                pmv.cmb1.addItem(cbd.resultado.getString(1));
            }
            pmv.cmb1.removeItemAt(4);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCmbBD2() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE planingmayordomia;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pmv.cmb2.removeAllItems();

            while (cbd.resultado.next()) {
                pmv.cmb2.addItem(cbd.resultado.getString(1));
            }
            pmv.cmb2.removeItemAt(4);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCmbBD3() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE planingmayordomia;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pmv.cmb3.removeAllItems();

            while (cbd.resultado.next()) {
                pmv.cmb3.addItem(cbd.resultado.getString(1));
            }
            pmv.cmb3.removeItemAt(4);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorEntidadesConocidas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void limpiarPantalla1() {
        pmv.txtIdentificador1.setText("");
        pmv.txtHora1.setText("");
        pmv.Jdate1.setDate(null);
        pmv.txtLabor1.setText("");
    }

    public void limpiarPantalla4() {
        pmv.txtIdentificador4.setText("");
        pmv.txtHora4.setText("");
        pmv.txtFecha4.setText("");
        pmv.txtLabor4.setText("");
        pmv.txtFiltro3.setText("");
    }

    public static Date ParseFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = null;
        try {
            fechaDate = (Date) formato.parse(fecha);
        } catch (ParseException ex) {
            System.out.println(ex);
        }
        return fechaDate;
    }

    /*Metodo para agregar un PlaningMayordomia*/
    public void agregarPlaningMayordomia(int identificador, String hora, String fecha, String labor) {

        try {
            pm = new PlaningMayordomia(identificador, hora, fecha, labor);
            pm.grabar();
            JOptionPane.showMessageDialog(null, "Insertado correctamente");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            //Logger.getLogger(ControladorPlaningMayordomia.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "El Identificador " + pmv.txtIdentificador1.getText() + " ya existe, ingrese un identificador distinto " + ex, "SofCofradias", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*Metodo para modificar un PlaningMayordomia*/
    public void modificarPlaningMayordomia(int identificador, String hora, String fecha, String labor) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        pm = new PlaningMayordomia(identificador, hora, fecha, labor);
        pm.actualizar();
    }

    /*Metodo para borrar un PlaningMayordomia*/
    public void eliminarPlaningMayordomia(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        PlaningMayordomia pm = new PlaningMayordomia(identificador);
        pm.borrar();
    }

    /*Metodo para leer todos los PlaningMayordomia*/
    public ArrayList<PlaningMayordomia> recargarPlaningMayordomia() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        PlaningMayordomia pm = new PlaningMayordomia();
        return pm.leerTodos();
    }

    /*Metodo para buscar una Hermandad indicando el campo y el valor*/
    public ArrayList<PlaningMayordomia> buscarPlaning(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        pm = new PlaningMayordomia();
        return pm.buscarFiltro(filtro, campo);
    }

}
