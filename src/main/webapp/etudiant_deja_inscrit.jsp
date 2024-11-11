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
</div>

<div class="menu">
    <div class="menu-options">
        <button class="btn"> Inscription reussi !!</button>
        <button class="btn"><a href="inscription.jsp">RETOUR</a></button>
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

            String matricule = (String) request.getSession().getAttribute("matricule");

            String sql = "SELECT e.matricule, e.prenom, e.nom " +
                    "FROM etudiant e " +
                    "JOIN classe c ON e.niveau = c.niveau AND e.serie = c.serie AND e.reference = c.reference " +
                    "WHERE e.matricule = ?";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
                stmt = conn.prepareStatement(sql);
                stmt.setString(1,matricule );
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String prenom = rs.getString("prenom");
                    String nom = rs.getString("nom");
        %>
        <tr>
            <td><%= matricule %></td>
            <td><%= prenom %></td>
            <td><%= nom %></td>
            <td>
                <form action="recu" method="get" style="display:inline;">
                    <input type="hidden" name="matricule" value="<%= matricule %>">
                    <button type="submit" class="btn-edit">Imprimer le recu</button>
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
