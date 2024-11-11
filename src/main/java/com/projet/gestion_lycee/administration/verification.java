package com.projet.gestion_lycee.administration;

import java.io.IOException;
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

@WebServlet(name="VerificationServlet", value="/verification_servlet")
public class verification extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer les données du formulaire
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établir la connexion avec la base de données
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");

            // Préparer la requête SQL pour vérifier l'email et le mot de passe
            String sql = "SELECT * FROM administration WHERE email = ? AND motdepasse = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            // Exécuter la requête
            rs = pstmt.executeQuery();

            // Vérifier si les informations de connexion sont correctes
            if (rs.next()) {
                // Si l'utilisateur est trouvé, démarrer une session
                HttpSession session = request.getSession();
                session.setAttribute("admin", rs.getString("prenom") + " " + rs.getString("nom"));
                request.getRequestDispatcher("interface_administration.jsp").forward(request,response);
            } else {
                // Si les informations sont incorrectes, renvoyer à la page de connexion avec un message d'erreur
                request.setAttribute("errorMessage", "Email ou mot de passe incorrect.");
                request.getRequestDispatcher("administration_existe.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } finally {
            // Fermer les ressources
            if (rs != null) try { rs.close(); } catch (Exception e) {}
            if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}

