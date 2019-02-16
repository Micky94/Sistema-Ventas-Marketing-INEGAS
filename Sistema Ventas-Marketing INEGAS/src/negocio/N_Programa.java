/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Programa;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Angel
 */
public class N_Programa {

    private Programa m_programa;

    public N_Programa() {
        this.m_programa = new Programa();
    }

    public DefaultTableModel obtenerPrograma(int codigo) {
        return this.m_programa.obtenerPrograma(codigo);
    }

    public DefaultTableModel obtenerProgramas() {
        return this.m_programa.obtenerProgramas();
    }

    public int registrarPrograma(String nombre, String version, String edicion, String tipo, String fecha_apro) {
        // No olvidar primero settear los datos
        this.m_programa.setPrograma(nombre ,version, edicion, tipo, fecha_apro);
        return this.m_programa.registrarPrograma();
    }

    public void modificarPrograma(int codigo, String nombre, String version, String edicion, String tipo, String fecha_apro) {
        // No olvidar primero settear los datos
        this.m_programa.setPrograma(nombre,version, edicion, tipo, fecha_apro);
        this.m_programa.setcodigo(codigo);
        this.m_programa.modificarPrograma();
    }

    public void eliminarPrograma(int codigo) {
        this.m_programa.setcodigo(codigo);
        this.m_programa.eliminarPrograma();
    }

}
