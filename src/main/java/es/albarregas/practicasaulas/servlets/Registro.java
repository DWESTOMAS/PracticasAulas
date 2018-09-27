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
         response.setContentType("text/html;charset=UTF-8");
        int[] errores={-1};
        try (PrintWriter out = response.getWriter()) {
            
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
                
                }
            }
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
              out.println("<p>El formulario es correcto</p>");
            
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
             
             
             
              out.println("<form name=\"env\" method=\"Post\" action=\"./Registro\" >");
              out.println( "<fieldset><legend>Datos personales</legend>");
              out.println("<p><label for=\"nombre\">Nombre: </label> <input type=\"text\" name=\"nombre\" maxlength=\"20\" minlength=\"1\"></p>");
              out.println("<p><label for=\"apellidos\">Apelidos: </label><input type=\"text\" name=\"apellidos\" maxlength=\"30\" minlength=\"1\"> </p>");
              out.println("<p><label for=\"sexo\">Sexo:</label><input type=\"radio\" name=\"sexo\" value=\"hombre\">Hombre</p>");
              out.println("<p><label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input type=\"radio\" name=\"sexo\" value=\"mujer\">Mujer</p>");
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
