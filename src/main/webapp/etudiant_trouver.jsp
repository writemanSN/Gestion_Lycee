<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Modifier Étudiant</title>
    <link rel="stylesheet" href="etudiant_trouver.css"> <!-- Lien vers le fichier CSS -->
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
<div class="container">
    <h1>Modifier les informations de l'étudiant</h1>
    <form action="modifier_etudiant" method="post">
        <input type="hidden" name="matricule" value="${matricule}">
        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="${nom}" required><br>

        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" value="${prenom}" required><br>

        <label for="niveau">Niveau :</label>
        <input type="text" id="niveau" name="niveau" value="${niveau}" required><br>

        <label for="serie">Série :</label>
        <input type="text" id="serie" name="serie" value="${serie}" required><br>

        <label for="reference">Référence :</label>
        <input type="text" id="reference" name="reference" value="${reference}" required><br>

        <button type="submit">INSCRIPTION</button>
    </form>
    <a href="inscription.jsp">Retour</a>
</div>
</body>
</html>
