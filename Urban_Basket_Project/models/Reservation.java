package models;

import java.time.LocalDateTime;

/**
 * Classe impl�mentant ReservationModel
 * @author BERGER Nicolas
 * @author SOLIVEAU Nicolas
 */
public class Reservation implements ReservationModel{
    LocalDateTime dateTime;
    UserModel user;
    SalleModel salle;

    public Reservation(LocalDateTime dateTime, UserModel user, SalleModel salle) {
        this.dateTime = dateTime;
        this.user = user;
        this.salle = salle;
    }


}
