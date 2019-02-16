/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesador;

/**
 *
 * @author Miguel Angel
 */
public class Token {

    // Constantes
    public static final int NUM = 0; // Numero Valor
    public static final int STRING = 1; // String Constante
    public static final int FUNC = 2; // Alguna Funcion
    public static final int GB = 3; //Guion Bajo
    public static final int CA = 4; // Corchete Abierto
    public static final int CC = 5; // Corchete Cerrado
    public static final int COMA = 6; // Coma ,
    public static final int FIN = 7;
    public static final int ERROR = 8;
    public static final int TRUE = 9;
    public static final int FALSE = 10;
    public static final int HELP = 11;

//Funciones
    //1 Administrador
   public static final int REGISTRARADMINISTRADOR = 200;
   public static final int OBTENERADMINISTRADOR = 201;
   public static final int OBTENERADMINISTRADORES = 202;
   public static final int MODIFICARADMINISTRADOR = 203;
   public static final int ELIMINARADMINISTRADOR = 204;
   //2 CLiente
    public static final int REGISTRARCLIENTE = 205;
    public static final int OBTENERCLIENTE = 206;
    public static final int OBTENERCLIENTES = 207;
    public static final int MODIFICARCLIENTE = 208;
    public static final int ELIMINARCLIENTE = 209;
    
//3 Docente
    public static final int REGISTRARDOCENTE = 210;
    public static final int OBTENERDOCENTE = 211;
    public static final int OBTENERDOCENTES = 212;
    public static final int MODIFICARDOCENTE = 213;
    public static final int ELIMINARDOCENTE = 214;

//    //6 Programa
//    public static final int REGISTRARPROGRAMA = 223;
//    public static final int OBTENERPROGRAMA = 224;
//    public static final int OBTENERPROGRAMAS = 225;
//    public static final int MODIFICARPROGRAMA = 226;
//    public static final int ELIMINARPROGRAMA = 227;
//    //7 Modulo
//    public static final int REGISTRARMODULO = 228;
//    public static final int OBTENERMODULO = 229;
//    public static final int OBTENERMODULOS = 230;
//    public static final int MODIFICARMODULO = 231;
//    public static final int ELIMINARMODULO = 232;

//    public static final int ELIMINARCARGOADMIN = 238;
//    //10 Detalle Programa Modulo
//    public static final int REGISTRARMODULOSPROGRAMA = 239;
//    public static final int OBTENERMODULOSPROGRAMA = 240;
//    public static final int ELIMINARMODULOPROGRAMA = 241;
//    //11 Oferta Programa
//    public static final int REGISTRAROFERTAPROGRAMA = 242;
//    public static final int OBTENEROFERTAPROGRAMA = 243;
//    public static final int OBTENEROFERTAPROGRAMAS = 244;
//    public static final int MODIFICAROFERTAPROGRAMA = 245;
//    public static final int ELIMINAROFERTAPROGRAMA = 246;
//    //12 Convalidacion
//    public static final int REGISTRARCONVALIDACION = 247;
//    public static final int OBTENERCONVALIDACION = 248;
//    public static final int OBTENERCONVALIDACIONES = 249;
//    public static final int ELIMINARCONVALIDACION = 250;
//    //13 Boleta Inscripcion
//    public static final int REGISTRARBOLETAINSCRIPCION = 251;
//    public static final int OBTENERBOLETAINSCRIPCION = 252;
//    public static final int OBTENERBOLETASINSCRIPCIONES = 253;
//    public static final int ELIMINARBOLETAINSCRIPCION = 254;
//    //14 Cuotas
//    public static final int OBTENERCUOTAS = 255;
//    public static final int ELIMINARCUOTA = 256;
//    //15 Cronograma Modulo
//    public static final int MODIFICARCRONOGRAMAMODULO = 257;
//    public static final int ELIMINARCRONOGRAMAMODULO = 258;
//    //16 Modulo Boleta
//    public static final int REGISTRARMODULOBOLETA = 259;

//    //19 Plan de Pago
//    public static final int REGISTRARPLANPAGO = 264;
//    public static final int OBTENERPLANPAGO = 265;
//    public static final int OBTENERPLANESPAGOS = 266;
//    public static final int ELIMINARPLANPAGO = 267;
//    //20 Coonvalidacion Informe
//    public static final int OBTENERCONVALIDACIONINFORME = 268;
//    public static final int ELIMINARCONVALIDACIONINFORME = 269;
//    //21 Reportes
//    public static final int REPORTEUSUARIOS = 270;
//    public static final int REPORTEGANANCIAS = 271;
//    //22 Notificaciones
 public static final int NOTIFICACIONES = 272;
 private int nombre;    
 private int atributo;
 private String toStr;

    public Token() {
    }

    public Token(int nombre, int atributo, String toStr) {
        this.nombre = nombre;
        this.atributo = atributo;
        this.toStr = toStr;
    }

 
    public int getNombre() {
        return nombre;
    }
    public int getAtributo() {
        return atributo;
    }
    public String getToStr() {
        return toStr;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public void setAtributo(int atributo) {
        this.atributo = atributo;
    }

    public void setToStr(String toStr) {
        this.toStr = toStr;
    }

   
  
}
