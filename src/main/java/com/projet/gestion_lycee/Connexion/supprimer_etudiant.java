package com.projet.gestion_lycee.Connexion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name ="supprimer_etudiant", value = "/supprimer_etudiant")
public class supprimer_etudiant extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matriculeStr = request.getParameter("matricule");
        int matricule = Integer.parseInt(matriculeStr);

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Charger le driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connexion à la base de données
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");

            // Démarrer la transaction
            conn.setAutoCommit(false);

            // Suppression des notes associées à l'étudiant
            String sqlNote = "DELETE FROM note WHERE matricule = ?";
            pstmt = conn.prepareStatement(sqlNote);
            pstmt.setInt(1, matricule);
            pstmt.executeUpdate();
            pstmt.close();

            // Suppression de l'étudiant
            String sqlEtudiant = "DELETE FROM etudiant WHERE matricule = ?";
            pstmt = conn.prepareStatement(sqlEtudiant);
            pstmt.setInt(1, matricule);
            pstmt.executeUpdate();

            // Valider la transaction
            conn.commit();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    // Annuler la transaction en cas d'erreur
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                // Fermer les ressources
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Rediriger vers inscription.jsp après suppression
        request.getRequestDispatcher("inscription.jsp").forward(request, response);
    }
}
