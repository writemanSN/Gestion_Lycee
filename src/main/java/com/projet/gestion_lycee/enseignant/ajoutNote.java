package com.projet.gestion_lycee.enseignant;

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

@WebServlet(name = "ajoutNote", value = "/ajoutNote")
public class ajoutNote extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matriculeStr = request.getParameter("matricule");
        int matricule = Integer.parseInt(matriculeStr);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");
            String sql = "SELECT n.* " +
                    "FROM note n " +
                    "JOIN etudiant e ON e.matricule = n.matricule " +
                    "WHERE e.matricule = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, matricule);
            rs = pstmt.executeQuery();

            request.getSession().setAttribute("matricule", matricule);

            if (rs.next()) {
                request.setAttribute("matricule", matricule);
                request.setAttribute("devoir", rs.getString("devoir"));
                request.setAttribute("examen", rs.getString("examen"));
                request.setAttribute("id_note", rs.getString("id_note"));
                request.setAttribute("annees_scolaire", rs.getString("annees_scolaire"));
                request.setAttribute("id_matiere", rs.getString("id_matiere"));
                request.setAttribute("semestre", rs.getString("semestre"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Peut-être mieux d'utiliser un logger
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("ajoutNote.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String examenStr = request.getParameter("examen");
        String devoirStr = request.getParameter("devoir");
        String semestre = request.getParameter("semestre");
        String matriculeStr = request.getParameter("matricule");
        String id_matiereStr = request.getParameter("id_matiere");
        String annees_scolaire = request.getParameter("annees_scolaire");

        int id_matiere = Integer.parseInt(id_matiereStr);
        int matricule = Integer.parseInt(matriculeStr);
        double examen = Double.parseDouble(examenStr); // Corrigé de Integer à Double
        double devoir = Double.parseDouble(devoirStr); // Corrigé de Integer à Double

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
            String sql = "INSERT INTO note (devoir, examen, semestre, matricule, id_matiere, annees_scolaire) " +
                    "VALUES ( ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, devoir);
            pstmt.setDouble(2, examen);
            pstmt.setString(3, semestre);
            pstmt.setInt(4, matricule);
            pstmt.setInt(5, id_matiere);
            pstmt.setString(6, annees_scolaire);
            pstmt.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Peut-être mieux d'utiliser un logger
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        request.getRequestDispatcher("gestion_note.jsp").forward(request, response);
    }
}
