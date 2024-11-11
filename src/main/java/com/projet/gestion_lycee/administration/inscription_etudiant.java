package com.projet.gestion_lycee.administration;

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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "inscription_etudiant",value = "/inscription_etudiant")
public class inscription_etudiant extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération des données du formulaire
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String dateNaissance = request.getParameter("date_naissance");
        String lieuNaissance = request.getParameter("lieu_naissance");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        String niveau = request.getParameter("niveau");
        String serie = request.getParameter("serie");
        String reference = request.getParameter("reference");

        // Obtention de la date d'inscription (date du jour)
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dateInscription = today.format(formatter);

        // Connexion à la base de données
        String url = "jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT"; // Remplacez par l'URL de votre base de données
        String user = "root"; // Remplacez par votre utilisateur MySQL
        String password = ""; // Remplacez par votre mot de passe MySQL

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            // Insertion des données dans la table 'etudiant'
            String sql = "INSERT INTO etudiant (prenom, nom, date_naissance, lieu_naissance, adresse, telephone, niveau, serie, reference, date_inscription) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, prenom);
            ps.setString(2, nom);
            ps.setString(3, dateNaissance);
            ps.setString(4, lieuNaissance);
            ps.setString(5, adresse);
            ps.setString(6, telephone);
            ps.setString(7, niveau);
            ps.setString(8, serie);
            ps.setString(9, reference);
            ps.setString(10, dateInscription);

            request.getSession().setAttribute("telephone", telephone);


            int rowsInserted = ps.executeUpdate();

            if (rowsInserted > 0) {
                // Redirection vers la page d'inscription avec succès
                request.getRequestDispatcher("etudiant_inscrit.jsp").forward(request,response);
            } else {
                // Redirection vers la page d'administration en cas d'échec
                request.getRequestDispatcher("interface_administration.jsp").forward(request,response);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            // Redirection vers la page d'administration en cas d'erreur
            response.sendRedirect("interface_administration.jsp?error=true");

        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

