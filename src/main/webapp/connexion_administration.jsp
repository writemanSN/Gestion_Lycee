<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inscription Enseignant</title>
    <link rel="stylesheet" type="text/css" href="connexion_administration.css">
</head>
<body>
<div class="background-container">
    <img src="index.png" alt="Background Image" class="background-image">
    <div class="menu-bar">
        <h1>GESTION ETABLISSEMENT</h1>
    </div>
    <div class="content">
        <div class="form-section">
            <h2>Inscription Administration</h2>
            <form action="Administration_Inscription" method="post">
                <label for="prenom">Prénom :</label>
                <input type="text" id="prenom" name="prenom" required><br>

                <label for="nom">Nom :</label>
                <input type="text" id="nom" name="nom" required><br>

                <label for="email">Email :</label>
                <input type="email" id="email" name="email" required><br>

                <label for="telephone">Téléphone :</label>
                <input type="text" id="telephone" name="telephone" required><br>

                <label for="date_naissance">Date de Naissance :</label>
                <input type="date" id="date_naissance" name="date_naissance" required><br>

                <label for="motdepasse">Mot de Passe :</label>
                <input type="password" id="motdepasse" name="motdepasse" required><br>

                <button type="submit">S'inscrire</button>
            </form>

            <button onclick="window.location.href='administration_existe.jsp';">J'ai déjà un compte</button><br><br>
            <button style="color: white"><a href="index.jsp">Retour</a></button>
        </div>
    </div>
</div>
</body>
</html>
