/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.javagladiators.education.hoe.herolevel.controllers;

import hu.javagladiators.education.hoe.herolevel.dao.HeroLevel;
import hu.javagladiators.education.hoe.herolevel.dao.HeroLevelException;
import hu.javagladiators.education.hoe.herolevel.services.HeroLevelResource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Create", urlPatterns = {"/Create"})
public class Create extends HttpServlet {
    
    @Inject
    HeroLevelResource resource;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!missingParameters(request.getParameterMap()).isEmpty()) {
            String errorMessage = "The following parameters are missing " + String.join(",", missingParameters(request.getParameterMap()));
            request.setAttribute("message", errorMessage);
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        } 
        else {
            try {
                HeroLevel d = new HeroLevel();
                d.setLevelName(request.getParameter("levelName"));
                d.setFromLevel(Integer.parseInt(request.getParameter("fromLevel")));
                d.setUntilLevel(Integer.parseInt(request.getParameter("untilLevel")));
                
                resource.create(d);
                response.sendRedirect("/HeroLevel");
                
            } catch (HeroLevelException ex) {
                request.setAttribute("message", ex.getMessage());
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
            
        }

    }

    private List<String> missingParameters(Map<String, String[]> params) {
        List<String> missingParams = new ArrayList<>();

        if (!params.containsKey("levelName") || params.get("levelName")[0]==null || params.get("levelName")[0].trim().isEmpty()) {
            missingParams.add("levelName");
        }
        if (!params.containsKey("fromLevel") || !Utils.isNumber(params.get("fromLevel")[0])) {
            missingParams.add("fromLevel");
        }
        if (!params.containsKey("untilLevel") || !Utils.isNumber(params.get("untilLevel")[0])) {
            missingParams.add("untilLevel");
        }

        return missingParams;
    }
}
