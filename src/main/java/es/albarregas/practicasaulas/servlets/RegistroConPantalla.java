/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.practicasaulas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tomlu
 */
@WebServlet(name = "RegistroConPantalla", urlPatterns = {"/RegistroConPantalla"})
public class RegistroConPantalla extends HttpServlet {
public RegistroConPantalla(){

super();
}
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        //Declaro las variables que necesitamos para recoger los datos del formulario oculto o del formulario visible
        
        String nombre=request.getParameter("Nombre");
        String apellidos=request.getParameter("Apellidos");
        String sexo=request.getParameter("sexo");
        String usuario=request.getParameter("Usuario");
        String password=request.getParameter("psswd");
        String deportes=request.getParameter("deportes");
       
        String lectura=request.getParameter("lectura");
        String cine=request.getParameter("cine");
        String viajes=request.getParameter("viajes");
        //------valores para las fechas, que lo convertiremos a entero para poder trabajar con ellas porque nos la devuleve en cadena
        String dia=request.getParameter("dia");
        String mes=request.getParameter("mes");
        String ano=request.getParameter("ano");
        int diaEnt=0;
        int mesEnt=0;
        int anoEnt=0;
        Boolean fechaCorrecta=true;
        
        String oculto=request.getParameter("oculto");
        
        //Declaro el map para recoger los errores
        Map<String,String[]> datosRecogidos=request.getParameterMap();
            
        
        //Declaro los objetos de apollo que necesitaré para la ejecucion del servlet
        int[]errores={-1,-1,-1,-1};
        
        int contadoErrores=0;//utilizo el contador errores para introducir el número de error en cada casilla
        
        
        
        //Detecto los errores que hay en el map y lo guardo en un array;
        for( String datos:datosRecogidos.keySet()){
            
            if(datos.equals("Nombre")){
            String[] valores=datosRecogidos.get(datos);
                for(int i=0;i<valores.length;i++){
                    if(valores[i].isEmpty() || valores[i].equals(" ")){
                        errores[contadoErrores]=contadoErrores;
                    }
            
                }
            
            }
            contadoErrores++;//sumamos uno al contador para que en el array nos guarde 1;
            if(datos.equals("dia")){
                String [] valores=datosRecogidos.get(datos);
                 for(int i=0;i<valores.length;i++){
                   //diaEnt=Integer.parseInt(valores[i]);
                   diaEnt=controlEnteros(valores[i]);
            
                }
            }else if(datos.equals("mes")){
                String [] valores=datosRecogidos.get(datos);
                 for(int i=0;i<valores.length;i++){
                   mesEnt=controlEnteros(valores[i]);
            
                }
        
            }else if(datos.equals("ano")){
                String [] valores=datosRecogidos.get(datos);
                 for(int i=0;i<valores.length;i++){
                     anoEnt=controlEnteros(valores[i]);
                  // anoEnt=Integer.parseInt(valores[i]);
                
                  }
                 if(diaEnt==-1 || mesEnt==-1||anoEnt==-1){
                    errores[1]=1;
                    diaEnt=0;mesEnt=0;anoEnt=0;
                    fechaCorrecta=false;
                 }else{
                    fechaCorrecta=comprobarFecha(diaEnt,mesEnt,anoEnt);
                        if(!fechaCorrecta){
                        errores[1]=1;
                        }
                 }
                
            }
           
            contadoErrores++;
            if(datos.equals("Usuario")){
             String[] valores=datosRecogidos.get(datos);
             for(int i=0;i<valores.length;i++){
                 if(valores[i].isEmpty() || valores[i].equals(" ")){
                 
                     errores[2]=contadoErrores;
                 }
             
             }
            }
            
            contadoErrores++;
            if(datos.equals("psswd")){
             String[] valores=datosRecogidos.get(datos);
             for(int i=0;i<valores.length;i++){
                 if(valores[i].isEmpty() || valores[i].equals(" ")){
                 
                     errores[3]=contadoErrores;
                 }
             
             }
            }
            
        }
        
        //revisamos los errores
        
        
     response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registro con pantalla intermedia</title>");            
            out.println("</head>");
            out.println("<body>");
            if(! esCorrectoFormulario(errores)){
            
                if(oculto.equals("false")){
                    //Si es falso es que biene del formulario normal, entonces creamos aquí un formulario con los
                    //campos ocultos que luego serán enviados, se pondrán los errores y volverá a este formulario
                
                        out.println("<div id='oculto'><form name=\"env\" method=\"Post\" action=\"./RegistroConPantalla\">");
                        out.println("<input type=\"hidden\" name=\"Nombre\" value=\""+((nombre.isEmpty())? "":nombre)+"\">");
                        out.println("<input type=\"hidden\" name=\"Apellidos\" value=\""+apellidos+"\">");
                        out.println("<input type=\"hidden\" name=\"sexo\" value=\""+sexo+"\">");
                        out.println("<input type=\"hidden\" name=\"dia\" value=\""+((fechaCorrecta)? dia:"")+"\">");
                        out.println("<input type=\"hidden\" name=\"mes\" value=\""+((fechaCorrecta)? mes:"")+"\">");
                        out.println("<input type=\"hidden\" name=\"ano\" value=\""+((fechaCorrecta)? ano:"")+"\">");
                        out.println("<input type=\"hidden\" name=\"Usuario\" value=\""+((usuario.isEmpty() || usuario.equals(" "))? "":usuario)+"\">");
                        out.println("<input type=\"hidden\" name=\"psswd\" value=\""+((password.isEmpty() || password.equals(" "))? "":password)+"\">");
                        out.println("<input type=\"hidden\" name=\"deportes\" value=\""+deportes+"\">");
                        out.println("<input type=\"hidden\" name=\"lectura\" value=\""+lectura+"\">");
                        out.println("<input type=\"hidden\" name=\"cine\" value=\""+cine+"\">");
                        out.println("<input type=\"hidden\" name=\"viajes\" value=\""+viajes+"\">");
                        out.println("<p> hay errores en el formuario</p>");
                        out.println("<input type=\"hidden\" name=\"oculto\" value=\"true\">");
                        out.println("<input type=\"submit\" value=\"volver\">");
                        out.println("</form></div>");
                }else{  
                        out.println(mostrarErrores(errores));
                        
                        out.println("<div id='oculto'><form name=\"env\" method=\"Post\" action=\"./RegistroConPantalla\">");
                        
                        out.println(" <fieldset>\n" +
"                                   <legend>Datos personales</legend>\n" +
"                                   <p><label for=\"nombre\">Nombre: </label> <input type=\"text\" name=\"Nombre\" value=\""+((nombre.isEmpty())? "":nombre)+"\">");
                        //mostramos la imagen de check o error
                        out.println("<img src=\"./IMG/"+((nombre.isEmpty()||nombre.equals(" "))? "no.png":"ok.jpg")+"\" style=\"height:10px; width:10px; max-width:10px; max-height:10px;\"/></p>");
                        out.println(" <p><label for=\"apellidos\">Apellidos: </label><input type=\"text\" name=\"Apellidos\" value=\""+apellidos+"\"> </p>");
                        out.println(" <p><label for=\"sexo\">Sexo:</label><input type=\"radio\" name=\"sexo\" value=\"hombre\" "+((sexo.equals("hombre"))? "checked=\"true\"":"")+">Hombre</p>");
                        out.println(" &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type=\"radio\" name=\"sexo\" value=\"mujer\" "+((sexo.equals("mujer"))? "checked=\"true\"":"")+">Mujer</p>");
                        out.println(mostrarFecha(diaEnt,mesEnt,anoEnt,fechaCorrecta));
                        out.println("<img src=\"./IMG/"+((fechaCorrecta)? "ok.jpg":"no.png")+"\" style=\"height:10px; width:10px; max-width:10px; max-height:10px;\"/></p>");
                        out.println("</fieldset> <br>");
                        out.println("<fieldset><legend>Datos acceso</legend>"); 
                        out.println("<p><label for=\"usu>\">Usuario:</label><input type=\"text\" name=\"Usuario\" value=\""+((usuario.isEmpty() || usuario.equals(" "))? "":usuario)+"\">");
                        out.println("<img src=\"./IMG/"+((usuario.isEmpty()||usuario.equals(" "))? "no.png":"ok.jpg")+"\" style=\"height:10px; width:10px; max-width:10px; max-height:10px;\"/></p>");
                        out.println("<p><label for=\"pas>\">Password:</label><input type=\"password\" name=\"psswd\" value=\""+((password.isEmpty() || password.equals(" "))? "":password)+"\">");
                        out.println("<img src=\"./IMG/"+((password.isEmpty()||password.equals(" "))? "no.png":"ok.jpg")+"\" style=\"height:10px; width:10px; max-width:10px; max-height:20px;\"/></p>");
                       out.println("</fieldset> <br>");
                       out.println("<fieldset><legend>Información general</legend>");
                       out.println("<p><label>Preferencias: </label></p> <br>");
                       
                       
                       
                       out.println("<p><input type=\"checkbox\"  name=\"deportes\" value=\"deportes\""+((deportes.equals("deportes"))? "checked":"")+">Deportes</p>");
                       out.println("<p><input type=\"checkbox\"  name=\"lectura\" value=\"lectura\""+((lectura.equals("lectura"))? "checked":"")+">Lectura</p>");
                       out.println("<p><input type=\"checkbox\"  name=\"cine\" value=\"cine\""+((cine.equals("cine"))? "checked":"")+">Cine</p>");
                       out.println("<p><input type=\"checkbox\" name=\"Viajes\" value=\"viajes\""+((viajes.equals("viajes"))? "checked":"")+">Viajes</p>");
                       out.println("</fieldset>");
                        out.println("<input type=\"hidden\" name=\"oculto\" value=\"false\">");
                        out.println("<input type=\"submit\" value=\"enviar\">");
                        out.println("</form></div>");
                       
                }
            
            
            
            
            
            }else{
            
            
                out.println("<h1>Registro satisfactorio</h1>");
                
                out.println(mostrarPantallaFinal(datosRecogidos));
            }
            
            
            
            
            
            
            
              out.println("</body>");
            out.println("</html>");
    
    
    
    
    
        }
    
    }
      public String mostrarPantallaFinal(Map<String,String[]> mostrarDatos){
          //método por el cual mostramos el resutado final, he decido hacerlo con strinbilder porque es menos costoso
          //para el servlet que crear borrar y añadir líneas
          //lo que hago recorrer el map y devuelvo la cadena construida. Para inmediatamente mostrarla.
          String[] meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
          String cadenaDevuelta="";
          String[] arrayAficiones={"deportes","lectura","cine","viajes"};
          int contadorDeEspacios=0;
          StringBuilder cadena=new StringBuilder();
          for(String datos:mostrarDatos.keySet()){
              if(datos.equals("Nombre")){
                  cadenaDevuelta+=datos;
                    //cadena.append(datos);
                    String [] valores=mostrarDatos.get(datos);
                    
                    for(int i=0;i<valores.length;i++){
                    
                        cadena.append(valores[i]);
                    
                    }
              
              }else if(datos.equals("Apellidos")){
                    cadenaDevuelta+=" y "+datos;
                     String [] valores=mostrarDatos.get(datos);
                     for(int i=0;i<valores.length;i++){
                         cadena.append(" ");
                         cadena.append(valores[i]);
                        
                     
                     }
                    cadena.append("</p>");
              }else if(datos.equals("sexo")){
                    cadena.append("<p>");
                    cadena.append("<strong>");
                    cadena.append(datos);
                    cadena.append(": </strong>");
                    String [] valores=mostrarDatos.get(datos);
                    for(int i=0;i<valores.length;i++){
                        cadena.append(valores[i]);
                    
                    }
                    cadena.append("</p>");
              }else if(datos.equals("dia")){
                    cadena.append("<p><Strong>Fecha de Nacimiento: </Strong>");
                    String [] valores=mostrarDatos.get(datos);
                    for(int i=0;i<valores.length;i++){
                    cadena.append(valores[i]);
                    cadena.append(" de ");
                    
                    }
              
              }else if(datos.equals("mes")){
                  String [] valores=mostrarDatos.get(datos);
                  for(int i=0;i<valores.length;i++){
                  cadena.append(meses[((controlEnteros(valores[i]))-1)]);
                  }
              
              }else if(datos.equals("ano")){
                  String [] valores=mostrarDatos.get(datos);
                  cadena.append(" de ");
                  for(int i=0;i<valores.length;i++){
                  cadena.append(valores[i]);
                  cadena.append("</p>");
                  }
                  
              
              }else if(datos.equals("Usuario")){
                  cadena.append("<p><strong>");
                  cadena.append(datos);
                  cadena.append(" :</strong>");
                  String [] valores=mostrarDatos.get(datos);
                  for(int i=0;i<valores.length;i++){
                      cadena.append(valores[i]);
                  
                  }
                      cadena.append("</p>");
              
              }else if(datos.equals("psswd")){
                  cadena.append("<p><strong>Password: </strong>");
                  String [] valores=mostrarDatos.get(datos);
                  for(int i=0;i<valores.length;i++){
                      cadena.append(valores[i]);
                  
                  }
                  cadena.append("</p>");
              
              
              }else if(datos.equals("deportes")){
                  cadena.append("<p><strong>Aficones: </strong>");
                  String [] valores=mostrarDatos.get(datos);
                  for(int i=0;i<valores.length;i++){
                      if(valores[i]!=null){
                          if(contadorDeEspacios==0){
                            cadena.append(valores[i]);
                            contadorDeEspacios++;
                          }
                      }
                  }
                  
              }else if(datos.equals("lectura")){
                 
                  String [] valores=mostrarDatos.get(datos);
                  for(int i=0;i<valores.length;i++){
                      if(valores[i]!=null){
                          if(contadorDeEspacios==0){
                            cadena.append(valores[i]);
                            contadorDeEspacios++;
                          }else{
                            cadena.append(", ");
                            cadena.append(valores[i]);
                          
                          }
                      }
                  }
                  
              }else if(datos.equals("cine")){
                 
                  String [] valores=mostrarDatos.get(datos);
                  for(int i=0;i<valores.length;i++){
                      if(valores[i]!=null){
                          if(contadorDeEspacios==0){
                            cadena.append(valores[i]);
                            contadorDeEspacios++;
                          }else{
                            cadena.append(", ");
                            cadena.append(valores[i]);
                          
                          }
                      }
                  }
                  
              }else if(datos.equals("Viajes")){
                 
                  String [] valores=mostrarDatos.get(datos);
                  for(int i=0;i<valores.length;i++){
                      if(valores[i]!=null){
                          if(contadorDeEspacios==0){
                            cadena.append(valores[i]);
                            contadorDeEspacios++;
                          }else{
                            cadena.append(", ");
                            cadena.append(valores[i]);
                          
                          }
                      }
                  }
                  
              }
             
           
              
          
          
          }
     
      cadenaDevuelta+=cadena;
      return cadenaDevuelta;
      }
          
     public String mostrarFecha(int dd,int mm,int yyyy,boolean esCorrectaFecha){
         //para no ocupar mucho espacio en memoria y el servlet se haga muy lento añadiendo líneas en una cadena tipo string
         //lo devuelvo como un string y con outprintln muestro la cadena formada;
         String cadena="";
         StringBuilder construirCadena=new StringBuilder();
         construirCadena.append("<p><label for=\"Fenac\">Fecha de Nacimiento:&nbsp;&nbsp; </label><select name=\"dia\">");
          
                for(int i=1;i<32;i++){
                    if((dd==i) && esCorrectaFecha){
                        construirCadena.append("<option value=\"").append(i).append("\" selected>").append(i).append("</option>");
                        
                    }else{
                    
                        construirCadena.append("<option value=\"").append(i).append("\">").append(i).append("</option>");
                    }
                
                }
                construirCadena.append("</select></select><label>&nbsp;&nbsp;/&nbsp;&nbsp;</label>\n<select name=\"mes\">");
                 for(int i=1;i<13;i++){
                    if((mm==i) && esCorrectaFecha){
                        construirCadena.append("<option value=\"").append(i).append("\" selected>").append(i).append("</option>");
                        
                    }else{
                    
                        construirCadena.append("<option value=\"").append(i).append("\">").append(i).append("</option>");
                    }
                
                }
              construirCadena.append("</select><label>&nbsp;&nbsp;/&nbsp;&nbsp;</label><select name=\"ano\">");
               for(int i=1954;i<2017;i++){
                    if((yyyy==i)&& esCorrectaFecha){
                        construirCadena.append("<option value=\"").append(i).append("\" selected>").append(i).append("</option>");
                        
                    }else{
                    
                        construirCadena.append("<option value=\"").append(i).append("\">").append(i).append("</option>");
                    }
                
                }
               construirCadena.append("</select>");
               cadena+=construirCadena;
         return cadena;
     }
     public boolean comprobarFecha(int dia,int mes,int ano){
         //método en el que compruebo si la fecha es correcta, si es correcta devuleve true y si no devuelve false
         Boolean fechaCorrecta=true;
         
         int[] diasMes={31,28,31,30,31,30,31,31,30,31,30,31};
         int mesActual=mes-1;
         int diaMax=diasMes[mesActual];
                if(mes != 2){
                    if(dia>diaMax){
                        fechaCorrecta=false;
                    }
                
                }else if(mes == 2){
                
                    if (((ano % 4 == 0) && ((ano % 100 != 0)) || ((ano % 400 == 0)))){
                        if(dia>29){
                         fechaCorrecta=false;
                        }
                
                    }else{
                        if(dia > diaMax){
                            fechaCorrecta=false;
                        
                        }
                    
                    }
         
                }
         
        
        return fechaCorrecta;
     }
     public boolean esCorrectoFormulario(int [] errores){
         //Método en el que evaluamos si hay errores en el formulario, si los hay devuleve false
         //Este método lo he creado para que si es correcto muestre la página final, y si no se va hacia el formulario erróneo
         boolean correcto=true;
         for(int i=0;i<errores.length;i++){
             if(errores[i]!=-1){
                 correcto=false;
                 break;
             }
         }
         return correcto;
     }
     public int controlEnteros(String cadena){
         //al convertir la cadena recogida en la fecha, ya sea dia,ya sea mes o año, algunas veces nos devuelve el servlet
         //la pantalla de error 500, y NumberFormatException e, por eso he decido controlar la conversión, si la conversión
         //no se hace bien entonces nos devuelve -1, y comprobamos sobre el código
     int numero;
            try{
                numero=Integer.parseInt(cadena);
            }catch(NumberFormatException e){
                numero=-1;
     
            }
     
        return numero;
     }
     public String mostrarErrores(int [] arrayError){
         //Este método muestra los errores que hay en le formulario, nos entra el array de errores,
         // a continuación lo va añadiendo a una cadena. Actúo con dos Arrays uno contiene enteros
         //y otro el nombre, como añado a cada posición del arrayError el mismo número que el índice del nombre error
         //añado el nombre error con el indice i que es el mismo indice que el nombre del error
         String cadenaError="";
         String[] nombreError={"nombre","fecha","usuario","password"};
         StringBuilder cadena=new StringBuilder();
            for(int i=0;i<arrayError.length;i++){
                if(arrayError[i]!=-1){
                    cadena.append("<p> El campo ");
                    cadena.append(nombreError[i]);
                    cadena.append(" no es correcto</p>");
                    
                }
            
            }
           cadenaError+=cadena; 
     
     return cadenaError;
     }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
