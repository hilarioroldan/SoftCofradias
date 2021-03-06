package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.FormaPago;
import modelo.Hermandad;
import modelo.TipoPago;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermandadConfigurarVista;
import vista.HermandadVista;
import vista.VentanaPrincipal;
import validaciones.*;

public class ControladorHermandad implements ActionListener {

    Hermandad h;
    HermandadVista hv;
    HermandadConfigurarVista hcv;
    DefaultTableModel m;

    public enum di {

        CREAR, MODIFICAR, BORRAR, SALIR, CONFIGURAR, INSERTARFP, MOSTRARFP, MODIFICARFP, ELIMINARFP, INSERTARTP, MOSTRARTP, MODIFICARTP, ELIMINARTP, btnInsertarFormaPago, btnInsertarTipoPago, btnSalir, btnSalir1, btnSalir2, btnSalir3, btnSalir4, btnSalir5, btnSalir6, btnSalir7, btnSalir8, btnModificar1, btnModificar2, btnEliminar1, btnEliminar2;
    }

    public void iniciar() {       
        
        h = new Hermandad();
        hv = new HermandadVista();
        hcv = new HermandadConfigurarVista();
        VentanaPrincipal x = new VentanaPrincipal();
        hv.setVisible(true);
        hv.setLocationRelativeTo(null);
        cargarTablaHermandades();
        
        // validaciones
        hv.txtTelefono1.setDocument(new limitarNumeroTexfield(hv.txtTelefono1, 9));
        hv.txtTelefono2.setDocument(new limitarNumeroTexfield(hv.txtTelefono2, 9));
        
        soloNumerosSoloLetras sn = new soloNumerosSoloLetras();
       sn.SLetras(hv.txtNombreHermandad);
        sn.SLetras(hv.txtMunicipio);
        sn.SLetras(hv.txtProvincia);
        sn.SNumeros(hv.txtTelefono1);
        sn.SNumeros(hv.txtTelefono2);
        sn.SNumeros(hv.txtFax);
        sn.SNumeros(hv.txtNumeroDeHermanos);
        
        
        
        // desactivacion de botones
        hcv.txtIdentificador2.setEnabled(false);
        hcv.txtIdentificador3.setEnabled(false);
        hcv.txtIdentificador4.setEnabled(false);
        hcv.txtIdentificador6.setEnabled(false);
        hcv.txtIdentificador7.setEnabled(false);
        hcv.txtIdentificador8.setEnabled(false);
        

        // comprobacion si hay una hdad creada para desactivar/activar boton crear hdad
        comprobacionHdadCreada();
        
        // comprobacion si hay una hdad creada para activar/desactivar modificar hdad
        comprobarBtnModificar();
        
        // comprobacion si hay una hdad para activar/desactivar btn borrarDatosBD
        comprobarBtnBorrarBD();
        
        // cargamos el numero de hermanos actual que tiene la Hdad
        cargarNumeroDeHermanos();
        
        // comprobamos si se ha creado la hdad para activar/desactivar btn MASoPciones
        comprobacionBtnMasOpciones();

        // desactivamos texfield numero de hermanos
        hv.txtNumeroDeHermanos.setEnabled(false);

        //se añade las acciones a los controles del formulario padre
        hv.btnModificar.setActionCommand("MODIFICAR");
        hv.btnBorrar.setActionCommand("BORRAR");
        hv.btnSalir.setActionCommand("SALIR");
        hv.btnConfigurar.setActionCommand("CONFIGURAR");
        hcv.btnInsertarFP.setActionCommand("INSERTARFP");
        hcv.btnMostrarFP.setActionCommand("MOSTRARFP");
        hcv.btnModificarFP.setActionCommand("MODIFICARFP");
        hcv.btnEliminarFP.setActionCommand("ELIMINARFP");
        hcv.btnInsertarTP.setActionCommand("INSERTARTP");
        hcv.btnMostrarTP.setActionCommand("MOSTRARTP");
        hcv.btnModificarTP.setActionCommand("MODIFICARTP");
        hcv.btnEliminarTP.setActionCommand("ELIMINARTP");
        hcv.btnInsertarFormaPago.setActionCommand("btnInsertarFormaPago");
        hcv.btnInsertarTipoPago.setActionCommand("btnInsertarTipoPago");
        hcv.btnSalir.setActionCommand("btnSalir");
        hcv.btnSalir1.setActionCommand("btnSalir1");
        hcv.btnSalir2.setActionCommand("btnSalir2");
        hcv.btnSalir3.setActionCommand("btnSalir3");
        hcv.btnSalir4.setActionCommand("btnSalir4");
        hcv.btnSalir5.setActionCommand("btnSalir5");
        hcv.btnSalir6.setActionCommand("btnSalir6");
        hcv.btnSalir7.setActionCommand("btnSalir7");
        hcv.btnSalir8.setActionCommand("btnSalir8");
        hcv.btnModificar1.setActionCommand("btnModificar1");
        hcv.btnModificar2.setActionCommand("btnModificar2");
        hcv.btnEliminar1.setActionCommand("btnEliminar1");
        hcv.btnEliminar2.setActionCommand("btnEliminar2");
        hv.btnCrear.setActionCommand("CREAR");
        //Se pone a escuchar las acciones del usuario
        hv.btnModificar.addActionListener(this);
        hv.btnBorrar.addActionListener(this);
        hv.btnSalir.addActionListener(this);
        hv.btnConfigurar.addActionListener(this);
        hcv.btnInsertarFP.addActionListener(this);
        hcv.btnMostrarFP.addActionListener(this);
        hcv.btnModificarFP.addActionListener(this);
        hcv.btnEliminarFP.addActionListener(this);
        hcv.btnInsertarTP.addActionListener(this);
        hcv.btnMostrarTP.addActionListener(this);
        hcv.btnModificarTP.addActionListener(this);
        hcv.btnEliminarTP.addActionListener(this);
        hcv.btnInsertarFormaPago.addActionListener(this);
        hcv.btnInsertarTipoPago.addActionListener(this);
        hcv.btnSalir.addActionListener(this);
        hcv.btnSalir1.addActionListener(this);
        hcv.btnSalir2.addActionListener(this);
        hcv.btnSalir3.addActionListener(this);
        hcv.btnSalir4.addActionListener(this);
        hcv.btnSalir5.addActionListener(this);
        hcv.btnSalir6.addActionListener(this);
        hcv.btnSalir7.addActionListener(this);
        hcv.btnSalir8.addActionListener(this);
        hcv.btnModificar1.addActionListener(this);
        hcv.btnModificar2.addActionListener(this);
        hcv.btnEliminar1.addActionListener(this);
        hcv.btnEliminar2.addActionListener(this);
        hv.btnCrear.addActionListener(this);

        hv.tblHermandad.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla hermandad           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });

        hcv.tblFormaPago1.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla forma pago           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed2(evt);
            }
        });

        hcv.tblFormaPago2.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla forma pago 2          
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed3(evt);
            }
        });

        hcv.tblFormaPago3.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla forma pago 3          
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed4(evt);
            }
        });

        hcv.TblTipoPago1.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla tipo pago           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed5(evt);
            }
        });

        hcv.TblTipoPago2.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla tipo pago           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed6(evt);
            }
        });

        hcv.TblTipoPago3.addMouseListener(new java.awt.event.MouseAdapter() {  //tabla tipo pago           
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed7(evt);
            }
        });
    }

    // ACCIONES DEL USUARIO
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (di.valueOf(e.getActionCommand())) {
            case CREAR:
                crearHdad();
                break;

            case SALIR:
                hv.dispose();
                break;

            case MODIFICAR:
                modificarHermandad();
                break;

            case BORRAR:
                borrarHdad();
                break;

            case CONFIGURAR:
                abrirConfigurar();
                break;

            case INSERTARFP:
                insertarFP();
                break;

            case MOSTRARFP:
                mostrarFP();
                break;

            case MODIFICARFP:
                modificarFP();
                break;

            case ELIMINARFP:
                eliminarFP();
                break;

            case INSERTARTP:
                insertarTP();
                break;

            case MOSTRARTP:
                mostrarTP();
                break;

            case MODIFICARTP:
                modificarTP();
                break;

            case ELIMINARTP:
                eliminarTP();
                break;

            case btnInsertarFormaPago:
                insertarFormaDePago();
                actualizarTblFormaPago1();
                break;

            case btnInsertarTipoPago:
                insertarTipoDePago();
                actualizarTblTipoPago1();

                break;

            case btnModificar1:
                modificarFormaDePago();
                actualizarTblFormaPago2();
                actualizarTblFormaPago1();
                break;

            case btnModificar2:
                modificarTipoDePago();
                actualizarTblTipoPago2();
                actualizarTblTipoPago1();
                break;

            case btnEliminar1:
                eliminarFormaPago();
                actualizarTblFormaPago3();
                actualizarTblFormaPago2();
                actualizarTblFormaPago1();
                break;

            case btnEliminar2:
                eliminarTipoPago();
                actualizarTblTipoPago3();
                actualizarTblTipoPago2();
                actualizarTblTipoPago1();
                break;

            case btnSalir:
                hcv.setVisible(false);
                break;

            case btnSalir1:
                hcv.formaPagoInsertar.dispose();
                hcv.setVisible(true);
                break;

            case btnSalir2:
                hcv.formaPagoMostrar.dispose();
                hcv.setVisible(true);
                break;

            case btnSalir3:
                hcv.formaPagoModificar.dispose();
                hcv.setVisible(true);
                break;

            case btnSalir4:
                hcv.formaPagoEliminar.dispose();
                hcv.setVisible(true);
                break;

            case btnSalir5:
                hcv.tipoPagoInsertar.dispose();
                hcv.setVisible(true);
                break;

            case btnSalir6:
                hcv.tipoPagoMostrar.dispose();
                hcv.setVisible(true);
                break;

            case btnSalir7:
                hcv.tipoPagoModificar.dispose();
                hcv.setVisible(true);
                break;

            case btnSalir8:
                hcv.tipoPagoEliminar.dispose();
                hcv.setVisible(true);
                break;
        }
    }
    
    public void borrarHdad() {
        
       int ax = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas continuar?\n Perderás todos los datos", "Selecciona una Opcion", JOptionPane.YES_NO_OPTION);
        if(ax == JOptionPane.YES_OPTION){
           // JOptionPane.showMessageDialog(null, "Has seleccionado SI.");
            
        
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_st.executeUpdate("truncate cartoncuotas;");
            cbd.un_st.executeUpdate("truncate entidadesconocidas;");
            cbd.un_st.executeUpdate("truncate librodeasientos;");
            cbd.un_st.executeUpdate("truncate formapago;");
            cbd.un_st.executeUpdate("truncate tipopago;");
            cbd.un_st.executeUpdate("truncate hermandadeshermanadas;");
            cbd.un_st.executeUpdate("truncate inventario;");
            cbd.un_st.executeUpdate("truncate juntagobierno;");
            cbd.un_st.executeUpdate("truncate repartoloteria;");
            cbd.un_st.executeUpdate("truncate protocolo;");
            cbd.un_st.executeUpdate("truncate papeletasitio;");
            cbd.un_st.executeUpdate("truncate salidaprocesional;");
            cbd.un_st.executeUpdate("truncate planingmayordomia;");
            cbd.un_st.executeUpdate("truncate planingsecretaria;");
            cbd.un_st.executeUpdate("truncate pagocuotas;");
            cbd.un_st.executeUpdate("delete from cuentabancaria;");
            cbd.un_st.executeUpdate("delete from hermanos;");
            cbd.un_st.executeUpdate("delete from loterias;");
            cbd.un_st.executeUpdate("delete from mayordomia;");
            cbd.un_st.executeUpdate("delete from secretaria;");
            cbd.un_st.executeUpdate("delete from hermandad;");
            JOptionPane.showMessageDialog(null, "Hermandad borrada correctamente");
            limpiarTexto();
            hv.btnCrear.setEnabled(true);
            cargarTablaHermandades();
            comprobarBtnModificar(); // comprobacion boton modificar
            comprobarBtnBorrarBD(); // comprobacion boton borrarDatosBD
            comprobacionBtnMasOpciones(); // comprobacion boton Mas Opciones
        
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        }
        } else if(ax == JOptionPane.NO_OPTION) {
           // JOptionPane.showMessageDialog(null, "Has seleccionado NO.");
        }
        
    }

    public void crearHdad() {

        if (!hv.txtNombreHermandad.getText().equalsIgnoreCase("") && hv.jDateChooser1.getDate() != null && !hv.txtDomicilio.getText().equalsIgnoreCase("") && !hv.txtMunicipio.getText().equalsIgnoreCase("") && !hv.txtProvincia.getText().equalsIgnoreCase("") && !hv.txtTelefono1.getText().equalsIgnoreCase("")) {

            // inicio
            try {

                String año_fundacion = "";
                //trabajando con fechas
                if (hv.jDateChooser1.getDate() != null) {
                    int año = hv.jDateChooser1.getCalendar().get(Calendar.YEAR);
                    int mes = hv.jDateChooser1.getCalendar().get(Calendar.MONTH);
                    int dia = hv.jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                    año_fundacion = año + "-" + mes + "-" + dia;
                    //
                }
                // fin fecha
                String nombre_hermandad = hv.txtNombreHermandad.getText();
                String domicilio = hv.txtDomicilio.getText();
                String municipio = hv.txtMunicipio.getText();
                String provincia = hv.txtProvincia.getText();
                int telf1 = 0;
                int telf2 = 0;
                int fax = 0;
                telf1 = Integer.parseInt(hv.txtTelefono1.getText());   
                if (!hv.txtTelefono2.getText().equalsIgnoreCase("")) {
                telf2 = Integer.parseInt(hv.txtTelefono2.getText()); 
                }
                if (!hv.txtFax.getText().equalsIgnoreCase("")) {
                fax = Integer.parseInt(hv.txtFax.getText()); 
                }
                String descripcion = hv.txtDescripcion.getText();

                agregarHermandad(1, nombre_hermandad, año_fundacion, domicilio, municipio, provincia, telf1, telf2, fax, descripcion);

                JOptionPane.showMessageDialog(null, "Has creado correctamente una Hermandad");

                crearSecretaria(); // creamos secretaria                        
                crearMayordormia(); // creamos mayordomia
                cargarTablaHermandades(); //
                hv.btnCrear.setEnabled(false); // bloqueamos el boton de crear
                limpiarTexto();
                
                comprobarBtnModificar(); // comprobacion boton modificar
                comprobarBtnBorrarBD(); // comprobacion boton borrarDatosBD
                comprobacionBtnMasOpciones(); // activacion del boton Masopciones

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                ex.printStackTrace();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Has introducido un parámetro incorrecto. Revisa lo campos introducidos", "Error", JOptionPane.ERROR_MESSAGE);
            }

            //fin
        } else {
            JOptionPane.showMessageDialog(null, "Asegúrese de que ha rellenado correctamente todos los campos obligatorios", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void modificarHermandad() {

        int clic = hv.tblHermandad.getSelectedRow();

        if (clic != -1) {

            try {

                String nombre_hermandadM = hv.txtNombreHermandad.getText();

                //trabajando con fechas
                String año_fundacion = "";
                if (hv.jDateChooser1.getDate() != null) {
                    int año = hv.jDateChooser1.getCalendar().get(Calendar.YEAR);
                    int mes = hv.jDateChooser1.getCalendar().get(Calendar.MONTH);
                    int dia = hv.jDateChooser1.getCalendar().get(Calendar.DAY_OF_MONTH);
                    año_fundacion = año + "-" + mes + "-" + dia;
                    //
                }
                // fin fecha
                
                String domicilioM = hv.txtDomicilio.getText();
                String municipioM = hv.txtMunicipio.getText();
                String provinciaM = hv.txtProvincia.getText();
                int telf1M = 0;
                int telf2M = 0;
                int faxM = 0;
                telf1M = Integer.parseInt(hv.txtTelefono1.getText());
                
                if (!hv.txtTelefono2.getText().equalsIgnoreCase("")) {
                telf2M = Integer.parseInt(hv.txtTelefono2.getText());
                }
                
                if (!hv.txtFax.getText().equalsIgnoreCase("")) {
                faxM = Integer.parseInt(hv.txtFax.getText());
                }
                
                String descripcionM = hv.txtDescripcion.getText();

                modificarHermandad(1, nombre_hermandadM, año_fundacion, domicilioM, municipioM, provinciaM, telf1M, telf2M, faxM, descripcionM);
                JOptionPane.showMessageDialog(null, "Modificado correctamente");
            
                
            cargarTablaHermandades();
            limpiarTexto();
            
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {

            } catch (NumberFormatException x) {
                JOptionPane.showMessageDialog(null, "Has introducido un parámetro incorrecto en un campo inadecuado", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Debes de seleccionar la Tabla para cargar los valores", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    //metodo para eliminar una forma de pago
    public void eliminarFormaPago() {
        int identificador = Integer.parseInt(hcv.txtIdentificador4.getText());
        try {
            eliminarFormaPago(identificador);
            JOptionPane.showMessageDialog(null, "¡Eliminado correctamente!");
            hcv.txtDescripcion4.setText("");
            hcv.txtIdentificador2.setText("");
            hcv.txtDescripcion2.setText("");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
    }

    //metodo para modificadr una forma de pago
    public void modificarFormaDePago() {
        try {
            int identificador = Integer.parseInt(hcv.txtIdentificador3.getText());
            String descripcion = hcv.txtDescripcion3.getText();
            modificarFormaPago(identificador, descripcion);
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
            hcv.txtIdentificador3.setText("");
            hcv.txtDescripcion3.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //metodo para insertar una forma de pago
    public void insertarFormaDePago() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select max(identificador) from formapago");
            int identificador = 0;
            if (cbd.resultado.next()) {
                identificador = cbd.resultado.getInt(1)+1;
            } else {
                identificador = 1;
            }
            try {
                String descripcion = hcv.txtDescripcion1.getText();
                agregarFormaPago(identificador, descripcion);
                hcv.txtDescripcion1.setText("");
            } catch (Exception e) {
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //metodo para eliminar una forma de pago
    public void eliminarTipoPago() {
        int identificador = Integer.parseInt(hcv.txtIdentificador8.getText());
        try {
            eliminarTipoPago(identificador);
            JOptionPane.showMessageDialog(null, "¡Eliminado correctamente!");
            hcv.txtDescripcion8.setText("");
            hcv.txtPrecio4.setText("");
            hcv.txtIdentificador8.setText("");
             hcv.txtIdentificador6.setText("");
            hcv.txtDescripcion6.setText("");
            hcv.txtPrecio2.setText("");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
        }
    }

    //metodo para modificar una forma de pago
    public void modificarTipoDePago() {
        try {
            int identificador = Integer.parseInt(hcv.txtIdentificador7.getText());
            String descripcion = hcv.txtDescripcion7.getText();
            Double precio = Double.parseDouble(hcv.txtPrecio3.getText());
            modificarTipoPago(identificador, descripcion, precio);
            JOptionPane.showMessageDialog(null, "Modificado correctamente");
            hcv.txtIdentificador7.setText("");
            hcv.txtDescripcion7.setText("");
            hcv.txtPrecio3.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //metodo para insertar una forma de pago
    public void insertarTipoDePago() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select max(identificador) from tipopago");
            int identificador = 0;
            if (cbd.resultado.next()) {
                identificador = cbd.resultado.getInt(1)+1;
            } else {
                identificador = 1;
            }
            try {
                String descripcion = hcv.txtDescripcion5.getText();
                Double precio = Double.parseDouble(hcv.txtPrecio1.getText());
                agregarTipoPago(identificador, descripcion, precio);
                hcv.txtDescripcion5.setText("");
                hcv.txtPrecio1.setText("");
            } catch (Exception e) {
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // metodo que actualiza una table en forma de pago
    public void actualizarTblFormaPago1() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Forma de pago"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[2];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from formapago";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                m.addRow(fila);
            }

            hcv.tblFormaPago1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hcv.tblFormaPago1.setRowSorter(ordenar);
            hcv.tblFormaPago1.setModel(m);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTblFormaPago2() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Forma de pago"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[2];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from formapago";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                m.addRow(fila);
            }

            hcv.tblFormaPago2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hcv.tblFormaPago2.setRowSorter(ordenar);
            hcv.tblFormaPago2.setModel(m);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTblFormaPago3() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Forma de pago"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[2];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from formapago";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                m.addRow(fila);
            }

            hcv.tblFormaPago3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hcv.tblFormaPago3.setRowSorter(ordenar);
            hcv.tblFormaPago3.setModel(m);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // metodo que actualiza una table en forma de pago
    public void actualizarTblTipoPago1() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Tipo de pago", "Precio"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[3];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from tipopago";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                fila[2] = cdb.resultado.getString(3);
                m.addRow(fila);
            }

            hcv.TblTipoPago1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hcv.TblTipoPago1.setRowSorter(ordenar);
            hcv.TblTipoPago1.setModel(m);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTblTipoPago2() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Tipo de pago", "Precio"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[3];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from tipopago";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                fila[2] = cdb.resultado.getString(3);
                m.addRow(fila);
            }

            hcv.TblTipoPago2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hcv.TblTipoPago2.setRowSorter(ordenar);
            hcv.TblTipoPago2.setModel(m);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actualizarTblTipoPago3() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Tipo de pago", "Precio"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[3];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from tipopago";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                fila[0] = cdb.resultado.getString(1);
                fila[1] = cdb.resultado.getString(2);
                fila[2] = cdb.resultado.getString(3);
                m.addRow(fila);
            }

            hcv.TblTipoPago3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hcv.TblTipoPago3.setRowSorter(ordenar);
            hcv.TblTipoPago3.setModel(m);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void insertarFP() {
        hcv.formaPagoInsertar.setVisible(true);
        hcv.formaPagoInsertar.setLocation(500, 200);
        hcv.formaPagoInsertar.setSize(500, 300);
        hcv.setVisible(false);
    }

    public void mostrarFP() {
        hcv.formaPagoMostrar.setVisible(true);
        hcv.formaPagoMostrar.setLocation(500, 200);
        hcv.formaPagoMostrar.setSize(500, 400);
        hcv.setVisible(false);
        actualizarTblFormaPago1();
    }

    public void modificarFP() {
        hcv.formaPagoModificar.setVisible(true);
        hcv.formaPagoModificar.setLocation(500, 200);
        hcv.formaPagoModificar.setSize(500, 400);
        hcv.setVisible(false);
        actualizarTblFormaPago2();
    }

    public void eliminarFP() {
        hcv.formaPagoEliminar.setVisible(true);
        hcv.formaPagoEliminar.setLocation(500, 200);
        hcv.formaPagoEliminar.setSize(500, 400);
        hcv.setVisible(false);
        actualizarTblFormaPago3();
    }

    public void insertarTP() {
        hcv.tipoPagoInsertar.setVisible(true);
        hcv.tipoPagoInsertar.setLocation(500, 200);
        hcv.tipoPagoInsertar.setSize(500, 400);
        hcv.setVisible(false);
    }

    public void mostrarTP() {
        hcv.tipoPagoMostrar.setVisible(true);
        hcv.tipoPagoMostrar.setLocation(500, 200);
        hcv.tipoPagoMostrar.setSize(500, 400);
        hcv.setVisible(false);
        actualizarTblTipoPago1();
    }

    public void modificarTP() {
        hcv.tipoPagoModificar.setVisible(true);
        hcv.tipoPagoModificar.setLocation(500, 200);
        hcv.tipoPagoModificar.setSize(500, 400);
        hcv.setVisible(false);
        actualizarTblTipoPago2();
    }

    public void eliminarTP() {
        hcv.tipoPagoEliminar.setVisible(true);
        hcv.tipoPagoEliminar.setLocation(500, 200);
        hcv.tipoPagoEliminar.setSize(500, 400);
        hcv.setVisible(false);
        actualizarTblTipoPago3();
    }

    public void abrirConfigurar() {
        hcv.setVisible(true);
        hcv.setLocationRelativeTo(hv);
    }

    /*Metodo para agregar un pago de cuota*/
    public void agregarFormaPago(int identificador, String tipo_pago) {
        FormaPago x = new FormaPago(identificador, tipo_pago);
        try {
            x.grabar();
            JOptionPane.showMessageDialog(null, "Forma de pago insertada correctamente");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Identificador " + identificador + " ya existe, ingrese un identificador distinto " + ex, "SofCofradias", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*Metodo para modificar un pago de cuota*/
    public void modificarFormaPago(int identificador, String nombre) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            FormaPago x = new FormaPago(identificador, nombre);
            x.actualizar();
        } catch (Exception e) {
        }
    }

    /*Metodo para borrar una forma de pago*/
    public void eliminarFormaPago(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        FormaPago x = new FormaPago(identificador);
        x.borrar();
    }

    /*Metodo para agregar un pago de cuota*/
    public void agregarTipoPago(int identificador, String tipo_pago, Double precio) {
        TipoPago x = new TipoPago(identificador, tipo_pago, precio);
        try {
            x.grabar();
            JOptionPane.showMessageDialog(null, "Tipo de cuota insertada correctamente");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Identificador " + identificador + " ya existe, ingrese un identificador distinto " + ex, "SofCofradias", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*Metodo para modificar un pago de cuota*/
    public void modificarTipoPago(int identificador, String nombre, Double precio) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        try {
            TipoPago x = new TipoPago(identificador, nombre, precio);
            x.actualizar();
        } catch (Exception e) {
        }
    }

    /*Metodo para borrar una forma de pago*/
    public void eliminarTipoPago(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        TipoPago x = new TipoPago(identificador);
        x.borrar();
    }

    /*Metodo para buscar una Hermandad indicando el campo y el valor*/
    public ArrayList<Hermandad> buscarHermandad(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        h = new Hermandad();
        return h.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar una Hermandad*/
    public void agregarHermandad(int identificador, String nombre, String año_fundacion, String domicilio, String municipio, String provincia, int telf1, int telf2, int fax, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        h = new Hermandad(identificador, nombre, año_fundacion, domicilio, municipio, provincia, telf1, telf2, fax, descripcion);
        h.grabar();
    }

    /*Metodo para modificar una Hermandad*/
    public void modificarHermandad(int identificador, String nombre, String año_fundacion, String domicilio, String municipio, String provincia, int telf1, int telf2, int fax, String descripcion) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        h = new Hermandad(identificador, nombre, año_fundacion, domicilio, municipio, provincia, telf1, telf2, fax, descripcion);
        h.actualizar();
    }

    /*Metodo para borrar una Hermandad*/
    public void eliminarHermandad(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Hermandad h;
        h = new Hermandad(identificador);
        h.borrar();
    }

    /*Metodo para leer todas las hermandades*/
    public ArrayList<Hermandad> recargarHermandades() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Hermandad h;
        h = new Hermandad();
        return h.leerTodos();
    }

    public void cargarTablaHermandades() {
        DefaultTableModel m;

        try {
            String titulo[] = {"Nombre hermandad", "Año fundacion", "Domicilio", "Municipio", "Provincia", "Telf1", "Telf2", "Fax", "Descripcion"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String fila[] = new String[9];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from hermandad";
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                //fila[0] = cdb.resultado.getString(1);
                fila[0] = cdb.resultado.getString(2);
                fila[1] = cdb.resultado.getString(3);
                fila[2] = cdb.resultado.getString(4);
                fila[3] = cdb.resultado.getString(5);
                fila[4] = cdb.resultado.getString(6);
                fila[5] = cdb.resultado.getString(7);
                
                if (!cdb.resultado.getString(8).equalsIgnoreCase("0")) {    // si en la tabla tenemos un 0 porque no se introduce un campo lo pasaremos a vacio
                fila[6] = cdb.resultado.getString(8);
                } else {
                fila[6] = "";    
                }
                
                if (!cdb.resultado.getString(9).equalsIgnoreCase("0")) {
                fila[7] = cdb.resultado.getString(9);
                } else {
                fila[7] = "";    
                }
                
                fila[8] = cdb.resultado.getString(10);
                m.addRow(fila);
            }

            hv.tblHermandad.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<TableModel>(m);
            hv.tblHermandad.setRowSorter(ordenar);
            hv.tblHermandad.setModel(m);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla. Póngase en contacto con el técnico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {

        try {
            Date x = null;
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select año_fundacion from hermandad where identificador=1");
            if (cbd.resultado.next()) {
                x = cbd.resultado.getDate(1);
            }
            int clic = hv.tblHermandad.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

            if (clic != -1) {
                try {
                    hv.txtNombreHermandad.setText(hv.tblHermandad.getValueAt(clic, 0).toString());
                    hv.jDateChooser1.setDate(x);
                    hv.txtDomicilio.setText(hv.tblHermandad.getValueAt(clic, 2).toString());
                    hv.txtMunicipio.setText(hv.tblHermandad.getValueAt(clic, 3).toString());
                    hv.txtProvincia.setText(hv.tblHermandad.getValueAt(clic, 4).toString());
                    hv.txtTelefono1.setText(hv.tblHermandad.getValueAt(clic, 5).toString());
                    hv.txtTelefono2.setText(hv.tblHermandad.getValueAt(clic, 6).toString());
                    hv.txtFax.setText(hv.tblHermandad.getValueAt(clic, 7).toString());
                    hv.txtDescripcion.setText(hv.tblHermandad.getValueAt(clic, 8).toString());
                } catch (Exception e) {
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void jTable1MousePressed2(java.awt.event.MouseEvent evt) {
        int clic = hcv.tblFormaPago1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            try {
                hcv.txtIdentificador2.setText(hcv.tblFormaPago1.getValueAt(clic, 0).toString());
                hcv.txtDescripcion2.setText(hcv.tblFormaPago1.getValueAt(clic, 1).toString());
            } catch (Exception e) {
            }
        }
    }

    private void jTable1MousePressed3(java.awt.event.MouseEvent evt) {
        int clic = hcv.tblFormaPago2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            try {
                hcv.txtIdentificador3.setText(hcv.tblFormaPago2.getValueAt(clic, 0).toString());
                hcv.txtDescripcion3.setText(hcv.tblFormaPago2.getValueAt(clic, 1).toString());
            } catch (Exception e) {
            }
        }
    }

    private void jTable1MousePressed4(java.awt.event.MouseEvent evt) {
        int clic = hcv.tblFormaPago3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            try {
                hcv.txtIdentificador4.setText(hcv.tblFormaPago3.getValueAt(clic, 0).toString());
                hcv.txtDescripcion4.setText(hcv.tblFormaPago3.getValueAt(clic, 1).toString());
            } catch (Exception e) {
            }
        }
    }

    private void jTable1MousePressed5(java.awt.event.MouseEvent evt) {
        int clic = hcv.TblTipoPago1.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            try {
                hcv.txtIdentificador6.setText(hcv.TblTipoPago1.getValueAt(clic, 0).toString());
                hcv.txtDescripcion6.setText(hcv.TblTipoPago1.getValueAt(clic, 1).toString());
                hcv.txtPrecio2.setText(hcv.TblTipoPago1.getValueAt(clic, 2).toString());
            } catch (Exception e) {
            }
        }
    }

    private void jTable1MousePressed6(java.awt.event.MouseEvent evt) {
        int clic = hcv.TblTipoPago2.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            try {
                hcv.txtIdentificador7.setText(hcv.TblTipoPago2.getValueAt(clic, 0).toString());
                hcv.txtDescripcion7.setText(hcv.TblTipoPago2.getValueAt(clic, 1).toString());
                hcv.txtPrecio3.setText(hcv.TblTipoPago2.getValueAt(clic, 2).toString());
            } catch (Exception e) {
            }
        }
    }

    private void jTable1MousePressed7(java.awt.event.MouseEvent evt) {
        int clic = hcv.TblTipoPago3.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

        if (clic != -1) {
            try {
                hcv.txtIdentificador8.setText(hcv.TblTipoPago3.getValueAt(clic, 0).toString());
                hcv.txtDescripcion8.setText(hcv.TblTipoPago3.getValueAt(clic, 1).toString());
                hcv.txtPrecio4.setText(hcv.TblTipoPago3.getValueAt(clic, 2).toString());
            } catch (Exception e) {
            }
        }
    }

    public void limpiarTexto() {
        hv.txtNombreHermandad.setText("");
        hv.jDateChooser1.setDate(null);
        hv.txtDomicilio.setText("");
        hv.txtMunicipio.setText("");
        hv.txtProvincia.setText("");
        hv.txtTelefono1.setText("");
        hv.txtTelefono2.setText("");
        hv.txtFax.setText("");
        hv.txtDescripcion.setText("");
        hv.txtNumeroDeHermanos.setText("");
    }

    public void comprobacionHdadCreada() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select * from hermandad");
            if (cbd.resultado.next()) {
                hv.btnCrear.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "¡Bienvenido por primera vez a SoftCofradias!");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void comprobarBtnModificar() {
        try {
            
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select * from hermandad");
            
            if (cbd.resultado.next()) {
                hv.btnModificar.setEnabled(true);
            } else {
                hv.btnModificar.setEnabled(false);
            }
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void comprobarBtnBorrarBD() {
        try {
            
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select * from hermandad");
            
            if (cbd.resultado.next()) {
                hv.btnBorrar.setEnabled(true);
            } else {
                hv.btnBorrar.setEnabled(false);
            }
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void crearSecretaria() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_st.executeUpdate("insert into secretaria(identificador, hermandad_id) values (1, 1);");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al crear secretaria. Pongase en contacto con el tecnico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void crearMayordormia() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_st.executeUpdate("insert into mayordomia(identificador, hermandad_id) values (1, 1);");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al crear mayordomia. Pongase en contacto con el tecnico", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cargarNumeroDeHermanos() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select numero_hermanos from hermandad");
            if (cbd.resultado.next()) {
            hv.txtNumeroDeHermanos.setText(cbd.resultado.getString(1));
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            
        }
    }
    
    public void comprobacionBtnMasOpciones() {
        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.resultado = cbd.un_st.executeQuery("select * from hermandad");
            
            if (cbd.resultado.next()) {
                hv.btnConfigurar.setEnabled(true);
            } else {
                hv.btnConfigurar.setEnabled(false);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorHermandad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
