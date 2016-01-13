package models;

import com.j256.ormlite.table.DatabaseTable;

/**
 * Classe représentant une Adresse
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */

@DatabaseTable(tableName="ADRESSE")
public class Adresse {

	private int numero;
	private String rue;
	private int codePostal;
	private String ville;
	private String pays;
	
	public Adresse(int num, String rue, int cP, String ville, String pays){
		this.numero = num;
		this.rue = rue;
		this.codePostal = cP;
		this.ville = ville;
		this.pays = pays;
	}
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getPays() {
		return pays;
	}
	public void setPays(String pays) {
		this.pays = pays;
	}
	public String toString(){
		return this.numero+" "+this.rue+" "+this.codePostal+" "+this.ville+" "+this.pays;
	}
}
