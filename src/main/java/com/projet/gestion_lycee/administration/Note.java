package com.projet.gestion_lycee.administration;
public class Note {
    private String matiere;
    private double devoir;
    private double examen;
    private int coefficient;

    // Getters and Setters
    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public double getDevoir() {
        return devoir;
    }

    public void setDevoir(double devoir) {
        this.devoir = devoir;
    }

    public double getExamen() {
        return examen;
    }

    public void setExamen(double examen) {
        this.examen = examen;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
}
