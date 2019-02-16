package negocio;

import datos.Detalle_Oferta_Modulo;
import javax.swing.table.DefaultTableModel;

public class N_Detalle_Oferta_Modulo {

    private Detalle_Oferta_Modulo detalle_Oferta_Modulo;

    public N_Detalle_Oferta_Modulo() {
        this.detalle_Oferta_Modulo = new Detalle_Oferta_Modulo();
    }

    public DefaultTableModel obtenerDetalleOfertaModulos(int codigo) {//COD OFERTA PROGRAMA
        return this.detalle_Oferta_Modulo.obtenerDetalleOfertaModulos(codigo);
    }

    public DefaultTableModel obtenerDetalleOfertaModulos() {
        return this.detalle_Oferta_Modulo.obtenerDetalleOfertaModulos();
    }

    public int registrarDetalleOfertaModulo(String fecha_inicio, String fecha_fin, int cod_oferta_programa, int cod_detalle_programa_modulo, int cod_docente) {
        this.detalle_Oferta_Modulo.setDetalleOfertaModulo(fecha_inicio, fecha_fin, cod_oferta_programa, cod_detalle_programa_modulo, cod_docente, "Abierta.");
        return this.detalle_Oferta_Modulo.registrarDetalleOfertaModulo();
    }



    public void modificarDetalleOfertaModulo(int codigo, String fecha_inicio, String fecha_fin, int cod_docente) {
        this.detalle_Oferta_Modulo.setCodigo(codigo);
        this.detalle_Oferta_Modulo.setDetalleOfertaModulo(fecha_inicio, fecha_fin, -1, -1, cod_docente, "");
        this.detalle_Oferta_Modulo.modificarDetalleOfertaModulo();
    }

    public void eliminarDetalleOfertaModulo(int codigo) {
        this.detalle_Oferta_Modulo.setCodigo(codigo);
        this.detalle_Oferta_Modulo.eliminarDetalleOfertaModulo();
    }
}
