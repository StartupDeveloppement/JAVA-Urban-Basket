package models;

import java.util.Date;

/**
 * Classe impl�mentant ReservationModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class Reservation {
    Date date;
    UserModel user;
    SalleModel salle;

    public Reservation(Date date, UserModel user, SalleModel salle) {
        this.date = date;
        this.user = user;
        this.salle = salle;
    }


}
