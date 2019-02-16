
package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;

public class Detalle_Oferta_Modulo {
   private int codigo;
    private String fecha_inicio;
    private String fecha_fin;
    private int cod_oferta_programa;
    private int cod_detalle_programa_modulo;
    private int cod_docente;
    private byte estado;
    private Conexion conection;

    public Detalle_Oferta_Modulo() {
        this.conection = Conexion.getInstancia();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getCod_oferta_programa() {
        return cod_oferta_programa;
    }

    public void setCod_oferta_programa(int cod_oferta_programa) {
        this.cod_oferta_programa = cod_oferta_programa;
    }

    public int getCod_detalle_programa_modulo() {
        return cod_detalle_programa_modulo;
    }

    public void setCod_detalle_programa_modulo(int cod_detalle_programa_modulo) {
        this.cod_detalle_programa_modulo = cod_detalle_programa_modulo;
    }

    public int getCod_docente() {
        return cod_docente;
    }

    public void setCod_docente(int cod_docente) {
        this.cod_docente = cod_docente;
    }

    public byte getEstado() {
        return estado;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    
    public void setDetalleOfertaModulo(String fecha_inicio, String fecha_fin, int cod_oferta_programa, int cod_detalle_programa_modulo, int cod_docente) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.cod_oferta_programa = cod_oferta_programa;
        this.cod_detalle_programa_modulo = cod_detalle_programa_modulo;
        this.cod_docente = cod_docente;
      
    }
    
    //==============================================================================
    //==============================================================================    
    /*OBTENER DETALLE OFERTA MODULO*/
    public DefaultTableModel obtenerDetalleOfertaModulos() {
        DefaultTableModel detalleOfertaModulos = new DefaultTableModel();
        detalleOfertaModulos.setColumnIdentifiers(new Object[]{
                       "codigo", "Fecha Inicio", "Fecha Fin", "COD Oferta Programa", "COD Detalle Programa Modulo", "COD Docente", "Estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM detalle_oferta_programa_detalle_programa_modulo WHERE estado='1'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                detalleOfertaModulos.addRow(new Object[]{
                    rs.getInt("codigo"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_fin"),
                    rs.getInt("cod_oferta_programa"),
                    rs.getInt("cod_detalle_programa_modulo"),
                    rs.getInt("cod_docente"),
                    rs.getInt("estado"),
             
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalleOfertaModulos;
    }
    
    //==============================================================================
    //==============================================================================
    /* OBTENER DETALLE OFERTA MODULO POR COD OFERTA PROGRAMA */
    public DefaultTableModel obtenerDetalleOfertaModulos(int codigo) {
        DefaultTableModel detalleOfertaModulos = new DefaultTableModel();
        detalleOfertaModulos.setColumnIdentifiers(new Object[]{
            "codigo", "Fecha Inicio", "Fecha Fin", "COD Oferta Programa", "COD Detalle Programa Modulo", "COD Docente", "Estado"
        });
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "SELECT * FROM detalle_oferta_programa_detalle_programa_modulo WHERE codigo=? and estado='1'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();
            this.conection.cerrarConexion();
            while (rs.next()) {
                detalleOfertaModulos.addRow(new Object[]{
                    rs.getInt("codigo"),
                    rs.getString("fecha_inicio"),
                    rs.getString("fecha_fin"),
                    rs.getInt("cod_oferta_programa"),
                    rs.getInt("cod_detalle_programa_modulo"),
                    rs.getInt("cod_docente"),
                    rs.getInt("estado"),
                 
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return detalleOfertaModulos;
    }
    
    //==============================================================================
    //==============================================================================
    /*REGISTRAR DETALLE OFERTA MODULO*/
    public int registrarDetalleOfertaModuloSD() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO detalle_oferta_programa_detalle_programa_modulo (fecha_inicio, fecha_fin, cod_oferta_programa, cod_detalle_programa_modulo, estado) "
                +"VALUES(?,?,?,?,'1',?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.fecha_inicio);
            ps.setString(2, this.fecha_fin);
            ps.setInt(3, this.cod_oferta_programa);
            ps.setInt(4, this.cod_detalle_programa_modulo);
         
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
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
    /*REGISTRAR DETALLE OFERTA MODULO*/
    public int registrarDetalleOfertaModulo() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "INSERT INTO detalle_oferta_programa_detalle_programa_modulo (fecha_inicio, fecha_fin, cod_oferta_programa, cod_detalle_programa_modulo, cod_docente, estado) "
                +"VALUES(?,?,?,?,?,'1',?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, this.fecha_inicio);
            ps.setString(2, this.fecha_fin);
            ps.setInt(3, this.cod_oferta_programa);
            ps.setInt(4, this.cod_detalle_programa_modulo);
            ps.setInt(5, this.cod_docente);
         
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
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
    /*MODIFICAR DETALLE OFERTA MODULO*/
    public void modificarDetalleOfertaModulo() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE detalle_oferta_programa_detalle_programa_modulo SET fecha_inicio = ?,fecha_fin = ?,cod_docente = ? WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, this.fecha_inicio);
            ps.setString(2, this.fecha_fin);
            ps.setInt(3, this.cod_docente);
            ps.setInt(4, this.codigo);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //==============================================================================
    //==============================================================================
    /*ELIMINAR DETALLE OFERTA MODULO*/

    public void eliminarDetalleOfertaModulo() {
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        String sql = "UPDATE detalle_oferta_programa_detalle_programa_modulo SET estado = '0' WHERE codigo = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.codigo);
            int rows = ps.executeUpdate();
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    //==============================================================================
    //==============================================================================

    public void setDetalleOfertaModulo(String fecha_inicio, String fecha_fin, int i, int i0, int cod_docente, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
}
