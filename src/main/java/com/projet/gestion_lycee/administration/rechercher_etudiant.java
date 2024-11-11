package com.projet.gestion_lycee.administration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "rechercher_etudiant",value = "/rechercher_etudiant")
public class rechercher_etudiant extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String matricule = request.getParameter("search_matricule");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM etudiant WHERE matricule = '" + matricule + "'");

            if (rs.next()) {
                request.setAttribute("matricule", rs.getString("matricule"));
                request.setAttribute("prenom", rs.getString("prenom"));
                request.setAttribute("nom", rs.getString("nom"));
                request.setAttribute("date_naissance", rs.getString("date_naissance"));
                request.setAttribute("lieu_naissance", rs.getString("lieu_naissance"));
                request.setAttribute("adresse", rs.getString("adresse"));
                request.setAttribute("telephone", rs.getString("telephone"));
                request.setAttribute("niveau", rs.getString("niveau"));
                request.setAttribute("serie", rs.getString("serie"));
                request.setAttribute("reference", rs.getString("reference"));

                RequestDispatcher dispatcher = request.getRequestDispatcher("rechercherEtu.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("etudiant_non_trouver.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
