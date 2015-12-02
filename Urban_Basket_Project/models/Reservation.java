package models;

import java.time.LocalDateTime;

/**
 * Classe implémentant ReservationModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class Reservation {
    LocalDateTime dateTime;
    UserModel user;
    SalleModel salle;
    int duree;

    public Reservation(LocalDateTime dateTime, UserModel user, SalleModel salle, int duree) {
        this.dateTime = dateTime;
        this.user = user;
        this.salle = salle;
        this.duree = duree;
    }

}
