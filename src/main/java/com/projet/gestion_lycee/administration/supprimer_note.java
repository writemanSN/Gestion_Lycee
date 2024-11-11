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

@WebServlet(name ="supprimer_note", value = "/supprimer_note")
public class supprimer_note extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matriculeStr = request.getParameter("matricule");
        int matricule = Integer.parseInt(matriculeStr);

        String id_matiereStr = request.getParameter("id_matiere");
        int id_matiere = Integer.parseInt(id_matiereStr);

        String semestre = request.getParameter("semestre");
        String annees_scolaire = request.getParameter("annees_scolaire");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");

            String sql = "DELETE FROM note WHERE matricule = ? AND id_matiere = ? AND semestre = ? AND annees_scolaire = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matricule);
            pstmt.setInt(2, id_matiere);
            pstmt.setString(3, semestre);
            pstmt.setString(4, annees_scolaire);

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
        request.getRequestDispatcher("gestion_note_admin.jsp").forward(request, response);
    }
}
