package com.projet.gestion_lycee.enseignant;

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

@WebServlet(name="Enseignant_Inscription", value = "/Enseignant_Inscription")
public class Enseignant_Inscription extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String telephone = request.getParameter("telephone");
        String dateNaissance = request.getParameter("date_naissance");
        String motdepasse = request.getParameter("motdepasse");

        Connection con = null;
        PreparedStatement pstmt = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");


            // Insérer l'enseignant
            String sql = "INSERT INTO enseignant (prenom, nom, email, telephone, date_naissance, motdepasse) VALUES (?, ?, ?, ?, ?, ?)";
            pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, prenom);
            pstmt.setString(2, nom);
            pstmt.setString(3, email);
            pstmt.setString(4, telephone);
            pstmt.setDate(5, java.sql.Date.valueOf(dateNaissance));
            pstmt.setString(6, motdepasse);
            pstmt.executeUpdate();


            request.getRequestDispatcher("enseignant_existe.jsp").forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("index.jsp").forward(request,response);
        } finally {
            if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
            if (con != null) try { con.close(); } catch (Exception e) {}
        }
    }
}
