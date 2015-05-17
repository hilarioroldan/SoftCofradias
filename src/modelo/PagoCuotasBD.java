package modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import servicios.ConectarServicio;
import servicios.Conexion;

public class PagoCuotasBD {

    private PagoCuotas p;
    private hermanito h2;

    public PagoCuotasBD(PagoCuotas p) {
        this.p = p;
    }

    public void guardar() throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();

        cbd.un_sql = "SELECT identificador FROM pagocuotas WHERE identificador=" + p.getIdentificador() + ";";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

        if (cbd.resultado != null) {
            cbd.un_sql = "INSERT INTO pagocuotas VALUES ('" + p.getIdentificador() + "','" + p.getNumero_hermano_id() + "','" + p.getFecha() + "','" + p.getMayordomia_id() + "','" + p.getPrecio() + "','" + p.isPagado() + "')";
            cbd.un_st.executeUpdate(cbd.un_sql);
        } else {
            JOptionPane.showInputDialog(null, "Error al registrar una cuota", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    public ArrayList<hermanito> buscar(String filtro, String campo) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {

        ArrayList<hermanito> listaHermanos = new ArrayList<hermanito>();

        Conexion cbd = ConectarServicio.getInstancia().getConexionDb();

        cbd.un_sql = "SELECT * FROM hermanos WHERE " + campo + " LIKE '%" + filtro + "%' ORDER BY numero_hermano;";
        cbd.resultado = cbd.un_st.executeQuery(cbd.un_sql);

        while (cbd.resultado.next()) {

            h2 = new hermanito();

            h2.setNumero_hermano(cbd.resultado.getInt("numero_hermano"));
            h2.setNombre(cbd.resultado.getString("nombre"));
            h2.setApellido(cbd.resultado.getString("apellidos"));
            h2.setNif(cbd.resultado.getString("nif"));
            h2.setMunicipio(cbd.resultado.getString("municipio"));
            h2.setProvincia(cbd.resultado.getString("provincia"));
            h2.setPais(cbd.resultado.getString("pais"));
            h2.setTfno(cbd.resultado.getInt("tfno"));
            h2.setEmail(cbd.resultado.getString("email"));
            h2.setBanco(cbd.resultado.getString("banco"));
            h2.setCuenta_bancaria(cbd.resultado.getString("cuenta_bancaria"));
            h2.setTipo_pago_id(cbd.resultado.getInt("tipo_pago_id"));
            h2.setForma_pago_id(cbd.resultado.getInt("forma_pago_id"));
            h2.setId_hermandad(cbd.resultado.getInt("id_hermandad"));

            listaHermanos.add(h2);

        }

        return listaHermanos;

    }
}
