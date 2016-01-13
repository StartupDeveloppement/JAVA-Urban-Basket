package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Classe impl�mentant UserModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */

@DatabaseTable(tableName="USER")
public class User implements UserModel {
	@DatabaseField(id=true)	
	private String pseudo;
	@DatabaseField
	private String mdp;
	@DatabaseField
	private String nom;
	@DatabaseField
	private String prenom;
	@DatabaseField
	private String dateNaissance;
	@DatabaseField
	private String mail;

	public User(){}
	
    public User(String pseudo, String mdp, String nom, String prenom, String dateNaissance, String mail) {
        this.pseudo = pseudo;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.mail = mail;
    }

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
}
