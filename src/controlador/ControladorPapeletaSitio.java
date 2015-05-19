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
import modelo.LoteriasYSorteos;
import modelo.PapeletaSitio;
import modelo.RepartoLoterias;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.LoteriasYSorteosVista;
import vista.PapeletaSitioVista;

public class ControladorPapeletaSitio implements ActionListener {

    PapeletaSitio ps;
    PapeletaSitioVista psvista;
    
    public void iniciar() {
        ps = new PapeletaSitio();
        psvista = new PapeletaSitioVista();
        psvista.setVisible(true);
        psvista.setLocationRelativeTo(null);
        
        cargarTablaPapeleta();
        
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
                String identificador = psvista.txtIdentificador6.getText();
                Date fecha = Date.valueOf(psvista.txtfecha3.getText());
                String num_hermano_id = psvista.txtnumhermanoid6.getText();
                String sale = psvista.txtsale3.getText();
                String donativo = psvista.txtdonativo3.getText();
                String salida_procesional_id = psvista.txtsalida3.getText();
                String numero_papeleta = psvista.txtnumpapeleta3.getText();
                
                try {
                    agregarPapeleta(Integer.parseInt(identificador), Integer.parseInt(num_hermano_id), fecha, Integer.parseInt(sale), Integer.parseInt(donativo), Integer.parseInt(salida_procesional_id), Integer.parseInt(numero_papeleta));
                    JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                }   
                cargarTablaPapeleta();
                cargarTablaPapeleta2();
                cargarTablaPapeleta3();
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
                String identificador3 = psvista.txtIdentificador4.getText();
                String num_hermano_id4 = psvista.txtnumhermanoid4.getText();
                String fecha4 = psvista.txtfecha1.getText();
                String sale4 = psvista.txtsale1.getText();
                String donativo4 = psvista.txtdonativo1.getText();
                String salida4 = psvista.txtsalida4.getText();
                String num_papeleta4 = psvista.txtnumpapeleta1.getText();
                
                try {
                    modificarPapeleta(Integer.parseInt(identificador3), Integer.parseInt(num_hermano_id4), Date.valueOf(fecha4), Integer.parseInt(sale4), Integer.parseInt(donativo4), Integer.parseInt(salida4), Integer.parseInt(num_papeleta4));                  JOptionPane.showMessageDialog(null, "¡Modificado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                }
                
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
        int clic = psvista.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        psvista.txtIdentificador5.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 0));
        psvista.txtnumhermanoid5.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 1));
        psvista.txtfecha2.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 2));
        psvista.txtsale2.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 3));
        
        psvista.txtdonativo2.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 4));
        psvista.txtloteriasid5.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 5));
        psvista.txtnumpapeleta2.setText((String) psvista.tablaEntidadConocida1.getValueAt(clic, 6));
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = psvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        
        psvista.txtIdentificador4.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 0));
        psvista.txtnumhermanoid4.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 1));
        psvista.txtfecha1.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 2));
        psvista.txtsale1.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 3));
        psvista.txtdonativo1.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 4));
        psvista.txtsalida4.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 5));
        psvista.txtnumpapeleta1.setText((String) psvista.tablaEntidadConocida2.getValueAt(clic, 6));
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = psvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
       psvista.txtIdentificador1.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 0));
        psvista.txtnumhermanoid1.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 1));
        psvista.txtfecha.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 2));
        psvista.txtsale.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 3));
        psvista.txtdonativo.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 4));
        psvista.txtloteriasid1.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 5));
        psvista.txtnumpapeleta.setText((String) psvista.tablaEntidadConocida3.getValueAt(clic, 6));
    }
    
    public void cargarTablaPapeleta() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Num hermano id", "fecha", "Sale", "Donativo", "Salida procesional id", "Numero papeleta"};   
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[7];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from papeletasitio";
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
            x = ps.buscarFiltro(filtro, campo);
            
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
            
            psvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            psvista.tablaEntidadConocida2.setRowSorter(ordenar);
            psvista.tablaEntidadConocida2.setModel(m);
            
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
            x = ps.buscarFiltro(filtro, campo);
            
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
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }

    /*Limpiar Texto*/
    public void limpiarTexto() {
        psvista.txtIdentificador4.setText("");
        psvista.txtnumhermanoid4.setText("");
        psvista.txtsale1.setText("");
        psvista.txtfecha1.setText("");
        psvista.txtdonativo1.setText("");
        psvista.txtsalida4.setText("");
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

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<PapeletaSitio> buscarPapeleta(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ps = new PapeletaSitio();
        return ps.buscarFiltro(filtro, campo);
    }
    
    /*Metodo para agregar una Entidad Conocida*/
 

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarPapeleta(int identificador, int numero_hermano_id, Date fecha, int sale, int donativo, int salida_procesional_id, int numero_papeleta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ps = new PapeletaSitio(identificador,numero_hermano_id,fecha,sale,donativo,salida_procesional_id,numero_papeleta);
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

    private void agregarPapeleta(int identificador, int numero_hermano_id, Date fecha, int sale, int donativo, int salida_procesional_id, int numero_papeleta) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        ps = new PapeletaSitio(identificador,numero_hermano_id,fecha,sale,donativo,salida_procesional_id,numero_papeleta);
        ps.grabar();
    }

}