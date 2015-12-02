package models;

/**
 * Classe implémentant SalleModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class Salle implements SalleModel{
	private int id;
    private String proprietaire;
    private int nbTerrains;
    private String adresse;
    private long lattitude;
    private long longitude;

    public Salle(String proprietaire, int nbTerrains, String adresse){
        this.proprietaire = proprietaire;
        this.nbTerrains = nbTerrains;
        this.adresse = adresse;
    }

    public Salle(String proprietaire, int nbTerrains,long lattitude, long longitude){
        this.proprietaire = proprietaire;
        this.nbTerrains = nbTerrains;
        this.lattitude = lattitude;
        this.longitude = longitude;
    }

    public Salle(String proprietaire, int nbTerrains,String adresse, long lattitude, long longitude){
        this.proprietaire = proprietaire;
        this.nbTerrains = nbTerrains;
        this.adresse = adresse;
        this.lattitude = lattitude;
        this.longitude = longitude;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
