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
public class Persona {

    private int codigo;
    private String ci;
    private String nombre ;
    private String apellido;
    private String fecha_expedicion;
    private String telefono;
    private String correo;
    private String direccion;
    private String estado_civil;
    private String cargo;
    private String tipo;
    private byte estado;

    Conexion conection;

    public Persona( int codigo, String ci, String nombre, String apellido, String fecha_expedicion, String telefono, String correo, String direccion, String estado_civil, String tipo, byte estado) {
        this.codigo = codigo;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_expedicion = fecha_expedicion;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.estado_civil = estado_civil;
        this.tipo = tipo;
        this.estado = estado;
       
    }

    public Persona() {
        this.conection= Conexion.getInstancia();
    }

   public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
    public void setPersona (int codigo, String ci, String nombre, String apellido, String fecha_expedicion, String telefono, String correo, String direccion, String estado_civil,String cargo, String tipo){
         this.codigo = codigo;
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fecha_expedicion = fecha_expedicion;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.estado_civil = estado_civil;
        this.cargo=cargo;
        this.tipo = tipo;
        this.estado= 1;
      
        
    }
     
         
    public int getCodigo() {
        return codigo;
    }

    public String getCi() {
        return ci;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getFecha_expedicion() {
        return fecha_expedicion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEstado_civil() {
        return estado_civil;
    }

    public String getTipo() {
        return tipo;
    }

    public byte getEstado() {
        return estado;
    }

    public Conexion getConection() {
        return conection;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setFecha_expedicion(String fecha_expedicion) {
        this.fecha_expedicion = fecha_expedicion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEstado_civil(String estado_civil) {
        this.estado_civil = estado_civil;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEstado(byte estado) {
        this.estado = estado;
    }

    public void setConection(Conexion conection) {
        this.conection = conection;
    }

    
//==============================================================================
//==============================================================================

    /* OBTENER PERSONA DE UN CODIGO */
    public DefaultTableModel obtenerPersona(int codigo) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel persona = new DefaultTableModel();
        persona.setColumnIdentifiers(new Object[]{
            "codigo", "ci","nombre", "apellido", "fecha_expedicion", "telefono","direccion", "estado_civil", "cargo","tipo", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT\n"
               + "persona.codigo,\n"
                + "persona.ci,\n"
                + "persona.nombre,\n"
                + "persona.apellido,\n"
                + "persona.fecha_expedicion,\n"
                + "persona.telefono,\n"
                + "persona.correo,\n"
                + "persona.direccion,\n"
                + "persona.estado_civil,\n"
                + "persona.cargo,\n"
                + "persona.tipo,\n"
                + "persona.estado,\n"
                + "FROM persona\n"
                + "WHERE persona.codigo=?"
                + " and persona.estado='1'";
        // Los simbolos de interrogacion son para mandar parametros 
        // a la consulta al momento de ejecutalas

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,codigo);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                persona.addRow(new Object[]{
                    rs.getInt("codigo"),
                    rs.getString ("ci"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("fecha_expedicion"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("direccion"),
                    rs.getString("cargo"),
                    rs.getString("tipo"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return persona;
    }

//==============================================================================
//==============================================================================

    /*OBTENER PERSONAS*/
    public DefaultTableModel obtenerPersonas(String tipo) {
        // Tabla para mostrar lo obtenido de la consulta
        DefaultTableModel modulos = new DefaultTableModel();
        modulos.setColumnIdentifiers(new Object[]{
            "codigo","ci" ,"nombre", "apellido",  "telefono", "correo", "tipo", "estado"
        });

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();
        // Preparo la consulta
        String sql = "SELECT\n"
                + "persona.cod,\n"
                + "persona.ci,\n"
                + "persona.nombre,\n"
                + "persona.apellido,\n"
                + "persona.telefono,\n"
                + "persona.correo,\n"
                + "persona.direccion,\n"
                + "persona.estado_civil,\n"
                + "persona.cargo,\n"
                + "persona.tipo,\n"
                + "persona.estado\n"
                + "FROM persona\n"
                + "WHERE persona.estado='1' and persona.tipo='" + tipo + "'";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();

            // Recorro el resultado
            while (rs.next()) {
                // Agrego las tuplas a mi tabla
                modulos.addRow(new Object[]{
                    rs.getInt("codigo"),
                    rs.getString("ci"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getString("direccion"),
                    rs.getString("estado_civil"),
                    rs.getString("cargo"),
                    rs.getString("tipo"),
                    rs.getInt("estado")
                });
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return modulos;
    }

//==============================================================================
//==============================================================================
/*REGISTRAR UNA PERSONA*/
    public int registrarPersona() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "INSERT INTO persona(\n"
                + "ci,nombre,apellido,fecha_expedicion,telefono,correo,direccion,estado_civil,cargo,tipo,estado)\n"
                + "VALUES(?,?,?,?,?,?,'1')";
        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // El segundo parametro se usa cuando se tienen tablas que generan llaves primarias
            // es bueno cuando nuestra bd tiene las primarias autoincrementables

            ps.setString(1, this.ci);
            ps.setString(2, this.nombre);
            ps.setString(3, this.apellido);
            ps.setString(4, this.fecha_expedicion);
            ps.setString(5, this.telefono);
            ps.setString(6, this.direccion);
            ps.setString(7, this.estado_civil);
            ps.setString(8, this.cargo);
            ps.setString(9, this.tipo);

            int rows = ps.executeUpdate();

            // Cierro Conexion
            this.conection.cerrarConexion();

            // Obtengo el id generado para devolverlo
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
/*MODIFICAR UNA PERSONA*/
    public void modificarPersona() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE persona SET \n"
                       
                + "nombre = ?,\n"
                + "apellido = ?,\n"
                + "ci = ?,\n"
                + "correo = ?,\n"
                + "telefono = ?,\n"
                + "estado_civil = ?,\n"
                + "cargo=?,\n"
                + "tipo = ?\n"
                + "WHERE persona.codigo = ?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt( 1, this.codigo);
            ps.setString(2,this.ci);
            ps.setString(3, this.nombre);
            ps.setString(4, this.apellido);
            ps.setString(5, this.telefono);
            ps.setString(6, this.correo);
            ps.setString(7, this.estado_civil);
            ps.setString(8, this.cargo);
            ps.setString(9, this.tipo);
            
            int rows= ps.executeUpdate();
            System.out.println(rows);
            // Cierro la conexion
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

//==============================================================================
//==============================================================================
/*ELIMINAR UNA PERSONA*/
    public void eliminarPersona() {
        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "UPDATE persona SET \n"
                + "estado = '0'\n"
                + "WHERE persona.codigo = ?";
        System.out.println(sql);

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, this.codigo);
            int rows = ps.executeUpdate();
            System.out.println(rows);
            // Cierro la conexion
            this.conection.cerrarConexion();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String verificar(String correo) {
        String resultado = "";

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT correo\n"
                + "FROM persona\n"
                + "WHERE persona.correo=?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();
            // Recorro el resultado
            while (rs.next()) {
                resultado=rs.getString("correo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    public String verificar2(String correo) {
        String resultado = "";

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT tipo\n"
                + "FROM persona\n"
                + "WHERE persona.correo=?";

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();
            // Recorro el resultado
            while (rs.next()) {
                resultado=rs.getString("tipo");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    public int cantidadPersonas(String tipoPersona) {
        int resultado = 0;

        // Abro y obtengo la conexion
        this.conection.abrirConexion();
        Connection con = this.conection.getConexion();

        // Preparo la consulta
        String sql = "SELECT count(persona)\n"
                + "FROM persona\n"
                + "WHERE persona.tipo=? and persona.estado='1'" ;

        try {
            // La ejecuto
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, tipoPersona);
            ResultSet rs = ps.executeQuery();

            // Cierro la conexion
            this.conection.cerrarConexion();
            // Recorro el resultado
            while (rs.next()) {
                resultado=rs.getInt("count");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resultado;
    }

    public void setPersona(String codigo, String ci, String nombre, String apellido, String telefono, String correo, String direccion, String cargo, String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPersona(String codigo, String ci, String nombre, String apellido, String fecha_expedicion, String telefono, String correo, String direccion, String estado_civil, String cargo, String tipo, String administrador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPersona(String codigo, String ci, String nombre, String apellido, String telefono, String correo, String direccion, String cargo, String tipo, String administrador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPersona(String nombre, String apellido, String ci, String telefono, String correo, String cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setPersona(String ci, String nombre, String apellido, String fecha_expedicion, String telefono, String correo, String direccion, String estado_civil, String cargo, String tipo, String administrador) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  }
