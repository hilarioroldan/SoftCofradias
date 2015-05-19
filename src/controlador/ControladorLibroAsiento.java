package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermanitoVista1;
import vista.LibroDeAsientoVista;
import validaciones.*;

public class ControladorLibroAsiento implements ActionListener {

    String fexha;
    String fexha2;
    int suma2;
    int suma;
    int suma3;

    LibroDeAsientos l1;
    LibroDeAsientoVista lv1;

    JFreeChart Grafica;

    DefaultComboBoxModel modeloCombo = new DefaultComboBoxModel();
    DefaultCategoryDataset Datos = new DefaultCategoryDataset();

    public enum di {

        ACEPTAR, ACEPTAR1, ACEPTAR2, ACEPTARCAL, CANCELAR, SALIR, VERCAL, BUSCAR, INGRESAR, RETIRAR, VER, VER2, GRAFICO, COMBOTXT;
    }

    public void iniciar() {

        l1 = new LibroDeAsientos();
        lv1 = new LibroDeAsientoVista();
        //m1 = new MovimientoBancario();
        lv1.grafic.setEnabled(false);

        lv1.setVisible(true);
        lv1.setLocationRelativeTo(null);
        lv1.cant.setEnabled(false);
        lv1.retiro01.setEnabled(false);
        cargar_tabla_Bancaria();
        lv1.id_bancario.setEnabled(true);
        
        // validaciones
        soloNumerosSoloLetras x = new soloNumerosSoloLetras();
        x.SLetras(lv1.concept);
        x.SNumeros(lv1.cant);
        x.SNumeros(lv1.retiro01);

        lv1.acept.setActionCommand("ACEPTAR");
        lv1.acept.addActionListener(this);
        lv1.cancel.setActionCommand("SALIR");
        lv1.cancel.addActionListener(this);
        lv1.ingreso.setActionCommand("INGRESAR");
        lv1.ingreso.addActionListener(this);
        lv1.retiro.setActionCommand("RETIRAR");
        lv1.retiro.addActionListener(this);

        lv1.ver2.setActionCommand("VER2");
        lv1.ver2.addActionListener(this);
        lv1.aceptarIng.setActionCommand("ACEPTAR1");
        lv1.aceptarIng.addActionListener(this);
        lv1.aceptarIng1.setActionCommand("ACEPTAR2");
        lv1.aceptarIng1.addActionListener(this);
        lv1.retiro.setActionCommand("RETIRAR");
        lv1.retiro.addActionListener(this);
        lv1.ver2.setActionCommand("VER2");
        lv1.ver2.addActionListener(this);
        lv1.grafic.setActionCommand("GRAFICO");
        lv1.grafic.addActionListener(this);
        lv1.aceptarCal.setActionCommand("ACEPTARCAL");
        lv1.aceptarCal.addActionListener(this);
        lv1.calcu.setActionCommand("VERCAL");
        lv1.calcu.addActionListener(this);
        lv1.uno.setActionCommand(lv1.calculadoratxt.getText());
        lv1.uno.addActionListener(this);
        cargarCmbBD1();

        lv1.seleccion_id.setActionCommand("COMBOTXT");
        lv1.seleccion_id.addActionListener(this);

        SeleccionNumeroCuenta();

        lv1.tabla_movimiento.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (ControladorLibroAsiento.di.valueOf(e.getActionCommand())) {

            case ACEPTAR1:
                int cog1 = Integer.parseInt(lv1.textIngreso.getText());
                lv1.cant.setText(cog1 + "");
                lv1.ingresar.dispose();
                break;

            case ACEPTAR2:
                int cog2 = Integer.parseInt(lv1.textIngreso1.getText());
                lv1.retiro01.setText(cog2 + "");
                lv1.retirar.dispose();
                break;

            case ACEPTAR:

                insertar();
                lv1.dispose();
                break;

            case COMBOTXT:

                SeleccionNumeroCuenta();
                lv1.id_bancario.getText();

                break;

            case ACEPTARCAL:

                int cog3 = Integer.parseInt(lv1.calculadoratxt.getText());
                lv1.textIngreso.setText(cog3 + "");
                lv1.teclado.dispose();
                break;

            case SALIR:
                lv1.dispose();
                break;

            case INGRESAR:
                lv1.ingresar.setEnabled(true);
                lv1.ingresar.setVisible(true);
                lv1.ingresar.setSize(400, 200);

                break;

            case RETIRAR:
                lv1.retirar.setEnabled(true);
                lv1.retirar.setVisible(true);
                lv1.retirar.setSize(400, 200);
                break;

            case VER2:
                lv1.movimiento.setEnabled(true);
                lv1.movimiento.setVisible(true);
                lv1.movimiento.setSize(590, 400);

                break;
            case GRAFICO:
                grafica_ingreso();
                grafica_retiro();
                grafica_Saldo_total();
                inicializacionGrafica();

                lv1.grafico.dispose();

                button();
                break;
            case VERCAL:
                lv1.teclado.setEnabled(true);
                lv1.teclado.setVisible(true);
                lv1.teclado.setSize(380, 260);

                break;

        }

    }

    private void cargarCmbBD1() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select num_cuenta from cuentabancaria;";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {

                lv1.seleccion_id.addItem(cbd.resultado.getObject("num_cuenta"));

            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void SeleccionNumeroCuenta() {
        try {

            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select identificador from cuentabancaria where num_cuenta=" + lv1.seleccion_id.getSelectedItem() + "";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            if (cbd.resultado.next()) {

                lv1.id_bancario.setText(cbd.resultado.getString("identificador"));

            } else {

                JOptionPane.showMessageDialog(null, "ESTA VACIO");

            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {

            Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);

        }

    }
    //metodo para insertar

    public void insertar() {

        try {

            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select max(identificador) from librodeasientos";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            int identificador = 0; //= Integer.parseInt(lv1.ident.getText());

            if (cbd.resultado.next()) {

                identificador = cbd.resultado.getInt(1) + 1;

            } else {

                identificador = 1;

            }

            String concepto = lv1.concept.getText();
            String fecha = null;

            if (lv1.jDateChooser1.getDate() != null) {
                // Trabajando con fecha                
                int año = lv1.jDateChooser1.getCalendar().get(Calendar.YEAR);
                int mes = lv1.jDateChooser1.getCalendar().get(Calendar.MONTH);
                int dia = lv1.jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                fecha = año + "-" + mes + "-" + dia;
                //
            }

            /* if(mes1.equals("enero")){
             int uno=01;
             fecha =año+"-"+uno+"-"+dia;
                        
             }else{
                        
             if(mes1.equals("febrero")){
             int uno=02;
             fecha =año+"-"+uno+"-"+dia;
                            
             }else{
             if(mes1.equals("marzo")){
             int uno=03;
             fecha =año+"-"+uno+"-"+dia;
                                
             }else{
                                
             if(mes1.equals("abril")){
             int uno=04;
             fecha =año+"-"+uno+"-"+dia;
                                    
             }else{
             if(mes1.equals("mayo")){
             int uno=05;
             fecha =año+"-"+uno+"-"+dia; ;
                                        
             }else{
                                        
             if(mes1.equals("junio")){
             int uno=06;
             fecha =año+"-"+uno+"-"+dia;
                                            
             }else{
             if(mes1.equals("julio")){
             int uno=07;
             fecha =año+"-"+uno+"-"+dia;
                                                
             }else{
                                                
             if(mes1.equals("agosto")){
             int uno=8;
             fecha =año+"-"+uno+"-"+dia;
                                                    
             }else{
             if(mes1.equals("septiembre")){
             int uno=9;
             fecha =año+"-"+uno+"-"+dia;
                                                        
             }else{
                                                        
             if(mes1.equals("octubre")){
             int uno=10;
             fecha =año+"-"+uno+"-"+dia;
                                                            
             }else{
             if(mes1.equals("noviembre")){
             int uno=11;
             fecha =año+"-"+uno+"-"+dia;
                                                                
             }else{
             if(mes1.equals("diciembre")){
             int uno=12;
             fecha =año+"-"+uno+"-"+dia;
                                                                    
             }
             }
             }
             }
             }
             }
                                            
             }
                                        
             }
                                    
             }
                                
             }
                            
             }
                        
             }*/
                    //para ingresar
            int ingresar = Integer.parseInt(lv1.cant.getText());
            //int ingreso=Integer.parseInt(lv1.cant.getText());
            int deber = Integer.parseInt(lv1.retiro01.getText());
                    //int retiro=Integer.parseInt(lv1.retiro01.getText());

            int mayordomia_id = 1;
            int cuenta_bancaria_id = Integer.parseInt(lv1.id_bancario.getText());
            int id_bancario = Integer.parseInt(lv1.id_bancario.getText());

            try {
                agregarDatos(identificador, fecha, concepto, ingresar, deber, mayordomia_id, cuenta_bancaria_id);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "No se Ha introducido ningun dato");
                Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);

            } catch (InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(HermanitoVista1.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    //metodo para agregar a la base de datos
    public void agregarDatos(int identificador, String fecha, String concepto, int ingresar, int deber, int mayordomia_id, int cuenta_bancaria_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        l1 = new LibroDeAsientos(identificador, fecha, concepto, ingresar, deber, mayordomia_id, cuenta_bancaria_id);
        l1.insertar();

    }

    public void button() {

        ChartPanel Panel = new ChartPanel(Grafica);
        JFrame Ventana = new JFrame("Movimiento de Saldo");
        Ventana.getContentPane().add(Panel);
        Ventana.pack();
        Ventana.setVisible(true);
        Ventana.setSize(1200, 600);
        Ventana.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

    }

    //seleccionamos en la tabla de movimientos bancarios
    public void seleccionTabla() {

        int clic = lv1.tabla_movimiento.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            lv1.grafic.setEnabled(true);
            lv1.año_busqueda.setEnabled(false);
            lv1.año_busqueda.setText(lv1.tabla_movimiento.getValueAt(clic, 0).toString());
           //  lv1.cantida.setText(lv1.tabla_movimiento.getValueAt(clic, 3).toString());
            // lv1.retirado.setText(lv1.tabla_movimiento.getValueAt(clic, 4).toString());

        }
    }

    //copn esto pretendemos cargar nuestra tabla de movimientos bancarios con el fin de obtener nuestra grafica
    public void cargar_tabla_Bancaria() {

        DefaultTableModel ff;
        int clic = lv1.tabla_movimiento.getSelectedRow();

        try {
            String titulo[] = {"identificador", "fecha", "concepto", "ingresos", "retiros", "id bancario"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[7];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from librodeasientos";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {

                //fila[0] = cdb.resultado.getString(0);          
                fila[0] = cdb.resultado.getString(1);
                fexha = cdb.resultado.getString("fecha");
                //seleccionamos solo el año , que es lo que nos interesa de la fecha
                fexha = fexha.substring(0, 4);//+"-"+fexha.substring(5,7)+"-"+fexha.substring(8,10);
                fila[1] = fexha;

                fila[2] = cdb.resultado.getString(3);
                fila[3] = cdb.resultado.getString(4);
                fila[4] = cdb.resultado.getString(5);
                fila[5] = cdb.resultado.getString(7);

                ff.addRow(fila);

            }

            lv1.tabla_movimiento.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            lv1.tabla_movimiento.setRowSorter(ordenar);
            lv1.tabla_movimiento.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {
        int clic = lv1.tabla_movimiento.getSelectedRow(); // se guarda en la variable el  de la fila cuando se hace click en una

        if (clic != -1) {
            lv1.grafic.setEnabled(true);
            lv1.año_busqueda.setText(lv1.tabla_movimiento.getValueAt(clic, 1).toString());
            //lv1.cantida.setText(lv1.tabla_movimiento.getValueAt(clic, 3).toString());
            //lv1.retirado.setText(lv1.tabla_movimiento.getValueAt(clic, 4).toString());
            lv1.id_banco_seleccionado.setText(lv1.tabla_movimiento.getValueAt(clic, 5).toString());

            //añadimos el año que deseamos mostrar en la grafica    
            Grafica = ChartFactory.createBarChart("Movimientos Bancarios",
                    "Año " + lv1.año_busqueda.getText(), "Dinero", Datos,
                    PlotOrientation.VERTICAL, true, true, false);

        }
    }

    //este metodo nos mnuestra y calcula la cantidad de dinero ingresado en nuestra cuenta en año especifico

    public void grafica_ingreso() {

        DefaultTableModel ff = null;

        try {
            String titulo[] = {"identificador", "fecha", "concepto", "ingresos", "retiros", "id bancario"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            int fila[] = new int[7];
            String fe[] = new String[7];

            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();

            cdb.un_sql = "select sum(ingresar) as total from librodeasientos where year(fecha)=" + lv1.año_busqueda.getText() + " and cuenta_bancaria_id=" + lv1.id_banco_seleccionado.getText() + "";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {

                suma = cdb.resultado.getInt("total");

                JOptionPane.showMessageDialog(null, suma);

            }
            //  JOptionPane.showMessageDialog(null, "cantidad retirada "+suma2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
     //******************************************************************************************//
    //este metodo nos suma y muestra la cantidad total de dinero extraido en nuestra cuenta de iun año especifico

    public void grafica_retiro() {

        DefaultTableModel ff = null;

        try {
            String titulo[] = {"identificador", "fecha", "concepto", "ingresos", "retiros", "id bancario"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            int fila[] = new int[7];
            String fe[] = new String[7];

            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();

            cdb.un_sql = "select sum(debe) as total2 from librodeasientos where year(fecha)=" + lv1.año_busqueda.getText() + " and cuenta_bancaria_id=" + lv1.id_banco_seleccionado.getText() + "";//" and id_bancario="+lv1.id_banco_seleccionado.getText()+"" ;
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {

                suma2 = cdb.resultado.getInt("total2");

                JOptionPane.showMessageDialog(null, suma2);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //*****************************************************************************************//

    public void grafica_Saldo_total() {

        DefaultTableModel ff = null;

        try {
            String titulo[] = {"identificador", "fecha", "concepto", "ingresos", "retiros", "id bancario"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            int fila[] = new int[7];
            String fe[] = new String[7];

            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();

            cdb.un_sql = "select cantidad from cuentabancaria where identificador=" + lv1.id_banco_seleccionado.getText() + " ";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {

                suma3 = cdb.resultado.getInt("cantidad");
           //  JOptionPane.showMessageDialog(null, suma3);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //este metodo inicia los valores que hemos pasado a la grafica  
    public void inicializacionGrafica() {

        int ingreso = suma;
        int retiro = suma2;
        int total = suma3;

        Datos.addValue(ingreso, "Ingresos Totales", "Ingresado");
        Datos.addValue(retiro, "Retiros Totales", "Retirado");
        Datos.addValue(total, "Saldo Disponible", "Saldo");

        Grafica = ChartFactory.createBarChart("Movimientos Bancarios",
                "Año de Movimiento " + lv1.año_busqueda.getText() + " - Identidad Bancaria Perteneciente: " + lv1.id_banco_seleccionado.getText(), "Saldo Bancario", Datos,
                PlotOrientation.VERTICAL, true, true, false);

    }

}
