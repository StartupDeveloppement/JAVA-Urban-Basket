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
    int duree;

    public Reservation(LocalDateTime dateResa_Deb, LocalDateTime dateResa_Fin, UserModel user, SalleModel salle, int duree) {
        this.dateResa_Deb = dateResa_Deb;
        this.dateResa_Fin = dateResa_Fin;
        this.user = user;
        this.salle = salle;
        this.duree = duree;
    }

}
