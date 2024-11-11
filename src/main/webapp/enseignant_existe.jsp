<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="index.css">
</head>
<body>
<div class="background-container">
    <img src="index.png" alt="Background Image" class="background-image">
    <div class="menu-bar">
        <h1>GESTION ETABLISSEMENT</h1>
    </div>
    <div class="content">
        <div class="text-section">
            <p>
                "Utilisez l'email et le mot de passe que vous avez fourni lors de votre inscription pour vous connecter
                <br>
                <strong> — ---- = — ---- </strong><br>
                En cas de probleme contacter le proviseur qu'il vous communique vos identifiants de connexion <br><br> MERCI!
            </p>
        </div>
        <div class="form-section">
            <h2>Connexion Enseignant</h2>
            <form action="verification" method="post">
                <label for="email">Email :</label>
                <input type="email" id="email" name="email" required><br>
                <label for="password">Mot de passe :</label>
                <input type="password" id="password" name="password" required><br>
                <button type="submit">Se connecter</button><br><br>
                <button style="color: white"><a href="index.jsp">Retour</a></button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
