package com.projet.gestion_lycee.administration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name="ajoutClasse", value = "/ajoutClasse")
public class ajoutClasse extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String niveau = request.getParameter("niveau");
        String serie = request.getParameter("serie");
        String reference = request.getParameter("reference");


        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");


            // Insérer l'enseignant
            String sql = "INSERT INTO classe (niveau, serie, reference) VALUES (?, ?, ?)";
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, niveau);
            pstmt.setString(2, serie);
            pstmt.setString(3, reference);
            pstmt.executeUpdate();


            request.getRequestDispatcher("classe.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}

