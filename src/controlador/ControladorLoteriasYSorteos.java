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
import modelo.LoteriasYSorteos;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.LoteriasYSorteosVista;

public class ControladorLoteriasYSorteos implements ActionListener {

    LoteriasYSorteos ls;
    LoteriasYSorteosVista Lsvista;
    
    int identificadorModificar = 0;
    
    
    public void iniciar() {
        ls = new LoteriasYSorteos();
        Lsvista = new LoteriasYSorteosVista();
        Lsvista.setVisible(true);
        Lsvista.setLocationRelativeTo(null);
        
        cargarTablaSecretaria();
        
        cargarCmbBD1();
        cargarCmbBD2();

        //se añade las acciones a los controles del formulario padre
        Lsvista.btnIngresar.setActionCommand("INGRESAR");
        Lsvista.btnBuscar.setActionCommand("BUSCAR1");
        Lsvista.btnBuscar2.setActionCommand("BUSCAR2");
        Lsvista.btnEliminar.setActionCommand("ELIMINAR");
        Lsvista.btnSalir.setActionCommand("SALIR1");
        Lsvista.btnSalir2.setActionCommand("SALIR2");
        Lsvista.btnSalir3.setActionCommand("SALIR3");
        Lsvista.btnSalir4.setActionCommand("SALIR4");
        Lsvista.modificar3.setActionCommand("MODIFICAR");
        Lsvista.btnreparto.setActionCommand("REPARTO");
        //Se pone a escuchar las acciones del usuario
        Lsvista.btnIngresar.addActionListener(this);
        Lsvista.btnBuscar.addActionListener(this);
        Lsvista.btnBuscar2.addActionListener(this);
        Lsvista.btnEliminar.addActionListener(this);
        Lsvista.btnSalir.addActionListener(this);
        Lsvista.btnSalir2.addActionListener(this);
        Lsvista.btnSalir3.addActionListener(this);
        Lsvista.btnSalir4.addActionListener(this);
        Lsvista.modificar3.addActionListener(this);
        Lsvista.btnreparto.addActionListener(this);
        //Al Hacer click a una fila de la tabla los valores se cargaran en los cuadros de texto correspondientes
        Lsvista.tablaEntidadConocida1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
            }
        });
        
        Lsvista.tablaEntidadConocida2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida2MousePressed(evt);
            }
        });
        
        Lsvista.tablaEntidadConocida3.addMouseListener(new java.awt.event.MouseAdapter() {
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
                break;
            case "BUSCAR2":
                cargarTablaSecretaria3();
                break;
            case "ELIMINAR":
                eliminarSeleccion();
                break;
            case "MODIFICAR":
                modificar();            
                
                break;
            case "REPARTO":
                ControladorRepartoLoterias rl = new ControladorRepartoLoterias();
                rl.iniciar();
                break;
            case "SALIR1":
                Lsvista.dispose();
                break;
            case "SALIR2":
                Lsvista.dispose();
                break;
            case "SALIR3":
                Lsvista.dispose();
                break;
            case "SALIR4":
                Lsvista.dispose();
                break;
        }

    }
    
    public void ingresar() {
        
                try {
                    
                    int identificador = 1;
                    
                    Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
                    cbd.resultado = cbd.un_st.executeQuery("select max(identificador) from loterias");
                    
                    if (cbd.resultado.next()) {
                        identificador = cbd.resultado.getInt(1)+1;
                    } else {
                        identificador = 1;
                    }
                    
                    // trabajando con fechas
            String fecha = "";
            if (Lsvista.jDateChooser1.getDate() != null) {
                    int año = Lsvista.jDateChooser1.getCalendar().get(Calendar.YEAR);
                    int mes = Lsvista.jDateChooser1.getCalendar().get(Calendar.MONTH);
                    int dia = Lsvista.jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                    fecha = año + "-" + mes + "-" + dia;
                    //
                }
            // fin
                    String sorteo = Lsvista.txtNombre.getText();
                    double cantidad = Double.parseDouble(Lsvista.txtCantidad.getText());
                    double ganancia = Double.parseDouble(Lsvista.txtGanaciaUnidad.getText());
                    double precio_unidad = Double.parseDouble(Lsvista.txtPrecioUnidad.getText());
                    
                    try {
                        agregarLoterias(identificador, sorteo, fecha, cantidad, ganancia, precio_unidad);
                        JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                        limpiarTexto();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                    }
                    cargarTablaSecretaria();
                    cargarTablaSecretaria2();
                    cargarTablaSecretaria3();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    Logger.getLogger(ControladorLoteriasYSorteos.class.getName()).log(Level.SEVERE, null, ex);
                }   
    }
    
    public void modificar(){
        String identificador3 = Lsvista.txtIdentificador3.getText();
                String fecha3 = "";
                if (Lsvista.jDateChooser3.getDate() != null) {
                    int año = Lsvista.jDateChooser3.getCalendar().get(Calendar.YEAR);
                    int mes = Lsvista.jDateChooser3.getCalendar().get(Calendar.MONTH);
                    int dia = Lsvista.jDateChooser3.getCalendar().get(Calendar.DAY_OF_MONTH);
                    fecha3 = año + "-" + mes + "-" + dia;
                    //
                }
                // fin fecha
                String sorteo3 = Lsvista.txtNombre3.getText();
                String cantidad3 = Lsvista.txtCantidad3.getText();
                String ganancia3 = Lsvista.txtGanaciaUnidad3.getText();
                String precio_unidad3 = Lsvista.txtPrecioUnidad3.getText();
                
                try {
                    modificarLoterias(Integer.parseInt(identificador3), sorteo3, fecha3, Double.parseDouble(cantidad3), Double.parseDouble(ganancia3), Double.parseDouble(precio_unidad3));
                    JOptionPane.showMessageDialog(null, "¡Modificado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                }
    }
    
    private void eliminarSeleccion() {
        String seleccion = Lsvista.txtIdentificador4.getText();
        try {
            eliminarLoterias(Integer.parseInt(seleccion));
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLoteriasYSorteos.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaSecretaria();
        limpiarTexto2();
        Lsvista.tablaEntidadConocida3.setModel(new DefaultTableModel());
        Lsvista.txtFiltro2.setText("");
    }
    
    
    private void cargarCmbBD1() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE planingsecretaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            Lsvista.cmbBD1.removeAllItems();
            
            while(cbd.resultado.next()){
                Lsvista.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLoteriasYSorteos.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void cargarCmbBD2() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE planingsecretaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            Lsvista.cmbBD2.removeAllItems();
            
            while(cbd.resultado.next()){
                Lsvista.cmbBD2.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLoteriasYSorteos.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {                                     
        try {
            int clic = Lsvista.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            Lsvista.txtIdentificador2.setText((String) Lsvista.tablaEntidadConocida1.getValueAt(clic, 0));
            String id = (String) Lsvista.tablaEntidadConocida1.getValueAt(clic, 0);
            Lsvista.txtNombre2.setText((String) Lsvista.tablaEntidadConocida1.getValueAt(clic, 1));
            cbd.resultado = cbd.un_st.executeQuery("select fecha_devolucion from loterias where identificador="+id);
            if (cbd.resultado.next()) {
            Lsvista.jDateChooser2.setDate(cbd.resultado.getDate(1));
            }
            Lsvista.txtCantidad2.setText((String) Lsvista.tablaEntidadConocida1.getValueAt(clic, 3));
            Lsvista.txtGanaciaUnidad2.setText((String) Lsvista.tablaEntidadConocida1.getValueAt(clic, 5));
            Lsvista.txtPrecioUnidad2.setText((String) Lsvista.tablaEntidadConocida1.getValueAt(clic, 4));
            //Lsvista.txtCantidad2.setText((String) Lsvista.tablaEntidadConocida1.getValueAt(clic, 2));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLoteriasYSorteos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {                                     
        try {
            int clic = Lsvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            
            Lsvista.txtIdentificador3.setText((String) Lsvista.tablaEntidadConocida2.getValueAt(clic, 0));
            String id = (String) Lsvista.tablaEntidadConocida2.getValueAt(clic, 0);
            Lsvista.txtNombre3.setText((String) Lsvista.tablaEntidadConocida2.getValueAt(clic, 1));
            cbd.resultado = cbd.un_st.executeQuery("select fecha_devolucion from loterias where identificador="+id);
            if (cbd.resultado.next()) {            
            Lsvista.jDateChooser3.setDate(cbd.resultado.getDate(1));
            } else {
                JOptionPane.showMessageDialog(null, "nada");
            }
            Lsvista.txtCantidad3.setText((String) Lsvista.tablaEntidadConocida2.getValueAt(clic, 3));
            Lsvista.txtGanaciaUnidad3.setText((String) Lsvista.tablaEntidadConocida2.getValueAt(clic, 5));
            Lsvista.txtPrecioUnidad3.setText((String) Lsvista.tablaEntidadConocida2.getValueAt(clic, 4));
            //Lsvista.txtCantidad3.setText((String) Lsvista.tablaEntidadConocida1.getValueAt(clic, 6));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLoteriasYSorteos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {                                     
        try {
            int clic = Lsvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            
            Lsvista.txtIdentificador4.setText((String) Lsvista.tablaEntidadConocida3.getValueAt(clic, 0));
            String id = (String) Lsvista.tablaEntidadConocida3.getValueAt(clic, 0);
            Lsvista.txtNombre4.setText((String) Lsvista.tablaEntidadConocida3.getValueAt(clic, 1));
            cbd.resultado = cbd.un_st.executeQuery("select fecha_devolucion from loterias where identificador="+id);
            if (cbd.resultado.next()) {
            Lsvista.jDateChooser4.setDate(cbd.resultado.getDate(1));
            }
            Lsvista.txtCantidad4.setText((String) Lsvista.tablaEntidadConocida3.getValueAt(clic, 3));
            Lsvista.txtGanaciaUnidad4.setText((String) Lsvista.tablaEntidadConocida3.getValueAt(clic, 5));
            Lsvista.txtPrecioUnidad4.setText((String) Lsvista.tablaEntidadConocida3.getValueAt(clic, 4));
           // Lsvista.txtCantidad4.setText((String) Lsvista.tablaEntidadConocida1.getValueAt(clic, 6));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLoteriasYSorteos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cargarTablaSecretaria() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Sorteo", "Fecha", "Cantidad", "Precio/u", "Ganancia/u"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[6];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from loterias";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            while(cbd.resultado.next()){
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("sorteo");
                fila[2] = cbd.resultado.getString("fecha_devolucion");
                fila[3] = cbd.resultado.getString("cantidad");
                fila[4] = cbd.resultado.getString("precio_unidad");
                fila[5] = cbd.resultado.getString("ganancia_unidad");
                
                m.addRow(fila);
            }
            
            Lsvista.tablaEntidadConocida1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            Lsvista.tablaEntidadConocida1.setRowSorter(ordenar);
            Lsvista.tablaEntidadConocida1.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaSecretaria2() {
        LoteriasYSorteos loteria = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Sorteo", "Fecha devolución", "Cantidad", "Precio unidad", "Ganancia unidad"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[6];
            ArrayList <LoteriasYSorteos> x;
            String campo = (String) Lsvista.cmbBD1.getSelectedItem();
            String filtro = Lsvista.txtFiltro1.getText();
            x = ls.buscarFiltro(filtro, campo);
            
            Iterator <LoteriasYSorteos> it = x.iterator();
            while(it.hasNext()){
                loteria = it.next();
                fila[0] = String.valueOf(loteria.getIdentificador());
                fila[1] = loteria.getSorteo();
                fila[2] = loteria.getFecha_devolucion();
                fila[3] = String.valueOf(loteria.getCantidad());
                fila[4] = String.valueOf(loteria.getPrecio_unidad());
                fila[5] = String.valueOf(loteria.getGanancia_unidad());
                m.addRow(fila);
            }           
            
            Lsvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            Lsvista.tablaEntidadConocida2.setRowSorter(ordenar);
            Lsvista.tablaEntidadConocida2.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaSecretaria3() {
        LoteriasYSorteos loteria = null;
        DefaultTableModel m;
        try {
                        
            String[] titulo = {"Nro", "Sorteo", "Fecha devolución", "Cantidad", "Precio unidad", "Ganancia unidad"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[6];
            ArrayList <LoteriasYSorteos> x;
            String campo = (String) Lsvista.cmbBD2.getSelectedItem();
            String filtro = Lsvista.txtFiltro2.getText();
            x = ls.buscarFiltro(filtro, campo);
            
            Iterator <LoteriasYSorteos> it = x.iterator();
            while(it.hasNext()){
                loteria = it.next();
                fila[0] = String.valueOf(loteria.getIdentificador());
                fila[1] = loteria.getSorteo();
                fila[2] = loteria.getFecha_devolucion().toString();
                fila[3] = String.valueOf(loteria.getCantidad());
                fila[4] = String.valueOf(loteria.getPrecio_unidad());
                fila[5] = String.valueOf(loteria.getGanancia_unidad());
          
                m.addRow(fila);
            }           
            
            Lsvista.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            Lsvista.tablaEntidadConocida3.setRowSorter(ordenar);
            Lsvista.tablaEntidadConocida3.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }

    /*Limpiar Texto*/
    public void limpiarTexto() {
        Lsvista.txtIdentificador4.setText("");
        Lsvista.txtNombre4.setText("");
        Lsvista.txtCantidad4.setText("");
        Lsvista.jDateChooser4.setDate(null);
        Lsvista.txtPrecioUnidad4.setText("");
    }
    
    /*Limpiar Texto*/
    public void limpiarTexto2() {
        Lsvista.txtIdentificador4.setText("");
        Lsvista.txtNombre4.setText("");
        Lsvista.txtCantidad4.setText("");
        Lsvista.jDateChooser4.setDate(null);
        Lsvista.txtPrecioUnidad4.setText("");
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<LoteriasYSorteos> buscarLoterias(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ls = new LoteriasYSorteos();
        return ls.buscarFiltro(filtro, campo);
    }
    
    /*Metodo para agregar una Entidad Conocida*/
 

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarLoterias(int id,String sorteo, String fecha, double cantidad, double ganancia, double precio_unidad) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ls = new LoteriasYSorteos(id,sorteo,fecha,cantidad,ganancia,precio_unidad);
        ls.actualizar();
    }

    /*Metodo para borrar una Entidad Conocida*/
    public void eliminarLoterias(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ls = new LoteriasYSorteos(identificador);
        ls.borrar();
    }

    /*Metodo para leer todas las Entidades Conocidas*/
    public ArrayList<LoteriasYSorteos> leerTodas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ls = new LoteriasYSorteos();
        return ls.leerTodos();
    }

    private void agregarLoterias(int id, String sorteo, String fecha, double cantidad, double ganancia, double precio_unidad) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ls = new LoteriasYSorteos(id,sorteo,fecha,cantidad,ganancia,precio_unidad);
        ls.grabar();
    }
}