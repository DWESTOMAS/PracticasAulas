/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.practicasaulas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tomlu
 */
@WebServlet(name = "FormularioSimple", urlPatterns = {"/FormularioSimple"})
public class FormularioSimple extends HttpServlet {

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
        //boolean correcto=false;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            //String nombre=request.getParameter("nombre");
            //String em=request.getParameter("email");
            //String[] marcado=request.getParameterValues("chek");
            Enumeration<String> parametros=request.getParameterNames();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<link href='./CSS/Style.css' rel='stylesheet' type='text/css'>");
            out.println("<title>Servlet FormularioSimple</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<header id='cabecera'>Formulario Simple</header>");
            out.println("<div id='caja'>");
            
            while(parametros.hasMoreElements()){
                String nombre=parametros.nextElement();
                if(!nombre.startsWith("env")){
             out.println("<p align='center'>"+nombre+": <strong>"+request.getParameter(nombre)+"</strong></p>");
             }
            
            
            }
            
           /* String nombre1="<p>Nombre:"+nombre+"</p>";
            String eml="<p>E-mail:"+em+"</p>";
            out.println((!nombre.equals("")) ? nombre1:"");
             out.println((!em.equals("")) ? eml:"");
             for(int i=0; i<marcado.length;i++){
                 if(marcado[i].equals("on")){
                    out.println("<p>Ha marcado el checkbox</p>");
                 }
             }*/
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
            
    }
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
        boolean correcto=false;
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
        boolean correcto=false;
         response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormularioSimple</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Vamos a ver si responde</h1>");
            
            out.println("</body>");
            out.println("</html>");
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
/* if(!correcto) {
    out.println(<p>no se han introducido datos</P)
}Enumeration<string> parametros=request.getParameterNames();
while(parametros.hasMoreElements()){
    String nombre=parametros.nextElement();
    if(!nombre.starsWidth("env")){
    out.println(nombre+ "-<strong>"+request.getParameter(nombre)+"</strong><br/>")
}
}
}
out.print("<p><a href='"+request.getContexPath()+"'incial</a></p>

*/