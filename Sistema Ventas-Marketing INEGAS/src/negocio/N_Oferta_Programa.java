package negocio;

import datos.Detalle_Oferta_Modulo;
import datos.Detalle_Programa_Modulo;
import datos.Oferta_Programa;
import javax.swing.table.DefaultTableModel;

public class N_Oferta_Programa {

    private Oferta_Programa oferta_Programa;
    private Detalle_Oferta_Modulo detalle_Oferta_Modulo;
    private Detalle_Programa_Modulo detalle_Programa_Modulo;

    public N_Oferta_Programa() {
        this.oferta_Programa = new Oferta_Programa();
        this.detalle_Oferta_Modulo = new Detalle_Oferta_Modulo();
        this.detalle_Programa_Modulo = new Detalle_Programa_Modulo();
    }

    public DefaultTableModel obtenerOfertaPrograma(int codigo) {
        return this.oferta_Programa.obtenerOfertaPrograma(codigo);
    }

    public DefaultTableModel obtenerOfertaProgramas() {
        return this.oferta_Programa.obtenerOfertaProgramas();
    }

    public int registrarOfertaPrograma(String fecha_inicio, String fecha_fin, String descripcion, int cod_programa) {
        this.oferta_Programa.setOfertaPrograma(fecha_inicio, fecha_fin, descripcion, cod_programa);
        int cod_oferta_programa = this.oferta_Programa.registrarOfertaPrograma();

        DefaultTableModel modulos = this.detalle_Programa_Modulo.obtenerDetalle_Programa_Modulo(cod_programa);//METODO 2 - OBTENER TODOS LOS DETALLE_PROGRAMA_MODULO POR COD PROGRAMA / BRIAN
        for (int i = 0; i < modulos.getRowCount(); i++) {
            int id_detalle_programa_modulo = (int) modulos.getValueAt(i, 0);
            this.detalle_Oferta_Modulo.setDetalleOfertaModulo("No definido.", "No Definido.", cod_oferta_programa, id_detalle_programa_modulo, -1, "Abierta.");
            this.detalle_Oferta_Modulo.registrarDetalleOfertaModuloSD();//REGISTRAR SIN COD DOCENTE
        }
        return cod_oferta_programa;
    }

    public void modificarOfertaPrograma(int codigo, String descripcion) {
        this.oferta_Programa.setDescripcion(descripcion);
        this.oferta_Programa.setCodigo(codigo);
        this.oferta_Programa.modificarOfertaPrograma();
    }

    public void eliminar(int codigo) {
        this.oferta_Programa.setCodigo(codigo);
        this.oferta_Programa.eliminarOfertaPrograma();
    }
}
