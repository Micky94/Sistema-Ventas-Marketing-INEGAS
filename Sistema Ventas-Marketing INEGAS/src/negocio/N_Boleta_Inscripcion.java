package negocio;

import correo.MimeMail;
//import datos.Boleta_Inscripcion;
import datos.Cuota;
import datos.Plan_Pago;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.table.DefaultTableModel;

public class N_Boleta_Inscripcion {

   // private Boleta_Inscripcion boleta_Inscripcion;
    private Plan_Pago plan_Pago;
    private Cuota cuota;

    public N_Boleta_Inscripcion() {
        this.boleta_Inscripcion = new Boleta_Inscripcion();
        this.plan_Pago = new Plan_Pago();
        this.cuota = new Cuota();
    }

    public DefaultTableModel obtenerBoletaInscripcion(int id) {
        return this.boleta_Inscripcion.obtenerBoletaInscripcion(id);
    }

    public DefaultTableModel obtenerBoletaInscripciones() {
        return this.boleta_Inscripcion.obtenerBoletaInscripciones();
    }

    public String enviarNotificacionAtrasadas() {
        String correos = "";
        MimeMail mailer = new MimeMail();
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String fecha_actual = dia + "/" + (mes) + "/" + año;

        DefaultTableModel boletas = this.boleta_Inscripcion.obtenerBoletasDeCuotas();
        for (int i = 0; i < boletas.getRowCount(); i++) {
            int monto_plan_pago = (int) boletas.getValueAt(i, 0);
            int id_plan_pago = (int) boletas.getValueAt(i, 4);
            int monto_cuotas = (int) this.cuota.obtenerSuma(id_plan_pago).getValueAt(0, 0);
            String fecha_inicio = (String) boletas.getValueAt(i, 5);
            String fecha_fin = (String) boletas.getValueAt(i, 6);

            String dia_inicio = "";
            String mes_inicio = "";
            String año_inicio = "";
            String dia_fin = "";
            String mes_fin = "";
            String año_fin = "";
            String aux = "";
            int cantSlash = 0;
            int lg = 0;
            String num = "1234567890";
            while (fecha_inicio.length() > lg && fecha_inicio.charAt(lg) != '-') {
                if (fecha_inicio.charAt(lg) == '/') {
                    cantSlash++;
                }
                if (num.contains("" + fecha_inicio.charAt(lg))) {//PREGUN SI ES NUMERO
                    aux += fecha_inicio.charAt(lg);
                    if (cantSlash == 0) {
                        dia_inicio += aux;
                        aux = "";
                    }
                    if (cantSlash == 1) {
                        mes_inicio += aux;
                        aux = "";
                    }
                    if (cantSlash == 2) {
                        año_inicio += aux;
                        aux = "";
                    }
                }
                lg++;
            }
            aux = "";
            cantSlash = 0;
            lg = 0;
            while (fecha_fin.length() > lg && fecha_fin.charAt(lg) != '-') {
                if (fecha_fin.charAt(lg) == '/') {
                    cantSlash++;
                }
                if (num.contains("" + fecha_fin.charAt(lg))) {//PREGUN SI ES NUMERO
                    aux += fecha_fin.charAt(lg);
                    if (cantSlash == 0) {
                        dia_fin += aux;
                        aux = "";
                    }
                    if (cantSlash == 1) {
                        mes_fin += aux;
                        aux = "";
                    }
                    if (cantSlash == 2) {
                        año_fin += aux;
                        aux = "";
                    }
                }
                lg++;
            }

            int rango_mes_pagar = 0;
            int meses_pasados = 0;
            if (Integer.parseInt(año_fin) > Integer.parseInt(año_inicio)) {
                rango_mes_pagar = ((Integer.parseInt(mes_fin) + 12) - Integer.parseInt(mes_inicio));
            } else {
                rango_mes_pagar = (Integer.parseInt(mes_fin) - Integer.parseInt(mes_inicio));
            }

            if (año > Integer.parseInt(año_inicio)) {
                meses_pasados = (mes - (Integer.parseInt(mes_inicio) - 12));
            } else {
                meses_pasados = (mes - Integer.parseInt(mes_inicio));
            }
            System.out.println(meses_pasados);
            if ((monto_plan_pago / 2) >= monto_cuotas && (meses_pasados >= (rango_mes_pagar / 2))) {
                String destinatario = (String) boletas.getValueAt(i, 3);
                correos = correos + destinatario + " \n";
                try {
                    mailer.sendHtmlEmail(destinatario, "SISTEMA POSTGRADO INEGAS", "<H1>Notificacion de Atraso</H1> \n "
                            + "<H3>Se le notifica que tiene retraso en sus pagos, favor realizarlos en la mayor brevedad posible.</H3>\n"
                            + "<H3>Atte. Administracion INEGAS.</H3>");
                } catch (Exception ex) {
                }

            }

        }
        return correos;
    }

    public int registrarBoletaInscripcion(int id_estudiante, int boolPago, String fecha_fin) {
        // SI BOOLPAGO = 1 => PLAN DE PAGO CONTADO
        // SI BOOLPAGO = 2 => PLAN DE PAGO POR 8 CUOTA
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        String fecha_actual = dia + "/" + (mes + 1) + "/" + año + "-" + hora + ":" + minuto + ":" + segundo;
        this.boleta_Inscripcion.setBoletaInscripcion(fecha_actual, id_estudiante);

        int id_boleta = this.boleta_Inscripcion.registrarBoletaInscripcion();//ID BOLETA INSCRIPCION
        if (boolPago == 1) {//INSERTO PLAN DE PAGO AL CONTADO
            this.plan_Pago.setPlanPago(fecha_actual, fecha_actual, 0, id_boleta);
            this.plan_Pago.registrarPlanPago();
        } else if (boolPago == 2) {//INSERTO PLAN DE PAGO POR 8 CUOTAS
            this.plan_Pago.setPlanPago(fecha_actual, fecha_fin, 0, id_boleta);
            int id_plan_pago = this.plan_Pago.registrarPlanPago();
            for (int i = 0; i < 8; i++) {
                this.cuota.setCuota("", 0, id_plan_pago);
                this.cuota.registrarCuota();
            }
        }
        return id_boleta;
    }

    public void eliminarBoletaInscripcion(int id) {
        this.boleta_Inscripcion.setId(id);
        this.boleta_Inscripcion.eliminarBoletaInscripcion();
    }
}
