package com.projet.gestion_lycee.Connexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "statistique", value = "/statistique")
public class statistique extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        int totalEtudiants = 0;
        int totalSecond = 0;
        int totalPremiere = 0;
        int totalTerminale = 0;
        int totalS1 = 0;
        int totalS2 = 0;
        int totalL2 = 0;
        int totalL1 = 0;
        int totalEnseignants = 0;
        int totalAdministrateurs = 0;

        try {
            // Connexion à la base de données
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");

            stmt = conn.createStatement();

            // Récupérer le nombre total d'étudiants
            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM etudiant");
            if (rs.next()) {
                totalEtudiants = rs.getInt("total");
            }

            // Récupérer le nombre d'étudiants par niveau
            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM etudiant WHERE niveau = 'second'");
            if (rs.next()) {
                totalSecond = rs.getInt("total");
            }

            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM etudiant WHERE niveau = 'premiere'");
            if (rs.next()) {
                totalPremiere = rs.getInt("total");
            }

            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM etudiant WHERE niveau = 'terminale'");
            if (rs.next()) {
                totalTerminale = rs.getInt("total");
            }

            // Récupérer le nombre d'étudiants par série
            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM etudiant WHERE serie = 'S1'");
            if (rs.next()) {
                totalS1 = rs.getInt("total");
            }

            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM etudiant WHERE serie = 'S2'");
            if (rs.next()) {
                totalS2 = rs.getInt("total");
            }

            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM etudiant WHERE serie = 'L2'");
            if (rs.next()) {
                totalL2 = rs.getInt("total");
            }

            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM etudiant WHERE serie = 'L1'");
            if (rs.next()) {
                totalL1 = rs.getInt("total");
            }

            // Récupérer le nombre d'enseignants
            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM enseignant");
            if (rs.next()) {
                totalEnseignants = rs.getInt("total");
            }

            // Récupérer le nombre d'administrateurs
            rs = stmt.executeQuery("SELECT COUNT(*) AS total FROM administration");
            if (rs.next()) {
                totalAdministrateurs = rs.getInt("total");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Passer les données à la page JSP
        request.getSession().setAttribute("totalEtudiants", totalEtudiants);
        request.getSession().setAttribute("totalSecond", totalSecond);
        request.getSession().setAttribute("totalPremiere", totalPremiere);
        request.getSession().setAttribute("totalTerminale", totalTerminale);
        request.getSession().setAttribute("totalS1", totalS1);
        request.getSession().setAttribute("totalS2", totalS2);
        request.getSession().setAttribute("totalL2", totalL2);
        request.getSession().setAttribute("totalL1", totalL1);
        request.getSession().setAttribute("totalEnseignants", totalEnseignants);
        request.getSession().setAttribute("totalAdministrateurs", totalAdministrateurs);

        request.getRequestDispatcher("statistique.jsp").forward(request, response);
    }
}
