package com.projet.gestion_lycee.administration;

import jakarta.servlet.RequestDispatcher;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GenererBulletinServlet", value = "/GenererBulletinServlet")
public class GenererBulletinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matricule = request.getParameter("matricule");
        String semestre = request.getParameter("semestre");
        String anneeScolaire = request.getParameter("annees_scolaire");

        Bulletin bulletin = new Bulletin();
        bulletin.setMatricule(matricule);
        bulletin.setAnnee(anneeScolaire); // Définit l'année scolaire
        bulletin.setSemestre(semestre); // Définit le semestre

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "")) {
            // Récupérer les informations de l'étudiant
            String sqlEtudiant = "SELECT prenom, nom, niveau, serie, reference FROM etudiant WHERE matricule = ?";
            try (PreparedStatement stmt = connection.prepareStatement(sqlEtudiant)) {
                stmt.setString(1, matricule);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    bulletin.setPrenom(rs.getString("prenom"));
                    bulletin.setNom(rs.getString("nom"));
                    bulletin.setNiveau(rs.getString("niveau"));
                    bulletin.setSerie(rs.getString("serie"));
                    bulletin.setReference(rs.getString("reference"));
                }
            }

            // Récupérer la moyenne
            String sqlMoyenne = "SELECT SUM(((n.devoir + n.examen) / 2) * m.coefficient) / SUM(m.coefficient) AS moyenne "
                    + "FROM note n "
                    + "JOIN matiere m ON n.id_matiere = m.id_matiere "
                    + "WHERE n.annees_scolaire = ? AND n.semestre = ? AND n.matricule = ? "
                    + "GROUP BY n.matricule";

            try (PreparedStatement stmt = connection.prepareStatement(sqlMoyenne)) {
                stmt.setString(1, anneeScolaire);
                stmt.setString(2, semestre);
                stmt.setString(3, matricule);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    bulletin.setMoyenne(rs.getDouble("moyenne"));
                } else {
                    bulletin.setMoyenne(0);
                }
            }

            // Récupérer la position
            String sqlPosition = "SELECT RANK() OVER (ORDER BY moyenne DESC) AS position "
                    + "FROM ("
                    + "  SELECT n.matricule, SUM(((n.devoir + n.examen) / 2) * m.coefficient) / SUM(m.coefficient) AS moyenne "
                    + "  FROM note n "
                    + "  JOIN matiere m ON n.id_matiere = m.id_matiere "
                    + "  WHERE n.annees_scolaire = ? AND n.semestre = ? "
                    + "  GROUP BY n.matricule"
                    + ") AS moyennes "
                    + "WHERE matricule = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sqlPosition)) {
                stmt.setString(1, anneeScolaire);
                stmt.setString(2, semestre);
                stmt.setString(3, matricule);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    bulletin.setPosition(rs.getInt("position"));
                } else {
                    bulletin.setPosition(0);
                }
            }

            // Récupérer les notes par matière
            String sqlNotes = "SELECT m.nom, n.devoir, n.examen, m.coefficient "
                    + "FROM note n "
                    + "JOIN matiere m ON n.id_matiere = m.id_matiere "
                    + "WHERE n.matricule = ? AND n.annees_scolaire = ? AND n.semestre = ?";

            try (PreparedStatement stmt = connection.prepareStatement(sqlNotes)) {
                stmt.setString(1, matricule);
                stmt.setString(2, anneeScolaire);
                stmt.setString(3, semestre);

                ResultSet rs = stmt.executeQuery();
                List<Note> notes = new ArrayList<>();
                while (rs.next()) {
                    Note note = new Note();
                    note.setMatiere(rs.getString("nom"));
                    note.setDevoir(rs.getDouble("devoir"));
                    note.setExamen(rs.getDouble("examen"));
                    note.setCoefficient(rs.getInt("coefficient"));
                    notes.add(note);
                }
                bulletin.setNotes(notes);
            }

            // Passer le bulletin à la page JSP
            if (bulletin.getMoyenne() != 0 || !bulletin.getNotes().isEmpty()) {
                request.getSession().setAttribute("bulletin", bulletin);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Bulletin.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Bulletin non trouvé pour le matricule spécifié.");
            }

        } catch (SQLException e) {
            throw new ServletException("Erreur de base de données : " + e.getMessage(), e);
        }
    }
}
