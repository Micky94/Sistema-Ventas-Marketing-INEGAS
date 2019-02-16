/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Angel
 */
public class Detalle_Programa_Modulo {

    private int codigo;
    private int cod_programa;
    private int cod_modulo;
    private byte estado;

    Conexion conection;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCod_programa() {
        return cod_programa;
    }

    public void setCod_programa(int cod_programa) {
        this.cod_programa = cod_programa;
    }

    public int getCod_modulo() {
        return cod_modulo;
    }

    public void setCod_modulo(int cod_modulo) {
        this.cod_modulo = cod_modulo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public Conexion getConnection() {
        return conection;
    }

    public void setConnection(Conexion connection) {
        this.conection = connection;
    }

    public Detalle_Programa_Modulo() {
        this.conection = Conexion.getInstancia();
    }

    public void setDetalle_Programa_Modulo(int cod_programa, int cod_modulo) {

        this.cod_programa = cod_programa;
        this.cod_modulo = cod_modulo;
        this.estado = 1;
    }

//==============================================================================
//==============================================================================
    /* OBTENER DETALLE MEDIANTE EL COD DE UN PROGRAMA */
    public DefaultTableModel obtenerDetalle_Programa_Modulo(int codigo) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel detalle_programa_modulo = new DefaultTableModel();
        detalle_programa_modulo.setColumnIdentifiers(new Object[]{
            "codigo", "COD Programa", "Programa", "COD Modulo", "Modulo"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
                + "detalle_programa_modulo.codigo as codd,"
                + "programa.cod as codp,\n"
                + "programa.nombre as nombrep,\n"
                + "modulo.cod as codm,\n"
                + "modulo.nombre as nombrem\n"
                + "FROM detalle_programa_modulo, modulo, programa\n"
                + "WHERE detalle_programa_modulo.cod_programa=programa.cod"
                + " and detalle_programa_modulo.cod_modulo=modulo.cod"
                + " and programa.cod=?"
                + " and detalle_programa_modulo.estado='1'";

        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                detalle_programa_modulo.addRow(new Object[]{
                    rs.getInt("codd"),
                    rs.getInt("codp"),
                    rs.getString("nombrep"),
                    rs.getInt("codm"),
                    rs.getString("nombre")});
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalle_programa_modulo;
    }

//==============================================================================
//==============================================================================

    /* REGISTRAR DETALLE */
    public int registrarDetalle_Programa_Modulo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        Modulo m = new Modulo();
        Programa p = new Programa();
        int costo = 0;

        // Preparo la consult
        String sql = "INSERT INTO detalle_programa_modulo(cod_programa,cod_modulo,estado)\n"
                + "VALUES(?,?,'1')";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro de usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables
            ps.setInt(1, this.cod_programa);
            ps.setInt(2, this.cod_modulo);
            int rows = ps.executeUpdate();
            costo = m.getCostoModulo(this.cod_modulo);
            p.actualizarCostoPrograma(this.cod_programa, costo, 1);
            // Cierro Conexion
            this.conection.cerrarConexion();

            // Obtengo el codigo generado pra devolverlo
            if (rows != 0) {
                ResultSet generateKeys = ps.getGeneratedKeys();
                if (generateKeys.next()) {
                    return generateKeys.getInt(1);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return -1;
        }
        return 0;
    }

//==============================================================================
//==============================================================================
/*ELIMINAR UN DETALLE*/
    public int eliminarDetalle_Programa_Modulo() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        Modulo m = new Modulo();
        Programa p = new Programa();
        int costo = 0;

        // Preparo la consulta
        String sql = "UPDATE detalle_programa_modulo "
                + "SET estado = '0'\n"
                + "WHERE detalle_programa_modulo.cod = ?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.codigo);
            int rows = ps.executeUpdate();
            System.out.println(rows);

            costo = m.getCostoModulo(obtenerCODModulo());

            // Cierro la conexion
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        //=======================================================
        int codPrograma = 0;
        this.conection.abrirConexion();
        con = this.conection.getConexion();

        // Preparo la consulta
        sql = "SELECT cod_programa "
                + "FROM detalle_programa_modulo \n"
                + "WHERE detalle_programa_modulo.cod=?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.codigo);
            ResultSet rs = ps.executeQuery();
            // Cierro la conexion

            while (rs.next()) {
                codPrograma = rs.getInt("cod_programa");
                p.actualizarCostoPrograma(codPrograma, costo, 0);
            }

            this.conection.cerrarConexion();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return codPrograma;
    }
    
    public int obtenerCODModulo() {
        int resultado = 0;

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql ="SELECT cod_modulo "
                + "FROM detalle_programa_modulo \n"
                + "WHERE detalle_programa_modulo.codigo=?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.codigo);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();
            // Recorro el resultado
            while (rs.next()) {
                resultado=rs.getInt("cod_modulo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

}
