package com.projet.gestion_lycee.administration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "modifier_etudiant", value = "/modifier_etudiant")
public class modifier_etudiant extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");
            String sql = "SELECT * FROM etudiant WHERE matricule = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, matricule);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("matricule", matricule); // Garder l'identifiant accessible pour doPost
                request.setAttribute("nom", rs.getString("nom"));
                request.setAttribute("prenom", rs.getString("prenom"));
                request.setAttribute("niveau", rs.getString("niveau"));
                request.setAttribute("serie", rs.getString("serie"));
                request.setAttribute("reference", rs.getString("reference"));
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
        request.getRequestDispatcher("etudiant_trouver.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String niveau = request.getParameter("niveau");
        String serie = request.getParameter("serie");
        String reference = request.getParameter("reference");

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
            String sql = "UPDATE etudiant SET nom = ?, prenom = ?, niveau = ?, serie = ?, reference = ? WHERE matricule = ?";

            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.setString(2, prenom);
            pstmt.setString(3, niveau);
            pstmt.setString(4, serie);
            pstmt.setString(5, reference);
            pstmt.setString(6, matricule);
            pstmt.executeUpdate();

            request.getSession().setAttribute("matricule", matricule);
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
        response.sendRedirect("etudiant_deja_inscrit.jsp");
    }
}