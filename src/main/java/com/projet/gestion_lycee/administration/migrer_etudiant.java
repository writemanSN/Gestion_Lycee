package com.projet.gestion_lycee.administration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.*;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "migrer_etudiant",value = "/migrer_etudiant")
public class migrer_etudiant extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        String nouveauNiveau = request.getParameter("niveau");
        String nouvelleSerie = request.getParameter("serie");
        String nouvelleReference = request.getParameter("reference");

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");

            String sql = "UPDATE etudiant SET niveau = ?, serie = ?, reference = ? WHERE matricule = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nouveauNiveau);
            stmt.setString(2, nouvelleSerie);
            stmt.setString(3, nouvelleReference);
            stmt.setString(4, matricule);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                response.sendRedirect("migration_reussie.jsp");
            } else {
                response.sendRedirect("migration_echouee.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

