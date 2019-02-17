/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package software;
import correo.ClienteSMTP;
import procesador.AnalizadorLex;
import procesador.Cinta;
import procesador.Parser;
import procesador.Token;
import utils.Helper;
import utils.Utils;
import correo.MimeMail;
import correo.SimuladorSMTP;
import datos.Persona;
import negocio.N_Administrador;
import negocio.N_Boleta_Inscripcion;

import negocio.N_Cliente;

import negocio.N_Cuota;
import negocio.N_Detalle_Boleta;
import negocio.N_Detalle_Oferta_Modulo;
;
import negocio.N_Detalle_Programa_Modulo;
import negocio.N_Modulo;
import negocio.N_Oferta_Programa;
import negocio.N_Plan_Pago;
//import negocio.N_Profesion;
import negocio.N_Programa;
//import negocio.N_Reportes;

/**
 *
 * @author Miguel Angel
 */
public class sistema_venta_marketing_INEGAS {
     public void processMessage(String Message) {
        // Setteando Variables
//        System.out.println("Class Acropolis:processMessage:Message " + Message);
//        String destinatario = "brian.mendez.rocha2@gmail.com";
        String destinatario = Utils.getDestinatario(Message);
//        System.out.println("############################################# =" +Message);
        System.out.println("Destinatario: " + destinatario);
        String content = Utils.getSubjectOrden(Message);
        content=verificarMensaje(content);
        int cantidad = content.length();

        System.out.println(">>>>>MENSAJE QUE RECIBO >>>>>>: " + content);
        Cinta cinta = new Cinta(content);
        AnalizadorLex analex = new AnalizadorLex(cinta);
        System.out.println(cinta);
        Parser parser = new Parser(analex);
        // Verificar Orden
        parser.Expresion();

        if (parser.errorFlag) {
            // Enviar Correo de Error
            ClienteSMTP.sendMail(destinatario, "Error de Comando",
                    "El comando introducido es incorrecto, trate consultando las ayudas con el comando HELP"
            );
            return;
        }

        analex.Init();
        Token token = analex.Preanalisis();
        System.out.println(token.getToStr());

        if (token.getNombre() == Token.HELP) {
            // Mostrar Ayudas
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_GLOBAL);
            return;
        }
//      Verifico si es usuario


//      Si todo va bien, procesar el Comando
//      Sino es HELP, es una funcionalidad
        System.out.println(token.getAtributo());
        switch (token.getAtributo()) {
            //==1
               
            case Token.REGISTRARADMINISTRADOR:
                if (!verificacionUsuarios(destinatario)) {
                    registrarAdministrador(analex, destinatario);
                }
                break;
            case Token.OBTENERADMINISTRADOR:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerAdministrador(analex, destinatario);
                }
                break;
            case Token.OBTENERADMINISTRADORES:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerAdministradores(analex, destinatario);
                }
                break;
            case Token.MODIFICARADMINISTRADOR:
                if (!verificacionUsuarios(destinatario)) {
                    modificarAdministrador(analex, destinatario);
                }
                break;
            case Token.ELIMINARADMINISTRADOR:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarAdministrador(analex, destinatario);
                }
                break;
            //==3    
            case Token.REGISTRARCLIENTE:
              registrarCliente(analex, destinatario);
                break;
            case Token.OBTENERCLIENTE:

                obtenerCliente(analex, destinatario);
                break;
            case Token.OBTENERCLIENTES:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerClientes(analex, destinatario);
                }
                break;
            case Token.MODIFICARCLIENTE:
                if (!verificacionUsuarios(destinatario)) {
                    modificarCliente(analex, destinatario);
                }
                break;
            case Token.ELIMINARCLIENTE:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarCliente(analex, destinatario);
                }
                break;
            //==4    
            case Token.REGISTRARCARGO:
                if (!verificacionUsuarios(destinatario)) {
                    registrarCargo(analex, destinatario);
                }
                break;
            case Token.OBTENERCARGOS:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerCargos(analex, destinatario);
                }
                break;
            case Token.MODIFICARCARGO:
                if (!verificacionUsuarios(destinatario)) {
                    modificarCargo(analex, destinatario);
                }
                break;
            case Token.ELIMINARCARGO:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarCargo(analex, destinatario);
                }
                break;
            //==5    
            case Token.REGISTRARPROFESION:
                if (!verificacionUsuarios(destinatario)) {
                    registrarProfesion(analex, destinatario);
                }
                break;
            case Token.OBTENERPROFESIONES:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerProfesiones(analex, destinatario);
                }
                break;
            case Token.MODIFICARPROFESION:
                if (!verificacionUsuarios(destinatario)) {
                    modificarProfesion(analex, destinatario);
                }
                break;
            case Token.ELIMINARPROFESION:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarProfesion(analex, destinatario);
                }
                break;
            //==6    
            case Token.REGISTRARPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    registrarPrograma(analex, destinatario);
                }
                break;
            case Token.OBTENERPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerPrograma(analex, destinatario);
                }
                break;
            case Token.OBTENERPROGRAMAS:

                obtenerProgramas(analex, destinatario);

                break;
            case Token.MODIFICARPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    modificarPrograma(analex, destinatario);
                }
                break;
            case Token.ELIMINARPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarPrograma(analex, destinatario);
                }
                break;
            //==7
            case Token.REGISTRARMODULO:
                if (!verificacionUsuarios(destinatario)) {
                    registrarModulo(analex, destinatario);
                }
                break;
            case Token.OBTENERMODULO:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerModulo(analex, destinatario);
                }
                break;
            case Token.OBTENERMODULOS:

                obtenerModulos(analex, destinatario);

                break;
            case Token.MODIFICARMODULO:
                if (!verificacionUsuarios(destinatario)) {
                    modificarModulo(analex, destinatario);
                }
                break;
            case Token.ELIMINARMODULO:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarModulo(analex, destinatario);
                }
                break;
            //==8
            case Token.REGISTRARPROFESIONDOCENTE:
                if (!verificacionUsuarios(destinatario)) {
                    registrarProfesionDocente(analex, destinatario);
                }
                break;
            case Token.OBTENERPROFESIONESDOCENTE:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerProfesionesDocente(analex, destinatario);
                }
                break;
            case Token.ELIMINARPROFESIONDOCENTE:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarProfesionDocente(analex, destinatario);
                }
                break;
            //==9
            case Token.REGISTRARCARGOADMIN:
                if (!verificacionUsuarios(destinatario)) {
                    registrarCargoAdmin(analex, destinatario);
                }
                break;
            case Token.OBTENERCARGOSADMIN:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerCargosAdmin(analex, destinatario);
                }
                break;
            case Token.ELIMINARCARGOADMIN:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarCargoAdmin(analex, destinatario);
                }
                break;
            //==10
            case Token.REGISTRARMODULOSPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    registrarModulosPrograma(analex, destinatario);
                }
                break;
            case Token.OBTENERMODULOSPROGRAMA:

                obtenerModulosPrograma(analex, destinatario);

                break;
            case Token.ELIMINARMODULOPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarModuloPrograma(analex, destinatario);
                }
                break;
            //==11
            case Token.REGISTRAROFERTAPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    registrarOfertaPrograma(analex, destinatario);
                }
                break;
            case Token.OBTENEROFERTAPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerOfertaPrograma(analex, destinatario);
                }
                break;
            case Token.OBTENEROFERTAPROGRAMAS:
                obtenerOfertaProgramas(analex, destinatario);
                break;
            case Token.MODIFICAROFERTAPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    modificarOfertaPrograma(analex, destinatario);
                }
                break;
            case Token.ELIMINAROFERTAPROGRAMA:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarOfertaPrograma(analex, destinatario);
                }
                break;
            //==12    
            case Token.REGISTRARCONVALIDACION:
                if (!verificacionUsuarios(destinatario)) {
                    registrarConvalidacion(analex, destinatario);
                }
                break;
            //13

            case Token.REGISTRARBOLETAINSCRIPCION:
                if (!verificacionUsuarios(destinatario)) {
                    registrarBoletaInscripcion(analex, destinatario);
                }
                break;
            case Token.OBTENERBOLETAINSCRIPCION:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerBoletaInscripcion(analex, destinatario);
                }
                break;
            case Token.OBTENERBOLETASINSCRIPCIONES:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerBoletasInscripciones(analex, destinatario);
                }
                break;
            case Token.ELIMINARBOLETAINSCRIPCION:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarBoletaInscripcion(analex, destinatario);
                }
                break;
            //14  
            case Token.OBTENERCUOTAS:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerCuotas(analex, destinatario);
                }
                break;
            case Token.ELIMINARCUOTA:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarCuota(analex, destinatario);
                }
                break;
            //15    
            case Token.MODIFICARCRONOGRAMAMODULO:
                if (!verificacionUsuarios(destinatario)) {
                    modificarCronogramaModulo(analex, destinatario);
                }
                break;
            case Token.ELIMINARCRONOGRAMAMODULO:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarCronogramaModulo(analex, destinatario);
                }
                break;
            //16    
            case Token.REGISTRARMODULOBOLETA:
                if (!verificacionUsuarios(destinatario)) {
                    registrarModuloBoleta(analex, destinatario);
                }
                break;
            //17 
            case Token.CERRARACTADENOTAS:
                if (!verificacionUsuarios(destinatario)) {
                    cerrarActaNotas(analex, destinatario);
                }
                break;
            //18
            case Token.OBTENERNOTA:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerNota(analex, destinatario);
                }
                break;
            case Token.OBTENERNOTAS:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerNotas(analex, destinatario);
                }
                break;
            case Token.ELIMINARNOTA:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarNota(analex, destinatario);
                }
                break;
            //19
            case Token.REGISTRARPLANPAGO:
                if (!verificacionUsuarios(destinatario)) {
                    registrarPlanPago(analex, destinatario);
                }
                break;
            case Token.OBTENERPLANPAGO:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerPlanPago(analex, destinatario);
                }
                break;
            case Token.OBTENERPLANESPAGOS:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerPlanPagos(analex, destinatario);
                }
                break;
            case Token.ELIMINARPLANPAGO:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarPlanPago(analex, destinatario);
                }
                break;
            //20
            case Token.OBTENERCONVALIDACIONINFORME:
                if (!verificacionUsuarios(destinatario)) {
                    obtenerConvalidacionInforme(analex, destinatario);
                }
                break;
            case Token.ELIMINARCONVALIDACIONINFORME:
                if (!verificacionUsuarios(destinatario)) {
                    eliminarConvalidacion(analex, destinatario);
                }
                break;
            //21
            case Token.REPORTEUSUARIOS:
                if (!verificacionUsuarios(destinatario)) {
                    reporteUsuarios(analex, destinatario);
                }
           
        }
    }
     
      public boolean verificacionUsuarios(String destinatario) {
        Persona person = new Persona();
        if (person.verificar2(destinatario) == "Cliente" || person.verificar2(destinatario) == "Docente") {
            ClienteSMTP.sendMail(destinatario, "Sitema - Postgrado INEGAS", "Usted no tiene privilegios para hacer uso de este comando..!! \n"
                    + "Puede consultar el comando HELP");
            System.out.println("Email enviado.");
            return true;
        }
        return false;
    }

    public String verificarMensaje(String sms) {
        char ultimo = sms.charAt(sms.length() - 1);
        if (ultimo != ']') {
            sms = sms + " [1]";
        }
        return sms;
    }

    private void registrarAdministrador(AnalizadorLex analex, String destinatario) {
   // Obtengo el Siguiente token
        analex.Avanzar();
        Token token = analex.Preanalisis();
        // Reviso si no es ayuda
        if (token.getNombre() == Token.HELP) {
            // Mostrar ayuda de esa funcionalidad
            // Enviar correo con la ayuda
            ClienteSMTP.sendMail(destinatario, "Ayudas - Sistema INEGAS", Helper.HELP_REGISTRARADMINISTRADOR);
            return;
        }

        // Sino, ejecutar el comando
        N_Administrador administradorN = new N_Administrador();
             
        // Atributos
        //ci, nombre, apellido,fecha_exped,telefono, correo,direccion ,estado_civil,cargo,tipo
        analex.Avanzar();
        String ci = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String nombre = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String apellido = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String fecha_espedicion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String telefono = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String correo = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String direccion = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
       String estado_civil = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar();
        String cargo = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
        analex.Avanzar(); 
        String tipo = Utils.quitarComillas(analex.Preanalisis().getToStr());
        analex.Avanzar();
         
              
        
        try {
            administradorN.registrarAdministrador(ci, nombre, apellido, fecha_espedicion, telefono, correo, direccion, estado_civil, cargo, tipo);
            MimeMail mimemailer = new MimeMail();
            mimemailer.sendHtmlEmail(destinatario, "Registrar Administrador", "Registro realizado correctamente\n" + Utils.dibujarTablawithHTML(administradorN.obtenerAdministradores()));
        } catch (Exception e) {
            ClienteSMTP.sendMail(destinatario, "Registrar Administrador", "error durante el registro, verifique con el comando HELP");
        }
        
    }
}
