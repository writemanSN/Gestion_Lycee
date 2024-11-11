<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>gestion classe</title>
    <link rel="stylesheet" href="gestion_etudiant.css">
</head>
<body>
<img src="interfaceAdmin.png" alt="Background Image" class="background-image">

<div class="menu-bar">
    <h1>GESTION ETABLISSEMENT</h1>

</div>

<div class="menu">
    <div class="menu-options">
        <button class="btn" id="add-client"><a href="connexion_enseignant.jsp">RETOUR A LA CONNECTION</a></button>
    </div>
</div>

<div class="client-list">
    <table>
        <thead>
        <tr>
            <th>id_classe</th>
            <th>NIVEAU</th>
            <th>SERIE</th>
            <th>REFERENCE</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

            // ID de l'enseignant à filtrer
            int idEnseignant = (Integer) session.getAttribute("id_enseignant"); // Remplacez par l'ID de l'enseignant

            String sql = "SELECT c.id_classe, c.niveau, c.serie, c.reference " +
                    "FROM classe c " +
                    "JOIN enseignant_classe_matiere ecm ON c.id_classe = ecm.id_classe " +
                    "WHERE ecm.id_enseignant = ?";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, idEnseignant);
                rs = stmt.executeQuery();

                while (rs.next()) {
                    String id_classe = rs.getString("id_classe");
                    String niveau = rs.getString("niveau");
                    String serie = rs.getString("serie");
                    String reference = rs.getString("reference");
        %>
        <tr>
            <td><%= id_classe %></td>
            <td><%= niveau %></td>
            <td><%= serie %></td>
            <td><%= reference %></td>
            <td>
                <form action="lister_etudiant" method="get" style="display:inline;">
                    <input type="hidden" name="id_classe" value="<%= id_classe %>">
                    <button type="submit" class="btn-edit">VOIR LES ETUDIANTS</button>
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
