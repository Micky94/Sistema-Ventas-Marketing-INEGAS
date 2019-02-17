 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesador;

import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Miguel Angel
 */
public class TPC {

    public TPC() {
    }

    private static final LinkedList<String> lexemas = new LinkedList<>(Arrays.asList(
            "HELP",
            "TRUE",
            "FALSE",
       
            //1
            "REGISTRARADMINISTRADOR",
            "OBTENERADMINISTRADOR",
            "OBTENERADMINISTRADORES",
            "MODIFICARADMINISTRADOR",
            "ELIMINARADMINISTRADOR",
            //2
            "REGISTRARCLIENTE",
            "OBTENERCLIENTE",
            "OBTENERCLIENTES",
            "MODIFICARCLIENTE",
            "ELIMINARCLIENTE",
         
            //3
            "REGISTRARPROGRAMA",
            "OBTENERPROGRAMA",
            "OBTENERPROGRAMAS",
            "MODIFICARPROGRAMA",
            "ELIMINARPROGRAMA",
            //4
            "REGISTRARMODULO",
            "OBTENERMODULO",
            "OBTENERMODULOS",
            "MODIFICARMODULO",
            "ELIMINARMODULO",
            //5
            "REGISTRARMODULOSPROGRAMA",
            "OBTENERMODULOSPROGRAMA",
            "ELIMINARMODULOPROGRAMA",
            
            //
            "REGISTRAROFERTAPROGRAMA",
            "OBTENEROFERTAPROGRAMA",
            "OBTENEROFERTAPROGRAMAS",
            "MODIFICAROFERTAPROGRAMA",
            "ELIMINAROFERTAPROGRAMA",
            //12
       
            "REGISTRARBOLETAINSCRIPCION",
            "OBTENERBOLETAINSCRIPCION",
            "OBTENERBOLETASINSCRIPCIONES",
            "ELIMINARBOLETAINSCRIPCION",
            //14   
            "OBTENERCUOTAS",
            "ELIMINARCUOTA",
            //15
            "MODIFICARCRONOGRAMAMODULO",
            "ELIMINARCRONOGRAMAMODULO",
            //16
            "REGISTRARMODULOBOLETA",
            //17
           
            "REGISTRARPLANPAGO",
            "OBTENERPLANPAGO",
            "OBTENERPLANESPAGOS",
            "ELIMINARPLANPAGO",
            //20
            "OBTENERCONVALIDACIONINFORME",
            "ELIMINARCONVALIDACIONINFORME",
            //21
            "REPORTEUSUARIOS",
            "REPORTEGANANCIAS",
            //22
            "NOTIFICACIONES"
    //Aqui van todos los nombres de los tokens declarados

    ));

    private static final LinkedList<Token> tokens = new LinkedList<>(Arrays.asList(
            new Token(Token.HELP, -1, "HELP"),
            new Token(Token.TRUE, -1, "TRUE"),
            new Token(Token.FALSE, -1, "FALSE"),
           
         
            //1
            new Token(Token.FUNC, Token.REGISTRARADMINISTRADOR, "REGISTRARADMINISTRADOR"),
            new Token(Token.FUNC, Token.OBTENERADMINISTRADOR, "OBTENERADMINISTRADOR"),
            new Token(Token.FUNC, Token.OBTENERADMINISTRADORES, "OBTENERADMINISTRADORES"),
            new Token(Token.FUNC, Token.MODIFICARADMINISTRADOR, "MODIFICARADMINISTRADOR"),
            new Token(Token.FUNC, Token.ELIMINARADMINISTRADOR, "ELIMINARADMINISTRADOR"),
            //3
            new Token(Token.FUNC, Token.REGISTRARCLIENTE, "REGISTRARCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTE, "OBTENERCLIENTE"),
            new Token(Token.FUNC, Token.OBTENERCLIENTES, "OBTENERCLIENTES"),
            new Token(Token.FUNC, Token.MODIFICARCLIENTE, "MODIFICARCLIENTE"),
            new Token(Token.FUNC, Token.ELIMINARCLIENTE, "ELIMINARCLIENTE"),
            //4
  
            new Token(Token.FUNC, Token.REGISTRARPROGRAMA, "REGISTRARPROGRAMA"),
            new Token(Token.FUNC, Token.OBTENERPROGRAMA, "OBTENERPROGRAMA"),
            new Token(Token.FUNC, Token.OBTENERPROGRAMAS, "OBTENERPROGRAMAS"),
            new Token(Token.FUNC, Token.MODIFICARPROGRAMA, "MODIFICARPROGRAMA"),
            new Token(Token.FUNC, Token.ELIMINARPROGRAMA, "ELIMINARPROGRAMA"),
            //5
            new Token(Token.FUNC, Token.REGISTRARMODULO, "REGISTRARMODULO"),
            new Token(Token.FUNC, Token.OBTENERMODULO, "OBTENERMODULO"),
            new Token(Token.FUNC, Token.OBTENERMODULOS, "OBTENERMODULOS"),
            new Token(Token.FUNC, Token.MODIFICARMODULO, "MODIFICARMODULO"),
            new Token(Token.FUNC, Token.ELIMINARMODULO, "ELIMINARMODULO"),
            //8
//            new Token(Token.FUNC, Token.REGISTRARPROFESIONDOCENTE, "REGISTRARPROFESIONDOCENTE"),
//            new Token(Token.FUNC, Token.OBTENERPROFESIONESDOCENTE, "OBTENERPROFESIONDOCENTE"),
//            new Token(Token.FUNC, Token.ELIMINARPROFESIONDOCENTE, "ELIMINARPROFESIONDOCENTE"),
//            9
//            new Token(Token.FUNC, Token.REGISTRARCARGOADMIN, "REGISTRARCARGOADMIN"),
//            new Token(Token.FUNC, Token.OBTENERCARGOSADMIN, "OBTENERCARGOSADMIN"),
//            new Token(Token.FUNC, Token.ELIMINARCARGOADMIN, "ELIMINARCARGOADMIN"),
//            10
//            new Token(Token.FUNC, Token.REGISTRARMODULOSPROGRAMA, "REGISTRARMODULOSPROGRAMA"),
//            new Token(Token.FUNC, Token.OBTENERMODULOSPROGRAMA, "OBTENERMODULOSPROGRAMA"),
//            new Token(Token.FUNC, Token.ELIMINARMODULOPROGRAMA, "ELIMINARMODULOPROGRAMA"),
//            11
//            new Token(Token.FUNC, Token.REGISTRAROFERTAPROGRAMA, "REGISTRAROFERTAPROGRAMA"),
//            new Token(Token.FUNC, Token.OBTENEROFERTAPROGRAMA, "OBTENEROFERTAPROGRAMA"),
//            new Token(Token.FUNC, Token.OBTENEROFERTAPROGRAMAS, "OBTENEROFERTAPROGRAMAS"),
//            new Token(Token.FUNC, Token.MODIFICAROFERTAPROGRAMA, "MODIFICAROFERTAPROGRAMA"),
//            new Token(Token.FUNC, Token.ELIMINAROFERTAPROGRAMA, "ELIMINAROFERTAPROGRAMA"),
//            12
//            new Token(Token.FUNC, Token.REGISTRARCONVALIDACION, "REGISTRARCONVALIDACION"),
//            new Token(Token.FUNC, Token.OBTENERCONVALIDACION, "OBTENERCONVALIDACION"),
//            new Token(Token.FUNC, Token.OBTENERCONVALIDACIONES, "OBTENERCONVALIDACIONES"),
//            new Token(Token.FUNC, Token.ELIMINARCONVALIDACION, "ELIMINARCONVALIDACION"),
//            13
//            new Token(Token.FUNC, Token.REGISTRARBOLETAINSCRIPCION, "REGISTRARBOLETAINSCRIPCION"),
//            new Token(Token.FUNC, Token.OBTENERBOLETAINSCRIPCION, "OBTENERBOLETAINSCRIPCION"),
//            new Token(Token.FUNC, Token.OBTENERBOLETASINSCRIPCIONES, "OBTENERBOLETASINSCRIPCIONES"),
//            new Token(Token.FUNC, Token.ELIMINARBOLETAINSCRIPCION, "ELIMINARBOLETAINSCRIPCION"),
//            14   
//            new Token(Token.FUNC, Token.OBTENERCUOTAS, "OBTENERCUOTAS"),
//            new Token(Token.FUNC, Token.ELIMINARCUOTA, "ELIMINARCUOTA"),
//            15
//            new Token(Token.FUNC, Token.MODIFICARCRONOGRAMAMODULO, "MODIFICARCRONOGRAMAMODULO"),
//            new Token(Token.FUNC, Token.ELIMINARCRONOGRAMAMODULO, "ELIMINARCRONOGRAMAMODULO"),
//            16
//            new Token(Token.FUNC, Token.REGISTRARMODULOBOLETA, "REGISTRARMODULOBOLETA"),
//            17
//            new Token(Token.FUNC, Token.CERRARACTADENOTAS, "CERRARACTADENOTAS"),
//            18
//            new Token(Token.FUNC, Token.OBTENERNOTA, "OBTENERNOTA"),
//            new Token(Token.FUNC, Token.OBTENERNOTAS, "OBTENERNOTAS"),
//            new Token(Token.FUNC, Token.ELIMINARNOTA, "ELIMINARNOTA"),
//            19
//            new Token(Token.FUNC, Token.REGISTRARPLANPAGO, "REGISTRARPLANPAGO"),
//            new Token(Token.FUNC, Token.OBTENERPLANPAGO, "OBTENERPLANPAGO"),
//            new Token(Token.FUNC, Token.OBTENERPLANESPAGOS, "OBTENERPLANESPAGOS"),
//            new Token(Token.FUNC, Token.ELIMINARPLANPAGO, "ELIMINARPLANPAGO"),
//            20
//            new Token(Token.FUNC, Token.OBTENERCONVALIDACIONINFORME, "OBTENERCONVALIDACIONINFORME"),
//            new Token(Token.FUNC, Token.ELIMINARCONVALIDACIONINFORME, "ELIMINARCONVALIDACIONINFORME"),
//            21
//            new Token(Token.FUNC, Token.REPORTEUSUARIOS, "REPORTEUSUARIOS"),
//            new Token(Token.FUNC, Token.REPORTEGANANCIAS, "REPORTEGANANCIAS"),
            //22
            new Token(Token.FUNC, Token.NOTIFICACIONES, "NOTIFICACIONES")
            
            
    ));

    public static Token estaEnTPC(String lexema) {
        lexema = lexema.toUpperCase();
        for (int i = 0; i < lexemas.size(); i++) {
            if (lexemas.get(i).toUpperCase().equals(lexema)) {
                Token token = new Token();
                token.setNombre(tokens.get(i).getNombre());
                token.setAtributo(tokens.get(i).getAtributo());
                token.setToStr(tokens.get(i).getToStr());
                return token;
            }
        }
        return null;
    }
}
