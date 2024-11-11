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

@WebServlet(name = "gestion_note_admin", value = "/gestion_note_admin")
public class gestion_note_admin extends HttpServlet {
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
        request.getRequestDispatcher("gestion_note_admin.jsp").forward(request, response);
    }
}