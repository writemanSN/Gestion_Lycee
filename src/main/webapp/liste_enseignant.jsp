<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion Enseignants</title>
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
        <button class="btn" id="add-client"><a href="liste_admin.jsp">ADMINISTRATEURS</a></button>
        <button class="btn" id="list-client"><a href="liste_enseignant.jsp">ENSEIGNANTS</a></button>
    </div>
</div>

<div class="client-list">
    <table>
        <thead>
        <tr>
            <th>ID Enseignant</th>
            <th>Prénom</th>
            <th>Nom</th>
            <th>Téléphone</th>
            <th>Email</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            Connection conn = null;
            Statement stmt = null;
            ResultSet rs = null;

            String sql = "SELECT * FROM enseignant";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    String id_enseignant = rs.getString("id_enseignant");
                    String prenom = rs.getString("prenom");
                    String nom = rs.getString("nom");
                    String telephone = rs.getString("telephone");
                    String email = rs.getString("email");
        %>
        <tr>
            <td><%= id_enseignant %></td>
            <td><%= prenom %></td>
            <td><%= nom %></td>
            <td><%= telephone %></td>
            <td><%= email %></td>
            <td>
                <form action="supprimerEnseignantProv" method="post" style="display:inline;">
                    <input type="hidden" name="id_enseignant" value="<%= id_enseignant %>">
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
