<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Résultat de la recherche</title>
    <link rel="stylesheet" href="rechercherEtu.css"> <!-- Lien vers le fichier CSS -->
</head>
<body>
<!-- Image de fond -->
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
<!-- Conteneur pour le contenu -->
<div class="container">


    <table>
        <thead>
        <tr>
            <th>Matricule</th>
            <th>Nom</th>
            <th>Prénom</th>
            <th>Niveau</th>
            <th>Série</th>
            <th>Référence</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${matricule}</td>
            <td>${nom}</td>
            <td>${prenom}</td>
            <td>${niveau}</td>
            <td>${serie}</td>
            <td>${reference}</td>
            <td>
                <form action="modifier_etudiant" method="get">
                    <input type="hidden" name="matricule" value="${matricule}">
                    <button type="submit">S'INSCRIRE</button>
                </form><br>
                <form action="supprimer_etudiant" method="post">
                    <input type="hidden" name="matricule" value="${matricule}">
                    <button type="submit">SUPPRIMER</button>
                </form>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>
