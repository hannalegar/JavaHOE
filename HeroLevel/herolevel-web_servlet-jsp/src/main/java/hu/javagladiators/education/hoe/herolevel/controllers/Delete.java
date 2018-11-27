/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.education.hoe.herolevel.controllers;

import hu.javagladiators.education.hoe.herolevel.dao.HeroLevelException;
import hu.javagladiators.education.hoe.herolevel.services.HeroLevelResource;
import java.io.IOException;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class Delete extends HttpServlet {
    
    @Inject
    HeroLevelResource resource;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
            if(validate(request.getParameterMap())){
                try {                    
                    long id = Long.parseLong(request.getParameter("id"));
                    resource.delete(id);
                    response.sendRedirect("/HeroLevel");
                } catch (HeroLevelException e) {
                    request.setAttribute("message", e.getMessage());
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            }
            else{
                request.setAttribute("message", "Missing identifier!");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
    }
    
    private boolean validate(Map<String,String[]> paramMap) {
        return paramMap.containsKey("id") && Utils.isNumber(paramMap.get("id")[0]);
    }
    
}
