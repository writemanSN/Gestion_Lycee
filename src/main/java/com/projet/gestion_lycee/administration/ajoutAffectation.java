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

@WebServlet(name="ajoutAffectation", value = "/ajoutAffectation")
public class ajoutAffectation extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String id_classeStr = request.getParameter("id_classe");
        String id_matiereStr = request.getParameter("id_matiere");
        String id_enseignantStr = request.getParameter("id_enseignant");

        // Convertir les paramètres nécessaires
        int id_classe = Integer.parseInt(id_classeStr);
        int id_matiere = Integer.parseInt(id_matiereStr);
        int id_enseignant = Integer.parseInt(id_enseignantStr);


        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connexion à la base de données
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");

            // Insérer la matière dans la table "matiere"
            String sql = "INSERT INTO enseignant_classe_matiere (id_enseignant, id_matiere, id_classe) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id_enseignant);
            pstmt.setInt(2, id_matiere);
            pstmt.setInt(3, id_classe);
            pstmt.executeUpdate();

            // Redirection vers la page de confirmation ou de visualisation
            request.getRequestDispatcher("gestion_affectation.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}
