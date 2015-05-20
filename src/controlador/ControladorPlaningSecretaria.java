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
import modelo.PlaningSecretaria;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.PlaningSecretariaVista;

public class ControladorPlaningSecretaria implements ActionListener {

    PlaningSecretaria pS;
    PlaningSecretariaVista pSvista;
    int identificadorModificar = 0;
    int identificadorEliminar = 0;

    public void iniciar() {
        //Planing
        pS = new PlaningSecretaria();
        pSvista = new PlaningSecretariaVista();
        pSvista.setVisible(true);
        pSvista.setLocationRelativeTo(null);
        
        cargarTablaBuscar();
        cargarTablaEliminar();
        cargarTablaModificar();
        
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
                ingresar();
                break;
            case "BUSCAR1":
                cargarTablaSecretaria2();
                pSvista.txtFiltro1.setText("");
            pSvista.txtFiltro2.setText("");
            pSvista.txtFiltro3.setText("");
                break;
            case "BUSCAR2":
                cargarTablaSecretaria3();
                pSvista.txtFiltro1.setText("");
            pSvista.txtFiltro2.setText("");
            pSvista.txtFiltro3.setText("");
                break;
            case "BUSCAR3":
                cargarTabla4();
                pSvista.txtFiltro1.setText("");
            pSvista.txtFiltro2.setText("");
            pSvista.txtFiltro3.setText("");
                break;
            case "ELIMINAR":
                eliminarSeleccion();
                break;
            case "MODIFICAR":
                modificar();                
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

    public void modificar() {
        String hora3 = pSvista.txthora5.getText();
        // trabajando con fechas
        String fecha3 = "";
        if (pSvista.jDateChooser3.getDate() != null) {
            int año = pSvista.jDateChooser3.getCalendar().get(Calendar.YEAR);
            int mes = pSvista.jDateChooser3.getCalendar().get(Calendar.MONTH);
            int dia = pSvista.jDateChooser3.getCalendar().get(Calendar.DAY_OF_MONTH);
            fecha3 = año + "-" + mes + "-" + dia;
            //
        }
        // fin
        String descripcion3 = pSvista.txtDescripcion5.getText();

        try {

            modificarPlaning(identificadorModificar, hora3, Date.valueOf(fecha3), descripcion3);
            JOptionPane.showMessageDialog(null, "Modificado Correctamente");
            cargarTablaBuscar();
            cargarTablaEliminar();
            cargarTablaModificar();
            limpiarTextoModificar();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            ex.printStackTrace();
//JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ingresar() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select max(identificador) from planingsecretaria");
            int identificador = 1;
            if (cbd.resultado.next()) {
                identificador = cbd.resultado.getInt(1) + 1;
            } else {
                identificador = 1;
            }
            String hora = pSvista.txtHora.getText();
            // trabajando con fechas
            String fecha = "";
            if (pSvista.jDateChooser1.getDate() != null) {
                int año = pSvista.jDateChooser1.getCalendar().get(Calendar.YEAR);
                int mes = pSvista.jDateChooser1.getCalendar().get(Calendar.MONTH);
                int dia = pSvista.jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                fecha = año + "-" + mes + "-" + dia;
                //
            }
            // fin
            String descripcion = pSvista.txtDescripcion.getText();

            agregarPlaning(identificador, hora, Date.valueOf(fecha), descripcion);
            JOptionPane.showMessageDialog(null, "Insertado Correctamente");
            cargarTablaBuscar();
            cargarTablaEliminar();
            cargarTablaModificar();
            limpiarTextoIngresar();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarSeleccion() {
        try {
            eliminarHermandadHermanadas(identificadorEliminar);
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
            cargarTablaBuscar();
            cargarTablaEliminar();
            cargarTablaModificar();
            limpiarTextoEliminar();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }
        pSvista.txtFiltro2.setText("");
    }

    private void cargarCmbBD1() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE planingsecretaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pSvista.cmbBD1.removeAllItems();

            while (cbd.resultado.next()) {
                pSvista.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCmbBD2() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE planingsecretaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pSvista.cmbBD2.removeAllItems();

            while (cbd.resultado.next()) {
                pSvista.cmbBD2.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void cargarCmbBD3() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE planingsecretaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pSvista.cmbBD3.removeAllItems();

            while (cbd.resultado.next()) {
                pSvista.cmbBD3.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            int clic = pSvista.tablaEntidadConocida4.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla

            identificadorModificar = Integer.parseInt((String) pSvista.tablaEntidadConocida4.getValueAt(clic, 0));
            pSvista.txthora5.setText((String) pSvista.tablaEntidadConocida4.getValueAt(clic, 1));
            int identificador = Integer.parseInt((String) pSvista.tablaEntidadConocida4.getValueAt(clic, 0));
            Date fecha = null;
            cbd.resultado = cbd.un_st.executeQuery("select fecha from planingsecretaria where identificador=" + identificador);
            if (cbd.resultado.next()) {
                fecha = cbd.resultado.getDate(1);
            }
            pSvista.jDateChooser3.setDate(fecha);
            pSvista.txtDescripcion5.setText((String) pSvista.tablaEntidadConocida4.getValueAt(clic, 2));
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            int clic = pSvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla

            //pSvista.txtIdentificador3.setText((String) pSvista.tablaEntidadConocida2.getValueAt(clic, 0));
            pSvista.txtHora3.setText((String) pSvista.tablaEntidadConocida2.getValueAt(clic, 1));
            int identificador = Integer.parseInt((String) pSvista.tablaEntidadConocida2.getValueAt(clic, 0));
            Date fecha = null;
            cbd.resultado = cbd.un_st.executeQuery("select fecha from planingsecretaria where identificador=" + identificador);
            if (cbd.resultado.next()) {
                fecha = cbd.resultado.getDate(1);
            }
            pSvista.jDateChooser2.setDate(fecha);
            pSvista.txtDescripcion3.setText((String) pSvista.tablaEntidadConocida2.getValueAt(clic, 2));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            int clic = pSvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla

            identificadorEliminar = Integer.parseInt((String) pSvista.tablaEntidadConocida3.getValueAt(clic, 0));
            pSvista.txtNombreEntidad4.setText((String) pSvista.tablaEntidadConocida3.getValueAt(clic, 1));
            int identificador = Integer.parseInt((String) pSvista.tablaEntidadConocida3.getValueAt(clic, 0));
            Date fecha = null;
            cbd.resultado = cbd.un_st.executeQuery("select fecha from planingsecretaria where identificador=" + identificador);
            if (cbd.resultado.next()) {
                fecha = cbd.resultado.getDate(1);
            }
            pSvista.jDateChooser4.setDate(fecha);
            pSvista.txtDescripcion4.setText((String) pSvista.tablaEntidadConocida3.getValueAt(clic, 2));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPlaningSecretaria.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void cargarTablaBuscar() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Hora", "Descripcion", "Fecha"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[4];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from planingsecretaria order by identificador asc";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("hora");
                fila[3] = cbd.resultado.getString("fecha");
                fila[2] = cbd.resultado.getString("descripcion");
                m.addRow(fila);
            }

            pSvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            pSvista.tablaEntidadConocida2.setRowSorter(ordenar);
            pSvista.tablaEntidadConocida2.setModel(m);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
           //e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void cargarTablaModificar() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Hora", "Descripcion", "Fecha"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[4];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from planingsecretaria order by identificador asc";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
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
           //e.printStackTrace();
           JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    public void cargarTablaEliminar() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Hora", "Descripcion", "Fecha"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[4];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from planingsecretaria order by identificador asc";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("hora");
                fila[3] = cbd.resultado.getString("fecha");
                fila[2] = cbd.resultado.getString("descripcion");
                m.addRow(fila);
            }

            pSvista.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            pSvista.tablaEntidadConocida3.setRowSorter(ordenar);
            pSvista.tablaEntidadConocida3.setModel(m);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
           //e.printStackTrace();
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
            ArrayList<PlaningSecretaria> x;
            String campo = (String) pSvista.cmbBD3.getSelectedItem();
            String filtro = pSvista.txtFiltro3.getText();
            if (!filtro.equalsIgnoreCase("")) {
            x = pS.buscarFiltro(filtro, campo);
            
            if (x.size()>0) {

            Iterator<PlaningSecretaria> it = x.iterator();
            while (it.hasNext()) {
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
            
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
            }
            
            } else {
                
            }

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
            ArrayList<PlaningSecretaria> x;
            String campo = (String) pSvista.cmbBD1.getSelectedItem();
            String filtro = pSvista.txtFiltro1.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
                 x = pS.buscarFiltro(filtro, campo);
                 
                 if (x.size()>0) {
                      Iterator<PlaningSecretaria> it = x.iterator();
            while (it.hasNext()) {
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
                 } else {
                     JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                     cargarTablaBuscar();
                     limpiarTextoBuscar();
                 }    
            
            } else {
                
            }
            
           

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
            ArrayList<PlaningSecretaria> x;
            String campo = (String) pSvista.cmbBD2.getSelectedItem();
            String filtro = pSvista.txtFiltro2.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
            x = pS.buscarFiltro(filtro, campo);
            
            if (x.size()>0) {

            Iterator<PlaningSecretaria> it = x.iterator();
            while (it.hasNext()) {
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
            
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                cargarTablaEliminar();
                limpiarTextoEliminar();
            }
            
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }


    
     /*Limpiar Texto*/
    public void limpiarTextoModificar() {
        //pSvista.txtIdentificador4.setText("");
        pSvista.txthora5.setText("");
        pSvista.jDateChooser3.setDate(null);
        pSvista.txtDescripcion5.setText("");
    }
    
      /*Limpiar Texto*/
    public void limpiarTextoEliminar() {
        //pSvista.txtIdentificador4.setText("");
        pSvista.txtNombreEntidad4.setText("");
        pSvista.jDateChooser4.setDate(null);
        pSvista.txtDescripcion4.setText("");
    }
    
       /*Limpiar Texto*/
    public void limpiarTextoBuscar() {
        //pSvista.txtIdentificador4.setText("");
        pSvista.txtHora3.setText("");
        pSvista.jDateChooser2.setDate(null);
        pSvista.txtDescripcion3.setText("");
    }
    
         /*Limpiar Texto*/
    public void limpiarTextoIngresar() {
        //pSvista.txtIdentificador4.setText("");
        pSvista.txtHora.setText("");
        pSvista.jDateChooser1.setDate(null);
        pSvista.txtDescripcion.setText("");
    }
    
    

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<PlaningSecretaria> buscarPlaning(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        pS = new PlaningSecretaria();
        return pS.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar una Entidad Conocida*/
    public void agregarPlaning(int id, String hora, Date fecha, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        pS = new PlaningSecretaria(id, hora, fecha, descripcion);
        pS.grabar();
    }

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarPlaning(int identificador, String hora, Date fecha, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        pS = new PlaningSecretaria(identificador, hora, fecha, descripcion);
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
