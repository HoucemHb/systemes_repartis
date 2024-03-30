package systemes_repartis.TP1.Ex3;

import java.io.Serializable;

public class Personne implements Serializable {
    private Integer age;
    private String nom;

    public Personne(Integer age, String nom) {
        this.age = age;
        this.nom = nom;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Personne{" +
                "age=" + age +
                ", nom='" + nom + '\'' +
                '}';
    }
}
