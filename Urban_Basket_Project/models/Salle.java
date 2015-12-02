package models;

/**
 * Classe impl�mentant SalleModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class Salle {
    private String proprietaire;
    private int nbTerrains;
    private Adresse adresse;
    private long lattitude;
    private long longitude;

    public Salle(String proprietaire, int nbTerrains, Adresse adresse){
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

    public Salle(String proprietaire, int nbTerrains,Adresse adresse, long lattitude, long longitude){
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
}
