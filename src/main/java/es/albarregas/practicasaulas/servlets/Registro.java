/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.practicasaulas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
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
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {
    public Registro(){
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
       
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    }
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
        String dia="";
        String mes="";
        String ano="";
        int[] diasMes={31,28,31,30,31,30,31,31,30,31,30,31};
        String[] nombreMes={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};
        String[] nombreError={"nombre","fecha","Usuario","PassWord"};
         response.setContentType("text/html;charset=UTF-8");
         
         String apellidos = request.getParameter("apellidos");
         String sexo=request.getParameter("sexo");
         String nombre=request.getParameter("nombre");
         String usuario=request.getParameter("usuario");
         String pasword=request.getParameter("passwd");
         String deportes=request.getParameter("deportes");
         String lectura=request.getParameter("lectura");
         String viajes=request.getParameter("viajes");
        
         
       
        try (PrintWriter out = response.getWriter()) {
             int[] errores={-1,-1,-1,-1};
            Map<String,String[]> recogidaDatos=request.getParameterMap();
            Iterator it=recogidaDatos.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String,String[]> datos=(Map.Entry<String,String[]>) it.next();
                String nombreCampo=datos.getKey();
                String[] valores=datos.getValue();
                if(nombreCampo.equals("nombre")){
                    if(!request.getParameter(nombreCampo).startsWith("env")){
                        for(int i=0;i<valores.length;i++){
                             
                            if(valores[i].equals(" ")|| valores[i].isEmpty()){
                            
                                errores[0]=1;
                            }
                        }
                    
                    }
                
             
                }else if(nombreCampo.equals("dia")){
                
                
                    if(!request.getParameter(nombreCampo).startsWith("env")){
                    
                    
                        dia=valores[0];
                        //mes=valores[1];
                       // ano=valores[2];
                    }
                
                
                
                }else if(nombreCampo.equals("mes")){
                
                    if(!request.getParameter(nombreCampo).startsWith("env")){
                
                        mes=valores[0];
                    }
                
                }else if(nombreCampo.equals("ano")){
                
                    if(!request.getParameter(nombreCampo).startsWith("env")){
                
                        ano=valores[0];
                    }
                
                }else if(nombreCampo.equals("usuario")){
                    if(!request.getParameter(nombreCampo).startsWith("env")){
                        for(int i=0;i<valores.length;i++){
                             
                            if(valores[i].equals(" ")|| valores[i].isEmpty()){
                            
                                errores[2]=1;
                            }
                        }
                    
                    }  
                    
                }else if(nombreCampo.equals("psswd")){
                    if(!request.getParameter(nombreCampo).startsWith("env")){
                        for(int i=0;i<valores.length;i++){
                             
                            if(valores[i].equals(" ")|| valores[i].isEmpty()){
                            
                                errores[3]=1;
                            }
                        }
                    
                    }  
                    
                }
                
                
                
                
                
            }
            //--------------------calculo la fecha---------------------------------------------//
            int anoEnt=Integer.parseInt(ano);
            int mesEnt=Integer.parseInt(mes);
            int diaEnt=Integer.parseInt(dia);
            int indice=mesEnt-1;
            int diaMax=diasMes[indice];
                if(mesEnt==2){
                    if ((anoEnt % 4 == 0) && ((anoEnt % 100 != 0) || (anoEnt % 400 == 0))){
                        if(diaEnt>29){
                         errores[1]=1;
                        }
                    
                    }else{
                    
                        if(diaEnt>28){
                        errores[1]=1;
                        }
                    
                    }
                
                }else{
                    if(diaEnt>diaMax){
                    
                        errores[1]=1;
                    }
                
                }
            //----------------------fin de calculo fecha----------------------------------------------
            
            //------------------------Vemos si es correcto el formulario---------------------------------
                boolean esCorrecto=true;
                for(int i=0;i<errores.length;i++){
                    if(errores[i]!=-1){
                    esCorrecto=false;
                    }
                }
            
            /* TODO output your page here. You may use following sample code. */
            if(esCorrecto){
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Registro at " + request.getContextPath() + "</h1>");
             //Desplegamos el map en el formulario resuelto si no 
             // out.println("<p>El formulario es correcto"+dia+".."+mes+"...."+ano+"</p>");
               
            out.println("<p>Registro Satisfactorio</p>");
            out.println("<p>Nombre y Apellidos:"+nombre+" "+apellidos+"</p>");
            out.println("<p>Sexo: "+sexo+"</p>");
            out.println("<p>Fecha de nacimiento: "+dia+" de "+nombreMes[mesEnt-1]+ " de "+ano+"</p>");
            out.println("<p>Usuario"+usuario+"</p>");
            out.println("<p>Contraseña:"+ pasword+"</p>");
            
            out.println("</body>");
            out.println("</html>");
            }else{
                
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registro</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>El formulario tiene errores</h1>");
             //Desplegamos el map en el formulario resuelto si no 
             
             for(int i=0;i<errores.length;i++){
                 if(errores[i]!=-1){
                 out.println("<p> El campo "+nombreError[i]+" no es correcto"+errores[i]+"</p>");
                 
                 }
             
             }
             
              out.println("<form name=\"env\" method=\"Post\" action=\"./Registro\" >");
              out.println("<input type=\"hidden\" name=\"nombre\" value=\"" + nombre + "\">\n");
              out.println("<input type=\"hidden\" name=\"apellidos\" value=\"" + apellidos + "\">\n");
              out.println("<input type=\"hidden\" name=\"sexo\" value=\""+sexo+"\">\n");
              out.println("<input type=\"hidden\" name=\"usuario\" value=\""+usuario+"\">\n");
              out.println("<input type=\"hidden\" name=\"psswd\" value=\""+pasword+"\">\n");
              
               String cadenaNombre=((errores[0]==-1)? nombre:"");
              out.println( "<fieldset><legend>Datos personales</legend>");
              out.println("<p><label for=\"nombre\">Nombre: </label> <input type=\"text\" name=\"nombre\" maxlength=\"20\" minlength=\"1\" value=\""+cadenaNombre+"\")></p>");
              out.println("<p><label for=\"apellidos\">Apellidos: </label><input type=\"text\" name=\"apellidos\"  value=\""+apellidos+"\" maxlength=\"30\" minlength=\"1\"> </p>");
              out.println("<p><label for=\"sexo\">Sexo:</label><input type=\"radio\" name=\"sexo\" value=\"hombre\""+((sexo.equals("hombre")?"checked":""))+">Hombre</p>");
              out.println("<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type=\"radio\" name=\"sexo\" value=\"mujer\""+((sexo.equals("mujer")? "checked":""))+">Mujer</p>");
              out.println("<p><label for=\"Fenac\">Fecha de Nacimiento:&nbsp;&nbsp; </label><select name=\"dia\">");
                for(int i=1;i<32;i++){
                    if((errores[1]==-1)&&(diaEnt==i)){
                        out.println("<option value=\""+i+"\" selected>"+i+"</option>");
                        
                    }else{
                    
                        out.println("<option value=\""+i+"\">"+i+"</option>");
                    }
                
                }
                out.println("</select></select><label>&nbsp;&nbsp;/&nbsp;&nbsp;</label>\n<select name=\"mes\">");
                 for(int i=1;i<13;i++){
                    if((errores[1]==-1)&&(mesEnt==i)){
                        out.println("<option value=\""+i+"\" selected>"+i+"</option>");
                        
                    }else{
                    
                        out.println("<option value=\""+i+"\">"+i+"</option>");
                    }
                
                }
              out.println("</select><label>&nbsp;&nbsp;/&nbsp;&nbsp;</label>\n" +
"                        <select name=\"ano\">");
               for(int i=1955;i<2017;i++){
                    if((errores[1]==-1)&&(anoEnt==i)){
                        out.println("<option value=\""+i+"\" selected>"+i+"</option>");
                        
                    }else{
                    
                        out.println("<option value=\""+i+"\">"+i+"</option>");
                    }
                
                }
              
              out.println("</select>");
              out.println("<fieldset><legend>Datos acceso</legend><p><label for=\"usu>\">Usuario:</label><input type=\"text\" name=\"usuario\" value=\""+((errores[2]==-1)? usuario:"")+"\"></p>");
              out.println("<p><label for=\"pas\">PassWord: </label><input type=\"password\" name=\"psswd\" value=\""+((errores[3]==-1)? pasword:"")+"\"></p>");
              out.println("  </fieldset><p>  <input type=\"submit\" value=\"enviar\"></p></form>");
             
              
            out.println("</body>");
            out.println("</html>");
            
            
            
            
            
            }
        }
        }
    

        
        
        
        
        
        
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
