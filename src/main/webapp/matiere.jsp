<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion Matières</title>
    <link rel="stylesheet" href="matiere.css">
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
        <button class="btn" id="add-matiere"><a href="ajoutMatiere.jsp">Ajouter Matière</a></button>
        <button class="btn" id="list-matiere"><a href="matiere.jsp">Lister Matières</a></button>
    </div>
</div>

<div class="matiere-list">
    <table>
        <thead>
        <tr>
            <th>ID Matière</th>
            <th>Nom</th>
            <th>Coefficient</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM matiere";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String id_matiere = rs.getString("id_matiere");
                    String nom = rs.getString("nom");
                    String coefficient = rs.getString("coefficient");
        %>
        <tr>
            <td><%= id_matiere %></td>
            <td><%= nom %></td>
            <td><%= coefficient %></td>
            <td>
                <form action="supprimerMatiere" method="post" style="display:inline;">
                    <input type="hidden" name="id_matiere" value="<%= id_matiere %>">
                    <button type="submit" class="btn-delete">Supprimer</button>
                </form>
                <form action="modifierMatiere" method="get" style="display:inline;">
                    <input type="hidden" name="id_matiere" value="<%= id_matiere %>">
                    <button type="submit" class="btn-edit">Modifier</button>
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
