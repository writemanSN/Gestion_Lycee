package com.projet.gestion_lycee.administration;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "lister_etudiant_admin", value = "/lister_etudiant_admin")
public class lister_etudiant_admin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_classeStr = request.getParameter("id_classe");
        int id_classe = Integer.parseInt(id_classeStr);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Charger le driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Établir la connexion à la base de données
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");

            // Requête SQL pour récupérer les étudiants en fonction de la classe
            String sql = "SELECT e.matricule, e.prenom, e.nom " +
                    "FROM etudiant e " +
                    "JOIN classe c ON e.niveau = c.niveau AND e.serie = c.serie AND e.reference = c.reference " +
                    "WHERE c.id_classe = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_classe);
            rs = pstmt.executeQuery();

            // Mettre les résultats dans les attributs de requête pour les utiliser dans le JSP
            request.getSession().setAttribute("etudiants", rs);

            // Vérifier si des étudiants ont été trouvés
            if (rs.next()) {
                request.setAttribute("niveau", rs.getString("niveau"));
                request.setAttribute("serie", rs.getString("serie"));
                request.setAttribute("reference", rs.getString("reference"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Fermer les ressources
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Rediriger vers la page JSP pour afficher les résultats
        request.getRequestDispatcher("lister_etudiant_admin.jsp").forward(request, response);
    }
}

