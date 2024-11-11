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

@WebServlet(name ="supprimerEnseignantProv", value = "/supprimerEnseignantProv")
public class supprimerEnseignantProv extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_enseignantStr = request.getParameter("id_enseignant");
        int id_enseignant = Integer.parseInt(id_enseignantStr);

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");
            String sql = "DELETE FROM enseignant WHERE id_enseignant = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_enseignant);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("liste_enseignant.jsp").forward(request, response);
    }
}



