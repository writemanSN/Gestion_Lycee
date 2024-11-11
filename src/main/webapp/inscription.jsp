        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, java.util.*" %>
<html>
<head>
    <title>Inscription Administration</title>
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
        <li><a href="index.jsp">SE DECONNECTER</a></li>
    </ul>
</div>

<div class="content">
    <h2>Formulaire d'inscription des étudiants</h2>

    <!-- Barre de recherche -->
    <form action="rechercher_etudiant" method="get" class="search-bar">
        <input type="text" id="search_matricule" name="search_matricule" placeholder="Rechercher par matricule..." required>
        <button type="submit">Rechercher</button>
    </form>

    <form action="inscription_etudiant" method="post">
        <label for="prenom">Prénom :</label>
        <input type="text" id="prenom" name="prenom" required><br>

        <label for="nom">Nom :</label>
        <input type="text" id="nom" name="nom" required><br>

        <label for="date_naissance">Date de Naissance :</label>
        <input type="date" id="date_naissance" name="date_naissance" required><br>

        <label for="lieu_naissance">Lieu de Naissance :</label>
        <input type="text" id="lieu_naissance" name="lieu_naissance" required><br>

        <label for="adresse">Adresse :</label>
        <input type="text" id="adresse" name="adresse" required><br>

        <label for="telephone">Téléphone :</label>
        <input type="text" id="telephone" name="telephone" required><br>

        <select id="niveau" name="niveau" class="select-large" required>
            <option value="">Sélectionnez un niveau</option>
            <option value="TERMINALE">TERMINALE</option>
            <option value="PREMIERE">PREMIÈRE</option>
            <option value="SECOND">SECOND</option>
        </select>

        <select id="serie" name="serie" class="select-large" required>
            <option value="">Sélectionnez une série</option>
            <option value="S2">S2</option>
            <option value="S1">S1</option>
            <option value="L2">L2</option>
            <option value="L'">L'</option>
        </select>

        <select id="reference" name="reference" class="select-large" required>
            <option value="">Sélectionnez une référence</option>
            <option value="A">A</option>
            <option value="B">B</option>
            <option value="C">C</option>
            <option value="D">D</option>
            <option value="E">E</option>
            <option value="F">F</option>
            <option value="G">G</option>
            <option value="H">H</option>
            <option value="I">I</option>
            <option value="J">J</option>
            <option value="K">K</option>
            <option value="L">L</option>
            <option value="M">M</option>
            <option value="N">N</option>
            <option value="O">O</option>
            <option value="P">P</option>
            <option value="Q">Q</option>
        </select>

        <br>

        <input type="hidden" id="date_inscription" name="date_inscription" required><br>

        <button type="submit">S'inscrire</button><br><br>

        <button style="color: white"><a href="interface_administration.jsp">Retour</a></button>
    </form>
</div>
</body>
</html>
