package models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Classe implémentant ReservationModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class Reservation {
    Timestamp date;
    UserModel user;
    SalleModel salle;
    int duree;

    public Reservation(Timestamp date, UserModel user, SalleModel salle, int duree) {
        this.date = date;
        this.user = user;
        this.salle = salle;
        this.duree = duree;
    }


}
