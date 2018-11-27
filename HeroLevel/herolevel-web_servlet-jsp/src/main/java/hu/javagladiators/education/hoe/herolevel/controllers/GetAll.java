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
import java.util.List;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HeroLevel", urlPatterns = {"/HeroLevel"})
public class GetAll extends HttpServlet {

    @Inject
    HeroLevelResource resource ;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            List<HeroLevel> all = this.resource.getAll();
            request.setAttribute("dbus", all);
            request.getRequestDispatcher("/heroLevel.jsp").include(request, response);
        } catch (HeroLevelException e) {
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/error.jsp").include(request, response);
        }    
    }
}