<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.projet.gestion_lycee.administration.Bulletin" %>
<%@ page import="com.projet.gestion_lycee.administration.Note" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
    // Récupérer le bulletin de la session
    Bulletin bulletin = (Bulletin) request.getSession().getAttribute("bulletin");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Bulletin de l'Étudiant</title>
    <link rel="stylesheet" href="Bulletin.css"> <!-- Ajoutez votre CSS ici -->
    <script>
        function imprimerBulletin() {
            window.print();
        }
    </script>
</head>
<body>
<h1>Bulletin Lycée ALPHA MOLO BALDE</h1> <p><strong>Date:</strong> <%= new SimpleDateFormat("dd/MM/yyyy").format(new Date()) %></p>

<% if (bulletin != null) { %>
<h2>Informations Générales</h2>
<p><strong>Matricule:</strong> <%= bulletin.getMatricule() %></p>
<p>Nom: <%= bulletin.getNom() %></p>
<p>Prénom: <%= bulletin.getPrenom() %></p>
<p>Niveau: <%= bulletin.getNiveau() %></p>
<p>Série: <%= bulletin.getSerie() %></p>
<p>Référence: <%= bulletin.getReference() %></p>
<p>Année Scolaire: <%= bulletin.getAnnee() %></p> <!-- Affiche l'année scolaire -->
<p>Semestre: <%= bulletin.getSemestre() %></p>

<h2>Notes par Matière</h2>
<%
    List<Note> notes = bulletin.getNotes();
    if (notes != null && !notes.isEmpty()) {
%>
<table>
    <thead>
    <tr>
        <th>Matière</th>
        <th>Devoir</th>
        <th>Examen</th>
        <th>Coefficient</th>
    </tr>
    </thead>
    <tbody>
    <% for (Note note : notes) { %>
    <tr>
        <td><%= note.getMatiere() %></td>
        <td><%= note.getDevoir() %></td>
        <td><%= note.getExamen() %></td>
        <td><%= note.getCoefficient() %></td>
    </tr>
    <% } %>
    </tbody>
</table>

<p><strong>Moyenne:</strong> <%= bulletin.getMoyenne() %></p>

<h2>Appréciation du Proviseur</h2>
<textarea rows="6" cols="65" placeholder="Écrivez votre appréciation ici..."></textarea>
<br><br>
<button class="print-button" onclick="imprimerBulletin()">Imprimer le Bulletin</button>
<% } else { %>
<p>Aucune note trouvée pour cet étudiant.</p>
<% } %>
<% } else { %>
<h2>Erreur</h2>
<p>Aucun bulletin trouvé. Veuillez vérifier vos informations.</p>
<% } %>

 <!-- Lien vers la page d'accueil -->
</body>
</html>
