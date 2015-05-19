package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.Protocolo;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.HermanitoVista1;
import vista.ProtocoloVista;

public class ControladorProtocolo implements ActionListener {

    Protocolo p;
    ProtocoloVista pv1;
    String numero;
    int id;

    public enum di {

        ACEPTAR, SALIR, SELECCION1, SELECCION2;
    }

    public void iniciar() {

        p = new Protocolo();
        pv1 = new ProtocoloVista();
        pv1.setVisible(true);
        pv1.setLocationRelativeTo(null);

        pv1.Aceptar.setActionCommand("ACEPTAR");
        pv1.Aceptar.addActionListener(this);
        pv1.Salir.setActionCommand("SALIR");
        pv1.Salir.addActionListener(this);
        //seleccion();
        Seleccion_salida();
       //pv1.hermano_seleccion.setActionCommand("SELECCION1");
        //pv1.hermano_seleccion.addActionListener(this);

       //cargar_seleccion();
    }

    public void agregarDatos(int identificador, String descripcion, int numero_hermano_id, int salida_profesional_id) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        p = new Protocolo(identificador, descripcion, numero_hermano_id, salida_profesional_id);
        p.guardar();

    }
       ///METODO PARA SELECCIONAR EL HERMANO QUE QUEREMOS INTRODUCIR

    /*public void seleccion(){
         
     try{
     Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
     cbd.un_sql="select nombre,apellidos from hermanos;";
     cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
     pv1.hermano_seleccion.removeAllItems();
              
     while(cbd.resultado.next()){
                
     String nombre=cbd.resultado.getString("nombre");
     String apellidos=cbd.resultado.getString("apellidos");
     pv1.hermano_seleccion.addItem(apellidos); 
     //pv1.hermano_seleccion.addItem();
  
     }                      
           
     } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
     Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
     } 
         
         
     }*/
         //METODO PARA CARGAR ESA SELECCION
    /* public void cargar_seleccion(){
         
     try{
     Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
     cbd.un_sql="select numero_hermano from hermanos where apellidos='"+pv1.hermano_seleccion.getSelectedItem()+"';";
     cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
           
              
     if(cbd.resultado.next()){
     numero = cbd.resultado.getString("numero_hermano");
     pv1.id_muestra.setText(""+numero);
  
     }                      
           
     } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
     Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
     } 
         
         
     }*/
    //metodo para sacar la salida procesional
    public void Seleccion_salida() {

        try {
            Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
            cbd.un_sql = "select s.descripcion_salida_procesional from salidaprocesional";
            cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);
            pv1.Seleccion_salida.removeAllItems();

            while (cbd.resultado.next()) {
                String salida = cbd.resultado.getString("identificador");
                pv1.Seleccion_salida.addItem(salida);

            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            Logger.getLogger(ControladorLibroAsiento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void actionPerformed(ActionEvent e) {

        switch (ControladorProtocolo.di.valueOf(e.getActionCommand())) {

            case ACEPTAR:
                aceptar();

                break;

            case SELECCION1:

                //cargar_seleccion();
                pv1.id_muestra.getText();
                break;

        }

    }
    
    public void aceptar() {
        try {

                    Conexion cbd = ConectarServicio.getInstancia().getConexionDb();
                    cbd.un_sql = "select max(identificador) from protocolo";
                    cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

                    int identificador = 0; //= Integer.parseInt(lv1.ident.getText());

                    if (cbd.resultado.next()) {

                        identificador = cbd.resultado.getInt(1) + 1;
                    } else {

                        identificador = 1;

                    }

                    String descripcion = pv1.area_de_texto.getText();
                    String numero_hermano_id = pv1.id_muestra.getText();

                    String salida_profesional_id = (String) pv1.Seleccion_salida.getSelectedItem();

                    try {
                        agregarDatos(identificador, descripcion, Integer.parseInt(numero_hermano_id), Integer.parseInt(salida_profesional_id));
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

}
