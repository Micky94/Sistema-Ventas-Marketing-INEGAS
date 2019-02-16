/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Persona;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Angel
 */
public class N_Administrador {

    private Persona m_persona;

    public N_Administrador() {
        this.m_persona = new Persona( );
    }

    public DefaultTableModel obtenerAdministrador(int codigo) {
        return this.m_persona.obtenerPersona(codigo);
    }

    public DefaultTableModel obtenerAdministradores() {
        return this.m_persona.obtenerPersonas("Administrador");
    }

    public int registrarAdministrador(String ci,String nombre, String apellido, String fecha_expedicion, String telefono, String correo,String direccion,String estado_civil,String cargo,String tipo) {
        // No olvidar primero settear los datos
        this.m_persona.setPersona ( ci, nombre, apellido, fecha_expedicion, telefono, correo, direccion, estado_civil, cargo, tipo, "Administrador");
        return this.m_persona.registrarPersona();
    }

    public void modificarAdministrador(int  codigo,String ci,String nombre, String apellido, String telefono, String correo,String direccion,String cargo, String tipo) {
        // No olvidar primero settear los datos
        this.m_persona.setPersona(ci, nombre, apellido , telefono, correo,direccion,cargo,tipo, "Administrador");
        this.m_persona.setCodigo(codigo);
        this.m_persona.modificarPersona();
    }

    public void eliminarAdministrador(int codigo) {
        this.m_persona.setCodigo(codigo);
        this.m_persona.eliminarPersona();
    }

    public String verificar(String correo) {
        return this.m_persona.verificar(correo);
    }
}
