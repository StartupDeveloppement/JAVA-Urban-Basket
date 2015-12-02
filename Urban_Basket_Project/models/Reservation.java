package models;

import java.time.LocalDateTime;

/**
 * Classe implémentant ReservationModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class Reservation {
    LocalDateTime dateResa_Deb;
    LocalDateTime dateResa_Fin;
    UserModel user;
    SalleModel salle;

    public Reservation(LocalDateTime dateResa_Deb, LocalDateTime dateResa_Fin, UserModel user, SalleModel salle) {
        this.dateResa_Deb = dateResa_Deb;
        this.dateResa_Fin = dateResa_Fin;
        this.user = user;
        this.salle = salle;
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

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public SalleModel getSalle() {
		return salle;
	}

	public void setSalle(SalleModel salle) {
		this.salle = salle;
	}

}
