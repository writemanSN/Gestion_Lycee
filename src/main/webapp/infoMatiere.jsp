<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, java.util.*" %>
<html>
<head>
    <title>AJOUTER UNE MATIÈRE</title>
    <link rel="stylesheet" type="text/css" href="ajoutMatiere.css">
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

<div class="content">
    <h2>AJOUTER UNE MATIÈRE</h2>

    <form action="modifierMatiere" method="post">
        <input type="hidden" id="id_matiere" name="id_matiere" value="${id_matiere}">

        <label>Sélectionnez une classe :</label>
        <select name="id_classe" id="id_classe" required>
            <option value="" disabled selected>Choisir une classe</option>
            <%
                try {
                    String url = "jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT";
                    String username = "root";
                    String password = "";
                    Connection conn = DriverManager.getConnection(url, username, password);
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT DISTINCT c.id_classe, c.niveau, c.serie " +
                            "FROM classe c " ;
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        int id_classe = rs.getInt("id_classe");
                        String niveau = rs.getString("niveau");
                        String serie = rs.getString("serie");
            %>
            <option value="<%= id_classe %>"><%= id_classe %> - <%= niveau %> - <%= serie %></option>
            <%
                    }
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            %>
        </select>

        <label for="nom_matiere">Nom de la matière :</label>
        <input type="text" id="nom_matiere" name="nom" placeholder="Nom de la matière" value="${nom}" required>

        <label for="coefficient">Coefficient :</label>
        <input type="number" id="coefficient" name="coefficient" placeholder="Coefficient" value="${coefficient}" required>

        <br><br>

        <button type="submit">AJOUTER</button>
        <button type="button" onclick="window.location.href='interface_administration.jsp'">Retour</button>
    </form>
</div>
</body>
</html>
