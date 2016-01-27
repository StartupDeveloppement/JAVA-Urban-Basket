package models;

import java.time.LocalDateTime;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import interfaces.DBClass;

/**
 * Classe implémentant ReservationModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */

@DatabaseTable(tableName="RESERVATION")
public class Reservation implements DBClass {
	@DatabaseField(generatedId=true)
	private Integer id;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
    private LocalDateTime dateResa_Deb;
	@DatabaseField(dataType=DataType.SERIALIZABLE)
    private LocalDateTime dateResa_Fin;
	@DatabaseField(canBeNull=false, foreign=true, foreignAutoRefresh=true)
    private User user;
	@DatabaseField(canBeNull=false, foreign=true, foreignAutoCreate=true, foreignAutoRefresh=true)
    private Salle salle;

	public Reservation(){}
	
    public Reservation(LocalDateTime dateResa_Deb, LocalDateTime dateResa_Fin, User user, Salle salle) {
        this.dateResa_Deb = dateResa_Deb;
        this.dateResa_Fin = dateResa_Fin;
        this.user = user;
        this.salle = salle;
    }

    public String toString(){
    	return "R�servation n�" + this.getId() + " D�but : " + this.getDateResa_Deb() + " Fin : " + this.getDateResa_Fin() + " Salle : " + this.getSalle().getAdresse() + " Membre : " + this.getUser().toString();
    }
    
    public LocalDateTime getDateResa_Deb() {
		return dateResa_Deb;
	}
	public void setDateResa_Deb(LocalDateTime dateResa_Deb) {
		this.dateResa_Deb = dateResa_Deb;
	}
	public LocalDateTime getDateResa_Fin() {
		return dateResa_Fin;
	}
	public void setDateResa_Fin(LocalDateTime dateResa_Fin) {
		this.dateResa_Fin = dateResa_Fin;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Salle getSalle() {
		return salle;
	}
	public void setSalle(Salle salle) {
		this.salle = salle;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
