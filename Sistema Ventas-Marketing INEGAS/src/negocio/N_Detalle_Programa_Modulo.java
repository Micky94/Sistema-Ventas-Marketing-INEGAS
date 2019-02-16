/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import datos.Detalle_Programa_Modulo;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miguel Angel
 */
public class N_Detalle_Programa_Modulo {

    private Detalle_Programa_Modulo m_detalle = new Detalle_Programa_Modulo();

    /* OBTENER DETALLE MEDIANTE EL COD DE UNA PROGRAMA */
    public DefaultTableModel obtenerDetalle_Programa_Modulo(int cod_Programa) {
        return this.m_detalle.obtenerDetalle_Programa_Modulo(cod_Programa);
    }

    public int registrarDetalle_Programa_Modulo(int cod_programa, int cod_modulo) {
        // No olvidar primero settear los datos
        this.m_detalle.setDetalle_Programa_Modulo(cod_programa, cod_modulo);
        return this.m_detalle.registrarDetalle_Programa_Modulo();
    }

    public int eliminarModulo(int codigo) {
        this.m_detalle.setCodigo(codigo);
        return this.m_detalle.eliminarDetalle_Programa_Modulo();
    }
}
