<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.projet.gestion_lycee.administration.Etudiant" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Imprimer Liste Étudiants</title>
  <link rel="stylesheet" href="imprimer_liste.css">
</head>
<body>
<div class="content">
  <h1>Liste des étudiants de la classe</h1>
  <p>
    <strong>Niveau :</strong> <%= request.getAttribute("niveau") %> -
    <strong>Série :</strong> <%= request.getAttribute("serie") %> -
    <strong>Référence :</strong> <%= request.getAttribute("reference") %>
  </p>

  <table>
    <thead>
    <tr>
      <th>Matricule</th>
      <th>Prénom</th>
      <th>Nom</th>
    </tr>
    </thead>
    <tbody>
    <%
      List<Etudiant> students = (List<Etudiant>) request.getAttribute("students");
      if (students != null) {
        for (Etudiant student : students) {
    %>
    <tr>
      <td><%= student.getMatricule() %></td>
      <td><%= student.getPrenom() %></td>
      <td><%= student.getNom() %></td>
    </tr>
    <%
        }
      }
    %>
    </tbody>
  </table>
</div>

<div class="actions">
  <button onclick="window.print()">Imprimer la Liste</button>
</div>
</body>
</html>
