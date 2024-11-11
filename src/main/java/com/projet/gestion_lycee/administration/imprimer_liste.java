package com.projet.gestion_lycee.administration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "imprimer_liste", value = "/imprimer_liste")
public class imprimer_liste extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idClasseStr = request.getParameter("id_classe");
        int idClasse = Integer.parseInt(idClasseStr);

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Etudiant> students = new ArrayList<>();

        String sql = "SELECT e.matricule, e.prenom, e.nom, c.niveau, c.serie, c.reference " +
                "FROM etudiant e " +
                "JOIN classe c ON e.niveau = c.niveau AND e.serie = c.serie AND e.reference = c.reference " +
                "WHERE c.id_classe = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idClasse);
            rs = stmt.executeQuery();

            String niveau = "";
            String serie = "";
            String reference = "";

            while (rs.next()) {
                // Récupération des étudiants
                String matricule = rs.getString("matricule");
                String prenom = rs.getString("prenom");
                String nom = rs.getString("nom");

                Etudiant student = new Etudiant(matricule, prenom, nom);
                students.add(student);

                // Récupération des informations de la classe (niveau, série, référence)
                if (niveau.isEmpty() && serie.isEmpty() && reference.isEmpty()) {
                    niveau = rs.getString("niveau");
                    serie = rs.getString("serie");
                    reference = rs.getString("reference");
                }
            }

            // Passer les informations à la JSP
            request.setAttribute("students", students);
            request.setAttribute("niveau", niveau);
            request.setAttribute("serie", serie);
            request.setAttribute("reference", reference);

            request.getRequestDispatcher("/imprimer_liste.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
