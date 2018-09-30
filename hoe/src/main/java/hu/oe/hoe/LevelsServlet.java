/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.oe.hoe;

import hu.oe.service.CustomException;
import hu.oe.service.LevelsService;
import hu.oe.datamodel.Levels;
import hu.oe.datamodel.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hannalegar
 */
@WebServlet(name = "LevelsServlet", urlPatterns = {"/levels"})
public class LevelsServlet extends HttpServlet {
    private static final String APPContextName ="levels";
    
    EntityManager  em = Persistence.createEntityManagerFactory("hu.oe_hoe_war_1.0PU").createEntityManager();
    
    private List<Levels> getSystemLevels(){
        List<Levels> res = em.createQuery("SELECT a FROM Levels a").getResultList();
        System.out.println(res.size());
        return res;
    }
    
    @Override
    public void init() throws ServletException {
        super.init(); 
        getServletContext().setAttribute(APPContextName,new ArrayList<>());
        
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LevelsServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LevelsServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");  
        request.setAttribute("levels",getSystemLevels());
        getServletContext().getRequestDispatcher("/levels.jsp").include(request, response);
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
        Levels newHero = new Levels(request.getParameter("pname"), request.getParameter("pdesc"));
        
        List<Levels> levels =getSystemLevels();
        
        if(request.getSession().getAttribute("user")!=null){
            boolean valid = true;
            User user = ((User)request.getSession().getAttribute("user"));
            LevelsService service = new LevelsService();
            
            try{
                service.add(newHero, user.getAccessories());
                doGet(request, response);
               
            }
            catch(CustomException e){
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().print("Ilyen nevű hős már létezik");
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
