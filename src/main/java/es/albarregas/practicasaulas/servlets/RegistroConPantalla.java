/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.practicasaulas.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;
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
       
        int contadorErrores=0;
         int[] errores = {-1,-1,-1,-1};
         String[] nombreError={"Nombre","Fecha","Usuario","Password"};
         int dia=0;
         int mes=0;
         int ano=0;
         Boolean deDondeVienes= false;
        Map <String,String[]> recogidaDatos=request.getParameterMap();
        for(String datos:recogidaDatos.keySet()){
            String[] valores=recogidaDatos.get(datos);
            for(int i=0;i<valores.length;i++){
                    if(datos.equals("nombre")){
                        if(valores[i].isEmpty() || valores[i].equals("")){
                        
                                errores[contadorErrores]=contadorErrores;
                                
                        }
                    
                    contadorErrores++;//saco el contador de errores para que  sume sea o no sea error
                    }else if(datos.equals("dia")){
                    
                        dia=Integer.parseInt(valores[i]);
                       
                    
                    }else if(datos.equals("mes")){
                    
                        mes=Integer.parseInt(valores[i]);
                    }else if(datos.equals("ano")){
                    
                        ano=Integer.parseInt(valores[i]);
                        if(!comprobarFecha(dia,mes,ano)){
                        
                            errores[contadorErrores]=contadorErrores;
                           
                        
                        }
                         contadorErrores++;
                    }else if(datos.equals("usuario")){
                        if(valores[i].isEmpty() || valores[i].equals(" ")){
                            errores[contadorErrores]=contadorErrores;
                        
                        }
                        contadorErrores++;
                    
                    }else if(datos.equals("psswd")){
                        if(valores[i].isEmpty() || valores[i].equals(" ")){
                          errores[contadorErrores]=contadorErrores;
                        
                        }
                    
                    
                    }else if(datos.equals("oculto")){
                    
                        if(valores[i].equals("true")){
                        deDondeVienes=true;
                        }
                    
                    }
                    
            
            }
        }
            boolean error=true;
            StringBuilder cadenaErrores=new StringBuilder();
            for(int i=0;i<errores.length;i++){
                if(errores[i]!=-1){
                    error=false;
                }
            
            }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistroConPantalla</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistroConPantalla at " + request.getContextPath() + "</h1>");
            if(deDondeVienes==true){
            if(error!=true){
                
                for(int i=0; i<errores.length;i++){
                   if(errores[i]!=-1){
                       cadenaErrores.append("<p>Hay errores en el campo ");
                       cadenaErrores.append(nombreError[errores[i]]);
                       //out.println("<p>hay errores en el registor</p>");
                       cadenaErrores.append("</p>");
                      
                      
                   }
                   
                
                }
               
             out.println(cadenaErrores);
             out.println(montarFormulario(recogidaDatos));
             
            }
            }else{
            
            out.println("<p> no vienes del formulario html</p>");
            
            }
            
            
            out.println("</body>");
            out.println("</html>");
       
        
        
       // if(){}
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    }
    public String montarFormulario(Map<String,String[]> datos1){
        StringBuilder uno=new StringBuilder();
        String cadena="";
       // uno.append("<form name=\"env\" method=\"Post\" action=\"../RegistroConPantalla\" ><fieldset>\n<legend>Datos personales</legend>\n><p><label for=\"nombre\">Nombre: </label> <input type=\"text\" name=\"nombre\" value=");
        for(String mostrarDatos:datos1.keySet()){
            String [] valores=datos1.get(mostrarDatos);
            String laKey=mostrarDatos;
            
            for(int i=0;i<valores.length;i++){
                uno.append("<p>");
                uno.append(laKey+": "+valores[i]);
                uno.append("</p>");
                
            }
        
        }
        cadena+=uno;
    return cadena;
    
    
    
    }
     public boolean comprobarFecha(int dia,int mes,int ano){
         
         Boolean fechaCorrecta=true;
         
         int[] diasMes={31,28,31,30,31,30,31,31,30,31,30,31};
         int diaMax=diasMes[mes-1];
                if(mes==2){
                    if ((ano % 4 == 0) && ((ano % 100 != 0) || (ano % 400 == 0))){
                        if(dia>29){
                         fechaCorrecta=false;
                        }
                    
                    }else{
                    
                        if(dia>28){
                        fechaCorrecta=false;
                        }
                    
                    }
                
                }else{
                    if(dia>diaMax){
                    
                        fechaCorrecta=false;
                    }
                
                }
         
         
         
        
        return fechaCorrecta;
        }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
