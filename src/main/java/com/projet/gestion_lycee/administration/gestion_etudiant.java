package com.projet.gestion_lycee.administration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "gestion_etudiant", value = "/gestion_etudiant")
public class gestion_etudiant extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String niveau = request.getParameter("niveau");
        String serie = request.getParameter("serie");
        String reference = request.getParameter("reference");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");

            String sql = "SELECT e.matricule, e.nom, e.prenom, n.devoir, n.examen from etudiant e, note n WHERE e.niveau = ? AND e.serie = ? AND e.reference = ? and e.matricule = n.matricule";


            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, niveau);
            pstmt.setString(2, serie);
            pstmt.setString(3, reference);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("niveau", rs.getString("niveau"));
                request.setAttribute("serie", rs.getString("serie"));
                request.setAttribute("reference", rs.getString("reference"));

                RequestDispatcher dispatcher = request.getRequestDispatcher("gestion_etudiant.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendRedirect("etudiant_non_trouve.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
