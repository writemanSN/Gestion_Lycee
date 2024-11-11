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
                "Veuillez vous connecter en utilisant les identifiants que vous avez reçus. Assurez-vous de les saisir correctement pour accéder à votre espace" <br>
                <strong> — ---- = — ----</strong><br>
                En cas de probleme contacter le proviseur qu'il vous communique vos identifiants de connexion <br><br> MERCI!
            </p>
        </div>
        <div class="form-section">
            <h2>Connexion</h2>
            <form action="connexion_servlet" method="post">
                <label for="identification">Identification :</label>
                <input type="text" id="identification" name="identification" required><br>
                <label for="password">Mot de passe :</label>
                <input type="password" id="password" name="password" required><br>
                <button type="submit">Se connecter</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
