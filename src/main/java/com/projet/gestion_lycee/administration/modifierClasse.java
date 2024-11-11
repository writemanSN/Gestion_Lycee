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

@WebServlet(name = "modifierClasse", value = "/modifierClasse")
public class modifierClasse extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_classeStr = request.getParameter("id_classe");
        int id_classe = Integer.parseInt(id_classeStr);

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee", "root", "");
            String sql = "SELECT * FROM classe WHERE id_classe = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id_classe);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("id_classe", id_classe); // Garder l'identifiant accessible pour doPost
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
        request.getRequestDispatcher("infoClasse.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id_classeStr = request.getParameter("id_classe");
        String niveau = request.getParameter("niveau");
        String serie = request.getParameter("serie");
        String reference = request.getParameter("reference");

        int id_classe = Integer.parseInt(id_classeStr);

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
            String sql = "UPDATE classe SET niveau = ?, serie = ?, reference = ? WHERE id_classe = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, niveau);
            pstmt.setString(2, serie);
            pstmt.setString(3, reference);
            pstmt.setInt(4, id_classe);
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
        request.getRequestDispatcher("classe.jsp").forward(request, response);
    }
}
