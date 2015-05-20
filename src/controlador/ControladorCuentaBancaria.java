/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import modelo.CuentaBancaria;
import modelo.hermanito;
import servicios.ConectarServicio;
import servicios.Conexion;
import vista.CuentaBancariaVista;

/**
 *
 * @author alex
 */
public class ControladorCuentaBancaria implements ActionListener {
    
    CuentaBancaria c1;
    CuentaBancariaVista cv1;
    DefaultTableModel c11;

   
   
    
       public enum di {

        GUARDAR,GUARDAR1,SALIR,SALIR1,MODIFICAR;
    }
    
    
    public void iniciar(){
    
    c1 = new CuentaBancaria();
    cv1 = new CuentaBancariaVista();
    cv1.setVisible(true);
    cv1.setLocationRelativeTo(null);
    
    cv1.aceptar.setActionCommand("GUARDAR");
    cv1.aceptar.addActionListener(this);
    cv1.aceptar1.setActionCommand("GUARDAR1");
    cv1.aceptar1.addActionListener(this);
    
    cv1.modificar.setActionCommand("MODIFICAR");
    cv1.modificar.addActionListener(this);
    
    
    cv1.cancelar.setActionCommand("SALIR");
    cv1.cancelar.addActionListener(this);
    cv1.cancelar1.setActionCommand("SALIR1");
    cv1.cancelar1.addActionListener(this);
    
    cargar_tabla_Bancaria();
    }
    
     public void actionPerformed(ActionEvent e) {
      
      switch (ControladorCuentaBancaria.di.valueOf(e.getActionCommand())) {
     
          case GUARDAR:          
              guardarDatos();
          break;
              
              
          case GUARDAR1:          
              guardarDatoscadosModif();
          break;    
          
          case SALIR:
              cv1.dispose();
              break;
              
           case SALIR1:
              cv1.dispose();
              break;   
           
          
           case MODIFICAR:
               
               cv1.modificacion.setSize(650,625);
               cv1.modificacion.setLocation(100,100);
            cv1.modificacion.setVisible(true);
                int clic = cv1.tabla_banco.getSelectedRow(); // se guarda en la variable el numero de la fila cuando se hace click en una

           
        if (clic!=-1) {
            cv1.id1.setEnabled(false);
            cv1.id1.setText(cv1.tabla_banco.getValueAt(clic, 0).toString());
            cv1.descripc1.setText(cv1.tabla_banco.getValueAt(clic, 1).toString());
            cv1.titulo_cuenta1.setText(cv1.tabla_banco.getValueAt(clic, 2).toString());
            cv1.Nombre_banco1.setText(cv1.tabla_banco.getValueAt(clic, 3).toString());
            cv1.dir1.setText(cv1.tabla_banco.getValueAt(clic, 4).toString());
            cv1.municip1.setText(cv1.tabla_banco.getValueAt(clic, 5).toString());
            cv1.postal1.setText(cv1.tabla_banco.getValueAt(clic, 6).toString());
            cv1.provin1.setText(cv1.tabla_banco.getValueAt(clic, 7).toString());
            cv1.pais1.setText(cv1.tabla_banco.getValueAt(clic, 8).toString());
            cv1.numero_de_Cuenta1.setText(cv1.tabla_banco.getValueAt(clic, 9).toString());
            cv1.iban_banco1.setText(cv1.tabla_banco.getValueAt(clic, 10).toString());
            cv1.bic_banco1.setText(cv1.tabla_banco.getValueAt(clic, 11).toString());
             cv1.saldo1.setText(cv1.tabla_banco.getValueAt(clic, 12).toString());
           
            }       
               break;
     }
    
}
     
     public void guardarDatos(){
     
     int identificador = Integer.parseInt(cv1.id.getText());
     String descripcion=cv1.descripc.getText();
     String titulo=cv1.titulo_cuenta.getText();
     String banco=cv1.Nombre_banco.getText();
     String direccion=cv1.dir.getText();
     String municipio=cv1.municip.getText();
     String codigo_postal=cv1.postal.getText();
     String provincia=cv1.provin.getText();
     String pais=cv1.pais.getText();
    String num_cuenta=cv1.numero_de_Cuenta.getText();
     String iban=cv1.iban_banco.getText();
     String bic=cv1.bic_banco.getText();
     int cantidad=Integer.parseInt(cv1.saldo.getText());

         
     try{
     
         agregarCuenta(identificador, descripcion, titulo, banco, direccion, municipio, codigo_postal, provincia, pais, num_cuenta,iban, bic, cantidad);
     
     }  catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ControladorCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ControladorCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      cv1.dispose();
     
     }
     
      public void guardarDatoscadosModif(){
     
     int identificador1 = Integer.parseInt(cv1.id1.getText());
     String descripcion1=cv1.descripc1.getText();
     String titulo1=cv1.titulo_cuenta1.getText();
     String banco1=cv1.Nombre_banco1.getText();
     String direccion1=cv1.dir1.getText();
     String municipio1=cv1.municip1.getText();
     String codigo_postal1=cv1.postal1.getText();
     String provincia1=cv1.provin1.getText();
     String pais1=cv1.pais1.getText();
    String num_cuenta1=cv1.numero_de_Cuenta1.getText();
     String iban1=cv1.iban_banco1.getText();
     String bic1=cv1.bic_banco1.getText();
     int cantidad1=Integer.parseInt(cv1.saldo1.getText());

         
     try{
     
         modificarCuenta(identificador1, descripcion1, titulo1, banco1, direccion1, municipio1, codigo_postal1, provincia1, pais1, num_cuenta1,iban1, bic1, cantidad1);
     
     }  catch (ClassNotFoundException ex) {
            Logger.getLogger(ControladorCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ControladorCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ControladorCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControladorCuentaBancaria.class.getName()).log(Level.SEVERE, null, ex);
        }
     
      cv1.dispose();
     
     }
     
     
     
      public void cargar_tabla_Bancaria(){
     
        DefaultTableModel ff;
        int clic = cv1.tabla_banco.getSelectedRow();
        
        try {
            String titulo[] = {"identificador","titulo","Banco","Descripcion","direccion","municipio","CP","Provincia","Pais","Numero de cuenta","iban","bic","cantidad"};
            ff = new DefaultTableModel(null, titulo);
            JTable p = new JTable(ff);
            String fila[] = new String[13];
            Conexion cdb = ConectarServicio.getInstancia().getConexionDb();
            cdb.un_sql = "select * from cuentabancaria" ;
            cdb.resultado = cdb.un_st.executeQuery(cdb.un_sql);

            while (cdb.resultado.next()) {
                    
              fila[0] = cdb.resultado.getString(1);
               
                fila[1]=cdb.resultado.getString(2);
                fila[2] = cdb.resultado.getString(3);
                fila[3] = cdb.resultado.getString(4);
                fila[4] = cdb.resultado.getString(5);
                fila[5] = cdb.resultado.getString(6);
                fila[6] = cdb.resultado.getString(7); 
                fila[7]=cdb.resultado.getString(8);
                fila[8] = cdb.resultado.getString(9);
                fila[9] = cdb.resultado.getString(10);
                fila[10] = cdb.resultado.getString(11);
                fila[11] = cdb.resultado.getString(12);
                fila[12] = cdb.resultado.getString(13);
                
                   ff.addRow(fila);
                
            }

           cv1.tabla_banco.setModel(ff);
            TableRowSorter<TableModel> ordenar = new TableRowSorter<>(ff);
           cv1.tabla_banco.setRowSorter(ordenar);
           cv1.tabla_banco.setModel(ff);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al extraer los datos de la tabla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
     
     //metodo para insertar datos de la nueva cuenta bancaria
       public void agregarCuenta(int identificador, String descripcion, String titulo, String banco, String direccion, String municipio, String cod_postal, String provincia, String pais,String num_cuenta ,String iban, String bic, int cantidad ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        c1 = new CuentaBancaria(identificador,descripcion,titulo,banco,direccion,municipio,cod_postal,provincia,pais,num_cuenta,iban,bic,cantidad);
        c1.insertar();
    
    }
       
  public void modificarCuenta(int identificador1, String descripcion1, String titulo1, String banco1, String direccion1, String municipio1, String cod_postal1, String provincia1, String pais1,String num_cuenta1 ,String iban1, String bic1, int cantidad1) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        c1 = new CuentaBancaria(identificador1,descripcion1,titulo1,banco1,direccion1,municipio1,cod_postal1,provincia1,pais1,num_cuenta1,iban1,bic1,cantidad1);
        c1.actualizar();
    }
         
     
}