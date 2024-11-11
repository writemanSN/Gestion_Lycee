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

@WebServlet(name="ajoutMatiere", value = "/ajoutMatiere")
public class ajoutMatiere extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String nomMatiere = request.getParameter("nom");
        String coefficientStr = request.getParameter("coefficient");
        String idClasseStr = request.getParameter("id_classe");

        // Convertir les paramètres nécessaires
        int coefficient = Integer.parseInt(coefficientStr);
        int idClasse = Integer.parseInt(idClasseStr);

        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connexion à la base de données
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");

            // Insérer la matière dans la table "matiere"
            String sql = "INSERT INTO matiere (nom, coefficient, id_classe) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, nomMatiere);
            pstmt.setInt(2, coefficient);
            pstmt.setInt(3, idClasse);
            pstmt.executeUpdate();

            // Redirection vers la page de confirmation ou de visualisation
            request.getRequestDispatcher("matiere.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}
