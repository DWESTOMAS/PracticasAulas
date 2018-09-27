/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.practicasaulas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
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
@WebServlet(name = "FormularioComplejo", urlPatterns = {"/FormularioComplejo"})
public class FormularioComplejo extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @param correcto
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,boolean correcto)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
       
        /* PrintWriter.out=response.getWriter();*/
        
        
        
        try (PrintWriter out = response.getWriter()) {
            String noValores="";
            if(correcto){
                noValores+="No se han introducido valores";
            }else{
                noValores+="Se han introducido valores";
            }
            //Enumeration<String> parametros=request.getParameterNames();
            
            
            Map<String, String[]> datos=request.getParameterMap();
            Iterator it=datos.entrySet().iterator();
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FormularioComplejo</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FormularioComplejo at " +  noValores+ "</h1>");
            while(it.hasNext()){
                Map.Entry<String,String[]> recogidaDatos=(Map.Entry<String,String[]>) it.next();
                String nombreCampo=recogidaDatos.getKey();
                String[] valores=recogidaDatos.getValue();
                if(!request.getParameter(nombreCampo).startsWith("env")){
                    for(int i=0;i<valores.length;i++){
                        if(!(valores[i].equals("") || valores[i].equals("Enviar") )){
                            
                            out.println("<p>"+valores[i]+"</p>");
                        }else{
                        
                            out.println("<p>No se ha introducido valores</p>");
                        }
                    
                    }
                
                
                
                }
            }
            out.println("<p><a href="+request.getContextPath()+">Volver</a></p>");
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
        boolean correcto=true;
        processRequest(request, response,correcto);
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
        boolean correcto=false;
        processRequest(request, response,correcto);
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
