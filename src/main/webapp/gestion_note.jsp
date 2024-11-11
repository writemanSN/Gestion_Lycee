<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Notes</title>
    <link rel="stylesheet" href="interface_enseignant.css">
</head>
<body>
<img src="interfaceAdmin.png" alt="Background Image" class="background-image">

<div class="menu-bar">
    <h1>GESTION ETABLISSEMENT</h1>
</div>

<div class="menu">
    <div class="menu-options">
        <button class="btn" id="add-client"><a href="interface_enseignant.jsp">RETOUR</a></button>
    </div>
</div>

<div class="client-list">
    <table>
        <thead>
        <tr>
            <th>NOM</th>
            <th>PRENOM</th>
            <th>ANNEES SCOLAIRE</th>
            <th>DEVOIR</th>
            <th>EXAMEN</th>
            <th>SEMESTRE</th>
            <th>NOM MATIERE</th>
        </tr>
        </thead>
        <tbody>
        <%
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            // Matricule de l'étudiant à filtrer
            int matriculeEtudiant = (Integer) session.getAttribute("matricule"); // Remplacez par le matricule de l'étudiant

            String sql = "SELECT e.nom AS nom_etudiant, e.prenom, n.annees_scolaire, n.devoir, n.examen, n.semestre, n.id_matiere, m.nom AS nom_matiere " +
                    "FROM note n " +
                    "JOIN etudiant e ON n.matricule = e.matricule " +
                    "JOIN matiere m ON m.id_matiere = n.id_matiere " +
                    "WHERE n.matricule = ?";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, matriculeEtudiant);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String nomEtudiant = rs.getString("nom_etudiant");
                    String nomMatiere = rs.getString("nom_matiere");
                    String prenom = rs.getString("prenom");
                    String anneesScolaire = rs.getString("annees_scolaire");
                    double devoir = rs.getDouble("devoir");
                    double examen = rs.getDouble("examen");
                    String semestre = rs.getString("semestre");

        %>
        <tr>
            <td><%= nomEtudiant %></td>
            <td><%= prenom %></td>
            <td><%= anneesScolaire %></td>
            <td><%= devoir %></td>
            <td><%= examen %></td>
            <td><%= semestre %></td>
            <td><%= nomMatiere %></td>

            <td>

            </td>
        </tr>
        <%
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (stmt != null) try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
                if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
            }
        %>
        </tbody>
    </table>
</div>

<button class="scroll-button" id="scrollToTop">↑</button>

<script>
    const scrollButton = document.getElementById('scrollToTop');

    window.onscroll = function() {
        if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
            scrollButton.classList.add('show');
        } else {
            scrollButton.classList.remove('show');
        }
    };

    scrollButton.onclick = function() {
        document.body.scrollTop = 0;
        document.documentElement.scrollTop = 0;
    };
</script>
</body>
</html>
