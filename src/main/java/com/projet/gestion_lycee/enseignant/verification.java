package com.projet.gestion_lycee.enseignant;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "verification", value = "/verification")
public class verification extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Paramètres de connexion à la base de données
        String jdbcURL = "jdbc:mysql://localhost:3306/lycee";
        String jdbcUsername = "root";
        String jdbcPassword = "";

        // SQL pour vérifier les informations de connexion
        String sql = "SELECT * FROM enseignant WHERE email = ? AND motdepasse = ?";

        try (Connection con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement pst = con.prepareStatement(sql)) {

            // Définir les paramètres de la requête
            pst.setString(1, email);
            pst.setString(2, password);

            // Exécuter la requête
            ResultSet rs = pst.executeQuery();

            // Vérifier si les informations sont valides
            if (rs.next()) {
                // Si l'enseignant est trouvé, créer une session et rediriger vers la page d'interface
                int idEnseignant = rs.getInt("id_enseignant");

                // Stocker l'id_enseignant dans la session
                HttpSession session = request.getSession();
                session.setAttribute("id_enseignant", idEnseignant);
                request.getRequestDispatcher("interface_enseignant.jsp").forward(request,response);
            } else {
                // Si l'enseignant n'est pas trouvé, renvoyer à la page de connexion avec un message d'erreur
                 request.getRequestDispatcher("index.jsp").forward(request,response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=2"); // Erreur de connexion à la base de données
        }
    }
}

