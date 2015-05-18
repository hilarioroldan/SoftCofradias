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

import modelo.*;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermanitoVista1;
import vista.JuntaDeGobiernoVista;
import validaciones.*;

public class ControladorJuntaGobierno implements ActionListener {

    JuntaGobierno s;
    CargosJunta c;
    JuntaDeGobiernoVista j1;

    public enum di {

        ACEPTAR, ACEPTAR1, SALIR, SALIR1, MODIFICAR, ELIMINAR, BUSCAR, GUARDAR2,AÑADIR,ACEPTAR_CARGO_SALIR,ELIMINAR_CARGO,ELIMINALO,MODIFI,GESTION,INSERTAR_CARGO;
    }

    public void iniciar() {
        s = new JuntaGobierno();
        c = new CargosJunta();
        j1 = new JuntaDeGobiernoVista();
        j1.setVisible(true);
        j1.setLocationRelativeTo(null);
        cargarTablaMiembros();
        cargarTablaMiembros2();
        cargarTablaJuntas2();
        
        cargarCMB1();
        cargarCMB2();
        // validaciones
        soloNumerosSoloLetras n = new soloNumerosSoloLetras();
        n.SLetras(j1.nomb);
        n.SLetras(j1.apel);
        n.SNumeros(j1.nherm);
        

        //se añade las acciones a los controles del formulario padre
        j1.aceptar.setActionCommand("ACEPTAR");
        j1.aceptar.addActionListener(this);
        //j1.aceptar1.setActionCommand("ACEPTAR1");
        //j1.aceptar1.addActionListener(this);

        j1.salir.setActionCommand("SALIR");
        j1.salir.addActionListener(this);
        //j1.salir1.setActionCommand("SALIR");
        //j1.salir1.addActionListener(this);

        j1.modificar.setActionCommand("MODIFICAR");
        j1.modificar.addActionListener(this);

        j1.eliminar.setActionCommand("ELIMINAR");
        j1.eliminar.addActionListener(this);

        j1.salir2.setActionCommand("SALIR1");
        j1.salir2.addActionListener(this);

        j1.tess.setActionCommand("BUSCAR");
        j1.tess.addActionListener(this);
        
        j1.gestionar.setActionCommand("GESTION");
        j1.gestionar.addActionListener(this);
        j1.añadir_cargo.setActionCommand("AÑADIR");
        j1.añadir_cargo.addActionListener(this);
        
        j1.insertar_cargo.setActionCommand("INSERTAR_CARGO");
        j1.insertar_cargo.addActionListener(this);
        
        j1.Eliminar_cargo.setActionCommand("ELIMINAR_CARGO");
        j1.Eliminar_cargo.addActionListener(this);
        
        j1.Eliminalo.setActionCommand("ELIMINALO");
        j1.Eliminalo.addActionListener(this);
        
        

        j1.tablagobierno.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla hermandad           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                //  jTable1MousePressed(evt);
            }
        });

    }

    public void actionPerformed(ActionEvent e) {
        switch (ControladorJuntaGobierno.di.valueOf(e.getActionCommand())) {

            case ACEPTAR:
                agregarJuntaGobierno();
                j1.dispose();
                break;
            case SALIR:
                j1.dispose();
                break;

            case ELIMINAR:
                eliminarSeleccion();
                cargarTablaMiembros();
                break;

            case MODIFICAR:

                //j1.jDialog1.setSize(900, 550);
                //j1.jDialog1.setLocation(100, 100);
                //j1.jDialog1.setVisible(true);
                int clic = j1.tablagobierno.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

                if (clic != -1) {
                 
                    //j1.nherm1.setEnabled(false);
                    //j1.nomb1.setText(j1.tablagobierno.getValueAt(clic, 1).toString());
                    //j1.apel1.setText(j1.tablagobierno.getValueAt(clic, 2).toString());
                    //j1.obs1.setText(j1.tablagobierno.getValueAt(clic, 4).toString());
                    //j1.nherm1.setText(j1.tablagobierno.getValueAt(clic, 5).toString());
                    //j1.seleccion.setSelectedItem(j1.tablagobierno.getValueAt(clic, 3));

                }
                cargarTablaMiembros();
                break;
            case BUSCAR:
                cargarTablaMiembros2();

                break;
            case ACEPTAR1:
                modificar();
                j1.dispose();
                break;

            case GESTION:
                
                j1.gestion_cargos.setVisible(true);
                j1.gestion_cargos.setLocationRelativeTo(null);
                j1.gestion_cargos.setSize(450, 250);
                
                break;
                
            case AÑADIR:
                
                j1.añadirNuevoCargo.setVisible(true);
                j1.añadirNuevoCargo.setLocationRelativeTo(null);
                j1.añadirNuevoCargo.setSize(450, 220);
                
                break;
                
            case INSERTAR_CARGO:
                
                
                Conexion cdb;
        try {
            
            cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql="select max(identificador) from cargosjunta";
                cdb.resultado=cdb.un_st.executeQuery(cdb.un_sql);
                
                int identificador=0;
                
                if(cdb.resultado.next()){
                
                identificador=cdb.resultado.getInt(1)+1;
                
                
                }else {
                
                
                identificador=1;
                }
                
                
                
                String cargo=j1.cargonuevo.getText();
                
                AgregarCargo(identificador, cargo);
                
                
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        }
                
                
                
                
                j1.añadirNuevoCargo.dispose();
                
                break;
                
            case ELIMINAR_CARGO:
                
                j1.eliminacion.setVisible(true);
                 j1.eliminacion.setLocationRelativeTo(null);
                j1.eliminacion.setSize(450, 220);
                
                break;
                
            case ELIMINALO:
                EliminarCargo();
                break;
                
                
        }

    }

    public void agregarJuntaGobierno() {
        
        try {
            
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            
            int identificador = Integer.parseInt(j1.nherm.getText());
            String nombre = j1.nomb.getText();
            String apellido = j1.apel.getText();
            String observaciones = j1.obs.getText();
            int numero_hermano_id = Integer.parseInt(j1.nherm.getText());
            int cargo = 1;
            cbd.resultado = cbd.un_st.executeQuery("select identificador from cargosjunta where descripcion='"+j1.seleccion.getSelectedItem()+"';");
            if (cbd.resultado.next()) {
               cargo = cbd.resultado.getInt(1);
            }
            try {
                agregarJuntaGobierno(identificador, nombre, apellido, cargo, observaciones, numero_hermano_id);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //metodo para eliminar un cargo
    
     public void EliminarCargo() {
        
        try {
            
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            
        
            int cargo = 1;
            cbd.resultado = cbd.un_st.executeQuery("select identificador from cargosjunta where descripcion='"+j1.cargo_seleccion_eliminacion.getSelectedItem()+"';");
            if (cbd.resultado.next()) {
               cargo = cbd.resultado.getInt(1);
            }
            
            EliminarCargo(cargo);
            
            
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

            //metodo para eliminar a un miembro de la junta de gobierno
    public void cargarTablaMiembros() {
        DefaultTableModel jj;

        try {
            String titulo[] = {"identificador", "nombre", "apellido", "cargo", "observaciones", "numero de hermano"};
            jj = new DefaultTableModel(null, titulo);
            JTable p = new JTable(jj);
            String fila[] = new String[6];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from juntagobierno";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                fila[2] = cdb.resultado.getString(3);
                fila[3] = cdb.resultado.getString(4);
                fila[4] = cdb.resultado.getString(5);
                fila[5] = cdb.resultado.getString(6);

                jj.addRow(fila);
            }

            j1.tablagobierno.setModel(jj);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(jj);
            j1.tablagobierno.setRowSorter(ordenar);
            j1.tablagobierno.setModel(jj);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarTablaMiembros2() {
        DefaultTableModel rr;

        try {
            String titulo[] = {"identificador", "nombre", "apellido", "cargo", "observaciones", "numero de hermano"};
            rr = new DefaultTableModel(null, titulo);
            JTable p = new JTable(rr);
            String fila[] = new String[6];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from juntagobierno";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                fila[2] = cdb.resultado.getString(3);
                fila[3] = cdb.resultado.getString(4);
                fila[4] = cdb.resultado.getString(5);
                fila[5] = cdb.resultado.getString(6);

                rr.addRow(fila);
            }

            j1.tablagobierno1.setModel(rr);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(rr);
            j1.tablagobierno1.setRowSorter(ordenar);
            j1.tablagobierno1.setModel(rr);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    /*private void jTable1MousePressed(java.awt.event.MouseEvent evt) {
     int clic = hv1.tablaHermano.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

     if (clic!=-1) {
     hv1.busquedah.setText(hv1.tablaHermano.getValueAt(clic, 1).toString());
     hv1.busquedaA.setText(hv1.tablaHermano.getValueAt(clic, 2).toString());
     hv1.herm.setText(hv1.tablaHermano.getValueAt(clic, 0).toString());
           
     }
     }*/

    public void cargarTablaJuntas2() {
        JuntaGobierno junta;

        DefaultTableModel ff;
        try {
            String titulo[] = {"identificador", "nombre", "apellido", "cargo", "observaciones", "numero_hermano"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String[] fila = new String[6];
            ArrayList<JuntaGobierno> x;
            String campo = (String) j1.cmbBD1.getSelectedItem();
            String filtro = j1.txtFiltro1.getText();
            x = s.buscarFiltro(filtro, campo);

            Iterator<JuntaGobierno> it = x.iterator();
            while (it.hasNext()) {
                junta = it.next();
                fila[0] = String.valueOf(junta.getIdentificador());
                fila[1] = junta.getNombre();
                fila[2] = junta.getApellido();
                fila[3] = String.valueOf(junta.getCargo());
                fila[4] = junta.getObservaciones();
                fila[5] = String.valueOf(junta.getNumero_hermano_id());

                ff.addRow(fila);
            }

            j1.tablagobierno1.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            j1.tablagobierno1.setRowSorter(ordenar);
            j1.tablagobierno1.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
e.printStackTrace();
//JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void eliminarSeleccion() {
        int clic = j1.tablagobierno.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        String identificador = j1.tablagobierno.getValueAt(clic, 5).toString();
        try {
            eliminarMiembro(Integer.parseInt(identificador));
            JOptionPane.showMessageDialog(null, "¡Eliminado Correctamente!", "SoftCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermano.class.getName()).log(Level.SEVERE, null, ex);
        }
        cargarTablaMiembros();

        j1.tablagobierno.setModel(new DefaultTableModel());

    }

    private void cargarCmbBD1() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "DESCRIBE juntagobierno;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            j1.cmbBD1.removeAllItems();

            while (cbd.resultado.next()) {
                j1.cmbBD1.addItem(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void modificar() {
        int identificador1 = 1;
        //String nombre1 = j1.nomb1.getText();
        //String apellido1 = j1.apel1.getText();
        //String observaciones1 = j1.obs1.getText();
        //int numero_hermano_id1 = Integer.parseInt(j1.nherm1.getText());
        //String cargo1 = String.valueOf(j1.seleccion.getSelectedItem());
        int cargo1 = 1;

       // try {
          //  modificarJuntaGobierno(identificador1, nombre1, apellido1, cargo1, observaciones1, numero_hermano_id1);
       // } catch (IllegalAccessException | SQLException ex) {
           // Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
       // } catch (ClassNotFoundException | InstantiationException ex) {
           // Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
       // }
        cargarTablaMiembros();
    }

    public void eliminarMiembro(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        JuntaGobierno j1;
        j1 = new JuntaGobierno(identificador);
        j1.borrar();
    }

    public void agregarJuntaGobierno(int identificador, String nombre, String apellido, int cargo, String observaciones, int numero_hermano_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        s = new JuntaGobierno(identificador, nombre, apellido, cargo, observaciones, numero_hermano_id);
        s.grabar();

    }

    public void modificarJuntaGobierno(int identificador, String nombre, String apellido, int cargo, String observaciones, int numero_hermano_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        s = new JuntaGobierno(identificador, nombre, apellido, cargo, observaciones, numero_hermano_id);
        s.actualizar();
    }

    public ArrayList<JuntaGobierno> buscarJunta(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        s = new JuntaGobierno();
        return s.buscarFiltro(filtro, campo);
    }
    
    public void cargarCMB1() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select descripcion from cargosjunta");
            j1.seleccion.removeAllItems();
            
            while (cbd.resultado.next()) {
                j1.seleccion.addItem(cbd.resultado.getString(1));
            }
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
       public void cargarCMB2() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select descripcion from cargosjunta");
            j1.cargo_seleccion_eliminacion.removeAllItems();
            
            while (cbd.resultado.next()) {
                j1.cargo_seleccion_eliminacion.addItem(cbd.resultado.getString(1));
            }
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void AgregarCargo(int identificador,String descripcion){
        
        try {
            c = new CargosJunta(identificador,descripcion);
            c.grabar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
 
    public void EliminarCargo(int identificador){
    
      c = new CargosJunta(identificador);
        try {
            c.borrar();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorJuntaGobierno.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
}
