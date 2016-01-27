package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import interfaces.DBClass;

/**
 * Classe implementant SalleModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
@DatabaseTable(tableName="SALLE")
public class Salle implements DBClass{
    @DatabaseField(generatedId = true)
	private int id;
    @DatabaseField
    private String proprietaire;
    @DatabaseField
    private int nbTerrains;
    @DatabaseField(canBeNull=false, foreign=true, foreignAutoCreate=true, foreignAutoRefresh=true)
    private Adresse adresse;
    @DatabaseField
    private long lattitude;
    @DatabaseField
    private long longitude;

    //No args constructor for ORMlite
    public Salle(){}

    //Private pour forcer la saisie d'une adresse ou latitude/longitude
    private Salle(String proprietaire, int nbTerrains){
    	this.proprietaire = proprietaire;
    	this.nbTerrains = nbTerrains;
    }

    public Salle(String proprietaire, int nbTerrains, Adresse adresse){
        this(proprietaire, nbTerrains);
        this.adresse = adresse;
    }

    public Salle(String proprietaire, int nbTerrains,long lattitude, long longitude){
    	this(proprietaire, nbTerrains);
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public Salle(String proprietaire, int nbTerrains,Adresse adresse, long lattitude, long longitude){
    	this(proprietaire, nbTerrains,lattitude, longitude);
        this.adresse = adresse;
    }

	public String toString(){
		return "Salle n°" + this.getId() + " Adresse : " + this.getAdresse().toString();
	}
	
    public String getProprietaire() {
        return proprietaire;
    }
    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }
    public long getLongitude() {
        return longitude;
    }
    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
    public Adresse getAdresse() {
        return adresse;
    }
    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
    public long getLattitude() {
        return lattitude;
    }
    public void setLattitude(long lattitude) {
        this.lattitude = lattitude;
    }
    public int getNbTerrains() {
        return nbTerrains;
    }
    public void setNbTerrains(int nbTerrains) {
        this.nbTerrains = nbTerrains;
    }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}