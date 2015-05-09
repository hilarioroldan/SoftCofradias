package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.Inventario;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.InventarioVista;

public class ControladorInventario implements ActionListener {

    Inventario i;
    InventarioVista iv;

    FileInputStream fis;
    int longitudBytes;

    int contador = 0;
    boolean salto = false;

    public void iniciar() {

        i = new Inventario();
        iv = new InventarioVista();

        iv.setVisible(true);
        iv.setLocationRelativeTo(null);

        // desabilitamos el boton registo de todos los paneles menos para el insertar
        iv.txtRegistro2.setEnabled(false);
        iv.txtRegistro3.setEnabled(false);
        iv.txtRegistro4.setEnabled(false);

        cargarTablaEntidadConocida1();
        // iniciliaciamos las fotos en primera posicion en la pantalla2
        ponerPrimerInventario();
        //iv.btnIzquierda1.setEnabled(false);
        contador++;
        // fin inicialicacion

        //se añade las acciones a los controles del formulario padre
        iv.btnInsertar1.setActionCommand("INSERTAR1");
        iv.btnExaminar1.setActionCommand("EXAMINAR1");
        iv.btnExaminar2.setActionCommand("EXAMINAR2");
        iv.btnIzquierda1.setActionCommand("btnIzquierda1");
        iv.btnDerecha1.setActionCommand("btnDerecha1");
        iv.btnBuscar4.setActionCommand("BTNBUSCAR4");
        iv.btnBuscar3.setActionCommand("BTNBUSCAR3");
        iv.btnEliminar.setActionCommand("ELIMINAR");
        iv.btnModificar.setActionCommand("MODIFICAR");
        iv.btnSalir1.setActionCommand("SALIR1");
        iv.btnSalir2.setActionCommand("SALIR2");
        iv.btnSalir3.setActionCommand("SALIR3");
        iv.btnSalir4.setActionCommand("SALIR4");
        //Se pone a escuchar las acciones del usuario
        iv.btnInsertar1.addActionListener(this);
        iv.btnIzquierda1.addActionListener(this);
        iv.btnDerecha1.addActionListener(this);
        iv.btnBuscar4.addActionListener(this);
        iv.btnBuscar3.addActionListener(this);
        iv.btnEliminar.addActionListener(this);
        iv.btnModificar.addActionListener(this);
        iv.btnExaminar1.addActionListener(this);
        iv.btnExaminar2.addActionListener(this);
        iv.btnSalir1.addActionListener(this);
        iv.btnSalir2.addActionListener(this);
        iv.btnSalir3.addActionListener(this);
        iv.btnSalir4.addActionListener(this);

        //Al Hacer click a una fila de la tabla los valores se cargaran en los cuadros de texto correspondientes
        iv.tblInventario1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventario1MousePressed(evt);
            }
        });
        //
        iv.tblInventario2.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventario2MousePressed(evt);
            }
        });
        //
        iv.tblInventario3.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventario3MousePressed(evt);
            }
        });        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {

            case "INSERTAR1":
                insertarPantalla1();
                cargarTablaEntidadConocida1();
                vaciarTxtPantalla1_2();
                break;

            case "btnIzquierda1":
                do {
                    btnizq1();
                    contador--;
                } while (salto == true);
                break;

            case "btnDerecha1":
                do {
                    btndcha1();
                    contador++;
                } while (salto == true);
                break;

            case "BTNBUSCAR4":
                buscarPantalla3();
                break;

            case "BTNBUSCAR3":
                buscarPantalla4();
                break;

            case "SALIR1":

                iv.dispose();
                break;

            case "SALIR2":

                iv.dispose();
                break;

            case "SALIR3":

                iv.dispose();
                break;

            case "SALIR4":

                iv.dispose();
                break;

            case "ELIMINAR":

                eliminarInventarioSeleccionado();
                cargarTablaEntidadConocida1();
                vaciarTxtPantalla4();

                break;

            case "EXAMINAR1":
                examinar1();
                break;

            case "EXAMINAR2":
                examinar2();
                break;

            case "MODIFICAR":
                modificarInventarioSeleccionado();
                break;
        }
    }

    public void modificarInventarioSeleccionado() {

        String identificador = iv.txtRegistro4.getText();
        String nombre = iv.txtNombre4.getText();
        String autor = iv.txtAutor4.getText();
        String estilo = iv.txtEstilo4.getText();
        String fecha_realizacion = iv.txtFechaRealizacion4.getText();
        String procedencia = iv.txtProcedencia4.getText();
        String valoracion_economica = iv.txtValoracionEconomica4.getText();
        String mejora = iv.txtMejora4.getText();
        String restauracion = iv.txtRestauracion4.getText();
        String cantidad = iv.txtCantidad4.getText();
        String observaciones = iv.txtObservaciones4.getText();
        String adquisicion = iv.txtAdquisicion4.getText();
        String fecha_baja = iv.txtFechaBaja4.getText();

        try {            

            modificarInventario(Integer.parseInt(identificador), nombre, autor, estilo, fecha_realizacion, procedencia, Double.parseDouble(valoracion_economica), mejora, restauracion, Integer.parseInt(cantidad), observaciones, adquisicion, fis, fecha_baja, longitudBytes);
            JOptionPane.showMessageDialog(null, "¡Modificado Correctamente!");

            iv.cuadroImagen4.setIcon(null);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Identificador " + iv.txtRegistro1.getText() + " ya existe, ingrese un identificador distinto " + ex, "SofCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al introducir un valor numerico en un campo inadecuado");
        } catch (java.lang.IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error al introducir un dato incorrecto, revise todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        cargarTablaEntidadConocida1();
        vaciarTxtPantalla3();
        vaciarTxtPantalla2();
        iv.btnIzquierda1.setEnabled(false);
        iv.btnDerecha1.setEnabled(false);

    }

    public void vaciarTxtPantalla4() {
        iv.txtRegistro3.setText("");
        iv.txtNombre3.setText("");
        iv.txtAdquisicion3.setText("");
        iv.txtAutor3.setText("");
        iv.txtEstilo3.setText("");
        iv.txtFechaRealizacion3.setText("");
        iv.txtProcedencia3.setText("");
        iv.txtValoracionEconomica3.setText("");
        iv.txtMejora3.setText("");
        iv.txtRestauracion3.setText("");
        iv.txtFechaBaja3.setText("");
        iv.txtCantidad3.setText("");
        iv.txtObservaciones3.setText("");
        iv.cuadroImagen3.setIcon(null);
        iv.tblInventario3.setModel(new DefaultTableModel());
        iv.txtFiltro3.setText("");
    }

    public void vaciarTxtPantalla3() {
        iv.txtRegistro4.setText("");
        iv.txtNombre4.setText("");
        iv.txtAdquisicion4.setText("");
        iv.txtAutor4.setText("");
        iv.txtEstilo4.setText("");
        iv.txtFechaRealizacion4.setText("");
        iv.txtProcedencia4.setText("");
        iv.txtValoracionEconomica4.setText("");
        iv.txtMejora4.setText("");
        iv.txtRestauracion4.setText("");
        iv.txtFechaBaja4.setText("");
        iv.txtCantidad4.setText("");
        iv.txtObservaciones4.setText("");
        iv.cuadroImagen4.setIcon(null);
        iv.tblInventario2.setModel(new DefaultTableModel());
        iv.txtFiltro4.setText("");
    }

    public void vaciarTxtPantalla2() {
        iv.txtRegistro2.setText("");
        iv.txtNombre2.setText("");
        iv.txtAdquisicion2.setText("");
        iv.txtAutor2.setText("");
        iv.txtEstilo2.setText("");
        iv.txtFechaRealizacion2.setText("");
        iv.txtProcedencia2.setText("");
        iv.txtValoracionEconomica2.setText("");
        iv.txtMejora2.setText("");
        iv.txtRestauracion2.setText("");
        iv.txtFechaBaja2.setText("");
        iv.txtCantidad2.setText("");
        iv.txtObservaciones2.setText("");
        iv.cuadroImagen2.setIcon(null);
        iv.tblInventario2.setModel(new DefaultTableModel());

        iv.btnIzquierda1.setEnabled(false);
        iv.btnDerecha1.setEnabled(false);

    }

    public void vaciarTxtPantalla1_2() {
        iv.txtRegistro1.setText("");
        iv.txtNombre1.setText("");
        iv.txtAdquisicion1.setText("");
        iv.txtAutor1.setText("");
        iv.txtEstilo1.setText("");
        
        iv.txtProcedencia1.setText("");
        iv.txtValoracionEconomica1.setText("");
        iv.txtMejora1.setText("");
        iv.txtRestauracion1.setText("");
        
        iv.txtCantidad1.setText("");
        iv.txtObservaciones1.setText("");
        iv.cuadroImagen1.setIcon(null);

    }
    
    public void vaciarTxtPantalla1() {
        iv.txtRegistro1.setText("");
        iv.txtNombre1.setText("");
        iv.txtAdquisicion1.setText("");
        iv.txtAutor1.setText("");
        iv.txtEstilo1.setText("");
        
        iv.txtProcedencia1.setText("");
        iv.txtValoracionEconomica1.setText("");
        iv.txtMejora1.setText("");
        iv.txtRestauracion1.setText("");
        
        iv.txtCantidad1.setText("");
        iv.txtObservaciones1.setText("");
        iv.cuadroImagen1.setIcon(null);
        iv.tblInventario1.setModel(new DefaultTableModel());

    }

    public void eliminarInventarioSeleccionado() {
        int seleccion = Integer.parseInt(iv.txtRegistro3.getText());
        if (seleccion != 0) {
            try {
                eliminarInventario(seleccion);
                JOptionPane.showMessageDialog(null, "¡Producto Eliminado Correctamente!");
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ningun articulo del inventario", "Error", JOptionPane.ERROR_MESSAGE);
        }

        vaciarTxtPantalla2();
    }

    public void buscarPantalla3() {
        int contador = 1; // con este contador sacaremos el primero valor para devolverlo a la interfez (txt)
        Inventario inventario = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Autor", "Estilo", "Fecha Realizacion", "Procedencia", "Valoracion Economica", "Mejora", "Restauracion", "Cantidad", "Observaciones", "Adquisicion", "Fecha Baja"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[13];
            ArrayList<Inventario> x;
            String campo = (String) iv.combo4.getSelectedItem();
            String filtro = iv.txtFiltro4.getText();
            x = buscarInventario(filtro, campo);

            Iterator<Inventario> it = x.iterator();
            while (it.hasNext()) {
                inventario = it.next();
                fila[0] = String.valueOf(inventario.getIdentificador());
                fila[1] = inventario.getNombre();
                fila[2] = inventario.getAutor();
                fila[3] = inventario.getEstilo();
                fila[4] = String.valueOf(inventario.getFecha_realizacion());
                fila[5] = inventario.getProcedencia();
                fila[6] = String.valueOf(inventario.getValoracion_economica());
                fila[7] = inventario.getMejora();
                fila[8] = inventario.getRestauracion();
                fila[9] = String.valueOf(inventario.getCantidad());
                fila[10] = inventario.getObservaciones();
                fila[11] = inventario.getAdquisicion();
                fila[12] = String.valueOf(inventario.getFecha_baja());
                m.addRow(fila);

                // devolvemos el primer valor a la interfaz (txt)
                if (contador == 1) {

                    Image foto = null;

                    iv.txtRegistro4.setText(fila[0]);
                    iv.txtNombre4.setText(fila[1]);
                    iv.txtAutor4.setText(fila[2]);
                    iv.txtEstilo4.setText(fila[3]);
                    iv.txtFechaRealizacion4.setText(fila[4]);
                    iv.txtProcedencia4.setText(fila[5]);
                    iv.txtValoracionEconomica4.setText(fila[6]);
                    iv.txtMejora4.setText(fila[7]);
                    iv.txtRestauracion4.setText(fila[8]);
                    iv.txtCantidad4.setText(fila[9]);
                    iv.txtObservaciones4.setText(fila[10]);
                    iv.txtAdquisicion4.setText(fila[11]);
                    iv.txtFechaBaja4.setText(fila[12]);

                    foto = this.leerImagen(Integer.parseInt(fila[0]));

                    if (foto != null) {
                        iv.cuadroImagen4.setIcon(new ImageIcon(foto));
                        iv.cuadroImagen4.updateUI();
                    } else {
                        iv.cuadroImagen4.setIcon(null);
                        iv.cuadroImagen4.updateUI();
                    }
                }
                contador++;
            }

            iv.tblInventario2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            iv.tblInventario2.setRowSorter(ordenar);
            iv.tblInventario2.setModel(m);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "1Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarPantalla4() {
        Inventario inventario = null;
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Autor", "Estilo", "Fecha Realizacion", "Procedencia", "Valoracion Economica", "Mejora", "Restauracion", "Cantidad", "Observaciones", "Adquisicion", "Fecha Baja"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[13];
            ArrayList<Inventario> x;
            String campo = (String) iv.combo3.getSelectedItem();
            String filtro = iv.txtFiltro3.getText();
            x = buscarInventario(filtro, campo);

            Iterator<Inventario> it = x.iterator();
            while (it.hasNext()) {
                inventario = it.next();
                fila[0] = String.valueOf(inventario.getIdentificador());
                fila[1] = inventario.getNombre();
                fila[2] = inventario.getAutor();
                fila[3] = inventario.getEstilo();
                fila[4] = String.valueOf(inventario.getFecha_realizacion());
                fila[5] = inventario.getProcedencia();
                fila[6] = String.valueOf(inventario.getValoracion_economica());
                fila[7] = inventario.getMejora();
                fila[8] = inventario.getRestauracion();
                fila[9] = String.valueOf(inventario.getCantidad());
                fila[10] = inventario.getObservaciones();
                fila[11] = inventario.getAdquisicion();
                fila[12] = String.valueOf(inventario.getFecha_baja());
                m.addRow(fila);
            }

            iv.tblInventario3.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            iv.tblInventario3.setRowSorter(ordenar);
            iv.tblInventario3.setModel(m);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "11Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ponerPrimerInventario() {

        int identificadorMenor = 0;
        int identificadorMayor = 0;

        ////// busqueda del identificador menor en la BD ///////
        try { // busqueda del menor

            // buscamos el numero mayor de identificador que existe en la BD
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT identificador FROM inventario ORDER BY identificador ASC LIMIT 1";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                identificadorMenor = cbd.resultado.getInt(1);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        ////// busqueda del identificador mayor en la BD ///////
        try { // busqueda del mayor

            // buscamos el numero mayor de identificador que existe en la BD
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT identificador FROM inventario ORDER BY identificador DESC LIMIT 1";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                identificadorMayor = cbd.resultado.getInt(1);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        // fin de busqueda //     
        //
        if (identificadorMayor == 0 && identificadorMenor == 0) {
            iv.btnDerecha1.setEnabled(false);
            iv.btnIzquierda1.setEnabled(false);
        } else {
                //JOptionPane.showMessageDialog(null, "mayor:"+identificadorMayor+"y menor:"+identificadorMenor);

            if (identificadorMayor == identificadorMenor) {
                iv.btnIzquierda1.setEnabled(false);
                iv.btnDerecha1.setEnabled(false);
            } else {
                iv.btnIzquierda1.setEnabled(false);
                iv.btnDerecha1.setEnabled(true);
            }

        //
            ArrayList inventarioSeleccionado;
            Inventario x = null;

            try {
                inventarioSeleccionado = buscarInventario(String.valueOf(identificadorMenor), "identificador");

                Iterator<Inventario> it = inventarioSeleccionado.iterator();

                while (it.hasNext()) {
                    x = it.next();

                    iv.txtRegistro2.setText(String.valueOf(x.getIdentificador()));
                    iv.txtNombre2.setText(x.getNombre());
                    iv.txtAutor2.setText(x.getAutor());
                    iv.txtEstilo2.setText(x.getEstilo());
                    iv.txtFechaRealizacion2.setText(String.valueOf(x.getFecha_realizacion()));
                    iv.txtProcedencia2.setText(x.getProcedencia());
                    iv.txtValoracionEconomica2.setText(String.valueOf(x.getValoracion_economica()));
                    iv.txtMejora2.setText(x.getMejora());
                    iv.txtRestauracion2.setText(x.getRestauracion());
                    iv.txtCantidad2.setText(String.valueOf(x.getCantidad()));
                    iv.txtObservaciones2.setText(x.getObservaciones());
                    iv.txtAdquisicion2.setText(x.getAdquisicion());
                    iv.txtFechaBaja2.setText(String.valueOf(x.getFecha_baja()));
                }

                Image foto = null;
                foto = leerImagen(Integer.parseInt(iv.txtRegistro2.getText()));
                if (foto != null) {
                    iv.cuadroImagen2.setIcon(new ImageIcon(foto));
                    salto = false;
                } else {
                    iv.cuadroImagen2.setIcon(null);
                }

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public void btnizq1() {

        int identificadorMenor = 0;

        ////// busqueda del identificador menor en la BD ///////
        try { // busqueda del menor

            // buscamos el numero menor de identificador que existe en la BD
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT identificador FROM inventario ORDER BY identificador ASC LIMIT 1";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                identificadorMenor = cbd.resultado.getInt(1);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        // fin de busqueda //
        salto = false;

        ArrayList inventarioSeleccionado;
        Inventario x = null;

        try {
            inventarioSeleccionado = buscarInventario(String.valueOf(contador - 1), "identificador");

            if (inventarioSeleccionado.size() > 0) {  // si el arraylist devuelve algun dato entonces..

                if ((contador - 1) == identificadorMenor) {  // si el identificador es 1 el boton izquierdo sera desactivado
                    iv.btnIzquierda1.setEnabled(false);
                    iv.btnDerecha1.setEnabled(true);

                    Iterator<Inventario> it = inventarioSeleccionado.iterator();

                    while (it.hasNext()) {
                        x = it.next();

                        iv.txtRegistro2.setText(String.valueOf(x.getIdentificador()));
                        iv.txtNombre2.setText(x.getNombre());
                        iv.txtAutor2.setText(x.getAutor());
                        iv.txtEstilo2.setText(x.getEstilo());
                        iv.txtFechaRealizacion2.setText(String.valueOf(x.getFecha_realizacion()));
                        iv.txtProcedencia2.setText(x.getProcedencia());
                        iv.txtValoracionEconomica2.setText(String.valueOf(x.getValoracion_economica()));
                        iv.txtMejora2.setText(x.getMejora());
                        iv.txtRestauracion2.setText(x.getRestauracion());
                        iv.txtCantidad2.setText(String.valueOf(x.getCantidad()));
                        iv.txtObservaciones2.setText(x.getObservaciones());
                        iv.txtAdquisicion2.setText(x.getAdquisicion());
                        iv.txtFechaBaja2.setText(String.valueOf(x.getFecha_baja()));
                    }

                    Image foto = null;
                    foto = leerImagen(Integer.parseInt(iv.txtRegistro2.getText()));
                    if (foto != null) {
                        iv.cuadroImagen2.setIcon(new ImageIcon(foto));
                        salto = false;
                    } else {
                        iv.cuadroImagen2.setIcon(null);
                    }

                } else {
                    //JOptionPane.showMessageDialog(null, "CONTADOR: " + contador);
                    iv.btnIzquierda1.setEnabled(true);
                    iv.btnDerecha1.setEnabled(true);

                    Iterator<Inventario> it = inventarioSeleccionado.iterator();

                    while (it.hasNext()) {
                        x = it.next();

                        iv.txtRegistro2.setText(String.valueOf(x.getIdentificador()));
                        iv.txtNombre2.setText(x.getNombre());
                        iv.txtAutor2.setText(x.getAutor());
                        iv.txtEstilo2.setText(x.getEstilo());
                        iv.txtFechaRealizacion2.setText(String.valueOf(x.getFecha_realizacion()));
                        iv.txtProcedencia2.setText(x.getProcedencia());
                        iv.txtValoracionEconomica2.setText(String.valueOf(x.getValoracion_economica()));
                        iv.txtMejora2.setText(x.getMejora());
                        iv.txtRestauracion2.setText(x.getRestauracion());
                        iv.txtCantidad2.setText(String.valueOf(x.getCantidad()));
                        iv.txtObservaciones2.setText(x.getObservaciones());
                        iv.txtAdquisicion2.setText(x.getAdquisicion());
                        iv.txtFechaBaja2.setText(String.valueOf(x.getFecha_baja()));
                    }

                    Image foto = null;
                    foto = leerImagen(Integer.parseInt(iv.txtRegistro2.getText()));
                    if (foto != null) {
                        iv.cuadroImagen2.setIcon(new ImageIcon(foto));
                        salto = false;
                    } else {
                        iv.cuadroImagen2.setIcon(null);
                        salto = false;
                    }

                }

            } else {
                //JOptionPane.showMessageDialog(null, "no contiene ningun valor: "+(contador-1));
                salto = true;
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void btndcha1() {

        int identificadorMayor = 0;
        int identificadorMenor = 0;

        try {

            // buscamos el numero mayor de identificador que existe en la BD
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT identificador FROM inventario ORDER BY identificador DESC LIMIT 1";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                identificadorMayor = cbd.resultado.getInt(1);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        try {

            // buscamos el numero mayor de identificador que existe en la BD
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT identificador FROM inventario ORDER BY identificador ASC LIMIT 1";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                identificadorMenor = cbd.resultado.getInt(1);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        // fin de busqueda
        salto = false;

        ArrayList inventarioSeleccionado;
        Inventario x = null;

        try {
            inventarioSeleccionado = buscarInventario(String.valueOf(contador + 1), "identificador");

            if (inventarioSeleccionado.size() > 0) { // si contiene algun valor el array

                if ((contador + 1) == identificadorMayor) {

                    iv.btnIzquierda1.setEnabled(true);
                    iv.btnDerecha1.setEnabled(false);

                    Iterator<Inventario> it = inventarioSeleccionado.iterator();

                    while (it.hasNext()) {
                        x = it.next();

                        iv.txtRegistro2.setText(String.valueOf(x.getIdentificador()));
                        iv.txtNombre2.setText(x.getNombre());
                        iv.txtAutor2.setText(x.getAutor());
                        iv.txtEstilo2.setText(x.getEstilo());
                        iv.txtFechaRealizacion2.setText(String.valueOf(x.getFecha_realizacion()));
                        iv.txtProcedencia2.setText(x.getProcedencia());
                        iv.txtValoracionEconomica2.setText(String.valueOf(x.getValoracion_economica()));
                        iv.txtMejora2.setText(x.getMejora());
                        iv.txtRestauracion2.setText(x.getRestauracion());
                        iv.txtCantidad2.setText(String.valueOf(x.getCantidad()));
                        iv.txtObservaciones2.setText(x.getObservaciones());
                        iv.txtAdquisicion2.setText(x.getAdquisicion());
                        iv.txtFechaBaja2.setText(String.valueOf(x.getFecha_baja()));
                    }

                    Image foto;
                    foto = leerImagen(Integer.parseInt(iv.txtRegistro2.getText()));
                    if (foto != null) {
                        iv.cuadroImagen2.setIcon(new ImageIcon(foto));
                    } else {
                        iv.cuadroImagen2.setIcon(null);
                        //JOptionPane.showMessageDialog(null, "imagen vacia");
                    }

                } else { // sino todo segira normalmente

                    iv.btnIzquierda1.setEnabled(true);
                    iv.btnDerecha1.setEnabled(true);

                    Iterator<Inventario> it = inventarioSeleccionado.iterator();

                    while (it.hasNext()) {
                        x = it.next();

                        iv.txtRegistro2.setText(String.valueOf(x.getIdentificador()));
                        iv.txtNombre2.setText(x.getNombre());
                        iv.txtAutor2.setText(x.getAutor());
                        iv.txtEstilo2.setText(x.getEstilo());
                        iv.txtFechaRealizacion2.setText(String.valueOf(x.getFecha_realizacion()));
                        iv.txtProcedencia2.setText(x.getProcedencia());
                        iv.txtValoracionEconomica2.setText(String.valueOf(x.getValoracion_economica()));
                        iv.txtMejora2.setText(x.getMejora());
                        iv.txtRestauracion2.setText(x.getRestauracion());
                        iv.txtCantidad2.setText(String.valueOf(x.getCantidad()));
                        iv.txtObservaciones2.setText(x.getObservaciones());
                        iv.txtAdquisicion2.setText(x.getAdquisicion());
                        iv.txtFechaBaja2.setText(String.valueOf(x.getFecha_baja()));
                    }

                    Image foto;
                    foto = leerImagen(Integer.parseInt(iv.txtRegistro2.getText()));
                    if (foto != null) {
                        iv.cuadroImagen2.setIcon(new ImageIcon(foto));
                    } else {
                        iv.cuadroImagen2.setIcon(null);
                        //JOptionPane.showMessageDialog(null, "imagen vacia");
                    }

                }

            } else {
                //JOptionPane.showMessageDialog(null, "no contiene ningun valor: "+(contador+1));
                salto = true;
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void tblInventario1MousePressed(java.awt.event.MouseEvent evt) {

        int clic = iv.tblInventario1.getSelectedRow();
        iv.txtRegistro2.setText((String) iv.tblInventario1.getValueAt(clic, 0));
        iv.txtNombre2.setText((String) iv.tblInventario1.getValueAt(clic, 1));
        iv.txtAutor2.setText((String) iv.tblInventario1.getValueAt(clic, 2));
        iv.txtEstilo2.setText((String) iv.tblInventario1.getValueAt(clic, 3));
        iv.txtFechaRealizacion2.setText((String) iv.tblInventario1.getValueAt(clic, 4));
        iv.txtProcedencia2.setText((String) iv.tblInventario1.getValueAt(clic, 5));
        iv.txtValoracionEconomica2.setText((String) iv.tblInventario1.getValueAt(clic, 6));
        iv.txtMejora2.setText((String) iv.tblInventario1.getValueAt(clic, 7));
        iv.txtRestauracion2.setText((String) iv.tblInventario1.getValueAt(clic, 8));
        iv.txtCantidad2.setText((String) iv.tblInventario1.getValueAt(clic, 9));
        iv.txtObservaciones2.setText((String) iv.tblInventario1.getValueAt(clic, 10));
        iv.txtAdquisicion2.setText((String) iv.tblInventario1.getValueAt(clic, 11));
        iv.txtFechaBaja2.setText((String) iv.tblInventario1.getValueAt(clic, 12));

        Image foto;
        foto = leerImagen(Integer.parseInt(iv.txtRegistro2.getText()));
        if (foto != null) {
            iv.cuadroImagen2.setIcon(new ImageIcon(foto));
        } else {
            iv.cuadroImagen2.setIcon(null);
            //JOptionPane.showMessageDialog(null, "imagen vacia");
        }

        contador = Integer.parseInt(iv.txtRegistro2.getText());

        ///////////////////////////////////////////////////////////
        int identificadorMayor = 0;
        int identificadorMenor = 0;

        try { // busqueda del mayor

            // buscamos el numero mayor de identificador que existe en la BD
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT identificador FROM inventario ORDER BY identificador DESC LIMIT 1";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                identificadorMayor = cbd.resultado.getInt(1);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        try { // busqueda del menor

            // buscamos el numero menor de identificador que existe en la BD
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT identificador FROM inventario ORDER BY identificador ASC LIMIT 1";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                identificadorMenor = cbd.resultado.getInt(1);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        // fin de busqueda
        if (identificadorMayor == identificadorMenor) {
            iv.btnDerecha1.setEnabled(false);
            iv.btnIzquierda1.setEnabled(false);
        } else if (iv.txtRegistro2.getText().equalsIgnoreCase(String.valueOf(identificadorMenor))) {
            iv.btnDerecha1.setEnabled(true);
            iv.btnIzquierda1.setEnabled(false);
        } else if (iv.txtRegistro2.getText().equalsIgnoreCase(String.valueOf(identificadorMayor))) {
            iv.btnDerecha1.setEnabled(false);
            iv.btnIzquierda1.setEnabled(true);
        } else {
            iv.btnDerecha1.setEnabled(true);
            iv.btnIzquierda1.setEnabled(true);
        }

    }

    private void tblInventario2MousePressed(java.awt.event.MouseEvent evt) {

        int clic = iv.tblInventario2.getSelectedRow();
        iv.txtRegistro4.setText((String) iv.tblInventario2.getValueAt(clic, 0));
        iv.txtNombre4.setText((String) iv.tblInventario2.getValueAt(clic, 1));
        iv.txtAutor4.setText((String) iv.tblInventario2.getValueAt(clic, 2));
        iv.txtEstilo4.setText((String) iv.tblInventario2.getValueAt(clic, 3));
        iv.txtFechaRealizacion4.setText((String) iv.tblInventario2.getValueAt(clic, 4));
        iv.txtProcedencia4.setText((String) iv.tblInventario2.getValueAt(clic, 5));
        iv.txtValoracionEconomica4.setText((String) iv.tblInventario2.getValueAt(clic, 6));
        iv.txtMejora4.setText((String) iv.tblInventario2.getValueAt(clic, 7));
        iv.txtRestauracion4.setText((String) iv.tblInventario2.getValueAt(clic, 8));
        iv.txtCantidad4.setText((String) iv.tblInventario2.getValueAt(clic, 9));
        iv.txtObservaciones4.setText((String) iv.tblInventario2.getValueAt(clic, 10));
        iv.txtAdquisicion4.setText((String) iv.tblInventario2.getValueAt(clic, 11));
        iv.txtFechaBaja4.setText((String) iv.tblInventario2.getValueAt(clic, 12));

        Image foto = null;
        foto = leerImagen(Integer.parseInt(iv.txtRegistro4.getText()));

        if (foto != null) {
            iv.cuadroImagen4.setIcon(new ImageIcon(foto));
        } else {
            iv.cuadroImagen4.setIcon(null);
        }

        contador = Integer.parseInt(iv.txtRegistro4.getText());

    }

    private void tblInventario3MousePressed(java.awt.event.MouseEvent evt) {

        int clic = iv.tblInventario3.getSelectedRow();
        iv.txtRegistro3.setText((String) iv.tblInventario3.getValueAt(clic, 0));
        iv.txtNombre3.setText((String) iv.tblInventario3.getValueAt(clic, 1));
        iv.txtAutor3.setText((String) iv.tblInventario3.getValueAt(clic, 2));
        iv.txtEstilo3.setText((String) iv.tblInventario3.getValueAt(clic, 3));
        iv.txtFechaRealizacion3.setText((String) iv.tblInventario3.getValueAt(clic, 4));
        iv.txtProcedencia3.setText((String) iv.tblInventario3.getValueAt(clic, 5));
        iv.txtValoracionEconomica3.setText((String) iv.tblInventario3.getValueAt(clic, 6));
        iv.txtMejora3.setText((String) iv.tblInventario3.getValueAt(clic, 7));
        iv.txtRestauracion3.setText((String) iv.tblInventario3.getValueAt(clic, 8));
        iv.txtCantidad3.setText((String) iv.tblInventario3.getValueAt(clic, 9));
        iv.txtObservaciones3.setText((String) iv.tblInventario3.getValueAt(clic, 10));
        iv.txtAdquisicion3.setText((String) iv.tblInventario3.getValueAt(clic, 11));
        iv.txtFechaBaja3.setText((String) iv.tblInventario3.getValueAt(clic, 12));

        Image foto;
        foto = leerImagen(Integer.parseInt(iv.txtRegistro3.getText()));
        iv.cuadroImagen3.setIcon(new ImageIcon(foto));

        contador = Integer.parseInt(iv.txtRegistro3.getText());

    }

    // metodo para leer una imagen en binario desde la base de datos
    // esta imagen se reajustará al tamaño del texfield automaticamente
    public Image leerImagen(int id) {

        Image newimg = null;

        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT imagen FROM inventario WHERE identificador=" + id;

            Image recuperada = null;

            try {
                cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
                Blob imagen = null;
                while (cbd.resultado.next()) {
                    imagen = cbd.resultado.getBlob("imagen");

                    if (imagen != null) {
                        try {
                            recuperada = javax.imageio.ImageIO.read(imagen.getBinaryStream());
                            newimg = recuperada.getScaledInstance(iv.cuadroImagen1.getWidth(), iv.cuadroImagen1.getHeight(), Image.SCALE_DEFAULT);

                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        //JOptionPane.showMessageDialog(null, "no hay imagen");
                    }

                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return newimg;
    }

    public void cargarTablaEntidadConocida1() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Autor", "Estilo", "Fecha Realizacion", "Procedencia", "Valoracion Economica", "Mejora", "Restauracion", "Cantidad", "Observaciones", "Adquisicion", "Fecha Baja"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[13];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from inventario";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("nombre");
                fila[2] = cbd.resultado.getString("autor");
                fila[3] = cbd.resultado.getString("estilo");
                fila[4] = cbd.resultado.getString("fecha_realizacion");
                fila[5] = cbd.resultado.getString("procedencia");
                fila[6] = cbd.resultado.getString("valoracion_economica");
                fila[7] = cbd.resultado.getString("mejora");
                fila[8] = cbd.resultado.getString("restauracion");
                fila[9] = cbd.resultado.getString("cantidad");
                fila[10] = cbd.resultado.getString("observaciones");
                fila[11] = cbd.resultado.getString("adquisicion");
                fila[12] = cbd.resultado.getString("fecha_baja");
                m.addRow(fila);
            }

            iv.tblInventario1.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            iv.tblInventario1.setRowSorter(ordenar);
            iv.tblInventario1.setModel(m);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla" + e, "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void cargarTablaEntidadConocida2() {
        DefaultTableModel m;
        try {
            String[] titulo = {"Nro", "Nombre", "Autor", "Estilo", "Fecha Realizacion", "Procedencia", "Valoracion Economica", "Mejora", "Restauracion", "Cantidad", "Observaciones", "Adquisicion", "Fecha Baja"};
            m = new DefaultTableModel(null, titulo);
            JTable p = new JTable(m);
            String[] fila = new String[9];
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select * from inventario";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                fila[0] = cbd.resultado.getString("identificador");
                fila[1] = cbd.resultado.getString("nombre");
                fila[2] = cbd.resultado.getString("autor");
                fila[3] = cbd.resultado.getString("estilo");
                fila[4] = cbd.resultado.getString("fecha_realizacion");
                fila[5] = cbd.resultado.getString("procedencia");
                fila[6] = cbd.resultado.getString("valoracion_economica");
                fila[7] = cbd.resultado.getString("mejora");
                fila[8] = cbd.resultado.getString("restauracion");
                fila[8] = cbd.resultado.getString("cantidad");
                fila[8] = cbd.resultado.getString("observaciones");
                fila[8] = cbd.resultado.getString("adquisicion");
                fila[8] = cbd.resultado.getString("fecha_baja");
                m.addRow(fila);
            }

            iv.tblInventario2.setModel(m);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(m);
            iv.tblInventario2.setRowSorter(ordenar);
            iv.tblInventario2.setModel(m);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void examinar1() {
        iv.cuadroImagen1.setIcon(null);
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.FILES_ONLY); //Solo arhivos y no carpetas
        int estado = j.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                fis = new FileInputStream(j.getSelectedFile());
                // Necesitamos saber la cantidad de Bytes
                this.longitudBytes = (int) j.getSelectedFile().length();

                try {
                    Image icono = ImageIO.read(j.getSelectedFile()).getScaledInstance(iv.cuadroImagen1.getWidth(), iv.cuadroImagen1.getHeight(), Image.SCALE_DEFAULT);
                    iv.cuadroImagen1.setIcon(new ImageIcon(icono));
                    iv.cuadroImagen1.updateUI();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "imagen: " + ex);
                } catch (java.lang.NullPointerException e) {
                    JOptionPane.showMessageDialog(null, "Error: Fichero no admitido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "imagen: " + ex);
            }
        }
    }

    public void examinar2() {
        iv.cuadroImagen4.setIcon(null);
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.FILES_ONLY); //Solo arhivos y no carpetas
        int estado = j.showOpenDialog(null);
        if (estado == JFileChooser.APPROVE_OPTION) {
            try {
                fis = new FileInputStream(j.getSelectedFile());
                // Necesitamos saber la cantidad de Bytes
                this.longitudBytes = (int) j.getSelectedFile().length();

                try {
                    Image icono = ImageIO.read(j.getSelectedFile()).getScaledInstance(iv.cuadroImagen4.getWidth(), iv.cuadroImagen4.getHeight(), Image.SCALE_DEFAULT);
                    iv.cuadroImagen4.setIcon(new ImageIcon(icono));
                    iv.cuadroImagen4.updateUI();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "imagen: " + ex);
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "imagen: " + ex);
            }
        }
    }

    public void insertarPantalla1() {
        String identificador = iv.txtRegistro1.getText();
        String nombre = iv.txtNombre1.getText();
        String autor = iv.txtAutor1.getText();
        String estilo = iv.txtEstilo1.getText();
        String fecha_realizacion = null;
        
        if (iv.jDate1!=null) {
           // Trabajando con fecha
        int año = iv.jDate1.getCalendar().get(Calendar.YEAR);
        int mes = iv.jDate1.getCalendar().get(Calendar.MONTH);
        int dia = iv.jDate1.getCalendar().get(Calendar.DAY_OF_MONTH);
        fecha_realizacion = año+"-"+mes+"-"+dia;
        // 
        } 
        
        String procedencia = iv.txtProcedencia1.getText();
        String valoracion_economica = iv.txtValoracionEconomica1.getText();
        String mejora = iv.txtMejora1.getText();
        String restauracion = iv.txtRestauracion1.getText();
        String cantidad = iv.txtCantidad1.getText();
        String observaciones = iv.txtObservaciones1.getText();
        String adquisicion = iv.txtAdquisicion1.getText();
        String fecha_baja = null;
        
       if (iv.jDate2!=null) {
           // Trabajando con fecha
        int año2 = iv.jDate2.getCalendar().get(Calendar.YEAR);
        int mes2 = iv.jDate2.getCalendar().get(Calendar.MONTH);
        int dia2 = iv.jDate2.getCalendar().get(Calendar.DAY_OF_MONTH);
        fecha_baja = año2+"-"+mes2+"-"+dia2;        
       }       

        try {
            if (iv.jDate1!=null && iv.jDate2==null) {

                agregarInventario(Integer.parseInt(identificador), nombre, autor, estilo, fecha_realizacion, procedencia, Double.parseDouble(valoracion_economica), mejora, restauracion, Integer.parseInt(cantidad), observaciones, adquisicion, this.fis, null, this.longitudBytes);
                JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!");

            } else if (iv.jDate1==null) {
                
                JOptionPane.showMessageDialog(null, "El campo Fecha Realizacion es Obligatorio");
                
            } else {
                
                agregarInventario(Integer.parseInt(identificador), nombre, autor, estilo, fecha_realizacion, procedencia, Double.parseDouble(valoracion_economica), mejora, restauracion, Integer.parseInt(cantidad), observaciones, adquisicion, this.fis, fecha_baja, this.longitudBytes);
                JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!");
            }

            iv.cuadroImagen1.setIcon(null);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "El Identificador " + iv.txtRegistro1.getText() + " ya existe, ingrese un identificador distinto " + ex, "SofCofradias", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error al introducir un valor numerico en un campo inadecuado");
        } catch (java.lang.IllegalArgumentException e) {
            JOptionPane.showMessageDialog(null, "Error: Parametros incorrectos introducidos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    /*public void modificarPantalla2() {
     String identificador = iv.txtRegistro1.getText();
     String nombre = iv.txtNombre1.getText();
     String autor = iv.txtAutor1.getText();
     String estilo = iv.txtEstilo1.getText();
     String fecha_realizacion = iv.txtFechaRealizacion1.getText();
     String procedencia = iv.txtProcedencia1.getText();
     String valoracion_economica = iv.txtValoracionEconomica1.getText();
     String mejora = iv.txtMejora1.getText();
     String restauracion = iv.txtRestauracion1.getText();
     String cantidad = iv.txtCantidad1.getText();
     String observaciones = iv.txtObservaciones1.getText();
     String adquisicion = iv.txtAdquisicion1.getText();
     String fecha_baja = iv.txtFechaBaja1.getText();

     try {
     agregarInventario(Integer.parseInt(identificador), nombre, autor, estilo, Date.valueOf(fecha_realizacion), procedencia, Double.parseDouble(valoracion_economica), mejora, restauracion, Integer.parseInt(cantidad), observaciones, adquisicion, this.fis, Date.valueOf(fecha_baja), this.longitudBytes);
     JOptionPane.showMessageDialog(null, "¡Insertado Correctamente!");

     iv.cuadroImagen1.setIcon(null);
     } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
     JOptionPane.showMessageDialog(null, "El Identificador " + iv.txtRegistro1.getText() + " ya existe, ingrese un identificador distinto " + ex, "SofCofradias", JOptionPane.ERROR_MESSAGE);
     }
     }*/
    public int buscarIdentificadorMinimo() {
        ////// busqueda del identificador menor en la BD ///////
        int identificadorMenor = 0;
        try { // busqueda del menor

            // buscamos el numero mayor de identificador que existe en la BD
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "SELECT identificador FROM inventario ORDER BY identificador ASC LIMIT 1";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

            while (cbd.resultado.next()) {
                identificadorMenor = cbd.resultado.getInt(1);
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        // fin de busqueda //
        return identificadorMenor;
    }

    /* Método para buscar un Inventario indicando el campo y el valor */
    public ArrayList<Inventario> buscarInventario(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        i = new Inventario();
        return i.buscarFiltro(filtro, campo);
    }

    /*Metodo para agregar un Inventario*/
    public void agregarInventario(int identificador, String nombre, String autor, String estilo, String fecha_realizacion, String procedencia, Double valoracion_economica, String mejora, String restauracion, int cantidad, String observaciones, String adquisicion, FileInputStream imagen, String fecha_baja, int longitudBytes) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        i = new Inventario(identificador, nombre, autor, estilo, fecha_realizacion, procedencia, valoracion_economica, mejora, restauracion, cantidad, observaciones, adquisicion, imagen, fecha_baja, longitudBytes);
        i.grabar();
    }

    /*Metodo para modificar una Entidad Conocida*/
    public void modificarInventario(int identificador, String nombre, String autor, String estilo, String fecha_realizacion, String procedencia, Double valoracion_economica, String mejora, String restauracion, int cantidad, String observaciones, String adquisicion, FileInputStream imagen, String fecha_baja, int longitudBytes) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        i = new Inventario(identificador, nombre, autor, estilo, fecha_realizacion, procedencia, valoracion_economica, mejora, restauracion, cantidad, observaciones, adquisicion, imagen, fecha_baja, longitudBytes);
        i.actualizar();
    }

    /*Metodo para borrar una Entidad Conocida*/
    public void eliminarInventario(int identificador) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        i = new Inventario(identificador);
        i.borrar();
    }

    /*Metodo para leer todas las Entidades Conocidas*/
    public ArrayList<Inventario> leerTodas() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        i = new Inventario();
        return i.leerTodos();
    }

}
