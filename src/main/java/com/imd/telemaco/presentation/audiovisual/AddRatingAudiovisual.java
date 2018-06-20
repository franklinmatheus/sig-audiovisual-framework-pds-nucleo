/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.imd.telemaco.presentation.audiovisual;

import com.imd.telemaco.business.AudiovisualServices;
import com.imd.telemaco.business.ValidateUserServices;
import com.imd.telemaco.business.exception.CloseConnectionException;
import com.imd.telemaco.business.exception.DatabaseException;
import com.imd.telemaco.business.exception.UserNotExistsException;
import com.imd.telemaco.entity.Rating;
import com.imd.telemaco.entity.User;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author franklin
 * @author Valmir Correa (valmir.jr.correa@gmail.com)
 */
public abstract class AddRatingAudiovisual extends HttpServlet {

    /**
     * Default
     */
    private static final long serialVersionUID = 1L;

    protected AudiovisualServices services;

    @Override
    public abstract void init();

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
        try {
            ValidateUserServices validateUser = new ValidateUserServices();

            String content = request.getParameter("content");
            int stars = Integer.parseInt(request.getParameter("rating"));
            Date date = new Date();
            System.out.println(request.getParameter("idAudiovisual"));
            int idAudiovisual = Integer.parseInt(request.getParameter("idAudiovisual"));
            int idUser = Integer.parseInt(request.getParameter("idUser"));
            User user = validateUser.select(idUser);

            Rating rating = new Rating();
            rating.setComment(content);
            rating.setDate(date);
            rating.setUser(user);
            rating.setIdAudiovisual(idAudiovisual);
            rating.setStars(stars);
            services.addRating(rating);

            ArrayList<Rating> ratings = services.getRatings(idAudiovisual);
            HttpSession session = request.getSession();
            session.setAttribute("ratings", ratings);

            response.sendRedirect("Audiovisual.jsp");
        } catch (DatabaseException | CloseConnectionException | UserNotExistsException e) {
            response.sendRedirect("Error.jsp");
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
