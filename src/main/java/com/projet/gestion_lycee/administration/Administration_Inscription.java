package com.projet.gestion_lycee.administration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="Administration_Inscription", value="/Administration_Inscription")
public class Administration_Inscription extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les données du formulaire
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String dateNaissance = request.getParameter("date_naissance");
        String motdepasse = request.getParameter("motdepasse");

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion avec la base de données
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");

            // Préparer la requête SQL pour insérer les données
            String sql = "INSERT INTO administration (prenom, nom, email, telephone, date_naissance, motdepasse) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql);

            // Définir les paramètres de la requête
            pstmt.setString(1, prenom);
            pstmt.setString(2, nom);
            pstmt.setString(3, email);
            pstmt.setString(4, telephone);
            pstmt.setDate(5, java.sql.Date.valueOf(dateNaissance));
            pstmt.setString(6, motdepasse);

            // Exécuter la requête
            pstmt.executeUpdate();

            // Redirection vers la page administration_existe.jsp
            request.getRequestDispatcher("administration_existe.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            // Fermer les ressources
            if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}

