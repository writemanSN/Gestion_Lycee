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

@WebServlet(name = "modifier_note", value = "/modifier_note")
public class modifier_note extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matriculeStr = request.getParameter("matricule");
        int matricule = Integer.parseInt(matriculeStr);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
            String sql = "SELECT n.* FROM note n JOIN etudiant e ON e.matricule = n.matricule WHERE e.matricule = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matricule);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                // Garder l'identifiant accessible pour doPost

                request.setAttribute("semestre", rs.getString("semestre"));
                request.setAttribute("annees_scolaire", rs.getString("annees_scolaire"));
                request.setAttribute("devoir", rs.getString("devoir"));
                request.setAttribute("examen", rs.getString("examen"));
                request.setAttribute("id_matiere", rs.getString("id_matiere"));
                request.setAttribute("matricule", rs.getString("matricule"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Redirige vers la page JSP avec les données de la note
        request.getRequestDispatcher("ajoutNote_admin.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String examenStr = request.getParameter("examen");
        String devoirStr = request.getParameter("devoir");
        String semestre = request.getParameter("semestre");
        String id_matiereStr = request.getParameter("id_matiere");
        String annees_scolaire = request.getParameter("annees_scolaire");

        int id_matiere = Integer.parseInt(id_matiereStr);
        double examen = Double.parseDouble(examenStr); // Conversion en Double pour éviter les erreurs
        double devoir = Double.parseDouble(devoirStr);

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
            String sql = "UPDATE note SET devoir = ?, examen = ?, annees_scolaire = ?, id_matiere = ? where semestre = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, devoir);
            pstmt.setDouble(2, examen);
            pstmt.setString(3, annees_scolaire);
            pstmt.setInt(4, id_matiere);
            pstmt.setString(5, semestre);
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

        // Redirige vers la page de gestion des notes après la mise à jour
        request.getRequestDispatcher("gestion_note_admin.jsp").forward(request, response);
    }
}
