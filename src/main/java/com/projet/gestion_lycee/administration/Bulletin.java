package com.projet.gestion_lycee.administration;

import java.util.List;

public class Bulletin {
    private String matricule;
    private double moyenne;
    private int position;
    private List<Note> notes;
    private Etudiant etudiant;  // Ajouter l'objet Etudiant
    private String classe;       // Ajouter le champ classe
    private String anneeScolaire; // Ajouter le champ année scolaire
    private String semestre;
    private String prenom;
    private String nom;
    private String niveau; // Ajoutez cet attribut pour le niveau
    private String serie;  // Ajoutez cet attribut pour la série
    private String reference;
    private String annee; // Ajoutez cet attribut pour l'année scolaire
    // Ajouter le champ semestre

    // Getters and Setters
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(double moyenne) {
        this.moyenne = moyenne;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Etudiant getEtudiant() {
        return etudiant; // Getter pour l'objet Etudiant
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant; // Setter pour l'objet Etudiant
    }

    public String getClasse() {
        return classe; // Getter pour la classe
    }

    public void setClasse(String classe) {
        this.classe = classe; // Setter pour la classe
    }

    public String getAnneeScolaire() {
        return anneeScolaire; // Getter pour l'année scolaire
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire; // Setter pour l'année scolaire
    }

    public String getSemestre() {
        return semestre; // Getter pour le semestre
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre; // Setter pour le semestre
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }
}
