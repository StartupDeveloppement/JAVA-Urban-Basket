package models;

/**
 * Classe impl�mentant UserModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class User implements UserModel {
    String pseudo;
    String mdp;
    String nom;
    String prenom;
    String dateNaissance;
    String mail;

    public User(String pseudo, String mdp, String nom, String prenom, String dateNaissance, String mail) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
    }
}
