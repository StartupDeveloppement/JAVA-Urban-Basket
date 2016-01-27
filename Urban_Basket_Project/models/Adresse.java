package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import interfaces.DBClass;

/**
 * Classe representant une Adresse
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */

@DatabaseTable(tableName="ADRESSE")
public class Adresse implements DBClass{
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField
	private int numero;
	@DatabaseField
	private String rue;
	@DatabaseField
	private int codePostal;
	@DatabaseField
	private String ville;
	@DatabaseField
	private String pays;
	
	public Adresse(){}
	
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}