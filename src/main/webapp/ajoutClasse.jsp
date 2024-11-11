<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.sql.*, java.util.*" %>
<html>
<head>
    <title>CLASSE</title>
    <link rel="stylesheet" type="text/css" href="ajoutClasse.css">
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
    <h2>AJOUTER UNE CLASSE</h2>

    <form action="ajoutClasse" method="post">
        <input type="hidden" id="id_classe" name="id_classe" required>

        <select id="niveau" name="niveau" required>
            <option value="">Sélectionnez un niveau</option>
            <option value="TERMINALE">TERMINALE</option>
            <option value="PREMIERE">PREMIÈRE</option>
            <option value="SECOND">SECOND</option>
        </select>

        <select id="serie" name="serie" required>
            <option value="">Sélectionnez une série</option>
            <option value="S2">S2</option>
            <option value="S1">S1</option>
            <option value="L2">L2</option>
            <option value="L'">L'</option>
        </select>
        <select id="reference" name="reference" required>
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
        <br><br>

        <button type="submit">AJOUTER</button>

        <button type="button" onclick="window.location.href='interface_administration.jsp'">Retour</button>
    </form>
</div>
</body>
</html>
