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
    <h2>FAITE UNE AFFECTATION POUR PLUS DE SECURITE</h2>

    <form action="ajoutAffectation" method="post">

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
                    String sql = "SELECT DISTINCT c.id_classe, c.niveau, c.serie, c.reference " +
                            "FROM classe c " ;
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        int id_classe = rs.getInt("id_classe");
                        String niveau = rs.getString("niveau");
                        String serie = rs.getString("serie");
                        String reference = rs.getString("reference");
            %>
            <option value="<%= id_classe %>"><%= id_classe %> - <%= niveau %> - <%= serie %> - <%= reference %></option>
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
        <label>Sélectionnez une matiere :</label>
        <select name="id_matiere" required>
            <option value="" disabled selected>Choisir une matiere</option>
            <%
                try {
                    String url = "jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT";
                    String username = "root";
                    String password = "";
                    Connection conn = DriverManager.getConnection(url, username, password);
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT DISTINCT id_matiere, nom, coefficient FROM matiere";
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        int id_matiere = rs.getInt("id_matiere");
                        String nom = rs.getString("nom");
                        int coefficient = rs.getInt("coefficient");
            %>
            <option value="<%= id_matiere %>"><%= nom %> - <%= coefficient %></option>
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

        <label>Sélectionnez un enseignant :</label>
        <select name="id_enseignant" id="id_enseignant" required>
            <option value="" disabled selected>Choisir un enseignant</option>
            <%
                try {
                    String url = "jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT";
                    String username = "root";
                    String password = "";
                    Connection conn = DriverManager.getConnection(url, username, password);
                    Statement stmt = conn.createStatement();
                    String sql = "SELECT DISTINCT id_enseignant, nom, prenom FROM enseignant";
                    ResultSet rs = stmt.executeQuery(sql);
                    while (rs.next()) {
                        int id_enseignant = rs.getInt("id_enseignant");
                        String nom = rs.getString("nom");
                        String prenom = rs.getString("prenom");
            %>
            <option value="<%= id_enseignant %>"><%= nom %> - <%= prenom %></option>
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




        <br><br>

        <button type="submit">AJOUTER</button>
        <button type="button" onclick="window.location.href='interface_administration.jsp'">Retour</button>
    </form>
</div>
</body>
</html>
