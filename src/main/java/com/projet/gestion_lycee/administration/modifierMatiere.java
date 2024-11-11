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

@WebServlet(name = "modifierMatiere", value = "/modifierMatiere")
public class modifierMatiere extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_matiereStr = request.getParameter("id_matiere");
        int id_matiere = Integer.parseInt(id_matiereStr);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");
            String sql = "SELECT * FROM matiere WHERE id_matiere = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_matiere);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("id_matiere", id_matiere); // Garder l'identifiant accessible pour doPost
                request.setAttribute("nom", rs.getString("nom"));
                request.setAttribute("coefficient", rs.getString("coefficient"));
                request.setAttribute("id_classe", rs.getString("id_classe"));
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
        request.getRequestDispatcher("infoMatiere.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_matiereStr = request.getParameter("id_matiere");
        String nom = request.getParameter("nom");
        String coefficientStr = request.getParameter("coefficient");
        String id_classeStr = request.getParameter("id_classe");

        int id_matiere = Integer.parseInt(id_matiereStr);
        int id_classe = Integer.parseInt(id_classeStr);
        int coefficient = Integer.parseInt(coefficientStr);

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
            String sql = "UPDATE matiere SET nom = ?, coefficient = ?, id_classe = ? WHERE id_matiere = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.setInt(2, coefficient);
            pstmt.setInt(3, id_classe);
            pstmt.setInt(4, id_matiere);
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
        request.getRequestDispatcher("matiere.jsp").forward(request, response);
    }
}
