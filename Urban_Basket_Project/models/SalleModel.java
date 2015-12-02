package models;

/**
 * Interface du Model Reservation
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public interface SalleModel {
	
    public String getProprietaire();
    public void setProprietaire(String proprietaire);
    public long getLongitude();
    public void setLongitude(long longitude);
    public Adresse getAdresse();
    public void setAdresse(Adresse adresse);
    public long getLattitude();
    public void setLattitude(long lattitude);
    public int getNbTerrains();
    public void setNbTerrains(int nbTerrains);
	public int getId();
	public void setId(int id);
}
