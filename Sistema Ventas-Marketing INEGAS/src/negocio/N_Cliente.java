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
public class N_Cliente {

    private Persona m_pesona;

    public N_Cliente() {
        this.m_pesona = new Persona();
    }

    public DefaultTableModel obtenerCliente(int codigo) {
        return this.m_pesona.obtenerPersona(codigo);
    }

    public DefaultTableModel obtenerClientes() {
        return this.m_pesona.obtenerPersonas("Cliente");
    }

    public int registrarCliente(String ci,String nombre, String apellido, String telefono, String correo,String direccion,String estado_civil,String cargo,String tipo,String estado ) {
        // No olvidar primero settear los datos
        this.m_pesona.setPersona(ci,nombre, apellido, telefono, correo,direccion,estado_civil,cargo,tipo,estado, "Cliente");
        return this.m_pesona.registrarPersona();
    }

    public void modificarCliente (int codigo,String ci,String nombre, String apellido, String telefono, String correo,String direccion,String cargo, String tipo,String estado) {
        // No olvidar primero settear los datos

        this.m_pesona.setPersona( ci, nombre, apellido, telefono, correo,direccion,cargo,tipo,estado, "Cliente");
        this.m_pesona.setCodigo(codigo);
        this.m_pesona.modificarPersona();
    }

    public void eliminarCliente(int codigo) {
        this.m_pesona.setCodigo(codigo);
        this.m_pesona.eliminarPersona();
    }

    public int cantidadClientes() {
        int n = 0;

        return n;
    }

   
}
