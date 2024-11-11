<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Notes</title>
    <link rel="stylesheet" href="ajoutNote.css">
</head>
<body>
<img src="interfaceAdmin.png" alt="Background Image" class="background-image">

<div class="menu-bar">
    <h1>GESTION ETABLISSEMENT</h1>
</div>

<div class="menu">
    <div class="menu-options">

    </div>
</div>

<div class="form-container">
    <h2>Veuillez entrer les notes de l'etudiant</h2>
    <form action="modifier_note" method="post">
        <input type="hidden" name="matricule" value="${matricule}">


        <label>Matière:</label>
        <select name="id_matiere" required>
            <%
                Connection conn = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;

                String sqlMatiere = "SELECT id_matiere, nom, coefficient FROM matiere"; // Modifier la requête pour sélectionner le nom et le coefficient
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lycee?serverTimezone=GMT", "root", "");
                    stmt = conn.prepareStatement(sqlMatiere);
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        int idMatiere = rs.getInt("id_matiere");
                        String nomMatiere = rs.getString("nom");
                        int coefficient = rs.getInt("coefficient");
            %>
            <option value="<%= idMatiere %>"><%= nomMatiere %> (Coefficient: <%= coefficient %>)</option>
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
        </select>

        <label>Devoir:</label>
        <input type="number" name="devoir" step="0.01"  value="${devoir}" required>

        <label>Examen:</label>
        <input type="number" name="examen" step="0.01"  value="${examen}" required>

        <label>Semestre:</label>
        <select name="semestre" required>
            <option>Entrer le semestre</option>
            <option value="1er Semestre">1er Semestre</option>
            <option value="2em Semestre">2em Semestre</option>
        </select>

        <label>Années Scolaire:</label>
        <input type="text" name="annees_scolaire"  value="${annees_scolaire}" required>

        <input type="submit" value="Ajouter Note" class="btn-submit"> <br><br>
        <button type="button" class="btn-submitRetour"><a href="gestion_note_admin.jsp">Retour</a></button>
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
