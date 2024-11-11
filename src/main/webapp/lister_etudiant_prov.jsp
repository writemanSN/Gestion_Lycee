<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion Classe</title>
    <link rel="stylesheet" href="classe.css">
</head>
<body>
<img src="interfaceAdmin.png" alt="Background Image" class="background-image">

<div class="menu-bar">
    <h1>GESTION ETABLISSEMENT</h1>
    <ul>
        <li><a href="inscription.jsp">INSCRIPTION</a></li>
        <li><a href="matiere.jsp">AFFICHES MATIERES</a></li>
        <li><a href="classe.jsp">GESTION CLASSES</a></li>
        <li><a href="interface_second.jsp">GESTION ETUDIANTS</a></li>
    </ul>
</div>

<div class="menu">
    <div class="menu-options">
        <button class="btn"><a href="interfaceproviseur.jsp">RETOUR</a></button>
    </div>
</div>

<div class="client-list">
    <table>
        <thead>
        <tr>
            <th>Matricule</th>
            <th>Prénom</th>
            <th>Nom</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            // Récupérer l'identifiant de la classe depuis le paramètre de la requête
            String idClasseStr = request.getParameter("id_classe");
            int idClasse = Integer.parseInt(idClasseStr);

            String sql = "SELECT e.matricule, e.prenom, e.nom " +
                    "FROM etudiant e " +
                    "JOIN classe c ON e.niveau = c.niveau AND e.serie = c.serie AND e.reference = c.reference " +
                    "WHERE c.id_classe = ?";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idClasse);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String matricule = rs.getString("matricule");
                    String prenom = rs.getString("prenom");
                    String nom = rs.getString("nom");
        %>
        <tr>
            <td><%= matricule %></td>
            <td><%= prenom %></td>
            <td><%= nom %></td>
            <td>
                <form action="gestion_note_admin" method="get" style="display:inline;">
                    <input type="hidden" name="matricule" value="<%= matricule %>">
                    <button type="submit" class="btn-edit">Voir ces Notes</button>
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
    </table> <br>
    <form action="imprimer_liste" method="get" style="display:inline;">
        <input type="hidden" name="id_classe" value="<%= idClasse %>">
        <button type="submit" class="btn-edit">IMPRIMER LA LISTE</button><br><br>
    </form>
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
