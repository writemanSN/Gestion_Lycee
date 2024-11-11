<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion Enseignant Classe Matiere</title>
    <link rel="stylesheet" href="classe.css">
</head>
<body>
<img src="interfaceAdmin.png" alt="Background Image" class="background-image">

<div class="menu-bar">
    <h1>GESTION ETABLISSEMENT</h1>
    <ul>
        <li><a href="inscription.jsp">INSCRIPTION</a></li>
        <li><a href="matiere.jsp">AFFICHER MATIERES</a></li>
        <li><a href="classe.jsp">GESTION CLASSES</a></li>
        <li><a href="interface_second.jsp">GESTION ETUDIANTS</a></li>
    </ul>
</div>

<div class="menu">
    <div class="menu-options">
        <button class="btn" id="add-client"><a href="ajoutClasse.jsp">Ajouter Classe</a></button>
        <button class="btn" id="list-client"><a href="classe.jsp">Lister Classes</a></button>
        <button class="btn"><a href="classe_enseignant.jsp">Ajouter Affectation</a></button>
        <button class="btn"><a href="gestion_affectation.jsp">Lister Affectations</a></button>
    </div>
</div>

<div class="client-list">
    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Enseignant</th>
            <th>Classe</th>
            <th>Matiere</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT ecm.id, en.nom AS enseignant_nom, en.prenom, c.niveau, c.serie, c.reference, m.nom AS matiere_nom " +
                    "FROM enseignant_classe_matiere ecm " +
                    "JOIN enseignant en ON ecm.id_enseignant = en.id_enseignant " +
                    "JOIN classe c ON ecm.id_classe = c.id_classe " +
                    "JOIN matiere m ON ecm.id_matiere = m.id_matiere";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String enseignantNom = rs.getString("enseignant_nom");
                    String prenom = rs.getString("prenom");
                    String niveauClasse = rs.getString("niveau");
                    String serieClasse = rs.getString("serie");
                    String reference = rs.getString("reference");
                    String nomMatiere = rs.getString("matiere_nom");
        %>
        <tr>
            <td><%= id %></td>
            <td><%= enseignantNom + " " + prenom %></td>
            <td><%= niveauClasse + " " + serieClasse + " " + reference %></td>
            <td><%= nomMatiere %></td>
            <td>
                <form action="supprimerAffectation" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= id %>">
                    <button type="submit" class="btn-delete">Supprimer</button>
                </form>
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
