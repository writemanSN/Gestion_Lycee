<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Informations de l'Étudiant</title>
    <link rel="stylesheet" type="text/css" href="inscription.css">
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
    <h2>Informations de l'étudiant</h2>

    <form action="migrer_etudiant" method="post">
        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" value="<%= request.getAttribute("prenom") %>" required><br>

        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" value="<%= request.getAttribute("nom") %>" required><br>

        <label for="date_naissance">Date de Naissance :</label>
        <input type="date" id="date_naissance" name="date_naissance" value="<%= request.getAttribute("date_naissance") %>" required><br>

        <label for="lieu_naissance">Lieu de Naissance :</label>
        <input type="text" id="lieu_naissance" name="lieu_naissance" value="<%= request.getAttribute("lieu_naissance") %>" required><br>

        <label for="adresse">Adresse :</label>
        <input type="text" id="adresse" name="adresse" value="<%= request.getAttribute("adresse") %>" required><br>

        <label for="telephone">Téléphone :</label>
        <input type="text" id="telephone" name="telephone" value="<%= request.getAttribute("telephone") %>" required><br>

        <label for="niveau">Niveau :</label>
        <input type="text" id="niveau" name="niveau" value="<%= request.getAttribute("niveau") %>" required><br>

        <label for="serie">Série :</label>
        <input type="text" id="serie" name="serie" value="<%= request.getAttribute("serie") %>" required><br>

        <label for="reference">Référence :</label>
        <input type="text" id="reference" name="reference" value="<%= request.getAttribute("reference") %>" required><br>

        <button type="submit">Inscrire</button>
    </form>

    <button><a href="inscription.jsp">Retour</a></button>
</div>
</body>
</html>
