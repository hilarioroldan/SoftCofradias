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
import modelo.PlaningSecretaria;
import modelo.RepartoLoterias;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.PlaningSecretariaVista;
import vista.RepartoLoteriasVista;

public class ControladorRepartoLoterias implements ActionListener {

    RepartoLoterias rl;
    RepartoLoteriasVista rlvista;
    
    
    public void iniciar() {
        //Planing
        rl = new RepartoLoterias();
        rlvista = new RepartoLoteriasVista();
        rlvista.setVisible(true);
        rlvista.setLocationRelativeTo(null);
        
        cargarTablaReparto();
        
        cargarCmbBD1();
        cargarCmbBD2();

        //se añade las acciones a los controles del formulario padre
        rlvista.btnIngresar.setActionCommand("INGRESAR");
        rlvista.btnBuscar.setActionCommand("BUSCAR1");
        rlvista.btnBuscar2.setActionCommand("BUSCAR2");
        rlvista.btnEliminar.setActionCommand("ELIMINAR");
        rlvista.btnSalir.setActionCommand("SALIR1");
        rlvista.btnSalir2.setActionCommand("SALIR2");
        rlvista.btnSalir3.setActionCommand("SALIR3");
        rlvista.btnSalir4.setActionCommand("SALIR4");
        rlvista.modificar3.setActionCommand("MODIFICAR");
        //Se pone a escuchar las acciones del usuario
        rlvista.btnIngresar.addActionListener(this);
        rlvista.btnBuscar.addActionListener(this);
        rlvista.btnBuscar2.addActionListener(this);
        rlvista.btnEliminar.addActionListener(this);
        rlvista.btnSalir.addActionListener(this);
        rlvista.btnSalir2.addActionListener(this);
        rlvista.btnSalir3.addActionListener(this);
        rlvista.btnSalir4.addActionListener(this);
        rlvista.modificar3.addActionListener(this);
        
        //Al Hacer click a una fila de la tabla los valores se cargaran en los cuadros de texto correspondientes
        rlvista.tablaEntidadConocida1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
            }
        });
        
        rlvista.tablaEntidadConocida2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida2MousePressed(evt);
            }
        });
        
        rlvista.tablaEntidadConocida3.addMouseListener(new java.awt.event.MouseAdapter() {
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
                ingresarLoteria();
                break;
                
            case "BUSCAR1":
                cargarTablaReparto2();
                break;
            case "BUSCAR2":
                cargarTablaReparto3();
                break;
            case "ELIMINAR":
                eliminarSeleccion();
                break;
            case "MODIFICAR":
                String identificador3 = rlvista.txtIdentificador3.getText();
                String num_hermano_id3 = rlvista.txtnumhermanoid3.getText();
                String num_desde3 = rlvista.txtnumdesde3.getText();
                String num_hasta3 = rlvista.txtnumhasta3.getText();
                String cantidad3 = rlvista.txtcantidad3.getText();
                String loterias_id3 = rlvista.txtloteriasid3.getText();
                
                try {
                    
                    modificarReparto(Integer.parseInt(identificador3),Integer.parseInt(num_hermano_id3),Integer.parseInt(num_desde3),Integer.parseInt(num_hasta3),Integer.parseInt(cantidad3),Integer.parseInt(loterias_id3));
                    JOptionPane.showMessageDialog(null, "¡Modificado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
                    limpiarTexto();
                    
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                }
                
                
                break;
            case "SALIR1":
                rlvista.dispose();
                break;
            case "SALIR2":
                rlvista.dispose();
                break;
            case "SALIR3":
                rlvista.dispose();
                break;
            case "SALIR4":
                rlvista.dispose();
                break;
        }

    }
    
    public void ingresarLoteria(){
        String identificador = rlvista.txtIdentificador.getText();
                String num_hermano_id = rlvista.txtnumhermanoid.getText();
                String num_desde = rlvista.txtnumdesde.getText();
                String num_hasta = rlvista.txtnumhasta.getText();
                String cantidad = rlvista.txtcantidad.getText();
                String loterias_id = rlvista.txtloteriasid.getText();
                
                try {
                   
                    agregarReparto(Integer.parseInt(identificador), Integer.parseInt(num_hermano_id),Integer.parseInt(num_desde),Integer.parseInt(num_hasta),Integer.parseInt(cantidad),Integer.parseInt(loterias_id));
                    JOptionPane.showMessageDialog(null, "Insertado Correctamente");
                    limpiarTexto();
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, ex+ " ya existe, ingrese un identificador distinto", "SofCofradias", JOptionPane.ERROR_MESSAGE);
                }   
                cargarTablaReparto();
                cargarTablaReparto2();
                cargarTablaReparto3();
    }
    
    private void eliminarSeleccion() {
        String seleccion = rlvista.txtIdentificador1.getText();
        try {
            eliminarHermandadHermanadas(Integer.parseInt(seleccion));
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorRepartoLoterias.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaReparto();
        limpiarTexto2();
        rlvista.tablaEntidadConocida3.setModel(new DefaultTableModel());
        rlvista.txtFiltro2.setText("");
    }
    
    
    private void cargarCmbBD1() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE repartoloteria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            rlvista.cmbBD1.removeAllItems();
            
            while(cbd.resultado.next()){
                rlvista.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorRepartoLoterias.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void cargarCmbBD2() {        
        try{
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql="DESCRIBE repartoloteria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            rlvista.cmbBD2.removeAllItems();
            
            while(cbd.resultado.next()){
                rlvista.cmbBD2.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorRepartoLoterias.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = rlvista.tablaEntidadConocida1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        rlvista.txtIdentificador2.setText((String) rlvista.tablaEntidadConocida1.getValueAt(clic, 0));
        rlvista.txtnumhermanoid2.setText((String) rlvista.tablaEntidadConocida1.getValueAt(clic, 1));
        rlvista.txtnumdesde2.setText((String) rlvista.tablaEntidadConocida1.getValueAt(clic, 2));
        rlvista.txtnumhasta2.setText((String) rlvista.tablaEntidadConocida1.getValueAt(clic, 3));
        rlvista.txtcantidad2.setText((String) rlvista.tablaEntidadConocida1.getValueAt(clic, 4));
        rlvista.txtloteriasid2.setText((String) rlvista.tablaEntidadConocida1.getValueAt(clic, 5));
        
    }
    
    private void tablaEntidadConocida2MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = rlvista.tablaEntidadConocida2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        
        rlvista.txtIdentificador3.setText((String) rlvista.tablaEntidadConocida2.getValueAt(clic, 0));
        rlvista.txtnumhermanoid3.setText((String) rlvista.tablaEntidadConocida2.getValueAt(clic, 1));
        rlvista.txtnumdesde3.setText((String) rlvista.tablaEntidadConocida2.getValueAt(clic, 2));
        rlvista.txtnumhasta3.setText((String) rlvista.tablaEntidadConocida2.getValueAt(clic, 3));
        rlvista.txtcantidad3.setText((String) rlvista.tablaEntidadConocida2.getValueAt(clic, 4));
        rlvista.txtloteriasid3.setText((String) rlvista.tablaEntidadConocida2.getValueAt(clic, 5));
        
    }
    
    private void tablaEntidadConocida3MousePressed(java.awt.event.MouseEvent evt) {                                     
        int clic = rlvista.tablaEntidadConocida3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla
        
        rlvista.txtIdentificador1.setText((String) rlvista.tablaEntidadConocida3.getValueAt(clic, 0));
        rlvista.txtnumhermanoid1.setText((String) rlvista.tablaEntidadConocida3.getValueAt(clic, 1));
        rlvista.txtnumdesde1.setText((String) rlvista.tablaEntidadConocida3.getValueAt(clic, 2));
        rlvista.txtnumhasta1.setText((String) rlvista.tablaEntidadConocida3.getValueAt(clic, 3));
        rlvista.txtcantidad1.setText((String) rlvista.tablaEntidadConocida3.getValueAt(clic, 4));
        rlvista.txtloteriasid1.setText((String) rlvista.tablaEntidadConocida3.getValueAt(clic, 5));
    }
    
    public void cargarTablaReparto() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Num hermano id", "Num desde", "Num hasta", "Cantidad", "Loterias id"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[6];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from repartoloteria";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            
            while(cbd.resultado.next()){
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("numero_hermano_id");
                fila[2] = cbd.resultado.getString("num_desde");
                fila[3] = cbd.resultado.getString("num_hasta");
                fila[4] = cbd.resultado.getString("cantidad");
                fila[5] = cbd.resultado.getString("loterias_id");
                m.addRow(fila);
            }
            
            rlvista.tablaEntidadConocida1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            rlvista.tablaEntidadConocida1.setRowSorter(ordenar);
            rlvista.tablaEntidadConocida1.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaReparto2() {
        RepartoLoterias reparto = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Num hermano id", "Num desde", "Num hasta", "Cantidad", "Loterias id"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[6];
            ArrayList <RepartoLoterias> x;
            String campo = (String) rlvista.cmbBD1.getSelectedItem();
            String filtro = rlvista.txtFiltro1.getText();
            x = rl.buscarFiltro(filtro, campo);
            
            Iterator <RepartoLoterias> it = x.iterator();
            while(it.hasNext()){
                reparto = it.next();
                fila[0] = String.valueOf(reparto.getIdentificador());
                fila[1] = String.valueOf(reparto.getNum_hermano_id());
                fila[2] = String.valueOf(reparto.getNum_desde());
                fila[3] = String.valueOf(reparto.getNum_hasta());
                fila[4] = String.valueOf(reparto.getCantidad());
                fila[5] = String.valueOf(reparto.getLoterias_id());
                m.addRow(fila);
            }           
            
            rlvista.tablaEntidadConocida2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            rlvista.tablaEntidadConocida2.setRowSorter(ordenar);
            rlvista.tablaEntidadConocida2.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }
    
    public void cargarTablaReparto3() {
        RepartoLoterias reparto = null;
        DefaultTableModel m;
        try {
                        
            String[] titulo = {"Nro", "Num hermano id", "Num desde", "Num hasta", "Cantidad", "Loterias id"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[6];
            ArrayList <RepartoLoterias> x;
            String campo = (String) rlvista.cmbBD2.getSelectedItem();
            String filtro = rlvista.txtFiltro2.getText();
            x = rl.buscarFiltro(filtro, campo);
            
            Iterator <RepartoLoterias> it = x.iterator();
            while(it.hasNext()){
                reparto = it.next();
                fila[0] = String.valueOf(reparto.getIdentificador());
                fila[1] = String.valueOf(reparto.getNum_hermano_id());
                fila[2] = String.valueOf(reparto.getNum_desde());
                fila[3] = String.valueOf(reparto.getNum_hasta());
                fila[4] = String.valueOf(reparto.getCantidad());
                fila[5] = String.valueOf(reparto.getLoterias_id());
                
                m.addRow(fila);
            }           
            
            rlvista.tablaEntidadConocida3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            rlvista.tablaEntidadConocida3.setRowSorter(ordenar);
            rlvista.tablaEntidadConocida3.setModel(m);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);        
        }
        
    }

    /*Limpiar Texto*/
    public void limpiarTexto() {
        rlvista.txtIdentificador.setText("");
        rlvista.txtcantidad.setText("");
        rlvista.txtloteriasid.setText("");
        rlvista.txtnumdesde.setText("");
        rlvista.txtnumhermanoid.setText("");
        rlvista.txtnumhasta.setText("");
    }
    
    /*Limpiar Texto*/
    public void limpiarTexto2() {
        rlvista.txtIdentificador1.setText("");
        rlvista.txtcantidad1.setText("");
        rlvista.txtloteriasid1.setText("");
        rlvista.txtnumdesde1.setText("");
        rlvista.txtnumhermanoid1.setText("");
        rlvista.txtnumhasta1.setText("");
    }

    /* Método para buscar una Entidad Conocida indicando el campo y el valor */
    public ArrayList<RepartoLoterias> buscarReparto(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        rl = new RepartoLoterias();
        return rl.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar una Entidad Conocida*/
    
    public void agregarReparto(int identificador,int numero_hermano_id,int num_desde,int num_hasta,int cantidad,int loterias_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        rl = new RepartoLoterias(identificador,numero_hermano_id,num_desde,num_hasta,cantidad,loterias_id);
        rl.grabar();
    }

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarReparto(int identificador,int num_hermano_id,int num_desde,int num_hasta,int cantidad,int loterias_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        rl = new RepartoLoterias(identificador,num_hermano_id,num_desde,num_hasta,cantidad,loterias_id);
        rl.actualizar();
    }

    /*Metodo para borrar una Entidad Conocida*/
    public void eliminarHermandadHermanadas(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        rl = new RepartoLoterias(identificador);
        rl.borrar();
    }

    /*Metodo para leer todas las Entidades Conocidas*/
    public ArrayList<RepartoLoterias> leerTodas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        rl = new RepartoLoterias();
        return rl.leerTodos();
    }
}