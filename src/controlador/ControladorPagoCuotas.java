package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
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
import modelo.CartonCuotas;
import modelo.hermanito;
import modelo.PagoCuotas;
import servicios.ConectarServicio;
import servicios.Conexion;
import validaciones.soloNumerosSoloLetras;
import vista.CartonVista;
import vista.PagoCuotasVista;

public class ControladorPagoCuotas implements ActionListener {

    hermanito h1 = new hermanito();
    PagoCuotas p;
    PagoCuotasVista p1;
    CartonCuotas cc;
    CartonVista cv;
    int contador = 0;

    int identificadorCartonSeleccionado = 0;

    public enum di {

        ACEPTAR, SALIR, MODIFICAR,
        GESTION, VER, SALIR1, BUSCAR,
        CONSULTARCARTON, ACCEDERCARTON, ACCIONADOCUADROAÑOCARTON,
        BTNAÑADIRCARTON, BTNAGREGARNUEVOCARTON, BTNBUSCARCARTON,
        BTNBUSCARAÑOCARTON, BTNELIMINARCARTON, BTNELIMINARCARTON2,
        ELIMINARCARTON, MODIFICARCUOTACARTON, BTNSALIRCARTONCUOTA, btnSalirOpcionesConsultaCarton,
        SALIR2, SALIRNUEVOCARTON, SALIRBUSCARAÑO, SALIRAÑO;
    }

    public void iniciar() {
        p = new PagoCuotas();
        p1 = new PagoCuotasVista();
        cv = new CartonVista();
        cc = new CartonCuotas();

        p1.setVisible(true);
        p1.setLocationRelativeTo(null);
        cargarTablaHermanos();

        //validaciones
        // VALIDACIONES //   
        soloNumerosSoloLetras x = new soloNumerosSoloLetras();
        x.SNumeros(p1.pric);
        x.SNumeros(p1.jTextField2);
        x.SNumeros(p1.txtAñoCuota);
        x.SNumeros(p1.txtAñoCuota2);

        //se añade las acciones a los controles del formulario padre
        cv.btnModificarCuotaMes.setActionCommand("MODIFICARCUOTACARTON");
        cv.btnModificarCuotaMes.addActionListener(this);
        p1.btnEliminarCartonFinal.setActionCommand("ELIMINARCARTON");
        p1.btnEliminarCartonFinal.addActionListener(this);
        p1.btnBuscarCuotasEliminar.setActionCommand("BTNELIMINARCARTON2");
        p1.btnBuscarCuotasEliminar.addActionListener(this);
        p1.btnEliminarCarton.setActionCommand("BTNELIMINARCARTON");
        p1.btnEliminarCarton.addActionListener(this);
        p1.btnBuscarAñoCarton.setActionCommand("BTNBUSCARAÑOCARTON");
        p1.btnBuscarAñoCarton.addActionListener(this);
        p1.btnBuscarCarton.addActionListener(this);
        p1.btnBuscarCarton.setActionCommand("BTNBUSCARCARTON");
        p1.btnAgregarNuevoCarton.setActionCommand("BTNAGREGARNUEVOCARTON");
        p1.btnAgregarNuevoCarton.addActionListener(this);
        p1.btnAñadirCarton.setActionCommand("BTNAÑADIRCARTON");
        p1.btnAñadirCarton.addActionListener(this);
        cv.cuadroAñoCarton.setActionCommand("ACCIONADOCUADROAÑOCARTON");
        cv.cuadroAñoCarton.addActionListener(this);
        p1.btnAccederCarton.setActionCommand("ACCEDERCARTON");
        p1.btnAccederCarton.addActionListener(this);
        p1.aceptar.setActionCommand("ACEPTAR");
        p1.aceptar.addActionListener(this);
        p1.salir.setActionCommand("SALIR");
        p1.salir.addActionListener(this);
        p1.gesti.setActionCommand("GESTION");
        p1.gesti.addActionListener(this);
        p1.pagos.setActionCommand("VER");
        p1.pagos.addActionListener(this);
        p1.salir1.setActionCommand("SALIR1");
        p1.salir1.addActionListener(this);
        p1.busqueda.setActionCommand("BUSCAR");
        p1.busqueda.addActionListener(this);
        p1.btnConsultarCarton.setActionCommand("CONSULTARCARTON");
        p1.btnConsultarCarton.addActionListener(this);
        cv.btnSalirCartonCuota.setActionCommand("BTNSALIRCARTONCUOTA");
        cv.btnSalirCartonCuota.addActionListener(this);
        p1.btnSalirOpcionesConsultaCarton.setActionCommand("btnSalirOpcionesConsultaCarton");
        p1.btnSalirOpcionesConsultaCarton.addActionListener(this);
        p1.saliropciones.setActionCommand("SALIR2");
        p1.saliropciones.addActionListener(this);
        p1.salirnuevocarton.setActionCommand("SALIRNUEVOCARTON");
        p1.salirnuevocarton.addActionListener(this);
        p1.salirbuscaraño.setActionCommand("SALIRBUSCARAÑO");
        p1.salirbuscaraño.addActionListener(this);
        p1.salireliminaraño.setActionCommand("SALIRAÑO");
        p1.salireliminaraño.addActionListener(this);
        

        p1.tblCuota2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaEntidadConocida1MousePressed(evt);
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (ControladorPagoCuotas.di.valueOf(e.getActionCommand())) {

            case ACEPTAR:

                aceptarPagodeCuota();

                break;
                
            case SALIR2:
                p1.cuotas.dispose();
                break;
                
            case SALIRAÑO:
                p1.eliminarAño.dispose();
                p1.consultarcarton.setVisible(true);
                break;
                
            case SALIRNUEVOCARTON:
                p1.añadirNuevoAño.dispose();
                p1.consultarcarton.setVisible(true);
                break;
                
            case SALIRBUSCARAÑO:
                p1.buscarAño.dispose();
                p1.consultarcarton.setVisible(true);
                break;

            case SALIR:
                p1.dispose();
                break;

            case SALIR1:
                p1.verpagos.dispose();
                break;

            case GESTION:

                int comprobacion = p1.tablaHermano.getSelectedRow(); // si hemos seleccionado una fila de la tabla
                // entonces..
                if (comprobacion != -1) {

                    p1.cuotas.setSize(350, 380);
                    p1.cuotas.setVisible(true);
                    p1.cuotas.setLocationRelativeTo(null);

                    p1.her.setEnabled(false);
                    p1.her.setText(p1.tablaHermano.getValueAt(comprobacion, 0).toString());
                    //JOptionPane.showMessageDialog(null,p1.her.getText() );

                    cargarPagos();

                } else {
                    JOptionPane.showMessageDialog(null, "Debes de seleccionar una fila de la tabla");
                }
                break;

            case VER:

                p1.verpagos.setSize(450, 500);
                p1.verpagos.setVisible(true);
                p1.verpagos.setLocationRelativeTo(null);

                break;

            case BUSCAR:
                cargarTablaHermanos2();
                break;

            case CONSULTARCARTON:
                consultarCarton();
                break;

            case ACCEDERCARTON:
                accederCarton();
                break;

            case ACCIONADOCUADROAÑOCARTON:
                localizarCartonPorEntradaTeclado();
                break;

            case BTNAÑADIRCARTON:
                añadirNuevoCarton();
                break;

            case BTNAGREGARNUEVOCARTON:
                btnAgregarNuevoCarton();
                break;

            case BTNBUSCARCARTON:
                buscarCarton();
                break;

            case BTNBUSCARAÑOCARTON:
                buscarAñoCarton();
                break;

            case BTNELIMINARCARTON:
                eliminarCarton();
                break;

            case BTNELIMINARCARTON2:
                cargarTablaCuotasFiltro2();
                break;

            case ELIMINARCARTON:
                eliminarCartonFinalx();
                cargarTablaCuotas2();
                break;

            case MODIFICARCUOTACARTON:
                modificarCartonCuota();
                break;

            case BTNSALIRCARTONCUOTA:
                cv.dispose();
                p1.consultarcarton.setVisible(true);
                break;
                
            case btnSalirOpcionesConsultaCarton:
                p1.consultarcarton.dispose();
                break;
        }
    }

    public void modificarCartonCuota() {

        try {
            modificarCuotadeCarton();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorPagoCuotas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void aceptarPagodeCuota() {
        if (p1.jDateChooser1.getDate()!=null && !p1.pric.getText().equalsIgnoreCase("")) {
        
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select max(identificador) from pagocuotas");
            int identificador = 1;

            if (cbd.resultado.next()) {
                identificador = cbd.resultado.getInt(1) + 1;
            } else {
                identificador = 1;
            }

            int numero_hermano_id = Integer.parseInt(p1.her.getText());

            String date = "";
            if (p1.jDateChooser1.getDate() != null) {
                // Trabajando con fecha
                int año = p1.jDateChooser1.getCalendar().get(Calendar.YEAR);
                int mes = p1.jDateChooser1.getCalendar().get(Calendar.MONTH);
                int dia = p1.jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                date = año + "-" + mes + "-" + dia;
                //
            }

            int precio = Integer.parseInt(p1.pric.getText());
            int mayordomia_id = 1;
            String pagado = String.valueOf(p1.seleccio.getSelectedItem());

            try {
                agregarPago(identificador, numero_hermano_id, date, mayordomia_id, precio, pagado);
                JOptionPane.showMessageDialog(null, "Pago realizado correctamente");
                cargarPagos();
               // p1.cuotas.dispose();
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                Logger.getLogger(PagoCuotasVista.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPagoCuotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        } else {
            JOptionPane.showMessageDialog(null, "Asegúrese de haber rellenado todos los campos obligatorios");
        }
    }

    public void modificarCuotadeCarton() throws SQLException {
        Conexion cbd = null;
        try {
            cbd = ConectarServicio.getInstancia().getConexionDb();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ControladorPagoCuotas.class.getName()).log(Level.SEVERE, null, ex);
        }

        String fechax = cv.cuadroAñoCarton.getText();

        cbd.un_sql = "SELECT * FROM cartoncuotas WHERE identificador=" + identificadorCartonSeleccionado + " and año=" + fechax + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

        if (cbd.resultado != null) {
            cbd.un_sql = "UPDATE cartoncuotas SET enero= ?" + ", febrero = ?" + ", marzo = ?" + ", abril = ?" + ", mayo = ?" + ", junio = ?" + ", julio = ?" + ", agosto = ?" + ", septiembre = ?" + ", octubre = ?" + ", noviembre = ?" + ", diciembre = ?" + " WHERE identificador=" + identificadorCartonSeleccionado + ";";

            //end declaracion bool
            boolean enerox = false;
            boolean febrerox = false;
            boolean marzox = false;
            boolean abrilx = false;
            boolean mayox = false;
            boolean juniox = false;
            boolean juliox = false;
            boolean agostox = false;
            boolean septiembrex = false;
            boolean octubrex = false;
            boolean noviembrex = false;
            boolean diciembrex = false;
            //fin

            if (cv.jCheckBox1.isSelected() == true) {
                enerox = true;
            } else {
                enerox = false;
            }

            if (cv.jCheckBox2.isSelected() == true) {
                febrerox = true;
            } else {
                febrerox = false;
            }

            if (cv.jCheckBox3.isSelected() == true) {
                marzox = true;
            } else {
                marzox = false;
            }

            if (cv.jCheckBox4.isSelected() == true) {
                abrilx = true;
            } else {
                abrilx = false;
            }

            if (cv.jCheckBox5.isSelected() == true) {
                mayox = true;
            } else {
                mayox = false;
            }

            if (cv.jCheckBox6.isSelected() == true) {
                juniox = true;
            } else {
                juniox = false;
            }

            if (cv.jCheckBox7.isSelected() == true) {
                juliox = true;
            } else {
                juliox = false;
            }

            if (cv.jCheckBox8.isSelected() == true) {
                agostox = true;
            } else {
                agostox = false;
            }

            if (cv.jCheckBox9.isSelected() == true) {
                septiembrex = true;
            } else {
                septiembrex = false;
            }

            if (cv.jCheckBox10.isSelected() == true) {
                octubrex = true;
            } else {
                octubrex = false;
            }

            if (cv.jCheckBox11.isSelected() == true) {
                noviembrex = true;
            } else {
                noviembrex = false;
            }

            if (cv.jCheckBox12.isSelected() == true) {
                diciembrex = true;
            } else {
                diciembrex = false;
            }

            PreparedStatement ps = cbd.conexion.prepareStatement(cbd.un_sql);

            ps.setBoolean(1, enerox);
            ps.setBoolean(2, febrerox);
            ps.setBoolean(3, marzox);
            ps.setBoolean(4, abrilx);
            ps.setBoolean(5, mayox);
            ps.setBoolean(6, juniox);
            ps.setBoolean(7, juliox);
            ps.setBoolean(8, agostox);
            ps.setBoolean(9, septiembrex);
            ps.setBoolean(10, octubrex);
            ps.setBoolean(11, noviembrex);
            ps.setBoolean(12, diciembrex);

            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }

    public void eliminarCartonFinalx() {
        String seleccion = p1.txtIdentificadorCuota.getText();

        try {
            eliminarCarton(Integer.parseInt(seleccion));
            JOptionPane.showMessageDialog(null, "Eliminado correctamente");
            p1.txtAñoCuota2.setText("");
            int numeroHermano = Integer.parseInt(p1.her.getText());
        boolean existe = comprobarSiExisteAlgunAñoCarton(numeroHermano);
        if (existe) {
            p1.btnAccederCarton.setEnabled(true);
            p1.btnBuscarCarton.setEnabled(true);
            p1.btnEliminarCarton.setEnabled(true);
        } else {
            p1.btnAccederCarton.setEnabled(false);
             p1.btnBuscarCarton.setEnabled(false);
            p1.btnEliminarCarton.setEnabled(false);
        }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPagoCuotas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void tablaEntidadConocida1MousePressed(java.awt.event.MouseEvent evt) {
        p1.txtIdentificadorCuota.setEnabled(false);
        int clic = p1.tblCuota2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una fila de la tabla

        p1.txtIdentificadorCuota.setText((String) p1.tblCuota2.getValueAt(clic, 0));
        p1.txtAñoCuota2.setText((String) p1.tblCuota2.getValueAt(clic, 2));

    }

    public void eliminarCarton() {
        p1.consultarcarton.setVisible(false);
        p1.eliminarAño.setVisible(true);
        p1.eliminarAño.setLocationRelativeTo(null);
        p1.eliminarAño.setSize(630, 400);
        cargarTablaCuotas2();
    }

    public void buscarAñoCarton() {
        cargarTablaCuotasFiltro();
    }

    public void buscarCarton() {
        p1.consultarcarton.setVisible(false);
        p1.buscarAño.setVisible(true);
        p1.buscarAño.setLocationRelativeTo(null);
        p1.buscarAño.setSize(630, 400);
        cargarTablaCuotas();
    }

    public void btnAgregarNuevoCarton() {
        
        if (!p1.jTextField2.getText().equalsIgnoreCase("")) {
        
            try {

            int numero_hermano_i = Integer.parseInt(p1.her.getText()); // guardamos en esta variable el numero de hermano

            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select max(identificador) from cartoncuotas;"; // cogemos el identificador mas grande
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            if (cbd.resultado.next()) {

                int contador = cbd.resultado.getInt(1); // guardamos el identificador de antes en esta variable

                int fecha = Integer.parseInt(p1.jTextField2.getText());
                // comprobacion de que la fecha no existe
                boolean error = false;
                cbd.un_sql = "select * from cartoncuotas where numero_hermano_id=" + numero_hermano_i + ";"; // cogemos el identificador mas grande
                cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
                while (cbd.resultado.next()) {
                    int año = cbd.resultado.getInt("año");
                    if (año == fecha) {
                        error = true;
                    }
                }
                // fin de comprobacion de la fecha
                if (error == false) {
                    agregarCarton(contador + 1, fecha, numero_hermano_i);
                    JOptionPane.showMessageDialog(null, "Insertado correctamente");
                     p1.añadirNuevoAño.dispose();
                     p1.consultarcarton.setVisible(true);
                    
                } else {
                    JOptionPane.showMessageDialog(null, "La fecha introducida ya existe");
                    error = false;
                }

            } else {

                //int numero_hermano_i = Integer.parseInt(p1.her.getText());
                int fecha = Integer.parseInt(p1.jTextField2.getText());
                agregarCarton(1, fecha, numero_hermano_i);
                JOptionPane.showMessageDialog(null, "Insertado correctamente");
                 p1.añadirNuevoAño.dispose();
                 p1.consultarcarton.setVisible(true);

            }
            
            p1.btnAccederCarton.setEnabled(true);
            p1.btnBuscarCarton.setEnabled(true);
            p1.btnEliminarCarton.setEnabled(true);
           
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPagoCuotas.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        } else {
            JOptionPane.showMessageDialog(null, "Asegúrese de haber rellanado todos los campos obligatorios");
        }
    }

    public void añadirNuevoCarton() {
        p1.jTextField1.setEnabled(false);
        p1.consultarcarton.setVisible(false);
        p1.añadirNuevoAño.setVisible(true);
        p1.añadirNuevoAño.setSize(400, 250);
        int numero_hermano_i = Integer.parseInt(p1.her.getText());
        p1.jTextField1.setText(String.valueOf(numero_hermano_i));
    }

    public void localizarCartonPorEntradaTeclado() {    
        cv.texto.setText("");
        // con este contador contaremos cuantos meses se ha pagado por el momento
        int contador = 0;
        
        try {
            String entrada = cv.cuadroAñoCarton.getText();
            int numero_hermano_i = Integer.parseInt(p1.her.getText());

            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from cartoncuotas where año like " + entrada + " and numero_hermano_id=" + numero_hermano_i + ";";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            if (cbd.resultado.next()) {
                //JOptionPane.showMessageDialog(null, "algo hay");
                identificadorCartonSeleccionado = cbd.resultado.getInt("identificador");
                boolean enero = cbd.resultado.getBoolean("enero");
                boolean febrero = cbd.resultado.getBoolean("febrero");
                boolean marzo = cbd.resultado.getBoolean("marzo");
                boolean abril = cbd.resultado.getBoolean("abril");
                boolean mayo = cbd.resultado.getBoolean("mayo");
                boolean junio = cbd.resultado.getBoolean("junio");
                boolean julio = cbd.resultado.getBoolean("julio");
                boolean agosto = cbd.resultado.getBoolean("agosto");
                boolean septiembre = cbd.resultado.getBoolean("septiembre");
                boolean octubre = cbd.resultado.getBoolean("octubre");
                boolean noviembre = cbd.resultado.getBoolean("noviembre");
                boolean diciembre = cbd.resultado.getBoolean("diciembre");
                                

                if (enero) {
                    cv.jCheckBox1.setSelected(true);
                    contador++;
                } else {
                    cv.jCheckBox1.setSelected(false);
                }

                if (febrero) {
                    contador++;
                    cv.jCheckBox2.setSelected(true);
                } else {
                    cv.jCheckBox2.setSelected(false);
                }

                if (marzo) {
                    contador++;
                    cv.jCheckBox3.setSelected(true);
                } else {
                    cv.jCheckBox3.setSelected(false);
                }

                if (abril) {
                    contador++;
                    cv.jCheckBox4.setSelected(true);
                } else {
                    cv.jCheckBox4.setSelected(false);
                }

                if (mayo) {
                    contador++;
                    cv.jCheckBox5.setSelected(true);
                } else {
                    cv.jCheckBox5.setSelected(false);
                }

                if (junio) {
                    contador++;
                    cv.jCheckBox6.setSelected(true);
                } else {
                    cv.jCheckBox6.setSelected(false);
                }

                if (julio) {
                    contador++;
                    cv.jCheckBox7.setSelected(true);
                } else {
                    cv.jCheckBox7.setSelected(false);
                }

                if (agosto) {
                    contador++;
                    cv.jCheckBox8.setSelected(true);
                } else {
                    cv.jCheckBox8.setSelected(false);
                }

                if (septiembre) {
                    contador++;
                    cv.jCheckBox9.setSelected(true);
                } else {
                    cv.jCheckBox9.setSelected(false);
                }

                if (octubre) {
                    contador++;
                    cv.jCheckBox10.setSelected(true);
                } else {
                    cv.jCheckBox10.setSelected(false);
                }

                if (noviembre) {
                    contador++;
                    cv.jCheckBox11.setSelected(true);
                } else {
                    cv.jCheckBox11.setSelected(false);
                }

                if (diciembre) {
                    contador++;
                    cv.jCheckBox12.setSelected(true);
                } else {
                    cv.jCheckBox12.setSelected(false);
                }
                
                cv.texto.setText("Número de meses pagados hasta el momento: "+contador);

            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningun resultado");
                cv.texto.setText("");
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPagoCuotas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void accederCarton() {
        cv.setVisible(true);
        p1.consultarcarton.setVisible(false);
        cv.jCheckBox1.setSelected(false);
        cv.jCheckBox2.setSelected(false);
        cv.jCheckBox3.setSelected(false);
        cv.jCheckBox4.setSelected(false);
        cv.jCheckBox5.setSelected(false);
        cv.jCheckBox6.setSelected(false);
        cv.jCheckBox7.setSelected(false);
        cv.jCheckBox8.setSelected(false);
        cv.jCheckBox9.setSelected(false);
        cv.jCheckBox10.setSelected(false);
        cv.jCheckBox11.setSelected(false);
        cv.jCheckBox12.setSelected(false);
        cv.cuadroAñoCarton.setText("");
        cv.texto.setText("");
    }

    public void consultarCarton() {
        p1.cuotas.setVisible(false);
        p1.consultarcarton.setVisible(true);
        p1.consultarcarton.setLocationRelativeTo(null);
        p1.consultarcarton.setSize(500, 300);
        
        int numeroHermano = Integer.parseInt(p1.her.getText());
        boolean existe = comprobarSiExisteAlgunAñoCarton(numeroHermano);
        if (existe) {
            p1.btnAccederCarton.setEnabled(true);
            p1.btnBuscarCarton.setEnabled(true);
            p1.btnEliminarCarton.setEnabled(true);
        } else {
            p1.btnAccederCarton.setEnabled(false);
             p1.btnBuscarCarton.setEnabled(false);
            p1.btnEliminarCarton.setEnabled(false);
        }
    }

    public void cargarTablaCuotasFiltro() {
        
        if (!p1.txtAñoCuota.getText().equalsIgnoreCase("")) {
                
        DefaultTableModel ff;

        try {
            int numero_hermano_i = Integer.parseInt(p1.her.getText());
            String titulo[] = {"Numero de Hermano", "Año", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[14];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            int año = Integer.parseInt(p1.txtAñoCuota.getText());
            cdb.un_sql = "select * from cartoncuotas where numero_hermano_id=" + numero_hermano_i + " and año=" + año;
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);
            
            while (cdb.resultado.next()) {
                
                fila[0] = cdb.resultado.getString("numero_hermano_id");
                fila[1] = cdb.resultado.getString("año");
                
                if (cdb.resultado.getString("enero").equalsIgnoreCase("1")) {
                    fila[2]="P";
                } else {
                    fila[2]="NP";
                }
                
                if (cdb.resultado.getString("febrero").equalsIgnoreCase("1")) {
                    fila[3]="P";
                } else {
                    fila[3]="NP";
                }
                
                if (cdb.resultado.getString("marzo").equalsIgnoreCase("1")) {
                    fila[4]="P";
                } else {
                    fila[4]="NP";
                }
                
                if (cdb.resultado.getString("abril").equalsIgnoreCase("1")) {
                    fila[5]="P";
                } else {
                    fila[5]="NP";
                }
                
                if (cdb.resultado.getString("mayo").equalsIgnoreCase("1")) {
                    fila[6]="P";
                } else {
                    fila[6]="NP";
                }
                
                if (cdb.resultado.getString("junio").equalsIgnoreCase("1")) {
                    fila[7]="P";
                } else {
                    fila[7]="NP";
                }
                
                if (cdb.resultado.getString("julio").equalsIgnoreCase("1")) {
                    fila[8]="P";
                } else {
                    fila[8]="NP";
                }
                
                if (cdb.resultado.getString("agosto").equalsIgnoreCase("1")) {
                    fila[9]="P";
                } else {
                    fila[9]="NP";
                }
                
                if (cdb.resultado.getString("septiembre").equalsIgnoreCase("1")) {
                    fila[10]="P";
                } else {
                    fila[10]="NP";
                }
                
                if (cdb.resultado.getString("octubre").equalsIgnoreCase("1")) {
                    fila[11]="P";
                } else {
                    fila[11]="NP";
                }
                
                if (cdb.resultado.getString("noviembre").equalsIgnoreCase("1")) {
                    fila[12]="P";
                } else {
                    fila[12]="NP";
                }
                
                if (cdb.resultado.getString("diciembre").equalsIgnoreCase("1")) {
                    fila[13]="P";
                } else {
                    fila[13]="NP";
                }
                
               /* fila[2] = cdb.resultado.getString("enero");
                fila[3] = cdb.resultado.getString("febrero");
                fila[4] = cdb.resultado.getString("marzo");
                fila[5] = cdb.resultado.getString("abril");
                fila[6] = cdb.resultado.getString("mayo");
                fila[7] = cdb.resultado.getString("junio");
                fila[8] = cdb.resultado.getString("julio");
                fila[9] = cdb.resultado.getString("agosto");
                fila[10] = cdb.resultado.getString("septiembre");
                fila[11] = cdb.resultado.getString("octubre");
                fila[12] = cdb.resultado.getString("noviembre");
                fila[13] = cdb.resultado.getString("diciembre");*/

                ff.addRow(fila);
            }

            p1.tblCuota.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tblCuota.setRowSorter(ordenar);
            p1.tblCuota.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        } 
    }

    public void cargarTablaCuotas2() {
        DefaultTableModel ff;

        try {
            int numero_hermano_i = Integer.parseInt(p1.her.getText());
            String titulo[] = {"Identificador", "Numero de Hermano", "Año", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[15];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from cartoncuotas where numero_hermano_id=" + numero_hermano_i;
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString("identificador");
                fila[1] = cdb.resultado.getString("numero_hermano_id");
                fila[2] = cdb.resultado.getString("año");
                
                if (cdb.resultado.getString("enero").equalsIgnoreCase("1")) {
                    fila[3]="P";
                } else {
                    fila[3]="NP";
                }
                
                if (cdb.resultado.getString("febrero").equalsIgnoreCase("1")) {
                    fila[4]="P";
                } else {
                    fila[4]="NP";
                }
                
                if (cdb.resultado.getString("marzo").equalsIgnoreCase("1")) {
                    fila[5]="P";
                } else {
                    fila[5]="NP";
                }
                
                if (cdb.resultado.getString("abril").equalsIgnoreCase("1")) {
                    fila[6]="P";
                } else {
                    fila[6]="NP";
                }
                
                if (cdb.resultado.getString("mayo").equalsIgnoreCase("1")) {
                    fila[7]="P";
                } else {
                    fila[7]="NP";
                }
                
                if (cdb.resultado.getString("junio").equalsIgnoreCase("1")) {
                    fila[8]="P";
                } else {
                    fila[8]="NP";
                }
                
                if (cdb.resultado.getString("julio").equalsIgnoreCase("1")) {
                    fila[9]="P";
                } else {
                    fila[9]="NP";
                }
                
                if (cdb.resultado.getString("agosto").equalsIgnoreCase("1")) {
                    fila[10]="P";
                } else {
                    fila[10]="NP";
                }
                
                if (cdb.resultado.getString("septiembre").equalsIgnoreCase("1")) {
                    fila[11]="P";
                } else {
                    fila[11]="NP";
                }
                
                if (cdb.resultado.getString("octubre").equalsIgnoreCase("1")) {
                    fila[12]="P";
                } else {
                    fila[12]="NP";
                }
                
                if (cdb.resultado.getString("noviembre").equalsIgnoreCase("1")) {
                    fila[13]="P";
                } else {
                    fila[13]="NP";
                }
                
                if (cdb.resultado.getString("diciembre").equalsIgnoreCase("1")) {
                    fila[14]="P";
                } else {
                    fila[14]="NP";
                }
                /*fila[3] = cdb.resultado.getString("enero");
                fila[4] = cdb.resultado.getString("febrero");
                fila[5] = cdb.resultado.getString("marzo");
                fila[6] = cdb.resultado.getString("abril");
                fila[7] = cdb.resultado.getString("mayo");
                fila[8] = cdb.resultado.getString("junio");
                fila[9] = cdb.resultado.getString("julio");
                fila[10] = cdb.resultado.getString("agosto");
                fila[11] = cdb.resultado.getString("septiembre");
                fila[12] = cdb.resultado.getString("octubre");
                fila[13] = cdb.resultado.getString("noviembre");
                fila[14] = cdb.resultado.getString("diciembre");*/

                ff.addRow(fila);
            }

            p1.tblCuota2.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tblCuota2.setRowSorter(ordenar);
            p1.tblCuota2.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarTablaCuotasFiltro2() {
        DefaultTableModel ff;

        try {
            int numero_hermano_i = Integer.parseInt(p1.her.getText());
            String titulo[] = {"identificador", "Numero de Hermano", "Año", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[14];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            int año = Integer.parseInt(p1.txtAñoCuota2.getText());
            cdb.un_sql = "select * from cartoncuotas where numero_hermano_id=" + numero_hermano_i + " and año=" + año;
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[1] = cdb.resultado.getString("numero_hermano_id");
                fila[2] = cdb.resultado.getString("año");
                
                if (cdb.resultado.getString("enero").equalsIgnoreCase("1")) {
                    fila[3]="P";
                } else {
                    fila[3]="NP";
                }
                
                if (cdb.resultado.getString("febrero").equalsIgnoreCase("1")) {
                    fila[4]="P";
                } else {
                    fila[4]="NP";
                }
                
                if (cdb.resultado.getString("marzo").equalsIgnoreCase("1")) {
                    fila[5]="P";
                } else {
                    fila[5]="NP";
                }
                
                if (cdb.resultado.getString("abril").equalsIgnoreCase("1")) {
                    fila[6]="P";
                } else {
                    fila[6]="NP";
                }
                
                if (cdb.resultado.getString("mayo").equalsIgnoreCase("1")) {
                    fila[7]="P";
                } else {
                    fila[7]="NP";
                }
                
                if (cdb.resultado.getString("junio").equalsIgnoreCase("1")) {
                    fila[8]="P";
                } else {
                    fila[8]="NP";
                }
                
                if (cdb.resultado.getString("julio").equalsIgnoreCase("1")) {
                    fila[9]="P";
                } else {
                    fila[9]="NP";
                }
                
                if (cdb.resultado.getString("agosto").equalsIgnoreCase("1")) {
                    fila[10]="P";
                } else {
                    fila[10]="NP";
                }
                
                if (cdb.resultado.getString("septiembre").equalsIgnoreCase("1")) {
                    fila[11]="P";
                } else {
                    fila[11]="NP";
                }
                
                if (cdb.resultado.getString("octubre").equalsIgnoreCase("1")) {
                    fila[12]="P";
                } else {
                    fila[12]="NP";
                }
                
                if (cdb.resultado.getString("noviembre").equalsIgnoreCase("1")) {
                    fila[13]="P";
                } else {
                    fila[13]="NP";
                }
                
                if (cdb.resultado.getString("diciembre").equalsIgnoreCase("1")) {
                    fila[14]="P";
                } else {
                    fila[14]="NP";
                }
                
                /*fila[3] = cdb.resultado.getString("enero");
                fila[4] = cdb.resultado.getString("febrero");
                fila[5] = cdb.resultado.getString("marzo");
                fila[6] = cdb.resultado.getString("abril");
                fila[7] = cdb.resultado.getString("mayo");
                fila[8] = cdb.resultado.getString("junio");
                fila[9] = cdb.resultado.getString("julio");
                fila[10] = cdb.resultado.getString("agosto");
                fila[11] = cdb.resultado.getString("septiembre");
                fila[12] = cdb.resultado.getString("octubre");
                fila[13] = cdb.resultado.getString("noviembre");
                fila[14] = cdb.resultado.getString("diciembre");*/

                ff.addRow(fila);

                p1.txtIdentificadorCuota.setText(fila[0]);
            }

            p1.tblCuota2.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tblCuota2.setRowSorter(ordenar);
            p1.tblCuota2.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarTablaCuotas() {
        DefaultTableModel ff;

        try {
            int numero_hermano_i = Integer.parseInt(p1.her.getText());
            String titulo[] = {"Numero de Hermano", "Año", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[14];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from cartoncuotas where numero_hermano_id=" + numero_hermano_i;
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString("numero_hermano_id");
                fila[1] = cdb.resultado.getString("año");
                
                if (cdb.resultado.getString("enero").equalsIgnoreCase("1")) {
                    fila[2]="P";
                } else {
                    fila[2]="NP";
                }
                
                if (cdb.resultado.getString("febrero").equalsIgnoreCase("1")) {
                    fila[3]="P";
                } else {
                    fila[3]="NP";
                }
                
                if (cdb.resultado.getString("marzo").equalsIgnoreCase("1")) {
                    fila[4]="P";
                } else {
                    fila[4]="NP";
                }
                
                if (cdb.resultado.getString("abril").equalsIgnoreCase("1")) {
                    fila[5]="P";
                } else {
                    fila[5]="NP";
                }
                
                if (cdb.resultado.getString("mayo").equalsIgnoreCase("1")) {
                    fila[6]="P";
                } else {
                    fila[6]="NP";
                }
                
                if (cdb.resultado.getString("junio").equalsIgnoreCase("1")) {
                    fila[7]="P";
                } else {
                    fila[7]="NP";
                }
                
                if (cdb.resultado.getString("julio").equalsIgnoreCase("1")) {
                    fila[8]="P";
                } else {
                    fila[8]="NP";
                }
                
                if (cdb.resultado.getString("agosto").equalsIgnoreCase("1")) {
                    fila[9]="P";
                } else {
                    fila[9]="NP";
                }
                
                if (cdb.resultado.getString("septiembre").equalsIgnoreCase("1")) {
                    fila[10]="P";
                } else {
                    fila[10]="NP";
                }
                
                if (cdb.resultado.getString("octubre").equalsIgnoreCase("1")) {
                    fila[11]="P";
                } else {
                    fila[11]="NP";
                }
                
                if (cdb.resultado.getString("noviembre").equalsIgnoreCase("1")) {
                    fila[12]="P";
                } else {
                    fila[12]="NP";
                }
                
                if (cdb.resultado.getString("diciembre").equalsIgnoreCase("1")) {
                    fila[13]="P";
                } else {
                    fila[13]="NP";
                }
                
               /* fila[2] = cdb.resultado.getString("enero");
                fila[3] = cdb.resultado.getString("febrero");
                fila[4] = cdb.resultado.getString("marzo");
                fila[5] = cdb.resultado.getString("abril");
                fila[6] = cdb.resultado.getString("mayo");
                fila[7] = cdb.resultado.getString("junio");
                fila[8] = cdb.resultado.getString("julio");
                fila[9] = cdb.resultado.getString("agosto");
                fila[10] = cdb.resultado.getString("septiembre");
                fila[11] = cdb.resultado.getString("octubre");
                fila[12] = cdb.resultado.getString("noviembre");
                fila[13] = cdb.resultado.getString("diciembre");*/

                ff.addRow(fila);
            }

            p1.tblCuota.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tblCuota.setRowSorter(ordenar);
            p1.tblCuota.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cargarTablaHermanos() {
        DefaultTableModel ff;

        try {
            String titulo[] = {"Numero de Hermano", "Nombre", "Apellidos", "Nif", "Municipio", "Provincia", "Pais", "Tfno", "Email", "Banco", "Cuenta Bancaria", "Forma de pago", "Tipo de pago"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[13];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from hermanos";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                fila[2] = cdb.resultado.getString(3);
                fila[3] = cdb.resultado.getString(4);
                fila[4] = cdb.resultado.getString(5);
                fila[5] = cdb.resultado.getString(6);
                fila[6] = cdb.resultado.getString(7);
                fila[7] = cdb.resultado.getString(8);
                fila[8] = cdb.resultado.getString(9);
                fila[9] = cdb.resultado.getString(10);
                fila[10] = cdb.resultado.getString(11);
                fila[11] = cdb.resultado.getString(13);
                fila[12] = cdb.resultado.getString(12);
                //fila[13] = cdb.resultado.getString(14);

                ff.addRow(fila);
            }

            p1.tablaHermano.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tablaHermano.setRowSorter(ordenar);
            p1.tablaHermano.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    //metodo para cargar los pagos

    public void cargarPagos() {
        DefaultTableModel ff;
        int numero_hermano_i = Integer.parseInt(p1.her.getText());
        try {
            String titulo[] = {"año", "precio", "pagado"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[3];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();

            // se guarda en la variable el numero de la fila cuando se hace click en una
            //String numero_hermano_id = (String) p1.tablaHermano.getValueAt(clic, 0);
            cdb.un_sql = "select * from pagocuotas WHERE numero_hermano_id=" + p1.her.getText() + ";";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {

                fila[0] = cdb.resultado.getString(3);
                fila[1] = cdb.resultado.getString(5);
                fila[2] = cdb.resultado.getString(6);

                ff.addRow(fila);
            }

            p1.tablaPagos.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tablaPagos.setRowSorter(ordenar);
            p1.tablaPagos.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            //  JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla" +e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }

    public void cargarTablaHermanos2() {
        hermanito hermanos = null;
        DefaultTableModel ff;
        try {
            String titulo[] = {"Numero de hermano", "Nombre", "Apellidos", "Dni", "Municipio", "Provincia", "Pais", "Tfno", "Email", "Banco", "Cuenta bancaria", "Forma de Pago", "Tipo de Pago"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String[] fila = new String[13];
            ArrayList<hermanito> x;
            String campo = (String) p1.seleccion.getSelectedItem();
            String filtro = p1.filtro.getText();
            
            if (!filtro.equalsIgnoreCase("")) {
            
            x = h1.buscarFiltro(filtro, campo);
            
            if (x.size()>0) {

            Iterator<hermanito> it = x.iterator();
            while (it.hasNext()) {
                hermanos = it.next();
                fila[0] = String.valueOf(hermanos.getNumero_hermano());
                fila[1] = hermanos.getNombre();
                fila[2] = hermanos.getApellido();
                fila[3] = hermanos.getNif();
                fila[4] = hermanos.getMunicipio();
                fila[5] = hermanos.getPais();
                fila[6] = hermanos.getProvincia();                
                fila[7] = String.valueOf(hermanos.getTfno());
                fila[8] = hermanos.getEmail();
                fila[9] = hermanos.getBanco();
                fila[10] = hermanos.getCuenta_bancaria();
                fila[11] = String.valueOf(hermanos.getForma_pago_id());
                fila[12] = String.valueOf(hermanos.getTipo_pago_id());
                
                ff.addRow(fila);
            }

            p1.tablaHermano.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
            p1.tablaHermano.setRowSorter(ordenar);
            p1.tablaHermano.setModel(ff);
            
            } else {
                JOptionPane.showMessageDialog(null, "No se ha encontrado ningún resultado");
                cargarTablaHermanos();
            }
            
            }

        } catch (Exception e) {
            //   e.printStackTrace();  
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
        p1.filtro.setText("");
    }
    
    public boolean comprobarSiExisteAlgunAñoCarton(int numeroHermano) {
        boolean correcto = false;
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select * from cartoncuotas c, hermanos h where h.numero_hermano=c.numero_hermano_id and c.numero_hermano_id="+numeroHermano);
            if (cbd.resultado.next()) {
                correcto=true;
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorPagoCuotas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    //agregar pago
    private void agregarPago(int identificador, int numero_hermano_id, String date, int mayordomia_id, int precio, String pagado) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        p = new PagoCuotas(identificador, numero_hermano_id, date, mayordomia_id, precio, pagado);
        p.guardar();
    }

    //buscar
    public ArrayList<hermanito> buscarHermano(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        h1 = new hermanito();
        return h1.buscarFiltro(filtro, campo);
    }

    //agregar carton
    private void agregarCarton(int identificador, int año, int numero_hermano_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        cc = new CartonCuotas(identificador, año, numero_hermano_id);
        cc.grabar();
    }

    /*Metodo para borrar una Entidad Conocida*/
    public void eliminarCarton(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        cc = new CartonCuotas(identificador);
        cc.borrar();
    }
}
