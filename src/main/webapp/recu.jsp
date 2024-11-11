<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Reçu d'Inscription</title>
    <link rel="stylesheet" href="recu.css">
</head>
<body>
<div class="receipt-container">
    <h1>Reçu d'Inscription</h1>
    <p><strong>Matricule:</strong> <%= request.getAttribute("matricule") %></p>
    <p><strong>Prénom:</strong> <%= request.getAttribute("prenom") %></p>
    <p><strong>Nom:</strong> <%= request.getAttribute("nom") %></p>
    <p><strong>Date de Naissance:</strong> <%= request.getAttribute("date_naissance") %></p>
    <p><strong>Lieu de Naissance:</strong> <%= request.getAttribute("lieu_naissance") %></p>
    <p><strong>Adresse:</strong> <%= request.getAttribute("adresse") %></p>
    <p><strong>Téléphone:</strong> <%= request.getAttribute("telephone") %></p>
    <p><strong>Niveau:</strong> <%= request.getAttribute("niveau") %></p>
    <p><strong>Série:</strong> <%= request.getAttribute("serie") %></p>
    <p><strong>Référence:</strong> <%= request.getAttribute("reference") %></p>
    <p><strong>Date d'Inscription:</strong> <%= request.getAttribute("date_inscription") %></p>

    <div class="signature">
        <p>______________________________</p>
        <p>Signature du Surveillant</p>
    </div>

    <div class="signature">
        <p>Montant__d'_inscription____12 000f____</p>
        <p>Douze mille franc Fca</p>
    </div>

    <button onclick="window.print()">Imprimer le Reçu</button>
</div>
</body>
</html>
