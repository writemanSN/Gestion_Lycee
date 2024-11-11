package com.projet.gestion_lycee.administration;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet(name = "recu", value = "/recu")
public class recu extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");

        String url = "jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT";
        String user = "root";
        String password = "";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            String sql = "SELECT prenom, nom, date_naissance, lieu_naissance, adresse, telephone, niveau, serie, reference, date_inscription " +
                    "FROM etudiant WHERE matricule = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, matricule);
            rs = stmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("matricule", matricule);
                request.setAttribute("prenom", rs.getString("prenom"));
                request.setAttribute("nom", rs.getString("nom"));
                request.setAttribute("date_naissance", rs.getString("date_naissance"));
                request.setAttribute("lieu_naissance", rs.getString("lieu_naissance"));
                request.setAttribute("adresse", rs.getString("adresse"));
                request.setAttribute("telephone", rs.getString("telephone"));
                request.setAttribute("niveau", rs.getString("niveau"));
                request.setAttribute("serie", rs.getString("serie"));
                request.setAttribute("reference", rs.getString("reference"));
                request.setAttribute("date_inscription", rs.getString("date_inscription"));

                // Redirection vers la page JSP pour afficher le reçu
                request.getRequestDispatcher("recu.jsp").forward(request, response);
            } else {
                response.sendRedirect("interface_administration.jsp?error=not_found");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("interface_administration.jsp?error=exception");
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

